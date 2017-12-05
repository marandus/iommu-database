/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.marandus.iommu.uploader.controller;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * TODO: Missing class level javadoc
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Controller
@Slf4j
public class MainPageController {

    /**
     * TODO: Javadoc
     *
     * @param model
     * @return
     *
     * @throws IOException
     */
    @GetMapping("/")
    public String index(Model model) throws IOException {

        return "index";
    }

    @PostMapping("/upload/file")
    public String handleFileUpload(@RequestParam("dmiDecode") MultipartFile dmiDecode, @RequestParam("iommuGroups") MultipartFile iommuGroups, RedirectAttributes redirectAttributes) {

        // TODO: Process uploaded dmiDecode and iommuGroups files
        log.info("DMI decode file:   [{}] {}[{}] ({} bytes)", dmiDecode.getContentType(), dmiDecode.getName(), dmiDecode.getOriginalFilename(), dmiDecode.getSize());
        log.info("IOMMU groups file: [{}] {}[{}] ({} bytes)", iommuGroups.getContentType(), iommuGroups.getName(), iommuGroups.getOriginalFilename(), iommuGroups.getSize());

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + dmiDecode.getOriginalFilename() + "' and '" + iommuGroups.getOriginalFilename() + "'!");

        return "redirect:/";
    }

    @PostMapping("/upload/plain")
    public String handleFileUpload(@RequestParam("dmiDecode") String dmiDecode, @RequestParam("iommuGroups") String iommuGroups, RedirectAttributes redirectAttributes) {

        // TODO: Process uploaded dmiDecode and iommuGroups plain strings
        log.info("DMI decode plain:   {} bytes", dmiDecode.getBytes().length);
        log.info("IOMMU groups plain: {} bytes", iommuGroups.getBytes().length);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded two files!");

        return "redirect:/";
    }
}
