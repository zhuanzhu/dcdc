package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:08
 */
public class PictureVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */

	private String name;		 
	/**
	 * 
	 */

	private String url;		 
	/**
	 * 平台id
	 */
	private String pictureUrl;

	private Long platformId;		 
	/**
	 * 排序
	 */

	private Integer sortValue;		 
	/**
	 * 
	 */

	private Integer type;		 
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 

	private List<PictureVO> styleImage;
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
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url 
	 */
	public void setUrl(String url) {
		this.url = url;
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

        public String getPictureUrl() {
            return pictureUrl;
        }
    
        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

	/**
	 * 
	 * @return 
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 
	 * @param type 
	 */
	public void setType(Integer type) {
		this.type = type;
	}	

        public List<PictureVO> getStyleImage() {
            return styleImage;
}

        public void setStyleImage(List<PictureVO> styleImage) {
            this.styleImage = styleImage;
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
}
	