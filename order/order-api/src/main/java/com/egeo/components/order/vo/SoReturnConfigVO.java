package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:01
 */
public class SoReturnConfigVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 前台订单可退货期限
	 */

	private Integer returnDays;		 
	/**
	 * 前台订单可换货期限
	 */

	private Integer replacementDays;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 换货可用类型:1.不启用2.仅后台可申请3.前后台都可申请
	 */

	private Integer replacementType;		 
	/**
	 * 退货可用类型:1.仅后台可申请2.前后台都可申请
	 */

	private Integer returnType;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 前台订单可退货期限
	 * @return 前台订单可退货期限
	 */
	public Integer getReturnDays() {
		return returnDays;
	}

	/**
	 * 前台订单可退货期限
	 * @param returnDays 前台订单可退货期限
	 */
	public void setReturnDays(Integer returnDays) {
		this.returnDays = returnDays;
	}	
	/**
	 * 前台订单可换货期限
	 * @return 前台订单可换货期限
	 */
	public Integer getReplacementDays() {
		return replacementDays;
	}

	/**
	 * 前台订单可换货期限
	 * @param replacementDays 前台订单可换货期限
	 */
	public void setReplacementDays(Integer replacementDays) {
		this.replacementDays = replacementDays;
	}	
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}	
	/**
	 * 换货可用类型:1.不启用2.仅后台可申请3.前后台都可申请
	 * @return 换货可用类型:1.不启用2.仅后台可申请3.前后台都可申请
	 */
	public Integer getReplacementType() {
		return replacementType;
	}

	/**
	 * 换货可用类型:1.不启用2.仅后台可申请3.前后台都可申请
	 * @param replacementType 换货可用类型:1.不启用2.仅后台可申请3.前后台都可申请
	 */
	public void setReplacementType(Integer replacementType) {
		this.replacementType = replacementType;
	}	
	/**
	 * 退货可用类型:1.仅后台可申请2.前后台都可申请
	 * @return 退货可用类型:1.仅后台可申请2.前后台都可申请
	 */
	public Integer getReturnType() {
		return returnType;
	}

	/**
	 * 退货可用类型:1.仅后台可申请2.前后台都可申请
	 * @param returnType 退货可用类型:1.仅后台可申请2.前后台都可申请
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
	