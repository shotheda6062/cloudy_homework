package com.ck.account.service;

public interface LoginService {

    void login(String accoundID, String password);

    void logout(String accountID);
}
