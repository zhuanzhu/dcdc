package com.egeo.components.promotion.facade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.client.LimitRuleClient;
import com.egeo.components.order.client.LimitRuleRecordClient;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.FreightRegulationClient;
import com.egeo.components.product.client.FreightTemplateClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.client.StandardProductUnitAttValueClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.exception.BusinessException;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;

@Component
public class ProductFacade {

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
	
	@Autowired
	private LimitRuleClient limitRuleReadService;
	
	@Autowired
	private LimitRuleRecordClient limitRuleRecordReadService;
	
	@Autowired
	private StandardProductUnitAttValueClient standardProductUnitAttValueReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	@Autowired
	private StandardUnitClient standardUnitReadService;

	private String limitBuyMoneySumError = "超过限购总金额,无法购买!";
	private String limitBuySumError = "超过限购数量,无法购买!";
	/**
	 * 根据puId查询单个pu
	 * @param puId
	 * @return
	 */
	public CommodityProductUnitDTO queryPuById(Long puId) {
		return puReadService.findSUSPUByPUId(puId);
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
	 * @param puId
	 * @return
	 */
	public Long queryUnitStockBySkuId(Long skuId) {
		return unitStockReadService.queryUnitStockBySkuId(skuId);
	}

	/**
	 * 根据skuId查询商品的规格
	 * @param puId
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
	public double freightAmountByMerchantId(double orderAmount) {
		//运费
		double freightAmount = 0d;
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		//根据商家id查询商家启用模版(现阶段写死、730运营方写活)
		freightTemplateDTO.setMerchantId(1L);
		freightTemplateDTO.setIsValid(1);
		List<FreightTemplateDTO> list = freightTemplateReadService.findFreightTemplateAll(freightTemplateDTO);
		if(list.size() == 1){
			freightTemplateDTO = list.get(0);
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
			}else{
				throw new BusinessException("查询商家启用模版异常，商家id：" + freightTemplateDTO.getMerchantId());
			}
	}
	/**
	 * 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
	 * @param goodsList
	 * @return
	 *//*
	public String isLimitBuy(List<LimitRuleRecordDTO> goodsList,Long companyId,Long platformId,Long memberId) {
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
				// 根据公司id查询公司类型
				Integer companyType = companyCoreReadService.findCompanyTypeById(companyId);
				Long companyAllId = -1L;
				// 公司类型：0:正式公司(默认) 1:演示公司 2:竞品公司
				switch (companyType) {
				case 0:
					companyAllId = -1L;
					break;
				case 1:
					companyAllId = -3L;
					break;
				case 2:
					companyAllId = -2L;
					break;
	
				default:
					throw new BusinessException("未定义公司类型");
				}
			List<Long> suCombinationIdList = standardUnitReadService.querySuCombinationBySuId(entry.getKey());
			// 根据su商品id查询所有启用限购规则信息
				List<LimitRuleDTO> list = limitRuleReadService.startLimitRuleDTOByStandardUnitId(entry.getKey(), companyId,companyAllId, platformId);
				
				// 获取当前su商品的总数量和总金额
				List<LimitRuleRecordDTO> value = entry.getValue();
				int num = 0;
				double priceSum = 0;
				for (LimitRuleRecordDTO limitRuleRecordDTO : value) {
					num = num + limitRuleRecordDTO.getBuySum().intValue();
					priceSum = priceSum + (limitRuleRecordDTO.getBuySum().doubleValue() * limitRuleRecordDTO.getBuyMoneySum().doubleValue());
				}
				for (LimitRuleDTO limitRuleDTO : list) {
					String limitRulejudge = limitRulejudge(limitRuleDTO,num,priceSum,memberId,platformId,companyId,);
					// 返回不为空直接返回限购提示
					if(limitRulejudge != null)
						return limitRulejudge;
				}
			}
		return null;
	}*/
	
	/**
	 * 是否超过限购判断
	 * @param limitRuleDTO
	 * @param num 购买数量
	 * @param priceSum 购买总金额
	 * @param memberId 用户id
	 * @param platformId 平台id
	 * @return
	 */
	private String limitRulejudge(LimitRuleDTO limitRuleDTO,int num,double priceSum,Long memberId,Long platformId,Long companyId,Long storeId) {
		// 获取当前时间
		long millis = DateUtils.curTimeMillis();

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
		//1.按用户限购
		if((limitRuleDTO.getLimitUnit().indexOf("1")!=-1)&&limitRuleDTO.getIsUserLimit()!=0){
			if(limitRuleDTO.getLimitTimeType()==1){
				//按时间段限购
				// 判断当前时间是否在限购规则时间内
				if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
					if(limitRuleDTO.getLimitTarget().equals(1)){
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, memberId, null, null, null);


					}else{
						//商品组
						//根据商品组id查询所有商品id
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(),null, com.egeo.utils.StringUtils.longsToStrings(suIdList),memberId, null, null, null);
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)){
						buySum=limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();

					}
					if(userLimitNum>0&&(buySum+num)>userLimitNum){
						return limitBuySumError;
					}
					if(userLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(userLimitMoneySum)>0){
						return limitBuyMoneySumError;
					}
				}
			}else{
				//按周期限购
				if(limitRuleDTO.getLimitTarget()==1){
					//单商品限购
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, memberId, null, null, limitRuleDTO.getPeriodType());

				}else{
					//商品组
					//根据商品组id查询所有商品id
					List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, com.egeo.utils.StringUtils.longsToStrings(suIdList), memberId, null, null, limitRuleDTO.getPeriodType());
				}
				//得到以前购买的数量
				Long buySum =0L;
				BigDecimal buyMoneySum = BigDecimal.ZERO;
				if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
					buySum = limitRuleRecordDTO.getBuySum();
					buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
				}
				if (userLimitNum>0&&(buySum + num) > userLimitNum) {
					return limitBuySumError;
				}
				if (userLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(userLimitMoneySum) > 0) {
					return limitBuyMoneySumError;
				}
			}

		}
		//2.按企业限购
		if((limitRuleDTO.getLimitUnit().indexOf("2")!=-1)&&limitRuleDTO.getIsCompanyLimit()!=0){
			if(limitRuleDTO.getLimitTimeType()==1){
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
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null,com.egeo.utils.StringUtils.longsToStrings(suIdList), null, null, companyId, null);
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
					}
					if (companyLimitNum>0&&(buySum + num) > companyLimitNum) {
						return limitBuySumError;
					}
					if (companyLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(companyLimitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}
			}else{
				//按周期限购
				if(limitRuleDTO.getLimitTarget()==1){
					//单商品限购
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null, companyId, limitRuleDTO.getPeriodType());

				}else{
					//商品组
					List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(),null, com.egeo.utils.StringUtils.longsToStrings(suIdList), null, null, companyId, limitRuleDTO.getPeriodType());
				}
				//得到以前购买的数量
				Long buySum =0L;
				BigDecimal buyMoneySum = BigDecimal.ZERO;
				if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
					buySum = limitRuleRecordDTO.getBuySum();
					buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
				}
				if (companyLimitNum>0&&(buySum + num) > companyLimitNum) {
					return limitBuySumError;
				}
				if (companyLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(companyLimitMoneySum) > 0) {
					return limitBuyMoneySumError;
				}
			}
		}
		//3.按门店限购
		if((limitRuleDTO.getLimitUnit().indexOf("3")!=-1)&&limitRuleDTO.getIsStoreLimit()!=0){
			if(limitRuleDTO.getLimitTimeType()==1){
				//按时间段限购
				// 判断当前时间是否在限购规则时间内
				if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
					if (limitRuleDTO.getLimitTarget().equals(1)) {
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, storeId,null, null);

					} else {
						//商品组
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, com.egeo.utils.StringUtils.longsToStrings(suIdList), null, storeId,null, null);
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
					}
					if (storeLimitNum>0&&(buySum + num) > storeLimitNum) {
						return limitBuySumError;
					}
					if (storeLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(storeLimitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}
			}else{
				//按周期限购
				if(limitRuleDTO.getLimitTarget()==1){
					//单商品限购
					//单商品限购
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, storeId,null, limitRuleDTO.getPeriodType());

				}else{
					//商品组
					List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null,com.egeo.utils.StringUtils.longsToStrings(suIdList), null, storeId,null, limitRuleDTO.getPeriodType());
				}
				//得到以前购买的数量
				Long buySum =0L;
				BigDecimal buyMoneySum = BigDecimal.ZERO;
				if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
					buySum = limitRuleRecordDTO.getBuySum();
					buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
				}
				if (storeLimitNum>0&&(buySum + num) > storeLimitNum) {
					return limitBuySumError;
				}
				if (storeLimitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(storeLimitMoneySum) > 0) {
					return limitBuyMoneySumError;
				}
			}
		}
		//4.按总数量/总金额限购
		if(limitRuleDTO.getIsLimit()!=0){
			if(limitRuleDTO.getLimitTimeType()==1){
				//按时间段限购
				// 判断当前时间是否在限购规则时间内
				if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()) {
					if (limitRuleDTO.getLimitTarget().equals(1)) {
						//单商品限购
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null,null, null);

					} else {
						//商品组
						List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
						limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, com.egeo.utils.StringUtils.longsToStrings(suIdList), null, null,null, null);
					}
					//得到以前购买的数量
					Long buySum =0L;
					BigDecimal buyMoneySum = BigDecimal.ZERO;
					if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
						buySum = limitRuleRecordDTO.getBuySum();
						buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
					}
					if (limitNum>0&&(buySum + num) > limitNum) {
						return limitBuySumError;
					}
					if (limitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(limitMoneySum) > 0) {
						return limitBuyMoneySumError;
					}
				}
			}else{
				//按周期限购
				if(limitRuleDTO.getLimitTarget()==1){
					//单商品限购
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), limitRuleDTO.getStandardUnitId(), null, null, null,null, limitRuleDTO.getPeriodType());

				}else{
					//商品组
					List<Long> suIdList=com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(limitRuleDTO.getSuCombId(),platformId));
					limitRuleRecordDTO = limitRuleRecordReadService.selectLimitStatistic(null,limitRuleDTO.getId(), null, com.egeo.utils.StringUtils.longsToStrings(suIdList), null, null,null, limitRuleDTO.getPeriodType());
				}
				//得到以前购买的数量
				Long buySum =0L;
				BigDecimal buyMoneySum = BigDecimal.ZERO;
				if(EmptyUtil.isNotEmpty(limitRuleRecordDTO)) {
					buySum = limitRuleRecordDTO.getBuySum();
					buyMoneySum = limitRuleRecordDTO.getBuyMoneySum();
				}
				if (limitNum>0&&(buySum + num) > limitNum) {
					return limitBuySumError;
				}
				if (limitMoneySum.compareTo(BigDecimal.ZERO)>0&&(buyMoneySum.add(BigDecimal.valueOf(priceSum))).compareTo(limitMoneySum) > 0) {
					return limitBuyMoneySumError;
				}
			}
		}

		/*// 按时间段限购
		if(limitRuleDTO.getLimitTimeType() == 1){

			// 获取当前时间
			long millis = DateUtils.curTimeMillis();
			// 判断当前时间是否在限购规则时间内
			if(limitRuleDTO.getLimitOriginTime().getTime() < millis && millis < limitRuleDTO.getLimitStopTime().getTime()){

				// 根据用户id和限购规则id平台id查询限购规则记录
				List<LimitRuleRecordDTO> limitRuleRecordList = limitRuleRecordReadService.findByUserIdLimitRuleId(limitRuleDTO.getId(), memberId, platformId);
				// 累加之前买的数量和金额
				for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
					num += limitRuleRecordDTO.getBuySum().intValue();
					priceSum += limitRuleRecordDTO.getBuyMoneySum().doubleValue();
				}

				// 判断是否超过限购数量，超过给出提示
				if(limitRuleDTO.getLimitType() == 1)
					if(limitRuleDTO.getSuLimitNum().intValue() < num)
						return "超过限购数量，无法购买";

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
			for (LimitRuleRecordDTO limitRuleRecordDTO : limitRuleRecordList) {
				num += limitRuleRecordDTO.getBuySum().intValue();
				priceSum += limitRuleRecordDTO.getBuyMoneySum().doubleValue();
			}

			// 判断是否超过限购数量，超过给出提示
			if(limitRuleDTO.getLimitType() == 1)
				if(limitRuleDTO.getSuLimitNum().intValue() < num)
					return "超过限购数量，无法购买";

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
	 * 根据skuid查询是否在app内使用
	 * @param skuId
	 * @return
	 */
	public boolean isAppUseBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadService.isAppUseBySkuId(skuId);
	}
	/**
	 * 根据skuId查询属性值Id
	 * @param skuId
	 * @return
	 */
	public Long findLinkAttValueIdBySkuId(Long skuId,Long attNameId) {
		return standardProductUnitAttValueReadService.findAttValueIdBySkuIdAndAttNameId(skuId,attNameId);
	}
	/**
	 * 根据用户id查询公司类型
	 * @param companyId
	 * @return
	 */
	public Integer findCompanyTypeByUserId(Long userId){
		return companyCoreReadService.findCompanyTypeByUserId(userId);
	}
	
}
