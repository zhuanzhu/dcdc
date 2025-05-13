package com.egeo.components.product.condition;

import com.egeo.components.product.po.PicturePO;

/**
 * 
 * @author xiaping
 * @date 2017-07-17 11:21:23
 */
public class PictureCondition extends PicturePO {
	private static final long serialVersionUID = 1L;
	/**
	 * su草稿图片关系id
	 */
	private Long merchantProdPictureId;
	/**
	 * 商家图片关系id
	 */
	private Long merchantPictureId;
	public Long getMerchantProdPictureId() {
		return merchantProdPictureId;
	}
	public void setMerchantProdPictureId(Long merchantProdPictureId) {
		this.merchantProdPictureId = merchantProdPictureId;
	}
	public Long getMerchantPictureId() {
		return merchantPictureId;
	}
	public void setMerchantPictureId(Long merchantPictureId) {
		this.merchantPictureId = merchantPictureId;
	}
	

}
	