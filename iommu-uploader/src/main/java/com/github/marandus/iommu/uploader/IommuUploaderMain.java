/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Main class for starting the IOMMU Uploader application. It extends
 * {@code SpringBootServletInitializer}, which allows the application to be served by a servlet
 * container, such as Apache Tomcat.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@SpringBootApplication
public class IommuUploaderMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IommuUploaderMain.class);
    }

    /**
     * Main entry point for the IOMMU Uploader application when running as a standalone application
     * with embedded Tomcat. This is not used when deployed to a full-featured servlet container.
     *
     * @param args Application parameters
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(IommuUploaderMain.class, args);
    }
}
