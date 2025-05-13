package com.egeo.components.product.business.impl;


import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.condition.MerchantProductUnitCondition;
import com.egeo.components.product.service.read.MerchantProductReadService;
import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.vo.*;
import com.egeo.components.user.client.SupplierClient;
import com.egeo.utils.StringUtils;
import com.egeo.utils.Upload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.common.BusinessConstant;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.product.business.MerchantProdCauseManage;
import com.egeo.components.product.business.MerchantProductManage;
import com.egeo.components.product.converter.MerchantProductConverter;
import com.egeo.components.product.converter.ProductUnitConverter;
import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.facade.FreightTemplateFacade;
import com.egeo.components.product.facade.MerchantFacade;
import com.egeo.components.product.facade.MerchantPictureFacade;
import com.egeo.components.product.facade.MerchantProdClientFacade;
import com.egeo.components.product.facade.MerchantProdDescribeFacade;
import com.egeo.components.product.facade.MerchantProdPictureFacade;
import com.egeo.components.product.facade.MerchantProdSalesRecordFacade;
import com.egeo.components.product.facade.MerchantProductCompanyFacade;
import com.egeo.components.product.facade.MerchantProductFacade;
import com.egeo.components.product.facade.MerchantProductMembershipFacade;
import com.egeo.components.product.facade.MerchantProductStoreFacade;
import com.egeo.components.product.facade.MpSerachKeywordFacade;
import com.egeo.components.product.facade.PictureFacade;
import com.egeo.components.product.facade.ProductFacade;
import com.egeo.components.product.facade.ProductUnitFacade;
import com.egeo.components.product.facade.SellPlatformMerchantProdFacade;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.product.facade.StandardProductUnitAttValueFacade;
import com.egeo.components.product.facade.StandardProductUnitFacade;
import com.egeo.components.product.facade.StandardUnitClientFacade;
import com.egeo.components.product.facade.StandardUnitCompanyFacade;
import com.egeo.components.product.facade.StandardUnitFacade;
import com.egeo.components.product.facade.StoreFacade;
import com.egeo.components.product.vo.MerchantProductShowVO;
import com.egeo.components.product.vo.MerchantProductVO;
import com.egeo.components.product.vo.ProductUnitVO;
import com.egeo.components.product.vo.PuExportVO;
import com.egeo.components.product.vo.StandardUnitExportVO;
import com.egeo.components.user.client.EnterpriseClient;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.LogMsgContentEntityToMap;
import com.egeo.utils.log.LogUtil;
import com.egeo.web.JsonResult;

@Service("merchantProduct")
public class MerchantProductManageImpl implements MerchantProductManage{

	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource(name="merchantProductFacade")
	private MerchantProductFacade merchantProductFacade;

	@Resource(name="pictureFacade")
	private PictureFacade pictureFacade;

	@Resource(name="merchantProdPictureFacade")
	private MerchantProdPictureFacade merchantProdPictureFacade;

	@Resource(name="merchantPictureFacade")
	private MerchantPictureFacade merchantPictureFacade;

	@Resource(name="sellPlatformMerchantProdFacade")
	private SellPlatformMerchantProdFacade sellPlatformMerchantProdFacade;

	@Resource(name="merchantProdClientFacade")
	private MerchantProdClientFacade merchantProdClientFacade;

	@Resource(name="merchantProductCompanyFacade")
	private MerchantProductCompanyFacade merchantProductCompanyFacade;

	@Resource(name="merchantProdDescribeFacade")
	private MerchantProdDescribeFacade merchantProdDescribeFacade;

	@Resource(name="standardUnitFacade")
	private StandardUnitFacade standardUnitFacade;
	
	@Autowired
	private EnterpriseClient enterpriseClient;

	@Autowired
	private SupplierClient supplierClient;

	@Resource(name="standardUnitClientFacade")
	private StandardUnitClientFacade standardUnitClientFacade;

	@Resource(name="standardUnitCompanyFacade")
	private StandardUnitCompanyFacade standardUnitCompanyFacade;

	@Resource(name="skuFacade")
	private SkuFacade skuFacade;

	@Resource(name="productUnitFacade")
	private ProductUnitFacade productUnitFacade;

	@Resource(name="merchantProdSalesRecordFacade")
	private MerchantProdSalesRecordFacade merchantProdSalesRecordFacade;

	@Resource(name="standardProductUnitFacade")
	private StandardProductUnitFacade standardProductUnitFacade;

	@Resource(name="merchantProdCause")
	private MerchantProdCauseManage merchantProdCauseManage;

	@Resource(name="merchantFacade")
	private MerchantFacade merchantFacade;

	@Resource(name="freightTemplateFacade")
	private FreightTemplateFacade freightTemplateFacade;

	@Resource(name="standardProductUnitAttValueFacade")
	private StandardProductUnitAttValueFacade standardProductUnitAttValueFacade;

	@Resource(name="mpSerachKeywordFacade")
	private MpSerachKeywordFacade mpSerachKeywordFacade;
	
	@Resource(name="storeFacade")
	private StoreFacade storeFacade;
	@Resource(name="productFacade")
	private ProductFacade productFacade;
	@Resource(name="merchantProductStoreFacade")
	private MerchantProductStoreFacade merchantProductStoreFacade;
	
	@Resource(name="merchantProductMembershipFacade")
	private MerchantProductMembershipFacade merchantProductMembershipFacade;

	@Resource
	private MerchantProductReadService merchantProductReadService;
	@Resource
	private StoreReadService storeReadService;
	@Autowired
	private Upload uploadService;

