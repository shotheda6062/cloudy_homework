package com.ck.file.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;

@Getter
@Setter
public class FileInfoRelayDto {

    String fileName;

    String fileExtestion;

    String originFileName;

    String createTime;

    String fileData;

}
