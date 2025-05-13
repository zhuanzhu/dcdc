package com.egeo.components.cms.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.TemplateManage;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.components.cms.facade.ElementFacade;
import com.egeo.components.cms.facade.InstFacade;
import com.egeo.components.cms.facade.TemplateFacade;
import com.egeo.components.cms.facade.UserFacade;
import com.egeo.components.cms.vo.BannerVO;
import com.egeo.components.cms.vo.ElementConstructVO;
import com.egeo.components.cms.vo.IconVO;
import com.egeo.components.cms.vo.ImgTxtVO;
import com.egeo.components.cms.vo.ShoppingLabelVO;
import com.egeo.components.cms.vo.SuListSuVO;
import com.egeo.components.cms.vo.TemplateConstructVO;
import com.egeo.components.cms.vo.TemplateDetailVO;
import com.egeo.components.cms.vo.TemplateInstContentVO;
import com.egeo.components.cms.vo.TemplatePageVO;
import com.egeo.components.cms.vo.TmplElementListVO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("template")
public class TemplateManageImpl implements TemplateManage {

	@Resource(name = "templateFacade")
	private TemplateFacade templateFacade;

	@Resource(name = "elementFacade")
	private ElementFacade elementFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "instFacade")
	private InstFacade instFacade;

	@Override
	public JsonResult<Map<String, Object>> templateConstruct(Integer clientType, Integer type, Long companyId,
			Long pageTabId,Long platformId) {

		// 动态逻辑代码
		if (type == null) {
			return JsonResult.fail("请指定模板类型");
		}
		if (clientType == null) {
			clientType = 1;
		}
		CompanyDTO company = userFacade.queryCompanyById(companyId);
		if (company == null) {
			return JsonResult.fail("用户公司信息有误");
		}
		TemplateDTO tmpl = templateFacade.queryInUseTemplateByClientType(clientType, type, company.getCompanyType(),platformId);
		if (tmpl == null) {
			return JsonResult.fail("暂无模板信息");
		}

		if (clientType != 4) {
			pageTabId = null;
		}
		if (clientType == 4 && type == 0 && pageTabId == null) {
			return JsonResult.fail("web商城模版的分页tab的id不能为空");
		}
		
		TemplateConstructVO template = new TemplateConstructVO();
		String coopUrl = company.getCompanyLogo();
		template.setCoopUrl(coopUrl);
		template.setShowCooperator(EmptyUtil.isNotBlank(coopUrl));
		template.setShowFgj(tmpl.getShowFgj() == 1);
		template.setTemplateId(tmpl.getId());
		template.setTemplateType(tmpl.getType());
		List<ElementConstructVO> ecList = new ArrayList<>();

		// 根据模板id查询组件列表
		List<ElementDTO> eles = elementFacade.queryElementListByTmlpId(tmpl.getId());
		for (Iterator<ElementDTO> it = eles.iterator(); it.hasNext();) {
			ElementDTO ele = it.next();
			Long eleId = ele.getId();
			InstDTO inst = instFacade.queryInstByElementId(eleId);
			if (inst == null) {
				it.remove();
				continue;
			} else {
				// 过滤web商城的分页tab
				if (pageTabId != null && (inst.getPageTabId() == null || !inst.getPageTabId().equals(pageTabId))) {
					it.remove();
					continue;
				}
				
				// 使用公司id对cms实例进行过滤
				List<InstCompanyDTO> ics = instFacade.queryInstCompanyListByInstIdAndCompanyId(inst.getId(), companyId,
						company.getCompanyType());
				if (ics.size() == 0) {
					it.remove();
					continue;
				}
			}
			ElementConstructVO ec = new ElementConstructVO();
			ec.setElementId(eleId);
			ec.setConfigType(ele.getConfigType());
			ec.setElementType(ele.getType());
			ec.setInstId(inst.getId());
			ec.setInstMargin(inst.getInstMargin());
			ecList.add(ec);
		}
		template.setElementList(ecList);
		return JsonResult.success("template", template);
	}

	@Override
	public JsonResult<Map<String, Object>> templatePage(Integer pageNo, Integer pageSize, String name, Integer type,
			Integer status, Integer clientType, Integer companyType,Long platformId) {
		if (type == null) {
			return JsonResult.fail("请选择模板类型");
		}
		if (EmptyUtil.isBlank(name)) {
			name = null;
		}
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<TemplateDTO> dtoPage = templateFacade.queryTemplatePage(name, type, page, status, clientType,
				companyType,platformId);
		List<TemplateDTO> dtoList = dtoPage.getList();
		PageResult<TemplatePageVO> voPage = new PageResult<>();
		voPage.copy(dtoPage);
		List<TemplatePageVO> voList = new ArrayList<>();
		for (TemplateDTO dto : dtoList) {
			TemplatePageVO vo = new TemplatePageVO();
			vo.setClientType(dto.getClientType());
			Integer clientVersionA = dto.getClientVersionA();
			if (clientVersionA != null) {
				VersionsDTO androidVersion = userFacade.queryVersionByCodeAndType(clientVersionA, 0,platformId);
				if (androidVersion == null) {
					vo.setClientVersionA("全部");
				} else {
					vo.setClientVersionA(androidVersion.getVersionsMark());
				}
			}
			Integer clientVersionI = dto.getClientVersionI();
			if (clientVersionI != null) {
				VersionsDTO iosVersion = userFacade.queryVersionByCodeAndType(clientVersionI, 1,platformId);
				if (iosVersion == null) {
					vo.setClientVersionI("全部");
				} else {
					vo.setClientVersionI(iosVersion.getVersionsMark());
				}
			}
			vo.setRemark(dto.getRemark());
			vo.setStatus(dto.getStatus());
			vo.setTemplateId(dto.getId());
			vo.setTemplateName(dto.getName());
			voList.add(vo);
		}
		voPage.setList(voList);
		return JsonResult.success("templatePage", voPage);
	}

	@Override
	public JsonResult<Map<String, Object>> useTemplate(Long platformId, Long templateId) {
		if (templateId == null) {
			return JsonResult.fail("请选择模板");
		}
		// 模板校验
		TemplateDTO t = templateFacade.queryTemplateById(templateId);
		if (t == null) {
			return JsonResult.fail("模板不存在");
		}
		if (EmptyUtil.isBlank(t.getName())) {
			return JsonResult.fail("模板名称为空,无法启用");
		}
		if (t.getClientType() == null) {
			return JsonResult.fail("未知模板所属客户端类型,无法启用");
		}
		if (t.getShowFgj() == null) {
			return JsonResult.fail("尚未配置logo信息,无法启用");
		}
		// 查询实例列表
		List<ElementDTO> eles = elementFacade.queryElementListByTmlpId(templateId);
		if (eles.size() == 0) {
			return JsonResult.fail("该模板尚未配置任何组件,无法启用");
		}
		for (ElementDTO ele : eles) {
			// 根据组件查询实例
			Long eleId = ele.getId();
			InstDTO inst = instFacade.queryInstByElementId(eleId);
			if (inst == null) {
				return JsonResult.fail("存在未配置实例的组件,无法启用");
			}
		}
		templateFacade.useTemplate(platformId,templateId, t.getClientType(), t.getType(), t.getCompanyType());
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> saveTemplate(Long templateId, String name, String remark, Integer clientType,
			Integer showFgj, String elementIds,Long platformId) {
		if (templateId == null) {
			return JsonResult.fail("请选择模板");
		}
		if (EmptyUtil.isBlank(name)) {
			return JsonResult.fail("请输入模板名");
		}
		if (name.length() > 30) {
			return JsonResult.fail("模板名称不超过30个字");
		}
		if (clientType == null) {
			return JsonResult.fail("请选择所属客户端");
		}
		if (remark != null && remark.length() > 100) {
			return JsonResult.fail("模板备注最多不超过100个字");
		}
		if (showFgj == null) {
			showFgj = 0;// 默认不显示
		}
		if (EmptyUtil.isBlank(elementIds)) {
			return JsonResult.fail("请至少配置一个实例");
		}
		List<Long> eleIds = StringUtils.stringWithBorder2IdList(elementIds, ",");
		if (eleIds.size() == 0) {
			return JsonResult.fail("请至少配置一个实例");
		}

		// 根据模版id查询模版信息
		TemplateDTO templateDTO = templateFacade.queryTemplateById(templateId);

		// 查询该客户端同一类型,公司类型下的模板列表
		// 如果只有一个模板且id与当前模板id相等
		// 则认为该模板是当前客户端类型的唯一模板
		// 默认设置为启用
		List<TemplateDTO> existTmpls = templateFacade.queryTemplatesByClientType(clientType, templateDTO.getType(),
				templateDTO.getCompanyType(),platformId);
		int defaultStatus = 1;// 默认启用值为停用
		boolean otherEnabledTmplExist = false;
		if (existTmpls.size() == 1) {
			TemplateDTO firstTmpl = existTmpls.get(0);
			// 如果出现出了的模版是当前模版则设为启用
			if (firstTmpl.getId().longValue() == templateId.longValue()) {
				defaultStatus = 0;
			}
		} else
		// 如果当前平台,当前公司类型下存在多个模板,且除该模板的所有模板都是禁用状态,将当前模板设为启用
		if (existTmpls.size() > 1) {
			for (TemplateDTO t : existTmpls) {
				if (t.getStatus() == 0 && t.getId().longValue() != templateId.longValue()) {
					// 发现了其他被启用的模板
					otherEnabledTmplExist = true;
					break;
				}
			}
			// 如果不存在其他启用的模板,设置这个模板为启用
			if (!otherEnabledTmplExist) {
				defaultStatus = 0;
			}
		}

		TemplateDTO tmpl = new TemplateDTO();
		tmpl.setClientType(clientType);
		tmpl.setName(name);
		tmpl.setRemark(remark);
		tmpl.setShowFgj(showFgj);
		tmpl.setId(templateId);
		tmpl.setStatus(defaultStatus);
		tmpl.setPlatformId(platformId);
		templateFacade.editTemplate(tmpl, eleIds);
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> templateDetail(Long templateId, Long platformId) {
		if (templateId == null) {
			return JsonResult.fail("请选择模板");
		}
		TemplateDTO tmpl = templateFacade.queryTemplateById(templateId);
		if (tmpl == null) {
			return JsonResult.fail("模板不存在");
		}
		TemplateDetailVO td = new TemplateDetailVO();
		td.setClientType(tmpl.getClientType());
		td.setName(tmpl.getName());
		td.setRemark(tmpl.getRemark());
		td.setShowFgj(tmpl.getShowFgj());
		td.setType(tmpl.getType());
		td.setCompanyType(tmpl.getCompanyType());
		// 查询组件列表
		List<ElementDTO> elementList = elementFacade.queryElementListByTmlpId(templateId);
		List<TmplElementListVO> voList = new ArrayList<>();
		for (ElementDTO ele : elementList) {
			TmplElementListVO vo = new TmplElementListVO();
			vo.setElementId(ele.getId());
			// 查询实例
			InstDTO inst = instFacade.queryInstByElementId(ele.getId());
			if (inst != null) {
				vo.setInstId(inst.getId());
			}
			Integer clientVersionA = ele.getClientVersionA();
			if (clientVersionA != null) {
				VersionsDTO androidVersion = userFacade.queryVersionByCodeAndType(clientVersionA, 0,platformId);
				if (androidVersion == null) {
					vo.setClientVersionA("全部");
				} else {
					vo.setClientVersionA(androidVersion.getVersionsMark());
				}
			}
			Integer clientVersionI = ele.getClientVersionI();
			if (clientVersionI != null) {
				VersionsDTO iosVersion = userFacade.queryVersionByCodeAndType(clientVersionI, 1,platformId);
				if (iosVersion == null) {
					vo.setClientVersionI("全部");
				} else {
					vo.setClientVersionI(iosVersion.getVersionsMark());
				}
			}
			vo.setElementName(ele.getName());
			// 获取字典
			Integer configType = ele.getConfigType();
			ElementDictDTO dict = elementFacade.queryElementDictByConfigType(configType);
			if (dict != null) {
				vo.setImgUrl(dict.getImgUrl());
			}
			voList.add(vo);
		}
		td.setElementList(voList);
		return JsonResult.success("template", td);
	}

	@Override
	public JsonResult<Map<String, Object>> createEmptyTmpl(Integer type, Integer companyType,Long platformId) {
		if (type == null) {
			return JsonResult.fail("请指定模板类型");
		}

		if (companyType == null) {
			return JsonResult.fail("请选择公司类型");
		}

		Long tmplId = templateFacade.createEmptyTmpl(type, companyType,platformId);
		return JsonResult.success("tmplId", tmplId);
	}

	@Override
	public JsonResult<TemplateInstContentVO> templateInstContent(Long clientId, Long storeId, Integer clientType, Integer type, Long companyId, Long platformId, Long pageTabId, Pagination page) {

		// 动态逻辑代码
		if (type == null) {
			return JsonResult.fail("请指定模板类型");
		}
		if (clientType == null) {
			clientType = 1;
		}
		CompanyDTO company = userFacade.queryCompanyById(companyId);
		if (company == null) {
			return JsonResult.fail("用户公司信息有误");
		}
		TemplateDTO tmpl = templateFacade.queryInUseTemplateByClientType(clientType, type, company.getCompanyType(),platformId);
		if (tmpl == null) {
			return JsonResult.fail("暂无模板信息");
		}

		if (clientType != 4) {
			pageTabId = null;
		}
		if (clientType == 4 && type == 0 && pageTabId == null) {
			return JsonResult.fail("web商城模版的分页tab的id不能为空");
		}

		TemplateInstContentVO template = new TemplateInstContentVO();
		String coopUrl = company.getCompanyLogo();
		template.setCoopUrl(coopUrl);
		template.setShowCooperator(EmptyUtil.isNotBlank(coopUrl));
		template.setShowFgj(tmpl.getShowFgj() == 1);
		template.setTemplateId(tmpl.getId());
		template.setTemplateType(tmpl.getType());
		// 根据模板id查询组件列表
		List<ElementConstructVO> ecList = getEcList(tmpl.getId(), pageTabId, companyId, company);

		//分页查询实例
		// 查询实例inst的信息
		List<Map<String, Object>> instMapList = new ArrayList<>();
		String error=getInstContentByPage(instMapList,storeId,ecList,companyId,platformId,clientId,page);
		if(EmptyUtil.isNotEmpty(error)){
			return JsonResult.fail(error);
		}
		template.setInstMapList(instMapList);
		template.setPageNo(page.getPageNo());
		template.setPageSize(page.getPageSize());
		template.setTotalSize(ecList.size());
		return JsonResult.success(template);
	}

	/*
	查询inst实例并进行分页
	 */
	private String getInstContentByPage(List<Map<String, Object>> instMapList, Long storeId, List<ElementConstructVO> ecList, Long companyId, Long platformId, Long clientId, Pagination page){
		int start=page.getPageSize()*(page.getPageNo()-1);
		int end=page.getPageSize()*page.getPageNo()-1;
		for(;start<ecList.size()&&start<=end;start++){
			Map<String, Object> data = new HashMap<>();
			InstDTO inst = instFacade.findInstById(ecList.get(start).getInstId());
			if (inst == null) {
				throw new BusinessException("实例不存在");
			}
			data.put("instId", ecList.get(start).getInstId());
			Integer configType = inst.getConfigType();
			data.put("elementType", configType);
			data.put("instMargin", inst.getInstMargin());
			data.put("configType", ecList.get(start).getConfigType());

			// 查询实例inst的信息
			Map<String, Object> instMap = new HashMap<>();
			int ct = configType / 100;
			String err = null;
			switch (ct) {
				case 0:
					// banner
					err = instContent_bannerList(ecList.get(start).getInstId(), companyId, instMap, clientId, platformId);
					break;
				case 1:
					// suList
					err = instContent_suList(storeId,ecList.get(start).getInstId(), companyId, clientId, instMap, configType, platformId);
					break;
				case 2:
					// icon
					err = instContent_iconGroup(ecList.get(start).getInstId(), instMap, companyId, clientId, platformId);
					break;
				case 3:
					// 图文
					err = instContent_imagetextGroup(ecList.get(start).getInstId(), instMap, companyId, Integer.valueOf(0), clientId, platformId);
					break;
				case 4:
					// 商城标签
					err = instContent_shoppingLabelGroup(ecList.get(start).getInstId(), instMap, companyId, platformId);
					break;
				case 5:
					// 混合组件1: 商品缩略图（上下两分图）
					err = instContent_imagetextGroup(ecList.get(start).getInstId(), instMap, companyId, Integer.valueOf(0), clientId, platformId);
					err = instContent_suList(storeId, ecList.get(start).getInstId(), companyId, clientId, instMap, configType, platformId);

					break;
				case 6:
					// 混合组件2: 商品缩略图（上轮播下两张分图）
					err = instContent_imagetextGroup(ecList.get(start).getInstId(), instMap, companyId, Integer.valueOf(0), clientId, platformId);
					err = instContent_imagetextGroup(ecList.get(start).getInstId(), instMap, companyId, Integer.valueOf(1), clientId, platformId);
					err = instContent_suList(storeId, ecList.get(start).getInstId(), companyId, clientId, instMap, configType, platformId);
					break;
			}
			if(EmptyUtil.isNotEmpty(err)){
				return err;
			}
			data.put("inst",instMap );
			instMapList.add(data);
		}
		return null;
	}

	/**
	 * 商城标签实例内容
	 *
	 * @param instId
	 * @param instMap
	 * @param companyId
	 * @return
	 */
	private String instContent_shoppingLabelGroup(Long instId, Map<String, Object> instMap, Long companyId,Long platformId) {
		ShoppingLabelGroupDTO slg = templateFacade.queryShoppingLabelGroupByInstId(instId);
		if (slg == null) {
			return "标签实例不存在";
		}
		Map<String, Object> labelGroup = new HashMap<>();
		labelGroup.put("imgUrl", slg.getImgUrl());
		List<ShoppingLabelDTO> dtoList = templateFacade.queryShoppingLabelListByGroupId(slg.getId());
		List<ShoppingLabelVO> voList = new ArrayList<>();
		for (ShoppingLabelDTO dto : dtoList) {
			ShoppingLabelVO vo = new ShoppingLabelVO();
			vo.setLabelName(dto.getName());
			voList.add(vo);
		}
		labelGroup.put("lableList", voList);
		instMap.put("labelGroup", labelGroup);
		return null;
	}



	/**
	 * 图文组件实例内容
	 *
	 * @param instId
	 * @param instMap
	 * @param companyId
	 * @return
	 */
	private String instContent_imagetextGroup(Long instId, Map<String, Object> instMap, Long companyId,
											  Integer groupType, Long clientId,Long platformId) {
		ImagetextGroupDTO ig = templateFacade.queryImagetextGroupByInstId(instId, groupType);
		if (ig == null) {
			return "图文组件实例不存在";
		}
		Map<String, Object> imagetext = new HashMap<>();
		imagetext.put("groupTitle", ig.getTitle());
		imagetext.put("groupType", ig.getGroupType());
		List<ImagetextDTO> itList = templateFacade.queryImagetextByGroupId(ig.getId());
		List<ImgTxtVO> imgtxts = new ArrayList<>();
		for (ImagetextDTO dto : itList) {
			ImgTxtVO vo = new ImgTxtVO();
			vo.setImagetextId(dto.getId());
			vo.setImagetextUrl(dto.getImgUrl());
			vo.setLinkType(6);
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = templateFacade.queryLinkableByttonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = templateFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);

						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = templateFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);

					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
					vo.setCmsPageId(queryCmsPageIdByLinkbaleId(linkableId,linkType,clientId,platformId));
				}
			}
			imgtxts.add(vo);
		}
		imagetext.put("imagetextList", imgtxts);
		if (groupType == 0) {

			instMap.put("imagetext", imagetext);
		} else if (groupType == 1) {

			instMap.put("imagetextBanner", imagetext);
		}
		return null;
	}

	/***
	 * 根据linkableId 查询跳转pageid
	 * @param linkableId
	 * @return
	 */
	private Long queryCmsPageIdByLinkbaleId(Long linkableId, Integer linkType,Long clinetId,Long platformId){
		
		Long cmsPageId = null;
		
		if(linkType == null || linkType != CmsConstant.CMS_LINK_TYPE_SU_LIST) {
			return null;
		}
		
		Integer clinetType = clinetId == 3 ? CmsConstant.CMS_CLINTE_TYPE_PC : CmsConstant.CMS_CLINTE_TYPE_MOBILE;
		
		List<LinkableButtonPageDTO> listDto = templateFacade.queryLinkableButtonPageById(linkableId);
		
		// 没有配置的情况下 获取默认页面
		if(listDto != null && listDto.size() > 0) {
			for (LinkableButtonPageDTO linkableButtonPageDTO : listDto) {
				
				if(clinetType == linkableButtonPageDTO.getClientType()) {
					cmsPageId = linkableButtonPageDTO.getCmsPageId();
				}
			}
		}
		
		if(cmsPageId == null) {
			if(platformId == CmsConstant.CMS_PLATFORM_FGJ ) {
				if(clinetType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_1;
				}else if(clinetType == CmsConstant.CMS_CLINTE_TYPE_PC) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_2;
				}
			}else if(platformId == CmsConstant.CMS_PLATFORM_MYY ) {
				if(clinetType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_9;
				}else if(clinetType == CmsConstant.CMS_CLINTE_TYPE_PC) {
					cmsPageId = CmsConstant.CMS_DEFAULT_PAGE_SU_10;
				}

			}
		}
			
		return cmsPageId;
	}

	/**
	 * icon组件实例内容
	 *
	 * @param instId
	 * @param instMap
	 * @param companyId
	 * @return
	 */
	private String instContent_iconGroup(Long instId, Map<String, Object> instMap, Long companyId, Long clientId,Long platformId) {
		// 查询iconGroup信息
		IconGroupDTO ig = templateFacade.queryIconGroupByInstId(instId);
		if (ig == null) {
			return "icon实例信息不存在";
		}
		Map<String, Object> iconGroup = new HashMap<>();
		iconGroup.put("groupId", ig.getId());
		iconGroup.put("titleName", ig.getTitle());

		// 查询icon列表
		List<IconDTO> iconList = templateFacade.queryIconsByGroupId(ig.getId());
		List<IconVO> voList = new ArrayList<>();
		for (IconDTO dto : iconList) {
			// 根据companyId过滤icon数据
			// List<IconCompanyDTO>
			// ics=iconFacade.queryIconCompanysByIconIdAndCompanyId(dto.getId(),companyId);
			IconVO vo = new IconVO();
			vo.setIconId(dto.getId());
			vo.setSummary(dto.getSummary());
			vo.setIconName(dto.getName());
			vo.setImgUrl(dto.getUrl());
			vo.setLinkType(6);
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = templateFacade.queryLinkableByttonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 当链接类型是单个商品时,linkId即suId
						// 单个商品查询spu模板
						Long suTmplId = templateFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);

						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = templateFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);
					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
					vo.setCmsPageId(queryCmsPageIdByLinkbaleId(linkableId,linkType,clientId,platformId));
				}
			}
			voList.add(vo);
		}
		iconGroup.put("iconList", voList);
		instMap.put("iconGroup", iconGroup);
		return null;
	}



	/**
	 * 商品列表实例内容
	 *
	 *
	 * @param storeId
	 * @param instId
	 * @param companyId
	 * @param instMap
	 * @return
	 */
	private String instContent_suList(Long storeId, Long instId, Long companyId, Long clientId, Map<String, Object> instMap,
									  Integer configType, Long platformId) {
		// 查询suList信息
		SuListDTO suListInst = templateFacade.querySuListByInstId(instId);
		if (suListInst == null) {
			return "商品列表实例不存在";
		}
		Map<String, Object> suList = new HashMap<>();
		String titleName = suListInst.getTitleName();
		suList.put("titleName", titleName);

		// 设置suList标题颜色
		String titleColor = null;
		if (suListInst.getTitleColor() != null) {
			CmsDictDTO cmsDictDTO = new CmsDictDTO();
			cmsDictDTO.setId(suListInst.getTitleColor());
			cmsDictDTO = templateFacade.findCmsDictById(cmsDictDTO);
			titleColor = cmsDictDTO != null ? cmsDictDTO.getDescription() : null;
		}

		suList.put("titleColor",titleColor);
		suList.put("showType", configType);
		suList.put("moreFlag", EmptyUtil.isNotBlank(titleName));
		suList.put("sucId", suListInst.getSucId());
		String bannerUrl = suListInst.getBannerUrl();
		if (StringUtils.isEmpty(bannerUrl)) {
			suList.put("banner", null);
		} else {
			BannerVO bannerVO = new BannerVO();
			bannerVO.setImgUrl(suListInst.getBannerUrl());
			bannerVO.setLinkType(6);
			Long linkableId = suListInst.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = templateFacade.queryLinkableByttonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					bannerVO.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					bannerVO.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = templateFacade.querySuTmplIdBySuId(LinkId);
						bannerVO.setSuTmplId(suTmplId);

						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = templateFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						bannerVO.setSuCompanyAvailable(companyAvailable);
					}
					bannerVO.setLinkParam(lb.getLinkParam());
					bannerVO.setLinkUrl(lb.getLinkUrl());
					bannerVO.setCmsPageId(queryCmsPageIdByLinkbaleId(linkableId,linkType,clientId,platformId));
				}

			}
			suList.put("banner", bannerVO);
		}
		PageResult<StandardUnitDTO> suPage = templateFacade.querySusBySucId(suListInst.getSucId(), platformId, companyId,
				clientId, suListInst.getMaxShow(), storeId);
		List<StandardUnitDTO> suDTOList = suPage.getList();
		List<SuListSuVO> goodsList = new ArrayList<>();
		for (StandardUnitDTO suDTO : suDTOList) {
			if(storeId==2L&&suDTO.getStoreId()!=2L){
				continue;
			}
			if(storeId==1L&&suDTO.getStoreId()!=1L){
				continue;
			}
			SuListSuVO vo = new SuListSuVO();
			vo.setMarketPrice(suDTO.getMarketPrice() == null ? null : suDTO.getMarketPrice().doubleValue());
			vo.setSalePrice(suDTO.getSalePrice() == null ? null : suDTO.getSalePrice().doubleValue());
			Long suId = suDTO.getId();
			vo.setSuId(suDTO.getId());
			vo.setSuImgUrl(suDTO.getPictureUrl());
			vo.setSuName(suDTO.getName());
			Long suTmplId = templateFacade.querySuTmplIdBySuId(suId);
			vo.setSuTmplId(suTmplId);
			vo.setSaleWay(suDTO.getSaleWay());
			if(suDTO.getMerchantId().equals(1L)){
				vo.setIsOwnMerchant(1);
			}else{
				vo.setIsOwnMerchant(0);
			}
			goodsList.add(vo);

		}
		suList.put("goodsList", goodsList);
		
		suList.put("linkType", CmsConstant.CMS_LINK_TYPE_SU_LIST);
		
		suList.put("cmsPageId", queryCmsPageIdByLinkbaleId(suListInst.getLinkableId(), CmsConstant.CMS_LINK_TYPE_SU_LIST,clientId,platformId));
		
		instMap.put("suList", suList);
		return null;
	}


	/**
	 * 轮播图列表实例内容
	 *
	 * @param instId
	 * @param companyId
	 */
	private String instContent_bannerList(Long instId, Long companyId, Map<String, Object> instMap, Long clientId,Long platformId) {
		List<BannerDTO> dtoList = templateFacade.queryBannerListByInstIdAndCompanyId(instId, companyId);
		List<BannerVO> bannerList = new ArrayList<>();
		for (BannerDTO dto : dtoList) {
			BannerVO vo = new BannerVO();
			vo.setId(dto.getId());
			vo.setImgUrl(dto.getImgUrl());
			vo.setLinkType(6);// 默认给出无效果
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = templateFacade.queryLinkableByttonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = templateFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);

						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = templateFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);
					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
					vo.setCmsPageId(queryCmsPageIdByLinkbaleId(linkableId,linkType,clientId,platformId));
				}
			}
			bannerList.add(vo);
		}
		instMap.put("bannerList", bannerList);
		return null;
	}





	/*
	 * 根据模板id查询组件列表
	 */
	private List<ElementConstructVO> getEcList(Long id, Long pageTabId, Long companyId, CompanyDTO company){
		List<ElementConstructVO> ecList = new ArrayList<>();

		// 根据模板id查询组件列表
		List<ElementDTO> eles = elementFacade.queryElementListByTmlpIdByPage(id,null);
		for (Iterator<ElementDTO> it = eles.iterator(); it.hasNext();) {
			ElementDTO ele = it.next();
			Long eleId = ele.getId();
			InstDTO inst = instFacade.queryInstByElementId(eleId);
			if (inst == null) {
				it.remove();
				continue;
			} else {
				// 过滤web商城的分页tab
				if (pageTabId != null && (inst.getPageTabId() == null || !inst.getPageTabId().equals(pageTabId))) {
					it.remove();
					continue;
				}

				// 使用公司id对cms实例进行过滤
				List<InstCompanyDTO> ics = instFacade.queryInstCompanyListByInstIdAndCompanyId(inst.getId(), companyId,
						company.getCompanyType());
				if (ics.size() == 0) {
					it.remove();
					continue;
				}
			}
			ElementConstructVO ec = new ElementConstructVO();
			ec.setElementId(eleId);
			ec.setConfigType(ele.getConfigType());
			ec.setElementType(ele.getType());
			ec.setInstId(inst.getId());
			ec.setInstMargin(inst.getInstMargin());
			ecList.add(ec);
		}
		return ecList;
	}

}
