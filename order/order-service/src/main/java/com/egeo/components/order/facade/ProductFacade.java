package com.egeo.components.order.facade;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.order.enums.OrderRedisKeyEnum;
import com.egeo.components.product.client.*;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.order.common.DateUtils;
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.LimitRuleStoreDTO;
import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.components.order.service.read.LimitRuleCompanyReadService;
import com.egeo.components.order.service.read.LimitRuleReadService;
import com.egeo.components.order.service.read.LimitRuleRecordReadService;
import com.egeo.components.order.service.read.LimitRuleStoreReadService;
import com.egeo.components.order.service.read.LimitRuleUserReadService;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.FreightRegulationClient;
import com.egeo.components.product.client.FreightTemplateClient;
import com.egeo.components.product.client.JdProductClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitCombinationClient;
import com.egeo.components.product.client.StandardUnitStoreClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.vo.JdProductVO2;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import org.springframework.util.CollectionUtils;

@Component
public class ProductFacade {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitClient puReadService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient puStockReadService;

	@Autowired
	private MerchantProductVirtualStockClient unitStockReadService;

	@Autowired
	private SkuClient skuReadService;

	@Autowired
	private FreightTemplateClient freightTemplateReadService;

	@Autowired
	private FreightRegulationClient freightRegulationReadService;

	@Resource
	private LimitRuleReadService limitRuleReadService;

	@Resource
	private LimitRuleRecordReadService limitRuleRecordReadService;

	@Autowired
	private CompanyCoreClient companyCoreReadService;

	@Autowired
	private StoreClient storeReadService;

	@Autowired
	private StandardUnitClient standardUnitReadService;

	@Autowired
	private StandardUnitStoreClient standardUnitStoreReadService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Autowired
	private StandardUnitCombinationClient standardUnitCombinationReadService;
	@Resource
	private LimitRuleUserReadService limitRuleUserReadService;
	@Resource
	private LimitRuleCompanyReadService limitRuleCompanyReadService;
	@Resource
	private LimitRuleStoreReadService limitRuleStoreReadService;
	@Autowired
	private JdProductClient jdProductReadService;
	@Autowired
	private CakeProductClient cakeProductReadService;

	@Autowired
	private WorldBuyProductClient worldBuyProductClient;

	@Autowired
	private JedisUtil jedisUtil;


	private String limitBuyMoneySumError = "超过限购总金额,无法购买!";
	private String limitBuySumError = "超过限购数量,无法购买!";
	/**
	 * 根据puId查询单个pu
	 * @param puId
	 * @return
	 */
	public CommodityProductUnitDTO queryPuById(Long puId) {
		CommodityProductUnitDTO pu = puReadService.findSUSPUByPUId(puId);
		SkuDTO sku = skuReadService.findSkuByPuId(puId);
		if (pu != null && sku != null) {
			pu.setExternalSkuId(sku.getExternalSkuId());
			pu.setSupplierPrice(sku.getSkuCostingPrice());
			pu.setTaxRate(sku.getTaxRate());
		}
		return pu;
	}

	public CommodityProductUnitDTO queryPuByIdAndSupplierPrice(Long puId) {
		CommodityProductUnitDTO pu = puReadService.findSUSPUByPUId(puId);
		SkuDTO sku = skuReadService.findSkuByPuId(puId);
		if (pu != null && sku != null) {
			pu.setExternalSkuId(sku.getExternalSkuId());
			pu.setSupplierPrice(sku.getSkuCostingPrice());
			pu.setTaxRate(sku.getTaxRate());
		}
		return pu;
	}
	/**
	 * 查询pu库存
	 * @param puId
	 * @return
	 */
	public Long queryStockByPUId(Long puId) {
		return puStockReadService.realStockNumByCommodityProductUnitId(puId);
	}

	/**
	 * 判断pu是否是unit商品
	 * @param puId
	 * @return
	 */
	public boolean queryIsUnit(Long puId) {
		return puReadService.queryIsUnit(puId);
	}

	/**
	 * 查询unit库存
	 * @return
	 */
	public Long queryUnitStockBySkuId(Long skuId) {
		return unitStockReadService.queryUnitStockBySkuId(skuId);
	}

	/**
	 * 根据skuId查询商品的规格
	 * @return
	 */
	public Map<String,String> queryStandardBySkuId(Long skuId) {
		return skuReadService.queryStandardBySkuId(skuId);
	}

	/**
	 * 根据名称查询pu
	 * @param puName
	 * @return
	 */
	public List<CommodityProductUnitDTO> queryPuByName(String puName) {
		return puReadService.queryPuByName(puName);
	}

