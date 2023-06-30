package com.ck.file.utility;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class GCSUploadFileUtils {

    @Autowired
    private Storage storage;

    public String uploadFile () {
        return "hellow";
    }


    public String getFile(){
        BlobId id = BlobId.fromGsUtilUri("gs://homeworkimage/test.txt");
        byte[] byteArray = storage.readAllBytes(id);

        System.out.println(byteArray);

        return "test 123";
    }

    private String genGCSFilePath(String buketName,String accountID,String fileName) {

        StringBuilder builder = new StringBuilder();
        builder.append("gs://");
        builder.append(buketName);
        builder.append("/");
        builder.append(accountID);
        builder.append("/");
        builder.append(fileName);

        return builder.toString();

    }

}
