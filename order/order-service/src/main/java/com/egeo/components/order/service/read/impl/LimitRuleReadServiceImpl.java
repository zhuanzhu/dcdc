package com.egeo.components.order.service.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.common.DateUtils;
import com.egeo.components.order.converter.LimitRuleConverter;
import com.egeo.components.order.converter.LimitRuleRecordConverter;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.manage.read.LimitRuleCompanyReadManage;
import com.egeo.components.order.manage.read.LimitRuleReadManage;
import com.egeo.components.order.manage.read.LimitRuleStoreReadManage;
import com.egeo.components.order.manage.read.LimitRuleUserReadManage;
import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.components.order.po.LimitRulePO;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.components.order.po.LimitRuleStorePO;
import com.egeo.components.order.po.LimitRuleUserPO;
import com.egeo.components.order.service.read.LimitRuleReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service("limitRuleReadService")
public class LimitRuleReadServiceImpl  implements LimitRuleReadService {
	private static final XLogger logger = XLogger.getLogger(LimitRuleReadServiceImpl.class);
	@Autowired
	private LimitRuleReadManage limitRuleReadManage;
	@Autowired
	private LimitRuleCompanyReadManage limitRuleCompanyReadManage;
	@Autowired
	private LimitRuleUserReadManage limitRuleUserReadManage;
	@Autowired
	private LimitRuleStoreReadManage limitRuleStoreReadManage;

	@Override
	public LimitRuleDTO findLimitRuleById(LimitRuleDTO dto) {
		LimitRulePO po = LimitRuleConverter.toPO(dto);
		LimitRulePO list = limitRuleReadManage.findLimitRuleById(po);
		return LimitRuleConverter.toDTO(list);
	}

	@Override
	public PageResult<LimitRuleDTO> findLimitRuleOfPage(LimitRuleDTO dto, Pagination page) {
		LimitRulePO po = LimitRuleConverter.toPO(dto);
        PageResult<LimitRulePO> pageResult = limitRuleReadManage.findLimitRuleOfPage(po, page);

        List<LimitRuleDTO> list = LimitRuleConverter.toDTO(pageResult.getList());
        PageResult<LimitRuleDTO> result = new PageResult<LimitRuleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LimitRuleDTO> findLimitRuleAll(LimitRuleDTO dto) {
		LimitRulePO po = LimitRuleConverter.toPO(dto);
		List<LimitRulePO> list = limitRuleReadManage.findLimitRuleAll(po);
		return LimitRuleConverter.toDTO(list);
	}
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	@Override
	public List<String> startLimitRuleByStandardUnitId(Long standardUnitId, Long companyId,Long companyAllId, Long platformId, Long userId,Long storeId, Map<Long, List<Long>> suCombMap) {
		//List<Long> suCombIdList = new ArrayList<>(suCombMap.keySet());
		List<Long> suCombIdList = null;
		if(EmptyUtil.isNotEmpty(suCombMap)){
			suCombIdList = new ArrayList<>(suCombMap.keySet());
		}
		logger.info("限购规则显示:standardUnitId="+standardUnitId);
		List<LimitRulePO> limitRuleList = limitRuleReadManage.startLimitRuleByStandardUnitId(standardUnitId, suCombIdList, platformId);
		List<String> list = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(limitRuleList)){
			list = limitRuleJointString(limitRuleList, suCombMap, userId, companyId, companyAllId, storeId);
		}
		return list;
	}

	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param platformId
	 * @return
	 */
	@Override
	public List<LimitRuleDTO> startLimitRuleDTOByStandardUnitId(Long standardUnitId, List<Long> suCombIdList, Long platformId) {
		List<LimitRulePO> limitRuleList = limitRuleReadManage.startLimitRuleByStandardUnitId(standardUnitId, suCombIdList, platformId);
		return LimitRuleConverter.toDTO(limitRuleList);

	}

