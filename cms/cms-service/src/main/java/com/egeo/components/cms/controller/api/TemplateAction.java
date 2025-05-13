package com.egeo.components.cms.controller.api;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.TemplateManage;
import com.egeo.components.cms.vo.TemplateInstContentVO;
import com.egeo.components.cms.vo.TemplateVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/template")
public class TemplateAction extends BaseSpringController {
	
	@Resource(name="template")
	private TemplateManage templateManage;

	/**
	 * 查询模板结构(客户端)
	 * @param clientType 客户端类型 1:app 2:微信 3:小程序 4:web商城
	 * @param type 模板类型 0:商城 1:应用
	 * @return
	 */
	@RequestMapping("templateConstruct")
	@ResponseBody
	public JsonResult<Map<String,Object>> templateConstruct(Integer clientType,Integer type, Long pageTabId, HttpServletRequest req){
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		CacheUser userCache = getCacheUser();
		return templateManage.templateConstruct(clientType,type,userCache.getCompanyId(), pageTabId,platformId);
	}

	/**
	 * 客户端查询组建实例(优化原有首页的两个接口)
	 */
	@RequestMapping("/templateInstContent")
	@ResponseBody
	public JsonResult<TemplateInstContentVO> templateInstContent(Integer clientType, Integer type, Long pageTabId, Pagination page, HttpServletRequest req){
		logger.info("客户端查询模板组建市里的接口入口");
		logger.info("接收参数clientType:"+clientType);
		logger.info("接收参数type:"+type);
		logger.info("接收参数pageTabId:"+pageTabId);
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//能够调用这个接口的只有商城首页才会,门店不存在这个接口
		Long storeId=null;
		if(platformId==7){
			storeId = 1L;
		}else if(platformId==2) {
			storeId = 2L;
		}
		String clientId_=req.getHeader("clientId");
		Long clientId=1l;
		if(EmptyUtil.isNotEmpty(clientId_)) {
			clientId=Long.parseLong(clientId_);
		}
		CacheUser userCache = getCacheUser(false);
		//CacheUser userCache = tryToGetCacheUser();
		return templateManage.templateInstContent(clientId,storeId,clientType,type,userCache.getCompanyId(),platformId,pageTabId,page);
	}



	/**
	 * 后台模板分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param type 模板类型 0:商城 1:应用
	 * @param companyType  0:正式公司 1:测试公司 2:竞品公司
	 * @return
	 */
	@RequestMapping("templatePage")
	@ResponseBody
	public JsonResult<Map<String,Object>> templatePage(
			Integer pageNo,Integer pageSize,
			String name,Integer type,
			Integer status,Integer clientType,Integer companyType,
			HttpServletRequest req){
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		return templateManage.templatePage(pageNo,pageSize,name,type,status,clientType,companyType,platformId);
	}
	
	/**
	 * 后台启用模板
	 * @param templateId
	 * @return
	 */
	@RequestMapping("useTemplate")
	@ResponseBody
	public JsonResult<Map<String,Object>> useTemplate(Long templateId,HttpServletRequest req){
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}else{
			return JsonResult.fail("platformId不能为空");
		}
		return templateManage.useTemplate(platformId,templateId);
	}
	
	/**
	 * 编辑模板
	 * @param templateId
	 * @param name
	 * @param remark
	 * @param clientType
	 * @param showFgj
	 * @param elementIds
	 * @return
	 */
	@RequestMapping("saveTemplate")
	@ResponseBody
//	public JsonResult<Map<String,Object>> saveTemplate(TemplateVO vo, String elementIds){	
	public JsonResult<Map<String,Object>> saveTemplate(TemplateVO vo,
			Long templateId,String name,String remark,
			Integer clientType,Integer showFgj,String elementIds,HttpServletRequest req){
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		return templateManage.saveTemplate(templateId, name, remark, clientType, showFgj, elementIds,platformId);
	}
	
	/**
	 * 模板详情
	 * @param templateId
	 * @return
	 */
	@RequestMapping("templateDetail")
	@ResponseBody
	public JsonResult<Map<String,Object>> templateDetail(Long templateId,HttpServletRequest req){
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		return templateManage.templateDetail(templateId,platformId);
	}
	
	/**
	 * 新建空白模板
	 * @param type 模板类型 0:商城 1:应用
	 * @return
	 */
	@RequestMapping("createEmptyTmpl")
	@ResponseBody
	public JsonResult<Map<String,Object>> createEmptyTmpl(Integer type, Integer companyType,HttpServletRequest req){
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		return templateManage.createEmptyTmpl(type, companyType,platformId);
	}
	
	
}
	