package com.egeo.components.cms.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.common.NormalConstant;
import com.egeo.components.cms.business.NavigationBarManage;
import com.egeo.components.cms.converter.NavigationBarConverter;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.facade.LinkableButtonFacade;
import com.egeo.components.cms.facade.NavigationBarFacade;
import com.egeo.components.cms.vo.NavigationBarVO;
import com.egeo.components.cms.vo.NavigationBarWebVO;
import com.egeo.components.cms.vo.NavigationLabelSimpleVO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("navigationBar")
public class NavigationBarManageImpl implements NavigationBarManage {

	@Resource(name = "navigationBarFacade")
	private NavigationBarFacade navigationBarFacade;
	
	@Resource(name = "linkableButtonFacade")
	private LinkableButtonFacade linkableButtonFacade;

	@Override
	public JsonResult<NavigationBarVO> findNavigationBarById(NavigationBarDTO dto) {
		NavigationBarDTO dto_ = navigationBarFacade.findNavigationBarById(dto);
		if (EmptyUtil.isEmpty(dto_))
			return JsonResult.fail("该导航栏不存在");

		// 查询导航栏下的标签
		NavigationLabelDTO navigationLabelDTO = new NavigationLabelDTO();
		navigationLabelDTO.setNavigationBarId(dto_.getId());
		List<NavigationLabelDTO> navigationLabelDTOList = navigationBarFacade
				.findNavigationLabelAll(navigationLabelDTO);
		List<NavigationLabelSimpleVO> navigationLabelList = new ArrayList<>();
		for (NavigationLabelDTO navigationLabelDTO_ : navigationLabelDTOList) {
			NavigationLabelSimpleVO navigationLabelSimpleVO = new NavigationLabelSimpleVO();
			navigationLabelSimpleVO.setId(navigationLabelDTO_.getId());
			navigationLabelSimpleVO.setName(navigationLabelDTO_.getNavigationLabelName());
			navigationLabelList.add(navigationLabelSimpleVO);
		}

		// 查询导航栏的所属公司
		NavigationBarCompanyDTO navigationBarCompanyDTO = new NavigationBarCompanyDTO();
		navigationBarCompanyDTO.setNavigationBarId(dto_.getId());
		List<NavigationBarCompanyDTO> navigationBarCompanyDTOList = navigationBarFacade
				.findNavigationBarCompanyAll(navigationBarCompanyDTO);
		List<Long> companyIdList = new ArrayList<>();
		for (NavigationBarCompanyDTO navigationBarCompanyDTO_ : navigationBarCompanyDTOList) {
			companyIdList.add(navigationBarCompanyDTO_.getCompanyId());
		}

		NavigationBarVO navigationBarVO = NavigationBarConverter.toVO(dto_);
		navigationBarVO.setNavigationLabelList(navigationLabelList);
		navigationBarVO.setCompanyIdList(companyIdList);

		return  JsonResult.success(navigationBarVO);
	}

	@Override
	public PageResult<NavigationBarDTO> findNavigationBarOfPage(NavigationBarDTO dto, Pagination page) {
		return navigationBarFacade.findNavigationBarOfPage(dto, page);
	}

	@Override
	public List<NavigationBarDTO> findNavigationBarAll(NavigationBarDTO dto) {
		return navigationBarFacade.findNavigationBarAll(dto);
	}

	@Override
	public JsonResult<Long> insertOrUpdateNavigationBarWithTx(NavigationBarDTO dto) {
		// 参数校验
		if (EmptyUtil.isEmpty(dto.getCompanyIdList()))
			return JsonResult.fail("请选择导航栏所属公司");
		if (EmptyUtil.isEmpty(dto.getNavigationBarType()))
			return JsonResult.fail("导航栏类型不能为空");
		if (EmptyUtil.isEmpty(dto.getNavigationLabelIdList()))
			return JsonResult.fail("请添加信息栏标签");
		if (dto.getNavigationBarType() == 0 && dto.getNavigationLabelIdList().size() > 9)
			return JsonResult.fail("同一导航主题下标签最多可配置 9 个");
		if (dto.getNavigationBarType() == 1 && dto.getNavigationLabelIdList().size() > 4)
			return JsonResult.fail("同一导航主题下标签最多可配置 4 个");
		if (EmptyUtil.isEmpty(dto.getNavigationBarName()))
			return JsonResult.fail("请填写导航栏名称");
		if (dto.getNavigationBarName().length() > 6)
			return JsonResult.fail("导航栏名称长度最多6个");

		Long rt = null;
		if (dto.getId() == null) {
			// 新增
			rt = navigationBarFacade.insertNavigationBarWithTx(dto);
		} else {
			// 编辑
			rt = new Long((long) navigationBarFacade.updateNavigationBarWithTx(dto));
		}

		return  JsonResult.success(rt);
	}

	@Override
	public int updateNavigationBarWithTx(NavigationBarDTO dto) {
		return navigationBarFacade.updateNavigationBarWithTx(dto);
	}

	@Override
	public int deleteNavigationBarWithTx(NavigationBarDTO dto) {
		return navigationBarFacade.deleteNavigationBarWithTx(dto);
	}

