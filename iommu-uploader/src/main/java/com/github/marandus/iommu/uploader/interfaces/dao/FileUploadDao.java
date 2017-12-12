/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.interfaces.dao;

import com.github.marandus.iommu.model.exceptions.DaoException;
import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;

/**
 * Specification of a DAO that stores and manages uploaded files to an implementation specific
 * storage backend.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public interface FileUploadDao {

    /**
     * Prepare the implementation specific storage backend for further use. This method will be
     * called during application startup and must return successfully. Otherwise application startup
     * will fail.
     * <p>
     * If the storage backend cannot be prepared successfully, this method MUST throw a
     * {@link DaoException} and thus interrupting application startup.
     *
     * @throws DaoException if storage backend preparation fails
     */
    public void prepareBackend() throws DaoException;

    /**
     * Store the uploaded file contents to the implementation specific storage backend. The upload
     * must be identified by the provided {@code id} parameter. It must be possible to access the
     * uploaded data at a later point by providing the upload ID.
     * <p>
     * This method SHOULD block, if {@link FileUploadDao#prepareBackend()} did not return
     * successfully yet. Since this method is called during upload web request, the block MUST be
     * interrupted by a reasonable timeout (e.g. 30 seconds). It MUST NOT block indefinitely to
     * prevent web request from stalling.
     *
     * @param id Unique upload ID
     * @param payload Wrapped input streams
     * @throws DaoException if an Exception occurs during storage operation
     */
    public void store(String id, UploadedStreams payload) throws DaoException;
}
