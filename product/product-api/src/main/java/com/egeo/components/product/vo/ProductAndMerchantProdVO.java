package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductAndMerchantProdVO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1290219354823083604L;

    //商品id
    private Long merchantProductId;
    
    //商品名称
    private String merchantProductName;
    
    /**
     * 销售价格(去所有商品的最低价格)
     */
    private BigDecimal salePrice;
    
    /**
     * 真实库存(所有商品的真实库存减去冻结库存的价格之和)
     */
    private Long Stock;
    
    /**
     * 累计销售数量
     */
    private Long salesVolume;
    
    /**
     * 已售基数
     */
    private Long soldBase;
    
    /**
     * 创建时间-应用操作时间
     */
    private Date createTime;
    
    // 创建开始时间
    private String beginTime;

    // 创建结束时间
    private String finishTime;
    
    /**
     * 商品状态
     */
    private Integer status;
    
    private Long platformId;

    public Long getMerchantProductId() {
        return merchantProductId;
    }

    public void setMerchantProductId(Long merchantProductId) {
        this.merchantProductId = merchantProductId;
    }

    public String getMerchantProductName() {
        return merchantProductName;
    }

    public void setMerchantProductName(String merchantProductName) {
        this.merchantProductName = merchantProductName;
    }

    public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Long getStock() {
        return Stock;
    }

    public void setStock(Long stock) {
        Stock = stock;
    }

    public Long getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Long salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Long getSoldBase() {
        return soldBase;
    }

    public void setSoldBase(Long soldBase) {
        this.soldBase = soldBase;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	
    
}
