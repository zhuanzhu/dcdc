package com.egeo.components.product.facade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.common.PageResults;
import com.egeo.components.stock.dto.ResidueStockNumConditionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.cms.client.FunctionModuleCategoryClient;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.client.LimitRuleClient;
import com.egeo.components.order.dto.StartLimitRuleByStandardUnitIdDTO;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.JdProductReadService;
import com.egeo.components.product.service.read.MembershipReadService;
import com.egeo.components.product.service.read.MerchantProdSalesRecordReadService;
import com.egeo.components.product.service.read.MerchantReadService;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.read.SkuAttNameReadService;
import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.components.product.service.read.StandardUnitClientReadService;
import com.egeo.components.product.service.read.StandardUnitCompanyReadService;
import com.egeo.components.product.service.read.StandardUnitDescribeReadService;
import com.egeo.components.product.service.read.StandardUnitMembershipReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.read.StandardUnitStoreReadService;
import com.egeo.components.product.service.read.StandardUnitTagReadService;
import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.service.read.SuSerachKeywordReadService;
import com.egeo.components.product.service.write.MerchantProductWriteService;
import com.egeo.components.product.service.write.StandardUnitWriteService;
import com.egeo.components.product.service.write.SuSerachRuleWriteService;
import com.egeo.components.product.vo.PuExportVO;
import com.egeo.components.product.vo.StandardUnitExportVO;
import com.egeo.components.promotion.client.CouponClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.user.client.ClientClient;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;


@Component
public class StandardUnitFacade {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private StandardUnitReadService standardUnitReadService;

	@Resource
	private StandardUnitWriteService standardUnitWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;

	@Autowired
	private MerchantProductWarehouseStockClient merchantProductWarehouseStockReadService;

	@Resource
	private SkuAttNameReadService skuAttNameReadService;

	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;

	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;

	@Resource
	private MerchantProdSalesRecordReadService merchantProdSalesRecordReadService;

	@Autowired
	private FunctionModuleCategoryClient functionModuleCategoryReadService;

	@Resource
	private StandardUnitDescribeReadService standardUnitDescribeReadService;

	@Autowired
	private UserAccountClient userAccountReadService;

	@Autowired
	private SaltClient saltReadService;
	@Autowired
	private CouponClient couponReadService;

	@Autowired
	private UserExtendClient userExtendReadService;

	@Autowired
	private UserClient userReadService;
	@Resource
	private StandardUnitCompanyReadService standardUnitCompanyReadService;

	@Resource
	private StandardUnitClientReadService standardUnitClientReadService;

	@Autowired
	private CompanyClient companyReadService;

	@Autowired
	private ClientClient clientReadService;

	@Resource
	private PictureReadService pictureReadService;

	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;

	@Resource
	private SkuReadService skuReadService;
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockReadService;

	@Resource
	private StandardProductUnitReadService standardProductUnitReadService;
	@Resource
	private StoreProductUnitReadService storeProductUnitReadService;

	@Resource(name="merchantProductFacade")
	private MerchantProductFacade merchantProductFacade;

	@Resource(name="freightTemplateFacade")
	private FreightTemplateFacade freightTemplateFacade;

	@Resource
	private StandardUnitTagReadService standardUnitTagReadService;

	@Autowired
	private LimitRuleClient limitRuleReadService;

	@Autowired
	private CouponUnitClient couponUnitReadService;

	@Resource(name="standardProductUnitAttValueFacade")
	private StandardProductUnitAttValueFacade standardProductUnitAttValueFacade;

	@Resource
	private SuSerachKeywordReadService suSerachKeywordReadService;

	@Resource
	private SuSerachRuleWriteService suSerachRuleWriteService;

	@Autowired
	private CompanyCoreClient companyCoreReadService;

	@Resource
	private StandardUnitMembershipReadService standardUnitMembershipReadService;

	@Resource
	private MembershipReadService membershipReadService;

	@Resource
	private StandardUnitStoreReadService standardUnitStoreReadService;

	@Resource
	private StoreReadService storeReadService;
	@Resource
	private MerchantReadService merchantReadService;
	@Resource
	private JdProductReadService jdProductReadService;
	@Resource
	private MerchantProductWriteService merchantProductWriteService;


	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;

	public StandardUnitDTO findStandardUnitById(StandardUnitDTO dto){
		if(dto.isJd()) {
			JdProductDTO dd = new JdProductDTO();
			dd.setId(dto.getId());
			JdProductDTO jdDto = jdProductManage.findJdProductById(dd);
			StandardUnitDTO rslt = StandardUnitConverter.toDTO(jdDto);
			return rslt;
		}else {
			return standardUnitReadService.findStandardUnitById(dto);
		}
	}

