package com.egeo.components.cms.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.business.NavigationBarManage;
import com.egeo.components.cms.converter.LinkableButtonConverter;
import com.egeo.components.cms.converter.NavigationBarConverter;
import com.egeo.components.cms.converter.NavigationLabelConverter;
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.vo.LinkableButtonVO;
import com.egeo.components.cms.vo.NavigationBarVO;
import com.egeo.components.cms.vo.NavigationBarWebVO;
import com.egeo.components.cms.vo.NavigationLabelVO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/navigationBar")
public class NavigationBarAction extends BaseSpringController {

	@Resource(name = "navigationBar")
	private NavigationBarManage navigationBarManage;

	/**
	 * 新增或编辑导航栏标签
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateNavigationLableWithTx")
	@ResponseBody
	public JsonResult<Long> insertOrUpdateNavigationLableWithTx(NavigationLabelVO vo, LinkableButtonVO linkableButtonVO,
			HttpServletRequest req) {

		return navigationBarManage.insertOrUpdateNavigationLableWithTx(NavigationLabelConverter.toDTO(vo),
				LinkableButtonConverter.toDTO(linkableButtonVO));
	}

	/**
	 * 查询导航栏标签详情通过id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findNavigationLableById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findNavigationLableById(Long id) {
		// 参数校验
		if (EmptyUtil.isEmpty(id))
			return JsonResult.fail("id不能为空");

		NavigationLabelDTO dto = new NavigationLabelDTO();
		dto.setId(id);

		return navigationBarManage.findNavigationLabelById(dto);

	}

	/**
	 * 删除导航栏标签详情通过id
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteNavigationLableWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteNavigationLableWithTx(NavigationLabelVO vo, HttpServletRequest req) {
		// 参数校验
		if (EmptyUtil.isEmpty(vo.getId()))
			return JsonResult.fail("id不能为空");

		NavigationLabelDTO dto = NavigationLabelConverter.toDTO(vo);
		int rt = navigationBarManage.deleteNavigationLableWithTx(dto);
		return JsonResult.success(rt);
	}

	// ====================================导航栏标签与导航栏的分割线===========================================

	/**
	 * 新增或编辑导航栏
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateNavigationBarWithTx")
	@ResponseBody
	public JsonResult<Long> insertOrUpdateNavigationBarWithTx(NavigationBarVO vo, String navigationLabelIds,
			String companyIds, HttpServletRequest req) {
		// 参数校验
		CacheUser cacheUser = getCacheUser();
		vo.setUpdateUser(vo.getId() != null ? cacheUser.getId() : null);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		try {
			List<Long> navigationLabelIdList = JSONArray.parseArray(navigationLabelIds, Long.class);
			vo.setNavigationLabelIdList(navigationLabelIdList);
		} catch (Exception e) {
			return JsonResult.fail("导航栏标签id集合参数错误");
		}

		try {
			List<Long> companyIdList = JSONArray.parseArray(companyIds, Long.class);
			vo.setCompanyIdList(companyIdList);
		} catch (Exception e) {
			return JsonResult.fail("导航栏公司id集合参数错误");
		}

		return navigationBarManage.insertOrUpdateNavigationBarWithTx(NavigationBarConverter.toDTO(vo));
	}

	/**
	 * 查询导航栏详情通过id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findNavigationBarById")
	@ResponseBody
	public JsonResult<NavigationBarVO> findNavigationBarById(Long id) {
		// 参数校验
		if (EmptyUtil.isEmpty(id))
			return JsonResult.fail("id不能为空");

		NavigationBarDTO dto = new NavigationBarDTO();
		dto.setId(id);

		return navigationBarManage.findNavigationBarById(dto);

	}

	/**
	 * 删除导航栏详情通过id
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteNavigationBarWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteNavigationBarWithTx(NavigationBarVO vo, HttpServletRequest req) {
		if (EmptyUtil.isEmpty(vo.getId()))
			return JsonResult.fail("id不能为空");
		NavigationBarDTO dto = NavigationBarConverter.toDTO(vo);
		int rt = navigationBarManage.deleteNavigationBarWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 导航栏列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findNavigationBarOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findNavigationBarOfPage(NavigationBarVO vo, Long companyId, 
			Pagination page, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		NavigationBarDTO dto = NavigationBarConverter.toDTO(vo);
		PageResult<Map<String, Object>> rt = navigationBarManage.findNavigationBarOfPageByBlurry(dto, companyId, page);
	
		return JsonResult.success(rt);

	}
	
	/**
	 * 通过导航栏id查询公司信息
	 * @param id
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findNavigationBarCompanyOfPage")
	@ResponseBody
	public JsonResult<List<CompanyDTO>> findNavigationBarCompanyOfPage(Long id, Pagination page,
			HttpServletRequest req) {
		if (EmptyUtil.isEmpty(id))
			return JsonResult.fail("id不能为空");
		
		return JsonResult.success(navigationBarManage.findNavigationBarCompanyOfPage(id, page));
		
	}
	
	/**
	 * web商城查询首页导航栏和底部信息栏的信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryPageTabInfo")
	@ResponseBody
	public JsonResult<List<NavigationBarWebVO>> queryPageTabInfo(Integer navigationBarType, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		String clientIdStr = req.getHeader("clientId");		
		Long platformId = null;
		Long clientId = null;		
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		if(EmptyUtil.isNotEmpty(clientIdStr)){
			clientId = Long.valueOf(clientIdStr);
		}
		CacheUser cacheUser = getCacheUser();
		return navigationBarManage.findPageTabAllByCompanyId(navigationBarType,cacheUser.getCompanyId(),platformId,clientId);

	}
	

	// 业务方法：
	@RequestMapping(value = "/findNavigationBarAll")
	@ResponseBody
	public JsonResult<List<NavigationBarVO>> findNavigationBarAll(NavigationBarVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		NavigationBarDTO dto = NavigationBarConverter.toDTO(vo);
		List<NavigationBarDTO> rt = navigationBarManage.findNavigationBarAll(dto);
		return JsonResult.success(NavigationBarConverter.toVO(rt));

	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateNavigationBarByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateNavigationBarByIdWithTx(NavigationBarVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		NavigationBarDTO dto = NavigationBarConverter.toDTO(vo);
		int rt = navigationBarManage.updateNavigationBarWithTx(dto);
		return JsonResult.success(rt);
	}

}
