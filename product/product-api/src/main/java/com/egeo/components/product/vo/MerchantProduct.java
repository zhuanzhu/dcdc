package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;

public class MerchantProduct implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8287091099486334757L;
    
    private Long id;
    
    private Long productId;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;
    
    /**
	 * 销售价格
	 */
	private BigDecimal salePrice;
    private Long soldBase;
    
    /**
     * 具体的商品
     */
    private List<MerchantProdAttNameValue> list;
    
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
    
    private List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdList;
    /**
     * 商品属性和属性值集合
     */
    private MerchantProdAttName merchantProdAttName;

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getSoldBase() {
        return soldBase;
    }

    public void setSoldBase(Long soldBase) {
        this.soldBase = soldBase;
    }
    
    public List<MerchantProdAttNameValue> getList() {
		return list;
	}

	public void setList(List<MerchantProdAttNameValue> list) {
		this.list = list;
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

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<SellPlatformMerchantProdDTO> getSellPlatformMerchantProdList() {
		return sellPlatformMerchantProdList;
	}

	public void setSellPlatformMerchantProdList(List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdList) {
		this.sellPlatformMerchantProdList = sellPlatformMerchantProdList;
	}

	public MerchantProdAttName getMerchantProdAttName() {
		return merchantProdAttName;
	}

	public void setMerchantProdAttName(MerchantProdAttName merchantProdAttName) {
		this.merchantProdAttName = merchantProdAttName;
	}
	
}
