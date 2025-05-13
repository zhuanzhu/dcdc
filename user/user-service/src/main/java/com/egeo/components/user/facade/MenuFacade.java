package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.dto.Menu;
import com.egeo.components.user.dto.MenuDTO;
import com.egeo.components.user.service.read.MenuReadService;
import com.egeo.components.user.service.write.MenuWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.JsonResult;



@Component
public class MenuFacade {
	
	@Resource
	private MenuReadService menuReadService;
	
	@Resource
	private MenuWriteService menuWriteService;

	public JsonResult<PageResult<Menu>> find(Pagination page,MenuDTO menuDTO) {
		return menuReadService.findWithTx(page,menuDTO);
		
	}

	public MenuDTO getMenuId(Long menuId) {
		return menuReadService.getMenuIdWithTx(menuId);
	}

	public List<Menu> getMenuIds(List<String> menuIds) {
		List<Menu> menus = menuReadService.menuByMenuIds(StringUtils.join(",", menuIds.toArray(new String[0])));
		
		return menus;
	}
	public String saveOrUpdate(MenuDTO dto,String userName) {
		
		return menuWriteService.saveOrUpdateWithTx(dto,userName);
	}

	public void deleteWithTx(MenuDTO dto) {
		
		menuWriteService.deleteWithTx(dto);
	}

	public List<Menu> getMenuListByUserId(Long UserId,Long platformId) {
		List<Menu> list = menuReadService.getMenuListByUserIdWithTx(UserId,platformId);
		StringBuffer menus = new StringBuffer();
    	if(list.size() > 0){
			for (Menu menu : list) {
				menus.append(menu.getId());
				menus.append(",");
			}
			
			//循环获取所有菜单集合
	    	List<Menu> menuLists = this.pisBymenuIds(menus.substring(0, menus.length()-1),list);
	    	
	    	//List去重
	    	HashSet<Menu> h  =   new  HashSet<Menu>(menuLists);
	    	StringBuffer menuIds = new StringBuffer();
	    	for (Menu menu : h) {
	    		menuIds.append(menu.getId());
	    		menuIds.append(",");
			}
	    	//根据所有菜单id重新查询一次来进行排序
	    	List<Menu> menuList = menuReadService.menuByMenuIds(menuIds.substring(0, menuIds.length()-1));
			List<Menu> sortList = new ArrayList<Menu>();
	        for (Menu tree : menuList) {
	            for (Menu t : menuList) {
	                if (t.getParentId().equals(tree.getId())) {
	                    if (tree.getChildren() == null) {
	                        List<Menu> myChildrens = new ArrayList<Menu>();
	                        myChildrens.add(t);
	                        tree.setChildren(myChildrens);
	                    } else {
	                        tree.getChildren().add(t);
	                    }
	                }
	            }
	            if (tree.getParentId().equals(PlatformKeyConstant.PLATFORMID)) {
	                sortList.add(tree);
	            }
	        }
			return sortList;
		}else{
			return null;
		}
		
    	
	}

	public List<Menu> getShowMenuListBySysCode(String sysCode,Long platformId) {

		List<Menu> list = menuReadService.getShowMenuListBySysCode(sysCode,platformId);
		StringBuffer menus = new StringBuffer();
		if(list.size() > 0){
			for (Menu menu : list) {
				menus.append(menu.getId());
				menus.append(",");
			}

			//循环获取所有菜单集合
			List<Menu> menuLists = this.pisBymenuIds(menus.substring(0, menus.length()-1),list);

			//List去重
			HashSet<Menu> h  =   new  HashSet<Menu>(menuLists);
			StringBuffer menuIds = new StringBuffer();
			for (Menu menu : h) {
				menuIds.append(menu.getId());
				menuIds.append(",");
			}
			//根据所有菜单id重新查询一次来进行排序
			List<Menu> menuList = menuReadService.menuByMenuIds(menuIds.substring(0, menuIds.length()-1));
			List<Menu> sortList = new ArrayList<Menu>();
			for (Menu tree : menuList) {
				for (Menu t : menuList) {
					if (t.getParentId().equals(tree.getId())) {
						if (tree.getChildren() == null) {
							List<Menu> myChildrens = new ArrayList<Menu>();
							myChildrens.add(t);
							tree.setChildren(myChildrens);
						} else {
							tree.getChildren().add(t);
						}
					}
				}
				if (tree.getParentId().equals(PlatformKeyConstant.PLATFORMID)) {
					sortList.add(tree);
				}
			}
			return sortList;
		}else{
			return null;
		}


	
		
	}
	public List<Menu> getShowMenuListByUserId(Long UserId,Long platformId) {
		List<Menu> list = menuReadService.getShowMenuListByUserId(UserId,platformId);
		StringBuffer menus = new StringBuffer();
		if(list.size() > 0){
			for (Menu menu : list) {
				menus.append(menu.getId());
				menus.append(",");
			}

			//循环获取所有菜单集合
			List<Menu> menuLists = this.pisBymenuIds(menus.substring(0, menus.length()-1),list);

			//List去重
			HashSet<Menu> h  =   new  HashSet<Menu>(menuLists);
			StringBuffer menuIds = new StringBuffer();
			for (Menu menu : h) {
				menuIds.append(menu.getId());
				menuIds.append(",");
			}
			//根据所有菜单id重新查询一次来进行排序
			List<Menu> menuList = menuReadService.menuByMenuIds(menuIds.substring(0, menuIds.length()-1));
			List<Menu> sortList = new ArrayList<Menu>();
			for (Menu tree : menuList) {
				for (Menu t : menuList) {
					if (t.getParentId().equals(tree.getId())) {
						if (tree.getChildren() == null) {
							List<Menu> myChildrens = new ArrayList<Menu>();
							myChildrens.add(t);
							tree.setChildren(myChildrens);
						} else {
							tree.getChildren().add(t);
						}
					}
				}
				if (tree.getParentId().equals(PlatformKeyConstant.PLATFORMID)) {
					sortList.add(tree);
				}
			}
			return sortList;
		}else{
			return null;
		}


	}
	public List<MenuDTO> getMenu(Long parentId) {
		
		return menuReadService.getMenu(parentId);
	}

    public JsonResult<List<Menu>> showMenuWithTx(Long roleId,Long platformId) {
        
        return menuReadService.showMenuWithTx(roleId,platformId);
    }

    public List<MenuDTO> menuByPlatformId(Long platformId) {
        
        return menuReadService.menuByPlatformId(platformId);
    }

    public JsonResult<List<MenuDTO>> menuByRoleId(Long roleId) {
        
        return menuReadService.menuByRoleId(roleId);
    }
	
    public List<Menu> pisBymenuIds(String menus,List<Menu> lists){
    	
		List<Menu> menuList = menuReadService.menuAllByMenuIds(menus);
		if(menuList.size() != 0 && !menuList.get(0).getId().equals(1L)){
			StringBuffer menuAll = new StringBuffer();
			for (Menu menu : menuList) {
				//父类集合
				menuAll.append(menu.getId());
				menuAll.append(",");
				//最终所以菜单集合
				lists.add(menu);
			}
			this.pisBymenuIds(menuAll.toString().substring(0, menuAll.length()-1),lists);
		}else{
			for (Menu menu : menuList) {
				
				//最终所以菜单集合
				lists.add(menu);
			}

		}
	return lists;
	
}
	
	


}
	