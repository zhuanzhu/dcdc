package com.egeo.components.order.vo;

/**
 * 可选收货地址列表VO
 * 用于订单收货信息修改弹框
 * @author graci
 *
 */
public class OptionalReceiverAddressVO {

	private Long id;
	private String name;
	private String mobile;
	private String province;
	private String city;
	private String county;
	private String address;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int type;



	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
