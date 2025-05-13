package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BlessingCoinBannerCompanyReadManage {

	public BlessingCoinBannerCompanyPO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyPO po);

	public PageResult<BlessingCoinBannerCompanyPO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyPO po,Pagination page);

	public List<BlessingCoinBannerCompanyPO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyPO po);
}
	