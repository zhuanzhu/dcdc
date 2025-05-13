package com.egeo.components.cms.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.InstManage;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.BannerInstDTO;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.components.cms.dto.IconCompanyDTO;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.facade.BannerFacade;
import com.egeo.components.cms.facade.CmsDictFacade;
import com.egeo.components.cms.facade.ElementFacade;
import com.egeo.components.cms.facade.IconFacade;
import com.egeo.components.cms.facade.ImagetextFacade;
import com.egeo.components.cms.facade.InstFacade;
import com.egeo.components.cms.facade.LinkableButtonFacade;
import com.egeo.components.cms.facade.PageTabFacade;
import com.egeo.components.cms.facade.ProductFacade;
import com.egeo.components.cms.facade.ShoppingLabelFacade;
import com.egeo.components.cms.facade.StandardUnitStoreFacade;
import com.egeo.components.cms.facade.SuListFacade;
import com.egeo.components.cms.vo.BannerVO;
import com.egeo.components.cms.vo.ElementDetailVO;
import com.egeo.components.cms.vo.IconGroupVO;
import com.egeo.components.cms.vo.IconVO;
import com.egeo.components.cms.vo.ImagetextGroupVO;
import com.egeo.components.cms.vo.ImgTxtVO;
import com.egeo.components.cms.vo.LinkableButtonPageVO;
import com.egeo.components.cms.vo.LinkableButtonVO;
import com.egeo.components.cms.vo.ShoppingLabelGroupVO;
import com.egeo.components.cms.vo.ShoppingLabelVO;
import com.egeo.components.cms.vo.SuListDetailVO;
import com.egeo.components.cms.vo.SuListSuVO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.orm.PageResult;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("inst")
public class InstManageImpl implements InstManage {
	

	@Resource(name = "instFacade")
	private InstFacade instFacade;

	@Resource(name = "elementFacade")
	private ElementFacade elementFacade;

	@Resource(name = "bannerFacade")
	private BannerFacade bannerFacade;

	@Resource(name = "suListFacade")
	private SuListFacade suListFacade;

	@Resource(name = "linkableButtonFacade")
	private LinkableButtonFacade linkableButtonFacade;

	@Resource(name = "iconFacade")
	private IconFacade iconFacade;

	@Resource(name = "imagetextFacade")
	private ImagetextFacade imagetextFacade;

	@Resource(name = "shoppingLabelFacade")
	private ShoppingLabelFacade shoppingLabelFacade;

	@Resource(name = "productFacade")
	private ProductFacade productFacade;
	
	@Resource(name = "cmsDictFacade")
	private CmsDictFacade cmsDictFacade;
	
	@Resource(name = "pageTabFacade")
	private PageTabFacade pageTabFacade;

	@Resource(name = "standardUnitStoreFacade")
	private StandardUnitStoreFacade standardUnitStoreFacade;
	

