package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.dto.CategoryAttNameValuse;
import com.egeo.components.product.dto.PictureDTO;
/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:08
 */
public class ProductVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * spu编号
	 */
	private String productSerialNumber;	
	
	/**
	 * 产品类目
	 */
	private String productCategory;	
	/**
	 * 是否可用:默认0否;1是
	 */
	private Integer isAvailable;
	/**
	 * 
	 */

	private Long brandId;		 
	/**
	 * 
	 */

	private Long categoryTreeNodeId;
	
	private List<Long> categoryPId;
	
	private List<String> categoryName;
	/**
	 * 
	 */

	private String name;
	/**
	 * 
	 */

	private String chineseName;
	/**
	 * 平台id
	 */

	private Long platformId;
	/**
	 * 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 */

	private Integer status;
	/**
	 * 
	 */

	private String productDetails;		 


		private Long categoryId;
		/**
		 * 产品不通过原因
		 */
		private String cause;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;	
	
	// 创建开始时间
    private String beginTime;

    // 创建结束时间
    private String finishTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 

	private List<PictureDTO> pictureList;
	
	private String content;
	
	private List<AttNameValueVO> list;
	
	private String url;
	/**
	 * 商品模版id
	 */
	private Long commodityTemplateId;
	/**
	 * 基本属性集合
	 */
	private List<CategoryAttNameValuse> categoryAttNameValuseList;
	
	private List<AttName> apecificationList;
	/**
	 * 参数属性集合
	 */
	private List<CategoryAttNameValuse> parameterCategoryAttNameValuse;
	/**
	 * 规格属性显示id
	 */
	private Long showProductAttNameId;

	/**
	 * 预警开始天数
	 */
	private Integer precautiousStart;
	/**
	 * 预警结束天数
	 */
	private Integer precautiousEnd;
	
	private Long enterpriseId;
	private Long supplierId;

	private String referlink;
	
	private String extend;
	private String priceDetail;
	
	
	
	public String getReferlink() {
		return referlink;
	}

	public void setReferlink(String referlink) {
		this.referlink = referlink;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(String priceDetail) {
		this.priceDetail = priceDetail;
	}
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * 预警线id集合
	 */
	private List<Long> precautiousLineIds;

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getPrecautiousLineIdStr() {
		return precautiousLineIdStr;
	}

	public void setPrecautiousLineIdStr(String precautiousLineIdStr) {
		this.precautiousLineIdStr = precautiousLineIdStr;
	}

	/**
	 * 预警线id串,6,7,8是预警线属性配置id,页面传值可以覆盖
	 */
	private String precautiousLineIdStr = "6,7,8";



	public List<Long> getPrecautiousLineIds() {
		return precautiousLineIds;
	}

	public void setPrecautiousLineIds(List<Long> precautiousLineIds) {
		this.precautiousLineIds = precautiousLineIds;
	}

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
	 * 
	 * @return 
	 */
	public Long getBrandId() {
		return brandId;
	}

	/**
	 * 
	 * @param brandId 
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	/**
	 * 
	 * @param categoryTreeNodeId 
	 */
	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * 
	 * @param chineseName 
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}	
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}	
	/**
	 * 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 * @return 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 * @param status 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}	

	
	        public Long getCategoryId() {
	        return categoryId;
	    }
	
	    public void setCategoryId(Long categoryId) {
	        this.categoryId = categoryId;
	    }
	/**
	 * 
	 * @return 
	 */
	public String getProductDetails() {
		return productDetails;
	}

	/**
	 * 
	 * @param productDetails 
	 */
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}	

		public String getCause() {
			return cause;
		}

		public void setCause(String cause) {
			this.cause = cause;
		}
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	public List<String> getCategoryName() {
			return categoryName;
	}

	public void setCategoryName(List<String> categoryName) {
		this.categoryName = categoryName;
	}

	public List<PictureDTO> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<PictureDTO> pictureList) {
		this.pictureList = pictureList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<AttNameValueVO> getList() {
		return list;
	}

	public void setList(List<AttNameValueVO> list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Long> getCategoryPId() {
		return categoryPId;
	}

	public void setCategoryPId(List<Long> categoryPId) {
		this.categoryPId = categoryPId;
	}

	public List<CategoryAttNameValuse> getCategoryAttNameValuseList() {
		return categoryAttNameValuseList;
	}

	public void setCategoryAttNameValuseList(List<CategoryAttNameValuse> categoryAttNameValuseList) {
		this.categoryAttNameValuseList = categoryAttNameValuseList;
	}

	public List<AttName> getApecificationList() {
		return apecificationList;
	}

	public void setApecificationList(List<AttName> apecificationList) {
		this.apecificationList = apecificationList;
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

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<CategoryAttNameValuse> getParameterCategoryAttNameValuse() {
		return parameterCategoryAttNameValuse;
	}

	public void setParameterCategoryAttNameValuse(List<CategoryAttNameValuse> parameterCategoryAttNameValuse) {
		this.parameterCategoryAttNameValuse = parameterCategoryAttNameValuse;
	}

	public Long getCommodityTemplateId() {
		return commodityTemplateId;
	}

	public void setCommodityTemplateId(Long commodityTemplateId) {
		this.commodityTemplateId = commodityTemplateId;
	}

	public Long getShowProductAttNameId() {
		return showProductAttNameId;
	}

	public void setShowProductAttNameId(Long showProductAttNameId) {
		this.showProductAttNameId = showProductAttNameId;
	}


	public Integer getPrecautiousStart() {
		return precautiousStart;
	}

	public void setPrecautiousStart(Integer precautiousStart) {
		this.precautiousStart = precautiousStart;
	}

	public Integer getPrecautiousEnd() {
		return precautiousEnd;
	}

	public void setPrecautiousEnd(Integer precautiousEnd) {
		this.precautiousEnd = precautiousEnd;
	}
}
	