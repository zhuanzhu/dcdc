package com.egeo.components.cms.business.impl;
	


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.PageTabManage;
import com.egeo.components.cms.converter.PageTabConverter;
import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.components.cms.facade.PageTabFacade;
import com.egeo.components.cms.facade.TemplateFacade;
import com.egeo.components.cms.facade.UserFacade;
import com.egeo.components.cms.vo.PageTabVO;
import com.egeo.components.cms.vo.PageTabWebVO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("pageTab")
public class PageTabManageImpl implements PageTabManage{

	
	@Resource(name="pageTabFacade")
	private PageTabFacade pageTabFacade;
	
	@Resource(name="userFacade")
	private UserFacade userFacade;
	
	@Resource(name="templateFacade")
	private TemplateFacade templateFacade;

	@Override
	public JsonResult<PageTabVO> findPageTabById(PageTabDTO dto) {
		PageTabDTO pageTabDTO = pageTabFacade.findPageTabById(dto);
		PageTabVO pageTabVO = PageTabConverter.toVO(pageTabDTO);
		if (EmptyUtil.isEmpty(pageTabVO)) 
			return JsonResult.fail("该分页tab不存在");

		// 查询商城模版的name
		
		return  JsonResult.success(pageTabVO);
	}

	@Override
	public PageResult<PageTabVO> findPageTabOfPage(PageTabDTO dto, Pagination page) {
		PageResult<PageTabDTO> rt = pageTabFacade.findPageTabOfPageByBlurry(dto, page);
		List<PageTabVO> list = PageTabConverter.toVO(rt.getList());
		for (PageTabVO pageTabVO : list) {
			// 查询更新人信息
			UserDTO userDTO = userFacade.findUserById(pageTabVO.getUpdateUser());
			pageTabVO.setUpdateUserName(userDTO != null ? userDTO.getLoginName() : null);
			
			// 设置更新时间 问题
			pageTabVO.setUpdateTime(!pageTabVO.getUpdateTime().equals(pageTabVO.getCreateTime()) 
					? pageTabVO.getUpdateTime() : null);
		}
		
		PageResult<PageTabVO> result = new PageResult<PageTabVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		
		return result;
	}

	@Override
	public List<PageTabDTO> findPageTabAll(PageTabDTO dto) {
		return pageTabFacade.findPageTabAll(dto);
	}

	@Override
	public JsonResult<Long> insertOrUpdatePageTabWithTx(PageTabDTO dto) {
		// 参数校验
		if (EmptyUtil.isEmpty(dto.getPageTabName())) 
			return JsonResult.fail("请填写 tab名称");
		if (dto.getPageTabName().length() > 4) 
			return JsonResult.fail("tab名称长度不能超过4个");
		if (EmptyUtil.isEmpty(dto.getCompanyId())) 
			return JsonResult.fail("请选择tab公司");
		/*if (EmptyUtil.isEmpty(dto.getWebTemplateId())) 
			return JsonResult.fail("请选择web商城模板");*/
		
		// 校验tab 名称是否重复（仅比较该公司分页 tab）
		PageTabDTO pageTabDTO = new PageTabDTO();
		pageTabDTO.setPageTabName(dto.getPageTabName());
		pageTabDTO.setCompanyId(dto.getCompanyId());
		List<PageTabDTO> pageTabDTOList = pageTabFacade.findPageTabAllForCheck(pageTabDTO);
		for (PageTabDTO pageTabDTO_ : pageTabDTOList) {
			if(EmptyUtil.isEmpty(dto.getId())) {
				return JsonResult.fail("tab名称重复");
			} else if (EmptyUtil.isNotEmpty(dto.getId()) && !dto.getId().equals(pageTabDTO_.getId())) {
				return JsonResult.fail("tab名称重复");
			}
		}
		
		// 查询当前分页tab的信息(编辑时)
		PageTabDTO dto_ = EmptyUtil.isNotEmpty(dto.getId()) ? pageTabFacade.findPageTabById(dto) : null;
		
		// tab排序号:当前tab在tab栏的排序号，如果有则为首页后的第X位，如果没有则按照创建时间排序(前提:非首页,普通tab)
		if ((dto.getIsHomePage() != null && dto.getIsHomePage() == 1) 
				|| EmptyUtil.isEmpty(dto.getPageTabSort())) {
			// 首页,tab排序号:-1:空值占位
			dto.setPageTabSort(-1);
		}
		
		Long rt = 0L;
		if (dto.getId() == null) {
			// 新增
			rt = pageTabFacade.insertPageTabWithTx(dto);
		} else {
			// 编辑
			if (dto_.getPageTabStatus() == 1) 
				return JsonResult.fail("启用状态下的分页 tab不可编辑");
			
			rt = new Long((long)pageTabFacade.updatePageTabWithTx(dto));
		}
		
		return  JsonResult.success(rt);
	}

