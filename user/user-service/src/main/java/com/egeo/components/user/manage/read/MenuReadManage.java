package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.MenuPO;

public interface MenuReadManage {

    public List<MenuPO> find(MenuPO po);

    public MenuPO getMenuId(Long menuId);

    public List<MenuPO> getMenuListByUserId(Long UserId,Long platformId);
    public List<MenuPO> getMenuListBySysCode(String sysCode,Long platformId);

    public List<MenuPO> getShowMenuListByUserId(Long UserId,Long platformId);

    public List<MenuPO> getMenu(Long parentId);

    public List<MenuPO> menuListByroleId(Long roleId);

    public List<MenuPO> menuByPlatformId(Long platformId);

	public List<MenuPO> menuAllByMenuIds(String menus);

	public List<MenuPO> menuByMenuIds(String menuIds);
}
