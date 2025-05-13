package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MerchantProdetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2945508915172634295L;
	private Long id;
	/**
	 * 商品名称
	 */
	private String name;
    /**
     * 市场价格
     */
    private BigDecimal marketPrice;
    /**
	 * 销售价格
	 */
	private BigDecimal salePrice;
    /**
     * 运费说明
     */
    private String freightExplain;

    /**
     * 发货说明
     */
    private String shipmentsExplain;
    
    /**
     * 商品描述信息
     */
    private String content;
    
    /**
     * 销售数量
     */
    private Long salesVolume;
    
    /**
	 * 属性id（用来确认那个属性显示图片）
	 */
	private Long radio;
	/**
	 * 商品总库存数量
	 */
	private Long stock;
    /**
     * 产品属性属性值
     */
    private List<AttNameValueAPP> list;
    /**
     * 轮播图路径
     */
    private List<String> pictureList;
    
    private String pictureUrl;
    
    private Long platformId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getFreightExplain() {
		return freightExplain;
	}

	public void setFreightExplain(String freightExplain) {
		this.freightExplain = freightExplain;
	}

	public String getShipmentsExplain() {
		return shipmentsExplain;
	}

	public void setShipmentsExplain(String shipmentsExplain) {
		this.shipmentsExplain = shipmentsExplain;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}

	public List<AttNameValueAPP> getList() {
		return list;
	}

	public void setList(List<AttNameValueAPP> list) {
		this.list = list;
	}

	public List<String> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRadio() {
		return radio;
	}

	public void setRadio(Long radio) {
		this.radio = radio;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	
	
	

    
    
}
