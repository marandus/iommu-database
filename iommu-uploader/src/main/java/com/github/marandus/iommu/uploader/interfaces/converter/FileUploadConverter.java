/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.interfaces.converter;

import com.github.marandus.iommu.model.exceptions.ConversionException;
import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;
import org.springframework.web.multipart.MultipartFile;

/**
 * Specification of a converter that processes uploaded file information into internal domain
 * objects.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public interface FileUploadConverter {

    /**
     * Load the provided plain text strings into {@code InputStream} instances and wrap them into an
     * {@link UploadedStreams} domain object.
     *
     * @param dmiDecode Uploaded plain text DMI decode contents
     * @param iommuGroups Uploaded plain text IOMMU groups contents
     * @return Wrapped input streams
     * @throws ConversionException if an Exception occurs during conversion
     */
    public UploadedStreams convert(final String dmiDecode, final String iommuGroups) throws ConversionException;

    /**
     * Retrieve the {@code InputStream} instances from the provided {@link MultipartFile} objects
     * and wrap them into an {@link UploadedStreams} domain object.
     *
     * @param dmiDecode Uploaded multipart file DMI decode contents
     * @param iommuGroups Uploaded multipart file IOMMU groups contents
     * @return Wrapped input streams
     * @throws ConversionException if an Exception occurs during conversion
     */
    public UploadedStreams convert(final MultipartFile dmiDecode, final MultipartFile iommuGroups) throws ConversionException;
}
