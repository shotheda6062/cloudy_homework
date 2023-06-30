package com.ck.account.dao;

import com.ck.account.dao.bean.AccountInfoPo;
import org.checkerframework.common.util.report.qual.ReportUnqualified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataAccess extends JpaRepository<AccountInfoPo, String> {


}
