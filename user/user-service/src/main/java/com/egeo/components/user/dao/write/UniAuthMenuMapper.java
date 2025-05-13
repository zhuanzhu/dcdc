package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.bean.UniAuthMenu;



public interface UniAuthMenuMapper {
	List<UniAuthMenu> byRid(Integer rid);
	
	List<UniAuthMenu> queryByParams(UniAuthMenu menu);
	
	/**
	 * @Title: insert 
	 * @Description: 为角色赋予对应菜单权限
	 * @param uniAuthMenu
	 * @return: int
	 */
	int insert(UniAuthMenu uniAuthMenu);
	
	/**
	 * @Title: delete 
	 * @Description: 根据角色ID删除对应的菜单权限
	 * @param rid
	 * @return: int
	 */
	int delete(Integer rid);

	List<UniAuthMenu> listByProperty(UniAuthMenu uniAuthMenu);

	List<UniAuthMenu> getTree(UniAuthMenu uniAuthMenu);

	int findByCount(UniAuthMenu uniAuthMenu);

	List<UniAuthMenu> getByUniAuthMenu(UniAuthMenu uniAuthMenu);

	int updatePart(UniAuthMenu uniAuthMenu);

	int updateIndex(@Param("index") Integer index, @Param("menuParentCodes") String menuParentCodes, @Param("minIndex") Integer minIndex, @Param("maxIndex") Integer maxIndex);

	UniAuthMenu getByPK(Integer mid);

	int updateStateByParentCodes(UniAuthMenu uniAuthMenu);

	List<UniAuthMenu> getListByRids(@Param("rids") List<Integer> rids);

	List<UniAuthMenu> getMenuByRids(@Param("rids") List<Integer> rids, @Param("menuParentCodes") String menuParentCodes);

	List<String> getResourceByRidsAndDomain(@Param("domain") String domain, @Param("rids") List<Integer> rids);
}
