package com.ck.file.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfoDto {

    String fileName;

    String fileExtestion;

    String originFileName;

    Boolean isCompress;
}
