package com.ck.account.utility.impl;

import com.ck.account.utility.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CloudKMSPasswordEncoder implements PasswordEncoder {

    @Autowired
    CryptoService cryptoService;

    @Override
    public String encode(CharSequence rawPassword) {
        return cryptoService.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
