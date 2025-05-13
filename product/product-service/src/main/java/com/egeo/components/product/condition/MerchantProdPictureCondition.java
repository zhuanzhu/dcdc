package com.egeo.components.product.condition;

import com.egeo.components.product.po.MerchantProdPicturePO;

/**
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProdPictureCondition extends MerchantProdPicturePO {
	private static final long serialVersionUID = 1L;
	/**
	 * 图片路径
	 */
	private String pictureUrl;
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}
	