package com.egeo.components.cms.po;

import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-09 09:55:33
 */
public class ElementPO {

	private Long id;

	/**
	 * 组件名
	 */
	private String name;

	/**
	 * 类型 0:商城类 1:应用类 2:通用类
	 */
	private Integer type;

	/**
	 * 安卓版本编号
	 */
	private Integer clientVersionA;

	/**
	 * ios版本编号
	 */
	private Integer clientVersionI;

	/**
	 * 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner
	 * 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列
	 * 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner
	 * 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题
	 * 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 */
	private Integer configType;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private Long templateId;
	
	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 组件名
	 * 
	 * @return 组件名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 组件名
	 * 
	 * @param name
	 *            组件名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 类型 0:商城类 1:应用类 2:通用类
	 * 
	 * @return 类型 0:商城类 1:应用类 2:通用类
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 0:商城类 1:应用类 2:通用类
	 * 
	 * @param type
	 *            类型 0:商城类 1:应用类 2:通用类
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 安卓版本编号
	 * 
	 * @return 安卓版本编号
	 */
	public Integer getClientVersionA() {
		return clientVersionA;
	}

	/**
	 * 安卓版本编号
	 * 
	 * @param clientVersionA
	 *            安卓版本编号
	 */
	public void setClientVersionA(Integer clientVersionA) {
		this.clientVersionA = clientVersionA;
	}

	/**
	 * ios版本编号
	 * 
	 * @return ios版本编号
	 */
	public Integer getClientVersionI() {
		return clientVersionI;
	}

	/**
	 * ios版本编号
	 * 
	 * @param clientVersionI
	 *            ios版本编号
	 */
	public void setClientVersionI(Integer clientVersionI) {
		this.clientVersionI = clientVersionI;
	}

	/**
	 * 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner
	 * 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列
	 * 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner
	 * 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题
	 * 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 * 
	 * @return 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner
	 *         104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner
	 *         108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner
	 *         112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题 201:icon(商城)有标题
	 *         202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题 302:图文组件3张无标题 303:图文组件3张有标题
	 *         400:标签3 401:标签4
	 */
	public Integer getConfigType() {
		return configType;
	}

	/**
	 * 配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner 103:商品列表banner
	 * 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner 107:商品列表2列banner 108:商品列表3列
	 * 109:商品列表3列更多 110:商品列表3列更多banner 111:商品列表3列banner 112:商品列表单列 113商品列表单列banner
	 * 200:icon(商城)无标题 201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题
	 * 302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 * 
	 * @param configType
	 *            配置类型 0:轮播图 100:商品列表单行 101:商品列表单行更多 102:商品列表单行更多banner
	 *            103:商品列表banner 104:商品列表2列 105:商品列表2列更多 106:商品列表2列更多banner
	 *            107:商品列表2列banner 108:商品列表3列 109:商品列表3列更多 110:商品列表3列更多banner
	 *            111:商品列表3列banner 112:商品列表单列 113商品列表单列banner 200:icon(商城)无标题
	 *            201:icon(商城)有标题 202:icon(应用) 300:图文组件单张无标题 301:图文组件单张有标题
	 *            302:图文组件3张无标题 303:图文组件3张有标题 400:标签3 401:标签4
	 */
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	/**
	 * 创建时间
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