	private List<String> limitRuleJointString(List<LimitRulePO> limitRuleList, Map<Long, List<Long>> suCombMap, Long userId, Long companyId, Long companyAllId, Long storeId) {
		List<String> limitStrList = new ArrayList<>();
		Long limitNum = null;
		/*Long userLimitNum = null;
		Long companyLimitNum = null;
		Long storeLimitNum = null;*/
		BigDecimal limitAmount = null;
		/*BigDecimal userLimitAmount = null;
		BigDecimal companyLimitAmount = null;
		BigDecimal storeLimitAmount = null;*/
		logger.info("显示限购数量");
		for (LimitRulePO limitRule : limitRuleList) {
			long millis = DateUtils.curTimeMillis();
			if (limitRule.getLimitTimeType() == 1
					&& !(limitRule.getLimitOriginTime().getTime() < millis && millis < limitRule.getLimitStopTime().getTime())) {
				continue;
			}
			//判断总量
			if (limitRule.getIsLimit() == null || limitRule.getIsLimit() > 1) {
				logger.info("总数量限制");
				LimitRuleRecordPO record = null;
				Integer periodType = null;
				if (limitRule.getLimitTimeType() == 2) {
					periodType = limitRule.getPeriodType();
				}
				if (EmptyUtil.isNotEmpty(limitRule.getStandardUnitId())) {
					record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), limitRule.getStandardUnitId(), null, null, null, null, periodType);
				} else if (EmptyUtil.isNotEmpty(limitRule.getSuCombId())) {
					record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), null, suCombMap.get(limitRule.getSuCombId()), null, null, null, periodType);
				}
				if (record == null) {
					record = new LimitRuleRecordPO();
				}
				if (record.getBuySum() == null) {
					record.setBuySum(0L);
				}
				if (record.getBuyMoneySum() == null) {
					record.setBuyMoneySum(new BigDecimal(0));
				}
				if (limitRule.getSuLimitNum() != null && (limitNum == null || limitNum > limitRule.getSuLimitNum())) {
					limitNum = limitRule.getSuLimitNum() - record.getBuySum();
					logger.info("总数量限制="+limitNum);
				}
				if (limitRule.getSuLimitAmount() != null && (limitAmount == null || limitAmount.compareTo(limitRule.getSuLimitAmount()) == 1)) {
					limitAmount = limitRule.getSuLimitAmount().subtract(record.getBuyMoneySum());
					logger.info("总金额限制="+limitAmount);
				}
			}
			if (EmptyUtil.isEmpty(limitRule.getLimitUnit()) || limitRule.getLimitUnit().contains("0")) {
				continue;
			}
			//公司限购
			if (limitRule.getLimitUnit() != null && limitRule.getLimitUnit().contains("2")) {
				logger.info("公司限制");
				if ((limitRule.getIsCompanyLimit() == null || limitRule.getIsCompanyLimit() != 0) && fireCompanyLimitRule(limitRule.getId(), companyId, companyAllId)) {
					Integer periodType = null;
					if (limitRule.getLimitTimeType() == 2) {
						periodType = limitRule.getPeriodType();
					}
					LimitRuleRecordPO record = null;
					if (EmptyUtil.isNotEmpty(limitRule.getStandardUnitId())) {
						record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), limitRule.getStandardUnitId(), null, null, null, companyId, periodType);
					} else if (EmptyUtil.isNotEmpty(limitRule.getSuCombId())) {
						record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), null, suCombMap.get(limitRule.getSuCombId()), null, null, companyId, periodType);
					}
					if (record == null) {
						record = new LimitRuleRecordPO();
					}
					if (record.getBuySum() == null) {
						record.setBuySum(0L);
					}
					if (record.getBuyMoneySum() == null) {
						record.setBuyMoneySum(new BigDecimal(0));
					}
					if (limitRule.getCompanyLimitNum() != null) {
						if (limitNum == null || limitNum > (limitRule.getCompanyLimitNum() - record.getBuySum())) {
							limitNum = limitRule.getCompanyLimitNum() - record.getBuySum();
							logger.info("公司数量限制="+limitNum);
						}
					}
					if (limitRule.getCompanyMoneySum() != null) {
						if (limitAmount == null || limitAmount.compareTo(limitRule.getCompanyMoneySum().subtract(record.getBuyMoneySum())) == 1) {
							limitAmount = limitRule.getCompanyMoneySum().subtract(record.getBuyMoneySum());
							logger.info("公司金额限制="+limitAmount);
						}
					}
				}
			}

			//用户限购
			if (limitRule.getLimitUnit() != null && limitRule.getLimitUnit().contains("1")) {
				logger.info("用户限制");
				if ((limitRule.getIsUserLimit() == null || limitRule.getIsUserLimit() != 0) && fireUserLimitRule(limitRule.getId(), companyId, companyAllId)) {
					Integer periodType = null;
					if (limitRule.getLimitTimeType() == 2) {
						periodType = limitRule.getPeriodType();
					}
					LimitRuleRecordPO record = null;
					if (EmptyUtil.isNotEmpty(limitRule.getStandardUnitId())) {
						record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), limitRule.getStandardUnitId(), null, userId, null, null, periodType);
					} else if (EmptyUtil.isNotEmpty(limitRule.getSuCombId())) {
						record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), null, suCombMap.get(limitRule.getSuCombId()), userId, null, null, periodType);
					}
					if (record == null) {
						record = new LimitRuleRecordPO();
					}
					if (record.getBuySum() == null) {
						record.setBuySum(0L);
					}
					if (record.getBuyMoneySum() == null) {
						record.setBuyMoneySum(new BigDecimal(0));
					}
					if (limitRule.getUserLimitNum() != null) {
						if (limitNum == null || limitNum > (limitRule.getUserLimitNum() - record.getBuySum())) {
							limitNum = limitRule.getUserLimitNum() - record.getBuySum();
							logger.info("用户数量限制="+limitNum);
						}
					}
					if (limitRule.getUserMoneySum() != null) {
						if (limitAmount == null || limitAmount.compareTo(limitRule.getUserMoneySum().subtract(record.getBuyMoneySum())) == 1) {
							limitAmount = limitRule.getUserMoneySum().subtract(record.getBuyMoneySum());
							logger.info("用户金额限制="+limitAmount);
						}
					}
				}
			}

			//门店限购
			if (limitRule.getLimitUnit() != null && limitRule.getLimitUnit().contains("3")) {
				logger.info("门店限制");
				if ((limitRule.getIsStoreLimit() == null || limitRule.getIsStoreLimit() != 0) && fireStoreLimitRule(limitRule.getId(), storeId)) {
					logger.info("进入门店限制");
					Integer periodType = null;
					if (limitRule.getLimitTimeType() == 2) {
						periodType = limitRule.getPeriodType();
					}
					LimitRuleRecordPO record = null;
					if (EmptyUtil.isNotEmpty(limitRule.getStandardUnitId())) {
						record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), limitRule.getStandardUnitId(), null, null, storeId, null, periodType);
					} else if (EmptyUtil.isNotEmpty(limitRule.getSuCombId())) {
						record = limitRuleReadManage.selectLimitStatistic(null, limitRule.getId(), null, suCombMap.get(limitRule.getSuCombId()), null, storeId, null, periodType);
					}
					if (record == null) {
						record = new LimitRuleRecordPO();
					}
					if (record.getBuySum() == null) {
						record.setBuySum(0L);
					}
					if (record.getBuyMoneySum() == null) {
						record.setBuyMoneySum(new BigDecimal(0));
					}
					if (limitRule.getStoreLimitNum() != null) {
						if (limitNum == null || limitNum > (limitRule.getStoreLimitNum() - record.getBuySum())) {
							limitNum = limitRule.getStoreLimitNum() - record.getBuySum();
							logger.info("门店限制="+limitNum);
						}
					}
					if (limitRule.getStoreMoneySum() != null) {
						if (limitAmount == null || limitAmount.compareTo(limitRule.getStoreMoneySum().subtract(record.getBuyMoneySum())) == 1) {
							limitAmount = limitRule.getStoreMoneySum().subtract(record.getBuyMoneySum());
							logger.info("门店限制="+limitAmount);
						}
					}
				}
			}
		}
		/*List<Long> numList = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(limitNum)){
			numList.add(limitNum);
		}
		if(EmptyUtil.isNotEmpty(userLimitNum)){
			numList.add(userLimitNum);
		}
		if(EmptyUtil.isNotEmpty(companyLimitNum)){
			numList.add(companyLimitNum);
		}
		if(EmptyUtil.isNotEmpty(storeLimitNum)){
			numList.add(storeLimitNum);
		}
		if(EmptyUtil.isNotEmpty(numList)){
			Collections.sort(numList);
			for(Long num:numList){
				logger.info("num="+num);

			}
		}*/
		if (limitNum != null) {
			limitStrList.add("该商品的剩余可购买数量为" + limitNum+ "件");
		}
		/*List<BigDecimal> sumList = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(limitAmount)){
			sumList.add(limitAmount);
		}
		if(EmptyUtil.isNotEmpty(userLimitAmount)){
			sumList.add(userLimitAmount);
		}
		if(EmptyUtil.isNotEmpty(companyLimitAmount)){
			sumList.add(companyLimitAmount);
		}
		if(EmptyUtil.isNotEmpty(storeLimitAmount)){
			sumList.add(storeLimitAmount);
		}
		if(EmptyUtil.isNotEmpty(sumList)){
			Collections.sort(sumList);
			for(BigDecimal sum:sumList){
				logger.info("sum="+sum);
				if (limitAmount != null) {

				}
			}
		}*/
		if (limitAmount != null) {
			limitStrList.add("该商品的剩余可购买金额为" + limitAmount + "元");
		}
		return limitStrList;
	}

	private boolean fireCompanyLimitRule(Long limitRuleId, Long companyId, Long companyAllId) {
		List<LimitRuleCompanyPO> lrcList1 = null;
		List<LimitRuleCompanyPO> lrcList2 = null;
		LimitRuleCompanyPO lrc = new LimitRuleCompanyPO();
		lrc.setLimitRuleId(limitRuleId);
		lrc.setCompanyId(companyId);
		lrcList1 = limitRuleCompanyReadManage.findLimitRuleCompanyAll(lrc);
		if (EmptyUtil.isEmpty(lrcList1)) {
			lrc.setCompanyId(companyAllId);
			lrcList2 = limitRuleCompanyReadManage.findLimitRuleCompanyAll(lrc);
		}
		return EmptyUtil.isNotEmpty(lrcList1) || EmptyUtil.isNotEmpty(lrcList2);
	}

	private boolean fireUserLimitRule(Long limitRuleId, Long companyId, Long companyAllId) {
		List<LimitRuleUserPO> lrcList1 = null;
		List<LimitRuleUserPO> lrcList2 = null;
		LimitRuleUserPO lrc = new LimitRuleUserPO();
		lrc.setLimitRuleId(limitRuleId);
		lrc.setCompanyId(companyId);
		lrcList1 = limitRuleUserReadManage.findLimitRuleUserAll(lrc);
		if (EmptyUtil.isEmpty(lrcList1)) {
			lrc.setCompanyId(companyAllId);
			lrcList2 = limitRuleUserReadManage.findLimitRuleUserAll(lrc);
		}
		return EmptyUtil.isNotEmpty(lrcList1) || EmptyUtil.isNotEmpty(lrcList2);
	}

	private boolean fireStoreLimitRule(Long limitRuleId, Long storeId) {
		LimitRuleStorePO lrc = new LimitRuleStorePO();
		lrc.setLimitRuleId(limitRuleId);
		lrc.setStoreId(storeId);
		List<LimitRuleStorePO> lrcList1 = limitRuleStoreReadManage.findLimitRuleStoreAll(lrc);
		return EmptyUtil.isNotEmpty(lrcList1);
	}

	/**
	 * 拼接字符串
	 * @return
	 */
	/*private String[] limitRuleJointString(LimitRulePO limitRulePO) {
		Long limitNum = null;
		BigDecimal limitAmount = null;
		if (limitRulePO.getIsLimit() != null && limitRulePO.getIsLimit().equals(1)) {
			limitNum = limitRulePO.getSuLimitNum();
			limitAmount = limitRulePO.getSuLimitAmount();
		}
		if (limitRulePO.getIsCompanyLimit() != null && limitRulePO.getIsCompanyLimit().equals(1)) {
			if (limitRulePO.getCompanyLimitNum() != null && (limitNum == null || limitRulePO.getCompanyLimitNum() < limitNum)) {
				limitNum = limitRulePO.getCompanyLimitNum();
			}
			if (limitRulePO.getCompanyMoneySum() != null && (limitAmount == null || limitRulePO.getCompanyMoneySum().compareTo(limitAmount) == -1)) {
				limitAmount = limitRulePO.getCompanyMoneySum();
			}
		}
		if (limitRulePO.getIsUserLimit() != null && limitRulePO.getIsUserLimit().equals(1)) {
			if (limitRulePO.getUserLimitNum() != null && (limitNum == null || limitRulePO.getUserLimitNum() < limitNum)) {
				limitNum = limitRulePO.getUserLimitNum();
			}
			if (limitRulePO.getUserMoneySum() != null && (limitAmount == null || limitRulePO.getUserMoneySum().compareTo(limitAmount) == -1)) {
				limitAmount = limitRulePO.getUserMoneySum();
			}
		}
		if (limitRulePO.getIsStoreLimit() != null && limitRulePO.getIsStoreLimit().equals(1)) {
			if (limitRulePO.getStoreLimitNum() != null && (limitNum == null || limitRulePO.getStoreLimitNum() < limitNum)) {
				limitNum = limitRulePO.getStoreLimitNum();
			}
			if (limitRulePO.getStoreMoneySum() != null && (limitAmount == null || limitRulePO.getStoreMoneySum().compareTo(limitAmount) == -1)) {
				limitAmount = limitRulePO.getStoreMoneySum();
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (limitRulePO.getLimitTimeType() == 1) {
			// 获取当前时间
			long millis = DateUtils.curTimeMillis();
			// 判断当前时间是否在限购规则时间内
			if(limitRulePO.getLimitOriginTime().getTime() < millis && millis < limitRulePO.getLimitStopTime().getTime()){
				if (limitNum != null) {
					stringBuilder.append("该商品的剩余可购买数量为" + limitNum + "件");
				}
				if (limitAmount != null) {
					stringBuilder.append("该商品的剩余可购买金额为" + limitAmount + "元");
				}
			}
		} else {
			if (limitNum != null) {
				stringBuilder.append("该商品的剩余可购买数量为" + limitNum + "件");
			}
			if (limitAmount != null) {
				stringBuilder.append("该商品的剩余可购买金额为" + limitAmount + "元");
			}
		}
		return null;
	}*/

	private String intTypeToString(Integer periodType) {
		// 周期类型：1、按日限购 2、按月限购 3、按年限购
		switch (periodType) {
		case 1:
			return "自然日";

		case 2:
			return "自然月";

		case 3:
			return "自然年";
		}
		return null;
	}

	@Override
	public LimitRuleRecordDTO selectLimitStatistic(Integer orderStatus, Long limitRuleId, Long standardUnitId, List<Long> standardUnitIdList,
                                                   Long userId, Long storeId, Long companyId, Integer periodType) {
		LimitRuleRecordPO po = limitRuleReadManage.selectLimitStatistic(orderStatus,limitRuleId, standardUnitId, standardUnitIdList, userId, storeId, companyId, periodType);
		return LimitRuleRecordConverter.toDTO(po);
	}

}
