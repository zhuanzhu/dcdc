package com.egeo.components.user.dao.write;

import java.util.List;

import com.egeo.components.user.bean.UniAuthElement;
import com.egeo.components.user.bean.UniAuthMenu;



public interface UniAuthElementMapper {
	/**
	 * @Title: byRid 
	 * @Description: 根据角色ID查询属性集合
	 * @param rid
	 * @return: List<Element>
	 */
	List<UniAuthElement> byRid(Integer rid);
	
	List<UniAuthElement> queryByParams(UniAuthMenu menu);
	
	/**
	 * @Title: insert 
	 * @Description: 为角色赋予对应按钮权限
	 * @param element
	 * @return: int
	 */
	int insert(UniAuthElement element);
	
	/**
	 * @Title: delete 
	 * @Description: 根据角色删除对应按钮权限
	 * @param rid
	 * @return: int
	 */
	int delete(Integer rid);
}
