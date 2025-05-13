package com.egeo.components.product.dto;

import com.egeo.utils.StringUtils;

public class JdSearchHitResultDTO {
	private String  brand	;  
	private String  imageUrl;  
	private String  wareName;  
	private String  wareId	;  
	private String  warePId	;  
	private String  brandId	;  
	private String  cid1	;  
	private String  cid2	;  
	private String  catId	;  
	private String  wstate	;  
	private String  wyn	    ;  
	private String  cid1Name;
	private String  cid2Name;
	private String  catName	;  
	private String  synonyms;
	/******业务逻辑******/
	public boolean productActive() {
		if(StringUtils.isBlank(wyn)) {
			return false;
		}
		return wyn.equalsIgnoreCase("1");
	}
	public boolean productActiveCompare(boolean active) {
		boolean myActive = productActive();
		return myActive==active;
	}
	public boolean state() {
		if(StringUtils.isBlank(wstate)) {
			return false;
		}
		return wstate.equalsIgnoreCase("1");
	}
	public boolean stateCompare(boolean state) {
		boolean myState = productActive();
		return myState==state;
	}
	public boolean hasProductId() {
		return !StringUtils.isBlank(wareId);
	}
	/************/
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getWareId() {
		return wareId;
	}
	public Long getWareIdLong() {
		return (wareId==null||wareId.length()==0)?null:Long.valueOf(wareId);
	}
	public void setWareId(String wareId) {
		this.wareId = wareId;
	}
	public String getWarePId() {
		return warePId;
	}
	public Long getWarePIdLong() {
		return (warePId==null||warePId.length()==0)?null:Long.valueOf(warePId);
	}
	public void setWarePId(String warePId) {
		this.warePId = warePId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getCid1() {
		return cid1;
	}
	public void setCid1(String cid1) {
		this.cid1 = cid1;
	}
	public String getCid2() {
		return cid2;
	}
	public void setCid2(String cid2) {
		this.cid2 = cid2;
	}
	public String getCatId() {
		return catId;
	}
	public Long getCatIdLong() {
		return (catId==null||catId.length()==0)?null:Long.valueOf(catId);
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getWstate() {
		return wstate;
	}
	public Integer getWstateInt() {
		return (wstate==null||wstate.length()==0)?null:Integer.valueOf(wstate);
	}
	public void setWstate(String wstate) {
		this.wstate = wstate;
	}
	public String getWyn() {
		return wyn;
	}
	public void setWyn(String wyn) {
		this.wyn = wyn;
	}
	public String getCid1Name() {
		return cid1Name;
	}
	public void setCid1Name(String cid1Name) {
		this.cid1Name = cid1Name;
	}
	public String getCid2Name() {
		return cid2Name;
	}
	public void setCid2Name(String cid2Name) {
		this.cid2Name = cid2Name;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}


}
