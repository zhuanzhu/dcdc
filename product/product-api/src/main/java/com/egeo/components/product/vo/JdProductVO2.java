package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author tan
 * @date 2019-03-26 10:43:54
 */
public class JdProductVO2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String picture;

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	private Date syncTime;
	private Date productCreateTime;

	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public Date getProductCreateTime() {
		return productCreateTime;
	}

	public void setProductCreateTime(Date productCreateTime) {
		this.productCreateTime = productCreateTime;
	}

	private Long id;
	/**
	 * 类目ID
	 */
	private Long categoryId;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品主图
	 */
	private String imagePath;
	/**
	 * 商品详情大字段
	 */
	private String introduction;
	/**
	 * 规格参数
	 */
	private String param;
	/**
	 * 商品最低起购量
	 */
	private Integer lowestBuy;
	/**
	 * spuId
	 */
	private Long spuId;
	/**
	 * spu名称
	 */
	private String spuName;
	/**
	 * 商品价格
	 */
	private String price;
	/**
	 * 市场价格
	 */
	private String marketPrice;
	/**
	 * 是否自营 0-否 1-是
	 */
	private Integer isSelf;
	/**
	 * 是否京东配送 0-否 1-是
	 */
	private Integer isJdLogistics;
	/**
	 * 是否可售 0-否 1-是
	 */
	private Integer saleState;
	/**
	 * 上下架状态 0-下架 1-上架
	 */
	private Integer state;
	/**
	 * 是否支持7 天无理由退货 0-否 1-是
	 */
	private Integer is7ToReturn;
	/**
	 * 无理由退货类型 0、3：不支持7天无理由退货；1、5、8或null：支持7天无理由退货；2：支持90天无理由退货；4、7：支持15天无理由退货；6：支持30天无理由退货；
	 */
	private Integer noReasonToReturn;
	/**
	 * 无理由退货文案类型 null：文案空；0：文案空；1：支持7天无理由退货；2：支持7天无理由退货（拆封后不支持）；3：支持7天无理由退货（激活后不支持）4：支持7天无理由退货（使用后不支持）；5：支持7天无理由退货（安装后不支持）；12：支持15天无理由退货；13：支持15天无理由退货（拆封后不支持）；14：支持15天无理由退货（激活后不支持）；15：支持15天无理由退货（使用后不支持）；16：支持15天无理由退货（安装后不支持）；22：支持30天无理由退货；23：支持30天无理由退货（安装后不支持）；24：支持30天无理由退货（拆封后不支持）；25：支持30天无理由退货（使用后不支持）；26：支持30天无理由退货（激活后不支持）；
	 */
	private Integer thwa;
	/**
	 * 规格json文本
	 */
	private String skuJson;

	private Date createTime;

	private Date updateTime;

	private Long enterpriseId;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	private Integer syncStatus;

	public Long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 类目ID
	 * @return 类目ID
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 类目ID
	 * @param categoryId 类目ID
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 商品名称
	 * @return 商品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名称
	 * @param name 商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 商品主图
	 * @return 商品主图
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * 商品主图
	 * @param imagePath 商品主图
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * 商品详情大字段
	 * @return 商品详情大字段
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 商品详情大字段
	 * @param introduction 商品详情大字段
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 规格参数
	 * @return 规格参数
	 */
	public String getParam() {
		return param;
	}

	/**
	 * 规格参数
	 * @param param 规格参数
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * 商品最低起购量
	 * @return 商品最低起购量
	 */
	public Integer getLowestBuy() {
		return lowestBuy;
	}

	/**
	 * 商品最低起购量
	 * @param lowestBuy 商品最低起购量
	 */
	public void setLowestBuy(Integer lowestBuy) {
		this.lowestBuy = lowestBuy;
	}
	/**
	 * spuId
	 * @return spuId
	 */
	public Long getSpuId() {
		return spuId;
	}

	/**
	 * spuId
	 * @param spuId spuId
	 */
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	/**
	 * spu名称
	 * @return spu名称
	 */
	public String getSpuName() {
		return spuName;
	}

	/**
	 * spu名称
	 * @param spuName spu名称
	 */
	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}
	/**
	 * 商品价格
	 * @return 商品价格
	 */
	public BigDecimal getPrice() {
		if(price==null) {
			return new BigDecimal(0);
		}
		return new BigDecimal(price);
	}

	/**
	 * 商品价格
	 * @param price 商品价格
	 */
	public void setPrice(BigDecimal pp) {
		if(pp==null) {
			this.price = null;
		}else {
			this.price = pp.toPlainString();
		}


	}
	/**
	 * 市场价格
	 * @return 市场价格
	 */
	public BigDecimal getMarketPrice() {
		if(marketPrice==null) {
			return new BigDecimal(0);
		}
		return new BigDecimal(marketPrice);
	}

	/**
	 * 市场价格
	 * @param marketPrice 市场价格
	 */
	public void setMarketPrice(BigDecimal pp) {
		if(pp==null) {
			this.marketPrice = null;
		}else {
			this.marketPrice = pp.toPlainString();
		}


	}
	/**
	 * 是否自营 0-否 1-是
	 * @return 是否自营 0-否 1-是
	 */
	public Integer getIsSelf() {
		return isSelf;
	}

	/**
	 * 是否自营 0-否 1-是
	 * @param isSelf 是否自营 0-否 1-是
	 */
	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}
	/**
	 * 是否京东配送 0-否 1-是
	 * @return 是否京东配送 0-否 1-是
	 */
	public Integer getIsJdLogistics() {
		return isJdLogistics;
	}

	/**
	 * 是否京东配送 0-否 1-是
	 * @param isJdLogistics 是否京东配送 0-否 1-是
	 */
	public void setIsJdLogistics(Integer isJdLogistics) {
		this.isJdLogistics = isJdLogistics;
	}
	/**
	 * 是否可售 0-否 1-是
	 * @return 是否可售 0-否 1-是
	 */
	public Integer getSaleState() {
		return saleState;
	}

	/**
	 * 是否可售 0-否 1-是
	 * @param saleState 是否可售 0-否 1-是
	 */
	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}
	/**
	 * 上下架状态 0-下架 1-上架
	 * @return 上下架状态 0-下架 1-上架
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 上下架状态 0-下架 1-上架
	 * @param state 上下架状态 0-下架 1-上架
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 是否支持7 天无理由退货 0-否 1-是
	 * @return 是否支持7 天无理由退货 0-否 1-是
	 */
	public Integer getIs7ToReturn() {
		return is7ToReturn;
	}

	/**
	 * 是否支持7 天无理由退货 0-否 1-是
	 * @param is7ToReturn 是否支持7 天无理由退货 0-否 1-是
	 */
	public void setIs7ToReturn(Integer is7ToReturn) {
		this.is7ToReturn = is7ToReturn;
	}
	/**
	 * 无理由退货类型 0、3：不支持7天无理由退货；1、5、8或null：支持7天无理由退货；2：支持90天无理由退货；4、7：支持15天无理由退货；6：支持30天无理由退货；
	 * @return 无理由退货类型 0、3：不支持7天无理由退货；1、5、8或null：支持7天无理由退货；2：支持90天无理由退货；4、7：支持15天无理由退货；6：支持30天无理由退货；
	 */
	public Integer getNoReasonToReturn() {
		return noReasonToReturn;
	}

	/**
	 * 无理由退货类型 0、3：不支持7天无理由退货；1、5、8或null：支持7天无理由退货；2：支持90天无理由退货；4、7：支持15天无理由退货；6：支持30天无理由退货；
	 * @param noReasonToReturn 无理由退货类型 0、3：不支持7天无理由退货；1、5、8或null：支持7天无理由退货；2：支持90天无理由退货；4、7：支持15天无理由退货；6：支持30天无理由退货；
	 */
	public void setNoReasonToReturn(Integer noReasonToReturn) {
		this.noReasonToReturn = noReasonToReturn;
	}
	/**
	 * 无理由退货文案类型 null：文案空；0：文案空；1：支持7天无理由退货；2：支持7天无理由退货（拆封后不支持）；3：支持7天无理由退货（激活后不支持）4：支持7天无理由退货（使用后不支持）；5：支持7天无理由退货（安装后不支持）；12：支持15天无理由退货；13：支持15天无理由退货（拆封后不支持）；14：支持15天无理由退货（激活后不支持）；15：支持15天无理由退货（使用后不支持）；16：支持15天无理由退货（安装后不支持）；22：支持30天无理由退货；23：支持30天无理由退货（安装后不支持）；24：支持30天无理由退货（拆封后不支持）；25：支持30天无理由退货（使用后不支持）；26：支持30天无理由退货（激活后不支持）；
	 * @return 无理由退货文案类型 null：文案空；0：文案空；1：支持7天无理由退货；2：支持7天无理由退货（拆封后不支持）；3：支持7天无理由退货（激活后不支持）4：支持7天无理由退货（使用后不支持）；5：支持7天无理由退货（安装后不支持）；12：支持15天无理由退货；13：支持15天无理由退货（拆封后不支持）；14：支持15天无理由退货（激活后不支持）；15：支持15天无理由退货（使用后不支持）；16：支持15天无理由退货（安装后不支持）；22：支持30天无理由退货；23：支持30天无理由退货（安装后不支持）；24：支持30天无理由退货（拆封后不支持）；25：支持30天无理由退货（使用后不支持）；26：支持30天无理由退货（激活后不支持）；
	 */
	public Integer getThwa() {
		return thwa;
	}

	/**
	 * 无理由退货文案类型 null：文案空；0：文案空；1：支持7天无理由退货；2：支持7天无理由退货（拆封后不支持）；3：支持7天无理由退货（激活后不支持）4：支持7天无理由退货（使用后不支持）；5：支持7天无理由退货（安装后不支持）；12：支持15天无理由退货；13：支持15天无理由退货（拆封后不支持）；14：支持15天无理由退货（激活后不支持）；15：支持15天无理由退货（使用后不支持）；16：支持15天无理由退货（安装后不支持）；22：支持30天无理由退货；23：支持30天无理由退货（安装后不支持）；24：支持30天无理由退货（拆封后不支持）；25：支持30天无理由退货（使用后不支持）；26：支持30天无理由退货（激活后不支持）；
	 * @param thwa 无理由退货文案类型 null：文案空；0：文案空；1：支持7天无理由退货；2：支持7天无理由退货（拆封后不支持）；3：支持7天无理由退货（激活后不支持）4：支持7天无理由退货（使用后不支持）；5：支持7天无理由退货（安装后不支持）；12：支持15天无理由退货；13：支持15天无理由退货（拆封后不支持）；14：支持15天无理由退货（激活后不支持）；15：支持15天无理由退货（使用后不支持）；16：支持15天无理由退货（安装后不支持）；22：支持30天无理由退货；23：支持30天无理由退货（安装后不支持）；24：支持30天无理由退货（拆封后不支持）；25：支持30天无理由退货（使用后不支持）；26：支持30天无理由退货（激活后不支持）；
	 */
	public void setThwa(Integer thwa) {
		this.thwa = thwa;
	}
	/**
	 * 规格json文本
	 * @return 规格json文本
	 */
	public String getSkuJson() {
		return skuJson;
	}

	/**
	 * 规格json文本
	 * @param skuJson 规格json文本
	 */
	public void setSkuJson(String skuJson) {
		this.skuJson = skuJson;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}
