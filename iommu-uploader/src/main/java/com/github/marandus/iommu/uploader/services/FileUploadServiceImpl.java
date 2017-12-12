/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.services;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.github.marandus.iommu.model.exceptions.DaoException;
import com.github.marandus.iommu.uploader.interfaces.dao.FileUploadDao;
import com.github.marandus.iommu.uploader.interfaces.services.FileUploadService;
import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;
import com.github.marandus.iommu.uploader.model.exceptions.UploadException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: Missing class level javadoc
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadDao uploadDao;

    private final TimeBasedGenerator uuidGenerator;

    /**
     * Create a new service instance. The constructor initializes the UUID generator used by
     * {@link FileUploadServiceImpl#processFiles(com.github.marandus.iommu.uploader.model.domain.UploadedStreams)}.
     */
    public FileUploadServiceImpl() {
        this.uuidGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
    }

    /**
     * {@inheritDoc }
     * <p>
     * This implementation generates a time and location based UUID to be used as upload ID.
     *
     * @param payload Wrapped input streams with uploaded file contents
     * @throws UploadException if processing or storage opearation fails
     */
    @Override
    public void processFiles(UploadedStreams payload) throws UploadException {
        try {
            UUID uploadId = this.uuidGenerator.generate();

            this.uploadDao.store(uploadId.toString(), payload);
        }
        catch (DaoException | RuntimeException ex) {
            throw new UploadException("Exception during uploaded file processing", ex);
        }
    }
}
