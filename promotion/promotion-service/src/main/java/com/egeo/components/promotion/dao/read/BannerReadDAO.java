package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.BannerPO;
import com.egeo.orm.BaseReadDAO;

public interface BannerReadDAO extends BaseReadDAO<BannerPO>{
	/**
	 * 查询Banner最大排序值
	 * @return
	 */
	Integer maxSortValue();
}
	