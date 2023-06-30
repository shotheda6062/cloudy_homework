package com.ck.file.controller;

import com.ck.file.service.FileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilePorcessController {

    @Autowired
    FileProcessService fileProcessService;

    @PostMapping("/getfile")
    public String getfile() {
        fileProcessService.getFile();

        return "1234567";
    }
}
