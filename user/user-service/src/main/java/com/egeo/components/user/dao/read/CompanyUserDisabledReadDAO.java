package com.egeo.components.user.dao.read;

import com.egeo.components.user.po.CompanyUserDisabledPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyUserDisabledReadDAO extends BaseReadDAO<CompanyUserDisabledPO>{

    Integer findRevalidationByCompanyId(@Param("companyId") Long companyId);

    List<Long> findUsersByCompanyId(@Param("companyId") Long companyId);
}
	