package com.egeo.components.product.facade;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.product.bean.ProductAttrBean;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.vo.*;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.utils.JdUtils2;
import com.egeo.components.utils.NumberUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.utils.StringUtils;
import com.egeo.web.JsonResult;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.client.ReceiverAddressClient;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.JdProductReadService;
import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.service.write.CommodityProductUnitWriteService;
import com.egeo.components.product.service.write.JdProductWriteService;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.delivery.jd.JdAllSkuInfo;
import org.springframework.util.CollectionUtils;

@Component
public class CommodityProductUnitFacade {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserClient userClient;

	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;
	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;

	@Resource
	private JdProductReadService jdProductReadService;
	@Resource
	private SkuReadService skuReadService;
	@Resource
	private CommodityProductUnitWriteService commodityProductUnitWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;

	@Autowired
	private MerchantProductWarehouseStockClient merchantProductWarehouseStockReadService;

	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;

	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;

	@Resource
	private AttributeValueReadService attributeValueReadService;

	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockReadService;

	@Resource
	private StoreProductUnitReadService storeProductUnitReadService;
	@Autowired
	private CompanyClient companyReadService;
	@Resource
	private StandardUnitReadService standardUnitReadService;
	@Resource
	private JdProductWriteService jdProductWriteService;

	@Autowired
	private ReceiverAddressClient receiverAddressReadService;

	@Autowired
	private JedisUtil jedisUtil;
	@Autowired
	private JdUtils jdUtils;
	@Resource(name="cakeProductManage")
	private CakeProductManage cakeProductManage;

	@Autowired
	private ChannelPriceHelper channelPriceHelper;

	@Autowired
	private ChannelProductFacade channelProductFacade;

	@Autowired
	private JdUtils2 jdUtils2;

