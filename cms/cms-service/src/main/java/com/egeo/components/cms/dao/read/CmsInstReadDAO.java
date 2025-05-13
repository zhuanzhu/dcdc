package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.CmsInstPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CmsInstReadDAO extends BaseReadDAO<CmsInstPO>{
	
	public List<CmsInstPO> findByPageAndCompanyOfPage(@Param("po") CmsInstPO po, @Param("companyId")Long companyId, @Param("companyAllId")Long companyAllId, @Param("page") Pagination page);
	
	public int countByPageAndCompanyOfPage(@Param("po") CmsInstPO po, @Param("companyId")Long companyId, @Param("companyAllId")Long companyAllId);
	
}
	