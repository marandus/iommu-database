/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.converters;

import com.github.marandus.argval.ArgumentValidator;
import com.github.marandus.iommu.model.exceptions.ConversionException;
import com.github.marandus.iommu.uploader.interfaces.converter.FileUploadConverter;
import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Default implementation of the {@link FileUploadConverter} interface.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Service
@Slf4j
public class FileUploadConverterImpl implements FileUploadConverter {

    /**
     * {@inheritDoc }
     * <p>
     * This implementation loads the bytes of the specified strings into
     * {@code ByteArrayInputStream} instances and adds them to the wrapper object.
     *
     * @param dmiDecode Uploaded plain text DMI decode contents
     * @param iommuGroups Uploaded plain text IOMMU groups contents
     * @return Wrapped input streams
     * @throws ConversionException if an Exception occurs during conversion
     */
    @Override
    public UploadedStreams convert(String dmiDecode, String iommuGroups) throws ConversionException {
        try {
            ArgumentValidator.requireNonBlank(dmiDecode, "DMI decode (String) conversion input");
            ArgumentValidator.requireNonBlank(iommuGroups, "IOMMU groups (String) conversion input");

            InputStream dmiDecodeStream = new ByteArrayInputStream(dmiDecode.getBytes());
            InputStream iommuGroupsStream = new ByteArrayInputStream(iommuGroups.getBytes());

            UploadedStreams rv = new UploadedStreams();
            rv.setDmiDecodeStream(dmiDecodeStream);
            rv.setIommuGroupsStream(iommuGroupsStream);

            return rv;
        }
        catch (RuntimeException ex) {
            throw new ConversionException("Exception during MultipartFile conversion", ex);
        }
    }

    /**
     * {@inheritDoc }
     * <p>
     * This implementation directly wraps the {@code ByteArrayInputStream} instances provided by the
     * {@code MultipartFile} objects. No further processing takes place.
     *
     * @param dmiDecode Uploaded multipart file DMI decode contents
     * @param iommuGroups Uploaded multipart file IOMMU groups contents
     * @return Wrapped input streams
     * @throws ConversionException if an Exception occurs during conversion
     */
    @Override
    public UploadedStreams convert(MultipartFile dmiDecode, MultipartFile iommuGroups) throws ConversionException {
        try {
            ArgumentValidator.requireNonNull(dmiDecode, "DMI decode (MultipartFile) conversion input");
            ArgumentValidator.requireNonNull(iommuGroups, "IOMMU groups (MultipartFile) conversion input");

            UploadedStreams rv = new UploadedStreams();
            rv.setDmiDecodeStream(dmiDecode.getInputStream());
            rv.setIommuGroupsStream(iommuGroups.getInputStream());

            return rv;
        }
        catch (IOException | RuntimeException ex) {
            throw new ConversionException("Exception during MultipartFile conversion", ex);
        }
    }

}