	public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO dto) {

		return commodityProductUnitReadService.findCommodityProductUnitById(dto);
	}

	public PageResult<CommodityProductUnitDTO> findCommodityProductUnitOfPage(CommodityProductUnitDTO dto,
                                                                              Pagination page) {

		return commodityProductUnitReadService.findCommodityProductUnitOfPage(dto, page);

	}

	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto) {

		return commodityProductUnitReadService.findCommodityProductUnitAll(dto);

	}

	public Long insertCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {

		return commodityProductUnitWriteService.insertCommodityProductUnitWithTx(dto);
	}

	public int updateCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {

		return commodityProductUnitWriteService.updateCommodityProductUnitWithTx(dto);
	}

	public int deleteCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {

		return commodityProductUnitWriteService.deleteCommodityProductUnitWithTx(dto);

	}

	/**
	 * 根据skuid查询pu总条数
	 *
	 * @return
	 */
	public int countRecord(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.countRecord(skuId);
	}

	/**
	 * 根据pu草稿id修改pu信息
	 *
	 * @param commodityProductUnitDTO
	 * @return
	 */
	public int updateCommodityProductUnitByProductUnitIdWithTx(CommodityProductUnitDTO commodityProductUnitDTO) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteService
				.updateCommodityProductUnitByProductUnitIdWithTx(commodityProductUnitDTO);
	}

	/**
	 * 查询所有pu库存信息
	 *
	 * @return
	 */
	public List<CommodityProductUnitWarehouseStockDTO> PUStock() {
		return commodityProductUnitWarehouseStockReadService
				.findCommodityProductUnitWarehouseStockAll(new CommodityProductUnitWarehouseStockDTO());
	}

	/**
	 * 查询所有sku信息
	 *
	 * @return
	 */
	public List<MerchantProductWarehouseStockDTO> SPUStock() {
		return merchantProductWarehouseStockReadService.findAll(new MerchantProductWarehouseStockDTO());
	}

	/**
	 * 根据条件查询pu库存信息
	 */
	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(
			CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockAll(dto);
	}

	/**
	 * 根据条件查询水库库存信息
	 *
	 * @param dto
	 * @return
	 */
	public List<MerchantProductWarehouseStockDTO> merchantProductWarehouseStockFindAll(
			MerchantProductWarehouseStockDTO dto) {
		return merchantProductWarehouseStockReadService.findAll(dto);
	}
	public ParseAddressJson getDeliveryPriceFromJd(String token,String address) {

		Map<String, String> map = new HashMap<>();
		//1.将地址转换成京东地址编号
		ParseAddressJson parseAddressJson = null;

		try {
			String result = jdUtils.parseAddress(address, token);
			JdResponse jdResponse = JSON.parseObject(result, JdResponse.class);
			logger.info("京东地址返回:"+JSON.toJSONString(jdResponse));
			if (jdResponse.isSuccess() && jdResponse.getResultCode().equals("0000")) {
				String json = jdResponse.getResult();
				parseAddressJson = JSON.parseObject(json, ParseAddressJson.class);
			}
		} catch (Exception e) {
logger.error("京东地址出现异常"+parseAddressJson.toString()+"原地址"+address);
		}
		return parseAddressJson;


	}
	private Map<String,Object> getJdProductStock(Long skuId,ReceiverAddressDTO addressDto){
		Map<String,Object> result=new HashMap<>();
		//0:无库存,1:有库存   2:无地址  3:不可售
		int jdStockStatus=2;
		SkuDTO skuDTO=new SkuDTO();
		skuDTO.setId(skuId);
		SkuDTO skuByPuId = skuReadService.findSkuById(skuDTO);
		String externalSkuId = skuId+"";
		if(skuByPuId!=null) {
			externalSkuId = skuByPuId.getExternalSkuId();
		}
		//String token = jdUtils.getAccessToken(jedisUtil);
		String token = jdUtils2.getAccessToken(jedisUtil);
		//4.校验是否在可售区域
		if(EmptyUtil.isNotEmpty(addressDto)){
			//2.校验京东是否可售
			String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
					+ addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
			ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token, address);
			String province = parseAddressJson.getProvince();
			String city = parseAddressJson.getCity();
			if(EmptyUtil.isNotEmpty(parseAddressJson)){
				result.put("city",city);
				result.put("province",province);
				result.put("address",addressDto.getGoodReceiverAddress());
				String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, externalSkuId, parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
				JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
				if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
					String json = jdResponse1.getResult();
					List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
					if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
						jdStockStatus=3;
					}
				}else{
					jdStockStatus=3;
				}


				//3.校验库存状态
				JdProductStockSearch stockSearch = new JdProductStockSearch();
				stockSearch.setSkuId(Long.valueOf(externalSkuId));
				stockSearch.setNum(Long.valueOf(1));
				List<JdProductStockSearch> searchList = new ArrayList<>();
				searchList.add(stockSearch);
				Integer provinceId = parseAddressJson.getProvinceId();
				Integer cityId = parseAddressJson.getCityId();
				Integer countyId = parseAddressJson.getCountyId();
				String arae = provinceId + "_" + cityId + "_" + countyId;
				String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), arae);
				List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
				if (EmptyUtil.isEmpty(jdProductStockArr)){
					throw new BusinessException("此商品库存异常,不可购买");
				}
				JSONObject jdProductStock=JSONObject.parseObject(jdProductStockArr.get(0).toString());
				if(EmptyUtil.isEmpty(jdProductStock)){
					throw new BusinessException("当前商品为商品库存存在问题");
				}else{
					String stockStateId = jdProductStock.getString("stockStateId");
					if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
						jdStockStatus=1;
					}else{
						jdStockStatus=0;

					}
				}
			}
		}



		result.put("jdStockStatus",jdStockStatus);
		logger.info("jdStockResult="+ JsonUtils.objectToJson(result));
		return result;

	}
	private Map<String,Object> getJdProductStock(CommodityProductUnitDTO pu,ReceiverAddressDTO addressDto){
		Map<String,Object> result=new HashMap<>();
		//0:无库存,1:有库存   2:无地址  3:不可售
		int jdStockStatus=2;
		SkuDTO skuDTO=new SkuDTO();
		skuDTO.setId(pu.getSkuId());
		SkuDTO skuByPuId = skuReadService.findSkuById(skuDTO);
		String externalSkuId = skuByPuId.getExternalSkuId();
		//String token = jdUtils.getAccessToken(jedisUtil);
		String token = jdUtils2.getAccessToken(jedisUtil);
		//4.校验是否在可售区域
		if(EmptyUtil.isNotEmpty(addressDto)){
			//2.校验京东是否可售
			String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
					+ addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
			ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token, address);
			String province = parseAddressJson.getProvince();
			String city = parseAddressJson.getCity();
			if(EmptyUtil.isNotEmpty(parseAddressJson)){
				result.put("city",city);
				result.put("province",province);
				String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, externalSkuId, parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
				JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
				if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
					String json = jdResponse1.getResult();
					List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
					if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
						jdStockStatus=3;
					}
				}else{
					jdStockStatus=3;
				}


				//3.校验库存状态
				JdProductStockSearch stockSearch = new JdProductStockSearch();
				stockSearch.setSkuId(Long.valueOf(externalSkuId));
				stockSearch.setNum(Long.valueOf(1));
				List<JdProductStockSearch> searchList = new ArrayList<>();
				searchList.add(stockSearch);
				Integer provinceId = parseAddressJson.getProvinceId();
				Integer cityId = parseAddressJson.getCityId();
				Integer countyId = parseAddressJson.getCountyId();
				String arae = provinceId + "_" + cityId + "_" + countyId;
				String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), arae);
				List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
				JSONObject jdProductStock=JSONObject.parseObject(jdProductStockArr.get(0).toString());
				if(EmptyUtil.isEmpty(jdProductStock)){
					throw new BusinessException("当前商品为商品库存存在问题");
				}else{
					String stockStateId = jdProductStock.getString("stockStateId");
					if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
						jdStockStatus=1;
					}else{
						jdStockStatus=0;

					}
				}
			}
		}

		result.put("jdStockStatus",jdStockStatus);
		logger.info("jdStockResult="+ JsonUtils.objectToJson(result));
		return result;

	}

	/**
	 * 根据suid查询su所有pu信息
	 *
	 * @param companyId
	 * @return
	 */
	public Map<String, Object> findCommodityProductUnitAllByStandardUnitId(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId) {
		StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(standardUnitId);
		Long merchantId =0L;
		Integer buyType=1;
		if(EmptyUtil.isNotEmpty(standardUnitById)){
			merchantId = standardUnitById.getMerchantId();
			buyType = standardUnitById.getBuyType();
		}

		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		Map<String, Object> maps = new HashMap<>();
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = standardProductUnitAttNameReadService
				.findByStandardUnitId(standardUnitId);

		List<PuIdAndPuAttName> puIdAndPuAttNames = new ArrayList<>();
		Map<String, Object> puMaps = new HashMap<>();
		List<Map<String, Object>> puList = new ArrayList<>();
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		// 查询以上架的所有pu信息
		commodityProductUnitDTO.setStandardUnitId(standardUnitId);
		LinkedList<CommodityProductUnitDTO> commodityProductUnits= new LinkedList<>();
		List<CommodityProductUnitDTO> commodityProductUnitList = findCommodityProductUnitAll(commodityProductUnitDTO);
		/*if(merchantId.equals(6)){
			//京东商品先校验是否详情同步
			if(EmptyUtil.isEmpty(commodityProductUnitList)){
				throw new BusinessException("pu不存在,suId="+standardUnitId);
			}
			buildJdProductDetail(commodityProductUnitList.get(0).getId());


		}


*/
		int jdStockStatus=2;
		if(merchantId==6){
			ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
			receiverAddressDTO.setIsDefault(1);
			receiverAddressDTO.setUserId(userId);
			List<ReceiverAddressDTO> receiverAddressAll = receiverAddressReadService.findReceiverAddressAll(receiverAddressDTO);
			Map<String, Object> jdProductStock=new HashMap<>();
			if(EmptyUtil.isNotEmpty(receiverAddressAll)){
				ReceiverAddressDTO addressDTO = receiverAddressAll.get(0);
				CommodityProductUnitDTO jdPu = commodityProductUnitList.get(0);
				jdProductStock= getJdProductStock(jdPu, addressDTO);
			}else{
				jdProductStock.put("jdStockStatus",jdStockStatus);
			}
			maps.put("jdStockStatusMap",jdProductStock);
		}
		// 组织puid和属性id
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			List<Long> puids = commodityProductUnitReadService.attValueByProductUnitId(commodityProductUnitDTO2.getId());
			// 根据puid查询pu库存信息
			Long realStockNum = commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(commodityProductUnitDTO2.getId());
			if (EmptyUtil.isEmpty(realStockNum)) {
				realStockNum = 0L;
			}
			Long storeRealStockNum = 0L;
			if(EmptyUtil.isEmpty(storeId) || storeId.equals(1L) || storeId.equals(2L)) {
				// 商城不查门店库存
				commodityProductUnitDTO2.setRealStockNum(realStockNum);
			} else {
				StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
				storeProductUnitDTO.setCommodityProductUnitId(commodityProductUnitDTO2.getId());
				storeProductUnitDTO.setStoreId(storeId);
				List<StoreProductUnitDTO> storeProductUnitDTOList = storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);
				if (EmptyUtil.isNotEmpty(storeProductUnitDTOList)) {
					// 查询门店库存
					storeRealStockNum = storePuWarehouseStockReadService.realStockNumByStoreProductUnitId(storeProductUnitDTOList.get(0).getId(), storeId);
					if (EmptyUtil.isEmpty(storeRealStockNum)) {
						storeRealStockNum = 0L;
					}
				}
				// 比较两个库存，取较小值
				commodityProductUnitDTO2.setRealStockNum(realStockNum < storeRealStockNum ? realStockNum : storeRealStockNum);
			}
			commodityProductUnitDTO2.setPuAttValueIds(puids);
			if (commodityProductUnitDTO2.getIsVendible() == 1) {
				PuIdAndPuAttName puIdAndPuAttName = new PuIdAndPuAttName();
				puIdAndPuAttName.setProductUnitId(commodityProductUnitDTO2.getId());
				puIdAndPuAttName.setPuAttNameIds(puids);
				puIdAndPuAttName.setRealStockNum(commodityProductUnitDTO2.getRealStockNum());
				puIdAndPuAttName.setStatus(commodityProductUnitDTO2.getStatus());
				puIdAndPuAttNames.add(puIdAndPuAttName);
			}
		}

		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			// 根据puid查询pu属性值id集合
			List<Long> puids = commodityProductUnitReadService
					.attValueByProductUnitId(commodityProductUnitDTO2.getId());
			commodityProductUnitDTO2.setPuAttValueIds(puids);
			commodityProductUnits.addLast(commodityProductUnitDTO2);
		}

		if(f == 0){
			recursionSort(companyDTO.getCompanyType(),commodityProductUnits, standardProductUnitAttNameDTOs, 0, puList,buyType);
			maps.put("pu", puList);
			maps.put("puAttNames", AttNameDTOTOMAP(standardProductUnitAttNameDTOs, puIdAndPuAttNames));
			return maps;
		}else if(f == 1){
			return IOSData(buyType,companyDTO.getCompanyType(),commodityProductUnits,standardProductUnitAttNameDTOs,0);
		} else if(f == 2 || f == 3){
			// 组织web数据
			webData(buyType,companyDTO.getCompanyType(),commodityProductUnitList,puMaps);
			maps.put("pu", puMaps);
			maps.put("puAttNames", AttNameDTOTOMAP(standardProductUnitAttNameDTOs, puIdAndPuAttNames));
			return maps;
		}
		return maps;
	}

	/**
	 * 根据suid查询su所有pu信息
	 *
	 * @param companyId
	 * @return
	 */
	public Map<String, Object> findJdCommodityProductUnitAllByStandardUnitId(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Long addressId) {
		StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(standardUnitId);
		Long merchantId =0L;
		Integer buyType=1;
		if(EmptyUtil.isNotEmpty(standardUnitById)){
			merchantId = standardUnitById.getMerchantId();
			buyType = standardUnitById.getBuyType();
		}

		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		Map<String, Object> maps = new HashMap<>();
		/*List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = standardProductUnitAttNameReadService
				.findByStandardUnitId(standardUnitId);*/

		List<PuIdAndPuAttName> puIdAndPuAttNames = new ArrayList<>();
		Map<String, Object> puMaps = new HashMap<>();
		List<Map<String, Object>> puList = new ArrayList<>();
		/*CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		// 查询以上架的所有pu信息
		commodityProductUnitDTO.setStandardUnitId(standardUnitId);*/
		LinkedList<CommodityProductUnitDTO> commodityProductUnits= new LinkedList<>();
		/*List<CommodityProductUnitDTO> commodityProductUnitList = findCommodityProductUnitAll(commodityProductUnitDTO);*/
		/*if(merchantId.equals(6)){
			//京东商品先校验是否详情同步
			if(EmptyUtil.isEmpty(commodityProductUnitList)){
				throw new BusinessException("pu不存在,suId="+standardUnitId);
			}
			buildJdProductDetail(commodityProductUnitList.get(0).getId());


		}


*/

		int jdStockStatus=2;
		ReceiverAddressDTO addressDTO;
		if(addressId==null) {
			ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
			receiverAddressDTO.setIsDefault(1);
			receiverAddressDTO.setUserId(userId);
			List<ReceiverAddressDTO> receiverAddressAll = receiverAddressReadService.findReceiverAddressAll(receiverAddressDTO);
			if(EmptyUtil.isNotEmpty(receiverAddressAll)){
				addressDTO = receiverAddressAll.get(0);
			}else {
				addressDTO = null;
			}
		}else {
			addressDTO = receiverAddressReadService.findReceiverAddressById(addressId);
		}

		boolean jdStockStatusFlag = false;
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
			if(RuntimeContext.cacheUser().getCompanyId()!=null) {
				List<CompanyConfigDTO> configs = userClient.findUserCompanyConfigs(RuntimeContext.cacheUser().getId());
				for(CompanyConfigDTO one : configs) {
					if(one.getKey().equalsIgnoreCase("jd.stock.status")) {
						if (EmptyUtil.isNotEmpty(one.getValue())){
							jdStockStatusFlag = Boolean.valueOf(one.getValue());
						}

					}
				}
			}
		}

		logger.info("设置京东商品库存限制："+jdStockStatusFlag);

		Map<String, Object> jdProductStock=new HashMap<>();
		if(addressDTO!=null) {
			jdProductStock= getJdProductStock(standardUnitId, addressDTO);
		}else {
			jdProductStock.put("jdStockStatus",jdStockStatus);
		}

		maps.put("jdStockStatusMap",jdProductStock);
		// 组织puid和属性id
		/*for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			List<Long> puids = commodityProductUnitReadService.attValueByProductUnitId(commodityProductUnitDTO2.getId());
			// 根据puid查询pu库存信息
			Long realStockNum = commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(commodityProductUnitDTO2.getId());
			if (EmptyUtil.isEmpty(realStockNum)) {
				realStockNum = 0L;
			}
			Long storeRealStockNum = 0L;
			if(EmptyUtil.isEmpty(storeId) || storeId.equals(1L) || storeId.equals(2L)) {
				// 商城不查门店库存
				commodityProductUnitDTO2.setRealStockNum(realStockNum);
			} else {
				StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
				storeProductUnitDTO.setCommodityProductUnitId(commodityProductUnitDTO2.getId());
				storeProductUnitDTO.setStoreId(storeId);
				List<StoreProductUnitDTO> storeProductUnitDTOList = storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);
				if (EmptyUtil.isNotEmpty(storeProductUnitDTOList)) {
					// 查询门店库存
					storeRealStockNum = storePuWarehouseStockReadService.realStockNumByStoreProductUnitId(storeProductUnitDTOList.get(0).getId(), storeId);
					if (EmptyUtil.isEmpty(storeRealStockNum)) {
						storeRealStockNum = 0L;
					}
				}
				// 比较两个库存，取较小值
				commodityProductUnitDTO2.setRealStockNum(realStockNum < storeRealStockNum ? realStockNum : storeRealStockNum);
			}
			commodityProductUnitDTO2.setPuAttValueIds(puids);
			if (commodityProductUnitDTO2.getIsVendible() == 1) {
				PuIdAndPuAttName puIdAndPuAttName = new PuIdAndPuAttName();
				puIdAndPuAttName.setProductUnitId(commodityProductUnitDTO2.getId());
				puIdAndPuAttName.setPuAttNameIds(puids);
				puIdAndPuAttName.setRealStockNum(commodityProductUnitDTO2.getRealStockNum());
				puIdAndPuAttName.setStatus(commodityProductUnitDTO2.getStatus());
				puIdAndPuAttNames.add(puIdAndPuAttName);
			}
		}

		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			// 根据puid查询pu属性值id集合
			List<Long> puids = commodityProductUnitReadService
					.attValueByProductUnitId(commodityProductUnitDTO2.getId());
			commodityProductUnitDTO2.setPuAttValueIds(puids);
			commodityProductUnits.addLast(commodityProductUnitDTO2);
		}*/

		/*if(f == 0){
			recursionSort(companyDTO.getCompanyType(),commodityProductUnits, standardProductUnitAttNameDTOs, 0, puList,buyType);
			maps.put("pu", puList);
			maps.put("puAttNames", AttNameDTOTOMAP(standardProductUnitAttNameDTOs, puIdAndPuAttNames));
			return maps;
		}else if(f == 1){
			return IOSData(buyType,companyDTO.getCompanyType(),commodityProductUnits,standardProductUnitAttNameDTOs,0);
		} else */if(f == 2 || f == 3){
			// 组织web数据
			/*webData(buyType,companyDTO.getCompanyType(),commodityProductUnitList,puMaps);*/
        	JdProductDTO dto = new JdProductDTO();
        	dto.setId(standardUnitId);
			JdProductDTO  jdProduct = jdProductManage.findJdProductSimpleById(dto);
			JdAllSkuInfo  allSku = jdProductManage.getAllSku(jdProduct,addressDTO);
			allSku.setDefaultImg(jdProduct.getImagePath());
			Map<String, Object> map = new HashMap<>();
        	map.put("skuInfo", allSku.skuInfo(standardUnitId));
        	map.put("buyType",1);
        	map.put("standardUnitId", standardUnitId);
        	map.put("standardUnitName", jdProduct.getName());
        	map.put("status", jdProduct.standardUnitState());
        	// 设置su商品销售价格
        	map.put("salePrice", jdProduct.getPrice());
        	map.put("marketPrice", jdProduct.getMarketPrice());
			logger.info("京东商品id:{}商品：{}商品售价:{}小于市场价{},结果毛利:{},是否纠正库存无货",standardUnitId,jdProduct.getName(),jdProduct.getPrice(),jdProduct.getMarketPrice(),jdProduct.getProfit());
			if(jdProduct.getProfit() ==null || jdProduct.getProfit() <=0){
				jdStockStatusFlag = false;
        		logger.info("京东商品：{}商品售价:{}小于市场价{},意味着毛利小于等于0,结果毛利:{},应纠正库存无货",jdProduct.getName(),jdProduct.getPrice(),jdProduct.getMarketPrice(),jdProduct.getProfit());
			}
        	map.put("freightExplain","以确认订单页面计算的实际运费为准");
        	map.put("shipmentsExplain", "以商品运营方的实际发货时间为准");
        	map.put("content", jdProduct.getIntroduction().replaceAll("width:750px", "width:100%"));
        	map.put("salesVolume", standardUnitId%100);
        	map.put("isOwnMerchant", 0);

        	map.put("commodityTemplateId", 2);
        	map.put("storeId", 1);
        	map.put("activityCode", "大厨管家总店");
        	map.put("presellPeriod", null);
        	map.put("relevanceSuId", null);
        	map.put("saleWay", 1);
    		//设置运营方名称
        	map.put("merchantName", "电商特供品");
        	map.put("standardUnitMembersExplain", "");

        	Map<String, Object> attmap = new HashMap<>();
			List<Map<String, Object>> attmapList = new ArrayList<>();
			attmap.put("attValue",jdProduct.getName());
			attmapList.add(attmap);
			map.put("attList", attmapList);

			String imagePath = jdProduct.getImagePath();
			map.put("limitBuyNum", 1);
			map.put("productUnitId", standardUnitId);
			map.put("productUnitName", jdProduct.getName());

            map.put("puPicUrl", imagePath);
            map.put("realStockNum", jdStockStatusFlag?100:0);
            map.put("isVendible", 1);

        	// 根据puid查询pu属性值id集合
        	StringBuffer stringBuffer = new StringBuffer();
        	// 根据pu属性值id集合查询pu属性值信息
        	AttributeValueDTO tmp = new AttributeValueDTO();
        	tmp.setId(standardUnitId);
        	tmp.setValue(jdProduct.getName());
        	List<AttributeValueDTO> attributeValueDTOs = new ArrayList<AttributeValueDTO>();
        	attributeValueDTOs.add(tmp);
        	StringBuffer keyName = new StringBuffer();
        	for (int i = 0; i < attributeValueDTOs.size(); i++) {
        		if (i >= attributeValueDTOs.size() - 1) {
        			stringBuffer.append(attributeValueDTOs.get(i).getId());
        			keyName.append(attributeValueDTOs.get(i).getValue());
        		} else {
        			stringBuffer.append(attributeValueDTOs.get(i).getId());
        			stringBuffer.append(";");
        			keyName.append(attributeValueDTOs.get(i).getValue());
        			keyName.append(",");
        		}

        	}
        	map.put("keyName", keyName);
        	puMaps.put(stringBuffer.toString(), map);

			maps.put("pu", puMaps);
			List<Map<String, Object>> attValueMaps = new ArrayList<>();

			Map<String, Object> map0 = new HashMap<>();
			Map<String, Object> map1 = new HashMap<>();
			Map<String, Object> map2 = new HashMap<>();
			Map<String, Object> map3 = new HashMap<>();
			Map<String, Object> map4 = new HashMap<>();
			Map<String, Object> strings1 = new HashMap<>();
			Map<String, Object> strings2 = new HashMap<>();
			Map<String, Object> strings3 = new HashMap<>();
			Map<String, Object> strings4 = new HashMap<>();
			List<Map<String, Object>> attList0 = new ArrayList<>();
			List<Map<String, Object>> attList1 = new ArrayList<>();
			List<Map<String, Object>> attList2 = new ArrayList<>();
			List<Map<String, Object>> attList3 = new ArrayList<>();
			List<Map<String, Object>> attList4 = new ArrayList<>();
			map0.put("attNameId", 100);
			map0.put("attName", "商品规格");
			if(allSku!=null && allSku.getSkuinfos()!=null && allSku.getSkuinfos().size()>0) {
				allSku.imgN4();
				//allSku.initStock(100);

				maps.put("puAttNames", allSku);
				/*attValueMaps.add();*/
			}


			map1.put("attNameId", 1);
			map1.put("attName", "是否为电子卡券");
			strings1.put("attValue", "否");
			strings1.put("attValueId", 2);
			strings1.put("status", 3);
			strings1.put("realStockNum", jdStockStatusFlag?100:0);
			attList1.add(strings1);
			map1.put("attValue", attList1);

			map2.put("attNameId", 2);
			map2.put("attName", "是否存在unit库存");
			strings2.put("attValue", "否");
			strings2.put("attValueId", 4);
			strings2.put("status", 3);
			strings2.put("realStockNum", jdStockStatusFlag?100:0);
			attList2.add(strings2);
			map2.put("attValue", attList2);

			map3.put("attNameId", 3);
			map3.put("attName", "是否在app内使用");
			strings3.put("attValue", "否");
			strings3.put("attValueId", 6);
			strings3.put("status", 3);
			strings3.put("realStockNum", jdStockStatusFlag?100:0);
			attList3.add(strings3);
			map3.put("attValue", attList3);

			map4.put("attNameId", 4);
			map4.put("attName", "第三方对接参数");
			strings4.put("attValue", "京东");
			strings4.put("attValueId", 13);
			strings4.put("status", 3);
			strings4.put("realStockNum", jdStockStatusFlag?100:0);
			attList4.add(strings4);
			map4.put("attValue", attList4);

			/*attValueMaps.add(map1);
			attValueMaps.add(map2);
			attValueMaps.add(map3);
			attValueMaps.add(map4);*/
			//logger.info("return返回京东商品详情结果maps:{}",JSON.toJSONString(maps));
			return maps;
		}
		//logger.info("返回京东商品详情结果maps:{}",JSON.toJSONString(maps));
		return maps;
	}

	public Map<String, Object> findJdStockStatus(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Long addressId) {
		StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(standardUnitId);
		Long merchantId =0L;
		Integer buyType=1;
		if(EmptyUtil.isNotEmpty(standardUnitById)){
			merchantId = standardUnitById.getMerchantId();
			buyType = standardUnitById.getBuyType();
		}

		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		Map<String, Object> maps = new HashMap<>();

		int jdStockStatus=2;
		ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
		receiverAddressDTO.setIsDefault(1);
		receiverAddressDTO.setUserId(userId);
		ReceiverAddressDTO addressDTO = receiverAddressReadService.findReceiverAddressById(addressId);
		Map<String, Object> jdProductStock=new HashMap<>();
		if(addressDTO!=null){
			jdProductStock= getJdProductStock(standardUnitId, addressDTO);
		}else{
			jdProductStock.put("jdStockStatus",jdStockStatus);
		}
		maps.put("jdStockStatusMap",jdProductStock);
		return maps;
	}
	private void buildJdProductDetail(JedisUtil jedisUtil,Long puId) {
		SkuDTO skuByPuId = skuReadService.findSkuByPuId(puId);
		String extandSkuId = skuByPuId.getExternalSkuId();

		JdProductDTO jdProductDTO = new JdProductDTO();
		jdProductDTO.setId(Long.valueOf(extandSkuId));
		JdProductDTO jdProductById = jdProductReadService.findJdProductById(jdProductDTO);
		if(EmptyUtil.isEmpty(jdProductById)){
			throw new BusinessException("当前商品京东已移除");
		}
		if(jdProductById.getIsDetail()==0){
			//还未更新商品详情
			JSONObject resultObj = jdUtils.getDetail(jedisUtil, Long.valueOf(extandSkuId));
			String skuInfo = jdUtils.getSimilarSku(jedisUtil, Long.valueOf(extandSkuId));
			String skuImg = jdUtils.getSkuImage(jedisUtil, Long.valueOf(extandSkuId));


			JdProductDTO jd= new JdProductDTO();
			jd.setId(Long.valueOf(extandSkuId));

		/*String category = resultObj.getString("category");
		jdProductDTO.setCategoryId(Long.parseLong(category.substring(category.lastIndexOf(";") + 1)));*/
			jd.setImagePath(skuImg);
			jd.setSkuJson(skuInfo);
			jd.setIntroduction(resultObj.getString("wxintroduction"));
			jd.setLowestBuy(resultObj.getInteger("LowestBuy"));
			jd.setIs7ToReturn(resultObj.getInteger("isToReturn"));
			jd.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
			jd.setIsSelf(resultObj.getInteger("isSelf"));
			jd.setNoReasonToReturn(resultObj.getInteger("noReasonToReturn"));
			jd.setParam(resultObj.getString("param"));
			jd.setSpuName(resultObj.getString("pName"));
			jd.setThwa(resultObj.getInteger("thwa"));
			jd.setIsDetail(1);
			jdProductWriteService.updateJdProductWithTx(jd);
			//更新pu的起够量
			if(EmptyUtil.isNotEmpty(jd.getLowestBuy())){
				CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();
				productUnitDTO.setId(puId);
				productUnitDTO.setLimitBuyNum(Long.valueOf(jd.getLowestBuy()));
				commodityProductUnitWriteService.updateCommodityProductUnitWithTx(productUnitDTO);
			}



		}

	}

	/**
	 * 组织web数据
	 * @param buyType
	 * @param companyType
	 * @param commodityProductUnitList
	 * @param puMaps
	 */
	private void webData(Integer buyType, Integer companyType, List<CommodityProductUnitDTO> commodityProductUnitList, Map<String, Object> puMaps) {
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			Map<String, Object> map = new HashMap<>();
			map.put("productUnitId", commodityProductUnitDTO2.getId());
			map.put("productUnitName", commodityProductUnitDTO2.getName());
			map.put("limitBuyNum", commodityProductUnitDTO2.getLimitBuyNum());
			map.put("buyType", buyType);
			switch (companyType){
				case 0:
					map.put("salePrice", commodityProductUnitDTO2.getSalePrice());
					break;
				case 1:
					map.put("salePrice", commodityProductUnitDTO2.getDemoSalePrice());
					break;
				case 2:
					map.put("salePrice", commodityProductUnitDTO2.getCompetingSalePrice());
					break;
			}
			map.put("puPicUrl", commodityProductUnitDTO2.getPuPicUrl());
			map.put("status", commodityProductUnitDTO2.getStatus());
			if (commodityProductUnitDTO2.getRealStockNum().longValue() < 0) {
				commodityProductUnitDTO2.setIsVendible(0);
			}
			map.put("realStockNum", commodityProductUnitDTO2.getRealStockNum());
			map.put("isVendible", commodityProductUnitDTO2.getIsVendible());

			// 根据puid查询pu属性值id集合
			StringBuffer stringBuffer = new StringBuffer();
			// 根据pu属性值id集合查询pu属性值信息
			List<AttributeValueDTO> attributeValueDTOs = attributeValueReadService.findByAttributeValueIds(commodityProductUnitDTO2.getPuAttValueIds());

			StringBuffer keyName = new StringBuffer();
			for (int i = 0; i < attributeValueDTOs.size(); i++) {
				if (i >= attributeValueDTOs.size() - 1) {
					stringBuffer.append(attributeValueDTOs.get(i).getId());
					keyName.append(attributeValueDTOs.get(i).getValue());
				} else {
					stringBuffer.append(attributeValueDTOs.get(i).getId());
					stringBuffer.append(";");
					keyName.append(attributeValueDTOs.get(i).getValue());
					keyName.append(",");
				}

			}
			map.put("keyName", keyName);
			puMaps.put(stringBuffer.toString(), map);
		}

	}

	private List<Map<String, Object>> AttNameDTOTOMAP(List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs,List<PuIdAndPuAttName> puIdAndPuAttNames){
		List<Map<String, Object>> attValueMaps = new ArrayList<>();
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO : standardProductUnitAttNameDTOs) {
			Map<String, Object> map = new HashMap<>();
			map.put("attNameId", standardProductUnitAttNameDTO.getAttNameId());
			map.put("attName", standardProductUnitAttNameDTO.getAttName());
			List<Map<String, Object>> attList = new ArrayList<>();

			// 根据spu属性id查询spu属性值信息
			List<StandardProductUnitAttValueDTO> list = standardProductUnitAttValueReadService
					.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
			for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : list) {
				Integer status = 4;
				Map<String, Object> strings = new HashMap<>();
				strings.put("attValue", standardProductUnitAttValueDTO.getAttValue());
				strings.put("attValueId", standardProductUnitAttValueDTO.getAttValueId());
				boolean isSave = false;
				Long realStockNum = 0L;
				// 循环所有有效的pu信息
				for (PuIdAndPuAttName puIdAndPuAttName : puIdAndPuAttNames) {
					List<Long> puAttNameIds = puIdAndPuAttName.getPuAttNameIds();
					// 根据pu所有属性值id查询是否存在该规格值下
					if (puAttNameIds.contains(standardProductUnitAttValueDTO.getAttValueId())) {
						isSave = true;
						if (puIdAndPuAttName.getStatus() == 3) {
							realStockNum = realStockNum + puIdAndPuAttName.getRealStockNum();
							status = 3;
						}

					}
				}
				strings.put("status", status);
				strings.put("realStockNum", realStockNum);
				if (isSave) {
					attList.add(strings);
				}

			}
			map.put("attValue", attList);
			attValueMaps.add(map);
		}
		return attValueMaps;
	}
	//IOS独享数据
	private Map<String, Object> IOSData(Integer buyType, Integer companyType, LinkedList<CommodityProductUnitDTO> commodityProductUnitList,
										List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs, int i) {
		Map<String, Object> maps = new HashMap<>();
		List<Map<String, Object>> attValueMaps = new ArrayList<>();
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO : standardProductUnitAttNameDTOs) {
			Map<String, Object> map = new HashMap<>();
			map.put("attNameId", standardProductUnitAttNameDTO.getAttNameId());
			map.put("attName", standardProductUnitAttNameDTO.getAttName());
			List<Map<String, Object>> attList = new ArrayList<>();
			// 根据spu属性id查询spu属性值信息
			List<StandardProductUnitAttValueDTO> list = standardProductUnitAttValueReadService
					.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
			for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : list) {
				Map<String, Object> strings = new HashMap<>();
				strings.put("attValue", standardProductUnitAttValueDTO.getAttValue());
				strings.put("attValueId", standardProductUnitAttValueDTO.getAttValueId());
				attList.add(strings);
			}
			map.put("attValue", attList);
			attValueMaps.add(map);
		}

		List<Map<String, Object>> puList = new ArrayList<>();
		recursionSort(companyType, commodityProductUnitList, standardProductUnitAttNameDTOs, i, puList, buyType);
		maps.put("pu", puList);
		maps.put("puAttNames", attValueMaps);
		return maps;
	}
	/**
	 * 按规格顺序进行排序
	 * @param companyType
	 * @param commodityProductUnitDTOs
	 * @param standardProductUnitAttNameDTOs
	 * @param i 规格索引
	 * @param puList
	 * @param buyType
	 */
	private void recursionSort(Integer companyType, LinkedList<CommodityProductUnitDTO> commodityProductUnitDTOs,
							   List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs, int i, List<Map<String, Object>> puList, Integer buyType){
			// 根据spu属性id查询spu属性值信息
			List<StandardProductUnitAttValueDTO> list = standardProductUnitAttValueReadService
					.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTOs.get(i).getId());
			// 初始化规格值数量的桶
			LinkedList<LinkedList<CommodityProductUnitDTO>> buckets = new LinkedList<LinkedList<CommodityProductUnitDTO>>();
			for (int j = 0; j < list.size(); j++) {
				LinkedList<CommodityProductUnitDTO> commodityProductUnitDTOList = new LinkedList<>();
				buckets.add(commodityProductUnitDTOList);
			}
				// 将队列数据依次取出
				while(commodityProductUnitDTOs.size() != 0){
					CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitDTOs.pop();
					for (int j = 0; j < list.size(); j++) {
						// 根据属性值id依次放入对应桶中
						if(commodityProductUnitDTO2.getPuAttValueIds().contains(list.get(j).getAttValueId())){
							if(EmptyUtil.isEmpty(commodityProductUnitDTO2.getPuAttValueIdsSort())){
								List<Long> puAttValueIdsSort = new ArrayList<>();
								puAttValueIdsSort.add(list.get(j).getAttValueId());
								commodityProductUnitDTO2.setPuAttValueIdsSort(puAttValueIdsSort);
							}else{
								commodityProductUnitDTO2.getPuAttValueIdsSort().add(list.get(j).getAttValueId());
							}
							buckets.get(j).addLast(commodityProductUnitDTO2);
						}

					}
				}
				// 循环桶，如果不是
				for (LinkedList<CommodityProductUnitDTO> linkedList : buckets) {
					if(standardProductUnitAttNameDTOs.size() - 1 != i){
						recursionSort(companyType, linkedList,standardProductUnitAttNameDTOs,i+1,puList, buyType);
					}else{
						for (CommodityProductUnitDTO commodityProductUnitDTO : linkedList) {
							Map<String, Object> puData = setPUData(companyType,commodityProductUnitDTO,buyType);
							puList.add(puData);
						}

					}
				}


	}
	//DTOTOMAP
	private Map<String, Object> setPUData(Integer companyType, CommodityProductUnitDTO commodityProductUnitDTO, Integer buyType){
		Map<String, Object> map = new HashMap<>();
		map.put("productUnitId", commodityProductUnitDTO.getId());
		map.put("productUnitName", commodityProductUnitDTO.getName());
		map.put("buyType", buyType);
		switch (companyType){
			case 0:
				map.put("salePrice", commodityProductUnitDTO.getSalePrice());
				break;
			case 1:
				map.put("salePrice", commodityProductUnitDTO.getDemoSalePrice());
				break;
			case 2:
				map.put("salePrice", commodityProductUnitDTO.getCompetingSalePrice());
				break;
		}
		map.put("puPicUrl", commodityProductUnitDTO.getPuPicUrl());
		map.put("status", commodityProductUnitDTO.getStatus());
		if (commodityProductUnitDTO.getRealStockNum().longValue() < 0) {
			commodityProductUnitDTO.setIsVendible(0);
		}
		map.put("realStockNum", commodityProductUnitDTO.getRealStockNum());
		map.put("isVendible", commodityProductUnitDTO.getIsVendible());
		// 根据pu属性值id集合查询pu属性值信息
		List<AttributeValueDTO> attributeValueDTOs = attributeValueReadService.findByAttributeValueIds(commodityProductUnitDTO.getPuAttValueIdsSort());
		List<Long> attValueList = commodityProductUnitDTO.getPuAttValueIdsSort();

		List<Map<String, Object>> atNameMapList = new ArrayList<>();
		for (int j = 0; j < attValueList.size(); j++) {
			for (AttributeValueDTO attributeValueDTO : attributeValueDTOs) {
				if(attValueList.get(j).equals(attributeValueDTO.getId())){
					Map<String, Object> atNameMap = new HashMap<>();
					atNameMap.put("attValueId", attributeValueDTO.getId());
					atNameMap.put("attNameId", attributeValueDTO.getAttributeNameId());
					atNameMap.put("attValue", attributeValueDTO.getValue());
					atNameMapList.add(atNameMap);
					break;
				}
			}
		}
		// 根据puid查询pu属性值id集合
		StringBuffer stringBuffer = new StringBuffer();
		StringBuffer keyName = new StringBuffer();
		for (int i = 0; i < atNameMapList.size(); i++) {
			if (i >= atNameMapList.size() - 1) {
				stringBuffer.append(atNameMapList.get(i).get("attValueId"));
				keyName.append(atNameMapList.get(i).get("attValue"));
			} else {
				stringBuffer.append(atNameMapList.get(i).get("attValueId"));
				stringBuffer.append(";");
				keyName.append(atNameMapList.get(i).get("attValue"));
				keyName.append(",");
			}

		}
		map.put("attNameIds", atNameMapList);
		map.put("keyName", keyName);
		map.put("specGroup", stringBuffer);
		return map;
	}

	public Long realStockNumByCommodityProductUnitId(Long puId) {
		return commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(puId);
	}

	public List<CommodityProductUnitDTO> findCommodityProductUnitLimit(CommodityProductUnitDTO dto) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.findCommodityProductUnitLimit(dto);
	}


	public List<CommodityProductUnitDTO> findPuListBySuId(Long suId){
		return commodityProductUnitReadService.findPuListBySuId(suId);
	}


	public Long checkJdProductDetail(JedisUtil jedisUtil, Long suId) {
		List<CommodityProductUnitDTO> puListBySuId = commodityProductUnitReadService.findPuListBySuId(suId);
		if(EmptyUtil.isEmpty(puListBySuId)){
			throw new BusinessException("当前su不存在pu");
		}
		Long skuId = puListBySuId.get(0).getSkuId();
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setId(skuId);
		SkuDTO skuById = skuReadService.findSkuById(skuDTO);
		if(skuById.getMerchantId().equals(6L)){
			//京东商品校验
			buildJdProductDetail(jedisUtil,puListBySuId.get(0).getId());

		}
		return skuById.getMerchantId();
	}



	/**
	 * 根据suid查询su所有pu信息
	 *
	 * @param companyId
	 * @return
	 */
	public Map<String, Object> findCakeCommodityProductUnitAllByStandardUnitId(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Long addressId,String productId) {
		StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(standardUnitId);
		Long merchantId =0L;
		Integer buyType=1;
		if(EmptyUtil.isNotEmpty(standardUnitById)){
			merchantId = standardUnitById.getMerchantId();
			buyType = standardUnitById.getBuyType();
		}

		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		Map<String, Object> maps = new HashMap<>();

		List<PuIdAndPuAttName> puIdAndPuAttNames = new ArrayList<>();
		Map<String, Object> puMaps = new HashMap<>();
		List<Map<String, Object>> puList = new ArrayList<>();
		LinkedList<CommodityProductUnitDTO> commodityProductUnits= new LinkedList<>();
		int jdStockStatus=2;
		ReceiverAddressDTO addressDTO;
		if(addressId==null) {
			ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
			receiverAddressDTO.setIsDefault(1);
			receiverAddressDTO.setUserId(userId);
			List<ReceiverAddressDTO> receiverAddressAll = receiverAddressReadService.findReceiverAddressAll(receiverAddressDTO);
			if(EmptyUtil.isNotEmpty(receiverAddressAll)){
				addressDTO = receiverAddressAll.get(0);
			}else {
				addressDTO = null;
			}
		}else {
			addressDTO = receiverAddressReadService.findReceiverAddressById(addressId);
		}
		CakeProductDetailSearchDTO search = new CakeProductDetailSearchDTO();
		search.setProduct_id(StringUtils.isNotEmpty(productId)?productId:String.valueOf(standardUnitId));
		JsonResult<CakeProductDetailDTO> searchResult =  cakeProductManage.searchProductCalcPriceDetail(search);

		if(Objects.isNull(searchResult) || Objects.isNull(searchResult.getData())){
			throw new BusinessException("未找到对应的商品信息");
		}
		CakeProductDetailDTO  cakeProductDetailDTO = searchResult.getData();
		CakeProductDetailProductsDTO productDetailProductsDTO = cakeProductDetailDTO.getProduct();
		List<CakeProductDetailSpecsDTO> allSku = cakeProductDetailDTO.getSpecs();
		//Map<String, String>  priceMap = channelPriceHelper.calcPlatformPrice(productDetailProductsDTO.getMarket_price(),productDetailProductsDTO.getPrice(),channelEnterpriseConfigs,null);
		Map<String, Object> map = new HashMap<>();
		map.put("skuInfo", allSku);
		map.put("buyType",1);
		map.put("standardUnitId", standardUnitId);
		map.put("standardUnitName", productDetailProductsDTO.getTitle());
		map.put("status", (productDetailProductsDTO.getStatus() !=null && Integer.valueOf(productDetailProductsDTO.getStatus()).intValue()==1?3:4));
		// 设置su商品销售价格
		//map.put("salePrice", CollectionUtils.isEmpty(priceMap)?productDetailProductsDTO.getPrice():new BigDecimal(priceMap.get(ChannelPriceConstants.CHANNEL_PRICE_KEY)));
		//map.put("marketPrice", CollectionUtils.isEmpty(priceMap)?productDetailProductsDTO.getMarket_price():new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
		map.put("salePrice", productDetailProductsDTO.getPrice());
		map.put("marketPrice", productDetailProductsDTO.getMarket_price());

		map.put("freightExplain","以确认订单页面计算的实际运费为准");
		map.put("shipmentsExplain", "以商品运营方的实际发货时间为准");
		map.put("salesVolume", standardUnitId%100);
		map.put("content", cakeProductDetailDTO.getContent_images());
		map.put("isOwnMerchant", 0);

		map.put("commodityTemplateId", 2);
		map.put("storeId", 1);
		map.put("activityCode", "大厨管家总店");
		map.put("presellPeriod", null);
		map.put("relevanceSuId", null);
		map.put("saleWay", 1);
		//设置运营方名称
		map.put("merchantName", "蛋糕专卖");
		map.put("merchantId", 7);
		map.put("standardUnitMembersExplain", "");



		String imagePath = productDetailProductsDTO.getImage_path();
		map.put("limitBuyNum", 1);
		map.put("productUnitId", standardUnitId);
		map.put("productUnitName", productDetailProductsDTO.getTitle());

		map.put("puPicUrl", imagePath);
		map.put("realStockNum", 100);
		map.put("isVendible", 1);

		// 根据puid查询pu属性值id集合
		StringBuffer stringBuffer = new StringBuffer();
		// 根据pu属性值id集合查询pu属性值信息
		Long stock=0L;
		boolean hasMax = false;
		List<SkuInfoAttVO> skuList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(allSku)){
			SkuInfoAttVO skuInfoAttVO = null;
			for (CakeProductDetailSpecsDTO spec : allSku) {
				skuInfoAttVO = new SkuInfoAttVO();
				skuInfoAttVO.setAttValueId(spec.getId());
				skuInfoAttVO.setAttValue(spec.getName());
				skuInfoAttVO.setRealStockNum(StringUtils.isNotEmpty(spec.getStock())?Long.valueOf(spec.getStock()):100);
				skuInfoAttVO.setStatus((productDetailProductsDTO.getStatus() !=null && Integer.valueOf(productDetailProductsDTO.getStatus()).intValue()==1?3:4));
				//Map<String, String>  skuPriceMap = channelPriceHelper.calcPlatformPrice(spec.getMarket_price(),spec.getClearing_price(),channelEnterpriseConfigs,null);
				//skuInfoAttVO.setSalePrice(new BigDecimal(skuPriceMap.get(ChannelPriceConstants.CHANNEL_PRICE_KEY)));
				skuInfoAttVO.setSalePrice(new BigDecimal(spec.getPrice()));
				skuList.add(skuInfoAttVO);
				if(StringUtils.isNotEmpty(spec.getStock()) && Objects.equals(spec.getStock(),"-9999999")){
					hasMax = true;
				}
				if(Objects.nonNull(skuInfoAttVO.getRealStockNum())){
					stock = stock+skuInfoAttVO.getRealStockNum();
				}
			}
		}
		map.put("skuInfo", skuList);
		map.put("realStockNum", hasMax?-9999999:stock);
		Map<String, Object> jdProductStock=new HashMap<>();
		jdProductStock.put("jdStockStatus",(hasMax ||stock>0)?1:0);
		maps.put("jdStockStatusMap",jdProductStock);

		// 根据pu属性值id集合查询pu属性值信息
		AttributeValueDTO tmp = new AttributeValueDTO();
		tmp.setId(StringUtils.isNotEmpty(productId)?Long.valueOf(productId):standardUnitId);
		tmp.setValue(productDetailProductsDTO.getTitle());
		List<AttributeValueDTO> attributeValueDTOs = new ArrayList<>();
		attributeValueDTOs.add(tmp);
		StringBuffer keyName = new StringBuffer();
		for (int i = 0; i < attributeValueDTOs.size(); i++) {
			if (i >= attributeValueDTOs.size() - 1) {
				stringBuffer.append(attributeValueDTOs.get(i).getId());
				keyName.append(attributeValueDTOs.get(i).getValue());
			} else {
				stringBuffer.append(attributeValueDTOs.get(i).getId());
				stringBuffer.append(";");
				keyName.append(attributeValueDTOs.get(i).getValue());
				keyName.append(",");
			}

		}
		map.put("keyName", keyName);
		puMaps.put(stringBuffer.toString(), map);

		maps.put("pu", puMaps);



		List<ProductAttrBean> pa = new ArrayList<ProductAttrBean>();
		for (int i = 0; i < attributeValueDTOs.size(); i++) {
			List<HashMap<String,Object>> atts = new ArrayList<>();
			ProductAttrBean productAttrDTO = new ProductAttrBean();
			productAttrDTO.setGroupName(attributeValueDTOs.get(i).getValue());

			HashMap<String,Object> att = new HashMap<>();
			att.put("attValue", attributeValueDTOs.get(i).getId());
			att.put("attName", attributeValueDTOs.get(i).getValue());

			productAttrDTO.setAtts(atts);
			atts.add(att);
			pa.add(productAttrDTO);
		}
		map.put("attList",pa);
