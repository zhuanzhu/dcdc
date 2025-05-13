package com.egeo.components.cms.service.read;


import java.util.List;

import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PageTabReadService {

	public PageTabDTO findPageTabById(PageTabDTO dto);

	public PageResult<PageTabDTO> findPageTabOfPage(PageTabDTO dto,Pagination page);

	public List<PageTabDTO> findPageTabAll(PageTabDTO dto);

	/**
	 * 分页tab参数校验
	 * @param dto
	 * @return
	 */
	public List<PageTabDTO> findPageTabAllForCheck(PageTabDTO dto);

	/**
	 * 模糊查询分页tab列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<PageTabDTO> findPageTabOfPageByBlurry(PageTabDTO dto, Pagination page);

	/**
	 * 通过公司id集合查询所有tab分页
	 * @param dto
	 * @param companyIdList
	 * @return
	 */
	public List<PageTabDTO> findPageTabAllByCompanyId(PageTabDTO dto, List<Long> companyIdList);
}
	