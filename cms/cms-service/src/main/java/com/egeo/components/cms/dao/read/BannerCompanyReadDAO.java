package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.condition.CmsPageCfgCondition;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface BannerCompanyReadDAO extends BaseReadDAO<BannerCompanyPO>{
	
	public List<BannerCompanyPO> findEnterpriseOfPage(@Param("po") BannerCompanyPO po, @Param("page") Pagination page,@Param("companyIdList")List<Long> companyIdList);

	public int countEnterpriseOfPage(@Param("po") BannerCompanyPO po,@Param("companyIdList")List<Long> companyIdList);
	
	public List<BannerCompanyPO> findEnterpriseAll(@Param("po") BannerCompanyPO po, @Param("page") Pagination page,@Param("companyIdList")List<Long> companyIdList);

}
	