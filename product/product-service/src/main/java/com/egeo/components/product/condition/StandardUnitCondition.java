package com.egeo.components.product.condition;

import com.egeo.components.product.dto.CakeProductDTO;
import com.egeo.components.product.dto.CakeProductDetailDTO;
import com.egeo.components.product.dto.CakeProductDetailProductsDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;

import java.math.BigDecimal;

/**
 *
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class StandardUnitCondition extends StandardUnitPO {
	private static final long serialVersionUID = 1L;
	private Integer source=1;
	//总店id
	private Long storeId;

	private String channelProductId;


	public void fromJdProduct(JdProductDTO jd) {
		// TODO Auto-generated constructor stub
		this.saleWay = 1;
		if(EmptyUtil.isNotEmpty(jd.getImagePath())){
			this.pictureUrl = jd.getImagePath().indexOf("http")>=0?jd.getImagePath():("https://img13.360buyimg.com/n1/"+jd.getImagePath());
		}
		this.content = jd.getName();
		this.source =3;
		this.commodityTemplateId = 2l;
		super.setId(jd.getId());
		super.setBuyType(1);
		super.setName(jd.getName());
		super.setIsVisible(0);
		super.setSupplierPrice(jd.getPrice());
		super.setSalePrice(jd.getSalePrice());
		super.setStandardProductUnitId(jd.getSpuId());
		super.setMarketPrice(jd.getMarketPrice());
		super.setCustomProfit(jd.getCustomProfit());
		super.setStatus(3);
		//super.setStatus(4);
	}

	public void fromCakeProduct(CakeProductDTO dto) {
		this.saleWay = 1;
		this.pictureUrl = dto.getImage_path();
		this.content = dto.getTitle();
		this.source =4;
		this.commodityTemplateId = 2L;
		this.channelProductId = dto.getId();
		super.setId(Long.valueOf(dto.getId()));
		super.setBuyType(1);
		super.setName(dto.getTitle());
		super.setIsVisible(0);
		super.setSalePrice(StringUtils.isNotBlank(dto.getPrice())?new BigDecimal(dto.getPrice()):null);
		super.setStandardProductUnitId(Long.valueOf(dto.getId()));
		super.setMarketPrice(StringUtils.isNotBlank(dto.getMarket_price())?new BigDecimal(dto.getMarket_price()):null);
		super.setStatus(3);
	}

	public void fromCakeProduct(CakeProductDetailProductsDTO dto) {
		this.saleWay = 1;
		this.pictureUrl = dto.getImage_path();
		this.content = dto.getTitle();
		this.source =4;
		this.commodityTemplateId = 2L;
		this.channelProductId = dto.getId();
		super.setId(Long.valueOf(dto.getId()));
		super.setBuyType(1);
		super.setName(dto.getTitle());
		super.setIsVisible(0);
		super.setSalePrice(StringUtils.isNotBlank(dto.getPrice())?new BigDecimal(dto.getPrice()):null);
		super.setStandardProductUnitId(Long.valueOf(dto.getId()));
		super.setMarketPrice(StringUtils.isNotBlank(dto.getMarket_price())?new BigDecimal(dto.getMarket_price()):null);
		super.setStatus(3);
	}

	public void fromChannelProductAndSku(ChannelProductAndSkuCondition dto,Integer source) {
		this.saleWay = 1;
		this.pictureUrl = StringUtils.isNotEmpty(dto.getSkuPicUrl())?dto.getSkuPicUrl():dto.getImagePath();
		this.content = StringUtils.isNotEmpty(dto.getSkuProductName())?dto.getSkuProductName():dto.getTitle();
		this.source =source;
		this.commodityTemplateId = 2L;
		this.channelProductId = dto.getProductId();
		super.setId(Long.valueOf(dto.getId()));
		super.setBuyType(1);
		super.setName(StringUtils.isNotEmpty(dto.getSkuProductName())?dto.getSkuProductName():dto.getTitle());
		super.setIsVisible(dto.getIsAvailable());
		super.setSalePrice(dto.getPrice() !=null ?dto.getPrice():dto.getSkuPrice());
		super.setStandardProductUnitId(Long.valueOf(dto.getStandardProductUnitId()));
		super.setMarketPrice(dto.getMarketPrice() !=null ?dto.getMarketPrice():dto.getSkuMarketPrice());
		super.setStatus(3);
	}


	public Integer getSource() {
		return source;
	}


	public void setSource(Integer source) {
		this.source = source;
	}


	@Override
	public Long getStoreId() {
		return storeId;
	}

	@Override
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	/**
	 * 商家名称
	 */
	private String merchantName;
	/**
	 * 商品封面图
	 */
	private String pictureUrl;
	/**
	 * 销售数量
	 */
	private Long salesVolume;
	/**
	 * 商品详情
	 */
	private String content;
	private Integer saleWay;

	@Override
	public Integer getSaleWay() {
		return saleWay;
	}

	@Override
	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	/**
	 * su商品详情dfs_url
	 */
	private String contentUrl;
	/**
	 * 商品模版id
	 */
	private Long commodityTemplateId;
	// 关联su名称
	private String relevanceSuName;
	/**
	 * 关联su商品模版id
	 */
	private String relevanceSuCommodityTemplateId;

	public String getRelevanceSuCommodityTemplateId() {
		return relevanceSuCommodityTemplateId;
	}

	public void setRelevanceSuCommodityTemplateId(String relevanceSuCommodityTemplateId) {
		this.relevanceSuCommodityTemplateId = relevanceSuCommodityTemplateId;
	}

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

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCommodityTemplateId() {
		return commodityTemplateId;
	}

	public void setCommodityTemplateId(Long commodityTemplateId) {
		this.commodityTemplateId = commodityTemplateId;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getChannelProductId() {
		return channelProductId;
	}

	public void setChannelProductId(String channelProductId) {
		this.channelProductId = channelProductId;
	}

}
