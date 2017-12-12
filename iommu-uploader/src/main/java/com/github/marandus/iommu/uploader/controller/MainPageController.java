/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.controller;

import com.github.marandus.iommu.uploader.interfaces.converter.FileUploadConverter;
import com.github.marandus.iommu.uploader.interfaces.services.FileUploadService;
import com.github.marandus.iommu.uploader.model.domain.UploadedStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Serve the main page of the IOMMU Uploader application. Containing the {@code index.html} file and
 * upload targets for {@code MultipartFile} and {@code String}.
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Controller
@Slf4j
public class MainPageController {

    @Autowired
    private FileUploadConverter fileUploadConverter;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * Serve the {@code index.html} file of the IOMMU Uploader application
     *
     * @param model Template model
     * @return Index template
     */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    /**
     * Allow upload of DMI decode and IOMMU groups files as {@code MultipartFile} instances.
     *
     * @param dmiDecode MultipartFile instance DMI decode file
     * @param iommuGroups MultipartFile instance IOMMU groups file
     * @param redirectAttributes Allows to pass attributes to the {@code Model} of the redirect
     * target
     * @return Redirect to index page
     *
     * @throws Exception
     */
    @PostMapping("/upload/file")
    public String handleFileUpload(@RequestParam("dmiDecode") MultipartFile dmiDecode, @RequestParam("iommuGroups") MultipartFile iommuGroups, RedirectAttributes redirectAttributes) throws Exception {

        log.debug("DMI decode file:   [{}] {}[{}] ({} bytes)", dmiDecode.getContentType(), dmiDecode.getName(), dmiDecode.getOriginalFilename(), dmiDecode.getSize());
        log.debug("IOMMU groups file: [{}] {}[{}] ({} bytes)", iommuGroups.getContentType(), iommuGroups.getName(), iommuGroups.getOriginalFilename(), iommuGroups.getSize());

        UploadedStreams payload = this.fileUploadConverter.convert(dmiDecode, iommuGroups);
        this.fileUploadService.processFiles(payload);

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + dmiDecode.getOriginalFilename() + "' and '" + iommuGroups.getOriginalFilename() + "'!");

        return "redirect:/";
    }

    /**
     * Allow upload of DMI decode and IOMMU groups files as plain strings.
     *
     * @param dmiDecode Plain string with DMI decode file contents
     * @param iommuGroups Plain string with IOMMU groups file contents
     * @param redirectAttributes Allows to pass attributes to the {@code Model} of the redirect
     * target
     * @return Redirect to index page
     *
     * @throws Exception
     */
    @PostMapping("/upload/plain")
    public String handleFileUpload(@RequestParam("dmiDecode") String dmiDecode, @RequestParam("iommuGroups") String iommuGroups, RedirectAttributes redirectAttributes) throws Exception {

        log.debug("DMI decode plain:   {} bytes", dmiDecode.getBytes().length);
        log.debug("IOMMU groups plain: {} bytes", iommuGroups.getBytes().length);

        UploadedStreams payload = this.fileUploadConverter.convert(dmiDecode, iommuGroups);
        this.fileUploadService.processFiles(payload);

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded two files!");

        return "redirect:/";
    }
}
