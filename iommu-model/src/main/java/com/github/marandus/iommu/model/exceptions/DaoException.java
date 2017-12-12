/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.model.exceptions;

/**
 * An Exception thrown when errors occur during DAO operations.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
public class DaoException extends Exception {

    /**
     * Constructs an instance of {@code DaoException} with the specified detail message.
     *
     * @param msg The detail message
     */
    public DaoException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of {@code DaoException} with the specified detail message and cause.
     *
     * @param msg The detail message
     * @param cause Throwable causing this Exception
     */
    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
