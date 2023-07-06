package com.ck.file.utility;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class GCSUploadFileUtils {

    @Autowired
    private Storage storage;


    @Value("${spring.cloud.gcp.storage.buket}")
    private String cloudBuket;


    public void upload(byte[] filebytes,String userAccount,String fileName,String fileType) {
        BlobId id = BlobId.fromGsUtilUri(genGCSFilePath(userAccount,fileName));
        BlobInfo info = BlobInfo.newBuilder(id).setContentType(fileType).build();
        storage.create(info, filebytes);
    }

    public byte[] getFile(String userAccount,String fileName){
        BlobId id = BlobId.fromGsUtilUri(genGCSFilePath(userAccount,fileName));
        return storage.readAllBytes(id);
    }

    private String genGCSFilePath(String accountID,String fileName) {

        StringBuilder builder = new StringBuilder();
        builder.append("gs://");
        builder.append(cloudBuket);
        builder.append("/");
        builder.append(accountID);
        builder.append("/");
        builder.append(fileName);

        return builder.toString();

    }

}
