package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.NavigationBarPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface NavigationBarReadDAO extends BaseReadDAO<NavigationBarPO>{

	/**
	 * 模糊查询导航栏列表
	 * @param po
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")NavigationBarPO po);

	List<NavigationBarPO> findOfPageByBlurry(@Param("po")NavigationBarPO po, @Param("page")Pagination page);
}
	