	/**
	 * 当pu对象中没有图片时调用该方法,
	 * 查询sku图片,sku图片为空时查询spu封面图片
	 * @param skuId
	 * @return
	 */
	public String queryPuNullImgUrl(Long skuId) {
		return puReadService.queryPuNullImgUrl(skuId);
	}
	/**
	 * 根据商家id查询商家启用模版
	 * @param merchantId
	 * @return
	 */
	public double freightAmountByMerchantId(double orderAmount, Long storeId, Long platformId, Long merchantId) {
		// 根据门店id查询总店信息
		StoreDTO storeDTO = storeReadService.findHeadStoreByStoreId(storeId);

		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		//根据商家id查询商家启用模版(现阶段写死、730运营方写活)
//		Long merchantId = 1L;
		double freightAmount = 0d;
		freightTemplateDTO.setMerchantId(merchantId);
		freightTemplateDTO.setIsValid(1);
		freightTemplateDTO.setStoreId(storeDTO.getId());
		freightTemplateDTO.setPlatformId(platformId);
		List<FreightTemplateDTO> list = freightTemplateReadService.findFreightTemplateAll(freightTemplateDTO);
		if(list.size() == 1){
		freightTemplateDTO = list.get(0);
		freightAmount = reckonvFreight(freightTemplateDTO,orderAmount);
		}else{
			// 默认启用运费模版查询默认总店的
			FreightTemplateDTO freightTemplate = new FreightTemplateDTO();
			freightTemplate.setMerchantId(merchantId);
			freightTemplate.setIsValid(1);
			if(platformId.equals(PlatformKeyConstant.FGJ_PLATFORM_ID)){
				freightTemplate.setStoreId(PlatformKeyConstant.FGJ_ROOT_STORE_ID);
			}
			if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
				freightTemplate.setStoreId(PlatformKeyConstant.MYY_ROOT_STORE_ID);
			}

			List<FreightTemplateDTO> freightTemplateList = freightTemplateReadService.findFreightTemplateAll(freightTemplate);
			if(freightTemplateList.size() == 1){
				freightTemplateDTO = list.get(0);
				freightAmount = reckonvFreight(freightTemplateDTO,orderAmount);
			}else{
				throw new BusinessException("查询商家启用模版异常，商家id：" + freightTemplateDTO.getMerchantId() + "门店Id：" + storeId);
			}

		}
		return freightAmount;
	}
	private double reckonvFreight(FreightTemplateDTO freightTemplateDTO,double orderAmount) {
		//运费
		double freightAmount = 0d;
		if(freightTemplateDTO.getIsExemption() == 0){
			//如果不包邮查询运费规则信息
			FreightRegulationDTO freightRegulationDTO = new FreightRegulationDTO();
			freightRegulationDTO.setFreightTemplateId(freightTemplateDTO.getId());
			List<FreightRegulationDTO> freightRegulationList = freightRegulationReadService.findFreightRegulationAll(freightRegulationDTO);

				for (int i = 0; i < freightRegulationList.size(); i++) {
					//第一次循环判断是否小于订单金额
					if(i == 0){
						if(freightRegulationList.get(i).getOrderMoney().doubleValue() > orderAmount){
							freightAmount = freightRegulationList.get(i).getFreightMoney().doubleValue();
						}
					}
					//判断是否大于前一次订单金额并且小于这一次循环金额
					else{
						if(freightRegulationList.get(i - 1).getOrderMoney().doubleValue() < orderAmount && orderAmount < freightRegulationList.get(i).getOrderMoney().doubleValue()){
							freightAmount = freightRegulationList.get(i).getFreightMoney().doubleValue();
						}
					}

				}
				//前面有赋值则用赋值的、如果没有则用默认值0
				return freightAmount;

			}else{
				//如果包邮则用默认值0
				return freightAmount;
			}
	}

	/**
	 * 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
	 *
	 * @param storeId
	 * @param goodsList
	 * @return
	 */
	public String isLimitBuy(Long storeId, List<LimitRuleRecordDTO> goodsList, Long companyId, Long platformId, Long memberId) {
		logger.info("进入限购校验");
		Map<Long, List<LimitRuleRecordDTO>> map = new HashMap<>();
		for (LimitRuleRecordDTO limitRuleRecordDTO : goodsList) {
			if(map.containsKey(limitRuleRecordDTO.getStandardUnitId())){
				List<LimitRuleRecordDTO> list = map.get(limitRuleRecordDTO.getStandardUnitId());
				list.add(limitRuleRecordDTO);
				map.put(limitRuleRecordDTO.getStandardUnitId(), list);
			}else{
				List<LimitRuleRecordDTO> list = new ArrayList<>();
				list.add(limitRuleRecordDTO);
				map.put(limitRuleRecordDTO.getStandardUnitId(), list);
			}
		}

		for (Map.Entry<Long, List<LimitRuleRecordDTO>> entry : map.entrySet()) {
			logger.info("限购校验suId="+entry.getKey());
				// 根据公司id查询公司类型对应的所有公司id
				Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
				List<Long> suCombinationIdList = com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.querySuCombinationBySuId(entry.getKey()));
			// 根据su商品id查询所有启用限购规则信息
				List<LimitRuleDTO> list = limitRuleReadService.startLimitRuleDTOByStandardUnitId(entry.getKey(), suCombinationIdList,platformId);

				// 获取当前su商品的总数量和总金额
				List<LimitRuleRecordDTO> value = entry.getValue();
				int num = 0;
				double priceSum = 0;
				for (LimitRuleRecordDTO limitRuleRecordDTO : value) {
					num = num + limitRuleRecordDTO.getBuySum().intValue();
					priceSum = priceSum + (limitRuleRecordDTO.getBuySum().doubleValue() * limitRuleRecordDTO.getBuyMoneySum().doubleValue());
				}
				for (LimitRuleDTO limitRuleDTO : list) {
					String limitRulejudge = limitRulejudge(entry.getKey(),map,limitRuleDTO,num,priceSum,memberId,platformId,companyId,storeId);
					// 返回不为空直接返回限购提示
					if(limitRulejudge != null)
						return limitRulejudge;
				}
			}
		return null;
	}

	/**
	 * 限购规则判断校验
	 *
	 *
	 * @param limitRuleRecordDTOS
	 * @param limitRuleDTO
	 * @param num 购买数量
	 * @param priceSum 购买总金额
	 * @param memberId 用户id
	 * @param platformId 平台id
	 * @param companyId
	 * @param storeId
	 * @return
	 */
	private String limitRulejudge(Long suId, Map<Long, List<LimitRuleRecordDTO>> limitRuleRecordDTOS, LimitRuleDTO limitRuleDTO, int num, double priceSum, Long memberId, Long platformId, Long companyId, Long storeId) {
		logger.info("进入每个规则校验");
		logger.info("限购的类型="+limitRuleDTO.getLimitUnit());
		logger.info("是否用户="+limitRuleDTO.getIsUserLimit());
		logger.info("是否公司="+limitRuleDTO.getIsCompanyLimit());
		logger.info("是否门店="+limitRuleDTO.getIsStoreLimit());
		logger.info("是否全部="+limitRuleDTO.getIsLimit());
		// 获取当前时间
		long millis = DateUtils.curTimeMillis();
		List<Long> idList = new ArrayList<>();
		for (Map.Entry<Long, List<LimitRuleRecordDTO>> entry : limitRuleRecordDTOS.entrySet()) {
			idList.add(entry.getKey());
		}

		LimitRuleRecordDTO limitRuleRecordDTO = new LimitRuleRecordDTO();
		BigDecimal userLimitMoneySum = BigDecimal.ZERO;
		Long userLimitNum = Long.valueOf(0);
		BigDecimal companyLimitMoneySum = BigDecimal.ZERO;
		Long companyLimitNum = Long.valueOf(0);
		BigDecimal storeLimitMoneySum = BigDecimal.ZERO;
		Long storeLimitNum = Long.valueOf(0);
		BigDecimal limitMoneySum = BigDecimal.ZERO;
		Long limitNum = Long.valueOf(0);

		if(EmptyUtil.isNotEmpty(limitRuleDTO.getUserMoneySum())){
			userLimitMoneySum = limitRuleDTO.getUserMoneySum();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getUserLimitNum())){
			userLimitNum = limitRuleDTO.getUserLimitNum();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getCompanyMoneySum())){
			companyLimitMoneySum = limitRuleDTO.getCompanyMoneySum();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getCompanyLimitNum())){
			companyLimitNum = limitRuleDTO.getCompanyLimitNum();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getStoreMoneySum())){
			storeLimitMoneySum = limitRuleDTO.getStoreMoneySum();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getStoreLimitNum())) {
			storeLimitNum = limitRuleDTO.getStoreLimitNum();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getSuLimitAmount())){
			limitMoneySum = limitRuleDTO.getSuLimitAmount();
		}
		if(EmptyUtil.isNotEmpty(limitRuleDTO.getSuLimitNum())){
			limitNum = limitRuleDTO.getSuLimitNum();

		}
		logger.info("userLimitNum="+userLimitNum);
		logger.info("userLimitMoneySum="+userLimitMoneySum);
		logger.info("companyLimitNum="+companyLimitNum);
		logger.info("companyLimitMoneySum="+companyLimitMoneySum);
		logger.info("storeLimitNum="+storeLimitNum);
		logger.info("storeLimitMoneySum="+storeLimitMoneySum);
		logger.info("limitNum="+limitNum);
		logger.info("limitMoneySum="+limitMoneySum);
		//1.按用户限购
		LimitRuleUserDTO limitRuleUserDTO = new LimitRuleUserDTO();
		limitRuleUserDTO.setLimitRuleId(limitRuleDTO.getId());
		limitRuleUserDTO.setCompanyId(companyId);
		logger.info("companyId="+companyId);
		logger.info("limitId="+limitRuleDTO.getId());

		Integer userCount=limitRuleUserReadService.findLimitRuleAllByParam(limitRuleUserDTO);
		logger.info("userCount="+userCount);
		if((limitRuleDTO.getLimitUnit().indexOf("1")!=-1)&&limitRuleDTO.getIsUserLimit()!=0&&userCount>0){
			logger.info("用户限量");
				if(limitRuleDTO.getLimitTimeType()==1){
					//按时间段限购
					// 判断当前时间是否在限购规则时间内
					if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
						Long nowNum=Long.valueOf(num);
						double nowBuySum = priceSum;
						if(limitRuleDTO.getLimitTarget().equals(1)){
							//单商品限购
							limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, memberId, null, null, null);


						}else{
							//商品组
							//根据商品组id查询所有商品id
							List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
							limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(),null, suIdList,memberId, null, null, null);
							for(Long id:idList){
								if(id.equals(suId)){
									continue;
								}
								if(suIdList.contains(id)){
									List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
									for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
										nowNum+=dto.getBuySum();
										nowBuySum += (dto.getBuyMoneySum().doubleValue());
									}
								}
							}

						}
						//得到以前购买的数量
						Long buySum =0L;
						BigDecimal buyMoneySum = BigDecimal.ZERO;
						if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)){
							buySum=limitRuleRecordDTO.getBuySum();
							logger.info("用户时间段:buyNum="+buySum);
							buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
							logger.info("用户时间段:buyMoneySum ="+buyMoneySum );

						}
						if(userLimitNum>0&&(buySum+nowNum)>userLimitNum){
							return limitBuySumError;
						}
						if(userLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(userLimitMoneySum)>0){
							return limitBuyMoneySumError;
						}
					}
				}else{
					Long nowNum=Long.valueOf(num);
					double nowBuySum = priceSum;
					//按周期限购
					if(limitRuleDTO.getLimitTarget()==1){
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, memberId, null, null, limitRuleDTO.getPeriodType());

					}else{
						//商品组
						//根据商品组id查询所有商品id
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, suIdList, memberId, null, null, limitRuleDTO.getPeriodType());
						for(Long id:idList){
							if(id.equals(suId)){
								continue;
							}
							if(suIdList.contains(id)){
								List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
								for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
									nowNum+=dto.getBuySum();
									nowBuySum += (dto.getBuyMoneySum().doubleValue());
									logger.info("nowNum="+nowNum);
									logger.info("nowBuySum="+nowBuySum);
								}
							}
						}
						logger.info("nowNum="+nowNum);
						logger.info("nowBuySum="+nowBuySum);
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
						logger.info("用户时间周期:buyNum="+buySum);
						logger.info("用户时间周期:buyMoneySum ="+buyMoneySum );

					}
					if (userLimitNum>0&&(buySum + nowNum) > userLimitNum) {
						return limitBuySumError;
					}
					if (userLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(userLimitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}



		}
		//2.按企业限购
		LimitRuleCompanyDTO limitRuleCompanyDTO = new LimitRuleCompanyDTO();
		limitRuleCompanyDTO.setLimitRuleId(limitRuleDTO.getId());
		limitRuleCompanyDTO.setCompanyId(companyId);
		Integer count=limitRuleCompanyReadService.findLimitRuleCompanyCount(limitRuleCompanyDTO);
		logger.info("companyCount="+count);
		if(count>0){
			if((limitRuleDTO.getLimitUnit().indexOf("2")!=-1)&&limitRuleDTO.getIsCompanyLimit()!=0){
				logger.info("企业限量");
				if(limitRuleDTO.getLimitTimeType()==1){
					Long nowNum=Long.valueOf(num);
					double nowBuySum = priceSum;
					//按时间段限购
					// 判断当前时间是否在限购规则时间内
					if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
						if (limitRuleDTO.getLimitTarget().equals(1)) {
							//单商品限购
							limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null, companyId, null);

						} else {
							//商品组
							//根据商品组id查询所有商品id
							List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
							limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null,suIdList, null, null, companyId, null);
							for(Long id:idList){
								if(id.equals(suId)){
									continue;
								}
								if(suIdList.contains(id)){
									List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
									for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
										nowNum+=dto.getBuySum();
										nowBuySum += (dto.getBuyMoneySum().doubleValue());
									}
								}
							}
						}
						//得到以前购买的数量
						Long buySum =0L;
						BigDecimal buyMoneySum = BigDecimal.ZERO;
						logger.info("limitRuleRecordDto="+limitRuleRecordDTO);
						if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
							buySum = limitRuleRecordDTO.getBuySum();
							buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
							logger.info("用户时间段:buyNum="+buySum);
							logger.info("用户时间段:buyMoneySum ="+buyMoneySum );

						}
						if (companyLimitNum>0&&(buySum + nowNum) > companyLimitNum) {
							return limitBuySumError;
						}
						if (companyLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(companyLimitMoneySum) > 0) {
							return limitBuyMoneySumError;
						}
					}
				}else{
					//按周期限购
					Long nowNum=Long.valueOf(num);
					double nowBuySum = priceSum;
					if(limitRuleDTO.getLimitTarget()==1){
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null, companyId, limitRuleDTO.getPeriodType());

					}else{
						//商品组
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(),null, suIdList, null, null, companyId, limitRuleDTO.getPeriodType());
						for(Long id:idList){
							if(id.equals(suId)){
								continue;
							}
							if(suIdList.contains(id)){
								List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
								for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
									nowNum+=dto.getBuySum();
									nowBuySum += (dto.getBuyMoneySum().doubleValue());
								}
							}
						}
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					logger.info("limitRuleRecordDto="+limitRuleRecordDTO);
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
						logger.info("用户时间周期:buyNum="+buySum);
						logger.info("用户时间周期:buyMoneySum ="+buyMoneySum );
					}
					if (companyLimitNum>0&&(buySum + nowNum) > companyLimitNum) {
						return limitBuySumError;
					}
					if (companyLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(companyLimitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}
			}
		}

		LimitRuleStoreDTO limitRuleStoreDTO = new LimitRuleStoreDTO();
		limitRuleStoreDTO.setLimitRuleId(limitRuleDTO.getId());
		limitRuleStoreDTO.setStoreId(storeId);
		Integer storeCount=limitRuleStoreReadService.findLimitRuleStoreCount(limitRuleStoreDTO);
		logger.info("storeCount="+storeCount);
		//3.按门店限购
		if((limitRuleDTO.getLimitUnit().indexOf("3")!=-1)&&limitRuleDTO.getIsStoreLimit()!=0&&storeCount>0){
			logger.info("门店限量");

			if(limitRuleDTO.getLimitTimeType()==1){
				//按时间段限购
				Long nowNum=Long.valueOf(num);
				double nowBuySum = priceSum;
				// 判断当前时间是否在限购规则时间内
				if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
					if (limitRuleDTO.getLimitTarget().equals(1)) {
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, storeId,null, null);

					} else {
						//商品组
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, suIdList, null, storeId,null, null);
						for(Long id:idList){
							if(id.equals(suId)){
								continue;
							}
							if(suIdList.contains(id)){
								List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
								for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
									nowNum+=dto.getBuySum();
									nowBuySum += (dto.getBuyMoneySum().doubleValue());
								}
							}
						}
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
						logger.info("用户时间段:buyNum="+buySum);
						logger.info("用户时间段:buyMoneySum ="+buyMoneySum );
					}
					if (storeLimitNum>0&&(buySum + nowNum) > limitNum) {
						return limitBuySumError;
					}
					if (storeLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(storeLimitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}
			}else{
				//按周期限购
				Long nowNum=Long.valueOf(num);
				double nowBuySum = priceSum;
				if(limitRuleDTO.getLimitTarget()==1){
					//单商品限购
					//单商品限购
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, storeId,null, limitRuleDTO.getPeriodType());

				}else{
					//商品组
					List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null,suIdList, null, storeId,null, limitRuleDTO.getPeriodType());
					for(Long id:idList){
						if(id.equals(suId)){
							continue;
						}
						if(suIdList.contains(id)){
							List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
							for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
								nowNum+=dto.getBuySum();
								nowBuySum += (dto.getBuyMoneySum().doubleValue());
							}
						}
					}
				}
				//得到以前购买的数量
				Long buySum =0L;
				BigDecimal buyMoneySum = BigDecimal.ZERO;
				if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
					buySum = limitRuleRecordDTO.getBuySum();
					buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
					logger.info("用户时间周期:buyNum="+buySum);
					logger.info("用户时间周期:buyMoneySum ="+buyMoneySum );
				}
				if (storeLimitNum>0&&(buySum + nowNum) > storeLimitNum) {
					return limitBuySumError;
				}
				if (storeLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(storeLimitMoneySum) > 0) {
					return limitBuyMoneySumError;
				}
			}
		}
		//4.按总数量/总金额限购
		if(limitRuleDTO.getIsLimit()!=0){
			logger.info("总限量");
			if(limitRuleDTO.getLimitTimeType()==1){
				Long nowNum=Long.valueOf(num);
				double nowBuySum = priceSum;
				//按时间段限购
				// 判断当前时间是否在限购规则时间内
				if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
					if (limitRuleDTO.getLimitTarget().equals(1)) {
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null,null, null);

					} else {
						//商品组
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, suIdList, null, null,null, null);
						for(Long id:idList){
							if(id.equals(suId)){
								continue;
							}
							if(suIdList.contains(id)){
								List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
								for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
									nowNum+=dto.getBuySum();
									nowBuySum += (dto.getBuyMoneySum().doubleValue());
								}
							}
						}
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
						logger.info("用户时间段:buyNum="+buySum);
						logger.info("用户时间段:buyMoneySum ="+buyMoneySum );
					}
					if (limitNum>0&&(buySum + nowNum) > limitNum) {
						return limitBuySumError;
					}
					if (limitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(limitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}
			}else{
				//按周期限购
				Long nowNum=Long.valueOf(num);
				double nowBuySum = priceSum;
				if(limitRuleDTO.getLimitTarget()==1){
					//单商品限购
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null,null, limitRuleDTO.getPeriodType());

				}else{
					//商品组
					List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, suIdList, null, null,null, limitRuleDTO.getPeriodType());
					for(Long id:idList){
						if(id.equals(suId)){
							continue;
						}
						if(suIdList.contains(id)){
							List<LimitRuleRecordDTO> limitRuleRecordDTOS1 = limitRuleRecordDTOS.get(id);
							for(LimitRuleRecordDTO dto:limitRuleRecordDTOS1){
								nowNum+=dto.getBuySum();
								nowBuySum += (dto.getBuyMoneySum().doubleValue());
							}
						}
					}
				}
				//得到以前购买的数量
				Long buySum =0L;
				BigDecimal buyMoneySum = BigDecimal.ZERO;
				if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
					buySum = limitRuleRecordDTO.getBuySum();
					buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
					logger.info("用户时间周期:buyNum="+buySum);
					logger.info("用户时间周期:buyMoneySum ="+buyMoneySum );
				}
				if (limitNum>0&&(buySum + nowNum) > limitNum) {
					return limitBuySumError;
				}
				if (limitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(nowBuySum))).compareTo(limitMoneySum) > 0) {
					return limitBuyMoneySumError;
				}
			}
		}



		// 按时间段限购
		/*if(limitRuleDTO.getLimitTimeType() == 1){

			// 获取当前时间
			long millis = DateUtils.curTimeMillis();
			// 判断当前时间是否在限购规则时间内
			if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()){

				// 根据用户id和限购规则id平台id查询限购规则记录
				List<LimitRuleRecordDTO> limitRuleRecordList = limitRuleRecordReadService.findByUserIdL imitRuleId(limitRuleDTO.getId(), memberId, platformId);
				// 累加之前买的数量和金额
				int allNum = 0;
				for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
					allNum = num + limitRuleRecordDTO.getBuySum().intValue();
					priceSum += limitRuleRecordDTO.getBuyMoneySum().doubleValue();
				}

				// 判断是否超过限购数量，超过给出提示
				if(limitRuleDTO.getLimitType() == 1){
					//是否超过个人限购数量
					if(limitRuleDTO.getUserLimitNum().intValue() < allNum ) {
						return "超过限购数量，无法购买";
					}
					//是否超过限购总数量
					//根据限购规则id平台id查询限购规则记录中购买数量
					Long buySum = limitRuleRecordReadService.findBuySumByLimitRuleIdPlatformId(limitRuleDTO.getId(), platformId);
					if (buySum != null && buySum != 0L){
						if (limitRuleDTO.getSuLimitNum() < buySum+num) {
							return "超过限购数量，无法购买";
						}
					}
				}

				// 判断是否超过限购金额，超过给出提示
				if(limitRuleDTO.getLimitType() == 2)
					if(limitRuleDTO.getUserMoneySum().doubleValue() < priceSum)
						return "超过限购总金额，无法购买";
			}
		}

		// 按周期限购
		if(limitRuleDTO.getLimitTimeType() == 2){
			// 根据自然年、月、日类型，用户id，限购规则id，平台id查询限购规则记录信息
			List<LimitRuleRecordDTO> limitRuleRecordList = limitRuleRecordReadService.findByPeriodTypeUserIdLimitRuleId(limitRuleDTO.getPeriodType(), memberId,limitRuleDTO.getId(), platformId);

			// 累加之前买的数量和金额
			int allNum = 0;
			for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
				allNum = num + limitRuleRecordDTO.getBuySum().intValue();
				priceSum += limitRuleRecordDTO.getBuyMoneySum().doubleValue();
			}

			// 判断是否超过限购数量，超过给出提示
			if(limitRuleDTO.getLimitType() == 1){
				if(limitRuleDTO.getUserLimitNum().intValue() < allNum){
					return "超过限购数量，无法购买";
				}
				//是否超过限购总数量
				//根据限购规则id平台id查询限购规则记录中购买数量
				Long buySum = limitRuleRecordReadService.findBuySumByLimitRuleIdPlatformId(limitRuleDTO.getId(), platformId);
				if (buySum != null && buySum != 0L){
					if (limitRuleDTO.getSuLimitNum() < buySum+num) {
						return "超过限购数量，无法购买";
					}
				}
			}

			// 判断是否超过限购金额，超过给出提示
			if(limitRuleDTO.getLimitType() == 2)
				if(limitRuleDTO.getUserMoneySum().doubleValue() < priceSum)
					return "超过限购总金额，无法购买";
		}*/
		return null;
	}

	/**
	 * 通过puid查询commodityTemplateId
	 * @param puId
	 * @return
	 */
	public Long queryCommodityTemplateIdByPuId(Long puId) {

		return puReadService.queryCommodityTemplateIdByPuId(puId);
	}
	/**
	 * 根据用户id查询公司类型
	 * @param
	 * @return
	 */
	public Integer findCompanyTypeByUserId(Long userId){
		return companyCoreReadService.findCompanyTypeByUserId(userId);
	}

    public StandardUnitDTO findStandardUnit(Long standardUnitId) {
		return standardUnitReadService.findStandardUnitById(standardUnitId);
	}

	public List<StandardUnitStoreDTO> findStandUnitStore(Long standardUnitId, Long storeId) {
		StandardUnitStoreDTO standardUnitStoreDTO = new StandardUnitStoreDTO();
		standardUnitStoreDTO.setStandardUnitId(standardUnitId);
		standardUnitStoreDTO.setStoreId(storeId);
		return standardUnitStoreReadService.findStandardUnitStoreAll(standardUnitStoreDTO);
	}

	public CommodityProductUnitDTO findCommodityProductUnit(Long productUnitId) {
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setId(productUnitId);
		return commodityProductUnitReadService.findCommodityProductUnitById(commodityProductUnitDTO);
	}

	public boolean queryIsCard(Long puId) {
		return puReadService.queryIsCard(puId);
	}

    public SkuDTO findSkuBySkuId(Long skuId) {
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setId(skuId);
		return skuReadService.findSkuById(skuDTO);
	}

	//校验京东sku的有效性(2.京东的上下架状态,3.同步状态)
	public JdProductDTO checkJdProductStatus(String skuId){
		JdProductVO2 jdProductDTO = new JdProductVO2();
		jdProductDTO.setId(Long.valueOf(skuId));
		JdProductDTO jdProductById = jdProductReadService.findJdProductById2(jdProductDTO);
		return jdProductById;

	}

	//校验京东sku的有效性(2.京东的上下架状态,3.同步状态)
	public JdProductDTO checkJdProductStatus(String skuId,Long enterpriseId){
		JdProductVO2 jdProductDTO = new JdProductVO2();
		jdProductDTO.setId(Long.valueOf(skuId));
		jdProductDTO.setEnterpriseId(enterpriseId);
		JdProductDTO jdProductById = jdProductReadService.findJdProductById2(jdProductDTO);
		return jdProductById;

	}

	public String getJdCategoryName(Long jdCategoryId){
		return jdProductReadService.getJdCategoryName(jdCategoryId);
	}

	/**获取到商品信息**/
	public CakeProductDetailDTO getCakeProduct(String productId,String skuId,String cityName,String cityId){
		return getCakeProduct(productId,skuId,cityName,cityId,null);
	}

	/**获取到商品信息**/
	public CakeProductDetailDTO getCakeProduct(String productId,String skuId,String cityName,String cityId,Long enterpriseId){
		CakeSPUIdSearchProductDetailDTO dto = new CakeSPUIdSearchProductDetailDTO();
		dto.setSpuId(productId);
		dto.setSkuId(skuId);
		dto.setCityName(cityName);
		dto.setCityId(cityId);
		dto.setEnterpriseId(enterpriseId);
		CakeProductDetailDTO rtDTO = getRedisCache(dto);
		if(rtDTO !=null){
			return rtDTO;
		}
		rtDTO = cakeProductReadService.findCakeProductBySkuId(dto);
		setRedisCacheProduct(dto,rtDTO);
		return rtDTO;
	}
	Gson gson = new Gson();
	private CakeProductDetailDTO getRedisCache(CakeSPUIdSearchProductDetailDTO dto){
		try {
			if(EmptyUtil.isNotEmpty(dto.getCityName())){
				//查询某城市是否售卖就不能查缓存中的了
				return null;
			}
			String redisKey = getCakeProductRedisKey(dto);
			Object obj = jedisUtil.get(redisKey);
			if(Objects.isNull(obj)){
				return null;
			}

			String cakeProductString = (String)jedisUtil.get(redisKey);
			return gson.fromJson(cakeProductString,CakeProductDetailDTO.class);
		}catch (Exception e){
			logger.error("{}获取缓存中的商品发生异常:{}",dto.getSpuId(),e);
			return null;
		}
	}
	private String getCakeProductRedisKey(CakeSPUIdSearchProductDetailDTO dto){
		OrderRedisKeyEnum orderRedisKeyEnum = OrderRedisKeyEnum.CAKE_PRODUCT_ID_KEY;
		StringBuffer sb = new StringBuffer();
		sb.append(orderRedisKeyEnum.getRedisKey());
		sb.append(dto.getSpuId());
		sb.append(dto.getEnterpriseId());
		String redisKey = sb.toString();
		return redisKey;
	}

	private void setRedisCacheProduct(CakeSPUIdSearchProductDetailDTO dto,CakeProductDetailDTO rtDTO){
		try {
			if(Objects.isNull(rtDTO) || Objects.isNull(dto)){
				return;
			}
			OrderRedisKeyEnum orderRedisKeyEnum = OrderRedisKeyEnum.CAKE_PRODUCT_ID_KEY;
			String redisKey = getCakeProductRedisKey(dto);
			jedisUtil.set(redisKey, orderRedisKeyEnum.getExpireTime(), gson.toJson(rtDTO));
		}catch (Exception e){
			logger.error("存放商品:{}至缓存中失败，发生异常:{}",dto.getSpuId(),e);
		}
	}

	/**获取skuInfo**/
	public CakeProductDetailSpecsDTO getCakeProductSkuInfo(String productId,String skuId,String cityName,String cityId){
		CakeProductDetailDTO dto = getCakeProduct(productId,skuId,cityName,cityId);
		return getCakeProductSkuInfo(dto,skuId);
	}

	public CakeProductDetailSpecsDTO getCakeProductSkuInfo(CakeProductDetailDTO dto,String skuId){
		if(StringUtils.isEmpty(skuId)){
			return null;
		}
		if(Objects.isNull(dto)){
			return null;
		}
		List<CakeProductDetailSpecsDTO>  specsList = dto.getSpecs();
		if(CollectionUtils.isEmpty(specsList)){
			return null;
		}
		for (CakeProductDetailSpecsDTO cakeProductDetailSpecsDTO : specsList) {
			if(Objects.equals(cakeProductDetailSpecsDTO.getId(),skuId)){
				return cakeProductDetailSpecsDTO;
			}
		}
		return null;
	}
	public ChannelProductDetailVO findWorldProduct(String productId,String skuId){
		return findWorldProduct(productId,skuId,null);
	}

	public ChannelProductDetailVO findWorldProduct(String productId,String skuId,Long enterpriseId){
		ChannelProductDetailRequestVO vo = new ChannelProductDetailRequestVO();
		vo.setChannelCode("worldBuy");
		vo.setProductId(productId);
		vo.setSkuId(skuId);
		vo.setEnterpriseId(enterpriseId);
		return worldBuyProductClient.findWorldProductBySkuId(vo);
	}

	public ChannelProductBatchDTO getCurrBatch(String skuId, List<ChannelProductBatchDTO> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		for (ChannelProductBatchDTO batchDTO : list) {
			if(String.valueOf(batchDTO.getId()).equals(skuId)){
				return batchDTO;
			}
		}
		return null;
	}

	public ChannelProductSkuDTO getCurrSkuInfo(String skuId, List<ChannelProductSkuDTO> skuList){
		if(CollectionUtils.isEmpty(skuList)){
			return null;
		}
		for (ChannelProductSkuDTO skuDTO : skuList) {
			if(String.valueOf(skuDTO.getExternalSkuId()).equals(skuId)){
				return skuDTO;
			}
		}
		return null;
	}
}
