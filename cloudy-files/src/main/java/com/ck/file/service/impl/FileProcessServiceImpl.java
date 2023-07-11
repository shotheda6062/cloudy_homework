package com.ck.file.service.impl;

import com.ck.file.dao.FileInfoDataAccess;
import com.ck.file.dao.bean.FileInfoPK;
import com.ck.file.dao.bean.FileInfoPo;
import com.ck.file.service.FileProcessService;
import com.ck.file.service.bean.FileInfoBo;
import com.ck.file.service.bean.FileInfoRelayBo;
import com.ck.file.utility.GCSUploadFileUtils;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileProcessServiceImpl implements FileProcessService {

    @Autowired
    GCSUploadFileUtils gcsUploadFileUtils;

    @Autowired
    FileInfoDataAccess fileInfoDataAccess;

    @Value("${spring.cloud.gcp.storage.buket}")
    private String cloudBuket;

    @Override
    public void upload(byte[] file, FileInfoBo fileInfo) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatFileName = formatter.format(new Date());

        FileInfoPo inputPo = new FileInfoPo();
        FileInfoPK inputPk = new FileInfoPK();

        inputPk.setFileName(formatFileName);
        inputPk.setUaerAccount(fileInfo.getUserAccount());
        inputPo.setFileInfoPK(inputPk);
        inputPo.setOrinFileName(fileInfo.getOriginFileName());
        inputPo.setFileExtension(fileInfo.getFileExtension());
        inputPo.setUpdateSNo(getMaxUpdSonByUserAccount(fileInfo.getUserAccount()));
        inputPo.setCreatedTime(new Timestamp(new Date().getTime()));
        inputPo.setUpdateTime(new Timestamp(new Date().getTime()));
        inputPo.setFileStatus(true);

        fileInfoDataAccess.save(inputPo);

        gcsUploadFileUtils.upload(file, fileInfo.getUserAccount(), formatFileName, fileInfo.getFileExtension(),cloudBuket);

    }

    @Override
    public byte[] getFile(FileInfoBo fileInfoBo) {
        return gcsUploadFileUtils.getFile(fileInfoBo.getUserAccount(), fileInfoBo.getFileName(),cloudBuket);
    }


    @Override
    public byte[] getFile(FileInfoBo fileInfoBo,String buket) {
        return gcsUploadFileUtils.getFile(fileInfoBo.getUserAccount(), fileInfoBo.getFileName(),buket);
    }

    @Override
    public List<FileInfoRelayBo> getFileList(String userAccount) {
        List<FileInfoRelayBo> result = new ArrayList<>();

        List<FileInfoPo> relayPoList = fileInfoDataAccess.getListByAccount(userAccount);

        if (null == relayPoList) {
            return result;
        }

        relayPoList.forEach(x -> {
            FileInfoRelayBo relayBo = new FileInfoRelayBo();
            relayBo.setOriginFileName(x.getOrinFileName());
            relayBo.setFileName(x.getFileInfoPK().getFileName());
            relayBo.setFileExtension(x.getFileExtension());
            result.add(relayBo);
        });

        return result;
    }


    private Integer getMaxUpdSonByUserAccount(String userAccount) {
        Integer maxValue = Optional.ofNullable(fileInfoDataAccess.getMaxFileSnoWithAccount(userAccount)).orElse(Integer.valueOf(0));
        return maxValue++;
    }
}
