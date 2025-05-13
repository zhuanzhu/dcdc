package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:01
 */
public class SoMessageRelVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 */

	private String orderCode;		 
	/**
	 * 消息ID（请求第三方接口时的唯一标识）
	 */

	private String messageid;		 
	/**
	 * 类型（1-海关报关,2-支付信息报关，3-跨境物流报关）
	 */

	private Integer type;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
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
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 * @return 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 * @param orderCode 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}	
	/**
	 * 消息ID（请求第三方接口时的唯一标识）
	 * @return 消息ID（请求第三方接口时的唯一标识）
	 */
	public String getMessageid() {
		return messageid;
	}

	/**
	 * 消息ID（请求第三方接口时的唯一标识）
	 * @param messageid 消息ID（请求第三方接口时的唯一标识）
	 */
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}	
	/**
	 * 类型（1-海关报关,2-支付信息报关，3-跨境物流报关）
	 * @return 类型（1-海关报关,2-支付信息报关，3-跨境物流报关）
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型（1-海关报关,2-支付信息报关，3-跨境物流报关）
	 * @param type 类型（1-海关报关,2-支付信息报关，3-跨境物流报关）
	 */
	public void setType(Integer type) {
		this.type = type;
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
	