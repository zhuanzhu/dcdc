package com.egeo.components.cms.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.business.InstManage;
import com.egeo.components.cms.vo.LinkableButtonPageVO;
import com.egeo.entity.CacheUser;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/inst")
public class InstAction extends BaseSpringController {

	@Resource(name = "inst")
	private InstManage instManage;

	/**
	 * 查询实例内容
	 * 
	 * @param elementId
	 * @param instId
	 * @param req
	 * @return
	 */
	@RequestMapping("instContent")
	@ResponseBody
	public JsonResult<Map<String, Object>> instContent(Long elementId, Long instId, HttpServletRequest req) {
		// 获取companyId
		CacheUser userCache = this.getCacheUser();
		Long companyId = userCache.getCompanyId();
		if (companyId == null) {
			return JsonResult.fail("公司信息有误");
		}
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		String clientId_=req.getHeader("clientId");
		Long clientId=1l;
		if(EmptyUtil.isNotEmpty(clientId_)) {
			clientId=Long.parseLong(clientId_);
		}
		Long storeId=null;
		if(platformId==7){
			storeId = 1L;
		}else if(platformId==2) {
			storeId = 2L;
		}
		return instManage.instContent(storeId,elementId, instId, companyId,platformId,clientId);
	}

	/**
	 * 新增/编辑组件和实例
	 * @param name
	 * @param margin
	 * @param elementDictId
	 * @param elementId
	 * @param instId
	 * @param companyIds
	 * @param bannerIds
	 * @param titleName
	 * @param sortType
	 * @param maxShow
	 * @param sucId
	 * @param bannerUrl
	 * @param linkType
	 * @param linkId
	 * @param linkUrl
	 * @param linkParam
	 * @param count
	 * @param iconArr
	 * @param iconTitle
	 * @param imagetextArr
	 * @param lableIconUrl
	 * @param lableArr
	 * @return
	 */
	@RequestMapping("saveEleAndInst")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveEleAndInst(
			// 通用参数
			Long templateId,String name, Integer margin, Long elementDictId, Long elementId, Long instId, String companyIds,
			Long pageTabId,
			// banner参数
			String bannerIds,
			// su列表参数
			Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType,
			Long linkId, String linkUrl,
			// icon参数
			Integer count, String iconArr, String iconTitle,
			// 图文参数
			String imagetextTitle,String imagetextArr, String imagetextBannerArr,
			// 标签参数
			String lableIconUrl, String lableArr,String linkableButtonPageList) {
		
			List<LinkableButtonPageVO> linkableButtonPageVOs = null;
			
			if(StringUtils.isNotBlank(linkableButtonPageList)) {
				linkableButtonPageVOs = JSONArray.parseArray(linkableButtonPageList, LinkableButtonPageVO.class);
			}
			
		return instManage.saveEleAndInst(
				templateId,name, margin, elementDictId, elementId, instId, companyIds, pageTabId,
				bannerIds, 
				titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, 
				count, iconArr, iconTitle,
				imagetextTitle, imagetextArr, imagetextBannerArr,
				lableIconUrl, lableArr,linkableButtonPageVOs);
	}
	
	/**
	 * 查询组件和实例详情
	 * @param elementId
	 * @return
	 */
	@RequestMapping("eleAndInstDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> eleAndInstDetail(Long elementId){
		return instManage.eleAndInstDetail(elementId);
	}
}
