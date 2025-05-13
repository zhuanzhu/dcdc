package com.egeo.components.user.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.dto.Menu;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.components.user.vo.MenuVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

/**
 * 菜单控制层
 * @author 闵浮龙
 *
 */

@Controller
@RequestMapping("/api/user/menu")
public class MenuAction extends BaseSpringController {

    @Resource(name = "menu")
    private com.egeo.components.user.business.MenuManage MenuManage;
    @Value("${adminUser.name}")
    private String adminUserName;
    /**
     * 按条件查询所有菜单
     *
     * @return
     */
    @RequestMapping(value = "menuList")
    @ResponseBody
    public JsonResult<PageResult<Menu>> find(Pagination page, MenuVO menuVO) {
        
        JsonResult<PageResult<Menu>> jsonResult = new JsonResult<PageResult<Menu>>();
            logger.info("orderby:"+page.getOrderBy()+"platform:"+menuVO.getPlatformId());
            logger.info("sort:"+menuVO.getSort());
            //如果平台id为空则设置默认值
            if (menuVO.getPlatformId() == null) {
                menuVO.setPlatformId(PlatformKeyConstant.PLATFORMID);
            }
        
            try {
                logger.info("查询所有菜单");
                return MenuManage.find(page, menuVO);
                
            } catch (Exception e) {
                logger.error("查询所有菜单异常！", e);
                jsonResult.setCode(1);
                jsonResult.setError("查询所有菜单失败:" + e.getMessage());
                return jsonResult;
            }
            
    }

    /**
     * 根据用户id查询需要显示的菜单
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "showList")
    @ResponseBody
    public JsonResult<List<Menu>> findShow(HttpServletRequest req, HttpServletResponse res) {
        CacheUser userCache = this.getCacheUser();
        JsonResult<List<Menu>> lists = new JsonResult<List<Menu>>();
        CacheUser cacheUser = this.getCacheUser();
        Long id = cacheUser.getId();
        Long platformId = null;
        String str = req.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
            logger.info("根据用户id查询菜单,  id = {}", id);
            if (id != null) {
            	if(platformId == 1){
	                // 判断是否为admin，是返回管理员菜单
	                if (userCache.getLoginName().equals(adminUserName)) {
	                    MenuVO menuVO = new MenuVO();
	                    menuVO.setPlatformId(PlatformKeyConstant.PLATFORMID);
	                    JsonResult<PageResult<Menu>> jsonResult = find(new Pagination(), menuVO);
	                    PageResult<Menu> result = jsonResult.getData();
	                    JsonResult<List<Menu>> list = new JsonResult<List<Menu>>();
	                    list.setData(result.getList());
	                    return list;
	                }
            	}
                    List<Menu> list = MenuManage.getShowMenuListByUserId(id,platformId);

                if (logger.isInfoEnabled()) {
                    logger.info("根据用户id查询菜单成功,  id = {}", id);
                }
                
                lists.setData(list);
                return lists;
            } else {
                lists.setCode(1);
                lists.setError("系统出错,请联系管理员！");
                return lists;
            }
    }

    /**
     * 根据菜单Id查询菜单
     * 
     * @return
     */
    @RequestMapping(value = "getId")
    @ResponseBody
    public JsonResult<MenuDTO> getMenuId(Long menuId) {
        logger.info("根据菜单id" + menuId + "查询菜单");
        JsonResult<MenuDTO> result = new JsonResult<MenuDTO>();
        if (menuId != null) {
            MenuDTO menuDTO = MenuManage.getMenuId(menuId);
            result.setData(menuDTO);
            return result;
        } else {
            result.setCode(1);
            result.setError("系统出错,请联系管理员！");
            return result;
        }

    }

    /**
     * 更新
     */
    @RequestMapping(value = "saveOrUpdate")
    @ResponseBody
    public JsonResult<String> saveOrUpdate(@Valid MenuVO vo, BindingResult br, HttpServletRequest req) {
        JsonResult<String> result = new JsonResult<String>();
        logger.info("更新菜单，菜单名称"+vo.getName());
        if (br.hasErrors()) { // 如果实体类校验失败
            List<ObjectError> errors = br.getAllErrors();
            result.setError(errors.get(0).getDefaultMessage());
            result.setCode(1);
            return result;
        } else {
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("开始更新菜单, MenuDTO = {}", vo);
                }
                String id = MenuManage.saveOrUpdate(vo, vo.getName());
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
    }

    /**
     * 根据菜单id删除
     * 
     * @param dto
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult<String> delete(MenuDTO dto) {
        MenuDTO menuDTO = MenuManage.getMenuId(dto.getId());
        if(dto.getId().equals(PlatformKeyConstant.SYSTEM_SETUP_ID)){
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
        if (dto.getId() != null) {
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("删除菜单, MenuDTOID = {}", dto.getId());
                }
                MenuManage.deleteWithTx(dto);
                if (logger.isInfoEnabled()) {
                    logger.info("删除菜单成功, MenuDTOID = {}", dto.getId());
                }
                result.setSuccess("删除成功");
                return result;
            } catch (Exception e) {
                logger.error("删除菜单失败！ name = " + dto.getName() + ",mid = " + dto.getId(), e);
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

    /**
     * 查询所有的菜单，通过角色所拥有的菜单判断是否选中
     */
    @RequestMapping(value = "showMenu")
    @ResponseBody
    public JsonResult<List<Menu>> showMenu(Long roleId,Long platformId) {
        return MenuManage.showMenuWithTx(roleId,platformId);
    }
    /**
     * 根据角色id查询菜单
     */
    @RequestMapping(value = "menuByRoleId")
    @ResponseBody
    public JsonResult<List<MenuDTO>> menuByRoleId(Long roleId) {
        return MenuManage.menuByRoleId(roleId);
    }
    

    /**
     * 通过平台id查询菜单
     */
    @RequestMapping(value = "menuByPlatformId")
    @ResponseBody
    public JsonResult<List<MenuDTO>> menuByPlatformId(Long platformId) {
        JsonResult<List<MenuDTO>> jsonResult = new JsonResult<List<MenuDTO>>();
        List<MenuDTO> list = MenuManage.menuByPlatformId(platformId);
        jsonResult.setData(list);
        return jsonResult;
    }

}
