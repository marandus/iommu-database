/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.model.domain;

import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Wrapper object for {@code InputStream} instances holding the uploaded file contents.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Getter
@Setter
@EqualsAndHashCode
public class UploadedStreams {

    /**
     * Uploaded DMI decode file contents.
     */
    private InputStream dmiDecodeStream;

    /**
     * Uploaded IOMMU groups file contents.
     */
    private InputStream iommuGroupsStream;
}
