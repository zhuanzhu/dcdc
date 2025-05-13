package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LeadingEndCategoryManage {

	public LeadingEndCategoryDTO findLeadingEndCategoryById(LeadingEndCategoryDTO dto);	

	public PageResult<LeadingEndCategoryDTO> findLeadingEndCategoryOfPage(LeadingEndCategoryDTO dto,Pagination page);

	public List<LeadingEndCategoryDTO> findLeadingEndCategoryAll(LeadingEndCategoryDTO dto);

	Long insertLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto);

	int updateLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto);

	int deleteLeadingEndCategoryWithTx(LeadingEndCategoryDTO dto);
	/**
	 * 查询所有只返回id和名称
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findLeadingEndCategoryIdAndName(LeadingEndCategoryDTO dto);
	/**
	 * 客户端分页显示类目及子模块
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> leadingEndCategoryOfPageApp(LeadingEndCategoryDTO dto, Pagination page);
}
	