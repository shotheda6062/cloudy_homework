package com.ck.file.service.impl;

import com.ck.file.service.FileProcessService;
import com.ck.file.utility.GCSUploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileProcessServiceImpl implements FileProcessService {

    @Autowired
    GCSUploadFileUtils gcsUploadFileUtils;

    @Override
    public String getFile() {
        return gcsUploadFileUtils.getFile();
    }
}
