package com.egeo.components.cms.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.CmsPageTabManage;
import com.egeo.components.cms.business.PageTabManage;
import com.egeo.components.cms.converter.PageTabConverter;
import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.vo.PageTabVO;
import com.egeo.components.cms.vo.PageTabWebVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/pageTab")
public class PageTabAction extends BaseSpringController {

	@Resource(name = "pageTab")
	private PageTabManage pageTabManage;
	
	@Resource(name = "cmsPageTab")
	private CmsPageTabManage cmsPageTabManage;

	/**
	 * 新增或编辑分页tab
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdatePageTabWithTx")
	@ResponseBody
	public JsonResult<Long> insertOrUpdatePageTabWithTx(PageTabVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		PageTabDTO dto = PageTabConverter.toDTO(vo);
		
		CacheUser cacheUser = getCacheUser();
		if (dto.getId() != null) {
			dto.setUpdateUser(cacheUser.getId());
		}

		return pageTabManage.insertOrUpdatePageTabWithTx(dto);
	}

	/**
	 * 修改分页tab状态
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/resetPageTabStatusByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> resetPageTabStatusByIdWithTx(PageTabVO vo, HttpServletRequest req) {
		// 参数校验
		if (EmptyUtil.isEmpty(vo.getId()))
			return JsonResult.fail("分页tab的id不能为空");
		
		PageTabDTO dto = new PageTabDTO();
		dto.setId(vo.getId());
		
		return pageTabManage.resetPageTabStatusByIdWithTx(dto);
	}

	/**
	 * 查看分页tab通过id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findPageTabById")
	@ResponseBody
	public JsonResult<PageTabVO> findPageTabById(Long id) {
		// 参数校验
		if (EmptyUtil.isEmpty(id))
			return JsonResult.fail("分页tab的id不能为空");
		
		PageTabDTO dto = new PageTabDTO();
		dto.setId(id);
		return pageTabManage.findPageTabById(dto);

	}
	
	/**
	 * 分页tab列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findPageTabOfPage")
	@ResponseBody
	public JsonResult<PageResult<PageTabVO>> findPageTabOfPage(PageTabVO vo, Pagination page, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		PageTabDTO dto = PageTabConverter.toDTO(vo);
		PageResult<PageTabVO> rt = pageTabManage.findPageTabOfPage(dto, page);
	
		return JsonResult.success(rt);
		
	}
	
	/**
	 * 通过公司id集合查询启用状态下的所有tab分页	
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findSimplePageTabAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findPageTabAllByCompanyId(Long templateId,Integer companyType, String companyIds, HttpServletRequest req) {
		List<Long> companyIdList = null;
		/*try {
			companyIdList = JSONArray.parseArray(companyIds, Long.class);
		} catch (Exception e) {
			return JsonResult.fail("公司id集合参数错误");
		}
		
		if (EmptyUtil.isEmpty(companyIdList))
			return JsonResult.fail("请先选择公司");*/
		
		if (EmptyUtil.isNotEmpty(templateId) && EmptyUtil.isNotEmpty(companyType))
			return JsonResult.fail("模版或公司类型不能为空");
		
		return pageTabManage.findPageTabAllByCompanyId(templateId,companyType, companyIdList);

	}

	/**
	 * web商城查询分页tab信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryPageTabByCompany")
	@ResponseBody
	public JsonResult<List<PageTabWebVO>> queryPageTabByCompany(HttpServletRequest req) {
		
		CacheUser cacheUser = getCacheUser();
		Long platformId = null;
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		return pageTabManage.queryPageTabByCompany(cacheUser.getCompanyId(),platformId);
	}
	
	/**
	 * 删除分页tab
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deletePageTabWithTx")
	@ResponseBody
	public JsonResult<Integer> deletePageTabWithTx(Long id, HttpServletRequest req) {
		if (id == null) 
			return JsonResult.fail("id不能为空");
		
		PageTabDTO dto = new PageTabDTO();
		dto.setId(id);
		int rt = pageTabManage.deletePageTabWithTx(dto);
		return JsonResult.success(rt);
	}

}
