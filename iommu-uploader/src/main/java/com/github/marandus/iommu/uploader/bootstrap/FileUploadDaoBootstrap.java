/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.bootstrap;

import com.github.marandus.iommu.uploader.interfaces.dao.FileUploadDao;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Prepare all instances of {@link FileUploadDao} by calling {@link FileUploadDao#prepareBackend()}.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Component
@Slf4j
public class FileUploadDaoBootstrap implements CommandLineRunner {

    @Autowired
    private List<FileUploadDao> daos;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting FileUploadDaoBootstrap...");

        for (FileUploadDao dao : this.daos) {
            log.debug("Prepare DAO: {}", dao.getClass().getName());

            dao.prepareBackend();
        }

        log.info("Finished FileUploadDaoBootstrap!");
    }
}