	@Override
	public JsonResult<Long> insertOrUpdateNavigationLableWithTx(NavigationLabelDTO dto,
			LinkableButtonDTO linkableButtonDTO) {
		// 参数校验
		if (EmptyUtil.isEmpty(dto.getNavigationLabelName()))
			return JsonResult.fail("导航栏标签名称不能为空");
		if (dto.getNavigationLabelName().length() > 15)
			return JsonResult.fail("导航栏标签名称长度最多15个");
		if (EmptyUtil.isEmpty(linkableButtonDTO.getLinkType()))
			return JsonResult.fail("跳转类型不能为空");

		// 跳转类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品池组(商品列表)、5.商品、6.无效果 7.分页tab
		if (EmptyUtil.isEmpty(linkableButtonDTO.getLinkType()))
			return JsonResult.fail("跳转页面不能为空");
		if (linkableButtonDTO.getLinkType() == 2 && EmptyUtil.isEmpty(linkableButtonDTO.getLinkUrl())) {
			return JsonResult.fail("配置链接不能为空");
		} else if (linkableButtonDTO.getLinkType() != 6 && linkableButtonDTO.getLinkType() != 2 
				&& EmptyUtil.isEmpty(linkableButtonDTO.getLinkId())) {
			return JsonResult.fail("跳转id不能为空");
		}

		return  JsonResult.success(navigationBarFacade.insertOrUpdateNavigationLableWithTx(dto, linkableButtonDTO));
	}

	@Override
	public JsonResult<Map<String, Object>> findNavigationLabelById(NavigationLabelDTO dto) {

		NavigationLabelDTO dto_ = navigationBarFacade.findNavigationLabelById(dto);
		if (EmptyUtil.isEmpty(dto_))
			return JsonResult.fail("该导航栏标签不存在");
		
		// 查询跳转链接信息
		LinkableButtonDTO linkableButtonDTO = linkableButtonFacade.queryLinkableButtonById(dto_.getLinkableButtonId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", dto_.getId());
//		map.put("navigationBarId", dto_.getNavigationBarId());
		map.put("navigationLabelName", dto_.getNavigationLabelName());
		map.put("navigationLabelRemark", dto_.getNavigationLabelRemark());
		map.put("linkId", linkableButtonDTO != null ?  linkableButtonDTO.getLinkId() : null);
		map.put("linkParam", linkableButtonDTO != null ? linkableButtonDTO.getLinkParam(): null);
		map.put("linkType", linkableButtonDTO != null ? linkableButtonDTO.getLinkType() : null);
		map.put("linkUrl", linkableButtonDTO != null ? linkableButtonDTO.getLinkUrl() : null);
		
		return  JsonResult.success(map);
	}

	@Override
	public int deleteNavigationLableWithTx(NavigationLabelDTO dto) {

		return navigationBarFacade.deleteNavigationLableWithTx(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findNavigationBarOfPageByBlurry(NavigationBarDTO dto, Long companyId,
			Pagination page) {
		// companyId筛选条件
		List<Long> navigationBarIdList = null;
		if (EmptyUtil.isNotEmpty(companyId)) {
			navigationBarIdList = new ArrayList<>();
			CompanyDTO companyDTO = navigationBarFacade.findCompanyById(companyId);
			if (companyDTO == null) {
				navigationBarIdList.add(-1L);
			} else {
				addNavigationBarIdListByCompanyId(navigationBarIdList, companyId);

				// 是否是测试公司 0:正式公司(默认值) 1:测试公司 2:竞品公司
				addNavigationBarIdListByCompanyId(navigationBarIdList, NormalConstant.getCompanyIdByCompanyType(companyDTO.getCompanyType()));
			}
		}

		// 去重设值
		dto.setCompanyIdList(navigationBarFacade.removeDuplicate(navigationBarIdList));
		PageResult<NavigationBarDTO> rt = navigationBarFacade.findNavigationBarOfPageByBlurry(dto, page);
		
		List<Map<String, Object>> list = new ArrayList<>();
		for (NavigationBarDTO navigationBarDTO : rt.getList()) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", navigationBarDTO.getId());
			map.put("navigationBarName", navigationBarDTO.getNavigationBarName());
			map.put("createTime", navigationBarDTO.getCreateTime().getTime());
			map.put("updateTime", !navigationBarDTO.getUpdateTime().equals(navigationBarDTO.getCreateTime())
					? navigationBarDTO.getUpdateTime().getTime() : null);

			// 查询更新人信息
			UserDTO userDTO = navigationBarDTO.getUpdateUser() != null
					? navigationBarFacade.findUserById(navigationBarDTO.getUpdateUser()) : null;
			map.put("updateUser", userDTO != null ? userDTO.getLoginName() : null);

			list.add(map);
		}
		
		PageResult<Map<String, Object>> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		
		return result;
	}

	/**
	 * 通过公司id添加导航栏id
	 * 
	 * @param navigationBarIdList
	 * @param companyId
	 */
	private void addNavigationBarIdListByCompanyId(List<Long> navigationBarIdList, Long companyId) {
		NavigationBarCompanyDTO navigationBarCompanyDTO = new NavigationBarCompanyDTO();
		navigationBarCompanyDTO.setCompanyId(companyId);
		List<NavigationBarCompanyDTO> list = navigationBarFacade.findNavigationBarCompanyAll(navigationBarCompanyDTO);
		for (NavigationBarCompanyDTO navigationBarCompanyDTO_ : list) {
			navigationBarIdList.add(navigationBarCompanyDTO_.getNavigationBarId());
		}
	}

	@Override
	public List<CompanyDTO> findNavigationBarCompanyOfPage(Long id, Pagination page) {
		
		return navigationBarFacade.findNavigationBarCompanyOfPage(id, page);
	}
	
	@Override
	public JsonResult<List<NavigationBarWebVO>> findPageTabAllByCompanyId(Integer navigationBarType, Long companyId, Long platformId, Long clientId) {
		
		return  JsonResult.success(navigationBarFacade.findPageTabAllByCompanyId(navigationBarType, companyId, platformId, clientId));
	}


}
