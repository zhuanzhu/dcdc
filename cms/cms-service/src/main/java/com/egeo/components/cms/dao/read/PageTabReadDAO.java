package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.PageTabPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface PageTabReadDAO extends BaseReadDAO<PageTabPO>{

	/**
	 * 分页tab参数校验
	 * @param po
	 * @return
	 */
	List<PageTabPO> findAllForCheck(@Param("po")PageTabPO po, @Param("page")Pagination page);

	/**
	 * 模糊查询分页tab列表
	 * @param po
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")PageTabPO po);

	List<PageTabPO> findOfPageByBlurry(@Param("po")PageTabPO po, @Param("page")Pagination page);

	/**
	 * 通过公司id集合查询所有tab分页
	 * @param po
	 * @param companyIdList
	 * @return
	 */
	List<PageTabPO> findAllByCompanyId(@Param("po")PageTabPO po, @Param("companyIdList")List<Long> companyIdList, @Param("page")Pagination page);
}
	