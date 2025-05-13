package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.user.vo.MenuVO;
import com.egeo.components.user.dto.Menu;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface MenuManage {

    public JsonResult<PageResult<Menu>> find(Pagination page,MenuVO menuVO);

    public MenuDTO getMenuId(Long menuId);

    public String saveOrUpdate(MenuVO vo,String userName);

    public void deleteWithTx(MenuDTO dto);
    public List<Menu> getMenuTreeOfPlatformId(Long platformId);
    public List<Menu> getMenuListByUserId(Long UserId,Long platformId);

    public List<Menu> getShowMenuListByUserId(Long UserId,Long platformId);

    public List<MenuDTO> getMenu(Long parentId);
    
    public JsonResult<List<Menu>> showMenuWithTx(Long roleId,Long platformId);

    public List<MenuDTO> menuByPlatformId(Long platformId);

    public JsonResult<List<MenuDTO>> menuByRoleId(Long roleId);

}
