package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.vo.NavigationBarVO;
import com.egeo.components.cms.vo.NavigationBarWebVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface NavigationBarManage {

	public JsonResult<NavigationBarVO> findNavigationBarById(NavigationBarDTO dto);

	public PageResult<NavigationBarDTO> findNavigationBarOfPage(NavigationBarDTO dto, Pagination page);

	public List<NavigationBarDTO> findNavigationBarAll(NavigationBarDTO dto);

	JsonResult<Long> insertOrUpdateNavigationBarWithTx(NavigationBarDTO dto);

	int updateNavigationBarWithTx(NavigationBarDTO dto);

	int deleteNavigationBarWithTx(NavigationBarDTO dto);

	/**
	 * 新增或编辑导航栏标签
	 * 
	 * @param vo
	 * @return
	 */
	public JsonResult<Long> insertOrUpdateNavigationLableWithTx(NavigationLabelDTO dto,
			LinkableButtonDTO linkableButtonDTO);

	/**
	 * 查询导航栏标签详情通过id
	 * @param dto
	 * @return
	 */
	public JsonResult<Map<String, Object>> findNavigationLabelById(NavigationLabelDTO dto);

	/**
	 * 删除导航栏标签详情通过id
	 * @param dto
	 * @return
	 */
	public int deleteNavigationLableWithTx(NavigationLabelDTO dto);

	/**
	 * 导航栏列表
	 * @param dto
	 * @param companyId
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findNavigationBarOfPageByBlurry(NavigationBarDTO dto, Long companyId,
			Pagination page);

	/**
	 * 通过导航栏id查询公司信息
	 * @param id
	 * @param page
	 * @return
	 */
	public List<CompanyDTO> findNavigationBarCompanyOfPage(Long id, Pagination page);

	/**
	 * 通过公司id查询首页导航栏和底部信息栏的信息
	 * @param companyId
	 * @return
	 */
	public JsonResult<List<NavigationBarWebVO>> findPageTabAllByCompanyId(Integer navigationBarType, Long companyId, Long platformId, Long clientId);
}
