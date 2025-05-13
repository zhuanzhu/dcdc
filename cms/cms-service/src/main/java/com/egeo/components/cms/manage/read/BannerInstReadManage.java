package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.BannerInstPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BannerInstReadManage {

	public BannerInstPO findBannerInstById(BannerInstPO po);

	public PageResult<BannerInstPO> findBannerInstOfPage(BannerInstPO po,Pagination page);

	public List<BannerInstPO> findBannerInstAll(BannerInstPO po);
}
	