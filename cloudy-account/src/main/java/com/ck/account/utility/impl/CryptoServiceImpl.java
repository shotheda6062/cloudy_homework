package com.ck.account.utility.impl;

import com.ck.account.utility.CryptoService;
import com.google.cloud.spring.kms.KmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    KmsTemplate kmsTemplate;

    @Value("${cloud.gcp.kms.id}")
    String keyId;

    @Override
    public String encrypt(String text) {
        byte[] encryptedBytes = kmsTemplate.
                encryptText(keyId, text);
        return encodeBase64(encryptedBytes);
    }


    private String encodeBase64(byte[] bytes) {
        byte[] encoded = Base64.getEncoder().encode(bytes);
        return new String(encoded);
    }

}
