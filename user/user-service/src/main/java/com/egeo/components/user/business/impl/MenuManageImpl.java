package com.egeo.components.user.business.impl;
	

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.MenuManage;
import com.egeo.components.user.converter.MenuConverter;
import com.egeo.components.user.dto.Menu;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.components.user.facade.MenuFacade;
import com.egeo.components.user.vo.MenuVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

@Service("menu")
public class MenuManageImpl implements MenuManage{

	
	@Resource(name="menuFacade")
	private MenuFacade menuFacade;
	
	@Override
	public JsonResult<PageResult<Menu>> find(Pagination page,MenuVO menuVO) {
		return menuFacade.find(page,MenuConverter.toDTO(menuVO));
		
	}

	@Override
	public MenuDTO getMenuId(Long menuId) {
		return menuFacade.getMenuId(menuId);
	}

	@Override
	public String saveOrUpdate(MenuVO vo,String userName) {
		return menuFacade.saveOrUpdate(MenuConverter.toDTO(vo),userName);
	}

	@Override
	public void deleteWithTx(MenuDTO dto) {
		menuFacade.deleteWithTx(dto);
		
	}

	@Override
	public List<Menu> getMenuListByUserId(Long UserId,Long platformId) {
		
		return menuFacade.getMenuListByUserId(UserId,platformId);
	}

	@Override
	public List<Menu> getShowMenuListByUserId(Long UserId,Long platformId) {

		return menuFacade.getShowMenuListByUserId(UserId,platformId);
	}

	@Override
	public List<Menu> getMenuTreeOfPlatformId(Long platformId) {
		List<Menu> platformMenus = menuFacade.getShowMenuListBySysCode("sys_platform", platformId);
		List<Menu> manageMenus = menuFacade.getShowMenuListBySysCode("sys_manage", platformId);
		Menu menuP = new Menu();
		menuP.setId(-1l);
		menuP.setSysCode("sys_platform");
		menuP.setTitle("平台管理系统");
		menuP.setIsShow("1");
        menuP.setSort(new BigDecimal(1));
        menuP.setChildren(platformMenus);
		Menu menuM = new Menu();
		menuM.setId(-2l);
		menuM.setSysCode("sys_manage");
		menuM.setTitle("商城管理系统");
		menuM.setIsShow("1");
		menuM.setSort(new BigDecimal(2));
		menuM.setChildren(manageMenus);
		List<Menu> rslt = new ArrayList<Menu>();
		rslt.add(menuP);
		rslt.add(menuM);
		return rslt;
	}
	@Override
	public List<MenuDTO> getMenu(Long parentId) {
		
		return menuFacade.getMenu(parentId);
	}

    @Override
    public JsonResult<List<Menu>> showMenuWithTx(Long roleId,Long platformId) {
        
        return menuFacade.showMenuWithTx(roleId,platformId);
    }

    @Override
    public List<MenuDTO> menuByPlatformId(Long platformId) {
        return menuFacade.menuByPlatformId(platformId);
    }

    @Override
    public JsonResult<List<MenuDTO>> menuByRoleId(Long roleId) {
        
        return menuFacade.menuByRoleId(roleId);
    }

	}
	