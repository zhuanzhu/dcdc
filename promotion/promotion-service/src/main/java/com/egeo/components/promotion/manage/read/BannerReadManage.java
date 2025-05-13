package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.BannerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface BannerReadManage {

	public BannerPO findBannerById(BannerPO po);

	public PageResult<BannerPO> findBannerOfPage(BannerPO po,Pagination page);

	public List<BannerPO> findBannerAll(BannerPO po);
	/**
	 * 查询Banner最大排序值
	 * @return
	 */
	public Integer maxSortValue();
}
	