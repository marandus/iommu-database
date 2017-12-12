/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.interfaces.services;

import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;
import com.github.marandus.iommu.uploader.model.exceptions.UploadException;

/**
 * Specification of a service that processes uploaded files.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public interface FileUploadService {

    /**
     * Process the uploaded files' contents and pass them to the selected storage backend. This
     * method MUST raise an {@link UploadException}, if the storage operation did not complete
     * successfully.
     *
     * @param payload Wrapped input streams with uploaded file contents
     * @throws UploadException if processing or storage opearation fails
     */
    public void processFiles(UploadedStreams payload) throws UploadException;
}
