package com.egeo.components.cms.facade;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.cms.common.DateUtils;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.components.cms.dto.CmsPageCompanyDTO;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.service.read.BannerReadService;
import com.egeo.components.cms.service.read.CmsElementReadService;
import com.egeo.components.cms.service.read.CmsInstCfgReadService;
import com.egeo.components.cms.service.read.CmsInstReadService;
import com.egeo.components.cms.service.read.CmsPageCfgReadService;
import com.egeo.components.cms.service.read.CmsPageCompanyReadService;
import com.egeo.components.cms.service.read.CmsPageReadService;
import com.egeo.components.cms.service.read.CmsPageTabReadService;
import com.egeo.components.cms.service.read.LinkableButtonPageReadService;
import com.egeo.components.cms.service.read.LinkableButtonReadService;
import com.egeo.components.cms.service.read.LinkableParamReadService;
import com.egeo.components.cms.service.write.CmsPageWriteService;
import com.egeo.components.cms.vo.BannerVO;
import com.egeo.components.product.client.CategoryTreeClient;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.vo.MerchantProductVO;
import com.egeo.components.promotion.client.CouponBatchClient;
import com.egeo.components.promotion.client.CouponCompanyClient;
import com.egeo.components.promotion.client.CouponGroupRelClient;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.client.CompanyPageClient;
import com.egeo.components.user.client.InfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class CmsPageFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsPageFacade.class);
	@Resource
	private CmsPageReadService cmsPageReadService;
	
	@Resource
	private CmsPageWriteService cmsPageWriteService;
	
	@Resource
	private CmsInstCfgReadService cmsInstCfgReadService;
	
	@Resource
	private BannerReadService bannerReadService;
	
	@Resource
	private LinkableButtonReadService linkableButtonReadService;
	
	@Autowired
	private StandardProductUnitClient spuReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	
	@Autowired
	private StandardUnitCompanyClient suCompanyReadService;
	
	@Resource
	private CmsPageCfgReadService cmsPageCfgReadService;
	
	@Resource
	private LinkableButtonPageReadService linkableButtonPageReadService;
	
	@Resource
	private LinkableParamReadService linkableParamReadService;
	
	@Autowired
	private StandardUnitClient standardUnitReadService;
	
	@Autowired
	private InfoClient infoReadService;
	
	@Resource
	private CmsInstReadService cmsInstReadService;
	
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	
	@Resource
	private CmsElementReadService cmsElementReadService;
	@Autowired
	private CompanyClient companyReadService;
	@Resource
	private CmsPageCompanyReadService cmsPageCompanyReadService;
	@Autowired
	private UserClient userReadService;
	@Resource
	private StandardUnitCompanyClient standardUnitCompanyReadService;
	@Resource
	private CmsPageTabReadService cmsPageTabReadService;
	@Autowired
	private StoreClient storeReadService;
	@Resource
	private CategoryTreeClient categoryTreeReadService;
	@Resource
	private CompanyPageClient companyPageReadService;
	@Resource
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;
	@Resource
	private CouponBatchClient couponBatchReadService;
	@Resource
	private CouponCompanyClient couponCompanyReadService;
	@Resource
	private CouponGroupRelClient couponGroupRelReadService;
	
	public CmsPageDTO findCmsPageById(CmsPageDTO dto){
		
		return cmsPageReadService.findCmsPageById(dto);
	}

	public PageResult<CmsPageDTO> findCmsPageOfPage(CmsPageDTO dto,Pagination page){
		
		List<CompanyDTO> companyDTOs = null;
		
		if(EmptyUtil.isNotBlank(dto.getCompanyName())) {
			List<Long> companyIds = new ArrayList<>();
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(dto.getCompanyName());
			companyDTOs = companyReadService.findCompanyAllByFuzzyName(companyDTO);
			if (EmptyUtil.isEmpty(companyDTOs)){
				return null;
			}
			companyDTOs.forEach(company->companyIds.add(company.getId()));
			dto.setCompanyIds(companyIds);
		}
		if(EmptyUtil.isEmpty(dto.getCompanyIds())) {
			if(Objects.nonNull(RuntimeContext.cacheUser()) && Objects.nonNull(RuntimeContext.cacheUser().getType())) {
				List<Long> companyIds = new ArrayList<>();
				if(Objects.equals(RuntimeContext.cacheUser().getType().intValue(), 3)
						|| Objects.equals(RuntimeContext.cacheUser().getType().intValue(),4)) {
					companyIds.add(RuntimeContext.cacheUser().getCompanyId());
				}else if(Objects.equals(RuntimeContext.cacheUser().getType().intValue(),2)) {
					//获取所有的公司信息
					CompanyDTO cpyDto = new CompanyDTO();
					cpyDto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
					companyDTOs = companyReadService.findCompanyAll(cpyDto);
					if(EmptyUtil.isNotEmpty(companyDTOs)) {
						companyDTOs.forEach(cpy->companyIds.add(cpy.getId()));
					}
					companyIds.add(companyCoreReadService.findCompanyAllIdByCompanyType(0));	// 默认公司配置
				}
				dto.setCompanyIds(companyIds);
			}else {
				throw new BusinessException("请重新登录");
			}
		}
		
		return cmsPageReadService.findCmsPageOfPage(dto, page);
		
	}
	
	
	public Map<Long, String> findCompanyByIdList(List<Long> ids){
		Map<Long, String> map = new HashMap<>();
		if(ids != null && ids.size() > 0) {
			List<CompanyDTO> companys = companyReadService.findCompanyByCompanyIds(com.egeo.utils.StringUtils.longsToStrings(ids));
			for (CompanyDTO companyDTO : companys) {
				map.put(companyDTO.getId(), companyDTO.getCompanyName());
			}
		}
		return map;
	}
	
	public Map<Long, String> findUserByIdList(List<Long> ids){
		Map<Long, String> map = new HashMap<>();
		if(ids != null && ids.size() > 0) {
			List<UserDTO> userDTOs = userReadService.queryUserByIds(com.egeo.utils.StringUtils.longsToStrings(ids));
			for (UserDTO userDTO : userDTOs) {
				map.put(userDTO.getId(), userDTO.getLoginName());
			}
		}
		return map;
	}

	public List<CmsPageDTO> findCmsPageAll(CmsPageDTO dto){
		
		return cmsPageReadService.findCmsPageAll(dto);
		
	}

	public Long insertCmsPageWithTx(CmsPageDTO dto, String configJson){
		
		return cmsPageWriteService.insertCmsPageWithTx(dto, configJson);
	}

	public int updateCmsPageWithTx(CmsPageDTO dto, String configJson){
		
		return cmsPageWriteService.updateCmsPageWithTx(dto, configJson);
	}

	public int deleteCmsPageWithTx(CmsPageDTO dto){
		
		return cmsPageWriteService.deleteCmsPageWithTx(dto);
		
	}
	
	public List<CmsInstCfgDTO> findPageInstCfgByPageId(Long pageId, List<Long> instIdList) {
		return cmsInstCfgReadService.findPageInstCfgByPageId(pageId, instIdList);
	} 

	public BannerVO findBannerByIdAndCompanyId(Long bannerId ,Long clientId, Long companyId, Long platformId) {
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findEnterpriseCompanyAllId(companyId);
		BannerDTO dto = bannerReadService.findBannerByIdAndCompanyId(bannerId, companyId, companyAllId);
		BannerVO vo = null;
		if (dto != null) {
			Map<String, Object> linkMap = new HashMap<>();
			vo = new BannerVO();
			vo.setId(dto.getId());
			vo.setImgUrl(dto.getImgUrl());
			vo.setLinkType(6);// 默认给出无效果
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = linkableButtonReadService.findLinkableButtonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long linkId = lb.getLinkId();
					vo.setLinkId(linkId);
					if (linkType != null && (linkType == CmsConstant.CMS_LINK_TYPE_LOCAL_PARAM 
		    				|| linkType == CmsConstant.CMS_LINK_TYPE_OPERATE)) {
						LinkableParamDTO lp = new LinkableParamDTO();
						lp.setLinkButtonId(linkableId);
						List<LinkableParamDTO> linkableParamList = linkableParamReadService.findLinkableParamAll(lp);
						if (EmptyUtil.isNotEmpty(linkableParamList)) {
							for (LinkableParamDTO linkableParam : linkableParamList) {
								linkMap.put(linkableParam.getName(), linkableParam.getValue());
							}
						}
		        		linkAuth(linkMap, linkType, linkId, companyId, platformId);
		    		} else if (linkType != null && linkType == CmsConstant.CMS_LINK_TYPE_SU_SINGLE) {
						// 单个商品查询spu模板
						StandardProductUnitDTO spu = spuReadService.querySpuBySuId(linkId);
						
						vo.setSuTmplId(spu==null?null:spu.getCommodityTemplateId());
						
						// 查询单个商品与当前公司、当前客户端是否有关系
				        Integer companyType = null;
				    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
				    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
				    	}else {
				    		companyType = RuntimeContext.cacheUser().getCompanyType();
				    	}
						Boolean companyAvailable = suCompanyReadService.querySuCompanyAvailable(linkId,companyId,clientId,companyType);
						vo.setSuCompanyAvailable(companyAvailable);
						linkMap.put("available", companyAvailable);
					} else if (linkType != null && linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
						Integer clientType = 6;
						if (clientId.equals(1L) || clientId.equals(2L)) {
							clientType = 6;
						}
						if (clientId.equals(3L)) {
							clientType = 5;
						}
						LinkableButtonPageDTO linkableButtonPageDTO = new LinkableButtonPageDTO();
						linkableButtonPageDTO.setLinkableId(linkableId);
						linkableButtonPageDTO.setClientType(clientType);
						List<LinkableButtonPageDTO> linkableButtonPageList = linkableButtonPageReadService.findLinkableButtonPageAll(linkableButtonPageDTO);
						Long cmsPageId = null;
						if (EmptyUtil.isNotEmpty(linkableButtonPageList) && linkableButtonPageList.get(0) != null && linkableButtonPageList.get(0).getCmsPageId() != null) {
							cmsPageId = linkableButtonPageList.get(0).getCmsPageId();
						}
						if (PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)) {
							if (clientType == 5) {
								vo.setCmsPageId(EmptyUtil.isEmpty(cmsPageId) ? CmsConstant.CMS_DEFAULT_PAGE_SU_2 : cmsPageId);
							} else if (clientType == 6) {
								vo.setCmsPageId(EmptyUtil.isEmpty(cmsPageId) ? CmsConstant.CMS_DEFAULT_PAGE_SU_1 :  cmsPageId);
							}
						} else if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
							if (clientType == 5) {
								vo.setCmsPageId(EmptyUtil.isEmpty(cmsPageId) ? CmsConstant.CMS_DEFAULT_PAGE_SU_10 : cmsPageId);
							} else if (clientType == 6) {
								vo.setCmsPageId(EmptyUtil.isEmpty(cmsPageId) ? CmsConstant.CMS_DEFAULT_PAGE_SU_9 :  cmsPageId);
							}
						}
						linkMap.put("available", true);
		    	        linkMap.put("msg", null);
					} else {
		    			linkMap.put("available", true);
		    			linkMap.put("msg", null);
		    			vo.setSuCompanyAvailable(true);
		    		}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
					vo.setExtParam1(linkMap.get("extParam1") == null ? null : linkMap.get("extParam1").toString());
					vo.setExtParam2(linkMap.get("extParam2") == null ? null : linkMap.get("extParam2").toString());
					vo.setExtParam3(linkMap.get("extParam3") == null ? null : linkMap.get("extParam3").toString());
					vo.setMsg(linkMap.get("msg") == null ? null : linkMap.get("msg").toString());
					vo.setAvailable(linkMap.get("available") == null ? false : Boolean.valueOf(linkMap.get("available").toString()));
					vo.setLinkId(linkMap.get("linkId") == null ? linkId : toLong(linkMap.get("linkId")));
				}
			}
		}
		return vo;
	}
	
	public Map<String, Object> findLinkableButtonConfig(Long linkableId ,Long clientId, Long companyId, Long platformId) {
		Map<String, Object> linkMap = new HashMap<>();
		Integer linkType = null;
        Long linkId = null;
        String linkUrl = null;
        String linkParam = null;
        Long cmsPageId = null;
        Long suTmplId = null;
        boolean available = false;
        String msg = null;
        if (linkableId != null) {
        	LinkableButtonDTO linkableButton = linkableButtonReadService.findLinkableButtonById(linkableId);
        	linkType = linkableButton.getLinkType();
        	linkUrl = linkableButton.getLinkUrl();
        	linkParam = linkableButton.getLinkParam();
        	linkId = linkableButton.getLinkId();
        	if (linkType != null && (linkType == CmsConstant.CMS_LINK_TYPE_LOCAL_PARAM 
    				|| linkType == CmsConstant.CMS_LINK_TYPE_OPERATE)) {
    			LinkableParamDTO dto = new LinkableParamDTO();
    			dto.setLinkButtonId(linkableId);
    			List<LinkableParamDTO> linkableParamList = linkableParamReadService.findLinkableParamAll(dto);
    			if (EmptyUtil.isNotEmpty(linkableParamList)) {
    				for (LinkableParamDTO linkableParam : linkableParamList) {
    					linkMap.put(linkableParam.getName(), linkableParam.getValue());
    				}
    			}
    			linkAuth(linkMap, linkType, linkId, companyId, platformId);
    		} else if (linkType != null && linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
    			Integer clientType = 6;
				if (clientId.equals(1L) || clientId.equals(2L)) {
					clientType = 6;
				}
				if (clientId.equals(3L)) {
					clientType = 5;
				}
				LinkableButtonPageDTO linkableButtonPageDTO = new LinkableButtonPageDTO();
				linkableButtonPageDTO.setLinkableId(linkableId);
				linkableButtonPageDTO.setClientType(clientType);
				List<LinkableButtonPageDTO> linkableButtonPageList = linkableButtonPageReadService.findLinkableButtonPageAll(linkableButtonPageDTO);
				if (PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)) {
					if (clientType == 5) {
						cmsPageId = EmptyUtil.isEmpty(linkableButtonPageList) ? CmsConstant.CMS_DEFAULT_PAGE_SU_2 : linkableButtonPageList.get(0).getCmsPageId();
					} else if (clientType == 6) {
						cmsPageId = EmptyUtil.isEmpty(linkableButtonPageList) ? CmsConstant.CMS_DEFAULT_PAGE_SU_1 :  linkableButtonPageList.get(0).getCmsPageId();
					}
				} else if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
					if (clientType == 5) {
						cmsPageId = EmptyUtil.isEmpty(linkableButtonPageList) ? CmsConstant.CMS_DEFAULT_PAGE_SU_10 : linkableButtonPageList.get(0).getCmsPageId();
					} else if (clientType == 6) {
						cmsPageId = EmptyUtil.isEmpty(linkableButtonPageList) ? CmsConstant.CMS_DEFAULT_PAGE_SU_9 :  linkableButtonPageList.get(0).getCmsPageId();
					}
				}
				linkMap.put("available", true);
    	        linkMap.put("msg", null);
    		} else if (linkType != null && linkType == CmsConstant.CMS_LINK_TYPE_SU_SINGLE) {
    			// 单个商品查询spu模板

    	        Integer companyType = null;
    	    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    	    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	    	}else {
    	    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	    	}
    			available = suCompanyReadService.querySuCompanyAvailable(linkId,companyId,clientId,companyType);
    			if (available) {
    				StandardProductUnitDTO spu = spuReadService.querySpuBySuId(linkId);
    				suTmplId = spu == null ? null : spu.getCommodityTemplateId();
    				linkMap.put("available", available);
    		        linkMap.put("msg", null);
    			} else {
    				linkMap.put("available", available);
    		        linkMap.put("msg", msg);
    			}
    		} else {
    			linkMap.put("available", true);
    			linkMap.put("msg", null);
    		}
        } else {
        	linkMap.put("available", true);
	        linkMap.put("msg", null);
        }
        //门店首页不存在 跳转到商城首页
        if(linkMap.get("linkId") == null) {
        	linkMap.put("linkId", linkId);
        }
        linkMap.put("linkType", linkType);
        
        linkMap.put("linkUrl", linkUrl);
        linkMap.put("linkParam", linkParam);
        linkMap.put("cmsPageId", cmsPageId);
        linkMap.put("suTmplId", suTmplId);
        return linkMap;
	}
	
	private void linkAuth(Map<String, Object> linkMap, Integer linkType, Long linkId, Long companyId, Long platformId) {
		if (EmptyUtil.isEmpty(linkType) || EmptyUtil.isEmpty(linkId) || EmptyUtil.isEmpty(platformId)) {
			linkMap.put("available", false);
			linkMap.put("msg", null);
		} else if (linkType == CmsConstant.CMS_LINK_TYPE_LOCAL_PARAM) {
			if (linkId.equals(17L) || linkId.equals(18L) || linkId.equals(63L) || linkId.equals(64L)) {
				//运营活动页，广告页鉴权
				checkCmsPageAvaiable(linkMap, companyId, "该活动已结束");
			} else if (linkId.equals(19L) || linkId.equals(65L)) {
				//公司介绍页，默认无效果
				linkMap.put("available", false);
				linkMap.put("msg", null);
			} else if (linkId.equals(20L) || linkId.equals(66L)) {
				//门店首页
				checkStoreAvaiable(linkMap, null,platformId);
			} else if (linkId.equals(71L) || linkId.equals(25L)) {
				//前台类目树
				checkCategoryTreeAvaiable(linkMap, companyId, null,platformId);
			} else if (linkId.equals(51L)) {
				//保险订单
				checkCompanyPageAvaiable(linkMap, companyId, 2, "贵公司未在大厨管家开通该功能，请贵公司联系大厨管家");
			} else if (linkId.equals(52L) || linkId.equals(84L)) {
				//我的体检
				checkCompanyPageAvaiable(linkMap, companyId, 3, "暂未开通该功能");
			} else {
				linkMap.put("available", true);
				linkMap.put("msg", null);
			}
		} else if (linkType == CmsConstant.CMS_LINK_TYPE_OPERATE) {
			if (linkId.equals(1L) || linkId.equals(6L)) {
				//搜索关键词
				linkMap.put("available", true);
				linkMap.put("msg", null);
			} else if (linkId.equals(2L) || linkId.equals(3L) || linkId.equals(7L) || linkId.equals(8L)) {
				//加入购物车，立即购买
				checkBuyPU(linkMap, companyId, "来晚一步，商品已售罄");
			} else if (linkId.equals(4L) || linkId.equals(5L) || linkId.equals(9L) || linkId.equals(10L)) {
				//立即领取，立即领用
				checkReceiveCoupon(linkMap, companyId, "来晚一步，优惠券已领完");
			}
		}
	}
	
	private void checkReceiveCoupon(Map<String, Object> linkMap, Long companyId, String errorMsg) {
		Object extParam1 = linkMap.get("extParam1");
		if (EmptyUtil.isNotEmpty(extParam1)) {
			Long couponBatchId = Long.parseLong(extParam1.toString());
			CouponBatchDTO findCouponBatch = new CouponBatchDTO();
			findCouponBatch.setId(couponBatchId);
			CouponBatchDTO couponBatch = couponBatchReadService.findCouponBatchById(findCouponBatch);
			if (couponBatch != null && couponBatch.getIsEffect() != null && couponBatch.getIsEffect() == 0) {
				boolean avaiable = false;
				Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
				if (couponBatch.getCouponRelType() == 1) {
					//优惠券组
					CouponGroupRelDTO findCouponGroupRel = new CouponGroupRelDTO();
					findCouponGroupRel.setCouponGroupId(couponBatch.getCouponRelId());
					List<CouponGroupRelDTO> couponGroupRelList = couponGroupRelReadService.findCouponGroupRelAll(findCouponGroupRel);
					for (CouponGroupRelDTO couponGroupRel : couponGroupRelList) {
						avaiable = checkSingleCoupon(couponGroupRel.getCouponId(), companyId, companyAllId);
						if (avaiable) {
							break;
						}
					}
				} else {
					avaiable = checkSingleCoupon(couponBatch.getCouponRelId(), companyId, companyAllId);
				}
				if (avaiable) {
					linkMap.put("available", true);
					linkMap.put("msg", null);
				} else {
					linkMap.put("available", false);
					linkMap.put("msg", errorMsg);
				}
			} else {
				linkMap.put("available", false);
				linkMap.put("msg", errorMsg);
			}
//			
		}
	}
	
	private boolean checkSingleCoupon(Long couponId, Long companyId, Long companyAllId) {
		CouponCompanyDTO dto = new CouponCompanyDTO();
		dto.setCouponId(couponId);
		List<CouponCompanyDTO> couponCompanyList = couponCompanyReadService.findCouponCompanyAll(dto);
		for (CouponCompanyDTO cc : couponCompanyList) {
			if (cc.getCompanyId() != null && (cc.getCompanyId().equals(companyId) || cc.getCompanyId().equals(companyAllId))) {
				return true;
			}
		}
		return false;
	}
	
	private void checkBuyPU(Map<String, Object> linkMap, Long companyId, String errorMsg) {
		Object extParam1 = linkMap.get("extParam1");
		Object extParam2 = linkMap.get("extParam2");
		if (EmptyUtil.isNotEmpty(extParam1) && EmptyUtil.isNotEmpty(extParam2)) {
			Long puId = Long.parseLong(extParam1.toString());
			Integer num = Integer.parseInt(extParam2.toString());
			CommodityProductUnitDTO findCommodityProduct = new CommodityProductUnitDTO();
			findCommodityProduct.setId(puId);
			CommodityProductUnitDTO commodityProduct = commodityProductUnitReadService.findCommodityProductUnitById(findCommodityProduct);
			if (commodityProduct != null && commodityProduct.getIsVendible() == 1) {
				//找到PU且PU可售
				boolean avaiable = true;
				StandardUnitCompanyDTO findStandardUnit = new StandardUnitCompanyDTO();
				findStandardUnit.setStandardUnitId(commodityProduct.getStandardUnitId());
				findStandardUnit.setCompanyId(companyId);
				List<StandardUnitCompanyDTO> standardUnitList1 = standardUnitCompanyReadService.findStandardUnitCompanyAll(findStandardUnit);
				if (EmptyUtil.isEmpty(standardUnitList1)) {
					avaiable = false;
					//用户公司查询商品为空
					Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
					findStandardUnit.setCompanyId(companyAllId);
					List<StandardUnitCompanyDTO> standardUnitList2 = standardUnitCompanyReadService.findStandardUnitCompanyAll(findStandardUnit);
					if (EmptyUtil.isNotEmpty(standardUnitList2)) {
						avaiable = true;
					}
				}
				if (avaiable) {
					//校验库存
					CommodityProductUnitWarehouseStockDTO puStock = commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(puId);
					if (puStock.getRealStockNum() >= num) {
						linkMap.put("available", true);
						linkMap.put("msg", null);
						
					} else {
						linkMap.put("available", false);
						linkMap.put("msg", errorMsg);
					}
				} else {
					linkMap.put("available", false);
					linkMap.put("msg", errorMsg);
				}
			} else {
				linkMap.put("available", false);
				linkMap.put("msg", errorMsg);
			}
		} else {
			linkMap.put("available", false);
			linkMap.put("msg", errorMsg);
		}
	}
	
	private void checkCmsPageAvaiable(Map<String, Object> linkMap, Long companyId, String errorMsg) {
		Object extParam1 = linkMap.get("extParam1");
		if (EmptyUtil.isNotEmpty(extParam1)) {
			Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
			Long pageId = Long.parseLong(extParam1.toString());
			CmsPageDTO cmsPageDTO = new CmsPageDTO();
			cmsPageDTO.setId(pageId);
			cmsPageDTO = cmsPageReadService.findCmsPageById(cmsPageDTO);
			if(cmsPageDTO != null && cmsPageDTO.getPageStatus() == 1) {
				linkMap.put("available", false);
				linkMap.put("msg", errorMsg);
				return;
			}
			CmsPageCompanyDTO pageCompany = new CmsPageCompanyDTO();
			pageCompany.setPageId(pageId);
			pageCompany.setCompanyId(companyAllId);
			List<CmsPageCompanyDTO> pageCompanyList1 = cmsPageCompanyReadService.findCmsPageCompanyAll(pageCompany);
			if (EmptyUtil.isEmpty(pageCompanyList1)) {
				pageCompany.setCompanyId(companyId);
				List<CmsPageCompanyDTO> pageCompanyList2 = cmsPageCompanyReadService.findCmsPageCompanyAll(pageCompany);
				if (EmptyUtil.isEmpty(pageCompanyList2)) {
					linkMap.put("available", false);
					linkMap.put("msg", errorMsg);
				} else {
					linkMap.put("available", true);
					linkMap.put("msg", null);
				}
			} else {
				linkMap.put("available", true);
				linkMap.put("msg", null);
			}
		} else {
			linkMap.put("available", false);
			linkMap.put("msg", errorMsg);
		}
	}
	
	private void checkStoreAvaiable(Map<String, Object> linkMap, String errorMsg,Long platformId) {
		Object extParam1 = linkMap.get("extParam1");
		if (EmptyUtil.isNotEmpty(extParam1)) {
			Long storeId = Long.parseLong(extParam1.toString());
			StoreDTO store = new StoreDTO();
			store.setId(storeId);
			StoreDTO storeDTO = storeReadService.findStoreById(store);
			if (storeDTO != null && storeDTO.getHasStoreMenu() != null && storeDTO.getHasStoreMenu().equals(1L)) {
				linkMap.put("available", true);
				linkMap.put("msg", null);
			} else {
				//鉴权失败进入 平台首页
				CmsPageDTO dto = new CmsPageDTO();
				dto.setPageStatus(0);
				dto.setPlatformId(platformId);
				dto.setTemplateType(2);
				List<CmsPageDTO> list = cmsPageReadService.findCmsPageAllByClientType(dto);
				
				if(list != null && list.size() > 0) {
					linkMap.put("extParam1", list.get(0).getId());
					if(platformId == CmsConstant.CMS_PLATFORM_FGJ) {
						linkMap.put("linkId", 12);
					}else {
						linkMap.put("linkId", 58);
					}
				}
				linkMap.put("available", true);
				linkMap.put("msg", null);
			}
		} else {
			linkMap.put("available", false);
			linkMap.put("msg", errorMsg);
		}
	}
	
	private void checkCategoryTreeAvaiable(Map<String, Object> linkMap, Long companyId, String errorMsg,Long platformId) {
		Object extParam1 = linkMap.get("extParam1");
		if (EmptyUtil.isNotEmpty(extParam1)) {

	        Integer companyType = null;
	    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
	    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
	    	}else {
	    		companyType = RuntimeContext.cacheUser().getCompanyType();
	    	}
			Long categoryTreeId = Long.parseLong(extParam1.toString());
			CategoryTreeDTO categoryTreeDTO = categoryTreeReadService.findByCategoryTreeId(categoryTreeId);
			if (categoryTreeDTO != null && companyType == categoryTreeDTO.getCompanyType() && categoryTreeDTO.getStartUsing() == 1) {
				linkMap.put("available", true);
				linkMap.put("msg", null);
			} else {
				//鉴权失败进入启用中的分类页
				CategoryTreeDTO dto = new CategoryTreeDTO();
				dto.setPlatformId(platformId);
				dto.setStartUsing(1);
				List<CategoryTreeDTO> list = categoryTreeReadService.findCategoryAll(categoryTreeDTO);
				
				if(list != null  &&list.size() > 0) {
					categoryTreeId = list.get(0).getId();
				}
				linkMap.put("available", true);
				linkMap.put("extParam1",categoryTreeId);
				linkMap.put("msg", errorMsg);
			}
		} else {
			linkMap.put("available", false);
			linkMap.put("msg", errorMsg);
		}
	}
	
	private void checkCompanyPageAvaiable(Map<String, Object> linkMap, Long companyId, Integer type, String errorMsg) {
		CompanyPageDTO dto = new CompanyPageDTO();
		dto.setCompanyId(companyId);
		dto.setType(type);
		List<CompanyPageDTO> companyPageList = companyPageReadService.findCompanyPageAll(dto);
		if (EmptyUtil.isNotEmpty(companyPageList)) {
			linkMap.put("available", true);
			linkMap.put("msg", null);
		} else {
			linkMap.put("available", false);
			linkMap.put("msg", errorMsg);
		}
	}
	
	public List<CmsPageDTO> findCmsPageAllByClientType(CmsPageDTO dto){
		
		return cmsPageReadService.findCmsPageAllByClientType(dto);
	}
	
	public List<CmsPageCfgDTO> findPageCfgByPageId(Long pageId) {
		return cmsPageCfgReadService.findPageCfgByPageId(pageId);
	}
	
	public int findSupportPageByVersionCode(Long pageId, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode) {
		return cmsPageReadService.findSupportPageByVersionCode(pageId, platformId, androidVersionCode, iosVersionCode);
	}
	
	public Long findSupportPageByPageTypeAndVersionCode(Integer pageType, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode, Long companyId) {
		Long companyAllId = companyCoreReadService.findEnterpriseCompanyAllId(companyId);
		return cmsPageReadService.findSupportPageByPageTypeAndVersionCode(pageType, platformId, androidVersionCode, iosVersionCode, companyId, companyAllId);
	}

	public List<Map<String, Object>> findSuList(String json, Long companyId) {
		List<Map<String, Object>> standardUnitList = new ArrayList<Map<String,Object>>();
		List<MerchantProductVO> products = JSON.parseArray(json, MerchantProductVO.class);
        Integer companyType = null;
        if(products==null || products.size()==0) {
        	return standardUnitList;
        }
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
    	List<Long> standardUnitIdList = new ArrayList<>();
    	for(MerchantProductVO product:products) {
    		standardUnitIdList.add(product.getId());
    	}
    	/*List<Long> suIdList = findCompanySuIdList(companyId,standardUnitIdList);*/
    	Map<Long, Object> pictureMap = findPictureInfo(standardUnitIdList); 
    	Map<Long, Object> spuMap = findSpuInfo(standardUnitIdList);

		Map<Long, CommodityProductUnitDTO> puMap = findPuInfoBySuId(companyType, standardUnitIdList);
		for (MerchantProductVO standardUnitDTO : products) {
			if (!standardUnitIdList.contains(standardUnitDTO.getId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("standardUnitId", standardUnitDTO.getId());
			map.put("stockWarning", standardUnitDTO.getStockWarning());
			map.put("name", standardUnitDTO.getName());
			map.put("pictureUrl", pictureMap.get(standardUnitDTO.getId()));
			map.put("salePrice", settingSalePrice(standardUnitDTO, companyType));
			map.put("marketPrice", standardUnitDTO.getMarketPrice());
			map.put("commodityTemplateId", spuMap.get(standardUnitDTO.getId()));
			
			//增加价格范围
			CommodityProductUnitDTO pu = puMap.get(standardUnitDTO.getId());
			if (pu != null) {
				map.put("puId", pu.getPuId());
				map.put("puSalePrice", pu.getPuSalePrice());
				map.put("maxSalePrice", pu.getMaxSalePrice());
				map.put("minSalePrice", pu.getMinSalePrice());
			}
			
			//				map.put("status", standardUnitDTO.getStatus());
			Boolean showOwnMerchant = false;
			/*CmsInstCfgDTO cic = new CmsInstCfgDTO();
			cic.setCmsInstId(cmsInstCfg.getCmsInstId());
			cic.setCmsCfgKeyId(204L);*/
			/*List<CmsInstCfgDTO> cicList = cmsInstCfgReadService.findCmsInstCfgAll(cic);
			if (EmptyUtil.isNotEmpty(cicList)) {
				showOwnMerchant = "1".equals(cicList.get(0).getCkgKeyCode());
			}*/
			
			map.put("saleWay", standardUnitDTO.getSaleWay());
			if(showOwnMerchant && standardUnitDTO.getMerchantId().equals(1L)){
				map.put("isOwnMerchant", 1);
			}else{
				map.put("isOwnMerchant",0 );
			}
			//购买方式
			map.put("buyType",standardUnitDTO.getBuyType());
			standardUnitList.add(map);
		}
		return standardUnitList;
	}
	public List<Map<String, Object>> findSuList(Long sucId, Long platformId, Long companyId, CmsInstCfgDTO cmsInstCfg) {
		List<Map<String, Object>> standardUnitList = new ArrayList<Map<String,Object>>();
		if (sucId != null) {

	        Integer companyType = null;
	    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
	    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
	    	}else {
	    		companyType = RuntimeContext.cacheUser().getCompanyType();
	    	}
			List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
					.findByStandardUnitCombinationId(sucId, platformId);
			
			Map<Long, CommodityProductUnitDTO> puMap = findPuInfo(companyType, standardUnitDTOList);
			 
			if (EmptyUtil.isNotEmpty(standardUnitDTOList)) {
				List<Long> standardUnitIdList = new ArrayList<>();
				for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
					standardUnitIdList.add(standardUnitDTO.getId());
				}
				List<Long> suIdList = findCompanySuIdList(companyId,standardUnitIdList);
				Map<Long, Object> pictureMap = findPictureInfo(suIdList); 
				Map<Long, Object> spuMap = findSpuInfo(suIdList);
				
				for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
					if (!suIdList.contains(standardUnitDTO.getId())) {
						continue;
					}
					Map<String, Object> map = new HashMap<>();
					map.put("standardUnitId", standardUnitDTO.getId());
					map.put("stockWarning", standardUnitDTO.getStockWarning());
					map.put("name", standardUnitDTO.getName());
					map.put("pictureUrl", pictureMap.get(standardUnitDTO.getId()));
					map.put("salePrice", settingSalePrice(standardUnitDTO, companyType));
					map.put("marketPrice", standardUnitDTO.getMarketPrice());
					map.put("commodityTemplateId", spuMap.get(standardUnitDTO.getId()));
					map.put("source", standardUnitDTO.getSource()==null?1:standardUnitDTO.getSource());
					//增加价格范围
					CommodityProductUnitDTO pu = puMap.get(standardUnitDTO.getId());
					if (pu != null) {
						map.put("puId", pu.getPuId());
						map.put("puSalePrice", pu.getPuSalePrice());
						map.put("maxSalePrice", pu.getMaxSalePrice());
						map.put("minSalePrice", pu.getMinSalePrice());
					}
					
					//				map.put("status", standardUnitDTO.getStatus());
					Boolean showOwnMerchant = false;
					CmsInstCfgDTO cic = new CmsInstCfgDTO();
					cic.setCmsInstId(cmsInstCfg.getCmsInstId());
					cic.setCmsCfgKeyId(204L);
					List<CmsInstCfgDTO> cicList = cmsInstCfgReadService.findCmsInstCfgAll(cic);
					if (EmptyUtil.isNotEmpty(cicList)) {
						showOwnMerchant = "1".equals(cicList.get(0).getCkgKeyCode());
					}
					
					map.put("saleWay", standardUnitDTO.getSaleWay());
					if(showOwnMerchant && standardUnitDTO.getMerchantId().equals(1L)){
						map.put("isOwnMerchant", 1);
					}else{
						map.put("isOwnMerchant",0 );
					}
					//购买方式
					map.put("buyType",standardUnitDTO.getBuyType());
					standardUnitList.add(map);
				}
			}
		}
		return standardUnitList;
	}
	
	private List<Long> findCompanySuIdList(Long companyId, List<Long> standardUnitIdList) {
		List<Long> suIdList = new ArrayList<>();

        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		StandardUnitCompanyDTO dto = new StandardUnitCompanyDTO();
		dto.setCompanyId(companyId);
		dto.setCompanyType(companyType);
		FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO findSuCompanyByCompanyIdAndTypeAndSuIdsDTO = new FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO();
		findSuCompanyByCompanyIdAndTypeAndSuIdsDTO.setDto(dto);
		findSuCompanyByCompanyIdAndTypeAndSuIdsDTO.setSuIds(standardUnitIdList);
		List<StandardUnitCompanyDTO> standardUnitCompanyList1 = standardUnitCompanyReadService.findSuCompanyByCompanyIdAndTypeAndSuIds(findSuCompanyByCompanyIdAndTypeAndSuIdsDTO);
		
		if (EmptyUtil.isNotEmpty(standardUnitCompanyList1)) {
			for (StandardUnitCompanyDTO suc : standardUnitCompanyList1) {
				suIdList.add(suc.getStandardUnitId());
			}
		}
		return suIdList;
	}

	private Map<Long, Object> findSpuInfo(List<Long> suIdList) {
		Map<Long, Object> map = new HashMap<>();
		if(suIdList != null && suIdList.size() > 0) {
			List<Map<String, Object>>  listMap = standardUnitReadService.findSpuInfo(com.egeo.utils.StringUtils.longsToStrings(suIdList));
			if(listMap != null && listMap.size() > 0) {
				for (Map<String, Object> map2 : listMap) {
					map.put(toLong(map2.get("suId")), map2.get("commodityTemplateId"));
				}
			}
		}
		
		return map;
	}

	private Map<Long, Object> findPictureInfo(List<Long> suIdList) {
		Map<Long, Object> map = new HashMap<>();
		if(suIdList != null && suIdList.size() > 0) {
			List<Map<String, Object>>  listMap = standardUnitReadService.findPictureInfo(com.egeo.utils.StringUtils.longsToStrings(suIdList));
			if(listMap != null && listMap.size() > 0) {
				for (Map<String, Object> map2 : listMap) {
					map.put(toLong(map2.get("suId")), map2.get("pictureUrl"));
				}
			}
		}
		
		return map;
	}
	private Long toLong(Object src) {
		if(src !=null) {
			if(src instanceof Integer) {
				return ((Integer)src).longValue();
			}else if(src instanceof String) {
				return Long.parseLong((String) src);
			}else if(src instanceof Long) {
				return (Long)src;
			}
		}
		return  null;
	}
	private List<Long> findCompanySuIdList(Long companyId) {
		List<Long> suIdList = new ArrayList<>();

        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		StandardUnitCompanyDTO dto = new StandardUnitCompanyDTO();
		dto.setCompanyId(companyId);
		List<StandardUnitCompanyDTO> standardUnitCompanyList1 = standardUnitCompanyReadService.findStandardUnitCompanyAll(dto);
		dto.setCompanyId(-1L);
		dto.setCompanyType(companyType);
		List<StandardUnitCompanyDTO> standardUnitCompanyList2 = standardUnitCompanyReadService.findStandardUnitCompanyAll(dto);
		if (EmptyUtil.isNotEmpty(standardUnitCompanyList1)) {
			for (StandardUnitCompanyDTO suc : standardUnitCompanyList1) {
				suIdList.add(suc.getStandardUnitId());
			}
		}
		if (EmptyUtil.isNotEmpty(standardUnitCompanyList2)) {
			for (StandardUnitCompanyDTO suc : standardUnitCompanyList2) {
				suIdList.add(suc.getStandardUnitId());
			}
		}
		return suIdList;
	}
	
	private Map<Long, CommodityProductUnitDTO> findPuInfo(Integer companyType, List<StandardUnitDTO> standardUnitDTOList) {
		Map<Long, CommodityProductUnitDTO> puMap = new HashMap<>();
		if (EmptyUtil.isNotEmpty(standardUnitDTOList)) {
			List<Long> suIds = new ArrayList<>();
			for (StandardUnitDTO su : standardUnitDTOList) {
				suIds.add(su.getId());
			}
			List<CommodityProductUnitDTO> puList = commodityProductUnitReadService.findPuInfoBySuIdList(companyType, com.egeo.utils.StringUtils.longsToStrings(suIds));
			for (CommodityProductUnitDTO pu : puList) {
				puMap.put(pu.getStandardUnitId(), pu);
			}
		}
		return puMap;
	}
	private Map<Long, CommodityProductUnitDTO> findPuInfoBySuId(Integer companyType, List<Long> standardUnitIdList) {
		Map<Long, CommodityProductUnitDTO> puMap = new HashMap<>();
		if (EmptyUtil.isNotEmpty(standardUnitIdList)) {
			List<CommodityProductUnitDTO> puList = commodityProductUnitReadService.findPuInfoBySuIdList(companyType, com.egeo.utils.StringUtils.longsToStrings(standardUnitIdList));
			for (CommodityProductUnitDTO pu : puList) {
				puMap.put(pu.getStandardUnitId(), pu);
			}
		}
		return puMap;
	}
	
	/**
	 * 设置su商品销售价格
	 * @param standardUnitDTO2
	 * @param companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 */
	private BigDecimal settingSalePrice(StandardUnitDTO standardUnitDTO2, Integer companyType) {
		switch (companyType) {
		case  0:
			return standardUnitDTO2.getSalePrice();
		case  1:
			return standardUnitDTO2.getDemoSalePrice();
		case  2:
			return standardUnitDTO2.getCompetingSalePrice();

		default:
			throw new BusinessException("未定义公司类型");
		}
	}

	private BigDecimal settingSalePrice(MerchantProductVO standardUnitDTO2, Integer companyType) {
		switch (companyType) {
		case  0:
			return standardUnitDTO2.getSalePrice();
		case  1:
			return standardUnitDTO2.getDemoSalePrice();
		case  2:
			return standardUnitDTO2.getCompetingSalePrice();

		default:
			throw new BusinessException("未定义公司类型");
		}
	}
	public List<Map<String, Object>> findUserInfoForMsgBox(Long userId, Map<String, Object> config) {
		List<Map<String, Object>> msgList = new ArrayList<>();
		Integer isRead = null; 
		Integer type = null; 
		Long platformId = null; 
		Date createTime = null; 
		Integer count = null; 
		Object showAllMsgObj = config.get("showAllMsg");
		Object showSystemMsgObj = config.get("showSystemMsg");
		Object showNoticeMsgObj = config.get("showNoticeMsg");
		Object showDuringDaysObj = config.get("showDuringDays");
		Object showAllDaysMsgObj = config.get("showAllDaysMsg");
		Object cycleCountObj = config.get("cycleCount");
		Object showMsgDateObj = config.get("showMsgDate");
		if (EmptyUtil.isNotEmpty(showAllMsgObj)) {
			Integer showAllMsg = Integer.parseInt(showAllMsgObj.toString());
			if (showAllMsg == 1) {
				isRead = 0;
			}
		}
		if (EmptyUtil.isNotEmpty(showSystemMsgObj)) {
			Integer showSystemMsg = Integer.parseInt(showSystemMsgObj.toString());
			if (showSystemMsg == 1) {
				type = 1;
			}
		}
		if (EmptyUtil.isNotEmpty(showNoticeMsgObj)) {
			Integer showNoticeMsg = Integer.parseInt(showNoticeMsgObj.toString());
			if (showNoticeMsg == 1) {
				if (type == null) {
					type = 2;
				} else {
					type = null;
				}
			}
		}
		if (EmptyUtil.isNotEmpty(showDuringDaysObj)) {
			if (EmptyUtil.isEmpty(showAllDaysMsgObj) || Integer.parseInt(showAllDaysMsgObj.toString()) != 1) {
				Integer showDuringDays = Integer.parseInt(showDuringDaysObj.toString());
				createTime = new Date();
				DateUtils.addDays(createTime, -showDuringDays);
			}
		}
		if (EmptyUtil.isNotEmpty(cycleCountObj)) {
			count = Integer.parseInt(cycleCountObj.toString());
		}
		List<InfoDTO> infoDTOList = infoReadService.findUserInfoForMsgBox(userId, isRead, type, platformId, createTime, count);
		if (EmptyUtil.isNotEmpty(infoDTOList)) {
			for (InfoDTO infoDTO : infoDTOList) {
				Map<String, Object> msg = new HashMap<>();
				msg.put("title", infoDTO.getTitle());
				logger.info("userId:{},infoDTO:{}",userId,JSON.toJSONString(infoDTO));
				if (infoDTO.getType() != null && infoDTO.getType() == 1) {
					msg.put("content", infoDTO.getSystemInfo());
				} else {
					msg.put("content", infoDTO.getInfoInform());
				}
				if (EmptyUtil.isNotEmpty(showMsgDateObj) && Integer.parseInt(showMsgDateObj.toString()) == 3) {
					int betweenDays = DateUtils.betweenDays(infoDTO.getCreateTime(), new Date());
					String daysStr = null;
					if (betweenDays == 0) {
						daysStr = "今天";
					} else if (betweenDays == 1) {
						daysStr = "昨天";
					} else if (betweenDays == 2) {
						daysStr = "前天";
					} else if (betweenDays >= 3 && betweenDays < 7) {
						daysStr = "三天前";
					} else if (betweenDays >= 7) {
						daysStr = "一周前";
					}
					msg.put("date", daysStr);
				} else {
					msg.put("date", DateUtils.format("MM月dd日", infoDTO.getCreateTime()));
				}
				logger.info("userId:{},msg:{}",userId,msg);
				msgList.add(msg);
			}
		}
		return msgList;
	}
	
	public CmsElementDTO findCmsElementById(Long elementId) {
		CmsElementDTO dto = new CmsElementDTO();
		dto.setId(elementId);
		return cmsElementReadService.findCmsElementById(dto);
	}
	
	public PageResult<CmsInstDTO> findByPageAndCompanyOfPage(Long pageId, Long companyId, Pagination page) {
		CmsPageDTO findPageDTO = new CmsPageDTO();
		findPageDTO.setId(pageId);
		CmsPageDTO dbPage = cmsPageReadService.findCmsPageByPageId(findPageDTO);

		Long companyAllId = null;
		if (companyId != null && dbPage.getTemplateType() != 1) {
			companyAllId = companyCoreReadService.findEnterpriseCompanyAllId(companyId);
		}
		CmsInstDTO dto = new CmsInstDTO();
		dto.setCmsPageId(pageId);
		return cmsInstReadService.findByPageAndCompanyOfPage(dto, companyId, companyAllId, page);
	}

	public int updateStatus(CmsPageDTO dto) {
		
		return cmsPageWriteService.updateStatusWithTx(dto);
	}
	
	public Long findPageCompanyId(Long pageId) {
		CmsPageCompanyDTO dto = new CmsPageCompanyDTO();
		dto.setPageId(pageId);
		List<CmsPageCompanyDTO> pageCompanyList = cmsPageCompanyReadService.findCmsPageCompanyAll(dto);
		if (EmptyUtil.isNotEmpty(pageCompanyList)) {
			return pageCompanyList.get(0).getCompanyId();
		}
		return null;
	}
	
	public Map<String, Object> findCompanyAndPlatformLogoInfo(Long companyId, Long platformId, Long clientId,Integer pageType) {
		Map<String, Object> logoTitleInfo = new HashMap<>();
		boolean useLogoTitle = false;
		CompanyDTO userCompany = companyReadService.findCompanyById(companyId);
		CompanyDTO platform = null;
		CmsPageTabDTO cmsPageTab = new CmsPageTabDTO();
		cmsPageTab.setCompanyId(companyId);
		cmsPageTab.setPlatformId(platformId);
		
		if(CmsConstant.CMS_PAGE_TYPE_HOME == pageType) {
			cmsPageTab.setType(CmsConstant.CMS_PAGE_TAB_TYPE_HOME);
		}else if(CmsConstant.CMS_PAGE_TYPE_MALL == pageType) {
			cmsPageTab.setType(CmsConstant.CMS_PAGE_TAB_TYPE_MALL);
		}else if(CmsConstant.CMS_PAGE_TYPE_APPLICATION == pageType) {
			cmsPageTab.setType(CmsConstant.CMS_PAGE_TAB_TYPE_APPLICATION);
		}
		
		if (PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)) {
			platform = companyReadService.findCompanyById(3L);
		} else if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
			platform = companyReadService.findCompanyById(2L);
		}
		if (platform != null) {
			
			if(StringUtils.isNotBlank(cmsPageTab.getType())) {
				List<CmsPageTabDTO> cmsPageTabList = cmsPageTabReadService.findCmsPageTabCondition(cmsPageTab);
				if(cmsPageTabList != null && cmsPageTabList.size() > 0) {
					logoTitleInfo.put("showCompanyLogo", cmsPageTabList.get(0).getShowClientLogo());
					logoTitleInfo.put("showPlatformLogo", cmsPageTabList.get(0).getShowPlatformLogo());
					//展示logo 并且 url不为空 则展示
					if((1 == cmsPageTabList.get(0).getShowClientLogo() && EmptyUtil.isNotEmpty(userCompany.getCompanyLogo())) || 
							(1 == cmsPageTabList.get(0).getShowPlatformLogo() && EmptyUtil.isNotEmpty(platform.getCompanyLogo()))) {
						useLogoTitle = true;
					}
				}
			}
			
			logoTitleInfo.put("companyLogoImg", userCompany.getCompanyLogo());
			if (EmptyUtil.isNotEmpty(userCompany.getCompanyLogo()) && EmptyUtil.isNotEmpty(userCompany.getCompanyLinkableId())) {
				logoTitleInfo.put("companyLogoLink", findLinkableButtonConfig(userCompany.getCompanyLinkableId(), clientId, companyId, platformId));
			}
			logoTitleInfo.put("platformLogoImg", platform.getCompanyLogo());
			if (EmptyUtil.isNotEmpty(platform.getCompanyLogo()) && EmptyUtil.isNotEmpty(platform.getCompanyLinkableId())) {
				logoTitleInfo.put("platformLogoLink", findLinkableButtonConfig(platform.getCompanyLinkableId(), clientId, companyId, platformId));
			}
			logoTitleInfo.put("useLogoTitle", useLogoTitle);
		}
		return logoTitleInfo;
	}

	public CmsPageDTO findCmsPageCompanyByPageId(Long id) {
		CmsPageDTO dto = new CmsPageDTO();
		dto.setId(id);
		return cmsPageReadService.findCmsPageByPageId(dto);
	}

	public List<CmsPageDTO> findCmsPageByCompanyIdAndVersion(CmsPageDTO dto) {
		
		return cmsPageReadService.findCmsPageByCompanyIdAndVersion(dto);
	}
	
	public CmsPageDTO findCmsPageConditionById(CmsPageDTO dto){
		
		return cmsPageReadService.findCmsPageByPageId(dto);
	}
}	