/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.dao;

import com.github.marandus.iommu.model.exceptions.DaoException;
import com.github.marandus.iommu.uploader.interfaces.dao.FileUploadDao;
import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link FileUploadDao} that utilizes a file system directory as storage for
 * uploaded files. It is only instanciated, if {@code iommu.upload.storage} is set to {@code FILE}
 * in {@code application.properties} file.
 * <p>
 * Behavior of this DAO is configurable via the {@code iommu.upload.storage.file.*} properties in
 * {@code application.properties} file.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Repository
@ConditionalOnProperty(prefix = "iommu.upload", name = {"storage"}, havingValue = "FILE")
@Slf4j
public class FilesystemFileUploadDao implements FileUploadDao {

    @Autowired
    private FilesystemFileUploadConfig config;

    private final CountDownLatch daoLock;
    private Path uploadRoot;

    public FilesystemFileUploadDao() {
        this.daoLock = new CountDownLatch(1);
        this.uploadRoot = null;
    }

    /**
     * {@inheritDoc }
     * <p>
     * Makes sure the upload root directory defined via {@code iommu.upload.storage.file.directory}
     * in {@code application.properties} file exists, and is readable and writeable. If it does not
     * exist, the method will try to create the whole path to the configured directory.
     *
     * @throws DaoException if directory is not readable, not writeable, or another Exception occurs
     * during preparation
     */
    @Override
    public void prepareBackend() throws DaoException {
        try {
            this.uploadRoot = FileSystems.getDefault().getPath(this.config.getUploadDirectory());

            if (Files.notExists(this.uploadRoot)) {
                Files.createDirectories(this.uploadRoot);
            }

            if (!Files.isReadable(this.uploadRoot)) {
                throw new DaoException("Upload root directory is not readable");
            }
            if (!Files.isWritable(this.uploadRoot)) {
                throw new DaoException("Upload root directory is not writable");
            }

            this.daoLock.countDown();
        }
        catch (IOException | RuntimeException ex) {
            throw new DaoException("Exception during FilesystemFileUploadDao preparation", ex);
        }
    }

    /**
     * {@inheritDoc }
     * <p>
     * This implementation creates a ZIP file that will contain two plain text files
     * {@code dmi_decode.txt} and {@code iommu_groups.txt}. The name of the ZIP file will be
     * {@code id.zip}, where {@code id} is replaced by the provided upload ID. The archive is placed
     * into the upload root directory.
     * <p>
     * The method {@link FilesystemFileUploadDao#prepareBackend()} MUST be called successfully prior
     * to calling this method. If the preparation did not finish, this method will block until
     * either the preparation succeeds or a 30 second timeout is reached. In case of timeout, a
     * {@link DaoException} will be raised.
     *
     * @param id Upload ID, will be used as ZIP file name
     * @param payload Wrapper object for the InputStream instances holding the uploaded contents
     * @throws DaoException if wait timeout is reached or another Exception occurs during storage
     * operation
     */
    @Override
    public void store(String id, UploadedStreams payload) throws DaoException {
        try {
            if (this.daoLock.await(30, TimeUnit.SECONDS)) {
                Path uploadPath = this.uploadRoot.resolve(id + ".zip");
                OutputStream uploadFileStream = Files.newOutputStream(uploadPath, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);

                // Write files to ZIP file
                try (ZipOutputStream zip = new ZipOutputStream(uploadFileStream)) {
                    // Write DMI decode content to ZIP file
                    zip.putNextEntry(new ZipEntry("dmi_decode.txt"));
                    this.writeToStream(payload.getDmiDecodeStream(), zip);
                    zip.closeEntry();

                    // Write IOMMU groups content to ZIP file
                    zip.putNextEntry(new ZipEntry("iommu_groups.txt"));
                    this.writeToStream(payload.getIommuGroupsStream(), zip);
                    zip.closeEntry();

                    zip.flush();
                }
            }
            else {
                throw new DaoException("Timeout while waiting for FilesystemFileUploadDao preparation");
            }
        }
        catch (InterruptedException | IOException | RuntimeException ex) {
            throw new DaoException("Exception during FilesystemFileUploadDao preparation", ex);
        }
    }

    /**
     * Write all bytes from the input stream to the output stream.
     *
     * @param is Input stream to read from
     * @param os Output stream to write to
     * @throws IOException if reading and/or writing the streams fails
     */
    private void writeToStream(InputStream is, OutputStream os) throws IOException {
        byte[] b = new byte[1024];
        int count;

        count = is.read(b);
        while (count > 0) {
            os.write(b, 0, count);
            count = is.read(b);
        }
    }

    /**
     * This class allows convenient access to all {@code iommu.upload.storage.file.*} properties in
     * {@code application.properties} file. It is only instanciated, if {@code iommu.upload.storage}
     * is set to {@code FILE} in {@code application.properties} file.
     *
     * @author Thomas Rix (thomasrix@exodus-project.net)
     */
    @Component
    @ConditionalOnProperty(prefix = "iommu.upload", name = {"storage"}, havingValue = "FILE")
    @Getter
    public static class FilesystemFileUploadConfig {

        /**
         * Path to upload root directory. This must be an absolute path.
         */
        @Value("${iommu.upload.storage.file.directory}")
        private String uploadDirectory;
    }
}