//
//		if(!CollectionUtils.isEmpty(skuList)){
//			Optional<SkuInfoAttVO> minSalePriceCondition = findMinSalePrice(skuList);
//			minSalePriceCondition.ifPresent(condition -> {
//				map.put("salePrice", condition.getSalePrice());
//			});
//		}

		return maps;
	}

	// 使用Stream API找到具有最小salePrice的SkuInfoAttVO对象
	public static Optional<SkuInfoAttVO> findMinSalePrice(List<SkuInfoAttVO> skuList) {
		return skuList.stream()
				.min(Comparator.comparing(SkuInfoAttVO::getSalePrice));
	}


	/**
	 * 根据suid查询su所有pu信息
	 *
	 * @param companyId
	 * @return
	 */
	public Map<String, Object> findWorldBuyCommodityProductUnitAllByStandardUnitId(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Long addressId,String productId) {
		StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(standardUnitId);
		Long merchantId =0L;
		Integer buyType=1;
		if(EmptyUtil.isNotEmpty(standardUnitById)){
			merchantId = standardUnitById.getMerchantId();
			buyType = standardUnitById.getBuyType();
		}

		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		Map<String, Object> maps = new HashMap<>();

		List<PuIdAndPuAttName> puIdAndPuAttNames = new ArrayList<>();
		Map<String, Object> puMaps = new HashMap<>();
		List<Map<String, Object>> puList = new ArrayList<>();
		LinkedList<CommodityProductUnitDTO> commodityProductUnits= new LinkedList<>();
		int jdStockStatus=2;
		ReceiverAddressDTO addressDTO;
		if(addressId==null) {
			ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
			receiverAddressDTO.setIsDefault(1);
			receiverAddressDTO.setUserId(userId);
			List<ReceiverAddressDTO> receiverAddressAll = receiverAddressReadService.findReceiverAddressAll(receiverAddressDTO);
			if(EmptyUtil.isNotEmpty(receiverAddressAll)){
				addressDTO = receiverAddressAll.get(0);
			}else {
				addressDTO = null;
			}
		}else {
			addressDTO = receiverAddressReadService.findReceiverAddressById(addressId);
		}
		ChannelProductDetailRequestVO detailRequestVO = new ChannelProductDetailRequestVO();
		detailRequestVO.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
		detailRequestVO.setProductId(productId);
		detailRequestVO.setSkuId(String.valueOf(standardUnitId));
		ChannelProductDetailVO channelProductDetailVO = channelProductFacade.getChannelProductDetail(detailRequestVO);
		if(channelProductDetailVO == null){
			throw new BusinessException("商品不存在");
		}
		ChannelProductDTO channelProductDTO =  channelProductDetailVO.getChannelProductDTO();
		if(channelProductDTO == null){
			throw new BusinessException("商品明细不存在");
		}
		ChannelProductDescriptionDTO descriptionDTO = channelProductDetailVO.getDescriptionDTO();
		List<String> pictureList = channelProductDetailVO.getPictureList();
		List<ChannelProductSkuDTO> skuAll = channelProductDetailVO.getSkuList();
		List<ChannelProductBatchDTO>  batchDTOList = channelProductDetailVO.getBatchDTOList();
		ChannelProductBatchDTO currBatchDTO = channelProductFacade.getCurrBatch(detailRequestVO,batchDTOList);
		Map<String, Object> map = new HashMap<>();
		map.put("buyType",1);
		map.put("standardUnitId", standardUnitId);
		map.put("standardUnitName", channelProductDTO.getTitle());
		map.put("status", (channelProductDTO.getStatus() !=null && Integer.valueOf(channelProductDTO.getStatus()).intValue()==1?3:4));
		// 设置su商品销售价格
		//map.put("salePrice", CollectionUtils.isEmpty(priceMap)?productDetailProductsDTO.getPrice():new BigDecimal(priceMap.get(ChannelPriceConstants.CHANNEL_PRICE_KEY)));
		//map.put("marketPrice", CollectionUtils.isEmpty(priceMap)?productDetailProductsDTO.getMarket_price():new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
		map.put("salePrice", (currBatchDTO !=null && currBatchDTO.getPrice() !=null)?currBatchDTO.getPrice():channelProductDTO.getPrice());
		map.put("marketPrice", (currBatchDTO !=null && currBatchDTO.getPriceMarket() !=null)?currBatchDTO.getPriceMarket():channelProductDTO.getMarketPrice());

		map.put("freightExplain","以确认订单页面计算的实际运费为准");
		map.put("shipmentsExplain", "以商品运营方的实际发货时间为准");
		map.put("salesVolume", standardUnitId%100);
		map.put("content", descriptionDTO !=null ?descriptionDTO.getContent():channelProductDTO.getTitle());
		map.put("isOwnMerchant", 0);

		map.put("commodityTemplateId", 2);
		map.put("storeId", 1);
		map.put("activityCode", "大厨管家总店");
		map.put("presellPeriod", null);
		map.put("relevanceSuId", null);
		map.put("saleWay", 1);
		//设置运营方名称
		map.put("merchantName", "海外专卖");
		map.put("merchantId", 8);
		map.put("standardUnitMembersExplain", "");



		String imagePath = channelProductDetailVO.getProductImg();
		map.put("limitBuyNum", 1);
		map.put("productUnitId", standardUnitId);
		map.put("productUnitName", channelProductDTO.getTitle());

		map.put("puPicUrl", imagePath);
		map.put("realStockNum", 100);
		map.put("isVendible", 1);

		// 根据puid查询pu属性值id集合
		StringBuffer stringBuffer = new StringBuffer();
		// 根据pu属性值id集合查询pu属性值信息
		Long stock=0L;
		boolean hasMax = false;
		List<SkuInfoAttVO> skuList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(skuAll)){
			SkuInfoAttVO skuInfoAttVO = null;
			Map<String,Long> calcStockMap = new HashMap<>();
			for (ChannelProductBatchDTO spec : batchDTOList) {
				skuInfoAttVO = new SkuInfoAttVO();
				skuInfoAttVO.setAttValueId(String.valueOf(spec.getId()));
				skuInfoAttVO.setAttValue(spec.getSpecName());
				skuInfoAttVO.setRealStockNum(spec.getNum() !=null?Long.valueOf(spec.getNum()):0);
				skuInfoAttVO.setStatus((channelProductDTO.getStatus() !=null && Integer.valueOf(channelProductDTO.getStatus()).intValue()==1?3:4));
				skuInfoAttVO.setSalePrice(spec.getPrice());
				skuList.add(skuInfoAttVO);
				calcStockMap.put(spec.getBatchNo(),skuInfoAttVO.getRealStockNum());
			}
			for (Long value : calcStockMap.values()) {
				stock += value;
			}
		}
		map.put("skuInfo", skuList);
		map.put("realStockNum",stock);
		Map<String, Object> jdProductStock=new HashMap<>();
		jdProductStock.put("jdStockStatus",(hasMax ||stock>0)?1:0);
		maps.put("jdStockStatusMap",jdProductStock);

		// 根据pu属性值id集合查询pu属性值信息
		AttributeValueDTO tmp = new AttributeValueDTO();
		tmp.setId(StringUtils.isNotEmpty(productId)?Long.valueOf(productId):standardUnitId);
		tmp.setValue(channelProductDTO.getTitle());
		List<AttributeValueDTO> attributeValueDTOs = new ArrayList<>();
		attributeValueDTOs.add(tmp);
		StringBuffer keyName = new StringBuffer();
		for (int i = 0; i < attributeValueDTOs.size(); i++) {
			if (i >= attributeValueDTOs.size() - 1) {
				stringBuffer.append(attributeValueDTOs.get(i).getId());
				keyName.append(attributeValueDTOs.get(i).getValue());
			} else {
				stringBuffer.append(attributeValueDTOs.get(i).getId());
				stringBuffer.append(";");
				keyName.append(attributeValueDTOs.get(i).getValue());
				keyName.append(",");
			}

		}
		map.put("keyName", keyName);
		puMaps.put(stringBuffer.toString(), map);

		maps.put("pu", puMaps);



		List<ProductAttrBean> pa = new ArrayList<ProductAttrBean>();
		for (int i = 0; i < attributeValueDTOs.size(); i++) {
			List<HashMap<String,Object>> atts = new ArrayList<>();
			ProductAttrBean productAttrDTO = new ProductAttrBean();
			productAttrDTO.setGroupName(attributeValueDTOs.get(i).getValue());

			HashMap<String,Object> att = new HashMap<>();
			att.put("attValue", attributeValueDTOs.get(i).getId());
			att.put("attName", attributeValueDTOs.get(i).getValue());

			productAttrDTO.setAtts(atts);
			atts.add(att);
			pa.add(productAttrDTO);
		}
		map.put("attList",pa);
		return maps;
	}
}