	public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitDTO dto,Pagination page){

		return standardUnitReadService.findStandardUnitOfPage(dto, page);

	}

	public List<StandardUnitDTO> findStandardUnitAll(StandardUnitDTO dto){

		return standardUnitReadService.findStandardUnitAll(dto);

	}

	public Long insertStandardUnitWithTx(StandardUnitDTO dto){

		return standardUnitWriteService.insertStandardUnitWithTx(dto);
	}

	public int updateStandardUnitWithTx(StandardUnitDTO dto){

		return standardUnitWriteService.updateStandardUnitWithTx(dto);
	}

	public int deleteStandardUnitWithTx(StandardUnitDTO dto){

		return standardUnitWriteService.deleteStandardUnitWithTx(dto);

	}
	/**
	 * 根据su草稿id集合查询su信息
	 * @param ids
	 * @return
	 */
	public List<StandardUnitDTO> findBymerchantProdId(List<Long> ids) {
		// TODO Auto-generated method stub
		return standardUnitReadService.findBymerchantProdId(ids);
	}
	/**
	 * su上下架
	 * @return
	 */
	public int putawaySoldOut(StandardUnitDTO dto,int type) {
		// TODO Auto-generated method stub
		return standardUnitWriteService.putawaySoldOutWithTx(dto,type);
	}
	/**
	 * 根据spuid查询所有su的条数
	 * @param standardProductUnitId
	 * @return
	 */
	public int countRecord(Long standardProductUnitId) {
		// TODO Auto-generated method stub
		return standardUnitReadService.countRecord(standardProductUnitId);
	}
	/**
	 * 查询su库存信息
	 * @param dto
	 * @return
	 */
	public List<CommodityProductUnitWarehouseStockDTO> standardProductStock(StandardUnitDTO dto){
		CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO = new CommodityProductUnitWarehouseStockDTO();
		commodityProductUnitWarehouseStockDTO.setStandardUnitId(dto.getId());
		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockAll(commodityProductUnitWarehouseStockDTO);
	}

	/**
	 * 查询spu库存信息
	 * @return
	 */
	public List<MerchantProductWarehouseStockDTO> standardProductStock(Long standardProductUnitId){
		MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO = new MerchantProductWarehouseStockDTO();
		merchantProductWarehouseStockDTO.setStandardProductUnitId(standardProductUnitId);
		return merchantProductWarehouseStockReadService.findAll(merchantProductWarehouseStockDTO);
	}
	/**
	 * 根据skuid查询sku属性和属性值集合
	 * @param skuId
	 * @return
	 */
	public List<Map<String, Object>> findAttNameValue(Long skuId) {
		List<Map<String, Object>> attNameValueList = new ArrayList<>();
		//根据skuid查询sku属性信息
		List<SkuAttNameDTO> list = skuAttNameReadService.findSkuAttNameByskuId(skuId);
		for (SkuAttNameDTO skuAttNameDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", skuAttNameDTO.getName());
			map.put("value", skuAttNameDTO.getValue());
			attNameValueList.add(map);
		}
		return attNameValueList;
	}
	/**
	 * app商品列表
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> findStandardUnitOfPageAPP(StandardUnitDTO standardUnitDTO, Pagination page) {
		return standardUnitReadService.findStandardUnitOfPageAPP(standardUnitDTO, page);
	}
	/**
	 * 根据类目节点id查询su商品信息
	 * @return
	 */
	public PageResult<StandardUnitDTO> standardUnitStockByCategoryTreeNodeId(StandardUnitDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		return standardUnitReadService.standardUnitStockByCategoryTreeNodeId(dto, page);
	}
	/**
	 * 根据suid查询su详情
	 * @param 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 * @param skuDTO
	 *@param standardUnitId  @return
	 */
	public Map<String, Object> findByStandardUnitId(SkuDTO skuDTO, Long standardUnitId, Long companyId, Long platformId, Integer companyType, Long storeId, Long userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> standardUnitMap = new HashMap<>();
		List<Map<String, Object>> maps = new ArrayList<>();
		// 根据su商品id查询su商品信息
		StandardUnitDTO standardUnitDTO2 = standardUnitReadService.findStandardUnitById(standardUnitId);
		//购买方式
		standardUnitMap.put("buyType",standardUnitDTO2.getBuyType());

		standardUnitMap.put("standardUnitId", standardUnitDTO2.getId());
		standardUnitMap.put("standardUnitName", standardUnitDTO2.getName());
		standardUnitMap.put("status", standardUnitDTO2.getStatus());
		// 设置su商品销售价格
		standardUnitMap.put("salePrice", settingSalePrice(standardUnitDTO2,companyType));
		standardUnitMap.put("marketPrice", standardUnitDTO2.getMarketPrice());

		standardUnitMap.put("freightExplain", standardUnitDTO2.getFreightExplain());
		standardUnitMap.put("shipmentsExplain", standardUnitDTO2.getShipmentsExplain());

		standardUnitMap.put("content", standardUnitDTO2.getContent());
		standardUnitMap.put("contentUrl", standardUnitDTO2.getContentUrl());
		if(EmptyUtil.isNotEmpty(standardUnitDTO2.getSalesVolume())){
			standardUnitMap.put("salesVolume", standardUnitDTO2.getSalesVolume() + standardUnitDTO2.getSoldBase());
		}else{
			standardUnitMap.put("salesVolume", standardUnitDTO2.getSoldBase());
		}
		if(standardUnitDTO2.getMerchantId().equals(1L)){
			standardUnitMap.put("isOwnMerchant", 1);
		}else{
			standardUnitMap.put("isOwnMerchant", 0);
		}
		// 运费模版赋值
		setFreightExplainAndShipmentsExplain(standardUnitMap,standardUnitDTO2);
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		// 根据su商品id查询限购规则信息
		Map<Long, List<Long>> suCombMap = standardUnitReadService.findSuCombinationMap(standardUnitDTO2.getId(), platformId);
		List<String> list = limitRuleReadService.startLimitRuleByStandardUnitId(new StartLimitRuleByStandardUnitIdDTO(standardUnitDTO2.getId(), companyId,companyAllId, platformId, userId, storeId, suCombMap));
		if(EmptyUtil.isNotEmpty(list)){
			String str = "";
			for (String s : list) {
				str += "," + s;
			}
			standardUnitMap.put("limitRule", str.substring(1));
		}else{
			standardUnitMap.put("limitRule", null);
		}

		standardUnitMap.put("commodityTemplateId", standardUnitDTO2.getCommodityTemplateId());
		standardUnitMap.put("storeId", standardUnitDTO2.getStoreId());
		// 根据总店id查询门店信息
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(standardUnitDTO2.getStoreId());
		StoreDTO storeDTO2 = storeReadService.findStoreById(storeDTO);
		// 赋值总店码
		standardUnitMap.put("activityCode", storeDTO2.getActivityCode());

		// 获取预售说明
		String presellPeriod = getPresellPeriod(standardUnitDTO2.getSaleWay(),standardUnitDTO2.getPresellPeriod());
		standardUnitMap.put("presellPeriod", presellPeriod);
		standardUnitMap.put("relevanceSuId", findRelevanceSu(standardUnitDTO2.getRelevanceSuId(), storeId));
		standardUnitMap.put("saleWay", standardUnitDTO2.getSaleWay());
		//设置运营方名称
		standardUnitMap.put("merchantName", findMerchantName(standardUnitDTO2.getMerchantId()));


		String standardUnitMembersExplain = null;
		// 商品销售方式：1正常销售、2团购、3、兑换卷 4、普通预售 5、会籍购买 6、会籍预售 7、预售
		if(standardUnitDTO2.getSaleWay() == 6){
			// 获取会籍说明
			standardUnitMembersExplain = getStandardUnitMembersExplain(standardUnitId,platformId);
		}
		standardUnitMap.put("standardUnitMembersExplain", standardUnitMembersExplain);
		// 根据suid查询su参数属性
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameList = standardProductUnitAttNameReadService.findByStandardProductUnitId(standardUnitDTO2.getStandardProductUnitId());
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO2 : standardProductUnitAttNameList) {
			Map<String, Object> map = new HashMap<>();
			map.put("attName", standardProductUnitAttNameDTO2.getAttName());
			if(standardProductUnitAttNameDTO2.getMode() == 2){
				//根据su属性id查询su属性值信息
				StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
				standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
				if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
					map.put("attValue", standardProductUnitAttValueList.get(0).getAttValueCustom());
				}

			}else if(standardProductUnitAttNameDTO2.getMode() == 5 || standardProductUnitAttNameDTO2.getMode() == 6){
				//根据su属性id查询su属性值信息
				StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
				standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
				if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
					map.put("attValue", new String(standardProductUnitAttValueList.get(0).getAttValueCustom() + standardProductUnitAttNameDTO2.getUnit()));
				}
			}else if(standardProductUnitAttNameDTO2.getMode() == 1 || standardProductUnitAttNameDTO2.getMode() == 3){
				//根据su属性id查询属性值信息
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < standardProductUnitAttValueList.size(); i++) {
					if(i >= standardProductUnitAttValueList.size() - 1){
						stringBuffer.append(standardProductUnitAttValueList.get(i).getAttValue());
					}else{
						stringBuffer.append(standardProductUnitAttValueList.get(i).getAttValue());
						stringBuffer.append(",");
					}

				}
				map.put("attValue", stringBuffer.toString());
				map.put("unit", standardProductUnitAttNameDTO2.getUnit());
			}else if(standardProductUnitAttNameDTO2.getMode() == 4){
				//根据su属性id查询su属性值信息
				StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
				standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
				if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
					if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList.get(0).getAttValueCustom())){
						String string = standardProductUnitAttValueList.get(0).getAttValueCustom();
				        String str = sdf.format(new Date(Long.valueOf(string)));
						map.put("attValue", str);
					}else{
						map.put("attValue", null);
					}

				}

			}


			maps.add(map);

		}


		if (standardUnitDTO2.getMerchantId().equals(6L)) {
			//京东商品描述
			String externalSkuId = skuDTO.getExternalSkuId();
			JdProductDTO jdProductDTO = new JdProductDTO();
			jdProductDTO.setId(Long.valueOf(externalSkuId));
			JdProductDTO jdProductById = jdProductReadService.findJdProductById(jdProductDTO);
			if(EmptyUtil.isEmpty(jdProductById)){
				throw new BusinessException("京东商品不存在");
			}
			standardUnitMap.put("content", jdProductById.getIntroduction().replaceAll("width:750px", "width:100%"));
			Map<String, Object> map = new HashMap<>();
			List<Map<String, Object>> mapList = new ArrayList<>();
			map.put("attValue",jdProductById.getParam());
			mapList.add(map);
			standardUnitMap.put("attList", mapList);
			//京东商品状态
			Integer state = jdProductById.getState();
			if(state==1&&standardUnitDTO2.getStatus()==3){
				standardUnitMap.put("status", standardUnitDTO2.getStatus());
			}else{
				standardUnitMap.put("status", 4);

			}


		}else{
			standardUnitMap.put("attList", maps);
		}



		return standardUnitMap;
	}

	private String findMerchantName(Long merchantId) {
		MerchantDTO dto = new MerchantDTO();
		dto.setId(merchantId);
		MerchantDTO findMerchant = merchantReadService.findMerchantById(dto);
		if (findMerchant != null) {
			return findMerchant.getName();
		} else {
			return "";
		}
	}

	private Long findRelevanceSu(Long suId, Long storeId) {
		if (EmptyUtil.isEmpty(storeId) || EmptyUtil.isEmpty(suId)) {
			return null;
		}
		if (storeId.equals(PlatformKeyConstant.MYY_ROOT_STORE_ID) || storeId.equals(PlatformKeyConstant.FGJ_ROOT_STORE_ID)) {
			StandardUnitDTO standardUnitDTO = standardUnitReadService.findStandardUnitById(suId);
			if (EmptyUtil.isEmpty(standardUnitDTO) || !storeId.equals(standardUnitDTO.getStoreId())) {
				return null;
			}
			return suId;
		}
		StandardUnitStoreDTO sut = new StandardUnitStoreDTO();
		sut.setStandardUnitId(suId);
		sut.setStoreId(storeId);
		List<StandardUnitStoreDTO> sutList = standardUnitStoreReadService.findStandardUnitStoreAll(sut);
		if (EmptyUtil.isNotEmpty(sutList)) {
			return suId;
		}
		return null;
	}

	/**
	 * 获取会籍说明
	 * @param standardUnitId
	 * @param platformId
	 * @return
	 */
	private String getStandardUnitMembersExplain(Long standardUnitId, Long platformId) {
		StringBuffer standardUnitMembersExplain = new StringBuffer();
		// 如果是会籍预售则查询su会籍关系
		StandardUnitMembershipDTO standardUnitMembershipDTO = new StandardUnitMembershipDTO();
		standardUnitMembershipDTO.setStandardUnitId(standardUnitId);
		standardUnitMembershipDTO.setPlatformId(platformId);
		List<StandardUnitMembershipDTO> standardUnitMembersList = standardUnitMembershipReadService.findStandardUnitMembershipAll(standardUnitMembershipDTO);

		if(EmptyUtil.isNotEmpty(standardUnitMembersExplain)){
			standardUnitMembersExplain.append("会籍预售商品，仅限");
		}
		for (int i = 0; i < standardUnitMembersList.size(); i++) {
			// 根据会籍id查询会籍详情
			MembershipDTO membershipDTO = new MembershipDTO();
			membershipDTO.setId(standardUnitMembersList.get(i).getMembershipId());
			MembershipDTO membershipDTO2 = membershipReadService.findMembershipById(membershipDTO);
			standardUnitMembersExplain.append(membershipDTO2.getMembershipName());
			if(i + 1 < standardUnitMembersList.size()){
				standardUnitMembersExplain.append("，");
			}else{
				standardUnitMembersExplain.append("购买");
			}
		}
		return standardUnitMembersExplain.toString();
	}
	/**
	 * 获取预售说明
	 * @param saleWay
	 * @param presellPeriod
	 * @return
	 */
	private String getPresellPeriod(Integer saleWay, Long presellPeriod) {
		if(saleWay == 4 || saleWay == 6){
			return "该商品为预售商品，提交订单"+ presellPeriod +"天后发货";
		}
		return null;
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

	/**
	 * 运费模版赋值
	 * @param standardUnitMap
	 * @param standardUnitDTO2
	 */
	private void setFreightExplainAndShipmentsExplain(Map<String, Object> standardUnitMap,
			StandardUnitDTO standardUnitDTO2) {
		// 判断是否有unit库存
		if(merchantProductFacade.queryIsUnit(standardUnitDTO2.getStandardProductUnitId())){
			standardUnitMap.put("freightExplain", "电子卡券，无需运费");
			if(EmptyUtil.isEmpty(standardUnitDTO2.getShipmentsExplain())){
				standardUnitMap.put("shipmentsExplain", "购买后闪电发送卡号/密，订单详情页可查看");
			}

		}else{
			Map<String, Object> freightTemplateByMap = freightTemplateFacade.freightTemplateByMerchantId(standardUnitDTO2.getMerchantId(),standardUnitDTO2.getStoreId(),standardUnitDTO2.getPlatformId());

			standardUnitMap.put("freightExplain", String.valueOf(freightTemplateByMap.get("freightRegulation")));
			if(EmptyUtil.isEmpty(standardUnitDTO2.getShipmentsExplain())){
				standardUnitMap.put("shipmentsExplain", String.valueOf(freightTemplateByMap.get("shipmentsExplain")));
			}
		}

	}

	/**
	 * 运费模版赋值
	 * @param standardUnitDTO2
	 */
	private void setFreightExplainAndShipmentsExplain(StandardUnitExportVO standardUnitExportVO,
			StandardUnitDTO standardUnitDTO2) {
		// 判断是否有unit库存
		if(merchantProductFacade.queryIsUnit(standardUnitDTO2.getStandardProductUnitId())){
			standardUnitExportVO.setFreightExplain("电子卡券，无需运费");
			if(EmptyUtil.isEmpty(standardUnitDTO2.getShipmentsExplain())){
				standardUnitExportVO.setShipmentsExplain("购买后闪电发送卡号/密，订单详情页可查看");
			}
		}else{
			Map<String, Object> freightTemplateByMap = freightTemplateFacade.freightTemplateByMerchantId(standardUnitDTO2.getMerchantId(),standardUnitDTO2.getStoreId(),standardUnitDTO2.getPlatformId());

			standardUnitExportVO.setFreightExplain(String.valueOf(freightTemplateByMap.get("freightRegulation")));
			if(EmptyUtil.isEmpty(standardUnitDTO2.getShipmentsExplain())){
				standardUnitExportVO.setShipmentsExplain(String.valueOf(freightTemplateByMap.get("shipmentsExplain")));
			}
		}

	}

	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	public Long findSalesRecordByStandardUnitId(Long standardUnitId) {

		return merchantProdSalesRecordReadService.findSalesRecordByStandardUnitId(standardUnitId);
	}
	/**
	 * 根据条件查询所有上架suid和名称
	 * @return
	 */
	public List<StandardUnitDTO> findStandardUnitIdAndName(StandardUnitDTO dto) {
		// TODO Auto-generated method stub
		return standardUnitReadService.findStandardUnitIdAndName(dto);
	}
	/**
	 * 根据功能模版id查询su商品信息(此接口废除)
	 * @
	 * @return
	 */
	public PageResult<StandardUnitDTO> standardUnitByFunctionModuleId(StandardUnitDTO dto, Long functionModuleId,
			Pagination page) {

		List<Long> categoryTreeNodeIdList = new ArrayList<>();
		FunctionModuleCategoryDTO functionModuleCategoryDTO = new FunctionModuleCategoryDTO();
		functionModuleCategoryDTO.setFunctionModuleId(functionModuleId);
		List<FunctionModuleCategoryDTO> functionModuleCategoryList = functionModuleCategoryReadService.findFunctionModuleCategoryAll(functionModuleCategoryDTO);
		for (FunctionModuleCategoryDTO functionModuleCategoryDTO2 : functionModuleCategoryList) {
			categoryTreeNodeIdList.add(functionModuleCategoryDTO2.getCategoryTreeNodeId());
		}
		if(EmptyUtil.isEmpty(categoryTreeNodeIdList)){
			PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
	        result.setList(new ArrayList<StandardUnitDTO>());
	        result.setPageNo(page.getPageNo());
	        result.setPageSize(page.getPageSize());
	        result.setTotalSize(0);
	        return result;
		}else{
			//根据类目节点id集合查询su商品信息
			return standardUnitReadService.standardUnitByFunctionModuleId(dto,categoryTreeNodeIdList,null, page);
		}

	}

	public PageResult<Map<String, Object>> findByCategoryTreeNodeIdOrSUCombination(Integer saleWay, Long categoryTreeNodeId,
																				   Long standardUnitCombinationId, Integer type, Integer fubiPay, Long userId, Long clientId,Long enterrprisetId, Long companyId,
																				   Long platformId, Long couponUnitId, Integer companyType, Pagination page, Long storeId, Integer buyType,Integer sellState,String keyWord) {
		BigDecimal userBalance = null;
		if (!userId.equals(-1L)) {
			//根据用户id查询用户信息
			UserExtendDTO userExtendDTO = userExtendReadService.userByUserId(userId);
			//是否在职:默认0否;1是
			if(userExtendDTO.getIsAvailable() == 0){
				//如果离职,将公司id设为空、不让该用户查询其公司相关su商品
				companyId = null;
			}
			//是否仅积分支付
			if(fubiPay == 1){
				//根据用户id查询用户可用积分余额（=积分-冻结积分）,并将其赋值，查询的时候判断其有值就会作为条件筛选
				userBalance = userBalance(userId);
			}
		}
		Long queryId = null;
		if(type == 1){
			queryId = categoryTreeNodeId;
		}else if(type == 2){
			queryId = standardUnitCombinationId;
		}
		Integer couponType=null;
		if(EmptyUtil.isNotEmpty(couponUnitId)&&!couponUnitId.equals(Long.valueOf(0L))){
			CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
			couponUnitDTO.setId(couponUnitId);
			CouponUnitDTO couponUnit = couponUnitReadService.findCouponUnitById(couponUnitDTO);
			if(EmptyUtil.isEmpty(couponUnit)){
				throw new BusinessException("id为:"+couponUnitId+"优惠卷不存在");
			}
			couponType = couponUnit.getCouponType();
		}

		//DTOTOMAP
		PageResult<StandardUnitDTO> result = standardUnitReadService.standardUnitByType(couponType,saleWay,queryId, type, platformId,userBalance,
				clientId,enterrprisetId, companyId,companyType, storeId, page, buyType,sellState,keyWord);
		/*List<StandardUnitDTO> standardUnitDTOList=result.getList();
		List<StandardUnitDTO> newStandardUnitDTOList = new ArrayList<>();
		for(StandardUnitDTO standardUnitDTO:standardUnitDTOList){
			if(platformId==2L&&standardUnitDTO.getStoreId()!=2L){
				continue;
			}
			newStandardUnitDTOList.add(standardUnitDTO);

		}
		result.setList(newStandardUnitDTOList);*/
		return assignmentStandardUnit(result, companyId, couponUnitId,companyType, storeId, platformId);
	}
	/**
	 * DTOTOMAP
	 * @param result
	 * @return
	 */
	private PageResult<Map<String, Object>> assignmentStandardUnit(PageResult<StandardUnitDTO> result,Long companyId, Long couponUnitId,Integer companyType, Long storeId, Long platformId) {
		List<Map<String, Object>> standardUnitList = new ArrayList<>();
		List<StandardUnitDTO> standardUnitDTOList = result.getList();
		Map<Long, CommodityProductUnitDTO> puMap = findPuInfo(companyType, standardUnitDTOList);
		if(EmptyUtil.isNotEmpty(standardUnitDTOList)){
			Set<Long> standardUnitIds=new HashSet<>();
			standardUnitDTOList.forEach(item->{
				if (Objects.nonNull(item.getSource()) && item.getSource()<3){
					standardUnitIds.add(item.getId());
				}
			});
			Map<Long,Long> stockNumMap=new HashMap<>();
			if (EmptyUtil.isNotEmpty(standardUnitIds)){
				List<ResidueStockNumConditionDTO> stockNums = commodityProductUnitWarehouseStockReadService.residueStockNumByStandardUnitIds(new ArrayList<>(standardUnitIds));
				if (EmptyUtil.isNotEmpty(stockNums)){
					stockNums.forEach(item->stockNumMap.put(item.getStandardUnitId(),item.getRealStockNum()));
				}
			}
			for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
				// 根据优惠卷unit的id查询优惠卷unit的信息
				if (EmptyUtil.isNotEmpty(couponUnitId) && !couponUnitId.equals(Long.valueOf(0L))) {
					CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
					couponUnitDTO.setId(couponUnitId);
					CouponUnitDTO couponUnitDTO_ = couponUnitReadService.findCouponUnitById(couponUnitDTO);
					if (EmptyUtil.isNotEmpty(couponUnitDTO_)) {
						if (couponUnitDTO_.getGoodsType() == 0) {
							// 0: 单su
							if (standardUnitDTO.getId().equals(couponUnitDTO_.getGoodsId())) {

								addStandardUnitList(standardUnitDTO, companyId, standardUnitList,companyType, puMap,stockNumMap);
							}
						} else if (couponUnitDTO_.getGoodsType() == 1) {
							// 1:商品组
							List<StandardUnitDTO> standardUnitDTOList_ = standardUnitReadService
									.findByStandardUnitCombinationId(couponUnitDTO_.getGoodsId(), null);

							for (StandardUnitDTO standardUnitDTO_ : standardUnitDTOList_) {
								if (standardUnitDTO.getId().equals(standardUnitDTO_.getId())) {

									addStandardUnitList(standardUnitDTO, companyId, standardUnitList,companyType, puMap,stockNumMap);
									break;
								}
							}
						}
					}
				} else {
					addStandardUnitList(standardUnitDTO, companyId, standardUnitList,companyType, puMap,stockNumMap);
				}
			}
		}
		PageResults<Map<String, Object>> pageResult = new PageResults<Map<String, Object>>();
		/**门店过滤放入sql实现**/
		/*if (storeId != null && EmptyUtil.isNotEmpty(standardUnitList)) {
			//新增
			if(storeId==2L||storeId==1L){
				List<Map<String,Object>> newList=new ArrayList<>();
				for(int i=0;i<standardUnitList.size();i++){
					Map<String, Object> map=standardUnitList.get(i);
					StandardUnitDTO standardUnitId = standardUnitReadService.findStandardUnitById(Long.valueOf(map.get("standardUnitId").toString()));
					if(standardUnitId.getStoreId().equals(storeId)){
						newList.add(map);
					}
				}
				pageResult.setList(newList);
			//
			}else{
				pageResult.setList(filterBySuStore(standardUnitList, storeId, platformId));
			}
		} else {
			pageResult.setList(standardUnitList);
		}*/

		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		pageResult.setList(standardUnitList);
		//logger.info("关键字搜索返回:{}", JSON.toJSONString(pageResult));
		return pageResult;
	}


	private Map<Long, CommodityProductUnitDTO> findPuInfo(Integer companyType, List<StandardUnitDTO> standardUnitDTOList) {
		Map<Long, CommodityProductUnitDTO> puMap = new HashMap<>();
		if (EmptyUtil.isNotEmpty(standardUnitDTOList)) {
			List<Long> suIds = new ArrayList<>();
			for (StandardUnitDTO su : standardUnitDTOList) {
				suIds.add(su.getId());
			}
			List<CommodityProductUnitDTO> puList = commodityProductUnitReadService.findPuInfoBySuIdList(companyType, suIds);
			for (CommodityProductUnitDTO pu : puList) {
				puMap.put(pu.getStandardUnitId(), pu);
			}
		}
		return puMap;
	}

	/**
	 * 根据su和门店的关联关系过滤门店su列表
	 * @param standardUnitList
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	private List<Map<String, Object>> filterBySuStore(List<Map<String, Object>> standardUnitList, Long storeId, Long platformId) {
		List<Map<String, Object>> storeStandardUnitList = new ArrayList<>();
		List<Long> suIdList = new ArrayList<>();
		for (Map<String, Object> su : standardUnitList) {
			suIdList.add((Long)su.get("standardUnitId"));
		}
		List<StandardUnitStoreDTO> standarsUnitStoreList = standardUnitStoreReadService.findByStoreAndSu(suIdList, storeId, platformId);
		List<Long> storeSuIdIdList = new ArrayList<>();
		if (EmptyUtil.isNotEmpty(standarsUnitStoreList)) {
			for (StandardUnitStoreDTO sus : standarsUnitStoreList) {
				storeSuIdIdList.add(sus.getStandardUnitId());
			}
		}
		for (Map<String, Object> su : standardUnitList) {
			if (storeSuIdIdList.contains(su.get("standardUnitId"))) {
				storeStandardUnitList.add(su);
			}
		}
		return storeStandardUnitList;
	}

	private void addJdProductList(JdProductDTO standardUnitDTO, Long companyId, List<Map<String, Object>> standardUnitList,Integer companyType, Map<Long, CommodityProductUnitDTO> puMap){
		Map<String, Object> map = new HashMap<>();
		map.put("standardUnitId", standardUnitDTO.getId());
		//根据suId查询剩余库存数量
		map.put("isOwnMerchant",0 );
		map.put("stockNum", standardUnitDTO.getId()%100);
		map.put("source", 3);

		map.put("name", standardUnitDTO.getName());
		map.put("pictureUrl", standardUnitDTO.getImagePath());
		map.put("salePrice", standardUnitDTO.getPrice());
		map.put("marketPrice", standardUnitDTO.getMarketPrice());
		map.put("commodityTemplateId", 2);
		map.put("status", 3);
		map.put("salesVolume", 0 );
		map.put("saleWay", 1);

		//购买方式
		map.put("buyType",1);
		CommodityProductUnitDTO pu = puMap.get(standardUnitDTO.getId());
		if (pu != null) {
			map.put("puId", pu.getPuId());
			map.put("puSalePrice", pu.getPuSalePrice());
			map.put("maxSalePrice", pu.getMaxSalePrice());
			map.put("minSalePrice", pu.getMinSalePrice());
		}
		standardUnitList.add(map);
	}
	private void addStandardUnitList(StandardUnitDTO standardUnitDTO, Long companyId, List<Map<String, Object>> standardUnitList,
                                     Integer companyType, Map<Long, CommodityProductUnitDTO> puMap,Map<Long,Long> stockNumMap){
		Map<String, Object> map = new HashMap<>();
		map.put("standardUnitId", standardUnitDTO.getId());
		//根据suId查询剩余库存数量
		map.put("isOwnMerchant",0 );
		map.put("stockNum", 1000);
		map.put("source", standardUnitDTO.getSource()==null?1:standardUnitDTO.getSource());
		if(standardUnitDTO.getSource()!=null && standardUnitDTO.getSource()<3) {
			if (stockNumMap.containsKey(standardUnitDTO.getId())){
				map.put("stockNum", stockNumMap.get(standardUnitDTO.getId()));
			}
			map.put("stockWarning", standardUnitDTO.getStockWarning());
			if(standardUnitDTO.getMerchantId().equals(1L)){
				map.put("isOwnMerchant", 1);
			}else{
				map.put("isOwnMerchant",0 );
			}

		}
		map.put("name", standardUnitDTO.getName());
		map.put("pictureUrl", standardUnitDTO.getPictureUrl());
		map.put("salePrice", settingSalePrice(standardUnitDTO,companyType));
		map.put("marketPrice", standardUnitDTO.getMarketPrice());
		map.put("commodityTemplateId", standardUnitDTO.getCommodityTemplateId());
		map.put("status", standardUnitDTO.getStatus());
		map.put("salesVolume", (EmptyUtil.isNotEmpty(standardUnitDTO.getSalesVolume()))?standardUnitDTO.getSalesVolume():0L );
		map.put("saleWay", standardUnitDTO.getSaleWay());
		map.put("channelProductId", standardUnitDTO.getCode());

		//购买方式
		map.put("buyType",standardUnitDTO.getBuyType());
		CommodityProductUnitDTO pu = puMap.get(standardUnitDTO.getId());
		if (pu != null) {
			map.put("puId", pu.getPuId());
			map.put("puSalePrice", pu.getPuSalePrice());
			map.put("maxSalePrice", pu.getMaxSalePrice());
			map.put("minSalePrice", pu.getMinSalePrice());
		}
		standardUnitList.add(map);
	}

	/**
	 * 查询积分余额
	 * 积分余额 = 积分-冻结积分
	 * @param userId
	 * @return
	 */
	private BigDecimal userBalance(Long userId) {
		// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
		List<UserAccountDTO> userAccountDTOs = userAccountReadService.queryUserAccountByUserId(userId);
		UserAccountDTO userAccountFubi = null;
		UserAccountDTO userAccountFd = null;
		UserAccountDTO userAccountFubiFrozen = null;
		for(UserAccountDTO account : userAccountDTOs) {
			if(account.getType()!=null && account.getType().intValue() == 0) {
				userAccountFubi = account;
			}else if(account.getType()!=null && account.getType().intValue() == 2) {
				userAccountFubiFrozen = account;
			}else if(account.getType()!=null && account.getType().intValue() == 4) {
				userAccountFd = account;
			}
		}
		if(EmptyUtil.isEmpty(userAccountFubi)){
			throw new BusinessException("用户账户数据未就绪");
		}


        List<CompanyConfigDTO> configs =  userReadService.findUserCompanyConfigs(userId);
        boolean validFubiAcc = true;
        boolean validFFAcc = true;
        for(CompanyConfigDTO config : configs) {
        	if(config.getKey().equalsIgnoreCase("account.0.valid")&& config.getValue()!=null && config.getValue().length()==1) {
        		validFubiAcc = (Integer.valueOf(config.getValue()).intValue()==0)?false:true;
        	}

        	if(config.getKey().equalsIgnoreCase("account.2.valid")&& config.getValue()!=null && config.getValue().length()==1) {
        		validFFAcc = (Integer.valueOf(config.getValue()).intValue()==0)?false:true;
        	}
        }
        if(validFubiAcc) {

    		// 根据uuid查询
    		SaltDTO saltDTO = saltReadService.querySaltByUUID(userAccountFubi.getUuid());
    		String ciphertext = MD5Util.MD5Salt(String.valueOf(userAccountFubi.getBalance()),
    				saltDTO.getSaltValue());
    		if (!ciphertext.equals(userAccountFubi.getCiphertext())) {
    			throw new BusinessException(BusinessExceptionConstant.FOSCOIN_ACCOUNT_ABNORMAL, "积分账户异常");
    		}
        }

		// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户

		if(EmptyUtil.isEmpty(userAccountFubiFrozen)){
			throw new BusinessException("用户账户数据未就绪");
		}

		if(validFFAcc) {

			// 根据uuid查询
			SaltDTO salt = saltReadService.querySaltByUUID(userAccountFubiFrozen.getUuid());
			String ciphertextSalt = MD5Util.MD5Salt(String.valueOf(userAccountFubiFrozen.getBalance()),
					salt.getSaltValue());
			if (!ciphertextSalt.equals(userAccountFubiFrozen.getCiphertext())) {
				throw new BusinessException(BusinessExceptionConstant.FOSCOIN_FREEZE_ACCOUNT_ABNORMAL, "积分冻结账户异常");
			}
		}
		// 用户积分余额
		BigDecimal decimal = userAccountFubi.getBalance().add((userAccountFd==null||userAccountFd.getBalance()==null)?(new BigDecimal(0)):userAccountFd.getBalance()).subtract(userAccountFubiFrozen.getBalance());
		return decimal;
	}

	/**
	 * su商品组合选择商品_su商品列表

	 * @return
	 */
	public PageResult<StandardUnitDTO> findBaseByConditionOfPage(Long standardUnitCombinationId,StandardUnitDTO standardUnitDTO, Pagination page) {
		// TODO Auto-generated method stub
		return standardUnitReadService.findBaseByConditionOfPage(standardUnitCombinationId,standardUnitDTO, page);
	}
	/**
	 * 根据su商品名称查询所有su商品
	 *
	 *
	 * @param jedisUtil
	 * @param platformId
	 * @param standardUnitName
	 * @return
	 */
	public List<Map<String, Object>> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<StandardUnitDTO> standardUnitList = standardUnitReadService.findByStandardUnitName(platformId,standardUnitId,standardUnitName);
		for (StandardUnitDTO standardUnitDTO2 : standardUnitList) {
			Map<String, Object> map = new HashMap<>();
			map.put("standardUnitId", standardUnitDTO2.getId());
			map.put("standardUnitName", standardUnitDTO2.getName());
			list.add(map);

		}
		return list;
	}
	/**
	 * 根据suid查询su真实数据
	 * @param standardUnitId
	 * @return
	 */
	public StandardUnitExportVO queryByStandardUnitId(Long standardUnitId) {
		StandardUnitExportVO standardUnitExportVO = new StandardUnitExportVO();
		Map<String, Object> standardUnitMap = new HashMap<>();
		//根据su商品id查询su商品信息
		StandardUnitDTO standardUnitDTO2 = standardUnitReadService.findStandardUnitById(standardUnitId);
		standardUnitExportVO.setId(standardUnitDTO2.getId());
		standardUnitExportVO.setStandardUnitName(standardUnitDTO2.getName());
		standardUnitExportVO.setMerchantProductSerialNumber(standardUnitDTO2.getMerchantProductSerialNumber());
		standardUnitExportVO.setProductCategory(standardUnitDTO2.getProductCategory());
		standardUnitExportVO.setStatus(standardUnitDTO2.getStatus());
		standardUnitExportVO.setSalePrice(standardUnitDTO2.getSalePrice());
		standardUnitExportVO.setMarketPrice(standardUnitDTO2.getMarketPrice());
		standardUnitExportVO.setFreightExplain(standardUnitDTO2.getFreightExplain());
		standardUnitExportVO.setShipmentsExplain(standardUnitDTO2.getShipmentsExplain());
		standardUnitExportVO.setSalesVolume(standardUnitDTO2.getSoldBase());
		standardUnitExportVO.setContent(standardUnitDTO2.getContent());
		standardUnitExportVO.setIsVisible(standardUnitDTO2.getIsVisible());
		standardUnitExportVO.setIsVendible(standardUnitDTO2.getIsVendible());
		standardUnitExportVO.setSoldBase(standardUnitDTO2.getSoldBase());
		standardUnitExportVO.setCommodityTemplateId(standardUnitDTO2.getCommodityTemplateId());
		standardUnitExportVO.setSaleWay(standardUnitDTO2.getSaleWay());
		standardUnitExportVO.setMerchantId(standardUnitDTO2.getMerchantId());
		standardUnitExportVO.setPromotionPrice(standardUnitDTO2.getPromotionPrice());
		standardUnitExportVO.setStockWarning(standardUnitDTO2.getStockWarning());
		standardUnitExportVO.setUpdateUsername(standardUnitDTO2.getUpdateUsername());
		standardUnitExportVO.setUpdateUserid(standardUnitDTO2.getUpdateUserid());
		standardUnitExportVO.setUpdateUserip(standardUnitDTO2.getUpdateUserip());
		standardUnitExportVO.setUpdateTime(standardUnitDTO2.getUpdateTime());
		standardUnitExportVO.setIsSpuKeyword(standardUnitDTO2.getIsSpuKeyword());
		standardUnitExportVO.setStoreId(standardUnitDTO2.getStoreId());
		standardUnitExportVO.setPresellPeriod(standardUnitDTO2.getPresellPeriod());
		standardUnitExportVO.setRelevanceSuId(standardUnitDTO2.getRelevanceSuId());
		standardUnitExportVO.setRelevanceSuName(standardUnitDTO2.getRelevanceSuName());
		standardUnitExportVO.setBuyType(standardUnitDTO2.getBuyType());
		standardUnitExportVO.setFrontSerialNumber(standardUnitDTO2.getFrontSerialNumber());

		// 根据suid查询门店id集合
		StandardUnitStoreDTO standardUnitStoreDTO = new StandardUnitStoreDTO();
		standardUnitStoreDTO.setStandardUnitId(standardUnitDTO2.getId());
		standardUnitStoreDTO.setPlatformId(standardUnitDTO2.getPlatformId());
		List<StandardUnitStoreDTO> standardUnitStoreList = standardUnitStoreReadService.findStandardUnitStoreAll(standardUnitStoreDTO);
		List<Long> storeIdList = new ArrayList<>(standardUnitStoreList.size());
		for (StandardUnitStoreDTO standardUnitStoreDTO2 : standardUnitStoreList) {
			storeIdList.add(standardUnitStoreDTO2.getStoreId());
		}
		standardUnitExportVO.setStoreIdList(storeIdList);

		// 根据suid查询会籍id集合
		StandardUnitMembershipDTO standardUnitMembershipDTO = new StandardUnitMembershipDTO();
		standardUnitMembershipDTO.setStandardUnitId(standardUnitDTO2.getId());
		standardUnitMembershipDTO.setPlatformId(standardUnitDTO2.getPlatformId());
		List<StandardUnitMembershipDTO> standardUnitMembershipList = standardUnitMembershipReadService.findStandardUnitMembershipAll(standardUnitMembershipDTO);
		List<Long> membershipIdList = new ArrayList<>(standardUnitMembershipList.size());
		for (StandardUnitMembershipDTO standardUnitMembershipDTO2 : standardUnitMembershipList) {
			membershipIdList.add(standardUnitMembershipDTO2.getMembershipId());
		}
		standardUnitExportVO.setMembershipIdList(membershipIdList);

		// 根据spuid查询spu关键词
		List<String> spuKeyWords = standardProductUnitAttValueFacade.keyWordByStandardProductUnitId(standardUnitDTO2.getStandardProductUnitId(),standardUnitDTO2.getPlatformId());
		standardUnitExportVO.setSpuKeywords(spuKeyWords);

		// 根据suid查询su关键词
		List<String> suKeywords = suSerachKeywordReadService.keywordByStandardUnitId(standardUnitDTO2.getId(),standardUnitDTO2.getPlatformId());
		standardUnitExportVO.setSuKeywords(suKeywords);
		// 运费模版赋值
		setFreightExplainAndShipmentsExplain(standardUnitExportVO,standardUnitDTO2);

		// 根据sudi查询su标签信息
		StandardUnitTagDTO standardUnitTagDTO = new StandardUnitTagDTO();
		standardUnitTagDTO.setStandardUnitId(standardUnitId);
		List<StandardUnitTagDTO> standardUnitTagDTOList = standardUnitTagReadService.findStandardUnitTagAll(standardUnitTagDTO);
		List<Long> tagList = new ArrayList<>();
		for (StandardUnitTagDTO standardUnitTagDTO2 : standardUnitTagDTOList) {
			tagList.add(standardUnitTagDTO2.getTagId());
		}
		standardUnitExportVO.setTagList(tagList);
		//根据spuid查询spu信息
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(standardUnitDTO2.getStandardProductUnitId());
		StandardProductUnitDTO standardProductUnitDTO2 = standardProductUnitReadService.findStandardProductUnitById(standardProductUnitDTO);
		standardUnitExportVO.setStandardProductUnitName(standardProductUnitDTO2.getName());
		standardUnitExportVO.setProductSerialNumber(standardProductUnitDTO2.getProductSerialNumber());

		//根据suid查询su图片信息
		List<PictureDTO> pictures = pictureReadService.picturesByStandardUnitId(standardUnitId);
		List<String> pictureList = new ArrayList<>(); // app轮播图
		List<String> webBannerPictureList = new ArrayList<>(); // web轮播图
		for (PictureDTO pictureDTO : pictures) {
			//类型：1、列表图片 2、轮播图片
			if(pictureDTO.getType() == 1){
				standardUnitMap.put("picture", pictureDTO.getUrl());
			}
			if(pictureDTO.getType() == 2){
				pictureList.add(pictureDTO.getUrl());
			}
			if(pictureDTO.getType() == 3)
				webBannerPictureList.add(pictureDTO.getUrl());
		}
		standardUnitExportVO.setPictureList(pictureList);
		standardUnitExportVO.setWebBannerPictureList(webBannerPictureList);

		//查询全部公司
		List<CompanyDTO> companyList = companyReadService.findCompanyAll(new CompanyDTO());
		List<Map<String, Object>> companyMapList = new ArrayList<>(); // 正式公司集合
		List<Map<String, Object>> demoCompanyMapList = new ArrayList<>(); // 演示公司集合
		List<Map<String, Object>> competingCompanyMapList = new ArrayList<>(); // 竞品公司集合
		for (CompanyDTO companyDTO : companyList) {
			if(EmptyUtil.isNotEmpty(companyDTO.getCompanyType())){
				Map<String, Object> companyMap = new HashMap<>();
				companyMap.put("id", companyDTO.getId());
				companyMap.put("companyName", companyDTO.getCompanyName());
				// 公司类型 0:正式公司 1:测试公司 2:竞品公司
				switch (companyDTO.getCompanyType()) {
				case 0:
					companyMapList.add(companyMap);
					break;
				case 1:
					demoCompanyMapList.add(companyMap);
					break;
				case 2:
					competingCompanyMapList.add(companyMap);
					break;

				default:
					break;
				}
			}

		}
		standardUnitExportVO.setCompanyList(companyMapList);
		standardUnitExportVO.setDemoCompanyList(demoCompanyMapList);
		standardUnitExportVO.setCompetingCompanyList(competingCompanyMapList);

		List<ClientDTO> clientList = clientReadService.findClientAll(new ClientDTO());
		List<Map<String, Object>> clientMapList = new ArrayList<>();
		for (ClientDTO clientDTO : clientList) {
			Map<String, Object> clientMap = new HashMap<>();
			clientMap.put("id", clientDTO.getId());
			clientMap.put("name", clientDTO.getName());
			clientMapList.add(clientMap);
		}
		standardUnitExportVO.setClientList(clientMapList);

		//根据suid查询公司信息
		List<Long> companyIds = new ArrayList<>(); // 正式公司id集合
		List<Long> demoCompanyIds = new ArrayList<>(); // 演示公司id集合
		List<Long> competingCompanyIds = new ArrayList<>(); // 竞品公司id集合
		findCompany(standardUnitId,companyIds,demoCompanyIds,competingCompanyIds);
		standardUnitExportVO.setCompanyIds(companyIds);
		standardUnitExportVO.setDemoCompanyIds(demoCompanyIds);
		standardUnitExportVO.setCompetingCompanyIds(competingCompanyIds);

		//根据suId查询运营方信息
		List<Long> clientIds = findClient(standardUnitId);
		standardUnitExportVO.setClientIds(clientIds);

		//根据suid查询pu信息
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setStandardUnitId(standardUnitId);
		List<CommodityProductUnitDTO> productUnitList = commodityProductUnitReadService.findCommodityProductUnitAll(commodityProductUnitDTO);
		List<PuExportVO> maps = new ArrayList<>();
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : productUnitList) {
			PuExportVO puExportVO = new PuExportVO();
			puExportVO.setId(commodityProductUnitDTO2.getId());
			puExportVO.setName(commodityProductUnitDTO2.getName());
			puExportVO.setCode(commodityProductUnitDTO2.getCode());
			puExportVO.setSalePrice(commodityProductUnitDTO2.getSalePrice());
			puExportVO.setPuPicUrl(commodityProductUnitDTO2.getPuPicUrl());
			puExportVO.setStatus(commodityProductUnitDTO2.getStatus());
			puExportVO.setIsVendible( commodityProductUnitDTO2.getIsVendible());
			puExportVO.setDemoSalePrice(commodityProductUnitDTO2.getDemoSalePrice());
			puExportVO.setCompetingSalePrice(commodityProductUnitDTO2.getCompetingSalePrice());

			if(commodityProductUnitDTO2.getIsVendible() == 0){
				puExportVO.setChecked(false);
			}else if(commodityProductUnitDTO2.getIsVendible() == 1){
				puExportVO.setChecked(true);
			}
			//根据skuid查询sku信息
			SkuDTO skuDTO = new SkuDTO();
			skuDTO.setId(commodityProductUnitDTO2.getSkuId());
			SkuDTO skuDTO2 = skuReadService.findSkuById(skuDTO);
			puExportVO.setIsAvailable(skuDTO2.getIsAvailable());
			puExportVO.setIsValid(skuDTO2.getIsValid());
			puExportVO.setSkuName(skuDTO2.getSkuName());
			maps.add(puExportVO);
		}
		standardUnitExportVO.setProductUnitList(maps);
		return standardUnitExportVO;
	}
	/**
	 * 根据suId查询运营方信息
	 * @param standardUnitId
	 * @return
	 */
	private List<Long> findClient(Long standardUnitId) {
		StandardUnitClientDTO standardUnitClientDTO = new StandardUnitClientDTO();
		standardUnitClientDTO.setStandardUnitId(standardUnitId);
		List<StandardUnitClientDTO> list = standardUnitClientReadService.findStandardUnitClientAll(standardUnitClientDTO);
		List<Long> clientIdList = new ArrayList<>();
		for (StandardUnitClientDTO standardUnitClientDTO2 : list) {
			clientIdList.add(standardUnitClientDTO2.getClientId());
		}
		return clientIdList;
	}

	/**
	 * 根据suid查询公司信息
	 * @param standardUnitId
	 * @return
	 */
	private void findCompany(Long standardUnitId,List<Long> companyIds,List<Long> demoCompanyIds,List<Long> competingCompanyIds) {
		//根据suId查询su信息
		StandardUnitCompanyDTO standardUnitCompanyDTO = new StandardUnitCompanyDTO();
		standardUnitCompanyDTO.setStandardUnitId(standardUnitId);
		List<StandardUnitCompanyDTO> standardUnitCompanyList = standardUnitCompanyReadService.findStandardUnitCompanyAll(standardUnitCompanyDTO);
		for (StandardUnitCompanyDTO standardUnitCompanyDTO2 : standardUnitCompanyList) {
			if(EmptyUtil.isNotEmpty(standardUnitCompanyDTO2.getCompanyType())){
				// 公司类型 0:正式公司 1:测试公司 2:竞品公司
				switch (standardUnitCompanyDTO2.getCompanyType()) {
				case 0:
					companyIds.add(standardUnitCompanyDTO2.getCompanyId());
					break;
				case 1:
					demoCompanyIds.add(standardUnitCompanyDTO2.getCompanyId());
					break;
				case 2:
					competingCompanyIds.add(standardUnitCompanyDTO2.getCompanyId());
					break;

				default:
					break;
				}
			}
		}
	}
	/**
	 * 根据条件分页查询su商品信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> findStandardUnitExtendOfPage(StandardUnitDTO dto, Pagination page,List<Long> standardUnitIdList) {
		// TODO Auto-generated method stub
		return standardUnitReadService.findStandardUnitExtendOfPage(dto, page,standardUnitIdList);
	}

	public PageResult<StandardUnitDTO> queryStandardUnitListByBlurry(StandardUnitDTO dto, Long excludeId, Pagination page) {

		return standardUnitReadService.queryStandardUnitListByBlurry(dto, excludeId, page);
	}
	/**
	 * 根据关键词搜索商品
	 *
	 *
	 * @param saleWay
	 * @param storeId
	 * @param name 搜索关键词
	 * @param fubiPay 是否积分支付
	 * @param clientId 客户端id
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @param page 分页信息
	 * @param buyType
	 * @return
	 */
	public PageResult<Map<String, Object>> findByKeywordOfPage(
			Integer saleWay, Long storeId, String name, Long userId, Integer fubiPay, Long clientId, Long companyId,
			Long platformId, Integer companyType, Pagination page, Integer buyType) {
		//根据用户id查询用户信息
		UserExtendDTO userExtendDTO = userExtendReadService.userByUserId(userId);
		//是否在职:默认0否;1是(-1为免登陆默认用户id)
		if(userId!=-1&&userExtendDTO.getIsAvailable() == 0){
			//如果离职,将公司id设为空、不让该用户查询其公司相关su商品
			companyId = null;
		}
		BigDecimal userBalance = null;
		//是否仅积分支付
		if(fubiPay == 1){
			//根据用户id查询用户可用积分余额,并将其赋值，查询的时候判断其有值就会作为条件筛选
			userBalance = userBalance(userId);
		}
		PageResult<StandardUnitDTO> result = standardUnitReadService.findByKeywordOfPage(saleWay,storeId,name,userBalance,clientId,companyId,platformId,companyType,page, buyType);

		return assignmentStandardUnit(result, companyId, null,companyType, null, platformId);
	}
	/**
	 * 刷新su商品搜索规则数据信息
	 * @return
	 */
	public void syncSaveSuSerachRule() {
		Integer count=standardUnitReadService.findStandardUnitCount();
		Integer pageSize=500;
		int page=((count-1)/pageSize)+1;

		for(int i=0;i<page;i++){
			logger.error("更新第"+i+"批数据");
			List<StandardUnitDTO> suList = standardUnitReadService.findStandardUnitAllByPage(i*pageSize,pageSize);
			suSerachRuleWriteService.syncSaveSuSerachRule(suList,i);

		}

	}

	public PageResult<StandardUnitDTO> querySuByCategoryTreeNodeIds(Pagination page, Map<String, Object> param, List<Long> frontCategoryTreeNodeIds) {
		return standardUnitReadService.querySuByFrontCategoryTreeNodeIds(page, param, frontCategoryTreeNodeIds);
	}

	public List<StandardUnitTagDTO> querySuIdsByTagIds(List<Long> tagIdList) {
		StandardUnitTagDTO dto = new StandardUnitTagDTO();
		dto.setTagIds(tagIdList);
		return standardUnitTagReadService.findStandardUnitTagAll(dto);
	}

	public PageResult<Map<String, Object>> findStandardUnitByStoreMenuIdOfPage(Long storeMenuNodeId,Long storeId,Long platformId, Pagination page) {
		PageResult<StandardUnitDTO> rt = null;
		// 如果门店菜单id不为空则根据门店菜单id查询su商品信息
		if(EmptyUtil.isNotEmpty(storeMenuNodeId) && storeMenuNodeId.doubleValue() != -99){
			rt = standardUnitReadService.findStandardUnitByStoreMenuIdOfPage(storeMenuNodeId,platformId, page);
		}
		// 如果门店菜单id为空则根据门店id查询su商品信息
		else{
			rt = standardUnitReadService.findStandardUnitByStoreIdOfPage(storeId,platformId, page);
		}
		List<StandardUnitDTO> standardUnitList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(standardUnitList.size());
		for (StandardUnitDTO standardUnitDTO : standardUnitList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", standardUnitDTO.getId());
			map.put("name", standardUnitDTO.getName());
			map.put("pictureUrl", standardUnitDTO.getPictureUrl());
			map.put("salePrice", standardUnitDTO.getSalePrice());
			map.put("marketPrice", standardUnitDTO.getMarketPrice());
			map.put("status", standardUnitDTO.getStatus());
			map.put("saleWay", standardUnitDTO.getSaleWay());
			map.put("stockWarning", standardUnitDTO.getStockWarning());
			map.put("commodityTemplateId", standardUnitDTO.getCommodityTemplateId());
			//根据suId查询剩余库存数量
			Long stockNum = commodityProductUnitWarehouseStockReadService.residueStockNumByStandardUnitId(standardUnitDTO.getId());
			map.put("stockNum", stockNum);
			if(standardUnitDTO.getMerchantId().equals(1L)){
				map.put("isOwnMerchant",1);
			}else{
				map.put("isOwnMerchant",0);
			}
			list.add(map);
		}

        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
	}

	public PageResult<StandardUnitDTO> findByHeadStoreId(StandardUnitDTO standardUnitDTO, Pagination page) {
		return standardUnitReadService.findStandardUnitOfPage(standardUnitDTO, page);
	}

	public CouponUnitDTO findCouponUnitByCouponUnitId(Long couponUnitId) {

		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(couponUnitId);
		return couponUnitReadService.findCouponUnitById(couponUnitDTO);

	}

	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO storeProductUnitDTO) {
		return storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);

	}

	public Long realStockNumByStoreProductUnitId(Long id, Long storeId) {
		return storePuWarehouseStockReadService.realStockNumByStoreProductUnitId(id, storeId);
	}

	public CouponDTO findCouponByCouponId(Long couponId) {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setId(couponId);
		return couponReadService.findCouponById(couponDTO);
	}

    public int updateSuVisible(StandardUnitDTO standardUnitDTO) {
		return standardUnitWriteService.updateSuVisible( standardUnitDTO);
	}

    public int updateMerchantProductVisible(Long standardUnitId, Integer status) {
		return merchantProductWriteService.updateMerchantProductVisible(standardUnitId,status);
	}
}