	@Override
	public JsonResult<Map<String, Object>> instContent(Long storeId,Long elementId, Long instId, Long companyId, Long platformId,
			Long clientId) {

		// 动态逻辑代码
		if (instId == null) {
			return JsonResult.fail("请选择实例");
		}
		InstDTO inst = instFacade.findInstById(instId);
		if (inst == null) {
			return JsonResult.fail("实例不存在");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("instId", instId);
		Integer configType = inst.getConfigType();
		data.put("elementType", configType);
		data.put("instMargin", inst.getInstMargin());
		
		// 查询实例inst的信息
		Map<String, Object> instMap = new HashMap<>();
		int ct = configType / 100;
		String err = null;
		switch (ct) {
		case 0:
			// banner
			err = instContent_bannerList(instId, companyId, instMap, clientId);
			break;
		case 1:
			// suList
			err = instContent_suList(storeId,instId, companyId, clientId, instMap, configType, platformId);
			break;
		case 2:
			// icon
			err = instContent_iconGroup(instId, instMap, companyId, clientId);
			break;
		case 3:
			// 图文
			err = instContent_imagetextGroup(instId, instMap, companyId, Integer.valueOf(0), clientId);
			break;
		case 4:
			// 商城标签
			err = instContent_shoppingLabelGroup(instId, instMap, companyId);
			break;
		case 5:
			// 混合组件1: 商品缩略图（上下两分图）
			err = instContent_imagetextGroup(instId, instMap, companyId, Integer.valueOf(0), clientId);
			err = instContent_suList(storeId, instId, companyId, clientId, instMap, configType, platformId);
			
			break;
		case 6:
			// 混合组件2: 商品缩略图（上轮播下两张分图）
			err = instContent_imagetextGroup(instId, instMap, companyId, Integer.valueOf(0), clientId);
			err = instContent_imagetextGroup(instId, instMap, companyId, Integer.valueOf(1), clientId);
			err = instContent_suList(storeId, instId, companyId, clientId, instMap, configType, platformId);
			break;
		}
		if (err != null) {
			return JsonResult.fail(err);
		}
		data.put("inst", instMap);
		return JsonResult.success(data);

	}

	/**
	 * 商城标签实例内容
	 * 
	 * @param instId
	 * @param instMap
	 * @param companyId
	 * @return
	 */
	private String instContent_shoppingLabelGroup(Long instId, Map<String, Object> instMap, Long companyId) {
		ShoppingLabelGroupDTO slg = shoppingLabelFacade.queryShoppingLabelGroupByInstId(instId);
		if (slg == null) {
			return "标签实例不存在";
		}
		Map<String, Object> labelGroup = new HashMap<>();
		labelGroup.put("imgUrl", slg.getImgUrl());
		List<ShoppingLabelDTO> dtoList = shoppingLabelFacade.queryShoppingLabelListByGroupId(slg.getId());
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
			Integer groupType, Long clientId) {
		ImagetextGroupDTO ig = imagetextFacade.queryImagetextGroupByInstId(instId, groupType);
		if (ig == null) {
			return "图文组件实例不存在";
		}
		Map<String, Object> imagetext = new HashMap<>();
		imagetext.put("groupTitle", ig.getTitle());
		imagetext.put("groupType", ig.getGroupType());
		List<ImagetextDTO> itList = imagetextFacade.queryImagetextByGroupId(ig.getId());
		List<ImgTxtVO> imgtxts = new ArrayList<>();
		for (ImagetextDTO dto : itList) {
			ImgTxtVO vo = new ImgTxtVO();
			vo.setImagetextId(dto.getId());
			vo.setImagetextUrl(dto.getImgUrl());
			vo.setLinkType(6);
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = linkableButtonFacade.queryLinkableButtonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = productFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);
						
						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = productFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);
						
					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
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

	/**
	 * icon组件实例内容
	 * 
	 * @param instId
	 * @param instMap
	 * @param companyId
	 * @return
	 */
	private String instContent_iconGroup(Long instId, Map<String, Object> instMap, Long companyId, Long clientId) {
		// 查询iconGroup信息
		IconGroupDTO ig = iconFacade.queryIconGroupByInstId(instId);
		if (ig == null) {
			return "icon实例信息不存在";
		}
		Map<String, Object> iconGroup = new HashMap<>();
		iconGroup.put("groupId", ig.getId());
		iconGroup.put("titleName", ig.getTitle());

		// 查询icon列表
		List<IconDTO> iconList = iconFacade.queryIconsByGroupId(ig.getId());
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
				LinkableButtonDTO lb = linkableButtonFacade.queryLinkableButtonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 当链接类型是单个商品时,linkId即suId
						// 单个商品查询spu模板
						Long suTmplId = productFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);
						
						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = productFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);
					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
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
		SuListDTO suListInst = suListFacade.querySuListByInstId(instId);
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
			cmsDictDTO = cmsDictFacade.findCmsDictById(cmsDictDTO);
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
				LinkableButtonDTO lb = linkableButtonFacade.queryLinkableButtonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					bannerVO.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					bannerVO.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = productFacade.querySuTmplIdBySuId(LinkId);
						bannerVO.setSuTmplId(suTmplId);
						
						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = productFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						bannerVO.setSuCompanyAvailable(companyAvailable);
					}
					bannerVO.setLinkParam(lb.getLinkParam());
					bannerVO.setLinkUrl(lb.getLinkUrl());
				}

			}
			suList.put("banner", bannerVO);
		}
		PageResult<StandardUnitDTO> suPage = suListFacade.querySusBySucId(suListInst.getSucId(), platformId, companyId,
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
				Long suTmplId = productFacade.querySuTmplIdBySuId(suId);
				vo.setSuTmplId(suTmplId);
				vo.setSaleWay(suDTO.getSaleWay());
				goodsList.add(vo);

		}
		suList.put("goodsList", goodsList);
		instMap.put("suList", suList);
		return null;
	}

	/**
	 * 轮播图列表实例内容
	 * 
	 * @param instId
	 * @param companyId
	 */
	private String instContent_bannerList(Long instId, Long companyId, Map<String, Object> instMap, Long clientId) {
		List<BannerDTO> dtoList = bannerFacade.queryBannerListByInstIdAndCompanyId(instId, companyId);
		List<BannerVO> bannerList = new ArrayList<>();
		for (BannerDTO dto : dtoList) {
			BannerVO vo = new BannerVO();
			vo.setId(dto.getId());
			vo.setImgUrl(dto.getImgUrl());
			vo.setLinkType(6);// 默认给出无效果
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = linkableButtonFacade.queryLinkableButtonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = productFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);
						
						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = productFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);
					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
				}
			}
			bannerList.add(vo);
		}
		instMap.put("bannerList", bannerList);
		return null;
	}

	@Override
	public JsonResult<Map<String, Object>> saveEleAndInst(
			// 通用参数
			Long templateId, String name, Integer margin, Long elementDictId, Long elementId, Long instId,
			String companyIds, Long pageTabId,
			// banner参数
			String bannerIds,
			// su列表参数
			Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType,
			Long linkId, String linkUrl,
			// icon参数
			Integer count, String iconArr, String iconTitle,
			// 图文参数
			String imagetextTitle, String imagetextArr, String imagetextBannerArr,
			// 标签参数
			String lableIconUrl, String lableArr,
			List<LinkableButtonPageVO> LinkableButtonPageVOs
			) {
		// 公共参数校验
		if (templateId == null) {
			return JsonResult.fail("请指定模板");
		}
		if (EmptyUtil.isBlank(name)) {
			return JsonResult.fail("请填写组件名");
		}
		if (margin == null) {
			return JsonResult.fail("请填写边距");
		}
		if (margin < 0) {
			return JsonResult.fail("边距不可为负值");
		}
		if (elementDictId == null) {
			return JsonResult.fail("请选择组件字典");
		}
		if (StringUtils.isEmpty(companyIds)) {
			return JsonResult.fail("至少选择一个公司");
		}
		if (EmptyUtil.isNotBlank(titleName) && titleName.length() > 6) {
			return JsonResult.fail("标题名称最多不超过6个字");
		}
		List<Long> cids = StringUtils.stringWithBorder2IdList(companyIds, ",");
		if (cids.size() == 0) {
			return JsonResult.fail("至少选择一个公司");
		}

		// facade方法原子性修改
		instFacade.saveEleAndInst(templateId, name, margin, elementDictId, elementId, instId, cids, pageTabId,
				bannerIds, titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, count, iconArr,
				iconTitle, imagetextTitle, imagetextArr, imagetextBannerArr, lableIconUrl, lableArr,LinkableButtonPageVOs);

		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> eleAndInstDetail(Long elementId) {
		if (elementId == null) {
			return JsonResult.fail("请选择组件");
		}
		ElementDTO ele = elementFacade.queryElementById(elementId);
		if (ele == null) {
			return JsonResult.fail("组件不存在");
		}
		ElementDetailVO ed = new ElementDetailVO();
		ed.setElementId(ele.getId());
		ed.setElementName(ele.getName());
		// 查询字典
		Integer configType = ele.getConfigType();
		ElementDictDTO dict = elementFacade.queryElementDictByConfigType(configType);
		if (dict != null) {
			ed.setImgUrl(dict.getImgUrl());
		}
		ed.setElementDictId(dict.getId());
		// 查询实例
		InstDTO inst = instFacade.queryInstByElementId(elementId);
		if (inst != null) {
			ed.setInstId(inst.getId());
			ed.setMargin(inst.getInstMargin());
			ed.setType(inst.getConfigType());
			ed.setPageTabId(inst.getPageTabId());
			// 查询实例关联公司列表
			List<InstCompanyDTO> ics = instFacade.queryInstCompanyListByInstId(inst.getId());
			Long[] icArr = new Long[ics.size()];
			for (int i = 0; i < ics.size(); i++) {
				icArr[i] = ics.get(i).getCompanyId();
			}
			ed.setCompanyIds(icArr);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("element", ed);
		data.put("bannerList", null);
		data.put("suList", null);
		data.put("iconGroup", null);
		data.put("imagetextGroup", null);
		data.put("tagGroup", null);
		if (inst == null) {
			return JsonResult.success(data);
		}
		// 查询实例具体内容
		int ct = configType / 100;
		Long instId = inst.getId();
		switch (ct) {
		case 0:
			// bannerList
			List<BannerInstDTO> bannerInstList = bannerFacade.queryBannerListByInstId(instId);
			// 查询所有banner
			List<BannerVO> bannerList = new ArrayList<>();
			for (BannerInstDTO bi : bannerInstList) {
				BannerDTO banner = bannerFacade.queryBannerById(bi.getBannerId());
				if (banner != null) {
					BannerVO vo = new BannerVO();
					vo.setId(banner.getId());
					vo.setImgUrl(banner.getImgUrl());
					bannerList.add(vo);
				}
			}
			data.put("bannerList", bannerList);
			break;
		case 1:
			// suList
			data.put("suList", addSuList(instId));
			
			break;
		case 2:
			// iconGroup
			IconGroupDTO ig = iconFacade.queryIconGroupByInstId(instId);
			if (ig != null) {
				IconGroupVO igVO = new IconGroupVO();
				igVO.setIconGroupId(ig.getId());
				igVO.setCount(ig.getCount());
				igVO.setTitle(ig.getTitle());
				List<IconVO> iconList = new ArrayList<>();
				List<IconDTO> iconDTOList = iconFacade.queryIconsByGroupId(ig.getId());
				for (IconDTO i : iconDTOList) {
					IconVO icvo = new IconVO();
					icvo.setIconId(i.getId());
					icvo.setIconName(i.getName());
					icvo.setImgUrl(i.getUrl());
					icvo.setSummary(i.getSummary());
					List<IconCompanyDTO> ics = iconFacade.queryIconCompanyByIconId(i.getId());
					List<Long> companyIds = new ArrayList<>();
					for (IconCompanyDTO ic : ics) {
						companyIds.add(ic.getCompanyId());
					}
					icvo.setCompanyIds(companyIds);
					Long linkableId = i.getLinkableId();
					if (linkableId != null) {
						LinkableButtonDTO lbDTO = linkableButtonFacade.queryLinkableButtonById(linkableId);
						if (lbDTO != null) {
							icvo.setLinkId(lbDTO.getLinkId());
							icvo.setLinkParam(lbDTO.getLinkParam());
							
							icvo.setLinkType(lbDTO.getLinkType());
							icvo.setLinkUrl(lbDTO.getLinkUrl());
							//设置 su组跳转页面属性
							if(lbDTO.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
								List<LinkableButtonPageVO> cmsPageList = linkableButtonFacade.queryLinkableButtonPagByLinkableId(linkableId);
								icvo.setLinkableButtonPageList(cmsPageList);
							}
						}
					}
					iconList.add(icvo);
				}
				igVO.setIconList(iconList);
				data.put("iconGroup", igVO);
			}
			break;
		case 3:
			// imagetext
			data.put("imagetextGroup", addImagetextGroupVO(instId, Integer.valueOf(0)));

			break;
		case 4:
			// labelGroup
			ShoppingLabelGroupDTO slgDTO = shoppingLabelFacade.queryShoppingLabelGroupByInstId(instId);
			ShoppingLabelGroupVO slgVO = new ShoppingLabelGroupVO();
			if (slgDTO != null) {
				List<ShoppingLabelDTO> slDTOList = shoppingLabelFacade.queryShoppingLabelListByGroupId(slgDTO.getId());
				List<ShoppingLabelVO> slVOList = new ArrayList<>();
				for (ShoppingLabelDTO dto : slDTOList) {
					ShoppingLabelVO vo = new ShoppingLabelVO();
					vo.setLabelName(dto.getName());
					slVOList.add(vo);
				}
				slgVO.setGroupId(slgDTO.getId());
				slgVO.setImgUrl(slgDTO.getImgUrl());
				slgVO.setLabelList(slVOList);
			}
			data.put("labelGroup", slgVO);
			break;
		case 5:
			// elementList
			Map<String, Object> mixInstForFive = new HashMap<>();
			ImagetextGroupVO imagetextGroup = addImagetextGroupVO(instId, Integer.valueOf(0));
			mixInstForFive.put("suList", addSuList(instId));
			mixInstForFive.put("imagetextGroup", imagetextGroup);
			data.put("mixInstForFive", mixInstForFive);
			break;
		case 6:
			// elementList
			Map<String, Object> mixInstForSix = new HashMap<>();
			ImagetextGroupVO imagetextGroupForNormal = addImagetextGroupVO(instId, Integer.valueOf(0));
			ImagetextGroupVO imagetextGroupForBanner = addImagetextGroupVO(instId, Integer.valueOf(1));
			mixInstForSix.put("suList", addSuList(instId));
			mixInstForSix.put("imagetextGroupForNormal", imagetextGroupForNormal);
			mixInstForSix.put("imagetextGroupForBanner", imagetextGroupForBanner);
			data.put("mixInstForSix", mixInstForSix);
			break;
		}
		return JsonResult.success(data);
	}

	private ImagetextGroupVO addImagetextGroupVO(Long instId, Integer groupType) {
		ImagetextGroupDTO itg = imagetextFacade.queryImagetextGroupByInstId(instId, groupType);
		ImagetextGroupVO itgVO = null;
		if (itg != null) {
			itgVO = new ImagetextGroupVO();
			itgVO.setGroupId(itg.getId());
			itgVO.setType(itg.getType());
			itgVO.setTitle(itg.getTitle());
			List<ImagetextDTO> itDTOList = imagetextFacade.queryImagetextByGroupId(itg.getId());
			List<ImgTxtVO> voList = new ArrayList<>();
			for (ImagetextDTO it : itDTOList) {
				ImgTxtVO itVO = new ImgTxtVO();
				itVO.setImagetextId(it.getId());
				itVO.setImagetextUrl(it.getImgUrl());
				itVO.setImagetextName(it.getName());
				Long lbId = it.getLinkableId();
				if (lbId != null) {
					LinkableButtonDTO lbDTO = linkableButtonFacade.queryLinkableButtonById(lbId);
					if (lbDTO != null) {
						itVO.setLinkId(lbDTO.getLinkId());
						itVO.setLinkParam(lbDTO.getLinkParam());
						itVO.setLinkType(lbDTO.getLinkType());
						itVO.setLinkUrl(lbDTO.getLinkUrl());
//						linkableButtonFacade.queryLinkableButtonPagByLinkableId(lbId);
						itVO.setLinkableButtonPageList(linkableButtonFacade.queryLinkableButtonPagByLinkableId(lbId));
					}
				}
				voList.add(itVO);
			}

			itgVO.setImagetextList(voList);
		}
		return itgVO;
	}
	
	public SuListDetailVO addSuList(Long instId) {
		SuListDTO suList = suListFacade.querySuListByInstId(instId);
		SuListDetailVO vo = null;
		if (suList != null) {
			vo = new SuListDetailVO();
			String bannerUrl = suList.getBannerUrl();
			vo.setBannerUrl(bannerUrl);
			Long linkableId = suList.getLinkableId();
			if (linkableId != null) {
				// 有banner
				LinkableButtonDTO lbDTO = linkableButtonFacade.queryLinkableButtonById(linkableId);
				if (lbDTO != null) {
					LinkableButtonVO lbVO = new LinkableButtonVO();
					lbVO.setId(lbDTO.getId());
					lbVO.setLinkId(lbDTO.getLinkId());
					lbVO.setLinkParam(lbDTO.getLinkParam());
					lbVO.setLinkType(lbDTO.getLinkType());
					lbVO.setLinkUrl(lbDTO.getLinkUrl());
					vo.setLinkableInfo(lbVO);
					lbVO.setLinkableButtonPageList(linkableButtonFacade.queryLinkableButtonPagByLinkableId(linkableId));
				}
			}
			vo.setMaxShow(suList.getMaxShow());
			vo.setSucId(suList.getSucId());
			vo.setSuListId(suList.getId());
			vo.setTitleName(suList.getTitleName());
			vo.setTitleColor(suList.getTitleColor());
		}
		return vo;
	}
	

}
