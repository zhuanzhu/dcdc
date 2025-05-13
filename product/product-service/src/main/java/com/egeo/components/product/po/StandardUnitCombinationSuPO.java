package com.egeo.components.product.po;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationSuPO {


	private Long id;

	/**
	 * 商品组合id
	 */
	private Long standardUnitCombinationId;

	/**
	 * 商品id
	 */
	private Long standardUnitId;

	/**
	 * 排序
	 */
	private Integer sortValue;
	private Integer source;
	private String snapshot;

	/**
	 * 在商品级别下挂sku来确定某一个sku
	 */
	private String thirdSkuId;

	private Integer sellState;
	private Date checkTime;
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

	private List<Long> standardUnitCombinationIds;

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
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

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getThirdSkuId() {
		return thirdSkuId;
	}

	public void setThirdSkuId(String thirdSkuId) {
		this.thirdSkuId = thirdSkuId;
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

	public List<Long> getStandardUnitCombinationIds() {
		return standardUnitCombinationIds;
	}

	public void setStandardUnitCombinationIds(List<Long> standardUnitCombinationIds) {
		this.standardUnitCombinationIds = standardUnitCombinationIds;
	}
}
