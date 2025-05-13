package com.egeo.components.user.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.bean.UniAuthMenu;
import com.egeo.components.user.common.BusinessException;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.common.ResultManager;
import com.egeo.components.user.controller.views.request.UniauthRoleMenuParam;
import com.egeo.components.user.dto.Menu;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.facade.MenuFacade;
import com.egeo.components.user.service.UniauthMenuService;
import com.egeo.components.user.service.read.RoleMenuReadService;
import com.egeo.components.user.service.write.RoleMenuWriteService;
import com.egeo.components.user.vo.MenuVO;
import com.egeo.components.utils.ResultUtil;
import com.egeo.components.utils.UtilHelper;
import com.egeo.config.RuntimeContext;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.log.XLogger;
import com.egeo.vo.BaseResponse;
import com.egeo.web.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthMenuController Author:
 * EDZ Date: 2018/10/23 14:52 Description: 资源 History: <author> <time> <version>
 * <desc> 作者姓名 修改时间 版本号 描述
 */
@Api(description = "权限--资源管理")
@Controller
@RequestMapping("/api/uniauth/menu")
public class UniauthMenuController {
	@Autowired
	private UniauthMenuService uniauthMenuService;

	public XLogger logger = XLogger.getLogger(this.getClass().getName());
    @Resource(name = "menu")
    private com.egeo.components.user.business.MenuManage menuManage;

	@Resource(name="menuFacade")
	private MenuFacade menuFacade;
	@Autowired
	private RoleMenuWriteService roleMenuWriteService;
	@Autowired
	private RoleMenuReadService roleMenuReadService;
    /**
     * 查询所有的菜单，通过角色所拥有的菜单判断是否选中
     */
    @RequestMapping(value = "roleMenuEditResouce")
    @ResponseBody
    public BaseResponse<Map<String, List<?>>> roleMenuEditResouce(@RequestParam(value = "rid") Long rid) {
    	BaseResponse<Map<String, List<?>>> response = new BaseResponse<>();

		if (UtilHelper.isEmpty(rid) ) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}
		Map<String, List<?>> results = new HashMap<>();
		List<MenuDTO> selectedList = menuFacade.menuByRoleId(rid).getData();
		List<Menu> allTree  = menuFacade.getShowMenuListBySysCode(selectedList.get(0).getSysCode(), 7l);
		
		List<String> selectedSet = null;
		if (selectedList!=null) {
			selectedSet = new ArrayList<String>();
			for (MenuDTO o : selectedList) {
				selectedSet.add(o.getId().longValue()+"");
			}
		}
		
