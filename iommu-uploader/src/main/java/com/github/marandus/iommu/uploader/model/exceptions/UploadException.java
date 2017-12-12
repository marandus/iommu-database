/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.model.exceptions;

/**
 * An Exception thrown when errors occur during upload operations.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public class UploadException extends Exception {

    /**
     * Constructs an instance of {@code UploadException} with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UploadException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of {@code UploadException} with the specified detail message and
     * cause.
     *
     * @param msg The detail message
     * @param cause Throwable causing this Exception
     */
    public UploadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
