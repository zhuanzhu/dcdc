package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.vo.PageTabVO;
import com.egeo.components.cms.vo.PageTabWebVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface PageTabManage {

	public JsonResult<PageTabVO> findPageTabById(PageTabDTO dto);

	public PageResult<PageTabVO> findPageTabOfPage(PageTabDTO dto, Pagination page);

	public List<PageTabDTO> findPageTabAll(PageTabDTO dto);

	/**
	 * 新增或编辑分页tab
	 * 
	 * @param dto
	 * @return
	 */
	JsonResult<Long> insertOrUpdatePageTabWithTx(PageTabDTO dto);

	JsonResult<Integer> resetPageTabStatusByIdWithTx(PageTabDTO dto);

	int deletePageTabWithTx(PageTabDTO dto);

	/**
	 * 通过公司id集合查询所有tab分页
	 * @param dto
	 * @param companyIdList
	 * @return
	 */
	public JsonResult<List<Map<String, Object>>> findPageTabAllByCompanyId(Long templateId,Integer companyType, List<Long> companyIdList);

	/**
	 * web商城查询分页tab信息
	 * @param companyId
	 * @return
	 */
	public JsonResult<List<PageTabWebVO>> queryPageTabByCompany(Long companyId,Long platformId);

}