	@Override
	public JsonResult<Integer> resetPageTabStatusByIdWithTx(PageTabDTO dto) {
		// 参数校验
		PageTabDTO dto_ = pageTabFacade.findPageTabById(dto);
		if (EmptyUtil.isEmpty(dto_))
			return JsonResult.fail("该分页tab不存在");
		if (dto_.getPageTabStatus() == 1) {
			// 停用
			// 首页tab不可停用
			if (dto_.getIsHomePage() == 1)
				return JsonResult.fail("启用首页tab不可停用");
			
			// 校验通过
			dto.setPageTabStatus(Integer.valueOf(0));
		} else if (dto_.getPageTabStatus() == 0) {
			// 启用
			if (dto_.getIsHomePage() == 1) {
				// 启用首页tab
				// 如之前已设置过首页，则之前启用的首页变为停用状态，并且取消置顶
				PageTabDTO pageTabDTO = new PageTabDTO();
				pageTabDTO.setIsHomePage(Integer.valueOf(1));
				List<PageTabDTO> pageTabDTOList = pageTabFacade.findPageTabAllForCheck(pageTabDTO);
				for (PageTabDTO pageTabDTO_ : pageTabDTOList) {
					PageTabDTO pageTabDTOForHomePage = new PageTabDTO();
					pageTabDTOForHomePage.setId(pageTabDTO_.getId());
					pageTabDTOForHomePage.setIsHomePage(Integer.valueOf(0));
					pageTabFacade.updatePageTabWithTx(pageTabDTOForHomePage);
				}
				
			} else if (dto_.getIsHomePage() == 0) {
				// 启用普通分页tab
				// 1.该分页 tab 排序号是否已被使用
				if (dto_.getPageTabSort() != null && dto_.getPageTabSort() != -1) {
					
					PageTabDTO pageTabDTO = new PageTabDTO();
					pageTabDTO.setPageTabSort(dto_.getPageTabSort());
					pageTabDTO.setPageTabStatus(Integer.valueOf(1));
					List<PageTabDTO> pageTabDTOList = pageTabFacade.findPageTabAllForCheck(pageTabDTO);
					if (EmptyUtil.isNotEmpty(pageTabDTOList)) 
						return JsonResult.fail("tab排序号已被使用");
				}
				
				// 2.已启用的分页 tab（除首页）是否已经达到 9 个
				PageTabDTO pageTabDTO = new PageTabDTO();
				pageTabDTO.setPageTabStatus(Integer.valueOf(1));
				List<PageTabDTO> pageTabDTOList = pageTabFacade.findPageTabAllForCheck(pageTabDTO);
				if (pageTabDTOList.size() >= 9) 
					return JsonResult.fail("启用的分页 tab总数不能超过 9 个");
			}
			
			// 校验通过
			dto.setPageTabStatus(Integer.valueOf(1));
		}
		
		return  JsonResult.success(pageTabFacade.updatePageTabWithTx(dto));
	}

	@Override
	public int deletePageTabWithTx(PageTabDTO dto) {
		
		return pageTabFacade.deletePageTabWithTx(dto);
	}

	@Override
	public JsonResult<List<Map<String, Object>>> findPageTabAllByCompanyId(Long templateId,Integer companyType, List<Long> companyIdList) {
		if(EmptyUtil.isEmpty(companyType)){
			TemplateDTO templateDTO = templateFacade.queryTemplateById(templateId);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if (templateDTO == null) {
				return  JsonResult.success(list);
			}
				
			// 公司类型赋值
			companyType = templateDTO.getCompanyType();
			if(companyType == null) {
				return  JsonResult.success(list);
			}
		}
		
		PageTabDTO dto = new PageTabDTO();
		dto.setCompanyId(Long.valueOf(companyType.toString()));
		List<PageTabDTO> pageTabDTOList = pageTabFacade.findPageTabAllByCompanyId(dto, companyIdList);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (PageTabDTO pageTabDTO : pageTabDTOList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pageTabDTO.getId());
			map.put("name", pageTabDTO.getPageTabName());
			
			list.add(map);
		}
		
		return  JsonResult.success(list);
	}

	@Override
	public JsonResult<List<PageTabWebVO>> queryPageTabByCompany(Long companyId,Long platformId) {
		CompanyDTO companyDTO = userFacade.queryCompanyById(companyId);
		if (companyDTO == null)
			return JsonResult.fail("公司不存在");
		
		PageTabDTO dto = new PageTabDTO();
		dto.setCompanyId(Long.valueOf(companyDTO.getCompanyType().longValue()));
		dto.setPlatformId(platformId);
		Pagination page = new Pagination();
		page.setPageSize(10);
		page.setOrderBy("page_tab_sort =-1 , page_tab_sort");
		PageResult<PageTabDTO> rt = pageTabFacade.findPageTabOfPage(dto,page);
		List<PageTabDTO> pageTabList = rt.getList();
		
		List<PageTabWebVO> ptList = new ArrayList<>();
		for (PageTabDTO pageTab : pageTabList) {
			PageTabWebVO pt = new PageTabWebVO();
			pt.setId(pageTab.getId());
			pt.setIsHomePage(pageTab.getIsHomePage());
			pt.setPageTabName(pageTab.getPageTabName());
			pt.setPageTabSort(pageTab.getPageTabSort());
			if (ptList.size() < 10) 
				ptList.add(pt);
		}
		
		return  JsonResult.success(ptList);
	}

}
	