	@Override
	public Map<String, Object> findMerchantProductById(MerchantProductDTO dto,CacheUser user,String ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		MerchantProductDTO merchantProductDTO = merchantProductFacade.findMerchantProductById(dto);

		// 创建su显示数据
		MerchantProductShowVO merchantProductShowVO = createMerchantProductShowVO(merchantProductDTO);

		//根据spuid更新supu信息
		merchantProductFacade.updateSuPu(merchantProductDTO.getStandardProductUnitId(),dto.getId(),merchantProductDTO.getPlatformId(),user,ip);

		// 添加图片信息
		setPictureData(dto.getId(),map);
		
		//查询所有所属平台
		List<ClientDTO> clientList = merchantProductFacade.findClientAll(new ClientDTO());
		map.put("clientList", clientList);
		
		// su显示数据公司id集合赋值
		setCompanyIds(merchantProductShowVO,dto.getId(),dto.getPlatformId());
		map.put("merchantProduct", merchantProductShowVO);

		// 组织公司信息并添加
		createCompanys(map,dto.getPlatformId());


		//根据su草稿id查询标签信息集合
		List<MerchantProductTagDTO> merchantProductTagList = merchantProductFacade.findTagAllByMerchantProductId(dto.getId());
		List<Long> tagIdList = new ArrayList<>();
		for (MerchantProductTagDTO merchantProductTagDTO : merchantProductTagList) {
			tagIdList.add(merchantProductTagDTO.getTagId());
		}
		map.put("merchantProductTagList", tagIdList);

		//根据su草稿id查询su草稿比价平台信息
		SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO = new SellPlatformMerchantProdDTO();
		sellPlatformMerchantProdDTO.setMerchantProductId(dto.getId());
		sellPlatformMerchantProdDTO.setSellPlatformId(dto.getPlatformId());
		List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdList = sellPlatformMerchantProdFacade.findByMerchantProdId(sellPlatformMerchantProdDTO);
		map.put("sellPlatformMerchantProdList", sellPlatformMerchantProdList);
		
		// 组织pu信息集合
		List<ProductUnitDTO> productUnitList = createProductUnitList(
				dto.getId(),dto.getPlatformId());
		map.put("productUnitList", productUnitList);
		
		// 根据suId查询su草稿关键词
		List<String> suKeyWordList = mpSerachKeywordFacade.keyWordByMerchantProductId(dto.getId(),dto.getPlatformId());
		map.put("suKeyWordList", suKeyWordList);
		
		// 根据spuid查询spu关键词
		List<String> spuKeyWordList = standardProductUnitAttValueFacade.keyWordByStandardProductUnitId(merchantProductDTO.getStandardProductUnitId(),dto.getPlatformId());
		map.put("spuKeyWordList", spuKeyWordList);
		
		// 组织门店Id集合
		List<Long> storeIdList = createStoreIdList(dto.getId(),dto.getPlatformId());
		map.put("storeIdList", storeIdList);
		
		// 组织会籍id集合
		List<Long> membershipIdList = createMembershipId(dto.getId(),dto.getPlatformId());
		map.put("membershipIdList", membershipIdList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> findMerchantProductById(MerchantProductDTO dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		MerchantProductDTO merchantProductDTO = merchantProductFacade.findMerchantProductById(dto);

		// 创建su显示数据
		MerchantProductShowVO merchantProductShowVO = createMerchantProductShowVO(merchantProductDTO);

		//根据spuid更新supu信息
		merchantProductFacade.updateSuPu(merchantProductDTO.getStandardProductUnitId(),dto.getId(),merchantProductDTO.getPlatformId());

		// 添加图片信息
		setPictureData(dto.getId(),map);
		
		//查询所有所属平台
		List<ClientDTO> clientList = merchantProductFacade.findClientAll(new ClientDTO());
		map.put("clientList", clientList);
		
		// su显示数据公司id集合赋值
		setCompanyIds(merchantProductShowVO,dto.getId(),dto.getPlatformId());
		map.put("merchantProduct", merchantProductShowVO);

		// 组织公司信息并添加
		createCompanys(map,dto.getPlatformId());


		//根据su草稿id查询标签信息集合
		List<MerchantProductTagDTO> merchantProductTagList = merchantProductFacade.findTagAllByMerchantProductId(dto.getId());
		List<Long> tagIdList = new ArrayList<>();
		for (MerchantProductTagDTO merchantProductTagDTO : merchantProductTagList) {
			tagIdList.add(merchantProductTagDTO.getTagId());
		}
		map.put("merchantProductTagList", tagIdList);

		//根据su草稿id查询su草稿比价平台信息
		SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO = new SellPlatformMerchantProdDTO();
		sellPlatformMerchantProdDTO.setMerchantProductId(dto.getId());
		sellPlatformMerchantProdDTO.setSellPlatformId(dto.getPlatformId());
		List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdList = sellPlatformMerchantProdFacade.findByMerchantProdId(sellPlatformMerchantProdDTO);
		map.put("sellPlatformMerchantProdList", sellPlatformMerchantProdList);
		
		// 组织pu信息集合
		List<ProductUnitDTO> productUnitList = createProductUnitList(
				dto.getId(),dto.getPlatformId());
		map.put("productUnitList", productUnitList);
		
		// 根据suId查询su草稿关键词
		List<String> suKeyWordList = mpSerachKeywordFacade.keyWordByMerchantProductId(dto.getId(),dto.getPlatformId());
		map.put("suKeyWordList", suKeyWordList);
		
		// 根据spuid查询spu关键词
		List<String> spuKeyWordList = standardProductUnitAttValueFacade.keyWordByStandardProductUnitId(merchantProductDTO.getStandardProductUnitId(),dto.getPlatformId());
		map.put("spuKeyWordList", spuKeyWordList);
		
		// 组织门店Id集合
		List<Long> storeIdList = createStoreIdList(dto.getId(),dto.getPlatformId());
		map.put("storeIdList", storeIdList);
		
		// 组织会籍id集合
		List<Long> membershipIdList = createMembershipId(dto.getId(),dto.getPlatformId());
		map.put("membershipIdList", membershipIdList);
		
		return map;
	}
	
	/**
	 * 添加图片信息
	 */
	private void setPictureData(Long merchantProductId,Map<String, Object> map) {
		//su草稿id查询su草稿图片信息
		List<PictureDTO> pictures = pictureFacade.findMPictureByMerchantProdId(merchantProductId);
		// app轮播图
		List<String> pictureList = new ArrayList<>();
		// web轮播图
		List<String> webBannerPictureList = new ArrayList<>();
		for (PictureDTO pictureDTO : pictures) {
			if(EmptyUtil.isNotEmpty(pictureDTO.getType())){
				//类型：1、列表图片 2、轮播图片 3、web端轮播图
				switch (pictureDTO.getType()) {
					case 1:
						map.put("picture", pictureDTO.getUrl());
						break;
					case 2:
						pictureList.add(pictureDTO.getUrl());
						break;
					case 3:
						webBannerPictureList.add(pictureDTO.getUrl());
						break;
					default:
						throw new BusinessException("未定义图片类型");
				}
			}
		}
		map.put("pictureList", pictureList);
		map.put("webBannerPictureList", webBannerPictureList);
		
	}
	/**
	 * su显示数据公司id集合赋值
	 * @param merchantProductShowVO
	 * @param merchantProductId
	 * @param platformId
	 */
	private void setCompanyIds(MerchantProductShowVO merchantProductShowVO, Long merchantProductId, Long platformId) {
		//根据su草稿id查询福利企业关系
		List<Long> companyIds = new ArrayList<>(); // 正式公司id集合
		List<Long> demoCompanyIds = new ArrayList<>(); // 演示公司id集合
		List<Long> competingCompanyIds = new ArrayList<>(); // 竞品公司id集合
		
		MerchantProductCompanyDTO merchantProductCompanyDTO = new MerchantProductCompanyDTO();
		merchantProductCompanyDTO.setMerchantProductId(merchantProductId);
		List<MerchantProductCompanyDTO> findMerchantProductCompanyAll = merchantProductCompanyFacade.findMerchantProductCompanyAll(merchantProductCompanyDTO);
		for (MerchantProductCompanyDTO merchantProductCompanyDTO2 : findMerchantProductCompanyAll) {
			if(EmptyUtil.isNotEmpty(merchantProductCompanyDTO2.getCompanyType())){
				// 公司类型 0:正式公司 1:测试公司 2:竞品公司
				switch (merchantProductCompanyDTO2.getCompanyType()) {
					case 0:
						companyIds.add(merchantProductCompanyDTO2.getCompanyId());
						break;
					case 1:
						demoCompanyIds.add(merchantProductCompanyDTO2.getCompanyId());
						break;
					case 2:
						competingCompanyIds.add(merchantProductCompanyDTO2.getCompanyId());
						break;

					default:
						break;
				}
			}
		}
		merchantProductShowVO.setCompanyIds(companyIds);
		merchantProductShowVO.setDemoCompanyIds(demoCompanyIds);
		merchantProductShowVO.setCompetingCompanyIds(competingCompanyIds);
		
	}
	/**
	 * 创建su显示数据
	 * @param merchantProductDTO
	 * @return
	 */
	private MerchantProductShowVO createMerchantProductShowVO(MerchantProductDTO merchantProductDTO) {
		MerchantProductShowVO merchantProductShowVO = new MerchantProductShowVO();
		merchantProductShowVO.setId(merchantProductDTO.getId());
		merchantProductShowVO.setMerchantProductSerialNumber(merchantProductDTO.getMerchantProductSerialNumber());
		merchantProductShowVO.setMerchantId(merchantProductDTO.getMerchantId());
		merchantProductShowVO.setStandardProductUnitId(merchantProductDTO.getStandardProductUnitId());
		merchantProductShowVO.setProductCategory(merchantProductDTO.getProductCategory());
		merchantProductShowVO.setName(merchantProductDTO.getName());
		merchantProductShowVO.setIsVisible(merchantProductDTO.getIsVisible());
		merchantProductShowVO.setSalePrice(merchantProductDTO.getSalePrice());
		merchantProductShowVO.setDemoSalePrice(merchantProductDTO.getDemoSalePrice());
		merchantProductShowVO.setCompetingSalePrice(merchantProductDTO.getCompetingSalePrice());
		merchantProductShowVO.setIsVendible(merchantProductDTO.getIsVendible());
		merchantProductShowVO.setMarketPrice(merchantProductDTO.getMarketPrice());
		merchantProductShowVO.setPromotionPrice(merchantProductDTO.getPromotionPrice());
		merchantProductShowVO.setStatus(merchantProductDTO.getStatus());
		merchantProductShowVO.setSaleWay(merchantProductDTO.getSaleWay());
		merchantProductShowVO.setSoldBase(merchantProductDTO.getSoldBase());
		merchantProductShowVO.setStockWarning(merchantProductDTO.getStockWarning());
		merchantProductShowVO.setIsSpuKeyword(merchantProductDTO.getIsSpuKeyword());
		merchantProductShowVO.setContent(merchantProductDTO.getContent());
		merchantProductShowVO.setStoreId(merchantProductDTO.getStoreId());
		merchantProductShowVO.setPresellPeriod(merchantProductDTO.getPresellPeriod());
		merchantProductShowVO.setRelevanceSuId(merchantProductDTO.getRelevanceSuId());
		merchantProductShowVO.setRelevanceSuName(merchantProductDTO.getRelevanceSuName());
		merchantProductShowVO.setBuyType(merchantProductDTO.getBuyType());
		merchantProductShowVO.setFrontSerialNumber(merchantProductDTO.getFrontSerialNumber());
		
		MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setId(merchantProductDTO.getMerchantId());
		MerchantDTO merchantById = merchantFacade.findMerchantById(merchantDTO);
		if(EmptyUtil.isEmpty(merchantById)){
			throw new BusinessException("运营方查询有误");
		}
		Map<Long, String> merchantMap = new HashMap<>();
		merchantMap.put(merchantById.getId(),merchantById.getName());
		merchantProductShowVO.setMerchantMap(merchantMap);

		// 判断是否有unit库存、在现在其不同的运费说明信息
		if(merchantProductFacade.queryIsUnit(merchantProductDTO.getStandardProductUnitId())){
			merchantProductShowVO.setFreightExplain("电子卡券，无需运费");
			if(EmptyUtil.isEmpty(merchantProductDTO.getShipmentsExplain())){
				merchantProductShowVO.setShipmentsExplain("购买后闪电发送卡号/密，订单详情页可查看");
			}else{
				merchantProductShowVO.setShipmentsExplain(merchantProductDTO.getShipmentsExplain());
			}
		}else{
			Map<String, Object> freightTemplateByMap = freightTemplateFacade.freightTemplateByMerchantId(merchantProductDTO.getMerchantId(),merchantProductDTO.getStoreId(),merchantProductDTO.getPlatformId());
			merchantProductShowVO.setFreightExplain(String.valueOf(freightTemplateByMap.get("freightRegulation")));
			if(EmptyUtil.isEmpty(merchantProductDTO.getShipmentsExplain())){
				merchantProductShowVO.setShipmentsExplain(String.valueOf(freightTemplateByMap.get("shipmentsExplain")));
			}else{
				merchantProductShowVO.setShipmentsExplain(merchantProductDTO.getShipmentsExplain());
			}
		}

		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(merchantProductDTO.getStandardProductUnitId());
		StandardProductUnitDTO standardProductUnitDTO2 = standardProductUnitFacade.findStandardProductUnitById(standardProductUnitDTO);
		merchantProductShowVO.setStandardProductUnitName(standardProductUnitDTO2.getName());
		merchantProductShowVO.setProductSerialNumber(standardProductUnitDTO2.getProductSerialNumber());
		
		//根据suid查询su销售记录
		Long salesVolume = merchantProdSalesRecordFacade.findSalesRecordByStandardUnitId(merchantProductDTO.getId());
		merchantProductShowVO.setSalesVolume(salesVolume);
		
		//根据su草稿信息查询所属平台关系
		MerchantProdClientDTO merchantProdClientDTO = new MerchantProdClientDTO();
		merchantProdClientDTO.setMerchantProductId(merchantProductDTO.getId());
		List<MerchantProdClientDTO> findMerchantProdClientAll = merchantProdClientFacade.findMerchantProdClientAll(merchantProdClientDTO);

		List<Long> clientIds = new ArrayList<>();
		for (MerchantProdClientDTO merchantProdClientDTO2 : findMerchantProdClientAll) {
			clientIds.add(merchantProdClientDTO2.getClientId());
		}
		merchantProductShowVO.setClientIds(clientIds);
		
		return merchantProductShowVO;
	}
	/**
	 * 组织公司信息并添加
	 * @param map
	 */
	private void createCompanys(Map<String, Object> map,Long platformId) {
		//查询所有福利企业信息
		CompanyDTO companyDTO2 = new CompanyDTO();
		companyDTO2.setPlatformId(platformId);

		List<CompanyDTO> companysList = merchantProductFacade.findCompanyAll(companyDTO2);

		List<CompanyDTO> companyList = new ArrayList<>(); // 正式公司集合
		List<CompanyDTO> demoCompanyList = new ArrayList<>(); // 演示公司集合
		List<CompanyDTO> competingCompanyList = new ArrayList<>(); // 竞品公司集合
		for (CompanyDTO companyDTO : companysList) {
			if(EmptyUtil.isNotEmpty(companyDTO.getIsTest())){
				// 公司类型 0:正式公司 1:测试公司 2:竞品公司
				switch (companyDTO.getCompanyType()) {
					case 0:
						companyList.add(companyDTO);
						break;
					case 1:
						demoCompanyList.add(companyDTO);
						break;
					case 2:
						competingCompanyList.add(companyDTO);
						break;

					default:
						break;
				}
			}
		}
		map.put("companyList", companyList);
		map.put("demoCompanyList", demoCompanyList);
		map.put("competingCompanyList", competingCompanyList);
		
	}
	/**
	 * 组织pu信息集合
	 * @param merchantProductId
	 * @param platformId
	 */
	private List<ProductUnitDTO> createProductUnitList(Long merchantProductId, Long platformId) {
		//根据su草稿id查询su草稿pu信息
		ProductUnitDTO productUnitDTO = new ProductUnitDTO();
		productUnitDTO.setMerchantProductId(merchantProductId);
		productUnitDTO.setPlatformId(platformId);
		List<ProductUnitDTO> productUnitList = productUnitFacade.findProductUnitAll(productUnitDTO);
		for (ProductUnitDTO productUnitDTO2 : productUnitList) {
			if(productUnitDTO2.getIsVendible() == 0){
				productUnitDTO2.setChecked(false);
			}else if(productUnitDTO2.getIsVendible() == 1){
				productUnitDTO2.setChecked(true);
			}
			//根据skuid查询sku信息
			SkuDTO skuDTO = new SkuDTO();
			skuDTO.setId(productUnitDTO2.getSkuId());
			SkuDTO skuDTO2 = skuFacade.findSkuById(skuDTO);
			productUnitDTO2.setIsAvailable(skuDTO2.getIsAvailable());
			productUnitDTO2.setIsValid(skuDTO2.getIsValid());
			productUnitDTO2.setSkuName(skuDTO2.getSkuName());
		}
		return productUnitList;
		
	}
	/**
	 * 组织会籍id集合
	 * @param merchantProductId
	 * @param platformId
	 */
	private List<Long> createMembershipId(Long merchantProductId, Long platformId) {
		// 根据su草稿id查询su会籍关系信息
		MerchantProductMembershipDTO merchantProductMembershipDTO = new MerchantProductMembershipDTO();
		merchantProductMembershipDTO.setMerchantProductId(merchantProductId);
		merchantProductMembershipDTO.setPlatformId(platformId);
		List<MerchantProductMembershipDTO> merchantProductMembershipList = merchantProductMembershipFacade.findMerchantProductMembershipAll(merchantProductMembershipDTO);
		List<Long> membershipIdList = new ArrayList<>(merchantProductMembershipList.size());
		if(EmptyUtil.isNotEmpty(merchantProductMembershipList)){
			for (MerchantProductMembershipDTO merchantProductMembership : merchantProductMembershipList) {
				membershipIdList.add(merchantProductMembership.getMembershipId());
			}
		}
		return membershipIdList;
		
	}

	private List<Long> createStoreIdList(Long merchantProductId,Long platformId) {
		// 根据su草稿id查询su门店关系信息
		MerchantProductStoreDTO merchantProductStoreDTO = new MerchantProductStoreDTO();
		merchantProductStoreDTO.setMerchantProductId(merchantProductId);
		merchantProductStoreDTO.setPlatformId(platformId);
		List<MerchantProductStoreDTO> merchantProductStoreList = merchantProductStoreFacade.findMerchantProductStoreAll(merchantProductStoreDTO);
		List<Long> storeIdList = new ArrayList<>(merchantProductStoreList.size());
		if(EmptyUtil.isNotEmpty(merchantProductStoreList)){
			for (MerchantProductStoreDTO merchantProductStore : merchantProductStoreList) {
				storeIdList.add(merchantProductStore.getStoreId());
			}
		}
		return storeIdList;
	}

	@Override
	public PageResult<MerchantProductVO> findMerchantProductOfPage(Integer platformFlag,BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, Date startTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList) {
		
		PageResult<MerchantProductDTO> result = new PageResult<MerchantProductDTO>();
		if (StringUtils.isNotEmpty(dto.getSupplierName())){
			Enterprise enterprise=supplierClient.findByName(dto.getSupplierName());
			if (enterprise==null || enterprise.getId()==null){
				throw new BusinessException("供应商["+dto.getSupplierName()+"]不存在");
			}
			dto.setSupplierId(enterprise.getId());
		}
		if(platformFlag!=null && platformFlag.intValue()==1) {
			result = merchantProductFacade.findPlatformMerchantProductOfPage(priceStart,priceEnd,startProfit,endProfit,startTime,endTime,dto, page,nameList);
		}else {
			result = merchantProductFacade.findMerchantProductOfPage(priceStart,priceEnd,startProfit,endProfit,startTime,endTime,dto, page,nameList);
		}
		List<MerchantProductDTO> merchantProductList = result.getList();
		List<MerchantProductVO> merchantProductVOList = new ArrayList<MerchantProductVO>();
		if(EmptyUtil.isNotEmpty(merchantProductList)){
			List<Long> ids = new ArrayList<>();
			for (MerchantProductDTO merchantProductDTO : merchantProductList) {
				//su草稿id查询su草稿所属平台信息
				MerchantProdClientDTO merchantProdClientDTO = new MerchantProdClientDTO();
				merchantProdClientDTO.setMerchantProductId(merchantProductDTO.getId());
				List<MerchantProdClientDTO> merchantProdClientList = merchantProdClientFacade.findMerchantProdClientAll(merchantProdClientDTO);
				//拼接所属平台id集合
				List<Long> clientIds = new ArrayList<>();
				for (MerchantProdClientDTO merchantProdClientDTO2 : merchantProdClientList) {
					clientIds.add(merchantProdClientDTO2.getClientId());
				}
				//根据所属平台id集合查询所属平台信息
				StringBuffer clients = new StringBuffer();
				if (EmptyUtil.isNotEmpty(clientIds)) {
					List<ClientDTO> ClientList = merchantProductFacade.findClientByClientIds(clientIds);
					for (int i = 0; i < ClientList.size(); i++) {
						clients.append(ClientList.get(i).getName());
						if (i + 1 < ClientList.size()) {
							clients.append(",");
						}
					}
					merchantProductDTO.setClients(clients.toString());
				}

				//根据su草稿id查询福利公司集合
				MerchantProductCompanyDTO merchantProductCompanyDTO = new MerchantProductCompanyDTO();
				merchantProductCompanyDTO.setMerchantProductId(merchantProductDTO.getId());
				List<MerchantProductCompanyDTO> merchantProductCompanyList = merchantProductCompanyFacade.findMerchantProductCompanyAll(merchantProductCompanyDTO);
				//拼接福利公司id集合
				List<Long> companyIds = new ArrayList<>();
				for (MerchantProductCompanyDTO merchantProductCompanyDTO2 : merchantProductCompanyList) {
					companyIds.add(merchantProductCompanyDTO2.getCompanyId());
				}
				//根据福利公司id集合查询福利公司信息
				if (EmptyUtil.isNotEmpty(companyIds)) {
					List<CompanyDTO> companyList = merchantProductFacade.findCompanyByCompanyIds(companyIds);
					//拼接福利公司名称字符串
					StringBuffer companys = new StringBuffer();
					for (int i = 0; i < companyList.size(); i++) {
						companys.append(companyList.get(i).getCompanyName());
						if (i + 1 < companyList.size()) {
							companys.append(",");
						}
					}
					merchantProductDTO.setCompanys(companys.toString());
				}

				ids.add(merchantProductDTO.getId());
			}
			if (EmptyUtil.isNotEmpty(ids)) {
				//根据su草稿id集合查询su信息
				List<StandardUnitDTO> standardUnitDTOList = standardUnitFacade.findBymerchantProdId(ids);
				for (MerchantProductDTO merchantProductDTO : merchantProductList) {
					for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
						if (merchantProductDTO.getId().equals(standardUnitDTO.getId())) {
							if (standardUnitDTO.getStatus() == 3 || standardUnitDTO.getStatus() == 4) {
								merchantProductDTO.setMerchantProductSerialNumber(standardUnitDTO.getMerchantProductSerialNumber());
								merchantProductDTO.setMerchantId(standardUnitDTO.getMerchantId());
								merchantProductDTO.setSuStatus(standardUnitDTO.getStatus());
								merchantProductDTO.setStandardProductUnitId(standardUnitDTO.getStandardProductUnitId());
								merchantProductDTO.setMerchantCateTreeNodeId(standardUnitDTO.getMerchantCateTreeNodeId());
								merchantProductDTO.setMerchantSeriesId(standardUnitDTO.getMerchantSeriesId());
								merchantProductDTO.setName(standardUnitDTO.getName());
								merchantProductDTO.setAlias(standardUnitDTO.getAlias());
								merchantProductDTO.setIsVisible(standardUnitDTO.getIsVisible());
								merchantProductDTO.setSubtitle(standardUnitDTO.getSubtitle());
								merchantProductDTO.setSupplierId(standardUnitDTO.getSupplierId());
								merchantProductDTO.setType(standardUnitDTO.getType());
								merchantProductDTO.setRemark(standardUnitDTO.getRemark());
								merchantProductDTO.setSalePrice(standardUnitDTO.getSalePrice());
								merchantProductDTO.setSaleTaxRate(standardUnitDTO.getSaleTaxRate());
								merchantProductDTO.setReturnDays(standardUnitDTO.getReturnDays());
								merchantProductDTO.setReplacementDays(standardUnitDTO.getReplacementDays());
								merchantProductDTO.setGuaranteeDays(standardUnitDTO.getGuaranteeDays());
								merchantProductDTO.setIsVatInvoice(standardUnitDTO.getIsVatInvoice());
								merchantProductDTO.setIsVipCard(standardUnitDTO.getIsVipCard());
								merchantProductDTO.setIsEnableShelflife(standardUnitDTO.getIsEnableShelflife());
								merchantProductDTO.setShelflifeDays(standardUnitDTO.getShelflifeDays());
								merchantProductDTO.setIsVendible(standardUnitDTO.getIsVendible());
								merchantProductDTO.setGrossWeight(standardUnitDTO.getGrossWeight());
								merchantProductDTO.setNetWeight(standardUnitDTO.getNetWeight());
								merchantProductDTO.setCode(standardUnitDTO.getCode());
								merchantProductDTO.setMerchantBrandId(standardUnitDTO.getMerchantBrandId());
								merchantProductDTO.setMarketPrice(standardUnitDTO.getMarketPrice());
								merchantProductDTO.setPromotionPrice(standardUnitDTO.getPromotionPrice());
								merchantProductDTO.setSaleWay(standardUnitDTO.getSaleWay());
								merchantProductDTO.setSoldBase(standardUnitDTO.getSoldBase());
								merchantProductDTO.setFreightExplain(standardUnitDTO.getFreightExplain());
								merchantProductDTO.setShipmentsExplain(standardUnitDTO.getShipmentsExplain());
								merchantProductDTO.setFreightTemplateId(standardUnitDTO.getFreightTemplateId());
								merchantProductDTO.setCreateTime(standardUnitDTO.getCreateTime());
								merchantProductDTO.setUpdateTime(standardUnitDTO.getUpdateTime());
								merchantProductDTO.setPlatformId(standardUnitDTO.getPlatformId());
								merchantProductDTO.setMerchantName(standardUnitDTO.getMerchantName());
								merchantProductDTO.setStoreId(standardUnitDTO.getStoreId());
								merchantProductDTO.setJdProfit(standardUnitDTO.getJdProfit());
								merchantProductDTO.setBuyType(standardUnitDTO.getBuyType());
								merchantProductDTO.setFrontSerialNumber(standardUnitDTO.getFrontSerialNumber());

								merchantProductDTO.setSalesVolume(standardUnitDTO.getSalesVolume());

								//根据suid查询su所属平台信息
								StandardUnitClientDTO standardUnitClientDTO = new StandardUnitClientDTO();
								standardUnitClientDTO.setStandardUnitId(standardUnitDTO.getId());
								List<StandardUnitClientDTO> standardUnitClientList = standardUnitClientFacade.findStandardUnitClientAll(standardUnitClientDTO);
								//所属平台id集合
								List<Long> ClientIds = new ArrayList<>();
								for (StandardUnitClientDTO standardUnitClientDTO2 : standardUnitClientList) {
									ClientIds.add(standardUnitClientDTO2.getClientId());
								}
								//根据所属平台id集合查询所属平台信息
								StringBuffer clients = new StringBuffer();
								if (EmptyUtil.isNotEmpty(ClientIds)) {
									List<ClientDTO> ClientList = merchantProductFacade.findClientByClientIds(ClientIds);
									for (int i = 0; i < ClientList.size(); i++) {
										clients.append(ClientList.get(i).getName());
										if (i + 1 < ClientList.size()) {
											clients.append(",");
										}
									}
									merchantProductDTO.setClients(clients.toString());
								}


								//根据suid查询可以显示的福利企业信息
								StandardUnitCompanyDTO standardUnitCompanyDTO = new StandardUnitCompanyDTO();
								standardUnitCompanyDTO.setStandardUnitId(standardUnitDTO.getId());
								List<StandardUnitCompanyDTO> standardUnitCompanyList = standardUnitCompanyFacade.findStandardUnitCompanyAll(standardUnitCompanyDTO);
								//拼接福利企业id集合
								List<Long> companyIds = new ArrayList<>();
								for (StandardUnitCompanyDTO standardUnitCompanyDTO2 : standardUnitCompanyList) {
									companyIds.add(standardUnitCompanyDTO2.getCompanyId());
								}

								//根据福利公司id集合查询福利公司信息
								if (EmptyUtil.isNotEmpty(companyIds)) {
									List<CompanyDTO> companyList = merchantProductFacade.findCompanyByCompanyIds(companyIds);
									//拼接福利公司名称字符串
									StringBuffer companys = new StringBuffer();
									for (int i = 0; i < companyList.size(); i++) {
										companys.append(companyList.get(i).getCompanyName());
										if (i + 1 < companyList.size()) {
											companys.append(",");
										}
									}
									merchantProductDTO.setCompanys(companys.toString());
								}
								break;
							}
						}

					}
				}
			}
		}
		PageResult<MerchantProductVO> pageResult = new PageResult<>();
		HashMap<Long,Enterprise> enterpriseMap = new HashMap<Long,Enterprise>();
		HashMap<Long,Enterprise> supplierMap = new HashMap<Long,Enterprise>();
		if(EmptyUtil.isNotEmpty(merchantProductList)){
			for (MerchantProductDTO merchantProductDTO : merchantProductList) {
				// 根据总店id查询店铺信息
				StoreDTO storeDTO = new StoreDTO();
				storeDTO.setId(merchantProductDTO.getStoreId());
				StoreDTO storeDTO2 = storeFacade.findStoreById(storeDTO);
				ProductDTO pdto = new ProductDTO();
				pdto.setId(merchantProductDTO.getStandardProductUnitId());
				if(EmptyUtil.isNotEmpty(storeDTO2)){
					merchantProductDTO.setStoreName(storeDTO2.getName());
					merchantProductDTO.setHeadActivityCode(storeDTO2.getActivityCode());
				}
				merchantProductDTO.setProfit(merchantProductDTO.getJdProfit());

				ProductDTO productDto = productFacade.findById(pdto);
				MerchantProductVO merchantProductVO = MerchantProductConverter.toVO(merchantProductDTO);
				if(productDto.getEnterpriseId()!=null) {
					Enterprise enterprise = null;
					if(enterpriseMap.containsKey(productDto.getEnterpriseId())) {
						enterprise = enterpriseMap.get(productDto.getEnterpriseId());
					}else {
						enterprise = enterpriseClient.findById(productDto.getEnterpriseId().intValue());
						enterpriseMap.put(productDto.getEnterpriseId(),enterprise);
					}
					
					if(enterprise!=null) {
						merchantProductVO.setEnterpriseName(enterprise.getName());
						merchantProductVO.setEnterpriseId(productDto.getEnterpriseId());
					}
				}
				if (merchantProductVO.getSupplierId()!=null){
					Enterprise supplier = null;
					if(supplierMap.containsKey(merchantProductVO.getSupplierId())) {
						supplier = supplierMap.get(merchantProductVO.getSupplierId());
					}else {
						supplier = supplierClient.findById(merchantProductVO.getSupplierId().intValue());
						supplierMap.put(merchantProductVO.getSupplierId(),supplier);
					}
					if(supplier!=null) {
						merchantProductVO.setSupplierName(supplier.getName());
					}
				}
				merchantProductVOList.add(merchantProductVO);
			}
		}
		pageResult.setList(merchantProductVOList);
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}


	@Override
	public JsonResult<Map<String, Object>> exportMerchantProductOfPage(Integer platformFlag, BigDecimal priceStart, BigDecimal priceEnd,
																		   Integer startProfit, Integer endProfit, Date starTime, Date endTime,
																		   MerchantProductDTO dto, List<String> nameList) {
		List<MerchantProductExportVO> exportVOS=new ArrayList<>();
		List<Long> storeIds=new ArrayList<>();
		if (EmptyUtil.isNotEmpty(dto.getStoreName())){
			storeIds=storeReadService.findStoreByName(dto.getStoreName());
		}
		dto.setStoreName(null);
		HashMap<Long,Enterprise> supplierMap = new HashMap<Long,Enterprise>();
		List<MerchantProductUnitCondition> list=merchantProductReadService.exportMerchantProduct(priceStart,priceEnd,startProfit,endProfit,storeIds,starTime,endTime,dto,nameList);
		if (EmptyUtil.isNotEmpty(list)){
			Set<Long> standardUnitIds=new HashSet<>();
			list.forEach(item->standardUnitIds.add(item.getStandardUnitId()));
			//根据suid查询可以显示的福利企业信息
			List<StandardUnitCompanyDTO> standardUnitCompanyList = standardUnitCompanyFacade.findStandardUnitCompanyAll(new ArrayList<>(standardUnitIds));
			Map<Long,List<Long>> standUnitIdCompanyMap=new HashMap<>();
			if (EmptyUtil.isNotEmpty(standardUnitCompanyList)){
				standardUnitCompanyList.forEach(item->{
					List<Long> companyIds=standUnitIdCompanyMap.getOrDefault(item.getStandardUnitId(),new ArrayList<>());
					companyIds.add(item.getCompanyId());
					if (!standUnitIdCompanyMap.containsKey(item.getStandardUnitId())){
						standUnitIdCompanyMap.put(item.getStandardUnitId(),companyIds);
					}
				});
			}
			Map<Long,CompanyDTO> companyIdMap=new HashMap<>();
			for (MerchantProductUnitCondition condition:list){
				MerchantProductExportVO exportVO=new MerchantProductExportVO();
				exportVO.setMerchantProductSerialNumber(condition.getMerchantProductSerialNumber());
				exportVO.setMerchantProductName(condition.getMerchantProductName());
				exportVO.setMerchantProductCategory(condition.getMerchantProductCategory());
				if (EmptyUtil.isNotEmpty(condition.getSupplierId())){
					Enterprise supplier = null;
					if(supplierMap.containsKey(condition.getSupplierId())) {
						supplier = supplierMap.get(condition.getSupplierId());
					}else {
						supplier = supplierClient.findById(condition.getSupplierId().intValue());
						supplierMap.put(condition.getSupplierId(),supplier);
					}
					if(EmptyUtil.isNotEmpty(supplier)) {
						exportVO.setSupplierName(supplier.getName());
					}
				}

				//拼接福利企业id集合
				List<Long> companyIds = standUnitIdCompanyMap.getOrDefault(condition.getStandardUnitId(),new ArrayList<>());
				//根据福利公司id集合查询福利公司信息
				if (EmptyUtil.isNotEmpty(companyIds)) {
					//拼接福利公司名称字符串
					StringBuffer companys = new StringBuffer();
					List<Long> searchCompany=new ArrayList<>();
					companyIds.forEach(id->{
						if (companyIdMap.containsKey(id)){
							CompanyDTO companyDTO=companyIdMap.get(id);
							companys.append(companyDTO.getCompanyName()).append(",");
						}else {
							searchCompany.add(id);
						}
					});
					if (EmptyUtil.isNotEmpty(searchCompany)){
						List<CompanyDTO> companyList = merchantProductFacade.findCompanyByCompanyIds(companyIds);
						if (EmptyUtil.isNotEmpty(companyList)){
							companyList.forEach(item->{
								companyIdMap.put(item.getId(),item);
								companys.append(item.getCompanyName()).append(",");
							});
						}
					}
					exportVO.setCompanies(companys.deleteCharAt(companys.lastIndexOf(",")).toString());
				}
				exportVO.setSaleVolume(condition.getSaleVolume());
				exportVO.setProductUnitSerialNumber(condition.getProductUnitSerialNumber());
				exportVO.setProductUnitName(condition.getProductUnitName());
				exportVO.setSalePrice(condition.getSalePrice());
				exportVO.setSkuMarketPrice(condition.getSkuMarketPrice());
				exportVO.setSkuCostingPrice(condition.getSkuCostingPrice());
				exportVO.setTaxRate(condition.getTaxRate());
				exportVO.setIsVisible(Objects.equals(condition.getIsVisible(),1)?"隐藏":"显示");
				String status="草稿";
				if (Objects.equals(condition.getStatus(),1)){
					status="待上架";
				}else if (Objects.equals(condition.getStatus(),2)){
					status="审核中";
				}else if (Objects.equals(condition.getStatus(),3)){
					status="已上架";
				}else if (Objects.equals(condition.getStatus(),4)){
					status="已下架";
				}else if (Objects.equals(condition.getStatus(),5)){
					status="审核未通过";
				}
				exportVO.setStatus(status);
				if (EmptyUtil.isNotEmpty(condition.getSalePrice()) && condition.getSalePrice().compareTo(BigDecimal.ZERO)>0
					&&EmptyUtil.isNotEmpty(condition.getSkuCostingPrice()) && condition.getSkuCostingPrice().compareTo(BigDecimal.ZERO)>0){
					BigDecimal profit=condition.getSalePrice().subtract(condition.getSkuCostingPrice())
							.multiply(new BigDecimal(100)).divide(condition.getSalePrice(),2,BigDecimal.ROUND_HALF_UP);
					exportVO.setProfit(String.format("%s%s",profit,"%"));
				}
				exportVOS.add(exportVO);
			}
		}
		return writeMerchantProductInExcel(exportVOS);
	}

	private JsonResult<Map<String, Object>> writeMerchantProductInExcel(List<MerchantProductExportVO> voList) {
		Workbook wsd = new XSSFWorkbook();
		Sheet cloneSheet = wsd.createSheet("商品规格明细");
		String[] headArr = {"商品编号", "商品名称", "类目","供应商", "销量", "市场价", "规格编号", "规格名称", "销售价", "供货价", "毛利率","商品税率","福利企业","商品显示","商品状态"};

		Row head = cloneSheet.createRow(0);
		for (int i = 0; i < headArr.length; i++) {
			head.createCell(i).setCellValue(headArr[i]);
		}

		for (int i = 0; i < voList.size(); i++) {
			MerchantProductExportVO vo = voList.get(i);
			Row row = cloneSheet.createRow(i + 1);
			row.createCell(0).setCellValue(vo.getMerchantProductSerialNumber());
			row.createCell(1).setCellValue(vo.getMerchantProductName());
			row.createCell(2).setCellValue(vo.getMerchantProductCategory());
			row.createCell(3).setCellValue(vo.getSupplierName());
			row.createCell(4).setCellValue(EmptyUtil.isEmpty(vo.getSaleVolume())?"":vo.getSaleVolume().toPlainString());
			row.createCell(5).setCellValue(EmptyUtil.isEmpty(vo.getSkuMarketPrice())?"":vo.getSkuMarketPrice().toPlainString());
			row.createCell(6).setCellValue(vo.getProductUnitSerialNumber());
			row.createCell(7).setCellValue(vo.getProductUnitName());
			row.createCell(8).setCellValue(EmptyUtil.isEmpty(vo.getSalePrice())?"":vo.getSalePrice().toPlainString());
			row.createCell(9).setCellValue(EmptyUtil.isEmpty(vo.getSkuCostingPrice())?"":vo.getSkuCostingPrice().toPlainString());
			row.createCell(10).setCellValue(vo.getProfit());
			row.createCell(11).setCellValue(EmptyUtil.isEmpty(vo.getTaxRate())?"":vo.getTaxRate().toPlainString()+"%");
			row.createCell(12).setCellValue(vo.getCompanies());
			row.createCell(13).setCellValue(vo.getIsVisible());
			row.createCell(14).setCellValue(vo.getStatus());
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wsd.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("导出失败!");
		}
		String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return JsonResult.success("url", upload);
	}


	@Override
	public List<MerchantProductDTO> findMerchantProductAll(MerchantProductDTO dto) {
		return merchantProductFacade.findMerchantProductAll(dto);
	}

	private void checkPrice(List<ProductUnitDTO> productUnitList){

	}
	@Override
	public JsonResult<Long> insertMerchantProductWithTx(
			MerchantProductDTO dto,
			String picture,
			String pictureList,
			String webBannerPictureList,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList,
			String clientList,
			String companyList,
			String content,
			List<ProductUnitDTO> productUnitList,
			List<Long> tagList,
			List<String> keywords,
			String demoCompanyList,
			String competingCompanyList,
			List<Long> storeIds,
			List<Long> membershipIds) {

		List<Long> companys = null; // 正式公司集合
		List<Long> demoCompanys = null; // 演示公司集合
		List<Long> competingCompanys = null; // 竞品公司集合


		if(companyList != null)
			companys = JSONArray.parseArray(companyList, Long.class);
		if(demoCompanyList != null)
			demoCompanys = JSONArray.parseArray(demoCompanyList, Long.class);
		if(competingCompanyList != null)
			competingCompanys = JSONArray.parseArray(competingCompanyList, Long.class);

		// 根据公司类型判断价格
		salePriceBycompanyTypeJudge(dto,companys,demoCompanys,competingCompanys);

		if(EmptyUtil.isEmpty(companys) && EmptyUtil.isEmpty(demoCompanys) && EmptyUtil.isEmpty(competingCompanys)){
			throw new BusinessException("请选择一个公司");
		}

		// 根据公司类型判断价格
		salePriceBycompanyTypeJudge(dto,companys,demoCompanys,competingCompanys);
		
		if(EmptyUtil.isEmpty(dto.getStoreId())){
			throw new BusinessException("请选择一个总店");
		}
		if (EmptyUtil.isNotEmpty(companyList) && EmptyUtil.isEmpty(demoCompanyList) && EmptyUtil.isNotEmpty(competingCompanyList)) {
			if(EmptyUtil.isEmpty(dto.getSalePrice())){
				throw new BusinessException("请输入商品销售价格");
			}
		}
		//数据验证
		verification(dto);

		Long rt=merchantProductFacade.insertMerchantProductWithTx(
				dto,picture,pictureList,webBannerPictureList,
				sellPlatformMerchantProdVOList,clientList,content,
				productUnitList,tagList,keywords,companys,demoCompanys,competingCompanys,
				storeIds,membershipIds);
		return JsonResult.success(rt);
	}
	/**
	 * 根据公司类型判断价格
	 * @param dto
	 * @param companys
	 * @param demoCompanys
	 * @param competingCompanys
	 */
	private void salePriceBycompanyTypeJudge(MerchantProductDTO dto, List<Long> companys, List<Long> demoCompanys,
											 List<Long> competingCompanys) {
		if(companys != null){
			if (companys.size() == 0)
				throw new BusinessException("请选择正式公司");
			if (EmptyUtil.isEmpty(dto.getSalePrice()) || dto.getSalePrice().doubleValue() <= 0)
				throw new BusinessException("请填写正式公司销售价格");
		}else{
			dto.setSalePrice(BigDecimal.valueOf(0.00));
		}
		if(demoCompanys != null){
			if (demoCompanys.size() == 0)
				throw new BusinessException("请选择演示公司");
			if (EmptyUtil.isEmpty(dto.getDemoSalePrice()) || dto.getDemoSalePrice().doubleValue() <= 0)
				throw new BusinessException("请填写演示公司销售价格");
		}else{
			dto.setDemoSalePrice(BigDecimal.valueOf(0.00));
		}
		if(competingCompanys != null){
			if (competingCompanys.size() == 0) {
				throw new BusinessException("请选择竞品公司");
			}
			if (EmptyUtil.isEmpty(dto.getCompetingSalePrice()) || dto.getCompetingSalePrice().doubleValue() <= 0)
				throw new BusinessException("请填写竞品公司销售价格");
		}else{
			dto.setCompetingSalePrice(BigDecimal.valueOf(0.00));
		}

	}

	@Override
	public int updateMerchantProductWithTx(
			MerchantProductDTO dto,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList,
			String clientList,
			String companyList,
			String content,
			List<Long> tagList,
			List<String> keywords,
			String demoCompanyList,
			String competingCompanyList,
			String webBannerPictureList,
			List<Long> storeIdList,
			List<Long> membershipIdList) {

		List<Long> companys = null; // 正式公司集合
		List<Long> demoCompanys = null; // 演示公司集合
		List<Long> competingCompanys = null; // 竞品公司集合

		if(companyList != null)
			companys = JSONArray.parseArray(companyList, Long.class);
		if(demoCompanyList != null)
			demoCompanys = JSONArray.parseArray(demoCompanyList, Long.class);
		if(competingCompanyList != null)
			competingCompanys = JSONArray.parseArray(competingCompanyList, Long.class);

		// 根据公司类型判断价格
		salePriceBycompanyTypeJudge(dto,companys,demoCompanys,competingCompanys);

		if(EmptyUtil.isEmpty(companys) && EmptyUtil.isEmpty(demoCompanys) && EmptyUtil.isEmpty(competingCompanys)){
			throw new BusinessException("请选择一个公司");
		}

		if (EmptyUtil.isNotEmpty(companyList) && EmptyUtil.isEmpty(demoCompanyList) && EmptyUtil.isNotEmpty(competingCompanyList)) {
			if(EmptyUtil.isEmpty(dto.getSalePrice())){
				throw new BusinessException("请输入商品销售价格");
			}
		}
		//数据验证
		verification(dto);
		dto.setContent(content);

		//修改商品详情信息
		return merchantProductFacade.updateMerchantProductWithTx(
				dto,sellPlatformMerchantProdVOList,clientList,tagList,keywords,companys,demoCompanys,competingCompanys,webBannerPictureList,
				storeIdList,membershipIdList);
	}

	@Override
	public int deleteMerchantProductWithTx(MerchantProductDTO dto) {
		return merchantProductFacade.deleteMerchantProductWithTx(dto);
	}
	/**
	 * 提交审核
	 * @param dto
	 * @return
	 */
	@Override
	public int submitAuditWithTx(MerchantProductDTO dto) {
		// TODO Auto-generated method stub
		return merchantProductFacade.submitAuditWithTx(dto);
	}

	@Override
	public int updateMerchantProductPictureByIdWithTx(
			Long merchantProductId,
			String picture,
			List<String> pictures,
			List<String> webBannerPictures,
			MerchantProductDTO merchantProductDTO) {
		MerchantProdClientDTO merchantProdClientDTO = new MerchantProdClientDTO();
		merchantProdClientDTO.setMerchantProductId(merchantProductId);
		List<MerchantProdClientDTO> merchantProdClientList = merchantProdClientFacade.findMerchantProdClientAll(merchantProdClientDTO);
		if(EmptyUtil.isEmpty(merchantProdClientList))
			throw new BusinessException("该su商品不存在客户端信息，请先选择客户端信息");
		List<Long> clientids = new ArrayList<>();
		for (MerchantProdClientDTO merchantProdClientDTO2 : merchantProdClientList) {
			clientids.add(merchantProdClientDTO2.getClientId());
		}
		// 根据su草稿id查询su草稿客户端信息
		if(EmptyUtil.isEmpty(picture))
			throw new BusinessException("列表图不能为空");
		// 如果客户端id包含app端或微信端id并且其轮播图图片为空
		if((clientids.contains(BusinessConstant.APP_CLIENT_Id) || clientids.contains(BusinessConstant.WEIXIN_CLIENT_ID)) && EmptyUtil.isEmpty(pictures))
			throw new BusinessException("商品app端或微信端轮播图不能为空");

		// 如果客户端id包含web客户端id并且其轮播图图片为空
		/*if(clientids.contains(BusinessConstant.WEB_CLIENT_ID) && EmptyUtil.isEmpty(webBannerPictures))
			throw new BusinessException("商品web端轮播图不能为空");*/

		return merchantProductFacade.updateMerchantProductPictureByIdWithTx(merchantProductId,picture, pictures,webBannerPictures,merchantProductDTO);
	}

	/**
	 * 根据su草稿id更新supu信息
	 * @param productUnitList
	 * @param merchantProductDTO
	 * @return
	 */
	@Override
	public int updateProductUnitByIdWithTx(List<ProductUnitVO> productUnitList,MerchantProductDTO merchantProductDTO ,
										   List<Long> companyIds,List<Long> demoCompanyIds,List<Long> competingCompanyIds) {
		if(EmptyUtil.isEmpty(merchantProductDTO.getStatus())){
			merchantProductDTO.setStatus(1);
		}
		return merchantProductFacade.updateProductUnitByIdWithTx(ProductUnitConverter.toDTOs(productUnitList),merchantProductDTO, companyIds,demoCompanyIds,competingCompanyIds);
	}

	/**
	 *  批量通过
	 * @param ids
	 * @param platformId
	 * @param req
	 * @return
	 */
	@Override
	public int passAllAuditWithTx(String ids,Long platformId,HttpServletRequest req) {
		int i = 0;
		List<Long> list = JSONArray.parseArray(ids, Long.class);
		for (Long merchantProductId : list) {
			// 通过类型为1
			i = i + merchantProductPassWithTx(merchantProductId,null,1,platformId,req);
		}
		return i;
	}

	/**
	 * 根据id通过
	 * @param merchantProductId
	 * @param cause
	 * @param type
	 * @param platformId
	 * @param req
	 * @return
	 */
	@Override
	public int merchantProductPassWithTx(Long merchantProductId,String cause,int type,Long platformId,HttpServletRequest req) {
		int i;
		MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
		merchantProductDTO.setId(merchantProductId);
		if(type == 0){
			MerchantProdCauseDTO merchantProdCauseDTO = new MerchantProdCauseDTO();
			merchantProdCauseDTO.setMerchantProdId(merchantProductId);
			merchantProdCauseDTO.setCause(cause);
			merchantProdCauseDTO.setPlatformId(platformId);
			Long merchantProdCauseId = merchantProdCauseManage.insertMerchantProdCauseWithTx(merchantProdCauseDTO);
			// 如果成功添加不通过原因、记录日志并且返回
			if(EmptyUtil.isNotEmpty(merchantProdCauseId)){
				MerchantProductDTO merchantProductDTO2 = merchantProductFacade.findMerchantProductById(merchantProductDTO);
				EgeoLog log = new EgeoLog();
				log.setMsgId(LogConstant.STANDARDUNIT_NO_PASS.getStatus());
				log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
				log.setOperObject("MerchantProductManageImpl_merchantProductPassWithTx");
				log.setOperatorObjId(merchantProductId);
				log.setOperatorName(merchantProductDTO2.getUpdateUsername());
				log.setOperatorId(merchantProductDTO2.getUpdateUserid());
				log.setIp(merchantProductDTO2.getUpdateUserip());
				log.setTime(merchantProductDTO2.getUpdateTime());
				log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
				log.setNewObj(merchantProdCauseDTO);
				log.setOperatorObjName(merchantProductDTO2.getName());
				log.setOperatorObjCode(merchantProductDTO2.getMerchantProductSerialNumber());
				EgeoBusinessLogCommon.fillLogValue(log, req);

				try {
					ActiveMQUtils.recordBusinessLog(log);
				}catch (Exception e) {
					// TODO: handle exception
					logger.error("发送日志消息失败："+ JSON.toJSONString(log));
				}
				return 1;
			}else{
				return 0;
			}
		}else{
			StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
			standardUnitDTO.setId(merchantProductId);
			StandardUnitDTO standardUnitDTO2 = standardUnitFacade.findStandardUnitById(standardUnitDTO);
			// 判断是否存在、不存在则是新增、存在则是修改
			if(standardUnitDTO2 == null){
				i = merchantProductFacade.merchantProductPassWithTx(merchantProductId,platformId);

				StandardUnitExportVO newObj = standardUnitFacade.queryByStandardUnitId(merchantProductId);

				EgeoLog log = new EgeoLog();
				log.setMsgId(LogConstant.STANDARDUNIT_SAVE.getStatus());
				log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
				log.setOperObject("MerchantProductManageImpl_merchantProductPassWithTx");
				log.setOperatorObjId(merchantProductId);
				log.setOperatorName(newObj.getUpdateUsername());
				log.setOperatorId(newObj.getUpdateUserid());
				log.setIp(newObj.getUpdateUserip());
				log.setTime(newObj.getUpdateTime());
				log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
				log.setNewObj(newObj);
				log.setOperatorObjName(String.valueOf(newObj.getStandardUnitName()));
				log.setOperatorObjCode(String.valueOf(newObj.getMerchantProductSerialNumber()));
				EgeoBusinessLogCommon.fillLogValue(log, req);

				try {
					ActiveMQUtils.recordBusinessLog(log);
				}catch (Exception e) {
					// TODO: handle exception
					logger.error("发送日志消息失败："+ JSON.toJSONString(log));
				}

				// 发送审核消息
				sendAuditInfo(newObj,req);
			}else{
				StandardUnitExportVO oldObj = standardUnitFacade.queryByStandardUnitId(merchantProductId);

				i = merchantProductFacade.merchantProductPassWithTx(merchantProductId,platformId);

				StandardUnitExportVO newObj = standardUnitFacade.queryByStandardUnitId(merchantProductId);

				List<PuExportVO> oldObjPuList = oldObj.getProductUnitList();
				List<PuExportVO> newObjPuList = newObj.getProductUnitList();

				/**
				 * 把不要判断的设置为空
				 */
				oldObj.setClientList(null);
				newObj.setClientList(null);
				oldObj.setProductUnitList(null);
				newObj.setProductUnitList(null);
				List<Object> diff = LogUtil.getObjDifference(oldObj, newObj);

				// su基础信息修改则发送消息
				if(EmptyUtil.isNotEmpty(diff)){
					// 发送审核su基础信息
					EgeoLog log = new EgeoLog();
					log.setMsgId(LogConstant.STANDARDUNIT_UPDATE.getStatus());
					log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
					log.setOperObject("MerchantProductManageImpl_merchantProductPassWithTx");
					log.setOperatorObjId(merchantProductId);
					log.setOperatorName(newObj.getUpdateUsername());
					log.setOperatorId(newObj.getUpdateUserid());
					log.setIp(newObj.getUpdateUserip());
					log.setTime(newObj.getUpdateTime());
					log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
					log.setOperatorObjName(String.valueOf(newObj.getStandardUnitName()));
					log.setOperatorObjCode(String.valueOf(newObj.getMerchantProductSerialNumber()));
					//设置msg详情
					log.setMsgContent(JSON.toJSONString(diff));
					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}


				List<Object> puDiff = new ArrayList<>();
				for (int j = 0; j < newObjPuList.size(); j++) {
					List<Object> list = LogMsgContentEntityToMap.getObjDifference(oldObjPuList.get(j), newObjPuList.get(j), newObjPuList.get(j).getClass(),newObjPuList.get(j).getName());
					puDiff.addAll(list);
				}
				// pu信息修改发送日志修改消息
				if(EmptyUtil.isNotEmpty(puDiff)){
					// 发送pu审核信息
					EgeoLog puLog = new EgeoLog();
					puLog.setMsgId(LogConstant.STANDARDUNIT_PU_UPDATE.getStatus());
					puLog.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
					puLog.setOperObject("MerchantProductManageImpl_merchantProductPassWithTx");
					puLog.setOperatorObjId(merchantProductId);
					puLog.setOperatorName(newObj.getUpdateUsername());
					puLog.setOperatorId(newObj.getUpdateUserid());
					puLog.setIp(newObj.getUpdateUserip());
					puLog.setTime(newObj.getUpdateTime());
					puLog.setType(LogTypeConstant.STANDARDUNIT.getStatus());
					puLog.setOperatorObjName(String.valueOf(newObj.getStandardUnitName()));
					puLog.setOperatorObjCode(String.valueOf(newObj.getMerchantProductSerialNumber()));
					//设置msg详情
					puLog.setMsgContent(JSON.toJSONString(puDiff));
					EgeoBusinessLogCommon.fillLogValue(puLog, req);

					try {
						ActiveMQUtils.recordBusinessLog(puLog);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(puLog));
					}
				}

				// 发送审核消息
				sendAuditInfo(newObj,req);
			}
			return i;
		}

	}

