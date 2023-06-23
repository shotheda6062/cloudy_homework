package com.ck.account.dao.bean;

import com.google.type.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "TB_ACCOUNT_INFO")
public class AccountInfoPo {

    @Id
    @Column(name="user_account")
    String userAccound;

    @Column(name="user_password")
    String userPassword;

    @Column(name="create_time")
    DateTime createTime;

    @Column(name="last_login")
    DateTime lastLogin;
}
