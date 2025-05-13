package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:44
 */
public class HomeConfigPO {


	private Long id;

	/**
	 * 类型 0:3个 1:4个
	 */
	private String name;	

	/**
	 * 小对勾图片url
	 */
	private String display;	

	
	private String config;


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


	public String getDisplay() {
		return display;
	}


	public void setDisplay(String display) {
		this.display = display;
	}


	public String getConfig() {
		return config;
	}


	public void setConfig(String config) {
		this.config = config;
	}


}
	