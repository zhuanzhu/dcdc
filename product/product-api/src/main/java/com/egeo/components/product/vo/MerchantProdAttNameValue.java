package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MerchantProdAttNameValue implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 78014377396057769L;
    
    private Long id;

    private List<AttNameValueVO> nameValues;
    
    /**
	 * sku图片
	 */
	private String skuPicUrl;
    
    /**
     * 销售价格
     */
    private BigDecimal salePrice;
    
    /**
     * 真实库存
     */
    private Long realStockNum;

    public List<AttNameValueVO> getNameValues() {
        return nameValues;
    }

    public void setNameValues(List<AttNameValueVO> nameValues) {
        this.nameValues = nameValues;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Long getRealStockNum() {
        return realStockNum;
    }

    public void setRealStockNum(Long realStockNum) {
        this.realStockNum = realStockNum;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuPicUrl() {
		return skuPicUrl;
	}

	public void setSkuPicUrl(String skuPicUrl) {
		this.skuPicUrl = skuPicUrl;
	}

}
