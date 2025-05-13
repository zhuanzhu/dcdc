package com.egeo.components.cms.controller.api;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.business.BannerManage;
import com.egeo.components.cms.dao.read.CmsMainCfgReadDAO;
import com.egeo.components.cms.dao.write.CmsMainCfgWriteDAO;
import com.egeo.components.cms.po.CmsMainCfgPO;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/cmsMainCfg")
public class CmsMainCfgAction extends BaseSpringController {
	
	private Logger logger = LoggerFactory.getLogger(CmsMainCfgAction.class);

	@Autowired
	private BannerManage bannerManage;
	@Autowired
	private CmsMainCfgWriteDAO cmsMainCfgWriteDAO;
	@Autowired
	private CmsMainCfgReadDAO cmsMainCfgReadDAO;

	/**
	 * 根据模板ID查询模板下所有组件的配置项
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public JsonResult<String> update(CmsMainCfgPO po) {
		logger.info("更新主页配置，数据:{}", JSON.toJSONString(po));
		try {
			if(po.getCompanyId()==null) {
				return JsonResult.fail("请选择公司");
			}
			int rslt = -1;
			//1.判断是否存在公司数据
			CmsMainCfgPO companyQuery = new CmsMainCfgPO();
			companyQuery.setCompanyId(po.getCompanyId());
			Pagination page = new Pagination();
			List<CmsMainCfgPO> companyDatas = cmsMainCfgReadDAO.findAll(companyQuery,page);
			if(companyDatas.size()>1) {
				return JsonResult.fail("数据重复，请修复数据");
			}else if(companyDatas==null || companyDatas.size()==0) {
				rslt = cmsMainCfgWriteDAO.insert(po);
			}else {
				/*if(po.getBannerHeader()!=null) {
					JSONArray banners = JSON.parseArray(po.getBannerHeader());
					for(int i =0 ;i <banners.size();i++) {
						JSONObject banner = banners.getJSONObject(i);
						Long bannerId = null;
						if(banner.containsKey("id")) {
							bannerId =banner.getLong("id") ;
						}
		
						Long bannerId,String name,String companyIds,
						String imgUrl,String remark,Integer enabled,Integer sort,Integer companyType,
						//链接信息
						Integer linkType,Long linkId,String linkUrl,String linkParam,Integer belongPage,Long platformId,
						String linkableButtonPageList,String extParam
						bannerManage.saveBanner(
								bannerId, banner.getString("name"), "["+po.getCompanyId()+"]", banner.getString("imgUrl"), 
								banner.getString("remark"), banner.getInteger("enabled"),banner.getInteger("sort"),banner.getInteger("companyType"),
								banner.getInteger("linkType"), banner.getLong("linkId"), banner.getString("linkUrl"), banner.getString("linkParam"),101,7l,
								banner.getString("linkableButtonPageList"),banner.getString("extParam"));
						System.out.println(bannerId);
					}
				}else  if(po.getBannerMiddle()!=null) {
						JSONArray banners = JSON.parseArray(po.getBannerMiddle());
						for(int i =0 ;i <banners.size();i++) {
							JSONObject banner = banners.getJSONObject(i);
							Long bannerId = null;
							if(banner.containsKey("id")) {
								bannerId =banner.getLong("id") ;
							}
			
							Long bannerId,String name,String companyIds,
							String imgUrl,String remark,Integer enabled,Integer sort,Integer companyType,
							//链接信息
							Integer linkType,Long linkId,String linkUrl,String linkParam,Integer belongPage,Long platformId,
							String linkableButtonPageList,String extParam
							bannerManage.saveBanner(
									bannerId, banner.getString("name"), "["+po.getCompanyId()+"]", banner.getString("imgUrl"), 
									banner.getString("remark"), banner.getInteger("enabled"),banner.getInteger("sort"),banner.getInteger("companyType"),
									banner.getInteger("linkType"), banner.getLong("linkId"), banner.getString("linkUrl"), banner.getString("linkParam"),102,7l,
									banner.getString("linkableButtonPageList"),banner.getString("extParam"));
							System.out.println(bannerId);
							CmsMainCfgPO oldCfg = companyDatas.get(0);
							
							
							
							rslt = cmsMainCfgWriteDAO.update(po);

							if(rslt<1) {
								return JsonResult.fail("更新主页配置没有对应数据");
							}
						}
				}else {

					po.setId(companyDatas.get(0).getId());
					rslt = cmsMainCfgWriteDAO.update(po);

					if(rslt<1) {
						return JsonResult.fail("更新主页配置没有对应数据");
					}
				}*/
				
				

				po.setId(companyDatas.get(0).getId());
				rslt = cmsMainCfgWriteDAO.update(po);

				if(rslt<1) {
					return JsonResult.fail("更新主页配置没有对应数据");
				}
				
			}
		} catch (Exception e) {
			logger.error("根据模板ID查询模板下所有组件的配置项错误", e);
			return JsonResult.fail("更新主页配置失败");
		}
		return JsonResult.success("1");
	}

	@RequestMapping(value = "/get")
	@ResponseBody
	public JsonResult<List<CmsMainCfgPO>> get(CmsMainCfgPO po) {
		logger.info("获取主页配置，数据:{}", JSON.toJSONString(po));
		try {
			if(po.getCompanyId()==null) {
				return JsonResult.fail("公司ID为空");
			}
			int rslt = -1;
			//1.判断是否存在公司数据
			CmsMainCfgPO companyQuery = new CmsMainCfgPO();
			companyQuery.setCompanyId(po.getCompanyId());
			Pagination page = new Pagination();
			List<CmsMainCfgPO> companyDatas = cmsMainCfgReadDAO.findAll(companyQuery,page);
			if(companyDatas.size()>1) {
				return JsonResult.fail("数据重复，请修复数据");
			}
			return JsonResult.success(companyDatas);
		} catch (Exception e) {
			logger.error("根据模板ID查询模板下所有组件的配置项错误", e);
			return JsonResult.fail("更新主页配置失败");
		}
	}
}
	