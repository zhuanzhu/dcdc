package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoUnitPO {


	private Long id;

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 子订单id
	 */
	private Long soChildId;	

	/**
	 * puid
	 */
	private Long puId;	

	/**
	 * unitid
	 */
	private Long unitId;	

	/**
	 * unit卡号
	 */
	private String unitNo;	

	/**
	 * unit密码
	 */
	private String unitCipher;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 订单id
	 * @return 订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 订单id
	 * @param soId 订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}

	/**
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}

	/**
	 * puid
	 * @return puid
	 */
	public Long getPuId() {
		return puId;
	}

	/**
	 * puid
	 * @param puId puid
	 */
	public void setPuId(Long puId) {
		this.puId = puId;
	}

	/**
	 * unitid
	 * @return unitid
	 */
	public Long getUnitId() {
		return unitId;
	}

	/**
	 * unitid
	 * @param unitId unitid
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	/**
	 * unit卡号
	 * @return unit卡号
	 */
	public String getUnitNo() {
		return unitNo;
	}

	/**
	 * unit卡号
	 * @param unitNo unit卡号
	 */
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	/**
	 * unit密码
	 * @return unit密码
	 */
	public String getUnitCipher() {
		return unitCipher;
	}

	/**
	 * unit密码
	 * @param unitCipher unit密码
	 */
	public void setUnitCipher(String unitCipher) {
		this.unitCipher = unitCipher;
	}

	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
}
	