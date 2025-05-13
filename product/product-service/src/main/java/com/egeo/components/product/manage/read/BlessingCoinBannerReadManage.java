package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.BlessingCoinBannerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BlessingCoinBannerReadManage {

	public BlessingCoinBannerPO findBlessingCoinBannerById(BlessingCoinBannerPO po);

	public PageResult<BlessingCoinBannerPO> findBlessingCoinBannerOfPage(BlessingCoinBannerPO po,Pagination page);

	public List<BlessingCoinBannerPO> findBlessingCoinBannerAll(BlessingCoinBannerPO po);
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<BlessingCoinBannerPO> findBlessingCoinBannerAllApp(BlessingCoinBannerPO po);
}
	