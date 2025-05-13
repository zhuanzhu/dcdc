package com.egeo.components.user.dao.write;

import com.egeo.components.user.po.CompanyUserDisabledPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

public interface CompanyUserDisabledWriteDAO extends BaseWriteDAO<CompanyUserDisabledPO> {

    int updateRevalidationByCompanyId(@Param("revalidation") Integer revalidation,@Param("companyId") Long companyId);
}
	