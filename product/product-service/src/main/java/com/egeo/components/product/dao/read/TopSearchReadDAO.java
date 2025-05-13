package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.TopSearchPO;
import com.egeo.orm.BaseReadDAO;

public interface TopSearchReadDAO extends BaseReadDAO<TopSearchPO>{
	/**
	 * 查询排序当前最大值
	 * @return
	 */
	Integer sortValueMax();
	/**
	 * 查询启用的热搜条数
	 * @return
	 */
	int findStartTopSearchNum(@Param("platformId")Long platformId);
}
	