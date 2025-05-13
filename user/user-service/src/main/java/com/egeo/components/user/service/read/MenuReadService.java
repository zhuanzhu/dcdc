package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.Menu;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface MenuReadService {

    public JsonResult<PageResult<Menu>> findWithTx(Pagination page,MenuDTO menuDTO);
    
    public MenuDTO getMenuIdWithTx(Long menuId);

    public List<Menu> getMenuListByUserIdWithTx(Long UserId,Long platformId);

    public List<Menu> getShowMenuListByUserId(Long UserId,Long platformId);
    
    public List<Menu> getShowMenuListBySysCode(String sysCode,Long platformId);

    public List<MenuDTO> getMenu(Long parentId);

    JsonResult<List<Menu>> showMenuWithTx(Long roleId,Long platformId);

    public List<MenuDTO> menuByPlatformId(Long platformId);

    public JsonResult<List<MenuDTO>> menuByRoleId(Long roleId);

	public List<Menu> menuAllByMenuIds(String menus);

	public List<Menu> menuByMenuIds(String menuIds);

}
