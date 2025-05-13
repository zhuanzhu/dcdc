package com.egeo.components.product.business.impl;


import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.bean.JdKeyWordCIdSearchResultBean;
import com.egeo.components.product.business.impl.Thread.CakeProductThread;
import com.egeo.components.product.business.impl.Thread.CommonThreadPoolExecutor;
import com.egeo.components.product.business.impl.Thread.JdKeyWordCIdSearchThread;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.vo.*;
import com.egeo.components.utils.JdUtils2;
import com.egeo.utils.*;
import com.google.common.collect.Lists;
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
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.order.client.ReceiverAddressClient;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.business.MerchantProductManage;
import com.egeo.components.product.business.impl.Thread.JdProductThread;
import com.egeo.components.product.converter.JdProductConverter;
import com.egeo.components.product.dao.JdEnterpriseConfigDAO;
import com.egeo.components.product.dao.JdPriceDAO;
import com.egeo.components.product.dao.JdUnsearchCategoryDAO;
import com.egeo.components.product.enums.JdPriceType;
import com.egeo.components.product.facade.AttributeNameFacade;
import com.egeo.components.product.facade.AttributeValueFacade;
import com.egeo.components.product.facade.CategoryAttNameFacade;
import com.egeo.components.product.facade.CategoryAttValueFacade;
import com.egeo.components.product.facade.CategoryTreeNodeFacade;
import com.egeo.components.product.facade.JdPriceConfigFacade;
import com.egeo.components.product.facade.JdProductFacade;
import com.egeo.components.product.facade.JdProductInnerIdFacade;
import com.egeo.components.product.facade.MerchantProductFacade;
import com.egeo.components.product.facade.ProductAttValueFacade;
import com.egeo.components.product.facade.ProductFacade;
import com.egeo.components.product.facade.ProductLimitProfitFacade;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.EnterpriseClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.JdPriceTools;
import com.egeo.components.utils.JsonUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.delivery.jd.JdAllSkuInfo;
import com.egeo.utils.delivery.jd.SaleAttr;
import com.egeo.utils.delivery.jd.SkuInfo;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Service("jdProduct")
public class JdProductManageImpl implements JdProductManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private JedisUtil cache;
	@Autowired
	private JdUnsearchCategoryDAO jdUnsearchCategoryDAO;

	@Resource(name = "jdProductFacade")
	private JdProductFacade jdProductFacade;

	@Resource(name = "categoryAttNameFacade")
	private CategoryAttNameFacade categoryAttNameFacade;

	@Resource(name = "attributeNameFacade")
	private AttributeNameFacade attributeNameFacade;

	@Resource(name = "attributeValueFacade")
	private AttributeValueFacade attributeValueFacade;

	@Resource(name = "categoryAttValueFacade")
	private CategoryAttValueFacade categoryAttValueFacade;

	@Resource(name = "productAttValueFacade")
	private ProductAttValueFacade productAttValueFacade;

    @Autowired
    private EnterpriseClient enterpriseService;
    @Autowired
    private CompanyClient companyClient;


    @Autowired
    private UserClient userClient;
	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "categoryTreeNodeFacade")
	private CategoryTreeNodeFacade categoryTreeNodeFacade;

	@Resource(name = "merchantProductFacade")
	private MerchantProductFacade merchantProductFacade;

	@Resource(name = "jdPriceConfigFacade")
	private JdPriceConfigFacade jdPriceConfigFacade;

	@Resource(name = "skuFacade")
	private SkuFacade skuFacade;
	@Autowired
	private ReceiverAddressClient receiverAddressClient;

	@Resource(name = "merchantProduct")
	private MerchantProductManage merchantProductManage;
	@Resource(name = "productLimitProfitFacade")
	private ProductLimitProfitFacade productLimitProfitFacade;
	@Resource(name = "jdProductInnerIdFacade")
	private JdProductInnerIdFacade jdProductInnerIdFacade;

	@Resource
	private JdPriceDAO jdPrice;
	@Resource
	private JdEnterpriseConfigDAO jdConfig;
	@Autowired
	private JdUtils jdUtils;
	@Resource
	private JdUtils2 jdUtils2;
	@Autowired
	private Upload uploadService;

	private Long productIdOld;
	private Long pictureIdOld;
	private Long attValueIdOld ;
	private Long productAttNameIdOld;
	private Long spuIdOld;
	private Long spuAttNameIdOld;
	private Long skuAttNameIdOld;
	private Long skuIdOld;
	private Long merchantProductIdOld;
	private Long merchantPictureIdOld;
	private Long productUnitIdOld;
	private Long puIdOld;

	@Override
	public JdProductDTO findJdProductById(JdProductDTO dto) {
		JdProductDTO rslt = fetchJdProductDetail(dto);
		rslt.setLedger(null);
		return rslt;
	}
	@Override
	public JdProductDTO findJdProductById2(JdProductDTO dto) {
		return fetchJdProductDetail(dto);
	}
	@Override
	public List<JdProductDTO> findJdProductByIds(List<JdProductDTO> dto) {
		return fetchJdProductSimples(dto);
	}
	@Override
	public JdProductDTO findJdProductSimpleById(JdProductDTO dto) {
		return fetchJdProductSimple(dto);
	}
	public JdAllSkuInfo getAllSku(JdProductDTO data,ReceiverAddressDTO addressDto) {
		Long skuId = data.getId();
		JdAllSkuInfo skuInfo = jdUtils.getAllSku(cache, skuId);
		if(skuInfo.getSkuinfos()==null || skuInfo.getSkuinfos().size()==0) {
			Map<String, Map<String, String>> skuMaps = new HashMap<>();
			skuInfo.setSkuinfos(skuMaps);
			Map<String, String> skuMap = new HashMap<>();
			skuMap.put("skuId", skuId.longValue()+"");
			skuMaps.put(skuId.longValue()+"", skuMap);
		}
		if(skuInfo.getRelation()==null || skuInfo.getRelation().size()==0) {
			List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
			skuInfo.setRelation(skuInfoList);
			SkuInfo dimone = new SkuInfo();
			dimone.setDim(1);
			dimone.setSaleName("");
			skuInfoList.add(dimone);
			List<SaleAttr> saleAttrList = new ArrayList<SaleAttr>();
			dimone.setSaleAttrList(saleAttrList);
			SaleAttr att = new SaleAttr();
			saleAttrList.add(att);
			att.setImagePath(data.getImagePath());
			att.setSaleValue(data.getName());
			List<Long> spuIds = new ArrayList<>();
			spuIds.add(skuId);
			att.setSkuIds(spuIds);
		}
		if(skuInfo!=null && skuInfo.getSkuinfos()!=null && skuInfo.getSkuinfos().size()>0) {
			List<Long> spuIds = new ArrayList<>();
			List<String> spuIds2 = new ArrayList<>();
			for(String skuIdd : skuInfo.getSkuinfos().keySet()) {
				spuIds.add(Long.valueOf(skuIdd));
				spuIds2.add(skuIdd);
			}

			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			/*List<JdEnterpriseConfigDTO> data = jdConfig.findAllConfig();*/
			List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
			JSONArray priceResultObj = jdUtils.getSellPrice(token, spuIds);
			HashMap<String,Map<String, Object>> allImges = jdUtils.getSkuImages(cache, spuIds2);
			for(Entry<String, Map<String, String>> entry : skuInfo.getSkuinfos().entrySet()) {
				String key = entry.getKey();
				if(allImges.containsKey(key)) {
					entry.getValue().put("image", allImges.get(key).get("primaryImg").toString());
				}
			}

			Map<String,Map<String,String>> skuStockMap = new HashMap<String,Map<String,String>>();

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

			logger.info("设置京东商品sku库存限制："+jdStockStatusFlag);

			if(addressDto==null) {
				for(Entry<String, Map<String, String>> entry : skuInfo.getSkuinfos().entrySet()) {
					String key = entry.getKey();
					if(!skuStockMap.containsKey(key)) {
						skuStockMap.put(key,new HashMap<String,String>());
					}
					Map<String,String> skuStockdata = skuStockMap.get(key);
					skuStockdata.put("realStockNum", "0");
					skuStockdata.put("jdStockStatus", "2");
				}
			}else {
				List<JdProductStockSearch> searchList = new ArrayList<>();
				for(Entry<String, Map<String, String>> entry : skuInfo.getSkuinfos().entrySet()) {
					String key = entry.getKey();
					JdProductStockSearch stockSearch = new JdProductStockSearch();
					stockSearch.setSkuId(Long.valueOf(key));
					stockSearch.setNum(1l);
					searchList.add(stockSearch);
				}
				String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
					+ addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
				ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token, address);
				Integer provinceId = parseAddressJson.getProvinceId();
				Integer cityId = parseAddressJson.getCityId();
				Integer countyId = parseAddressJson.getCountyId();
				String area = provinceId + "_" + cityId + "_" + countyId;
				String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), area);
				List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
				if(jdProductStockArr!=null && jdProductStockArr.size()>0) {
					for(JSONObject jdProductStock : jdProductStockArr) {
						//0:无库存,1:有库存   2:无地址  3:不可售
						int jdStockStatus=0;
						if(!EmptyUtil.isEmpty(jdProductStock)){
							Long nowSkuId = jdProductStock.getLong("skuId");
							if(!skuStockMap.containsKey(nowSkuId.longValue()+"")) {
								skuStockMap.put(nowSkuId.longValue()+"",new HashMap<String,String>());
							}
							Map<String,String> skuStockdata = skuStockMap.get(nowSkuId.longValue()+"");

							String stockStateId = jdProductStock.getString("stockStateId");
							if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
								jdStockStatus=1;
							}else{
								jdStockStatus=0;
							}
							if(jdStockStatus==1 && jdStockStatusFlag) {
								Integer remainNum = (jdProductStock.containsKey("remainNum"))?jdProductStock.getInteger("remainNum"):0;

								skuStockdata.put("realStockNum", (remainNum==null||remainNum.intValue()==-1)?"100":((remainNum.intValue()<-1)?"0":remainNum.intValue()+""));
							}else {
								skuStockdata.put("realStockNum", "0");
							}
							skuStockdata.put("jdStockStatus", jdStockStatus+"");
						}
					}
				}


			}


			HashMap<String,JdPriceResultDTO> priceMap = new HashMap<String,JdPriceResultDTO>();
			for(int i=0;i<priceResultObj.size();i++) {
				JSONObject price = priceResultObj.getJSONObject(i);
				JdPriceResultDTO jdrslt = price.toJavaObject(JdPriceResultDTO.class);
				priceMap.put(jdrslt.getSkuIdStr(), jdrslt);
			}

			Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
			List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(spuIds, RuntimeContext.cacheUser().getEnterpriseId());
			if(jdPriceCustomizes!=null && jdPriceCustomizes.size()>0) {
				jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getPidStr, one -> one));;
			}

			for(Entry<String, Map<String, String>> entry : skuInfo.getSkuinfos().entrySet()) {
				String skuIdd = entry.getKey();
				Map<String, String> skuBody = entry.getValue();
				Map<String, String> skuStockData = skuStockMap.get(skuIdd);
				Map<String,Object> sellPriceMap = JdSellPrice(skuIdd, priceMap.get(skuIdd), jdPriceCustomizeMap.get(skuIdd), jdEnterpriseConfigs);
				BigDecimal sellPrice = (sellPriceMap!=null&&sellPriceMap.get("finalPrice")!=null)? (BigDecimal) sellPriceMap.get("finalPrice"):null;
				skuBody.put("status", "3");
				skuBody.put("jdStockStatus", skuStockData.get("jdStockStatus"));
				skuBody.put("realStockNum", skuStockData.get("realStockNum"));
				if(sellPrice!=null) {
					skuBody.put("price", sellPrice.setScale(2, RoundingMode.HALF_UP).toPlainString());
					boolean profitOk = (sellPriceMap!=null&&sellPriceMap.get("profitOk")!=null)? (Boolean) sellPriceMap.get("profitOk"):true;
					if (!profitOk){
						skuBody.put("realStockNum", "0");
						skuBody.put("jdStockStatus", "0");
					}
				}else {
					skuBody.put("status", "4");
				}

			}


		}
		return skuInfo;
	}
	@Override
	public List<JdPriceAuditingVO> findJdProductForPriceAuditing(List<JdPriceDTO> dto) {
		List<JdPriceAuditingVO> rslt = new ArrayList<JdPriceAuditingVO>();
		if(dto!=null && dto.size()>0) {

			List<Long> res = new ArrayList<>();
			for(JdPriceDTO one : dto) {
				if(one.getSpuId()==null) {
					res.add(one.getPid());
				}else {
					res.add(one.getSpuId());
				}
			}
			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			JSONArray priceResultObj = jdUtils.getSellPrice(token, res);
			/*HashMap<Long,JdPriceDTO> jdPriceConfigMap = new HashMap<Long,JdPriceDTO>();
			for(JdPriceDTO one : dto) {
				jdPriceConfigMap.put(one.getSpuId(), one);
			}*/
			HashMap<Long,JSONObject> priceMap = new HashMap<Long,JSONObject>();
			for(int i=0;i<priceResultObj.size();i++) {
				JSONObject price = priceResultObj.getJSONObject(i);
				priceMap.put(price.getLong("skuId"), price);
			}
			List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfig();

			for(JdPriceDTO one : dto) {
				Long skuId ;
				if(one.getSpuId()==null) {
					skuId = one.getPid();
				}else {
					skuId = one.getSpuId();
				}
				JdPriceAuditingVO data = new JdPriceAuditingVO(one);
				JSONObject resultObj = jdUtils.getDetail(cache, skuId);
				if(resultObj!=null && priceMap.containsKey(skuId)) {
					JSONObject priceObj = priceMap.get(skuId);
					//获取实时价格
					BigDecimal realPrice = priceObj.getBigDecimal("price");
					BigDecimal realJdPrice = priceObj.getBigDecimal("jdPrice");

					if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs,one.getEnterpriseId())){
						JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs,one.getEnterpriseId());
						if(defaultConfig!=null && defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
							realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
						}
					}

					BigDecimal finalPrice = BigDecimal.valueOf(realPrice.doubleValue());

					//优先级1的是代理商自定义价格
					if(one!=null && JdPriceType.isValidate(one.getPriceType())) {
						if(JdPriceType.IncreaseFixedValue.equal(one.getPriceType())) {
							finalPrice = realPrice.add(new BigDecimal(one.getPriceValue()));
						}else if(JdPriceType.IncreaseFixedRatio.equal(one.getPriceType())) {
							finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(one.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
						}else if(JdPriceType.FixedPrice.equal(one.getPriceType())) {
							finalPrice = new BigDecimal(one.getPriceValue());
						}
					}else {

						if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs,one.getEnterpriseId())) {
							//优先级2 代理商自定义京东配置
							JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs,one.getEnterpriseId());
							long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
							BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
							finalPrice = realPrice.add(realPrice.multiply(vv));
						}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs,one.getEnterpriseId())){
							//优先级3 平台给代理商定义的配置
							JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs,one.getEnterpriseId());
							long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
							BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
							finalPrice = realPrice.add(realPrice.multiply(vv));
						}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
							//优先级4 平台缺省配置
						}



					}
					//京东价格逻辑预设置
					data.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
					data.setIsSelf(resultObj.getInteger("isSelf"));
					data.setName(resultObj.getString("name"));
					finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

					if(finalPrice.compareTo(realJdPrice)>0) {
						//京东价格逻辑：若计算价格大于京东市场价，售价就是京东市场价
						finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
					}
					data.setEnterprisePrice(finalPrice.toPlainString());
					data.setJdPrice(realPrice.toPlainString());
					data.setMarketPrice(realJdPrice.toPlainString());
					rslt.add(data);

				}else {
					if(resultObj==null) {
						logger.info("京东商品异常："+skuId+"  产品详情为空   价格详情:"+(priceMap.containsKey(skuId)?priceMap.get(skuId).toJSONString():"无价格信息"));

					}else {
						logger.info("京东商品异常："+skuId+"  产品详情："+resultObj.toJSONString()+ "  \n价格详情:"+(priceMap.containsKey(skuId)?priceMap.get(skuId).toJSONString():"无价格信息"));

					}
				}
			}
		}
		return rslt;
	}

	/*
	@Override
	public PageResult<JdProductDTO> findJdProductOfPage(JdProductDTO dto, Pagination page) {
		return jdProductFacade.findJdProductOfPage(dto, page);
	}

	@Override
	public List<JdProductDTO> findJdProductAll(JdProductDTO dto) {
		return jdProductFacade.findJdProductAll(dto);
	}

	@Override
	public Long insertJdProductWithTx(JdProductDTO dto) {
		return jdProductFacade.insertJdProductWithTx(dto);
	}

	@Override
	public int updateJdProductWithTx(JdProductDTO dto) {
		return jdProductFacade.updateJdProductWithTx(dto);
	}

	@Override
	public int deleteJdProductWithTx(JdProductDTO dto) {
		return jdProductFacade.deleteJdProductWithTx(dto);
	}

	@Override
	public void syncJdCategory(String parentId) {
		Long treeId = jdProductFacade.findBackendCategoryTreeId();
		Long selfParentId = jdProductFacade.createJdTopCategoryIfNotExist(treeId);
		syncJdCategoryByParentId(treeId, parentId, selfParentId, 0);
	}*/


	public JsonResult<JSONObject> getJdCategory(String jdParentId, Integer catClass) {
		JSONObject result = jdUtils.getJdCategory(cache, jdParentId, catClass, null, null);

		return JsonResult.success(result);
	}
	/*
	 * 用于查询列表
	 */

	public JsonResult<PageResult<JdProductDTO>> searchOfCategoryLevel2(JDProductSearchDTO search) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(search.getCid2())) {
			throw new BusinessException("类目异常");
		}
		return search(search);
	}

	/**
	 * 代理商京东专区搜索
	 * @param search
	 * @return
	 */
	public JsonResult<PageResult<JdProductDTO>> search(JDProductSearchDTO search) {
		// TODO Auto-generated method stub
		//1.过滤出符合条件productActive+inStore 的结果，rsltMap<skuId,JdProductDTO>
		List<JdProductDTO> rslt = new ArrayList<JdProductDTO>();
		if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
	        if(RuntimeContext.cacheUser().getCompanyId()!=null) {
	        	CompanyDTO company = companyClient.findCompanyById(RuntimeContext.cacheUser().getCompanyId());
	        	if(company.getEnterpriseId()!=null) {
	        		logger.info("用户："+RuntimeContext.cacheUser().getLoginName()+"  登录公司id:"+RuntimeContext.cacheUser().getCompanyId().longValue()+" 代理id为空并填充成功");
	        		RuntimeContext.cacheUser().setEnterpriseId(company.getEnterpriseId());
	        	}else {
					return JsonResult.success(null);
		        }
	        }else {
				return JsonResult.success(null);
	        }
		}
		List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
		Set<String> cateporysDefine ;
		//判断搜索的类目是不是符合要求  如果设置了类目权限，那就必须要有cid2入参，否则报错
		JdEnterpriseConfigDTO enterpriseDefaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
		if(RuntimeContext.cacheUser().isNotPlatformUser() && enterpriseDefaultConfig!=null && StringUtils.isNotEmpty(enterpriseDefaultConfig.getJdCategorys())) {
			cateporysDefine = new HashSet<String>(Arrays.asList(enterpriseDefaultConfig.getJdCategorys().split(",")));
			if(StringUtils.isEmpty(search.getCid2()) || (!cateporysDefine.contains(search.getCid2()))) {
				throw new BusinessException("类目异常");
			}
		}
		List<String> cid1s=new ArrayList<>();
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
			if(RuntimeContext.cacheUser().getType().intValue()==4 && RuntimeContext.cacheUser().getCompanyId()!=null) {
				List<CompanyConfigDTO> configs = userClient.findUserCompanyConfigs(RuntimeContext.cacheUser().getId());
				for(CompanyConfigDTO one : configs) {
					if(one.getKey().equalsIgnoreCase("jd.category.cid1s.only")) {
						if (EmptyUtil.isNotEmpty(one.getValue())){
							cid1s=Arrays.asList(one.getValue().split(","));
						}
						logger.info("设置京东类目限制："+one.getValue());
					}
				}
			}
		}

		//设置缺省排序方式
		if(StringUtils.isEmpty(search.getSortType())) {
			search.setSortType("sort_totalsales15_desc");
		}

		if(StringUtils.isEmpty(search.getPriceMax())) {
			String max = JdPriceTools.getConfigPriceMax(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(max)) {
				search.setPriceMax(Integer.valueOf(max));
			}
		}else {
			String max = JdPriceTools.getConfigPriceMax(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(max) && Integer.valueOf(max)<search.getPriceMax()) {
				search.setPriceMax(Integer.valueOf(max));
			}
		}
		if(StringUtils.isEmpty(search.getPriceMin())) {
			String min = JdPriceTools.getConfigPriceMin(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(min)) {
				search.setPriceMin(Integer.valueOf(min));
			}
		}
		//设置多个京东类目限制时,轮询每个类目，结果存在时停止轮询
		PageResult<JdSearchHitResultDTO> hit = new PageResult<JdSearchHitResultDTO>();
		if (EmptyUtil.isNotEmpty(cid1s)){
			if (EmptyUtil.isNotEmpty(search.getCheckResultMap())){
				logger.info("关键字搜索查询");
				getLimitCIdSearchJdHitResult(search, jdEnterpriseConfigs, cid1s, hit,rslt);
			}else{
				logger.info("不是关键字搜索查询");
				for(String cid1:cid1s){
					search.setCid1(cid1);
					hit=searchJdHitResult(search,rslt,jdEnterpriseConfigs);
					if (Objects.nonNull(hit) && EmptyUtil.isNotEmpty(hit.getList())){
						break;
					}
				}
			}

		}else {
			hit=searchJdHitResult(search,rslt,jdEnterpriseConfigs);
		}
		//2.处理价格
		//2.1查询价格
		PageResult<JdProductDTO> data = new PageResult<JdProductDTO>();
		data.setList(rslt);
		data.setPageNo(hit.getPageNo());
		data.setPageSize(hit.getPageSize());
		data.setTotalSize(hit.getTotalSize());
		return JsonResult.success(data);
	}

	private void getLimitCIdSearchJdHitResult(JDProductSearchDTO search, List<JdEnterpriseConfigDTO> jdEnterpriseConfigs, List<String> cid1s, PageResult<JdSearchHitResultDTO> hit,List<JdProductDTO> rslt) {
		//经过线程池中获取不到企业id，采用传值方式
		search.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
		// 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
		ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
		// 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
		CompletionService<JdKeyWordCIdSearchResultBean> completionService = new ExecutorCompletionService<>(executor);
		List<Future<JdKeyWordCIdSearchResultBean>> results = new ArrayList<>();
		Future<JdKeyWordCIdSearchResultBean> future = null;
		int partitionSize =2;
		if(cid1s.size() >1 && cid1s.size() <=5){
			partitionSize =1;
		}
		List<List<String>> smallerLists = Lists.partition(cid1s, partitionSize);
		for (List<String> smallList : smallerLists) {
			future = completionService.submit(new JdKeyWordCIdSearchThread(search,smallList, jdEnterpriseConfigs));
			results.add(future);
		}
		Integer maxTotalSize = 0;
		Integer pageNo = 0;
		Integer pageSize=0;
		for (Future<JdKeyWordCIdSearchResultBean> fut : results) {
			try {
				JdKeyWordCIdSearchResultBean rt =  fut.get();
				if(Objects.isNull(rt)){
					continue;
				}
				if(!CollectionUtils.isEmpty(rt.getRslt())){
					rslt.addAll(rt.getRslt());
				}
				//返回最大totalSize
				if(rt.getMaxTotalSize()>maxTotalSize){
					maxTotalSize = rt.getMaxTotalSize();
				}
				pageNo = rt.getPageNo();
				pageSize = rt.getPageSize();
			}catch (Exception e){
				logger.error("线程池中获取京东商品发生异常:{}",e);
			}
		}
		hit.setTotalSize(maxTotalSize);
		if(pageSize!=null && pageSize.intValue()>0){
			hit.setPageSize(pageSize);
			hit.setPageNo(pageNo);
		}else{
			hit.setPageSize(search.getPageSize());
			hit.setPageNo(search.getPageIndex());
		}
	}

	/**
	 * 关键字搜索京东商品信息--后续多线程有调用
	 * @param search
	 * @param jdResultList
	 * @return
	 */
	@Override
	public PageResult<JdSearchHitResultDTO> searchJdHitResult(JDProductSearchDTO search,List<JdProductDTO> jdResultList,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs){
		PageResult<JdSearchHitResultDTO> hit = search(search, true, true);
		if(hit!=null && hit.getList()!=null && hit.getList().size()>0) {
			Map<String,JdSearchHitResultDTO> hitMap = hit.getList().stream().collect(Collectors.toMap(JdSearchHitResultDTO::getWareId, one -> one));
			Map<String, String> param = new HashMap<String, String>();
			Map<String,JdPriceResultDTO> priceMap = null;
			//String accessToken = jdUtils.getAccessToken(cache);
			String accessToken = jdUtils2.getAccessToken(cache);
			param.put("token", accessToken);
			logger.info("京东token："+accessToken);
			param.put("sku", String.join(",", hitMap.keySet())	);
			String resultPrice = HttpClientUtil.doPost(jdUtils.getSellPriceUrl, param, "UTF-8", 30000);
			if(resultPrice!=null && resultPrice.length()>0) {
				JSONObject priceRslt = JSON.parseObject(resultPrice);
				if(priceRslt.containsKey("success")&&priceRslt.getBooleanValue("success")) {
					if(priceRslt.containsKey("result")) {
						List<JdPriceResultDTO> priceResults = priceRslt.getJSONArray("result").toJavaList(JdPriceResultDTO.class);
						priceMap = priceResults.stream().collect(Collectors.toMap(JdPriceResultDTO::getSkuIdStr, one -> one));
					}
				}
			}
			List<Long> spuIds = hitMap.keySet().stream().map(Long::valueOf).collect(Collectors.toList());

			Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
			Long enterpriseId = EmptyUtil.isNotEmpty(search.getEnterpriseId())?search.getEnterpriseId():RuntimeContext.cacheUser().getEnterpriseId();
			List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(spuIds,enterpriseId);
			if(jdPriceCustomizes!=null && jdPriceCustomizes.size()>0) {
				jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getPidStr, one -> one));;
			}
			//是否存在抛弃
			boolean hasExistsFilter = false;
			for(JdSearchHitResultDTO data : hit.getList()) {
				JdProductDTO productDto = JoinJdSearchHitResultAndPrice(data, priceMap.get(data.getWareId()),jdPriceCustomizeMap.get(data.getWareId()),jdEnterpriseConfigs);
				if(productDto!=null) {
					jdResultList.add(productDto);
				}else{
					logger.info("京东商品在处理价格后被抛弃:{}",JSON.toJSONString(data));
					hasExistsFilter = true;
				}
			}

			if(hit.getTotalSize()==hit.getList().size()){
				//存在过滤抛弃,但是记录是查询完整的，所有都抛弃之后就不管了
				hasExistsFilter = false;
			}
			Map<String,Object> checkMap = search.getCheckResultMap();
			if(!CollectionUtils.isEmpty(checkMap)){
				//当前关键字搜索本地筛选发生抛弃
				checkMap.put(search.getKeyword(),hasExistsFilter);
			}
			Map<String,Object> catCheckMap = search.getCatCheckResultMap();
			if(!CollectionUtils.isEmpty(catCheckMap) && EmptyUtil.isNotEmpty(search.getCid2())){
				//当前关键字搜索本地筛选发生抛弃
				catCheckMap.put(search.getCid2(),hasExistsFilter);
			}
			/*
			for (Map.Entry<String, JdSearchHitResultDTO> entry : hitMap.entrySet()) {
				JdProductDTO productDto = JoinJdSearchHitResultAndPrice(entry.getValue(), priceMap.get(entry.getKey()),jdPriceCustomizeMap.get(entry.getKey()),jdEnterpriseConfigs);
				if(productDto!=null) {
					rslt.add(productDto);
				}
			}*/
		}
		return hit;
	}
	/*
	 * 用于代理商后台查询
	 */

	public JsonResult<PageResult<JdProductEnterpriseVO>> searchEnterprise(JDProductSearchDTO search) {
		// TODO Auto-generated method stub
		//1.过滤出符合条件productActive+inStore 的结果，rsltMap<skuId,JdProductDTO>
		List<JdProductEnterpriseVO> rslt = new ArrayList<JdProductEnterpriseVO>();
		if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
			return JsonResult.success(null);
		}

		List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
		Set<String> cateporysDefine ;
		//判断搜索的类目是不是符合要求  如果设置了类目权限，那就必须要有cid2入参，否则报错
		JdEnterpriseConfigDTO enterpriseDefaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
		if(RuntimeContext.cacheUser().isNotPlatformUser() && enterpriseDefaultConfig!=null && StringUtils.isNotEmpty(enterpriseDefaultConfig.getJdCategorys())) {
			cateporysDefine = new HashSet<String>(Arrays.asList(enterpriseDefaultConfig.getJdCategorys().split(",")));
			if(StringUtils.isEmpty(search.getCid2()) || (!cateporysDefine.contains(search.getCid2()))) {
				throw new BusinessException("类目异常");
			}
		}

		//设置缺省排序方式
		if(StringUtils.isEmpty(search.getSortType())) {
			search.setSortType("sort_totalsales15_desc");
		}

		if(StringUtils.isEmpty(search.getPriceMax())) {
			String max = JdPriceTools.getConfigPriceMax(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(max)) {
				search.setPriceMax(Integer.valueOf(max));
			}
		}
		if(StringUtils.isEmpty(search.getPriceMin())) {
			String min = JdPriceTools.getConfigPriceMin(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(min)) {
				search.setPriceMin(Integer.valueOf(min));
			}
		}

		//2.处理价格
		//2.1查询价格
		PageResult<JdProductEnterpriseVO> data = searchJdProduct(search,jdEnterpriseConfigs);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> exportEnterprise(JDProductSearchDTO search) {
		List<JdProductEnterpriseVO> rslt = new ArrayList<JdProductEnterpriseVO>();
		if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
			throw new BusinessException("当前登录信息失效");
		}

		List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
		Set<String> cateporysDefine ;
		//判断搜索的类目是不是符合要求  如果设置了类目权限，那就必须要有cid2入参，否则报错
		JdEnterpriseConfigDTO enterpriseDefaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
		if(RuntimeContext.cacheUser().isNotPlatformUser() && enterpriseDefaultConfig!=null && StringUtils.isNotEmpty(enterpriseDefaultConfig.getJdCategorys())) {
			cateporysDefine = new HashSet<String>(Arrays.asList(enterpriseDefaultConfig.getJdCategorys().split(",")));
			if(StringUtils.isEmpty(search.getCid2()) || (!cateporysDefine.contains(search.getCid2()))) {
				throw new BusinessException("类目异常");
			}
		}

		//设置缺省排序方式
		if(StringUtils.isEmpty(search.getSortType())) {
			search.setSortType("sort_totalsales15_desc");
		}

		if(StringUtils.isEmpty(search.getPriceMax())) {
			String max = JdPriceTools.getConfigPriceMax(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(max)) {
				search.setPriceMax(Integer.valueOf(max));
			}
		}
		if(StringUtils.isEmpty(search.getPriceMin())) {
			String min = JdPriceTools.getConfigPriceMin(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(min)) {
				search.setPriceMin(Integer.valueOf(min));
			}
		}
		PageResult<JdProductEnterpriseVO> pageResult=searchJdProduct(search,jdEnterpriseConfigs);
		int totalPage=pageResult.getTotalPage();
		while (EmptyUtil.isNotEmpty(pageResult.getList())){
			rslt.addAll(pageResult.getList());
			if ((pageResult.getPageNo()>=totalPage)){
				break;
			}
			search.setPageIndex(search.getPageIndex()+1);
			pageResult=searchJdProduct(search,jdEnterpriseConfigs);
		}
		return writeJdProductInExcel(rslt);
	}

	private JsonResult<Map<String, Object>> writeJdProductInExcel(List<JdProductEnterpriseVO> voList) {
		Workbook wsd = new XSSFWorkbook();
		Sheet cloneSheet = wsd.createSheet("京东产品");
		String[] headArr = {"商品ID", "SKU名称", "协议价格","销售价格", "京东市场价格", "销售毛利"};

		Row head = cloneSheet.createRow(0);
		for (int i = 0; i < headArr.length; i++) {
			head.createCell(i).setCellValue(headArr[i]);
		}

		for (int i = 0; i < voList.size(); i++) {
			JdProductEnterpriseVO vo = voList.get(i);
			Row row = cloneSheet.createRow(i + 1);
			row.createCell(0).setCellValue(String.valueOf(vo.getId()));
			row.createCell(1).setCellValue(vo.getName());
			row.createCell(2).setCellValue(vo.getPrice());
			row.createCell(3).setCellValue(vo.getSalePrice());
			row.createCell(4).setCellValue(vo.getMarketPrice());
			row.createCell(5).setCellValue(vo.getProfit()+"%");
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

	private PageResult<JdProductEnterpriseVO> searchJdProduct(JDProductSearchDTO search,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs){
		List<JdProductEnterpriseVO> rslt = new ArrayList<JdProductEnterpriseVO>();
		PageResult<JdSearchHitResultDTO> hit = search(search, true, true);
		if(hit!=null && hit.getList()!=null && hit.getList().size()>0) {
			Map<String,JdSearchHitResultDTO> hitMap = hit.getList().stream().collect(Collectors.toMap(JdSearchHitResultDTO::getWareId, one -> one));
			Map<String, String> param = new HashMap<String, String>();
			Map<String,JdPriceResultDTO> priceMap = null;
			//String accessToken = jdUtils.getAccessToken(cache);
			String accessToken = jdUtils2.getAccessToken(cache);
			param.put("token", accessToken);
			param.put("sku", String.join(",", hitMap.keySet())	);
			String resultPrice = HttpClientUtil.doPost(jdUtils.getSellPriceUrl, param, "UTF-8", 30000);
			if(resultPrice!=null && resultPrice.length()>0) {
				JSONObject priceRslt = JSON.parseObject(resultPrice);
				if(priceRslt.containsKey("success")&&priceRslt.getBooleanValue("success")) {
					if(priceRslt.containsKey("result")) {
						List<JdPriceResultDTO> priceResults = priceRslt.getJSONArray("result").toJavaList(JdPriceResultDTO.class);
						priceMap = priceResults.stream().collect(Collectors.toMap(JdPriceResultDTO::getSkuIdStr, one -> one));
					}
				}
			}
			List<Long> spuIds = hitMap.keySet().stream().map(Long::valueOf).collect(Collectors.toList());

			Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
			List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(spuIds, RuntimeContext.cacheUser().getEnterpriseId());
			if(jdPriceCustomizes!=null && jdPriceCustomizes.size()>0) {
				jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getPidStr, one -> one));;
			}

			for (JdSearchHitResultDTO hitResultDTO:hit.getList()){
				JdProductEnterpriseVO productDto = JoinEnterpriseJdSearchHitResultAndPrice(hitResultDTO, priceMap.get(hitResultDTO.getWareId()),jdPriceCustomizeMap.get(hitResultDTO.getWareId()),jdEnterpriseConfigs);
				if(productDto!=null) {
					productDto.setSource(3);
					rslt.add(productDto);
				}
			}
		}
		//2.处理价格
		//2.1查询价格
		PageResult<JdProductEnterpriseVO> data = new PageResult<JdProductEnterpriseVO>();

		//刷详情
		/*if(rslt!=null && rslt.size()>0) {
			for(JdProductDTO one : rslt) {
				//fetchJdProductDetail(one);
			}
		}*/
		data.setList(rslt);
		data.setPageNo(hit.getPageNo());
		data.setPageSize(hit.getPageSize());
		data.setTotalSize(hit.getTotalSize());
		return data;
	}

	public JdProductDTO fetchJdProductDetail(JdProductDTO data) {
		Long enterpriseId = EmptyUtil.isNotEmpty(data.getEnterpriseId())?data.getEnterpriseId():RuntimeContext.cacheUser().getEnterpriseId();
		Long skuId = data.getId();
		JSONObject resultObj = jdUtils2.getDetail(cache, skuId);
		if(resultObj!=null ) {
			System.out.println(resultObj);
			String skuInfo = jdUtils.getSimilarSku(cache, skuId);
			String skuImg = jdUtils.getSkuImage(cache, skuId);
			List<Long> res = new ArrayList<>();
			res.add(skuId);
			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			JSONArray priceResultObj = jdUtils.getSellPrice(token, res);
			JdProductDTO jdProductDTO = new JdProductDTO();
			if (Objects.isNull(priceResultObj) || priceResultObj.isEmpty()){
				throw new BusinessException("商品异常,不可购买");
			}
			JSONObject ob = (JSONObject) priceResultObj.get(0);
			//BigDecimal price = ob == null ? null : ob.getBigDecimal("price");
			//BigDecimal marketPrice = ob == null ? null : ob.getBigDecimal("marketPrice");
			JSONObject ledgerObj = new JSONObject();
			/*String category = resultObj.getString("category");
			jdProductDTO.setCategoryId(Long.parseLong(category.substring(category.lastIndexOf(";") + 1)));*/
			jdProductDTO.setId(skuId);
			jdProductDTO.setImagePath(skuImg);
			/*String imagePath = resultObj.getString("imagePath");
			if(imagePath!=null) {
				imagePath = "http://img13.360buyimg.com/n1/"+imagePath;
			}
			jdProductDTO.setImagePath(imagePath);*/
			jdProductDTO.setSkuJson(skuInfo);
			String introduction = resultObj.getString("introduction");
			if(introduction==null) {
				introduction = resultObj.getString("nappintroduction");
			}
			if(introduction==null) {
				introduction = resultObj.getString("wxintroduction");
			}
			jdProductDTO.setWxContent(resultObj.getString("wxintroduction"));
			jdProductDTO.setIntroduction(introduction);
			logger.info("字符串:" + jdProductDTO.getIntroduction());
			jdProductDTO.setIs7ToReturn(resultObj.getInteger("isToReturn"));
			jdProductDTO.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
			jdProductDTO.setIsSelf(resultObj.getInteger("isSelf"));
//			jdProductDTO.setLowestBuy(resultObj.getString(""));

			jdProductDTO.setName(resultObj.getString("name"));
			jdProductDTO.setNoReasonToReturn(resultObj.getInteger("noReasonToReturn"));
			jdProductDTO.setParam(resultObj.getString("paramDetailJson"));
//			jdProductDTO.setSaleState(resultObj.getString(""));
//			jdProductDTO.setSpuId(resultObj.getString(""));
			jdProductDTO.setSpuName(resultObj.getString("pName"));
			jdProductDTO.setState(jdUtils.getSkuState(cache, skuId));
			jdProductDTO.setSyncStatus(1);
			jdProductDTO.setThwa(resultObj.getInteger("thwa"));
			jdProductDTO.setCategoryIds(resultObj.getString("category"));
			if (resultObj.containsKey("taxInfo")){
				jdProductDTO.setTaxInfo(resultObj.getString("taxInfo"));
			}
			List<Long> spuIds = new ArrayList<Long>();
			spuIds.add(skuId);
			Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
			List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(spuIds, enterpriseId);
			ledgerObj.put("jdPriceCustomizes", jdPriceCustomizes);
			if(jdPriceCustomizes!=null && jdPriceCustomizes.size()>0) {
				jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getSpuIdStr, one -> one));;
			}
			//获取实时价格
			BigDecimal realPrice = ob.getBigDecimal("price");
			BigDecimal realJdPrice = ob.getBigDecimal("jdPrice");
			List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(enterpriseId);
			ledgerObj.put("jdEnterpriseConfigs", jdEnterpriseConfigs);
			BigDecimal ledgerPlateform = new BigDecimal(0);
			BigDecimal ledgerEnterprise = new BigDecimal(0);
			if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
				JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {

					//京东价格逻辑预设置
					//1 如果平台加价之后超过京东市场价，那么平台价格定位京东市场价
					ledgerPlateform = realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP));
					//1.若结算价+平台加价大于京东销售价格
					if(realPrice.add(ledgerPlateform).compareTo(realJdPrice)>0) {
						realPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
						//重新计算平台分成
						ledgerPlateform = BigDecimal.valueOf(realJdPrice.subtract(ob.getBigDecimal("price")).doubleValue());
					}else {
						realPrice = ob.getBigDecimal("price").add(ledgerPlateform);
					}
				}
			}

			BigDecimal finalPrice = BigDecimal.valueOf(realPrice.doubleValue());;

			JdPriceDTO customPrice = null;
			if(jdPriceCustomizeMap!=null && jdPriceCustomizeMap.size()>0) {
				customPrice = jdPriceCustomizeMap.get(skuId.longValue()+"");
			}
			//优先级1的是代理商自定义价格
			if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
				if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
					finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
				}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
					finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
				}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
					finalPrice = new BigDecimal(customPrice.getPriceValue());
				}
			}else {

				if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
					//优先级2 代理商自定义京东配置
					JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
					long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
					BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					finalPrice = realPrice.add(realPrice.multiply(vv));
				}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
					//优先级3 平台给代理商定义的配置
					JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
					long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
					BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					finalPrice = realPrice.add(realPrice.multiply(vv));
				}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
					//优先级4 平台缺省配置
				}



			}
			//京东价格逻辑预设置

			finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
			if(finalPrice.compareTo(realJdPrice)>0) {
				finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
			}
			ledgerEnterprise = finalPrice.subtract(realPrice);

			jdProductDTO.setPrice(finalPrice);
			if(finalPrice.compareTo(realJdPrice)<0) {
				//finalPrice<realJdPrice
				jdProductDTO.setMarketPrice(realJdPrice);
				//dto.setProfit(realJdPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
			}else {
				//finalPrice超过参考价，以finalPrice为准
				jdProductDTO.setMarketPrice(finalPrice);
				//dto.setProfit(finalPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
			}

			ledgerObj.put("ledgerPlateform", ledgerPlateform.setScale(2, RoundingMode.HALF_UP).toPlainString());
			ledgerObj.put("ledgerEnterprise", ledgerEnterprise.setScale(2, RoundingMode.HALF_UP).toPlainString());
			ledgerObj.put("jdPriceObj", ob);
			ledgerObj.put("marketPrice", jdProductDTO.getMarketPrice().toPlainString());
			ledgerObj.put("price", jdProductDTO.getPrice().toPlainString());
			ledgerObj.put("jdMarketPrice", realJdPrice.toPlainString());
			ledgerObj.put("jdPrice", realPrice.toPlainString());
			jdProductDTO.setLedger(ledgerObj.toJSONString());




			return jdProductDTO;
		}
		return null;




		/*
		Map<String, String> param = new HashMap<String, String>();
		Map<String,JdPriceResultDTO> priceMap = null;
		JSONObject detailJson = jdUtils.getDetail(cache, data.getId());
		if(detailJson!=null ) {

			data.setIntroduction(detailJson.getString("wxintroduction"));
			data.setLowestBuy(detailJson.getInteger("LowestBuy"));
			data.setIs7ToReturn(detailJson.getInteger("isToReturn"));
			data.setIsJdLogistics(detailJson.getInteger("isJDLogistics"));
			data.setIsSelf(detailJson.getInteger("isSelf"));
			data.setNoReasonToReturn(detailJson.getInteger("noReasonToReturn"));
			data.setParam(detailJson.getString("param"));
			data.setSpuName(detailJson.getString("pName"));
			data.setThwa(detailJson.getInteger("thwa"));

		}*/
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

	public Map<String,JdProductDTO> getJdProductStatStockAndPrice(Long companyId,Long enterpriseId,HashMap<String,Integer> skuNumMap,ReceiverAddressDTO addressDto,Boolean price,Boolean stock){

		long start = System.currentTimeMillis();
		HashMap<String,JdProductDTO> productMap = new HashMap<String,JdProductDTO>();
		for(String one : skuNumMap.keySet()) {
			JdProductDTO product = productMap.get(one);
			if(product==null) {
				product = new JdProductDTO();
				//设置缺省值
				product.setSaleState(0);
				product.setState(0);
				product.setStock(skuNumMap.get(one));
				if(price!=null && price) {
					product.setPrice(new BigDecimal(-1));
					product.setMarketPrice(new BigDecimal(-1));
				}

				product.setId(Long.valueOf(one));
				productMap.put(one, product);
			}
		}
		logger.info("京东商品开始查询状态，库存，价格");
		if(EmptyUtil.isNotEmpty(addressDto) && productMap!=null && productMap.size()>0) {
			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			String externalSkuId = StringUtils.join(",", productMap.keySet().toArray(new String[0]));
			String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
							+ addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
			ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token, address);
			/*String province = parseAddressJson.getProvince();
			String city = parseAddressJson.getCity();*/
			if(EmptyUtil.isNotEmpty(parseAddressJson)){
				//0.首先判断商品状态
				String skuStatFromJd = jdUtils.getSkuStatusFromJd(token, externalSkuId);
				JdResponse jdResponse0 = JSON.parseObject(skuStatFromJd, JdResponse.class);
				if(jdResponse0.isSuccess()&&jdResponse0.getResultCode().equals("0000")){
					String json = jdResponse0.getResult();
					List<JdSkuState> jdSkuState = JSON.parseArray(json, JdSkuState.class);
					for(JdSkuState oneStatus : jdSkuState) {
						Long nowSkuId = oneStatus.getSku();
						if(productMap.containsKey(nowSkuId.longValue()+"")) {
							JdProductDTO product = productMap.get(nowSkuId.longValue()+"");
							product.setState(oneStatus.getState());
						}
					}
				}
				logger.info("京东商品{}开始首先判断商品状态{}",externalSkuId,JSON.toJSONString(jdResponse0));
				//1.区域可售性
				String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, externalSkuId, parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
				JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
				if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
					String json = jdResponse1.getResult();
					List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
					for(JdSkuAddressSellStatus oneStatus : jdSkuStatus) {
						Long nowSkuId = oneStatus.getSkuId();
						if(productMap.containsKey(nowSkuId.longValue()+"")) {
							JdProductDTO product = productMap.get(nowSkuId.longValue()+"");
							product.setSaleState((oneStatus.getIsAreaRestrict()!=null && oneStatus.getIsAreaRestrict().equals("false"))?1:0);
						}
					}
				}
				logger.info("京东商品{}开始区域可售性{}",externalSkuId,JSON.toJSONString(jdResponse1));

				logger.info("京东商品是否查询库存{}准备的sku{}",stock,JSON.toJSONString(productMap));
				//2.库存
				if(stock!=null && stock) {
					List<JdProductStockSearch> searchList = new ArrayList<>();
					for(String key : productMap.keySet()) {
						if(skuNumMap.containsKey(key)) {
							JdProductStockSearch stockSearch = new JdProductStockSearch();
							stockSearch.setSkuId(Long.valueOf(key));
							stockSearch.setNum(skuNumMap.get(key)==null?0l:skuNumMap.get(key).longValue());
							searchList.add(stockSearch);
						}
					}
					logger.info("京东商品库存的查询条件为:{}",JSON.toJSONString(searchList));
					Integer provinceId = parseAddressJson.getProvinceId();
					Integer cityId = parseAddressJson.getCityId();
					Integer countyId = parseAddressJson.getCountyId();
					String area = provinceId + "_" + cityId + "_" + countyId;
					String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), area);
					List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
					if(jdProductStockArr!=null && jdProductStockArr.size()>0) {
						for(JSONObject jdProductStock : jdProductStockArr) {
							//0:无库存,1:有库存   2:无地址  3:不可售
							int jdStockStatus=0;
							if(!EmptyUtil.isEmpty(jdProductStock)){
								Long nowSkuId = jdProductStock.getLong("skuId");
								String stockStateId = jdProductStock.getString("stockStateId");
								Integer remainNum = (jdProductStock.containsKey("remainNum")&&jdProductStock.getInteger("remainNum")>0)?jdProductStock.getInteger("remainNum"):0;
								if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
									jdStockStatus=1;
								}else{
									jdStockStatus=0;
								}
								if(jdStockStatus==1 && skuNumMap.containsKey(nowSkuId.longValue()+"")) {
									JdProductDTO product = productMap.get(nowSkuId.longValue()+"");
									product.setStock((remainNum>skuNumMap.get(nowSkuId.longValue()+""))?remainNum:skuNumMap.get(nowSkuId.longValue()+""));
								}
							}
						}
					}
					logger.info("京东商品{}准备开始库存的结果{}",stock,jdProductStockString);
				}

				//3.价格
				if(price!=null && price) {
				    List<Long> res = Arrays.asList( skuNumMap.keySet().toArray( new String[0])).
				    		  stream().map(Long::parseLong).collect(Collectors.toList());
				    List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(res, enterpriseId);

					Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
				    if(jdPriceCustomizes!=null && jdPriceCustomizes.size()>0) {
						jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getSpuIdStr, one -> one));;
					}
					JSONArray priceResultObj = jdUtils.getSellPrice(token, res);
					if(priceResultObj!=null && priceResultObj.size()>0) {
						for(int index=0;index<priceResultObj.size();index++) {
							JSONObject ob = (JSONObject) priceResultObj.get(index);
							if(ob!=null && ob.containsKey("skuId")&& skuNumMap.containsKey(ob.getLong("skuId").longValue()+"")) {
								Long nowSkuId = ob.getLong("skuId");
								JdProductDTO product = productMap.get(nowSkuId.longValue()+"");
								BigDecimal realPrice = ob.getBigDecimal("price");
								BigDecimal realJdPrice = ob.getBigDecimal("jdPrice");
								List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
								if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
									JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
									if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
										realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
									}
								}

								BigDecimal finalPrice = realPrice;

								JdPriceDTO customPrice = null;
								if(jdPriceCustomizeMap!=null && jdPriceCustomizeMap.size()>0) {
									customPrice = jdPriceCustomizeMap.get(nowSkuId.longValue()+"");
								}
								//优先级1的是代理商自定义价格
								if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
									if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
										finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
									}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
										finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
									}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
										finalPrice = new BigDecimal(customPrice.getPriceValue());
									}
								}else {

									if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
										//优先级2 代理商自定义京东配置
										JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
										long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
										BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
										finalPrice = realPrice.add(realPrice.multiply(vv));
									}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
										//优先级3 平台给代理商定义的配置
										JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
										long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
										BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
										finalPrice = realPrice.add(realPrice.multiply(vv));
									}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
										//优先级4 平台缺省配置
									}



								}
								//京东价格逻辑预设置
								finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

								if(finalPrice.compareTo(realJdPrice)>0) {
									finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
								}
								product.setPrice(finalPrice);
								if(finalPrice.compareTo(realJdPrice)<0) {
									//finalPrice<realJdPrice
									product.setMarketPrice(realJdPrice);
									//dto.setProfit(realJdPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
								}else {
									//finalPrice超过参考价，以finalPrice为准
									product.setMarketPrice(finalPrice);
									//dto.setProfit(finalPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
								}
							}
						}
					}
				}
			}
		}

		//4.校验是否在可售区域
		if(!SpringContextTool.isPrd()) {
			logger.info("getJdProductStatStockAndPrice cost(ms):"+(System.currentTimeMillis()-start)+"  return="+ JsonUtils.objectToJson(productMap));
		}
		return productMap;

	}

	public List<JdProductDTO> fetchJdProductSimples(List<JdProductDTO> data) {
		if(data==null || data.size()==0) {
			return null;
		}
		long start = System.currentTimeMillis();
		HashMap<String,List<JSONObject>> productMap = new HashMap<String,List<JSONObject>>();
		//如果数据里有名字和图片，就认为不需要获取详情，只需要获取价格和状态。使用入参的作为缓存数据即可
		HashMap<String,JdProductDTO> productCacheMap = new HashMap<String,JdProductDTO>();
		for(JdProductDTO one : data) {
			Long skuId = one.getId();
			if(skuId==null) {
				continue;
			}
			if(StringUtils.isNotEmpty(one.getName())&&StringUtils.isNotEmpty(one.getImagePath())) {
				productCacheMap.put(skuId.longValue()+"", one);
			}else {
				List<JSONObject> list = productMap.getOrDefault(skuId,new ArrayList<JSONObject>());
				JSONObject resultObj = jdUtils.getDetail(cache, skuId);
				list.add(resultObj);
				if (!productMap.containsKey(skuId)){
					productMap.put(skuId.longValue()+"", list);
				}
			}


		}
		logger.info("获取京东商品明细耗时："+(System.currentTimeMillis()-start));
		start = System.currentTimeMillis();
		List<JdProductDTO> rslt = new ArrayList<JdProductDTO>();

		List<Long> res = new ArrayList<>();
		if(!productMap.isEmpty()) {
			for(String skuIdStr : productMap.keySet()) {
				res.add(Long.valueOf(skuIdStr));
			}
		}
		for(Entry<String,JdProductDTO> entry : productCacheMap.entrySet()) {
			if(!productMap.containsKey(entry.getKey())) {
				res.add(Long.valueOf(entry.getKey()));
			}
		}
		//String token = jdUtils.getAccessToken(cache);
		String token = jdUtils2.getAccessToken(cache);
//		JSONArray priceResultObj = jdUtils.getSellPrice(token, res);
		Map<Long,JSONObject> priceMap = jdProductFacade.getJdSellPrice(token,res);

		logger.info("获取京东商品价格耗时："+(System.currentTimeMillis()-start));
		start = System.currentTimeMillis();
		Map<Long,Integer> jdStatusMap = jdProductFacade.getJdSkuState(res);
		logger.info("获取京东商品状态耗时："+(System.currentTimeMillis()-start));
		start = System.currentTimeMillis();
//		if(priceResultObj!=null && priceResultObj.size()>0) {
//			for(int index=0;index<priceResultObj.size();index++) {
//				JSONObject ob = (JSONObject) priceResultObj.get(index);
//				Long priceSkuId = ob.getLong("skuId");
//				priceMap.put(priceSkuId, ob);
//			}
//		}
		List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
		if(productCacheMap!=null && productCacheMap.size()>0) {
			List<Long> priceIds=new ArrayList<>();
			productCacheMap.keySet().forEach(id->priceIds.add(Long.valueOf(id)));
			Map<String, JdPriceDTO> jdPriceCustomizeMap = queryJdPriceCustomizes(priceIds);
			for(Entry<String,JdProductDTO> oneCache : productCacheMap.entrySet()) {
				Long skuId = Long.valueOf(oneCache.getKey());
				JSONObject ob = priceMap.get(skuId);
				//获取实时价格
				if(ob!=null) {
					setPrice(jdPriceCustomizeMap,ob, oneCache.getValue(),jdEnterpriseConfigs);
				}else {
					oneCache.getValue().setPrice(new BigDecimal(-1));
					oneCache.getValue().setMarketPrice(new BigDecimal(-1));
					oneCache.getValue().setState(0);
				}

				rslt.add(oneCache.getValue());
			}
		}


		if(productMap!=null && productMap.size()>0) {
			/*String skuInfo = jdUtils.getSimilarSku(cache, skuId);
			String skuImg = jdUtils.getSkuImage(cache, skuId);*/
			List<Long> priceIds=new ArrayList<>();
			productMap.keySet().forEach(id->priceIds.add(Long.valueOf(id)));
			Map<String, JdPriceDTO> jdPriceCustomizeMap = queryJdPriceCustomizes(priceIds);

			for(Entry<String,List<JSONObject>> oneSkus : productMap.entrySet()) {
				Long skuId = Long.valueOf(oneSkus.getKey());
				for(JSONObject resultObj : oneSkus.getValue()) {

					JdProductDTO jdProductDTO = new JdProductDTO();
					JSONObject ob = priceMap.get(skuId);
					/*String category = resultObj.getString("category");
					jdProductDTO.setCategoryId(Long.parseLong(category.substring(category.lastIndexOf(";") + 1)));*/
					jdProductDTO.setId(skuId);
					//jdProductDTO.setImagePath(skuImg);
					String imagePath = Objects.nonNull(resultObj) && resultObj.containsKey("imagePath") ? resultObj.getString("imagePath") :null;
					if(imagePath!=null) {
						imagePath = "http://img13.360buyimg.com/n1/"+imagePath;
					}
					jdProductDTO.setImagePath(imagePath);
					String introduction = Objects.nonNull(resultObj) && resultObj.containsKey("introduction") ?resultObj.getString("introduction"):null;
					if(introduction==null) {
						introduction = Objects.nonNull(resultObj) && resultObj.containsKey("nappintroduction") ?resultObj.getString("nappintroduction"):null;
					}
					if(introduction==null) {
						introduction = Objects.nonNull(resultObj) && resultObj.containsKey("wxintroduction") ?resultObj.getString("wxintroduction"):null;
					}
					jdProductDTO.setIntroduction(introduction);
					logger.info("字符串:" + jdProductDTO.getIntroduction());
					if(Objects.nonNull(resultObj) && resultObj.containsKey("isToReturn")){
						jdProductDTO.setIs7ToReturn(resultObj.getInteger("isToReturn"));
					}
					if(Objects.nonNull(resultObj) && resultObj.containsKey("isJDLogistics")){
						jdProductDTO.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
					}
					if(Objects.nonNull(resultObj) && resultObj.containsKey("isSelf")){
						jdProductDTO.setIsSelf(resultObj.getInteger("isSelf"));
					}
					if(Objects.nonNull(resultObj) && resultObj.containsKey("name")){
						jdProductDTO.setName(resultObj.getString("name"));
					}
					if(Objects.nonNull(resultObj) && resultObj.containsKey("noReasonToReturn")){
						jdProductDTO.setNoReasonToReturn(resultObj.getInteger("noReasonToReturn"));
					}
					if(Objects.nonNull(resultObj) && resultObj.containsKey("pName")){
						jdProductDTO.setSpuName(resultObj.getString("pName"));
					}
					if(Objects.nonNull(resultObj) && resultObj.containsKey("thwa")){
						jdProductDTO.setThwa(resultObj.getInteger("thwa"));
					}

//					jdProductDTO.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
//					jdProductDTO.setIsSelf(resultObj.getInteger("isSelf"));
//					jdProductDTO.setLowestBuy(resultObj.getString(""));

/*					jdProductDTO.setName(resultObj.getString("name"));
					jdProductDTO.setNoReasonToReturn(resultObj.getInteger("noReasonToReturn"));*/
//					jdProductDTO.setSaleState(resultObj.getString(""));
//					jdProductDTO.setSpuId(resultObj.getString(""));
//					jdProductDTO.setSpuName(resultObj.getString("pName"));
					jdProductDTO.setUpdateTime(DateUtils.getTodayDate());
					jdProductDTO.setState(0);
					if(Objects.nonNull(jdStatusMap) && jdStatusMap.containsKey(skuId)) {
						jdProductDTO.setState(jdStatusMap.get(skuId));
					}
					jdProductDTO.setSyncStatus(1);
//					jdProductDTO.setThwa(resultObj.getInteger("thwa"));

					//获取实时价格
					if(ob!=null) {
						setPrice(jdPriceCustomizeMap,ob, jdProductDTO,jdEnterpriseConfigs);
					}else {
						jdProductDTO.setPrice(new BigDecimal(-1));
						jdProductDTO.setMarketPrice(new BigDecimal(-1));
						jdProductDTO.setState(0);
					}

					rslt.add(jdProductDTO);


				}
			}
			//BigDecimal price = ob == null ? null : ob.getBigDecimal("price");
			//BigDecimal marketPrice = ob == null ? null : ob.getBigDecimal("marketPrice");

		}
		return rslt;




	}
	private void setPrice(Map<String, JdPriceDTO> jdPriceCustomizeMap,JSONObject ob,JdProductDTO jdProductDTO,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		Long skuId = jdProductDTO.getId();
		BigDecimal realPrice = ob.getBigDecimal("price");
		BigDecimal realJdPrice = ob.getBigDecimal("jdPrice");
		if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
			JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
			if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
				realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
			}
		}

		BigDecimal finalPrice = realPrice;

		JdPriceDTO customPrice = null;
		if(jdPriceCustomizeMap!=null && jdPriceCustomizeMap.size()>0) {
			customPrice = jdPriceCustomizeMap.get(skuId.longValue()+"");
		}
		//优先级1的是代理商自定义价格
		if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
			if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
			}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
			}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
				finalPrice = new BigDecimal(customPrice.getPriceValue());
			}
		}else {

			if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				//优先级2 代理商自定义京东配置
				JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
				//优先级3 平台给代理商定义的配置
				JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
				//优先级4 平台缺省配置
			}



		}

		//京东价格逻辑预设置
		finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

		if(finalPrice.compareTo(realJdPrice)>0) {
			finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
		}

		jdProductDTO.setPrice(realPrice);
		jdProductDTO.setSalePrice(finalPrice);
		if(finalPrice.compareTo(realJdPrice)<0) {
			//finalPrice<realJdPrice
			jdProductDTO.setMarketPrice(realJdPrice);
			//dto.setProfit(realJdPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
		}else {
			//finalPrice超过参考价，以finalPrice为准
			jdProductDTO.setMarketPrice(finalPrice);
			//dto.setProfit(finalPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
		}
		jdProductDTO.setCustomProfit(finalPrice.subtract(realPrice).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
	}



	private Map<String, JdPriceDTO> queryJdPriceCustomizes(List<Long> skuIds){
		Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
		List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(skuIds, RuntimeContext.cacheUser().getEnterpriseId());
		if(EmptyUtil.isNotEmpty(jdPriceCustomizes)) {
			jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getSpuIdStr, one -> one));;
		}
		return jdPriceCustomizeMap;
	}

	public JdProductDTO fetchJdProductSimple(JdProductDTO data) {
		Long skuId = data.getId();
		JSONObject resultObj = jdUtils.getDetail(cache, skuId);
		if(resultObj!=null ) {
			//String skuImg = jdUtils.getSkuImage(cache, skuId);
			List<Long> res = new ArrayList<>();
			res.add(skuId);
			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			JSONArray priceResultObj = jdUtils.getSellPrice(token, res);
			JSONObject ob = (JSONObject) priceResultObj.get(0);
			//BigDecimal price = ob == null ? null : ob.getBigDecimal("price");
			//BigDecimal marketPrice = ob == null ? null : ob.getBigDecimal("marketPrice");
			JdProductDTO jdProductDTO = new JdProductDTO();

			/*String category = resultObj.getString("category");
			jdProductDTO.setCategoryId(Long.parseLong(category.substring(category.lastIndexOf(";") + 1)));*/
			jdProductDTO.setId(skuId);
			//jdProductDTO.setImagePath(skuImg);
			String imagePath = resultObj.getString("imagePath");
			if(imagePath!=null) {
				imagePath = "http://img13.360buyimg.com/n1/"+imagePath;
			}
			jdProductDTO.setImagePath(imagePath);
			String introduction = resultObj.getString("introduction");
			if(introduction==null) {
				introduction = resultObj.getString("nappintroduction");
			}
			if(introduction==null) {
				introduction = resultObj.getString("wxintroduction");
			}
			jdProductDTO.setIntroduction(introduction);
			logger.info("字符串:" + jdProductDTO.getIntroduction());
			jdProductDTO.setIs7ToReturn(resultObj.getInteger("isToReturn"));
			jdProductDTO.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
			jdProductDTO.setIsSelf(resultObj.getInteger("isSelf"));
//			jdProductDTO.setLowestBuy(resultObj.getString(""));

			jdProductDTO.setName(resultObj.getString("name"));
			jdProductDTO.setNoReasonToReturn(resultObj.getInteger("noReasonToReturn"));
//			jdProductDTO.setSaleState(resultObj.getString(""));
//			jdProductDTO.setSpuId(resultObj.getString(""));
			jdProductDTO.setSpuName(resultObj.getString("pName"));
			jdProductDTO.setState(jdUtils.getSkuState(cache, skuId));
			jdProductDTO.setSyncStatus(1);
			jdProductDTO.setThwa(resultObj.getInteger("thwa"));
			List<Long> spuIds = new ArrayList<Long>();
			spuIds.add(skuId);
			Map<String, JdPriceDTO> jdPriceCustomizeMap = new HashMap<String, JdPriceDTO>();
			List<JdPriceDTO> jdPriceCustomizes = jdPrice.findAllPriceOfEnterpriseAndSpus(spuIds, RuntimeContext.cacheUser().getEnterpriseId());

			if(jdPriceCustomizes!=null && jdPriceCustomizes.size()>0) {
				jdPriceCustomizeMap = jdPriceCustomizes.stream().collect(Collectors.toMap(JdPriceDTO::getSpuIdStr, one -> one));;
			}
			//获取实时价格
			BigDecimal realPrice = ob.getBigDecimal("price");
			BigDecimal realJdPrice = ob.getBigDecimal("jdPrice");
			List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findAllConfigOfEnterprise(RuntimeContext.cacheUser().getEnterpriseId());
			if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
				JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
					realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
				}
			}

			BigDecimal finalPrice = realPrice;

			JdPriceDTO customPrice = null;
			if(jdPriceCustomizeMap!=null && jdPriceCustomizeMap.size()>0) {
				customPrice = jdPriceCustomizeMap.get(skuId.longValue()+"");
			}
			//优先级1的是代理商自定义价格
			if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
				if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
					finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
				}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
					finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
				}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
					finalPrice = new BigDecimal(customPrice.getPriceValue());
				}
			}else {

				if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
					//优先级2 代理商自定义京东配置
					JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
					long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
					BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					finalPrice = realPrice.add(realPrice.multiply(vv));
				}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
					//优先级3 平台给代理商定义的配置
					JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
					long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
					BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					finalPrice = realPrice.add(realPrice.multiply(vv));
				}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
					//优先级4 平台缺省配置
				}



			}
			//京东价格逻辑预设置
			finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

			if(finalPrice.compareTo(realJdPrice)>0) {
				finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
			}

			jdProductDTO.setPrice(finalPrice);
			if(finalPrice.compareTo(realJdPrice)<0) {
				//finalPrice<realJdPrice
				jdProductDTO.setMarketPrice(realJdPrice);

				//update 20250509 因为详情中需要判断毛利是否大于0而放开
				jdProductDTO.setProfit(realJdPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
			}else {
				//finalPrice超过参考价，以finalPrice为准
				jdProductDTO.setMarketPrice(finalPrice);
				//update 20250509 因为详情中需要判断毛利是否大于0而放开
				jdProductDTO.setProfit(finalPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
			}





			return jdProductDTO;
		}
		return null;



	}
	public JsonResult<PageResult<JdProductDTO>> searchPlatform(JDProductSearchDTO search) {
		// TODO Auto-generated method stub
		//1.过滤出符合条件productActive+inStore 的结果，rsltMap<skuId,JdProductDTO>
		List<JdProductDTO> rslt = new ArrayList<JdProductDTO>();
		if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
			return JsonResult.success(null);
		}

		List<JdEnterpriseConfigDTO> jdEnterpriseConfigs = jdConfig.findConfigOfPlatform();

		//设置缺省排序方式
		if(StringUtils.isEmpty(search.getSortType())) {
			search.setSortType("sort_totalsales15_desc");
		}

		if(StringUtils.isEmpty(search.getPriceMax())) {
			String max = JdPriceTools.getConfigPriceMax(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(max)) {
				search.setPriceMax(Integer.valueOf(max));
			}
		}
		if(StringUtils.isEmpty(search.getPriceMin())) {
			String min = JdPriceTools.getConfigPriceMin(jdEnterpriseConfigs);
			if(StringUtils.isNotBlank(min)) {
				search.setPriceMin(Integer.valueOf(min));
			}
		}

		PageResult<JdSearchHitResultDTO> hit = search(search, true, true);
		if(hit!=null && hit.getList()!=null && hit.getList().size()>0) {
			Map<String,JdSearchHitResultDTO> hitMap = hit.getList().stream().collect(Collectors.toMap(JdSearchHitResultDTO::getWareId, one -> one));
			Map<String, String> param = new HashMap<String, String>();
			Map<String,JdPriceResultDTO> priceMap = null;
			//String accessToken = jdUtils.getAccessToken(cache);
			String accessToken = jdUtils2.getAccessToken(cache);
			param.put("token", accessToken);
			param.put("sku", String.join(",", hitMap.keySet())	);
			String resultPrice = HttpClientUtil.doPost(jdUtils.getSellPriceUrl, param, "UTF-8", 30000);
			if(resultPrice!=null && resultPrice.length()>0) {
				JSONObject priceRslt = JSON.parseObject(resultPrice);
				if(priceRslt.containsKey("success")&&priceRslt.getBooleanValue("success")) {
					if(priceRslt.containsKey("result")) {
						List<JdPriceResultDTO> priceResults = priceRslt.getJSONArray("result").toJavaList(JdPriceResultDTO.class);
						priceMap = priceResults.stream().collect(Collectors.toMap(JdPriceResultDTO::getSkuIdStr, one -> one));
					}
				}
			}


			for (Map.Entry<String, JdSearchHitResultDTO> entry : hitMap.entrySet()) {
				JdProductDTO productDto = JoinJdSearchHitResultAndPrice(entry.getValue(), priceMap.get(entry.getKey()),null,jdEnterpriseConfigs);
				if(productDto!=null) {
					rslt.add(productDto);
				}
			}
		}
		//2.处理价格
		//2.1查询价格
		PageResult<JdProductDTO> data = new PageResult<JdProductDTO>();
		//刷详情
		if(rslt!=null && rslt.size()>0) {
			for(JdProductDTO one : rslt) {
				//fetchJdProductDetail(one);
			}
		}
		data.setList(rslt);
		data.setPageNo(hit.getPageNo());
		data.setPageSize(hit.getPageSize());
		data.setTotalSize(hit.getTotalSize());
		return JsonResult.success(data);
	}

	/**
	 * @param hit   京东的查询结果
	 * @param price 京东的实时价格
	 * @param customPrice  代理商自定义的京东价格
	 * @param jdEnterpriseConfigs 代理商京东定价策略
	 * @return
	 */
	private JdProductEnterpriseVO JoinEnterpriseJdSearchHitResultAndPrice(JdSearchHitResultDTO hit,JdPriceResultDTO price,JdPriceDTO customPrice,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(price == null || hit ==null) {
			return null;
		}
		//获取实时价格
		BigDecimal realPrice = price.getPriceBigDecimal();
		BigDecimal realJdPrice = price.getJdPriceBigDecimal();
		if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
			JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
			if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
				realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
			}
		}

		BigDecimal finalPrice = realPrice;
		//优先级1的是代理商自定义价格
		if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
			if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
			}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
			}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
				finalPrice = new BigDecimal(customPrice.getPriceValue());
			}
		}else {

			if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				//优先级2 代理商自定义京东配置
				JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
				//优先级3 平台给代理商定义的配置
				JdEnterpriseConfigDTO config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
				//优先级4 平台缺省配置
			}



		}

		//京东价格逻辑预设置
		finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

		if(finalPrice.compareTo(realJdPrice)>0) {
			finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
		}

		JdProductEnterpriseVO dto = JdProductConverter.toEnterpriseVO(hit);
		dto.setPrice(realPrice);
		if(customPrice!=null) {
			dto.setPriceType(customPrice.getPriceType());
			dto.setPriceValue(customPrice.getPriceValue());
			dto.setPriceAudit(customPrice.getAudit());
		}

		dto.setSalePrice(finalPrice);
		dto.setMarketPrice(realJdPrice);
		dto.setProfit(finalPrice.subtract(realPrice).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		/*
		if(finalPrice.compareTo(realJdPrice)==-1) {
			//finalPrice<realJdPrice
			dto.setMarketPrice(realJdPrice);
			dto.setProfit(finalPrice.subtract(realPrice).divide(realPrice,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}else {
			//finalPrice超过参考价，以finalPrice为准
			dto.setMarketPrice(finalPrice);
			dto.setProfit(finalPrice.subtract(realPrice).divide(realPrice,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}*/
		return dto;
	}
	/**
	 * @param hit   京东的查询结果
	 * @param price 京东的实时价格
	 * @param customPrice  代理商自定义的京东价格
	 * @param jdEnterpriseConfigs 代理商京东定价策略
	 * @return
	 */
	private JdProductDTO JoinJdSearchHitResultAndPrice(JdSearchHitResultDTO hit,JdPriceResultDTO price,JdPriceDTO customPrice,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(price == null || hit ==null) {
			return null;
		}
		//获取实时价格
		BigDecimal realPrice = price.getPriceBigDecimal();
		BigDecimal realJdPrice = price.getJdPriceBigDecimal();
		if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
			JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
			if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
				realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
			}
		}

		BigDecimal finalPrice = realPrice;
		JdEnterpriseConfigDTO config=null;
		//优先级1的是代理商自定义价格
		if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
			if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
			}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
			}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
				finalPrice = new BigDecimal(customPrice.getPriceValue());
			}
		}else {
			if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				//优先级2 代理商自定义京东配置
				config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
				//优先级3 平台给代理商定义的配置
				config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
				//优先级4 平台缺省配置
			}



		}
		//京东价格逻辑预设置
		finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

		if(finalPrice.compareTo(realJdPrice)>0) {
			finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
		}
		JdProductDTO dto = JdProductConverter.toDTO(hit);
		dto.setPrice(realPrice);
		dto.setSalePrice(finalPrice);
		if(finalPrice.compareTo(realJdPrice)<0) {
			//finalPrice<realJdPrice
			dto.setMarketPrice(realJdPrice);
			dto.setProfit(realJdPrice.subtract(realPrice).divide(realJdPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
		}else {
			//finalPrice超过参考价，以finalPrice为准
			dto.setMarketPrice(finalPrice);
			dto.setProfit(finalPrice.subtract(realPrice).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
			if (Objects.nonNull(config)){
				if (Objects.nonNull(config.getGrossMarginMin()) && config.getGrossMarginMin()>0){
					if (dto.getProfit()<config.getGrossMarginMin()){
						logger.info("京东商品因毛利Profit={}小于配置的最小毛利GrossMarginMin={}被抛弃，价格:{}",dto.getProfit(),config.getGrossMarginMin(),JSON.toJSONString(price));
						return null;
					}
				}
			}
		}
		return dto;
	}

	private Map<String,Object> JdSellPrice(String skuId,JdPriceResultDTO price,JdPriceDTO customPrice,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
		if(price == null || skuId ==null) {
			return null;
		}
		//获取实时价格
		BigDecimal realPrice = price.getPriceBigDecimal();
		BigDecimal realJdPrice = price.getJdPriceBigDecimal();
		if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
			JdEnterpriseConfigDTO defaultConfig = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
			if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
				realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
			}
		}

		BigDecimal finalPrice = realPrice;
		JdEnterpriseConfigDTO config=null;
		//优先级1的是代理商自定义价格
		if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
			if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
			}else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
				finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
			}else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
				finalPrice = new BigDecimal(customPrice.getPriceValue());
			}
		}else {

			if(JdPriceTools.hasEnterpriseSellConfig(jdEnterpriseConfigs)) {
				//优先级2 代理商自定义京东配置
				config = JdPriceTools.getEnterpriseSellConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasEnterpriseDefaultConfig(jdEnterpriseConfigs)){
				//优先级3 平台给代理商定义的配置
				config = JdPriceTools.getEnterpriseDefaultConfig(jdEnterpriseConfigs);
				long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
				BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
				finalPrice = realPrice.add(realPrice.multiply(vv));
			}else if(JdPriceTools.hasPlatformConfig(jdEnterpriseConfigs)){
				//优先级4 平台缺省配置
			}



			//京东价格逻辑预设置
		}

		boolean profitOk=true;
		//计算后的价格大于京东市场价
		int profit=0;
		if(finalPrice.compareTo(realJdPrice)>0) {
			//
			finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());

			if(finalPrice.compareTo(realJdPrice)<0){
				profit = realJdPrice.subtract(realPrice).divide(realJdPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
			}else{
				profit=finalPrice.subtract(realPrice).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
			}
			if (Objects.nonNull(config)){
				if (Objects.nonNull(config.getGrossMarginMin()) && config.getGrossMarginMin()>0){
					if (profit<config.getGrossMarginMin()){
						profitOk=false;
					}
					logger.info("skuId:{},realJdPrice:{}finalPrice:{},realPrice:{}毛利:{},最小毛利:{},profitOk:{}",skuId,realJdPrice,finalPrice,realPrice,profit,config.getGrossMarginMin(),profitOk);
				}else{
					if(profit <15){
						profitOk=false;
					}
					logger.info("skuId:{}未配置最小毛利或最小毛利小于0情况profitOk:{}",skuId,profitOk);
				}
			}else{
				if(profit <15){
					profitOk=false;
				}
				logger.info("config为空情况skuId:{},finalPrice:{},realPrice:{}毛利:{},最小毛利假设为0,profitOk:{}",skuId,finalPrice,realPrice,profit,profitOk);
			}
		}else {
			finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
		}
		Map<String,Object> sellPriceRst=new HashMap<>();
		sellPriceRst.put("finalPrice",finalPrice);
		sellPriceRst.put("profitOk",profitOk);
		logger.info("最终skuId:{},finalPrice:{},realJdPrice:{},realPrice:{},profitOk:{}",skuId,finalPrice,realJdPrice,realPrice,profitOk);
		return sellPriceRst;
	}
	/**
	 * 过滤出符合条件productActive+inStore 的结果
	 * @param search
	 * @param productActive
	 * 			null，不做处理
	 * 			true，产品有效
	 * @param inStore
	 * 			null,不做处理
	 * 			true,产品在架
	 * @return
	 */
	private PageResult<JdSearchHitResultDTO> search(JDProductSearchDTO search,Boolean productActive,Boolean inStore) {
		//String accessToken = jdUtils.getAccessToken(cache);
		String accessToken = jdUtils2.getAccessToken(cache);
		List<JdUnsearchCategoryDTO> jdUnsearchCategorys = jdUnsearchCategoryDAO.findAll();
		Map<String, String> searchParam=search.param(accessToken);
		logger.info("searchParam："+searchParam.toString()+";productSearchUrl="+jdUtils.productSearchUrl);
		String resultProduct = HttpClientUtil.doPost(jdUtils.productSearchUrl, searchParam, "UTF-8", 30000);
		//这里根据所有的代理商配置修改价格，以0.1元为单位四舍五入
		PageResult<JdSearchHitResultDTO> rslt = new PageResult<JdSearchHitResultDTO>();
		List<JdSearchHitResultDTO> rsltList = new ArrayList<JdSearchHitResultDTO>();
		//1.过滤出符合条件productActive+inStore 的结果，rsltMap<skuId,JdProductDTO>
		if(resultProduct!=null && resultProduct.length()>0) {
			JSONObject productRslt = JSON.parseObject(resultProduct);
			logger.info("productSearchUrl："+productRslt.toJSONString());
			if(productRslt.containsKey("success")&&productRslt.getBooleanValue("success")) {
				if(productRslt.containsKey("result")) {
					JSONObject productRsltJson = productRslt.getJSONObject("result");
					rslt.setPageNo(productRsltJson.getIntValue("pageIndex"));
					rslt.setPageSize(productRsltJson.getIntValue("pageSize"));
					rslt.setTotalSize(productRsltJson.getIntValue("resultCount"));
					if(productRsltJson!=null && productRsltJson.containsKey("hitResult")&&productRsltJson.get("hitResult")!=null) {
						List<JdSearchHitResultDTO> hitResults = productRsltJson.getJSONArray("hitResult").toJavaList(JdSearchHitResultDTO.class);
						if(hitResults!=null && hitResults.size()>0) {
							List<Long> skuIds=hitResults.stream().map(JdSearchHitResultDTO::getWareIdLong).collect(Collectors.toList());
							Map<Long,Integer> jdStatusMap = jdUtils.getSkuState(cache,skuIds);
							for(int hitIndex = 0;hitIndex<hitResults.size();hitIndex++) {
								JdSearchHitResultDTO one = hitResults.get(hitIndex);
								if(one!=null && one.hasProductId()) {
									if(productActive!=null && (!one.productActiveCompare(productActive))) {
										//productActive不为空就 需要判断产品有效性，如果搜索结果状态与需要过滤的状态不一致，就进行接下来的处理
										continue;
									}
									if (Objects.equals(jdStatusMap.get(one.getWareIdLong()),0)){
										continue;
									}
									if(inStore!=null && (!one.stateCompare(inStore))) {
										//逻辑同上 productActive
										continue;
									}
									//状态过滤完毕，
									boolean match = false;
									for(JdUnsearchCategoryDTO unsearchC : jdUnsearchCategorys) {
										String unsearchCategory = unsearchC.getCategory();
										String cats = one.getCid1()+"-"+one.getCid2()+"-"+one.getCatId();
										if(unsearchCategory.equalsIgnoreCase(cats)) {
											System.out.println("unsearch cat:"+cats);
											match = true;
										}
									}
									if(!match) {
										rsltList.add(one);
									}
								}
							}
						}
						rslt.setList(rsltList);
					}
				}
			}
		}
		return rslt;
	}
	@Override
	public void deleteJdMessage() {
		String type = "2,4,6,16,48";
		//String accessToken = jdUtils.getAccessToken(cache);
		String accessToken = jdUtils2.getAccessToken(cache);
		JSONArray jdProductChange = jdUtils.getJdProductChange(accessToken, type);
		if(EmptyUtil.isNotEmpty(jdProductChange)){
			List<String> list = new ArrayList<>();
			for(Object obj:jdProductChange) {
				JSONObject ob = (JSONObject) obj;
				String messId = ob.getString("id");
				list.add(messId);
			}
			if(EmptyUtil.isNotEmpty(list)){
				int page = ((list.size()-1)/ 100)+1;
				for(int i=0;i<page;i++){
					String ids = "";

					if(i==page-1){
						for (int j = i * 100; j < list.size()%100; j++) {
							if (j == (list.size() - 1)) {
								ids = ids + list.get(j);
							} else {
								ids = ids + list.get(j) + ",";
							}
						}
					}else {
						for (int j = i * 100; j < (i + 1) * 100; j++) {
							if (j == (list.size() - 1)) {
								ids = ids + list.get(j);
							} else {
								ids = ids + list.get(j) + ",";
							}
						}
					}
					String message = jdUtils.deleteJdMessage(accessToken, ids);
					logger.info("删除消息结果:"+message);
				}
			}
		}



	}

	@Override
	public JsonResult getJdCategoryList(Integer catClass, Long parentId) {
		return jdProductFacade.getJdCategoryList(catClass,parentId);
	}

	@Override
	public void deleteRedisJd() {
		Set<String> keys = cache.getKeys("JD*");
		if(EmptyUtil.isNotEmpty(keys)){
			for(String key:keys){
				cache.del(key);

			}
		}

	}

	@Override
	public void updateJdProductByJdSkuId(List<Long> skuIdList) {
		initOldId();
		//String token = jdUtils.getAccessToken(cache);
		String token = jdUtils2.getAccessToken(cache);
		List<JdProductDTO> list = new ArrayList<>();
		for(Long skuId:skuIdList){
			JdProductDTO productDTO = new JdProductDTO();
			JSONObject detail = jdUtils.getDetail(cache, skuId);
			String skuInfo = jdUtils.getSimilarSku(cache,skuId);
			String name = detail.getString("name");
			productDTO.setName(name);
			String lowBuy=detail.getString("LowestBuy");
			if(EmptyUtil.isEmpty(lowBuy)){
				productDTO.setLowestBuy(Integer.valueOf(1));
			}else{
				productDTO.setLowestBuy(Integer.valueOf(lowBuy));
			}
			String spuId = detail.getString("spuId");
			productDTO.setSpuId(Long.valueOf(spuId));
			Integer isSelf = detail.getInteger("isSelf");
			productDTO.setIsSelf(isSelf);

			String category = detail.getString("category");
			String[] split = category.split(";");
			Long catId = Long.valueOf(split[split.length - 1]);
			//查询类目
			//查询本地内容
			JdCategoryDTO jdCategoryDTO = null;
			CategoryDTO categoryDTO = null;
			CategoryTreeNodeDTO catTreeNode2 = null;
			jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(catId);
			if (EmptyUtil.isNotEmpty(jdCategoryDTO)) {
				categoryDTO = jdProductFacade.findCategoryById(jdCategoryDTO.getInnerCategoryId());
				CategoryTreeNodeDTO catTreeNode = new CategoryTreeNodeDTO();
				catTreeNode.setCategoryId(jdCategoryDTO.getInnerCategoryId());
				List<CategoryTreeNodeDTO> catTreeNodeList = categoryTreeNodeFacade.findAll(catTreeNode);
				if (EmptyUtil.isNotEmpty(catTreeNodeList)) {
					catTreeNode2 = catTreeNodeList.get(0);
				}
			} else {
				logger.info("京东分类不存在,catId=" + catId);
				continue;
			}
			String categoryName = getCategoryName(catId);
			productDTO.setInnerCategoryId(jdCategoryDTO.getInnerCategoryId());
			productDTO.setInnerCategorySerialNumber(categoryDTO.getSerialNumber());
			productDTO.setInnerCategoryTreeNode(catTreeNode2.getId());
			productDTO.setInnerCategoryName(categoryName);
			productDTO.setIsDetail(0);
			productDTO.setCategoryId(catId);
			productDTO.setId(skuId);
			productDTO.setSkuJson(skuInfo);



			String state = detail.getString("state");
			productDTO.setState(Integer.valueOf(state));
			String imagePath = detail.getString("imagePath");
			productDTO.setImagePath("http://img13.360buyimg.com/n1/s375x375_"+imagePath);
			productDTO.setPicture("http://img13.360buyimg.com/n1/s375x375_"+imagePath);
			list.add(productDTO);
		}
		JSONArray priceResultObj = jdUtils.getSellPrice(token, skuIdList);

		if (EmptyUtil.isNotEmpty(priceResultObj)) {

			for (Object obj : priceResultObj) {
				JSONObject ob = (JSONObject) obj;
				if (EmptyUtil.isNotEmpty(ob)) {
					BigDecimal price = ob.getBigDecimal("price");
					BigDecimal marketPrice = ob.getBigDecimal("jdPrice");
					if (EmptyUtil.isEmpty(price) || EmptyUtil.isEmpty(marketPrice)) {
						logger.info("价格为空");

					}
					Long skuId = ob.getLong("skuId");
					if (EmptyUtil.isNotEmpty(skuId)) {
						for (JdProductDTO jdProductDTO : list) {
							if (jdProductDTO.getId().equals(skuId)) {
								jdProductDTO.setPrice(price);
								jdProductDTO.setMarketPrice(marketPrice);
								jdProductDTO.setSyncStatus(1);
							}
						}
					}
				} else {
					logger.info("京东商品价格为空");
				}
			}
		} else {
			logger.error("查询商品价格失败,sku=" + skuIdList.toString());
		}
		List<JdProductInnerIdDTO> jdProductInnerIdDTOList = jdProductInnerIdFacade.findJdProductInnerIdAllByJdSkuIdList(skuIdList);

		Integer PROFIT = productLimitProfitFacade.findProductLimitProfit();
		List<Long> jdIdArr = new ArrayList<>();
		checkProfit(jdIdArr, null, Integer.valueOf(1),list, PROFIT,Integer.valueOf(1),null,jdProductInnerIdDTOList);



	}



	@Override
	public void updateJdProductByLimitProfit(Integer productLimitProfit) {
		initOldId();
		//将京东所有商品下架隐藏
		jdProductFacade.updateJdProductAllDown();
		//查询所有符合毛利且本地已创建的商品修改状态
		jdProductFacade.updateJdProductStatusByProfit(productLimitProfit);
		//查询所有符合毛利本地未创建的商品进行本地商品创建(因为可能查询数量过大导致服务调用超过6s,因此分页查询)
		Integer count=jdProductFacade.findJdProductCountByProfit(productLimitProfit);
		List<JdProductDTO> jdProductDTOAllList = new ArrayList<>();
		//每500个一次查询
		int pagSize=500;
		int page = ((count - 1) / pagSize)+1;
		List<Long> jdId = new ArrayList<>();
		for(int i=0;i<page;i++){
			List<JdProductDTO> jdProductList=jdProductFacade.findJdProductListByProfit(productLimitProfit,i*pagSize,pagSize);
			if(EmptyUtil.isNotEmpty(jdProductList)){
				jdProductDTOAllList.addAll(jdProductList);
				for(JdProductDTO dto:jdProductList){
					jdId.add(dto.getId());
				}
			}
		}
		//对查询到的京东商品的分类信息进行查询
		List<Long> categoryIdList = new ArrayList<>();
		List<JdProductDTO> selectedJdProduct = new ArrayList<>();
		for(JdProductDTO dto:jdProductDTOAllList){
			if (EmptyUtil.isNotEmpty(dto.getCategoryId())) {
				if(categoryIdList.contains(dto.getCategoryId())){
					//已查询过
					for(JdProductDTO jdProduct:selectedJdProduct){
						if(jdProduct.getCategoryId().equals(dto.getCategoryId())){
							dto.setInnerCategoryId(jdProduct.getInnerCategoryId());
							dto.setInnerCategorySerialNumber(jdProduct.getInnerCategorySerialNumber());
							dto.setInnerCategoryTreeNode(jdProduct.getInnerCategoryTreeNode());
							dto.setInnerCategoryName(jdProduct.getInnerCategoryName());
							break;
						}

					}


				}else{
					//查询本地类目内容
					JdCategoryDTO jdCategoryDTO = null;
					CategoryDTO categoryDTO = null;
					CategoryTreeNodeDTO catTreeNode2 = null;
					//查询类目
					jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(dto.getCategoryId());
					if (EmptyUtil.isNotEmpty(jdCategoryDTO)) {
						categoryDTO = jdProductFacade.findCategoryById(jdCategoryDTO.getInnerCategoryId());
						CategoryTreeNodeDTO catTreeNode = new CategoryTreeNodeDTO();
						catTreeNode.setCategoryId(jdCategoryDTO.getInnerCategoryId());
						List<CategoryTreeNodeDTO> catTreeNodeList = categoryTreeNodeFacade.findAll(catTreeNode);
						if (EmptyUtil.isNotEmpty(catTreeNodeList)) {
							catTreeNode2 = catTreeNodeList.get(0);
						}
					} else {
						logger.info("京东分类不存在,catId=" + dto.getCategoryId());
						throw new BusinessException("京东分类不存在,catId=" + dto.getCategoryId());
					}
					String categoryName = getCategoryName(dto.getCategoryId());
					categoryIdList.add(dto.getCategoryId());
					dto.setInnerCategoryId(jdCategoryDTO.getInnerCategoryId());
					dto.setInnerCategorySerialNumber(categoryDTO.getSerialNumber());
					dto.setInnerCategoryTreeNode(catTreeNode2.getId());
					dto.setInnerCategoryName(categoryName);
					selectedJdProduct.add(dto);
				}

			}
		}
		//更新京东本地商品创建时间
		jdProductFacade.updateProductCreateTime(jdId);
		//创建本地商品
		createNewJdProductLocation(jdProductDTOAllList);
	}


	private void syncJdCategoryByParentId(Long treeId, String jdParentId, Long selfParentId, int catClass) {
		if (catClass <= 2) {
			JSONObject result = jdUtils.getJdCategory(cache, jdParentId, catClass, null, null);
			if (EmptyUtil.isNotEmpty(result)) {
				JSONArray cats = result.getJSONArray("categorys");
				if (EmptyUtil.isNotEmpty(cats)) {
					for (Object cat : cats) {
						JSONObject catObj = (JSONObject) cat;
						String catId = catObj.getString("catId");
						String name = catObj.getString("name");
						Long selfNextParentId = jdProductFacade.createJdCategoryToLocal(treeId, catId, name, jdParentId, selfParentId, catClass);
//				catObj.getString("parentId")
						syncJdCategoryByParentId(treeId, catId, selfNextParentId, catClass + 1);
					}
				}

			}
		}

	}



	private void getSkuByPage(String pageNum, CacheUser user, String ip, String mac, JdPriceConfigDTO jdPriceConfigDTO, HttpServletRequest req) {
		JSONObject resultObj = jdUtils.getSkuByPage(cache, pageNum, 1);
		if (resultObj != null) {
			Integer pageCount = resultObj.getInteger("pageCount");
			JSONArray skuIds = resultObj.getJSONArray("skuIds");
			if (skuIds != null) {
				for (Object skuIdObj : skuIds) {
					Long skuId = Long.parseLong(skuIdObj.toString());
					getSkuDetail(skuId, user, ip, mac, jdPriceConfigDTO, req);
					//break;
				}
			}

		}
	}

	private JdProductDTO getSkuDetail(Long skuId, CacheUser user, String ip, String mac, JdPriceConfigDTO jdPriceConfigDTO, HttpServletRequest req) {
		JSONObject resultObj = jdUtils.getDetail(cache, skuId);
		String skuInfo = jdUtils.getSimilarSku(cache, skuId);
		String skuImg = jdUtils.getSkuImage(cache, skuId);
		List<Long> res = new ArrayList<>();
		res.add(skuId);
		//String token = jdUtils.getAccessToken(cache);
		String token = jdUtils2.getAccessToken(cache);
		JSONArray priceResultObj = jdUtils.getSellPrice(token, res);
		JSONObject ob = (JSONObject) priceResultObj.get(0);
		BigDecimal price = ob == null ? null : ob.getBigDecimal("price");
		BigDecimal marketPrice = ob == null ? null : ob.getBigDecimal("marketPrice");

		JdProductDTO jdProductDTO = new JdProductDTO();

		/*String category = resultObj.getString("category");
		jdProductDTO.setCategoryId(Long.parseLong(category.substring(category.lastIndexOf(";") + 1)));*/
		jdProductDTO.setImagePath(skuImg);
		jdProductDTO.setSkuJson(skuInfo);
		jdProductDTO.setIntroduction(resultObj.getString("nappintroduction"));
		logger.info("字符串:" + jdProductDTO.getIntroduction());
		jdProductDTO.setIs7ToReturn(resultObj.getInteger("isToReturn"));
		jdProductDTO.setIsJdLogistics(resultObj.getInteger("isJDLogistics"));
		jdProductDTO.setIsSelf(resultObj.getInteger("isSelf"));
//		jdProductDTO.setLowestBuy(resultObj.getString(""));
		jdProductDTO.setMarketPrice(marketPrice);
		jdProductDTO.setName(resultObj.getString("name"));
		jdProductDTO.setNoReasonToReturn(resultObj.getInteger("noReasonToReturn"));
		jdProductDTO.setParam(skuInfo);
		jdProductDTO.setPrice(price);
//		jdProductDTO.setSaleState(resultObj.getString(""));
//		jdProductDTO.setSpuId(resultObj.getString(""));
		jdProductDTO.setSpuName(resultObj.getString("pName"));
		jdProductDTO.setState(jdUtils.getSkuState(cache, skuId));
		jdProductDTO.setSyncStatus(1);
		jdProductDTO.setThwa(resultObj.getInteger("thwa"));
		/*jdProductFacade.insertJdProductWithTx(jdProductDTO);
		createSelfProduct(jdProductDTO, user, ip, mac, jdPriceConfigDTO, req);*/
		return jdProductDTO;

	}
