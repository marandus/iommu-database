/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.exodusproject.iommu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@SpringBootApplication
public class IommuDatabaseMain extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IommuDatabaseMain.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IommuDatabaseMain.class, args);
    }

}
