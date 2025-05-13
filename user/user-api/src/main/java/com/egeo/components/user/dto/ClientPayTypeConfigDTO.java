package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-08-30 17:15:02
 */
public class ClientPayTypeConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 客户端id
	 */
	private Long clientId;	

	/**
	 * 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	private Integer payTypeCode;	

	/**
	 * 排序指数,数值越大排序越前
	 */
	private Integer indexCode;	

	/**
	 * 是否停用,1:停用,0:使用中
	 */
	private Integer isStop;	


	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	public Long getId() {
		return id;
	}

	public Long platformId;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 客户端id
	 * @return 客户端id
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * 客户端id
	 * @param clientId 客户端id
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	/**
	 * 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 * @return 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	public Integer getPayTypeCode() {
		return payTypeCode;
	}

	/**
	 * 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 * @param payTypeCode 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	public void setPayTypeCode(Integer payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	/**
	 * 排序指数,数值越大排序越前
	 * @return 排序指数,数值越大排序越前
	 */
	public Integer getIndexCode() {
		return indexCode;
	}

	/**
	 * 排序指数,数值越大排序越前
	 * @param indexCode 排序指数,数值越大排序越前
	 */
	public void setIndexCode(Integer indexCode) {
		this.indexCode = indexCode;
	}
	/**
	 * 是否停用,1:停用,0:使用中
	 * @return 是否停用,1:停用,0:使用中
	 */
	public Integer getIsStop() {
		return isStop;
	}

	/**
	 * 是否停用,1:停用,0:使用中
	 * @param isStop 是否停用,1:停用,0:使用中
	 */
	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
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
}
	