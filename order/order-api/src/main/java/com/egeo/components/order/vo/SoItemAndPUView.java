package com.egeo.components.order.vo;

public class SoItemAndPUView {

	private Long id;

	private Long soChildId;
	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * puid
	 */
	private Long puId;
	/**
	 * 母订单id
	 */
	private Long soId;

	private Integer puCount;

	/**
	 * pu编号
	 */
	private String puSn;
	/**
	 * pu名称
	 */
	private String puName;

	/**
	 * 商品规格
	 */
	private String standard;

	private String channelProductId;

	public String getPuSn() {
		return puSn;
	}

	public void setPuSn(String puSn) {
		this.puSn = puSn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSoChildId() {
		return soChildId;
	}

	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPuId() {
		return puId;
	}

	public void setPuId(Long puId) {
		this.puId = puId;
	}

	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	public Integer getPuCount() {
		return puCount;
	}

	public void setPuCount(Integer puCount) {
		this.puCount = puCount;
	}

	public String getPuName() {
		return puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getChannelProductId() {
		return channelProductId;
	}

	public void setChannelProductId(String channelProductId) {
		this.channelProductId = channelProductId;
	}
}
