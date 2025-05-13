package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.TemplatePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface TemplateReadDAO extends BaseReadDAO<TemplatePO> {

	/**
	 * 查询模板分页列表
	 * 
	 * @param name
	 * @param page
	 * @param clientType 
	 * @param status 
	 * @return
	 */
	List<TemplatePO> queryTemplatePage(
			@Param("name") String name, 
			@Param("type") Integer type,
			@Param("page") Pagination page, 
			@Param("status")Integer status, 
			@Param("clientType")Integer clientType,
			@Param("companyType")Integer companyType,
			@Param("platformId")Long platformId);

	/**
	 * 查询模板分页列表总记录数
	 * 
	 * @param name
	 * @param clientType 
	 * @param status 
	 * @return
	 */
	Integer queryTemplatePageTotalSize(
			@Param("name") String name, 
			@Param("type") Integer type, 
			@Param("status")Integer status, 
			@Param("clientType")Integer clientType,
			@Param("companyType")Integer companyType,
			@Param("platformId")Long platformId);

	/**
	 * 根据客户端类型查询被启用的模板
	 * 
	 * @param clientType
	 * @param type
	 *            0:商城 1:应用
	 * @return
	 */
	TemplatePO queryInUseTemplateByClientType(@Param("clientType") Integer clientType, @Param("type") Integer type,
			@Param("companyType")Integer companyType,@Param("platformId")Long platformId);
}
