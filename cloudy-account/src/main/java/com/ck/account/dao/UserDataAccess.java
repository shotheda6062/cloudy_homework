package com.ck.account.dao;

import com.ck.account.dao.bean.AccountInfoPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataAccess extends JpaRepository<AccountInfoPo, String> {


}
