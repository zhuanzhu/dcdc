package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationSuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 商品组合id
	 */
	private Long standardUnitCombinationId;

	private String merchantProductSerialNumber;

	private String imgUrl;
	/**
	 * 商品id
	 */
	private Long standardUnitId;
	/**
	 * price
	 */
	private BigDecimal standardUnitSalePrice;
	private BigDecimal standardUnitDemoPrice;
	private BigDecimal standardUnitCompetingPrice;
	/**
	 * 供货价
	 */
	private BigDecimal standardUnitSupplierPrice;



	/**
	 * 排序
	 */
	private Integer sortValue;
	/**
	 * su商品名称
	 */
	private String StandardUnitName;

	private Integer sellState;
	private Date checkTime;
	private String profit;
	private Integer source;
    private Long supplierId;
	private String standardUnitCombinationName;
	private Long skuId;

	/**
	 * 仅排序价格
	 */
	private BigDecimal sortPrice;
	/**
	 * 仅排序销售数量
	 */
	private Integer sortSalesNum;

	/**
	 * 仅商品关键字/sku名称查询
	 */
	private String  thirdSkuName;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 商品组合id
	 * @return 商品组合id
	 */
	public Long getStandardUnitCombinationId() {
		return standardUnitCombinationId;
	}

	/**
	 * 商品组合id
	 * @param standardUnitCombinationId 商品组合id
	 */
	public void setStandardUnitCombinationId(Long standardUnitCombinationId) {
		this.standardUnitCombinationId = standardUnitCombinationId;
	}
	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * 商品id
	 * @param standardUnitId 商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}
	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	public String getStandardUnitName() {
		return StandardUnitName;
	}

	public void setStandardUnitName(String standardUnitName) {
		StandardUnitName = standardUnitName;
	}

	public BigDecimal getStandardUnitSalePrice() {
		return standardUnitSalePrice;
	}

	public void setStandardUnitSalePrice(BigDecimal standardUnitSalePrice) {
		this.standardUnitSalePrice = standardUnitSalePrice;
	}

	public BigDecimal getStandardUnitDemoPrice() {
		return standardUnitDemoPrice;
	}

	public void setStandardUnitDemoPrice(BigDecimal standardUnitDemoPrice) {
		this.standardUnitDemoPrice = standardUnitDemoPrice;
	}

	public BigDecimal getStandardUnitCompetingPrice() {
		return standardUnitCompetingPrice;
	}

	public void setStandardUnitCompetingPrice(BigDecimal standardUnitCompetingPrice) {
		this.standardUnitCompetingPrice = standardUnitCompetingPrice;
	}

	public Integer getSellState() {
		return sellState;
	}

	public void setSellState(Integer sellState) {
		this.sellState = sellState;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public BigDecimal getStandardUnitSupplierPrice() {
		return standardUnitSupplierPrice;
	}

	public void setStandardUnitSupplierPrice(BigDecimal standardUnitSupplierPrice) {
		this.standardUnitSupplierPrice = standardUnitSupplierPrice;
	}

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getStandardUnitCombinationName() {
		return standardUnitCombinationName;
	}

	public void setStandardUnitCombinationName(String standardUnitCombinationName) {
		this.standardUnitCombinationName = standardUnitCombinationName;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public BigDecimal getSortPrice() {
		return sortPrice;
	}

	public void setSortPrice(BigDecimal sortPrice) {
		this.sortPrice = sortPrice;
	}

	public Integer getSortSalesNum() {
		return sortSalesNum;
	}

	public void setSortSalesNum(Integer sortSalesNum) {
		this.sortSalesNum = sortSalesNum;
	}

	public String getThirdSkuName() {
		return thirdSkuName;
	}

	public void setThirdSkuName(String thirdSkuName) {
		this.thirdSkuName = thirdSkuName;
	}
}
