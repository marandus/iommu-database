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
 * TODO: Missing class level javadoc
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@SpringBootApplication
public class IommuUploaderMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IommuUploaderMain.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IommuUploaderMain.class, args);
    }
}
