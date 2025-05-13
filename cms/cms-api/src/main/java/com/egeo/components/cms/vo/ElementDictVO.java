package com.egeo.components.cms.vo;

/**
 * 组件字典VO
 * @author graci
 *
 */
public class ElementDictVO {

	private Long dictId;
	
	private String imgUrl;
	
	private String dictName;
	
	private String clientVersionA;
	
	private String clientVersionI;
	
	private Integer configType;

	public Integer getConfigType() {
		return configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getClientVersionA() {
		return clientVersionA;
	}

	public void setClientVersionA(String clientVersionA) {
		this.clientVersionA = clientVersionA;
	}

	public String getClientVersionI() {
		return clientVersionI;
	}

	public void setClientVersionI(String clientVersionI) {
		this.clientVersionI = clientVersionI;
	}
	
	
}
