package com.ck.file.controller;

import com.ck.file.controller.dto.FileInfoDto;
import com.ck.file.dao.bean.FileInfoPo;
import com.ck.file.service.FileProcessService;
import com.ck.file.service.bean.FileInfoBo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FilePorcessController {

    @Autowired
    FileProcessService fileProcessService;


    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("userAccount") String userAccount) throws IOException {

        FileInfoBo fileInfo = new FileInfoBo();
        fileInfo.setUserAccount(userAccount);
        fileInfo.setOriginFileName(file.getOriginalFilename());
        fileInfo.setFileExtension(getExtension(file.getOriginalFilename(),""));
        fileProcessService.upload(file.getBytes(), fileInfo);

    }


    @PostMapping("/getfile")
    public byte[] getfile(@RequestBody FileInfoDto fileInfoDto) {
        FileInfoBo fileInfoBo = new FileInfoBo();
        fileInfoBo.setFileName(fileInfoDto.getFileName());
        fileInfoBo.setUserAccount(fileInfoDto.getUserAccount());
        return fileProcessService.getFile(fileInfoBo);
    }

    @RequestMapping(value = "/getFileList", method = RequestMethod.POST)
    public ResponseEntity<List<FileInfoDto>> getFileList(@RequestBody FileInfoDto fileInfoDto) {

        List<FileInfoDto> result = new ArrayList<>();
        List<FileInfoBo> relayBoLit = fileProcessService.getFileList(fileInfoDto.getUserAccount());
        relayBoLit.forEach(x -> {
            FileInfoDto relayDto = new FileInfoDto();
            relayDto.setFileName(x.getFileName());
            relayDto.setUserAccount(x.getUserAccount());
            relayDto.setOriginFileName(x.getOriginFileName());
            relayDto.setFileExtestion(x.getFileExtension());
            result.add(relayDto);
        });


        return ResponseEntity.ok(result);
    }

    private String getExtension(String filename, String orElse) {
        return Optional.ofNullable(filename)
                .filter(s -> s.contains("."))
                .map(s -> s.substring(s.lastIndexOf(".") + 1))
                .orElse(orElse);
    }
}