/*
	private void createSelfProduct(JdProductDTO jdProductDTO, CacheUser user, String ip, String mac, JdPriceConfigDTO jdPriceConfigDTO, HttpServletRequest req) {
		JdCategoryDTO jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(jdProductDTO.getCategoryId());
		if (jdCategoryDTO == null) {
			syncJdCategory(String.valueOf(0));
		}
		jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(jdProductDTO.getCategoryId());
		if (jdCategoryDTO == null) {
			throw new BusinessException("京东分类信息同步失败");
		}
		createInnerProduct(jdProductDTO, jdCategoryDTO, user, ip, mac, jdPriceConfigDTO, req);
	}*/
/*
	private void createInnerProduct(JdProductDTO jdProductDTO, JdCategoryDTO jdCategoryDTO, CacheUser user, String ip, String mac, JdPriceConfigDTO jdPriceConfigDTO, HttpServletRequest req) {
		Map<String, String> skuMap = JSONObject.parseObject(jdProductDTO.getSkuJson(), Map.class);
		String skuStr = "";
		if (skuMap != null && !skuMap.isEmpty()) {
			for (Entry<String, String> entry : skuMap.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				if (EmptyUtil.isNotEmpty(key) && EmptyUtil.isNotEmpty(val)) {
					skuStr += key + ":" + val + " ";
				}
			}
		}

		AttributeNameDTO attName = new AttributeNameDTO();
		attName.setName("商品规格");
		attName.setMode(2);
		List<AttributeNameDTO> attNameList = attributeNameFacade.findAll(attName);
		if (EmptyUtil.isEmpty(attNameList)) {
			attName.setSpecificationProperty(1);
			attName.setParameterProperty(0);
			attName.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
			attributeNameFacade.saveAttributeName(attName);
		} else {
			attName = attNameList.get(0);
		}
		List<AttNameValueDTO> atts = buildParamAtt();
		AttributeValueDTO attValue = new AttributeValueDTO();
		attValue.setValue(skuStr);
		List<AttributeValueDTO> attValueList = attributeValueFacade.findAll(attValue);
		if (EmptyUtil.isEmpty(attValueList)) {
			attValue.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
			attValue.setAttributeNameId(attName.getId());
			Long aLong = attributeValueFacade.saveAttributeValue(attValue);
			attValue.setId(aLong);
		} else {
			attValue = attValueList.get(0);
		}

		CategoryAttNameDTO catAttName = new CategoryAttNameDTO();
		catAttName.setAttNameId(attName.getId());
		catAttName.setCategoryId(jdCategoryDTO.getInnerCategoryId());
		List<CategoryAttNameDTO> catAttNameList = categoryAttNameFacade.findAll(catAttName);
		if (EmptyUtil.isEmpty(catAttNameList)) {
			catAttName.setType(2);
			catAttName.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
			String s = categoryAttNameFacade.addCategoryAttNameWithTx(catAttName);
			catAttName.setId(Long.valueOf(s));
		} else {
			catAttName = catAttNameList.get(0);
		}

		CategoryAttValueDTO catAttValue = new CategoryAttValueDTO();
		catAttValue.setAttValueId(attValue.getId());
		catAttValue.setCategoryAttNameId(catAttName.getId());
		List<CategoryAttValueDTO> catAttValueList = categoryAttValueFacade.findAll(catAttValue);
		if (EmptyUtil.isEmpty(catAttValueList)) {
			catAttValue.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
			categoryAttValueFacade.saveCategoryAttValue(catAttValue);
		} else {
			catAttValue = catAttValueList.get(0);
		}
		//将固定参数写入类目参数中
		for (AttNameValueDTO dto : atts) {
			CategoryAttNameDTO name = new CategoryAttNameDTO();
			name.setAttNameId(Long.valueOf(dto.getKey()));
			name.setCategoryId(jdCategoryDTO.getInnerCategoryId());
			List<CategoryAttNameDTO> nameList = categoryAttNameFacade.findAll(name);
			if (EmptyUtil.isEmpty(nameList)) {
				name.setType(dto.getType());
				name.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
				String s = categoryAttNameFacade.addCategoryAttNameWithTx(name);
				name.setId(Long.valueOf(s));
			}

		}
		Long productId = null;
		List<ProductAttValueDTO> productAttValueList = new ArrayList<>();
		ProductAttValueDTO productAttValueDTO = new ProductAttValueDTO();
		productAttValueDTO.setAttValueId(attValue.getId());
		productAttValueList.add(productAttValueDTO);
		for (AttNameValueDTO dto : atts) {
			ProductAttValueDTO productAttValue = new ProductAttValueDTO();
			productAttValue.setAttValueId(Long.valueOf(dto.getValue()));
			productAttValueList.add(productAttValue);
		}
		productId = productAttValueFacade.saveProductAttValueByProductIdAndAttnameId(productId, productAttValueList, PlatformKeyConstant.FGJ_PLATFORM_ID);

		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productId);

		CategoryTreeNodeDTO catTreeNode = new CategoryTreeNodeDTO();
		catTreeNode.setCategoryId(jdCategoryDTO.getInnerCategoryId());
		List<CategoryTreeNodeDTO> catTreeNodeList = categoryTreeNodeFacade.findAll(catTreeNode);
		if (EmptyUtil.isNotEmpty(catTreeNodeList)) {
			catTreeNode = catTreeNodeList.get(0);
		}
		productDTO.setCategoryId(jdCategoryDTO.getInnerCategoryId());
		productDTO.setCategoryTreeNodeId(catTreeNode.getId());
		productDTO.setName(jdProductDTO.getName());
		productDTO.setMarketPrice(jdProductDTO.getMarketPrice());
		productDTO.setStatus(1);
		productDTO.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
		productDTO.setCommodityTemplateId(2L);
		productDTO.setProductCategory(getCategoryName(jdProductDTO.getCategoryId()));

		ProductDescriptionDTO productDescriptionDTO = new ProductDescriptionDTO();
		productDescriptionDTO.setProductId(productId);
		String str = jdProductDTO.getIntroduction();
		logger.info("商品描述:" + str);
		//productDescriptionDTO.setContent(jdProductDTO.getIntroduction());
		productDescriptionDTO.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);

		PictureDTO pictureDTO = new PictureDTO();
		Map<String, Object> imgMap = JSONObject.parseObject(jdProductDTO.getImagePath(), Map.class);
		if (imgMap != null) {
			List<PictureDTO> picList = new ArrayList<>();
			PictureDTO pic = new PictureDTO();
			String url = (String) imgMap.get("primaryImg");
			pic.setUrl(url);
			pic.setName(url.substring(url.lastIndexOf("/")));
			picList.add(pic);
			List<String> assisImgList = (List<String>) imgMap.get("assisImg");
			for (String assisImg : assisImgList) {
				pic.setUrl(assisImg);
				pic.setName(assisImg.substring(assisImg.lastIndexOf("/")));
			}
			pictureDTO.setStyleImage(picList);
		}

		BrandDTO brandDTO = new BrandDTO();
		List<AttNameValueDTO> lists = new ArrayList<>();
		AttNameValueDTO attNameValue = new AttNameValueDTO();
		attNameValue.setKey(attName.getId().toString());
		attNameValue.setValue(attValue.getId().toString());
		//lists.add(attNameValue);

		lists.addAll(atts);

		productFacade.updateProduct(productDTO, productDescriptionDTO, pictureDTO, brandDTO, lists, attName.getId());
		ProductDTO dto = new ProductDTO();
		dto.setId(productDTO.getId());
		dto.setStatus(1);
		productFacade.audit(dto);
		//审核产品
		productDTO.setStatus(3);
		productFacade.updateStatus(productDTO, user.getId(), user.getName(), ip, mac);
		logger.info("产品添加成功,产品id:" + productDTO.getId());

		logger.info("开始添加商品");
		//添加商品
		MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
		merchantProductDTO.setCreateUserid(user.getId());
		merchantProductDTO.setCreateUsername(user.getName());
		merchantProductDTO.setCreateUserip(ip);
		merchantProductDTO.setCreateUsermac(mac);
		merchantProductDTO.setUpdateUserid(user.getId());
		merchantProductDTO.setUpdateUsername(user.getName());
		merchantProductDTO.setUpdateUserip(ip);
		merchantProductDTO.setUpdateUsermac(mac);
		merchantProductDTO.setFrontSerialNumber(99999);
		merchantProductDTO.setName(productDTO.getName());
		//运营方京东专卖店
		merchantProductDTO.setMerchantId(6L);
		merchantProductDTO.setStandardProductUnitId(productId);
		merchantProductDTO.setProductCategory(productDTO.getProductCategory());
		merchantProductDTO.setIsVisible(0);
		merchantProductDTO.setType(0);
		merchantProductDTO.setMarketPrice(productDTO.getMarketPrice());
		merchantProductDTO.setStatus(2);
		merchantProductDTO.setSaleWay(1);
		merchantProductDTO.setSoldBase(0L);
		merchantProductDTO.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
		merchantProductDTO.setStoreId(PlatformKeyConstant.FGJ_ROOT_STORE_ID);
		merchantProductDTO.setSalePrice(jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTO.getStandardCompanyRate())).divide(BigDecimal.valueOf(100L), 2));

		String picture = (String) imgMap.get("primaryImg");
		String pictureList = JSONObject.toJSONString(imgMap.get("assisImg"));
		String webBannerPictureList = JSONObject.toJSONString(new ArrayList<>());
		List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList = new ArrayList<>();
		String clientList = "[1,2]";
		String content = jdProductDTO.getIntroduction();
		List<Long> tagList = new ArrayList<>();
		List<String> keywords = new ArrayList<>();
		List<Long> storeIds = new ArrayList<>();
		storeIds.add(PlatformKeyConstant.FGJ_ROOT_STORE_ID);
		List<Long> membershipIds = new ArrayList<>();
		List<Long> companys = Arrays.asList(new Long[]{-1L});
		List<Long> demoCompanys = Arrays.asList(new Long[]{-1L});
		List<Long> competingCompanys = Arrays.asList(new Long[]{-1L});

		List<ProductUnitDTO> productUnitList = new ArrayList<>();
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setStandardProductUnitId(productId);
		List<SkuDTO> skuList = skuFacade.findSkuAll(skuDTO);
		if (EmptyUtil.isNotEmpty(skuList)) {
			skuDTO = skuList.get(0);
		}
		ProductUnitDTO productUnitDTO = new ProductUnitDTO();
		productUnitDTO.setSkuId(skuDTO.getId());
		productUnitDTO.setChecked(true);
		productUnitDTO.setSalePrice(jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTO.getStandardCompanyRate())).divide(BigDecimal.valueOf(100L), 2));
		productUnitDTO.setDemoSalePrice(jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTO.getDemocompanysCompanyRate())).divide(BigDecimal.valueOf(100L), 2));
		productUnitDTO.setCompetingSalePrice(jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTO.getCompetingCompanyRate())).divide(BigDecimal.valueOf(100L), 2));
		productUnitDTO.setName(productDTO.getName());
		productUnitDTO.setSkuName(skuDTO.getSkuName());
		productUnitDTO.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);

		skuFacade.updateExternalSkuId(skuDTO.getId(), jdProductDTO.getId().toString(), null);

		productUnitList.add(productUnitDTO);
		Long merchantProductId = merchantProductFacade.insertMerchantProductWithTx(
				merchantProductDTO, picture, pictureList, webBannerPictureList,
				sellPlatformMerchantProdVOList, clientList, content,
				productUnitList, tagList, keywords, companys, demoCompanys, competingCompanys,
				storeIds, membershipIds);

		merchantProductManage.merchantProductPassWithTx(merchantProductId, null, 1, PlatformKeyConstant.FGJ_PLATFORM_ID, req);

	}*/

	@Override
	public   String getCategoryName(Long jdCategoryId) {
		String catName = "";
		List<JdCategoryDTO> catList = new ArrayList<>();
		getCategoryPath(catList, jdCategoryId);

		if (catList.size() > 1) {
			for (int i = catList.size() - 1; i >= 0; i--) {
				JdCategoryDTO cat = catList.get(i);
				if (i < catList.size() - 1) {
					catName += " > ";
				}
				catName += cat.getName();
			}
		} else if (catList.size() ==1) {
			catName = catList.get(0).getName();
		}

		return catName;
	}


	//京东撒谎那个破生成固定的基本参数快集合
	private List<AttNameValueDTO> buildParamAtt() {
		AttNameValueDTO att1 = new AttNameValueDTO();
		att1.setValue(2 + "");
		att1.setKey(1 + "");
		att1.setMode(1 + "");
		att1.setName("是否为电子卡券");
		AttNameValueDTO att2 = new AttNameValueDTO();
		att2.setValue(4 + "");
		att2.setKey(2 + "");
		att2.setMode(1 + "");
		att2.setName("是否存在unit库存");
		AttNameValueDTO att3 = new AttNameValueDTO();
		att3.setValue(6 + "");
		att3.setKey(3 + "");
		att3.setMode(1 + "");
		att3.setName("是否在app内使用");
		AttNameValueDTO att4 = new AttNameValueDTO();
		att4.setValue(13 + "");
		att4.setKey(4 + "");
		att4.setMode(1 + "");
		att4.setName("第三方对接参数");
		AttNameValueDTO att5 = new AttNameValueDTO();
		att5.setValue("");
		att5.setKey(1 + "");
		att5.setMode(1 + "");
		att5.setName("搜索关键词");
		List<AttNameValueDTO> res = new ArrayList<AttNameValueDTO>();
		res.add(att1);
		res.add(att2);
		res.add(att3);
		res.add(att4);
		//res.add(att5);
		return res;
	}

	private void getCategoryPath(List<JdCategoryDTO> catList, Long jdCategoryId) {
		JdCategoryDTO jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(jdCategoryId);
		if (jdCategoryDTO != null) {
			catList.add(jdCategoryDTO);
			if (jdCategoryDTO.getParentId() != null&&jdCategoryDTO.getParentId()!=0) {
				getCategoryPath(catList, jdCategoryDTO.getParentId());
			}
		}
	}

	private void initOldId(){
		productIdOld=jdProductFacade.findProductLastId();
		pictureIdOld=jdProductFacade.findPictureLastId() ;
		attValueIdOld=jdProductFacade.findAttributeValueLastId();
		productAttNameIdOld=jdProductFacade.findProductAttNameLastId();
		spuIdOld=jdProductFacade.findStandardProductUnitLastId();
		spuAttNameIdOld=jdProductFacade.findStandardProductUnitAttNameLastId();
		skuAttNameIdOld=jdProductFacade.findSkuAttNameLastId();
		skuIdOld=jdProductFacade.findSkuLastId();
		merchantProductIdOld= jdProductFacade.findMerchantProductLastId();
		merchantPictureIdOld=jdProductFacade.findMerchantPictureLastId();
		productUnitIdOld=jdProductFacade.findProductUnitLastId();
		puIdOld=jdProductFacade.findCommodityProductUnitLastId();
	}
	public void syncJdProduct(CacheUser user, String ip, String mac, HttpServletRequest req) {
		//将已有京东上全部变成已移除
		jdProductFacade.setAllSyncStatus(0);
		//初始化id
		initOldId();
		List<JdProductInnerIdDTO> jdProductInnerIdDTOList = jdProductInnerIdFacade.findJdProductInnerIdAll(new JdProductInnerIdDTO());
		//加价率=10%
		Integer PROFIT = productLimitProfitFacade.findProductLimitProfit();
		//价格上下限
		JdPriceLimitUploadDTO priceLimitUploadDTO=jdProductFacade.getJdPriceLimitUpLoad();
		//查询京东所有三级节点
		List<Long> categoryIdAll= jdProductFacade.findJdCategoryIdByCatClass(3);
		List<Long> allIdList = jdProductFacade.findAllIdList();

		/*//测试代码
		Long[] arr={655L, 672L, 673L, 674L, 675L, 678L, 679L,
				680L, 681L, 682L, 683L, 684L, 687L, 688L, 689L,
				690L, 691L, 692L, 693L, 694L, 695L, 696L, 698L, 700L,701L,
				702L,717L,718L,719L,720L,721L,722L,723L,724L,725L,727L};

		categoryIdAll = Arrays.asList(arr);*/

		//第一步:更具查询到的所有京东三级类目,每100个节点查询一次,查询出所有的京东商品信息
		int catSize=100;
		List<Long> categoryIdList = new ArrayList<>();
		List<Long> jdIdArr = new ArrayList<>();//存放新增本地的京东商品id,防止出现重复创建(一个京东商品在不同的分类中)
		for(int p=0;p<((categoryIdAll.size()-1)/catSize)+1;p++){
			if((p+1)==((categoryIdAll.size()-1)/catSize)+1){
				categoryIdList  = categoryIdAll.subList(p* catSize, categoryIdAll.size());
			}else{
				categoryIdList =categoryIdAll.subList(p * catSize, (p + 1) * catSize);
			}


			ExecutorService executorService = Executors.newFixedThreadPool(100);
			List<JdProductDTO> jdProductDTOList = new ArrayList<>();

			List<Long> idList = new ArrayList<>();
			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			if (EmptyUtil.isNotEmpty(categoryIdList)) {
				int i=0;
				for (Long catId : categoryIdList) {
					i++;
					//每500个节点更新一次token(长时间查询可能会导致token失效)
					if(i%500==0){
						//token=jdUtils.getAccessToken(cache);
						token=jdUtils2.getAccessToken(cache);
					}
					List<JdProductDTO> jdProduct = getJdProduct(priceLimitUploadDTO.getJdPriceLimitStart(),priceLimitUploadDTO.getJdPriceLimitEnd(),executorService, catId, token);

					if (EmptyUtil.isNotEmpty(jdProduct)) {
						for(JdProductDTO jd:jdProduct){
							if(!idList.contains(jd.getId())){
								idList.add(jd.getId());
								jdProductDTOList.add(jd);
							}
						}
					}
				}
			}
			logger.error("第"+p+"批节点有京东商品个数:"+jdProductDTOList.size());


			//第二步:京东商品基础信息查询完成,开始查询京东商品的价格,查询后将价格放入商品集合中
			logger.error("第"+p+"批节点开始查询价格");
			getJdProductPrice(jdProductDTOList,token);

			//第三布:开始计算校验毛利然后保存京东商品创建本地商品
			checkProfit(jdIdArr,allIdList,null,jdProductDTOList, PROFIT,p,categoryIdList,jdProductInnerIdDTOList);


			executorService.shutdown();

		}


	}


	public void syncJdProductList(CacheUser user, String ip, String mac, HttpServletRequest req) {
		//将已有京东上全部变成已移除
		jdProductFacade.setAllSyncStatus(0);
		//初始化id
		initOldId();
		List<JdProductInnerIdDTO> jdProductInnerIdDTOList = jdProductInnerIdFacade.findJdProductInnerIdAll(new JdProductInnerIdDTO());
		Integer PROFIT = productLimitProfitFacade.findProductLimitProfit();
		JdPriceLimitUploadDTO priceLimitUploadDTO=jdProductFacade.getJdPriceLimitUpLoad();
		//查询京东所有三级节点
		List<Long> categoryIdAll= jdProductFacade.findJdCategoryIdByCatClass(3);
		List<Long> allIdList = jdProductFacade.findAllIdList();

		/*//测试代码
		Long[] arr={655L, 672L, 673L, 674L, 675L, 678L, 679L,
				680L, 681L, 682L, 683L, 684L, 687L, 688L, 689L,
				690L, 691L, 692L, 693L, 694L, 695L, 696L, 698L, 700L,701L,
				702L,717L,718L,719L,720L,721L,722L,723L,724L,725L,727L};

		categoryIdAll = Arrays.asList(arr);*/

		//第一步:更具查询到的所有京东三级类目,每100个节点查询一次,查询出所有的京东商品信息
		int catSize=100;
		List<Long> categoryIdList = new ArrayList<>();
		List<Long> jdIdArr = new ArrayList<>();//存放新增本地的京东商品id,防止出现重复创建(一个京东商品在不同的分类中)
		for(int p=0;p<((categoryIdAll.size()-1)/catSize)+1;p++){
			if((p+1)==((categoryIdAll.size()-1)/catSize)+1){
				categoryIdList  = categoryIdAll.subList(p* catSize, categoryIdAll.size());
			}else{
				categoryIdList =categoryIdAll.subList(p * catSize, (p + 1) * catSize);
			}


			ExecutorService executorService = Executors.newFixedThreadPool(100);
			List<JdProductDTO> jdProductDTOList = new ArrayList<>();

			List<Long> idList = new ArrayList<>();
			//String token = jdUtils.getAccessToken(cache);
			String token = jdUtils2.getAccessToken(cache);
			if (EmptyUtil.isNotEmpty(categoryIdList)) {
				int i=0;
				for (Long catId : categoryIdList) {
					i++;
					//每500个节点更新一次token(长时间查询可能会导致token失效)
					if(i%500==0){
						//token=jdUtils.getAccessToken(cache);
						token=jdUtils2.getAccessToken(cache);
					}
					List<JdProductDTO> jdProduct = getJdProduct(priceLimitUploadDTO.getJdPriceLimitStart(),priceLimitUploadDTO.getJdPriceLimitEnd(),executorService, catId, token);

					if (EmptyUtil.isNotEmpty(jdProduct)) {
						for(JdProductDTO jd:jdProduct){
							if(!idList.contains(jd.getId())){
								idList.add(jd.getId());
								jdProductDTOList.add(jd);
							}
						}
					}
				}
			}
			logger.error("第"+p+"批节点有京东商品个数:"+jdProductDTOList.size());


			//第二步:京东商品基础信息查询完成,开始查询京东商品的价格,查询后将价格放入商品集合中
			logger.error("第"+p+"批节点开始查询价格");
			getJdProductPrice(jdProductDTOList,token);

			//第三布:开始计算校验毛利然后保存京东商品创建本地商品
			saveJdProductList(jdIdArr,allIdList,null,jdProductDTOList, PROFIT,p,categoryIdList,jdProductInnerIdDTOList);


			executorService.shutdown();

		}


	}
	private void getJdProductPrice(List<JdProductDTO> jdProductDTOList, String token) {
		//得到所有商品,批量查询商品价格
		//token = jdUtils.getAccessToken(cache);
		token = jdUtils2.getAccessToken(cache);
		for (int i = 0; i < (((jdProductDTOList.size() - 1) / 100) + 1); i++) {
			if(i!=0&&i%100==0){
				//token= jdUtils.getAccessToken(cache);
				token= jdUtils2.getAccessToken(cache);
			}
			List<Long> skuIdList = new ArrayList<>();
			if (i == ((jdProductDTOList.size() - 1) / 100)) {
				for (int j = 0; j < (jdProductDTOList.size() - (100 * i)); j++) {
					Long id = jdProductDTOList.get((100 * i) + j).getId();
					skuIdList.add(id);
				}
			} else {
				for (int j = 0; j < 100; j++) {
					Long id = jdProductDTOList.get((100 * i) + j).getId();
					skuIdList.add(id);
				}
			}
			JSONArray priceResultObj = jdUtils.getSellPrice(token, skuIdList);

			if (EmptyUtil.isNotEmpty(priceResultObj)) {
				for (Object obj : priceResultObj) {
					JSONObject ob = (JSONObject) obj;
					if (EmptyUtil.isNotEmpty(ob)) {
						BigDecimal price = ob.getBigDecimal("price");
						BigDecimal marketPrice = ob.getBigDecimal("jdPrice");
						if (EmptyUtil.isEmpty(price) || EmptyUtil.isEmpty(marketPrice)) {
							logger.info("价格为空");
						}
						Long skuId = ob.getLong("skuId");
						if (EmptyUtil.isNotEmpty(skuId)) {
							for (JdProductDTO jdProductDTO : jdProductDTOList) {
								if (jdProductDTO.getId().equals(skuId)) {
									jdProductDTO.setPrice(price);
									jdProductDTO.setMarketPrice(marketPrice);
									jdProductDTO.setSyncStatus(1);
								}
							}
						}
					} else {
						logger.info("京东商品价格为空");
					}
				}
			} else {
				logger.error("查询商品价格失败,sku=" + skuIdList.toString());
			}
		}
	}

	private void saveJdProductList(List<Long> jdIdArr, List<Long> oldJdList, Integer type, List<JdProductDTO> jdProductDTOList, Integer PROFIT, Integer p, List<Long> categoryIdList, List<JdProductInnerIdDTO> jdProductInnerIdDTOList){
		//type为空是更新下载京东商品池,type为1时是更新指定京东商品,所以不需要查询所有已存在京东商品,此时可以确定该商品已存在仅更新
		if (EmptyUtil.isNotEmpty(jdProductDTOList)) {

			List<JdProductDTO> addList = new ArrayList<>();
			List<JdProductDTO> updateList = new ArrayList<>();
			for (JdProductDTO jp : jdProductDTOList) {
				//计算毛利
				if (EmptyUtil.isNotEmpty(jp.getPrice()) && EmptyUtil.isNotEmpty(jp.getMarketPrice())) {
					BigDecimal divide = jp.getMarketPrice().subtract(jp.getPrice()).divide(jp.getPrice(), 2);
					jp.setProfit(divide.multiply(BigDecimal.valueOf(100)).intValue());
					if (jp.getProfit() >= PROFIT) {
						//jp.setIsShow(1);
						jp.setProductCreateTime(new Date());

					}
				} else {
					jp.setProfit(0);
					jp.setPrice(BigDecimal.ZERO);
					jp.setMarketPrice(BigDecimal.ZERO);
					logger.info("当前商品价格为空,skuid:" + jp.getId());
				}

				jp.setIsShow(3);//默认按照毛利显示

				//jp.setImagePath(null);
				jp.setSyncTime(new Date());

				if(EmptyUtil.isEmpty(type)){
					if (EmptyUtil.isNotEmpty(oldJdList) && oldJdList.contains(jp.getId())) {
						updateList.add(jp);
					} else {
						addList.add(jp);
					}
				}else{
					updateList.add(jp);
				}


			}


			//处理京东商品池的保存更新
			if (EmptyUtil.isNotEmpty(addList)) {
				logger.info("更新京东商品池,添加商品数量:" + addList.size());
				jdProductFacade.saveJdProductList(addList);
				logger.info("更新京东商品池,主线程完成");
			}
			if (EmptyUtil.isNotEmpty(updateList)) {
				logger.info("更新京东商品池,更新商品数量:" + updateList.size());
				jdProductFacade.updateJdProductList(updateList);
			}

		/*	//处理本地的商品更新与创建
			//处理新增的商品
			if (EmptyUtil.isNotEmpty(addList)) {
				logger.error("第"+p+"批新增商品处理:"+addList.size());
				createProductByJdProduct(jdIdArr,addList,p);
			}
			//处理更新的商品
			if (EmptyUtil.isNotEmpty(updateList)) {
				logger.error("第"+p+"批新增商品处理:"+updateList.size());
				updateProductByJdProduct(updateList,p,jdProductInnerIdDTOList);
			}*/

		}
	}




	private void checkProfit(List<Long> jdIdArr, List<Long> oldJdList, Integer type, List<JdProductDTO> jdProductDTOList, Integer PROFIT, Integer p, List<Long> categoryIdList, List<JdProductInnerIdDTO> jdProductInnerIdDTOList){
		//type为空是更新下载京东商品池,type为1时是更新指定京东商品,所以不需要查询所有已存在京东商品,此时可以确定该商品已存在仅更新
		if (EmptyUtil.isNotEmpty(jdProductDTOList)) {

			List<JdProductDTO> addList = new ArrayList<>();
			List<JdProductDTO> updateList = new ArrayList<>();
			for (JdProductDTO jp : jdProductDTOList) {
				//计算毛利
				if (EmptyUtil.isNotEmpty(jp.getPrice()) && EmptyUtil.isNotEmpty(jp.getMarketPrice())) {
					//毛利率  = 100*(市场价-京东售价)/京东售价  保留2位小数
					BigDecimal divide = jp.getMarketPrice().subtract(jp.getPrice()).divide(jp.getPrice(), 2);
					jp.setProfit(divide.multiply(BigDecimal.valueOf(100)).intValue());
					if (jp.getProfit() >= PROFIT) {
						//jp.setIsShow(1);
						jp.setProductCreateTime(new Date());

					}
				} else {
					jp.setProfit(0);
					jp.setPrice(BigDecimal.ZERO);
					jp.setMarketPrice(BigDecimal.ZERO);
					logger.info("当前商品价格为空,skuid:" + jp.getId());
				}

				jp.setIsShow(3);//默认按照毛利显示

				//jp.setImagePath(null);
				jp.setSyncTime(new Date());

				if(EmptyUtil.isEmpty(type)){
					if (EmptyUtil.isNotEmpty(oldJdList) && oldJdList.contains(jp.getId())) {
						updateList.add(jp);
					} else {
						addList.add(jp);
					}
				}else{
					updateList.add(jp);
				}


			}

			/*try {
				cache.set("PROD-JD-"+p,jdProductDTOList);
			}catch (Exception e){
				if(EmptyUtil.isNotEmpty(categoryIdList)){
					logger.error("写缓存失败第"+p+"批节点"+categoryIdList.toString());
				}
			}*/

			//处理京东商品池的保存更新
			if (EmptyUtil.isNotEmpty(addList)) {
				logger.info("更新京东商品池,添加商品数量:" + addList.size());
				jdProductFacade.saveJdProductList(addList);
				logger.info("更新京东商品池,主线程完成");
			}
			if (EmptyUtil.isNotEmpty(updateList)) {
				logger.info("更新京东商品池,更新商品数量:" + updateList.size());
				jdProductFacade.updateJdProductList(updateList);
			}

			//处理本地的商品更新与创建
			//处理新增的商品
			if (EmptyUtil.isNotEmpty(addList)) {
				logger.error("第"+p+"批新增商品处理:"+addList.size());
				createProductByJdProduct(jdIdArr,addList,p);
			}
			//处理更新的商品
			if (EmptyUtil.isNotEmpty(updateList)) {
				logger.error("第"+p+"批新增商品处理:"+updateList.size());
				updateProductByJdProduct(updateList,p,jdProductInnerIdDTOList);
			}

		}
	}

	private void updateProductByJdProduct(List<JdProductDTO> updateList, int p, List<JdProductInnerIdDTO> jdProductInnerIdDTOList) {
		Integer PROFIT = productLimitProfitFacade.findProductLimitProfit();

		//筛选需要的product进行产品,商品的更新
		List<JdProductDTO> updateProductList = new ArrayList<>();
		for (JdProductDTO jdProductDTO : updateList) {

			if (jdProductDTO.getProfit() >= PROFIT) {
				//大于20%毛利,就创建商品
				updateProductList.add(jdProductDTO);
			}
		}
		logger.info("商品数量=" + updateProductList.size());
		if(EmptyUtil.isNotEmpty(updateProductList)){
			List<JdProductInnerIdDTO> list = new ArrayList<>();
			List<JdProductDTO> update = new ArrayList<>();
			List<JdProductDTO> add = new ArrayList<>();
			boolean flag = false;
			for(JdProductDTO jdProductDTO : updateProductList){
				if(EmptyUtil.isNotEmpty(jdProductInnerIdDTOList)){
					for(JdProductInnerIdDTO dto:jdProductInnerIdDTOList){
						if(jdProductDTO.getId().equals(dto.getJdSkuId())){
							flag=true;
							update.add(jdProductDTO);
							list.add(dto);
							break;
						}
					}
				}
				if(!flag){
					add.add(jdProductDTO);
				}

			}
			if(EmptyUtil.isNotEmpty(update)){
				logger.error("更新的商品数量:"+update.size());
				jdProductFacade.updateProductByJdProductList(update,list);
			}
			if(EmptyUtil.isNotEmpty(add)){
				logger.error("更新商品中需要新创建本地商品:"+add.size());
				createNewJdProductLocation(add);
			}
		}



	}

	/* 1.根据skuIds获得库中的Jd产品数据
	 */
	/*@Override
	public void syncJdProductByJdSkuId(List<Long> skuIdList) {
		initOldId();
		//组织需要的参数
		List<JdProductDTO> jdProductDTOList = jdProductFacade.findJdProductListByIds(skuIdList);
		for (JdProductDTO dto : jdProductDTOList) {
			if (EmptyUtil.isNotEmpty(dto.getProductCreateTime())) {
				throw new BusinessException(dto.getId() + "该商品已同步过,无法再次强制同步");
			}
			dto.setIsShow(1);
			dto.setProductCreateTime(new Date());

			//查询本地类目内容
			JdCategoryDTO jdCategoryDTO = null;
			CategoryDTO categoryDTO = null;
			CategoryTreeNodeDTO catTreeNode2 = null;
			if (EmptyUtil.isNotEmpty(dto.getCategoryId())) {
				//查询类目
				jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(dto.getCategoryId());
				if (EmptyUtil.isNotEmpty(jdCategoryDTO)) {
					categoryDTO = jdProductFacade.findCategoryById(jdCategoryDTO.getInnerCategoryId());
					CategoryTreeNodeDTO catTreeNode = new CategoryTreeNodeDTO();
					catTreeNode.setCategoryId(jdCategoryDTO.getInnerCategoryId());
					List<CategoryTreeNodeDTO> catTreeNodeList = categoryTreeNodeFacade.findAll(catTreeNode);
					if (EmptyUtil.isNotEmpty(catTreeNodeList)) {
						catTreeNode2 = catTreeNodeList.get(0);
					}


				} else {
					logger.info("京东分类不存在,catId=" + dto.getCategoryId());
					throw new BusinessException("京东分类不存在,catId=" + dto.getCategoryId());

				}

			}
			String categoryName = getCategoryName(dto.getCategoryId());

			dto.setInnerCategoryId(jdCategoryDTO.getInnerCategoryId());
			dto.setInnerCategorySerialNumber(categoryDTO.getSerialNumber());
			dto.setInnerCategoryTreeNode(catTreeNode2.getId());
			dto.setInnerCategoryName(categoryName);


		}
		logger.info("查询到需要手动同步的商品");
		if (EmptyUtil.isNotEmpty(jdProductDTOList)) {
			jdProductFacade.createProduct(jdProductDTOList, this.productIdOld, this.pictureIdOld, this.attValueIdOld, this.productAttNameIdOld, this.spuIdOld, this.spuAttNameIdOld, this.skuAttNameIdOld, this.skuIdOld, this.merchantProductIdOld, this.merchantPictureIdOld, this.productUnitIdOld, this.puIdOld);
			jdProductFacade.updateCreateProductList(jdProductDTOList);
		}
	}
*/
	@Override
	public  PageResult<JdProductDTO> getJdProductListByParams(Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow, Pagination page) {
		PageResult<JdProductDTO> jdProductList= jdProductFacade.getJdProductListByParams(page, skuId, skuNameList, updateTimeStart, updateTimeEnd,
				profitStart, profitEnd, state, sycStatus, catId, isShow);
		for(JdProductDTO dto:jdProductList.getList()){
			Long jdCatId = dto.getCategoryId();
			String categoryName = getCategoryName(jdCatId);
			dto.setCategoryName(categoryName);

		}
		return jdProductList;

	}

	private void createProductByJdProduct(List<Long> jdIdArr, List<JdProductDTO> addList, int p) {
		Integer PROFIT = productLimitProfitFacade.findProductLimitProfit();
		logger.info("商品数量=" + addList.size());

		//筛选需要的product进行产品,商品的创建(仅新增的京东商品,更新的商品还未实现)
		List<JdProductDTO> createProductList = new ArrayList<>();
		for (JdProductDTO jdProductDTO : addList) {

			if (jdProductDTO.getProfit() >= PROFIT&&!jdIdArr.contains(jdProductDTO.getId())) {
				//大于20%毛利,就创建商品
				jdIdArr.add(jdProductDTO.getId());
				createProductList.add(jdProductDTO);
			}
		}
		logger.info("需要创建本地商品数量=" + createProductList.size());
		if (EmptyUtil.isNotEmpty(createProductList)) {
			createNewJdProductLocation(createProductList);


		}
	}

	private void createNewJdProductLocation(List<JdProductDTO> createProductList){
		jdProductFacade.createProduct(createProductList,this.productIdOld,this.pictureIdOld,this.attValueIdOld,
				this.productAttNameIdOld,this.spuIdOld,this.spuAttNameIdOld,this.skuAttNameIdOld,this.skuIdOld,
				this.merchantProductIdOld ,this.merchantPictureIdOld,this.productUnitIdOld,this.puIdOld);
		//计算id
		int size = createProductList.size();
		this.productIdOld  = this.productIdOld+size;
		this.pictureIdOld = this.pictureIdOld+size;
		this.attValueIdOld  = this.attValueIdOld+size;
		this.productAttNameIdOld =this. productAttNameIdOld+(size*5);
		this.spuIdOld  = this.spuIdOld+size;
		this.spuAttNameIdOld= this.spuAttNameIdOld+(size*5);
		this.skuAttNameIdOld = this.skuAttNameIdOld+size;
		this.skuIdOld = this.skuIdOld+size;
		this.merchantProductIdOld  = this.merchantProductIdOld+size;
		this.merchantPictureIdOld = this.merchantPictureIdOld+size;
		this.productUnitIdOld = this.productUnitIdOld+size;
		this.puIdOld = this.puIdOld+size;
	}

	private List<JdProductDTO> getJdProduct(BigDecimal min, BigDecimal max, ExecutorService executorService, Long catId, String token) {
		List<JdProductDTO> jdProductDTOList = new ArrayList<>();

		//查询本地内容
		JdCategoryDTO jdCategoryDTO = null;
		CategoryDTO categoryDTO = null;
		CategoryTreeNodeDTO catTreeNode2 = null;
		if (EmptyUtil.isNotEmpty(catId)) {
			//查询类目
			jdCategoryDTO = jdProductFacade.findJdCategoryDTOById(catId);
			if (EmptyUtil.isNotEmpty(jdCategoryDTO)) {
				categoryDTO = jdProductFacade.findCategoryById(jdCategoryDTO.getInnerCategoryId());
				CategoryTreeNodeDTO catTreeNode = new CategoryTreeNodeDTO();
				catTreeNode.setCategoryId(jdCategoryDTO.getInnerCategoryId());
				List<CategoryTreeNodeDTO> catTreeNodeList = categoryTreeNodeFacade.findAll(catTreeNode);
				if (EmptyUtil.isNotEmpty(catTreeNodeList)) {
					catTreeNode2 = catTreeNodeList.get(0);
				}
			} else {
				logger.info("京东分类不存在,catId=" + catId);
				return jdProductDTOList;

			}

		}
		String categoryName = getCategoryName(catId);

		int count = 1;
		List<JdProductDTO> productDTOS = threadJdProduct(min,max,executorService, count, catId, token, categoryName, catTreeNode2, categoryDTO, jdCategoryDTO);
		for (JdProductDTO productDTO : productDTOS) {
			productDTO.setInnerCategoryId(jdCategoryDTO.getInnerCategoryId());
			productDTO.setInnerCategorySerialNumber(categoryDTO.getSerialNumber());
			productDTO.setInnerCategoryTreeNode(catTreeNode2.getId());
			productDTO.setInnerCategoryName(categoryName);
			productDTO.setIsDetail(0);
		}
		return productDTOS;



		/*int count=1;
		return threadJdProduct(executorService,count, catId, token, null, null, null, null);*/


	}

	private List<JdProductDTO> threadJdProduct(BigDecimal min, BigDecimal max, ExecutorService executorService, int count, Long catId, String token, String categoryName, CategoryTreeNodeDTO catTreeNode2, CategoryDTO categoryDTO, JdCategoryDTO jdCategoryDTO){

		List<JdProductDTO> res = new ArrayList<>();
		JdProductThread jdProductThread = new JdProductThread();
		jdProductThread.setCatId(catId.toString());
		jdProductThread.setToken(token);
		jdProductThread.setCategoryDTO(categoryDTO);
		jdProductThread.setCategoryName(categoryName);
		jdProductThread.setCatTreeNode2(catTreeNode2);
		jdProductThread.setMax(max.longValue());
		jdProductThread.setMin(min.longValue());
		FutureTask<List<JdProductDTO>> task = new FutureTask<>(jdProductThread);
		executorService.execute(task);
		try {
			res = task.get();
			return res;
		} catch (Exception e) {
			logger.info("根据类目节点查询商品出错,节点id="+catId);
			if(count<=3){
				count++;
				threadJdProduct(min, max, executorService, count,catId,token,categoryName,catTreeNode2,categoryDTO,jdCategoryDTO);
			}else{
				return res;
			}
		}
		return res;
	}

	@Override
	public JdProductDTO fetchJdProductRate(Long skuId) {
		JSONObject resultObj = jdUtils2.getDetail(cache, skuId);
		if(resultObj!=null ) {
			JdProductDTO jdProductDTO = new JdProductDTO();
			if (resultObj.containsKey("taxInfo")){
				jdProductDTO.setTaxInfo(resultObj.getString("taxInfo"));
			}
			return jdProductDTO;
		}
		return null;
	}
}
