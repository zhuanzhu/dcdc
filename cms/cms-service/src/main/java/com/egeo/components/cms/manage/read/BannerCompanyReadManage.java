package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BannerCompanyReadManage {

	public BannerCompanyPO findBannerCompanyById(BannerCompanyPO po);

	public PageResult<BannerCompanyPO> findBannerCompanyOfPage(BannerCompanyPO po,Pagination page);
	public PageResult<BannerCompanyPO> findBannerCompanysOfPage(BannerCompanyPO po,Pagination page,List<Long> companyIdList);

	public List<BannerCompanyPO> findBannerCompanyAll(BannerCompanyPO po);
	public List<BannerCompanyPO> findBannerCompanysAll(BannerCompanyPO po,List<Long> companyIdList);
}
	