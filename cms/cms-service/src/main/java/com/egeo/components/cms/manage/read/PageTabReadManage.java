package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.PageTabPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PageTabReadManage {

	public PageTabPO findPageTabById(PageTabPO po);

	public PageResult<PageTabPO> findPageTabOfPage(PageTabPO po,Pagination page);

	public List<PageTabPO> findPageTabAll(PageTabPO po);

	/**
	 * 分页tab参数校验
	 * @param po
	 * @return
	 */
	public List<PageTabPO> findPageTabAllForCheck(PageTabPO po);

	/**
	 * 模糊查询分页tab列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<PageTabPO> findPageTabOfPageByBlurry(PageTabPO po, Pagination page);

	/**
	 * 通过公司id集合查询所有tab分页
	 * @param po
	 * @param companyIdList
	 * @return
	 */
	public List<PageTabPO> findPageTabAllByCompanyId(PageTabPO po, List<Long> companyIdList);
}
	