package com.egeo.components.product.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egeo.utils.StringUtils;

public class JDProductSearchDTO {
	private String keyword;
	private String catId;
	private Integer pageIndex;
	private Integer pageSize;
	private Integer priceMin;
	private Integer priceMax;
	private List<String> brands;
	private String cid1;
	private String cid2;
	private String sortType;
	private Boolean priceCol;
	private Boolean extAttrCol;
	private Boolean mergeSku;

	private Map<String,Object> checkResultMap;

	private Map<String,Object> catCheckResultMap;

	private Long enterpriseId;

	public Map<String, String> param(String token){
		Map<String, String> param = new HashMap<>();
		param.put("token", token);
		if(StringUtils.isNotBlank(keyword)) {
			param.put("keyword", keyword);
		}
		if(StringUtils.isNotBlank(catId)) {
			param.put("catId", catId);
		}
		if(pageIndex!=null && pageIndex>0) {
			param.put("pageIndex", String.valueOf(pageIndex));
		}
		if(pageSize!=null && pageSize>0) {
			param.put("pageSize", String.valueOf(pageSize));
		}
		if(priceMin!=null && priceMin>0) {
			param.put("min", String.valueOf(priceMin));
		}
		if(priceMax!=null && priceMax>0) {
			param.put("max", String.valueOf(priceMax));
		}
		if(brands!=null && brands.size()>0 && brands.get(0)!=null ) {
			param.put("brands", String.join(",",brands));
		}
		if(StringUtils.isNotBlank(cid1)) {
			param.put("cid1", cid1);
		}
		if(StringUtils.isNotBlank(cid2)) {
			param.put("cid2", cid2);
		}
		if(StringUtils.isNotBlank(SortTypeEnum.typeValid(sortType))) {
			param.put("sortType", sortType);
		}
		if(priceCol!=null && priceCol) {
			param.put("priceCol", "yes");
		}
		if(extAttrCol!=null && extAttrCol) {
			param.put("extAttrCol", "yes");
		}
		if(mergeSku!=null && mergeSku) {
			param.put("mergeSku", "yes");
		}
		return param;
	}
	public enum SortTypeEnum {

		sale_desc("sale_desc","销量降序"),
		price_asc("price_asc","价格升序"),
		price_desc("price_desc","价格降序"),
		winsdate_desc("winsdate_desc","上架时间降序"),
		sort_totalsales15_desc("sort_totalsales15_desc","按销量排序_15天销售额"),
		sort_days_15_qtty_desc("sort_days_15_qtty_desc","按15日销量排序"),
		sort_days_30_qtty_desc("sort_days_30_qtty_desc","按30日销量排序"),
		sort_days_15_gmv_desc("sort_days_15_gmv_desc","按15日销售额排序"),
		sort_days_30_gmv_desc("sort_days_30_gmv_desc","按30日销售额排序");
		String type;
		String comment;

	    public String getType() {
			return type;
		}

		public void setStatus(String type) {
			this.type = type;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		SortTypeEnum(String type,String comment) {
	        this.type = type;
	        this.comment = comment;
	    }

		/**
		 * comment
		 * @param status
		 * @return
		 */
		public static String comment(String type){
			for(SortTypeEnum o:SortTypeEnum.values()){
				if(o.type.equalsIgnoreCase(type)){
					return o.getComment();
				}
			}
			return "";
		}/**
		 * type
		 * @param status
		 * @return
		 */
		public static String typeValid(String type){
			for(SortTypeEnum o:SortTypeEnum.values()){
				if(o.type.equalsIgnoreCase(type)){
					return type;
				}
			}
			return null;
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Integer priceMin) {
		this.priceMin = priceMin;
	}

	public Integer getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Integer priceMax) {
		this.priceMax = priceMax;
	}

	public List<String> getBrands() {
		return brands;
	}

	public void setBrands(List<String> brands) {
		this.brands = brands;
	}
	public void addBrands(String brand) {
		if(this.brands ==null) {
			this.brands = new ArrayList<String>();
		}
		this.brands.add(brand);
	}

	public String getCid1() {
		return cid1;
	}

	public void setCid1(String cid1) {
		this.cid1 = cid1;
	}

	public String getCid2() {
		return cid2;
	}

	public void setCid2(String cid2) {
		this.cid2 = cid2;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Boolean getPriceCol() {
		return priceCol;
	}

	public void setPriceCol(Boolean priceCol) {
		this.priceCol = priceCol;
	}

	public Boolean getExtAttrCol() {
		return extAttrCol;
	}

	public void setExtAttrCol(Boolean extAttrCol) {
		this.extAttrCol = extAttrCol;
	}

	public Boolean getMergeSku() {
		return mergeSku;
	}

	public void setMergeSku(Boolean mergeSku) {
		this.mergeSku = mergeSku;
	}

	public Map<String, Object> getCheckResultMap() {
		return checkResultMap;
	}

	public void setCheckResultMap(Map<String, Object> checkResultMap) {
		this.checkResultMap = checkResultMap;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Map<String, Object> getCatCheckResultMap() {
		return catCheckResultMap;
	}

	public void setCatCheckResultMap(Map<String, Object> catCheckResultMap) {
		this.catCheckResultMap = catCheckResultMap;
	}
}
