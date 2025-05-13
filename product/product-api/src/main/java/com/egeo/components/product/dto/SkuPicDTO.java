package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * sku于对应图片的关联类
 * @author GRACIA
 *
 */
public class SkuPicDTO implements Serializable {

	private static final long serialVersionUID = -151286114516758933L;
	private Long skuId;
	private Long productId;
	private Long radio;
	private Long mpId;
	private Long mpAttNameId;
	private Long mpAttValueId;
	private String pictureUrl;
	
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getRadio() {
		return radio;
	}
	public void setRadio(Long radio) {
		this.radio = radio;
	}
	public Long getMpId() {
		return mpId;
	}
	public void setMpId(Long mpId) {
		this.mpId = mpId;
	}
	public Long getMpAttNameId() {
		return mpAttNameId;
	}
	public void setMpAttNameId(Long mpAttNameId) {
		this.mpAttNameId = mpAttNameId;
	}
	public Long getMpAttValueId() {
		return mpAttValueId;
	}
	public void setMpAttValueId(Long mpAttValueId) {
		this.mpAttValueId = mpAttValueId;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}
