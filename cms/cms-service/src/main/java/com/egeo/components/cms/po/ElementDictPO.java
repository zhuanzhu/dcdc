package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-09 09:55:34
 */
public class ElementDictPO {


	private Long id;

	/**
	 * 组件字典示例图片
	 */
	private String imgUrl;	

	/**
	 * 字典名称
	 */
	private String name;	

	/**
	 * 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 */
	private Integer configType;	

	/**
	 * 安卓版本
	 */
	private Integer clientVersionA;	

	/**
	 * ios版本编号
	 */
	private Integer clientVersionI;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 修改时间
	 */
	private Date updateTime;	
	
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	 * 组件字典示例图片
	 * @return 组件字典示例图片
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 组件字典示例图片
	 * @param imgUrl 组件字典示例图片
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * 字典名称
	 * @return 字典名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 字典名称
	 * @param name 字典名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 * @return 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 */
	public Integer getConfigType() {
		return configType;
	}

	/**
	 * 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 * @param configType 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 */
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	/**
	 * 安卓版本
	 * @return 安卓版本
	 */
	public Integer getClientVersionA() {
		return clientVersionA;
	}

	/**
	 * 安卓版本
	 * @param clientVersionA 安卓版本
	 */
	public void setClientVersionA(Integer clientVersionA) {
		this.clientVersionA = clientVersionA;
	}

	/**
	 * ios版本编号
	 * @return ios版本编号
	 */
	public Integer getClientVersionI() {
		return clientVersionI;
	}

	/**
	 * ios版本编号
	 * @param clientVersionI ios版本编号
	 */
	public void setClientVersionI(Integer clientVersionI) {
		this.clientVersionI = clientVersionI;
	}

	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改时间
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	