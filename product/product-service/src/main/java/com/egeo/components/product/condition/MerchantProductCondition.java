package com.egeo.components.product.condition;

import com.egeo.components.product.po.MerchantProductPO;

/**
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProductCondition extends MerchantProductPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 商家名称
	 */
	private String merchantName;
	/**
	 * su草稿详情
	 */
	private String content;	
	/**
	 * 关联su商品名称
	 */
	private String relevanceSuName;
	
	public String getRelevanceSuName() {
		return relevanceSuName;
	}
	public void setRelevanceSuName(String relevanceSuName) {
		this.relevanceSuName = relevanceSuName;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	} 
	
	

}
	