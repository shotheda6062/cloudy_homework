package com.ck.file.service.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ByteArrayResource;

@Getter
@Setter
public class FileInfoRelayBo {
    String originFileName;

    String fileName;

    String fileExtension;

}
