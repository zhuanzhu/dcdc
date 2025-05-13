package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author xia
 * @date 2019-05-27 15:07:40
 */
public class JdProductInnerIdVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long jdSkuId;
	/**
	 * 本地productid
	 */
	private Long innerProductId;
	/**
	 * 本地skuid
	 */
	private Long innerSkuId;
	/**
	 * 本地suid
	 */
	private Long innerSuId;
	/**
	 * 本地pu草稿id
	 */
	private Long innerProductUnitId;
	/**
	 * 本地puid
	 */
	private Long innerPuId;
	/**
	 * 本地pictureid
	 */
	private Long innerPictureId;
	/**
	 * 本地attvalueid
	 */
	private Long innerAttValueId;

	public Long getJdSkuId() {
		return jdSkuId;
	}

	/**
	 * 京东skuid
	 * @param jdSkuId 京东skuid
	 */
	public void setJdSkuId(Long jdSkuId) {
		this.jdSkuId = jdSkuId;
	}
	/**
	 * 本地productid
	 * @return 本地productid
	 */
	public Long getInnerProductId() {
		return innerProductId;
	}

	/**
	 * 本地productid
	 * @param innerProductId 本地productid
	 */
	public void setInnerProductId(Long innerProductId) {
		this.innerProductId = innerProductId;
	}	
	/**
	 * 本地skuid
	 * @return 本地skuid
	 */
	public Long getInnerSkuId() {
		return innerSkuId;
	}

	/**
	 * 本地skuid
	 * @param innerSkuId 本地skuid
	 */
	public void setInnerSkuId(Long innerSkuId) {
		this.innerSkuId = innerSkuId;
	}	
	/**
	 * 本地suid
	 * @return 本地suid
	 */
	public Long getInnerSuId() {
		return innerSuId;
	}

	/**
	 * 本地suid
	 * @param innerSuId 本地suid
	 */
	public void setInnerSuId(Long innerSuId) {
		this.innerSuId = innerSuId;
	}	
	/**
	 * 本地pu草稿id
	 * @return 本地pu草稿id
	 */
	public Long getInnerProductUnitId() {
		return innerProductUnitId;
	}

	/**
	 * 本地pu草稿id
	 * @param innerProductUnitId 本地pu草稿id
	 */
	public void setInnerProductUnitId(Long innerProductUnitId) {
		this.innerProductUnitId = innerProductUnitId;
	}	
	/**
	 * 本地puid
	 * @return 本地puid
	 */
	public Long getInnerPuId() {
		return innerPuId;
	}

	/**
	 * 本地puid
	 * @param innerPuId 本地puid
	 */
	public void setInnerPuId(Long innerPuId) {
		this.innerPuId = innerPuId;
	}	
	/**
	 * 本地pictureid
	 * @return 本地pictureid
	 */
	public Long getInnerPictureId() {
		return innerPictureId;
	}

	/**
	 * 本地pictureid
	 * @param innerPictureId 本地pictureid
	 */
	public void setInnerPictureId(Long innerPictureId) {
		this.innerPictureId = innerPictureId;
	}	
	/**
	 * 本地attvalueid
	 * @return 本地attvalueid
	 */
	public Long getInnerAttValueId() {
		return innerAttValueId;
	}

	/**
	 * 本地attvalueid
	 * @param innerAttValueId 本地attvalueid
	 */
	public void setInnerAttValueId(Long innerAttValueId) {
		this.innerAttValueId = innerAttValueId;
	}	
}
	