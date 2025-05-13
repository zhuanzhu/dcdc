/**
 * Created By: XI
 * Created On: 2018-10-25 10:15:10
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.bean;

import java.io.Serializable;


public class UniauthRoleMenu implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	角色菜单ID
	  */
	private Integer rmid;

	/**
	  *	角色ID
	  */
	private Integer rid;

	/**
	  *	菜单编号
	  */
	private String menuCode;

	/**
	  *	角色菜单ID
	  */
	public Integer getRmid()
	{
		return rmid;
	}
	
	/**
	  *	角色菜单ID
	  */
	public void setRmid(Integer rmid)
	{
		this.rmid = rmid;
	}
	
	/**
	  *	角色ID
	  */
	public Integer getRid()
	{
		return rid;
	}
	
	/**
	  *	角色ID
	  */
	public void setRid(Integer rid)
	{
		this.rid = rid;
	}
	
	/**
	  *	菜单编号
	  */
	public String getMenuCode()
	{
		return menuCode;
	}
	
	/**
	  *	菜单编号
	  */
	public void setMenuCode(String menuCode)
	{
		this.menuCode = menuCode;
	}
	
	public String toString()
	{
		return "UniauthRoleMenu [" + 
					"rmid=" + rmid + 
					", rid=" + rid + 
					", menuCode=" + menuCode + 
				"]";
	}
}

