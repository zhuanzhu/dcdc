package com.egeo.components.user.manage.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.MenuReadManage;
import com.egeo.components.user.dao.read.MenuReadDAO;
import com.egeo.components.user.po.MenuPO;

@Service
public class MenuReadManageImpl implements MenuReadManage {
	@Autowired
	private MenuReadDAO menuReadDAO;
	public List<MenuPO> find(MenuPO po) {
		
		return menuReadDAO.findAll(po,null);
		
	}
	@Override
	public MenuPO getMenuId(Long menuId) {
		MenuPO po = new MenuPO();
		po.setId(menuId);
		return menuReadDAO.findById(po);
	}
	@Override
	public List<MenuPO> getMenuListByUserId(Long UserId,Long platformId) {
		List<MenuPO> list = menuReadDAO.getMenuListByUserId(UserId,platformId);
		return list;
	}
	@Override
	public List<MenuPO> getShowMenuListByUserId(Long UserId,Long platformId) {
		List<MenuPO> list = menuReadDAO.getShowMenuListByUserId(UserId,platformId);
		return list;
	}
	@Override
	public List<MenuPO> getMenuListBySysCode(String sysCode,Long platformId) {
		List<MenuPO> list = menuReadDAO.getMenuListBySysCode(sysCode,platformId);
		return list;
	}

	@Override
	public List<MenuPO> getMenu(Long parentId) {
		MenuPO po = new MenuPO();
		po.setParentId(parentId);
		return menuReadDAO.findAll(po,null);
	}
    @Override
    public List<MenuPO> menuListByroleId(Long roleId) {
        
        return menuReadDAO.menuListByroleId(roleId);
    }
    @Override
    public List<MenuPO> menuByPlatformId(Long platformId) {
        
        return menuReadDAO.menuByPlatformId(platformId);
    }
	@Override
	public List<MenuPO> menuAllByMenuIds(String menus) {
		
		return menuReadDAO.menuAllByMenuIds(menus);
	}
	@Override
	public List<MenuPO> menuByMenuIds(String menuIds) {
		
		return menuReadDAO.menuByMenuIds(menuIds);
	}
	
}
	