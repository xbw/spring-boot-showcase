package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.framework.util.MD5;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    @Value("${file.local.path}")
    private String localPath;
    @Value("${file.virtual.path}")
    private String virtualPath;

    @GetMapping({"/", "/upload"})
    public String index() {
        return "upload";
    }

    @GetMapping("/status")
    public String status() {
        return "status";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) {
        if (file.isEmpty()) {
            redirect.addFlashAttribute("status", "Fail！");
            redirect.addFlashAttribute("message", "No file！");
        } else {
            try {
                byte[] fileBytes = file.getBytes();
                String newFileName = MD5.calcMD5(file.getInputStream());
                String[] type = file.getContentType().split("/");
                Path path = Paths.get(localPath + newFileName + "." + type[1]);
                Files.write(path, fileBytes);

                redirect.addFlashAttribute("status", "Upload Successful！");
                redirect.addFlashAttribute("message", "The file name is " + newFileName + "." + type[1]);
                redirect.addFlashAttribute("fileUrl", virtualPath.replace("**", newFileName + "." + type[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:status";
    }

}