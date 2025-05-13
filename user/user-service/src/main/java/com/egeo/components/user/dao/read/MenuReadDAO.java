package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.MenuPO;
import com.egeo.orm.BaseReadDAO;

public interface MenuReadDAO extends BaseReadDAO<MenuPO> {

    List<MenuPO> getMenuListByUserId(@Param("userId")Long userId,@Param("platformId")Long platformId);
    List<MenuPO> getMenuListBySysCode(@Param("sysCode")String sysCode,@Param("platformId")Long platformId);

    List<MenuPO> getShowMenuListByUserId(@Param("userId")Long userId,@Param("platformId")Long platformId);

    List<MenuPO> menuListByroleId(Long roleId);

    List<MenuPO> menuByPlatformId(Long platformId);

	List<MenuPO> menuAllByMenuIds(@Param("menus")String menus);

	List<MenuPO> menuByMenuIds(@Param("menuIds")String menuIds);
}