		results.put("allTree", allTree);
		results.put("selectedTree", selectedSet);
		response.setData(results);
    	return response;
    }
    
    
	@ApiOperation("资源管理--获取子级接口")
	@RequestMapping(value = "/getChildrens", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthMenu>> getChildrens(
			@RequestParam(value = "parentCode", required = false) String parentCode,
			@RequestParam(value = "menuState", required = false) Integer menuState) {
		BaseResponse<List<UniAuthMenu>> response = new BaseResponse<List<UniAuthMenu>>();
		UniAuthMenu uniAuthMenu = new UniAuthMenu();
		uniAuthMenu.setMenuState(menuState);
		if (UtilHelper.isEmpty(parentCode)) {
			uniAuthMenu.setMenuParentCode("$");
		} else {
			uniAuthMenu.setMenuParentCode(parentCode);
		}

		List<UniAuthMenu> list = uniauthMenuService.listByProperty(uniAuthMenu);
		response.setData(list);
		return response;
	}
	@ApiOperation("资源管理--获取子级接口")
	@RequestMapping(value = "/per/getAllChildrens", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<Menu>> getAllChildrens() {
		BaseResponse<List<Menu>> response = new BaseResponse<List<Menu>>();
		List<Menu> list = menuManage.getMenuTreeOfPlatformId(7l);
		response.setData(list);
		return response;
	}
	@ApiOperation("资源管理--获取菜单树接口")
	@RequestMapping(value = "/getTree", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthMenu>> getTree(
			@RequestParam(value = "menuCode", required = false) String menuCode) {
		BaseResponse<List<UniAuthMenu>> response = new BaseResponse<List<UniAuthMenu>>();
		UniAuthMenu uniAuthMenu = new UniAuthMenu();
		if (!UtilHelper.isEmpty(menuCode)) {
			uniAuthMenu.setMenuCode(menuCode);
		} else {
			uniAuthMenu.setMenuParentCode("$");
		}

		List<UniAuthMenu> list = uniauthMenuService.getTree(uniAuthMenu);
		response.setData(list);
		return response;
	}

	@ApiOperation("菜单管理--新增菜单接口")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> insert(@RequestBody  Menu menu) {

        MenuVO vo = new MenuVO();
        vo.setId(menu.getId());
        vo.setName(menu.getTitle());
        vo.setHref(menu.getIndex());
        vo.setRemarks(menu.getRemarks());
        vo.setIcon(menu.getIcon());
        vo.setIsShow(menu.getIsShow());
        vo.setSysCode(menu.getSysCode());
        vo.setParentId(menu.getParentId());
        vo.setSort(menu.getSort());

        JsonResult<String> result = new JsonResult<String>();
        logger.info("更新菜单，菜单名称"+vo.getName());

        try {
            if (logger.isInfoEnabled()) {
                logger.info("开始更新菜单, MenuDTO = {}", vo);
            }
            String id = menuManage.saveOrUpdate(vo, vo.getName());
            if (logger.isInfoEnabled()) {
                logger.info("更新菜单成功, MenuDTOName = {}", vo.getName());
            }
            result.setData(id);
            return result;
        } catch (Exception e) {
            logger.error("更新菜单异常！ MenuName = " + vo.getName(), e);
            result.setCode(1);
            result.setError("更新菜单失败:" + e.getMessage());
            return result;
        }
    
    
	
	}

	@ApiOperation("菜单管理--修改菜单接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> update(@RequestBody  Menu menu) {

        MenuVO vo = new MenuVO();
        vo.setId(menu.getId());
        vo.setName(menu.getTitle());
        vo.setHref(menu.getIndex());
        vo.setRemarks(menu.getRemarks());
        vo.setIcon(menu.getIcon());
        vo.setIsShow(menu.getIsShow());
        vo.setSysCode(menu.getSysCode());
        vo.setParentId(menu.getParentId());
        vo.setSort(menu.getSort());
        JsonResult<String> result = new JsonResult<String>();
        logger.info("更新菜单，菜单名称"+vo.getName());
        try {
            if (logger.isInfoEnabled()) {
                logger.info("开始更新菜单, MenuDTO = {}", vo);
            }
            String id = menuManage.saveOrUpdate(vo, vo.getName());
            if (logger.isInfoEnabled()) {
                logger.info("更新菜单成功, MenuDTOName = {}", vo.getName());
            }
            result.setData(id);
            return result;
        } catch (Exception e) {
            logger.error("更新菜单异常！ MenuName = " + vo.getName(), e);
            result.setCode(1);
            result.setError("更新菜单失败:" + e.getMessage());
            return result;
        }
    
    
	}

	@ApiOperation("菜单管理--拖动菜单接口")
	@RequestMapping(value = "/updateIndex", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> updateIndex(@RequestParam("mid") Integer mid,
			@RequestParam("newIndex") Integer newIndex) {
		BaseResponse<String> response = new BaseResponse<String>();
		if (UtilHelper.isEmpty(mid) || UtilHelper.isEmpty(newIndex)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		uniauthMenuService.updateIndex(mid, newIndex);
		return response;
	}

	@ApiOperation("部门管理--删除菜单接口")
	@RequestMapping(value = "/deleteByPK", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> deleteByPK(@RequestParam("id") Long id) {

        MenuDTO menuDTO = menuManage.getMenuId(id);
        if(id.equals(PlatformKeyConstant.SYSTEM_SETUP_ID)){
            throw new BusinessException(BusinessExceptionConstant.NO_DELETE_MENU, "不能删除与系统设置相关的菜单"); 
        }
        List<Long> pid = menuDTO.getPid();
        if(pid != null){
        	boolean bl = false;
            for (Long long1 : pid) {
                if(long1.equals(PlatformKeyConstant.SYSTEM_SETUP_ID)){
                    bl = true;
                }
            }
            if(bl){
                throw new BusinessException(BusinessExceptionConstant.NO_DELETE_MENU, "不能删除与系统设置相关的菜单"); 
            }
        }
        JsonResult<String> result = new JsonResult<String>();
        if (id != null) {
            MenuDTO dto = new MenuDTO();
            dto.setId(id);
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("删除菜单, MenuDTOID = {}", dto.getId());
                }
                menuManage.deleteWithTx(dto);
                if (logger.isInfoEnabled()) {
                    logger.info("删除菜单成功, MenuDTOID = {}", dto.getId());
                }
                result.setSuccess("删除成功");
                return result;
            } catch (Exception e) {
                logger.error("删除菜单失败！id = " + dto.getId(), e);
                result.setError("删除失败：" + e.getMessage());
                result.setCode(1);
                return result;
            }
        } else {
            result.setError("删除失败!");
            result.setCode(1);
            return result;
        }

    
	}

	@ApiOperation("资源管理--根据ID查询资源详情接口")
	@RequestMapping(value = "/getDetails", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<UniAuthMenu> getDetails(@RequestParam(value = "mid") Integer mid) {
		BaseResponse<UniAuthMenu> response = new BaseResponse<UniAuthMenu>();
		if (UtilHelper.isEmpty(mid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		UniAuthMenu details = uniauthMenuService.getDetails(mid);
		response.setData(details);
		return response;
	}

	@ApiOperation("角色权限设置--根据登录用户和角色ID查询菜单接口")
	@RequestMapping(value = "/getTreeByUuidAndRid", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<Map<String, List<?>>> getTreeByUuidAndRid(@RequestParam(value = "rid") Integer rid,
			@RequestHeader("userUuid") String userUuid) {
		BaseResponse<Map<String, List<?>>> response = new BaseResponse<>();

		if (UtilHelper.isEmpty(rid) || UtilHelper.isEmpty(userUuid)) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		Map<String, List<?>> results = uniauthMenuService.getTreeByUuidAndRid(userUuid, rid);
		response.setData(results);
		return response;
	}

	@ApiOperation("角色权限设置--保存权限接口")
	@RequestMapping(value = "/insertRoleMenu", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> insertRoleMenu(@RequestBody UniauthRoleMenuParam uniauthRoleMenuParam) {

		BaseResponse<String> response = new BaseResponse<String>();
		if (UtilHelper.isEmpty(uniauthRoleMenuParam) || UtilHelper.isEmpty(uniauthRoleMenuParam.getRid())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}
		List<Menu> menus = menuFacade.getMenuIds(uniauthRoleMenuParam.getMenuCodes());
		List<RoleMenuDTO> dtos = new ArrayList<RoleMenuDTO>();
		for(Menu menu : menus) {
			RoleMenuDTO dto = new RoleMenuDTO();
			dto.setMenuId(menu.getId());
			dto.setRoleId(uniauthRoleMenuParam.getRid());
			dtos.add(dto);
		}
		roleMenuWriteService.refreshWithTx(dtos, uniauthRoleMenuParam.getRid());
		return response;
	}

	@ApiOperation("资源管理--根据登录用户查询菜单接口")
	@RequestMapping(value = "/getMenuByUuid", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<List<UniAuthMenu>> getMenuByUuid(
			@ApiParam(value = "系统编码(必传)") @RequestParam(value = "sysCode") String sysCode,
			@ApiParam(value = "用户uuid(必传)") @RequestHeader("userUuid") String userUuid) {
		BaseResponse<List<UniAuthMenu>> response = new BaseResponse<List<UniAuthMenu>>();
		try {
			if (UtilHelper.isEmpty(sysCode) || UtilHelper.isEmpty(userUuid)) {
				return ResultUtil.fail2List(com.egeo.components.user.common.ResponseCodeEnum.PARAM_IS_NULL, UniAuthMenu.class);
			}

			List<UniAuthMenu> menus = uniauthMenuService.getMenuByUuid(userUuid, sysCode);

			/*if((!SpringContextTool.isPrd())&&userUuid.equalsIgnoreCase("3caf2de2d74b4d7894a513b71319b111")) {
				return ResultUtil.fail2List(com.egeo.components.user.common.ResponseCodeEnum.ALL_UNIAUTHMENU, UniAuthMenu.class);
			}*/
			if (menus == null || menus.size() == 0) {
				return ResultUtil.fail2List(com.egeo.components.user.common.ResponseCodeEnum.NO_UNIAUTHMENU, UniAuthMenu.class);
			}
			response.setData(menus);
		} catch (BusinessException e) {
			response.setCode(e.getErrNo());
			response.setMsg(e.getErrNoMsg());
		} catch (Exception e) {
			response.setCode(ResultManager.CODE_ERROR);
			response.setMsg(ResultManager.MSG_ERROR);
		}
		return response;
	}

	@ApiOperation("资源管理--根据登录用户和域名查询访问资源接口")
	@RequestMapping(value = "/getResourceByUuidAndDomain", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<Boolean> getResourceByUuidAndDomain(
			@RequestParam(value = "domain", required = false) String domain,
			@RequestParam(value = "userUuid", required = false) String userUuid, @RequestParam("url") String url) {
		BaseResponse<Boolean> response = new BaseResponse<Boolean>();

		Boolean resourceByUuidAndDomain = uniauthMenuService.getResourceByUuidAndDomain(userUuid, domain, url);
		response.setData(resourceByUuidAndDomain);
		return response;
	}
}
