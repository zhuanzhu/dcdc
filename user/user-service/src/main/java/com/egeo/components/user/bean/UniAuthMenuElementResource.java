package com.egeo.components.user.bean;

/**
 * @ClassName: MenuElementResource 
 * @Description: 菜单、属性和资源关联对象
 * @author: Administrator
 * @date: 2017年11月16日 下午1:32:37
 */
public class UniAuthMenuElementResource {
	private String menuCode;
	private Integer eleId;
	private Integer resId;
	
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public Integer getEleId() {
		return eleId;
	}
	public void setEleId(Integer eleId) {
		this.eleId = eleId;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	
	
}
