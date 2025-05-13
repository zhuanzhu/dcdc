/**
 * Created By: XI
 * Created On: 2018-10-23 17:53:42
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.bean;

import java.io.Serializable;


public class UniauthMenuDomain implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	主键
	  */
	private Integer mdid;

	/**
	  *	菜单id
	  */
	private Integer mid;

	/**
	  *	域名id
	  */
	private Integer domainId;

	/**
	  *	主键
	  */
	public Integer getMdid()
	{
		return mdid;
	}
	
	/**
	  *	主键
	  */
	public void setMdid(Integer mdid)
	{
		this.mdid = mdid;
	}
	
	/**
	  *	菜单id
	  */
	public Integer getMid()
	{
		return mid;
	}
	
	/**
	  *	菜单id
	  */
	public void setMid(Integer mid)
	{
		this.mid = mid;
	}
	
	/**
	  *	域名id
	  */
	public Integer getDomainId()
	{
		return domainId;
	}
	
	/**
	  *	域名id
	  */
	public void setDomainId(Integer domainId)
	{
		this.domainId = domainId;
	}
	
	public String toString()
	{
		return "UniauthMenuDomain [" + 
					"mdid=" + mdid + 
					", mid=" + mid + 
					", domainId=" + domainId + 
				"]";
	}
}