	/**
	 * 发送审核消息
	 * @param newObj
	 * @param req
	 */
	private void sendAuditInfo(StandardUnitExportVO newObj, HttpServletRequest req) {
		EgeoLog log = new EgeoLog();
		log.setMsgId(LogConstant.STANDARDUNIT_PASS.getStatus());
		log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
		log.setOperObject("MerchantProductManageImpl_merchantProductPassWithTx");
		log.setOperatorObjId(newObj.getId());
		log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
		log.setOperatorObjName(String.valueOf(newObj.getStandardUnitName()));
		log.setOperatorObjCode(String.valueOf(newObj.getMerchantProductSerialNumber()));
		EgeoBusinessLogCommon.fillLogValue(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}

	}

	private void verification(MerchantProductDTO merchantProductDTO){
		if(EmptyUtil.isEmpty(merchantProductDTO.getName())){
			throw new BusinessException("请输入商品名称");
		}
		/*if(EmptyUtil.isEmpty(merchantProductDTO.getSalePrice())){
			throw new BusinessException("请输入商品销售价格");
		}*/
		if(EmptyUtil.isEmpty(merchantProductDTO.getMarketPrice())){
			throw new BusinessException("请输入商品市场价格");
		}
		if(EmptyUtil.isEmpty(merchantProductDTO.getSaleWay())){
			throw new BusinessException("请选择销售方式");
		}
		if(EmptyUtil.isEmpty(merchantProductDTO.getMerchantId())){
			throw new BusinessException("请选择商家");
		}
		if(EmptyUtil.isEmpty(merchantProductDTO.getStoreId())){
			throw new BusinessException("请选择总店");
		}
		if(EmptyUtil.isEmpty(merchantProductDTO.getFrontSerialNumber())){
			throw new BusinessException("请输入前端编号");
		}else if(merchantProductDTO.getFrontSerialNumber() > 999999999 || merchantProductDTO.getFrontSerialNumber() < 0){
			throw new BusinessException("前端编号请输入 0 到 999999999正整数");
		}
	}
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public Map<String, Object> findMerchantProductAppById(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProductFacade.findMerchantProductAppById(merchantProductId);
	}

	@Override
	public PageResult<MerchantProductDTO> findCommodityDetailsOfPage(CommodityDetailsDTO dto, Pagination page) {
		return merchantProductFacade.findCommodityDetailsOfPage(dto, page);
	}

	@Override
	public JsonResult<List<Map<String,Object>>> findPuNameBySuIdBackStage(Long suId) {
		List<Map<String,Object>> result = merchantProductFacade.findPuNameBySuIdBackStage(suId);
		return JsonResult.success(result);
	}

	@Override
	public long findMaxfrontSerialNumber() {
		// TODO Auto-generated method stub
		return merchantProductFacade.findMaxfrontSerialNumber();
	}
	
	

}
	