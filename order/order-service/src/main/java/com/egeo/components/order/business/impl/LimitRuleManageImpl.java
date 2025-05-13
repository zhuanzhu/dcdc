package com.egeo.components.order.business.impl;
	

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.order.business.LimitRuleManage;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.facade.LimitRuleFacade;
import com.egeo.components.order.facade.LimitRuleRecordFacade;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.components.order.common.DateUtils;
import com.egeo.utils.EmptyUtil;

@Service("limitRule")
public class LimitRuleManageImpl implements LimitRuleManage{

	
	@Resource(name="limitRuleFacade")
	private LimitRuleFacade limitRuleFacade;
	
	@Resource(name="limitRuleRecordFacade")
	private LimitRuleRecordFacade limitRuleRecordFacade;
	/**
	 * 根据限购规则id查询限购规则信息
	 * @return
	 */
	@Override
	public Map<String, Object> findLimitRuleById(Long limitRuleId) {
		Map<String, Object> map = new HashMap<>();
		LimitRuleDTO limitRuleDTO = new LimitRuleDTO();
		limitRuleDTO.setId(limitRuleId);
		LimitRuleDTO limitRuleDTO2 = limitRuleFacade.findLimitRuleById(limitRuleDTO);
		map.put("id", limitRuleDTO2.getId());
		map.put("name", limitRuleDTO2.getName());
		map.put("standardUnitId", limitRuleDTO2.getStandardUnitId());
		map.put("suCombId", limitRuleDTO2.getSuCombId());
		if (EmptyUtil.isNotEmpty(limitRuleDTO2.getStandardUnitId())) {
			StandardUnitDTO standardUnitDTO = limitRuleFacade.standardUnitByStandardUnitId(limitRuleDTO2.getStandardUnitId());
			map.put("standardUnitName", standardUnitDTO.getName());
		} else if (EmptyUtil.isNotEmpty(limitRuleDTO2.getSuCombId())) {
			StandardUnitCombinationDTO suComb = limitRuleFacade.findStandardUnitCombinationById(limitRuleDTO2.getSuCombId());
			map.put("standardUnitName", suComb.getCombinationName());
		}
		List<Long> companyIds = limitRuleFacade.findCompanyByLimitRuleId(limitRuleId);
		map.put("companyIds", companyIds);
		List<Long> userCompanyIds = limitRuleFacade.findLimitRuleUserCompany(limitRuleId);
		map.put("userCompanyIds", userCompanyIds);
		List<Long> storeIds = limitRuleFacade.findLimitRuleStore(limitRuleId);
		map.put("storeIds", storeIds);
		map.put("isLimit", limitRuleDTO2.getIsLimit());
		map.put("suLimitNum", limitRuleDTO2.getSuLimitNum());
		map.put("limitTimeType", limitRuleDTO2.getLimitTimeType());
		map.put("limitOriginTime", limitRuleDTO2.getLimitOriginTime());
		map.put("limitStopTime", limitRuleDTO2.getLimitStopTime());
		map.put("periodType", limitRuleDTO2.getPeriodType());
		map.put("limitUnit", limitRuleDTO2.getLimitUnit());
		map.put("userLimitNum", limitRuleDTO2.getUserLimitNum());
		map.put("userMoneySum", limitRuleDTO2.getUserMoneySum());
		map.put("limitTarget", limitRuleDTO2.getLimitTarget());
		map.put("suLimitAmount", limitRuleDTO2.getSuLimitAmount());
		map.put("companyType", limitRuleDTO2.getCompanyType());
		map.put("isUserLimit", limitRuleDTO2.getIsUserLimit());
		map.put("isCompanyLimit", limitRuleDTO2.getIsCompanyLimit());
		map.put("companyLimitNum", limitRuleDTO2.getCompanyLimitNum());
		map.put("companyMoneySum", limitRuleDTO2.getCompanyMoneySum());
		map.put("isStoreLimit", limitRuleDTO2.getIsStoreLimit());
		map.put("storeLimitNum", limitRuleDTO2.getStoreLimitNum());
		map.put("storeMoneySum", limitRuleDTO2.getStoreMoneySum());
		return map;
	}

	@Override
	public PageResult<Map<String, Object>> findLimitRuleOfPage(LimitRuleDTO dto, Pagination page) {
		PageResult<LimitRuleDTO> result = limitRuleFacade.findLimitRuleOfPage(dto, page);
		List<Map<String, Object>> maps = new ArrayList<>();
		List<LimitRuleDTO> limitRuleList = result.getList();
		for (LimitRuleDTO limitRuleDTO : limitRuleList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", limitRuleDTO.getId());
			map.put("name", limitRuleDTO.getName());
			map.put("serialNumber", limitRuleDTO.getSerialNumber());
			String suLimitCount = null;
			Integer limitType = null;
			if (limitRuleDTO.getIsLimit().equals(0)) {
				suLimitCount = "不限量";
			} else {
				if (EmptyUtil.isNotEmpty(limitRuleDTO.getSuLimitNum())) {
					suLimitCount = limitRuleDTO.getSuLimitNum().toString();
					limitType = 1;
				} else if (EmptyUtil.isNotEmpty(limitRuleDTO.getSuLimitAmount())) {
					suLimitCount = limitRuleDTO.getSuLimitAmount().toString();
					limitType = 2;
				}
			}
			map.put("suLimitCount", suLimitCount);
			map.put("limitType", limitType);
			if(limitRuleDTO.getLimitTimeType() != null){
				if(limitRuleDTO.getLimitTimeType() == 1){
					String limitOriginTime = DateUtils.stampToDate(limitRuleDTO.getLimitOriginTime(), "yyyy-MM-dd HH");
					String limitStopTime = DateUtils.stampToDate(limitRuleDTO.getLimitStopTime(), "yyyy-MM-dd HH");
					map.put("limitTime", limitOriginTime + "至" + limitStopTime);
				}
				if(limitRuleDTO.getLimitTimeType() == 2){
					map.put("limitTime", periodTypeToString(limitRuleDTO.getPeriodType()));
				}
			}
			map.put("createTime", limitRuleDTO.getCreateTime());
			map.put("createUserid", limitRuleDTO.getCreateUserid());
			map.put("createUsername", limitRuleDTO.getCreateUsername());
			map.put("createTime", limitRuleDTO.getCreateTime());
			map.put("updateUserid", limitRuleDTO.getUpdateUserid());
			map.put("updateUsername", limitRuleDTO.getUpdateUsername());
			map.put("updateTime", limitRuleDTO.getUpdateTime());
			map.put("isStart", limitRuleDTO.getIsStart());
			/*if(limitType != null && limitType == 1){
				map.put("userLimitNum", limitRuleDTO.getUserLimitNum());
				map.put("userMoneySum", null);
				// 根据限购规则id查询限购规则购买商品总量
				Long buySum = limitRuleRecordFacade.findBuySumByLimitRuleId(limitRuleDTO.getId());
				map.put("buySum", buySum);
				if(buySum != null && buySum.doubleValue() > 0){
					map.put("isUpdate", 0);
				}else{
					map.put("isUpdate", 1);
				}
			} else if(limitType != null && limitType == 2){
				map.put("userLimitNum", null);
				map.put("userMoneySum", limitRuleDTO.getUserMoneySum());
				// 根据限购规则id查询限购规则购买商品总金额
				BigDecimal buyMoneySum = limitRuleRecordFacade.findBuyMoneySumByLimitRuleId(limitRuleDTO.getId());
				map.put("buySum", buyMoneySum);
				if(buyMoneySum != null && buyMoneySum.doubleValue() > 0){
					map.put("isUpdate", 0);
				}else{
					map.put("isUpdate", 1);
				}
			} else {
				map.put("isUpdate", 1);
			}*/
			
			LimitRuleRecordDTO recordDTO = limitRuleFacade.selectLimitStatistic(Integer.valueOf(2),limitRuleDTO.getId(), null, null, null, null, null, limitRuleDTO.getPeriodType());
			if (recordDTO == null) {
				recordDTO = new LimitRuleRecordDTO();
				if (recordDTO.getBuySum() == null) {
					recordDTO.setBuySum(0L);
				} 
				if (recordDTO.getBuyMoneySum() == null) {
					recordDTO.setBuyMoneySum(new BigDecimal(0));
				} 
			}
			if (limitRuleDTO.getIsLimit() == 2) {//总量限量
				map.put("buySum", recordDTO.getBuySum());
				map.put("remainSum", limitRuleDTO.getSuLimitNum() - recordDTO.getBuySum());
			} else if (limitRuleDTO.getIsLimit() == 3) {//金额限量
				map.put("buySum", recordDTO.getBuyMoneySum());
				map.put("remainSum", limitRuleDTO.getSuLimitAmount().subtract(recordDTO.getBuyMoneySum()));
			}

			LimitRuleRecordDTO djRecordDTO = limitRuleFacade.selectLimitStatistic(Integer.valueOf(1),limitRuleDTO.getId(), null, null, null, null, null, limitRuleDTO.getPeriodType());
			if (djRecordDTO == null) {
				djRecordDTO = new LimitRuleRecordDTO();
				if (djRecordDTO.getBuySum() == null) {
					djRecordDTO.setBuySum(0L);
				}
				if (djRecordDTO.getBuyMoneySum() == null) {
					djRecordDTO.setBuyMoneySum(new BigDecimal(0));
				}
			}
			Long limitNum = 0L;
			BigDecimal limitSum = BigDecimal.ZERO;
			if(limitRuleDTO.getLimitUnit().contains("1")){
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getUserLimitNum())){
					limitNum=limitRuleDTO.getUserLimitNum();
				}
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getUserMoneySum())){
					limitSum=limitRuleDTO.getUserMoneySum();
				}
			}else if(limitRuleDTO.getLimitUnit().contains("2")){
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getCompanyLimitNum())) {
					if (limitRuleDTO.getCompanyLimitNum() >= limitNum) {
						limitNum = limitRuleDTO.getCompanyLimitNum();
					}
				}
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getCompanyMoneySum())){
					if(limitRuleDTO.getCompanyMoneySum().compareTo(limitSum)>=0){
						limitSum=limitRuleDTO.getCompanyMoneySum();
					}
				}


			}else if(limitRuleDTO.getLimitUnit().contains("3")){
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getStoreLimitNum())){
					if (limitRuleDTO.getStoreLimitNum() >= limitNum) {
						limitNum = limitRuleDTO.getStoreLimitNum();
					}
				}
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getStoreMoneySum())){
					if (limitRuleDTO.getStoreMoneySum().compareTo(limitSum)>=0) {
						limitSum= limitRuleDTO.getStoreMoneySum();
					}

				}
			}else if(limitRuleDTO.getIsLimit()==1){
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getSuLimitNum())){
					if(limitRuleDTO.getSuLimitNum()>=limitNum){
						limitNum=limitRuleDTO.getSuLimitNum();
					}
				}
				if(EmptyUtil.isNotEmpty(limitRuleDTO.getSuLimitAmount())){
					if(limitRuleDTO.getSuLimitAmount().compareTo(limitSum)>=0){
						limitSum=limitRuleDTO.getSuLimitAmount();
					}
				}
			}


			if (EmptyUtil.isNotEmpty(limitNum)&&djRecordDTO.getBuySum()>0&&limitNum>0) {//总量限量
				map.put("frozenSum", djRecordDTO.getBuySum());
				map.put("remainSum", limitNum - djRecordDTO.getBuySum()-recordDTO.getBuySum());
			} else if(EmptyUtil.isNotEmpty(limitSum)&&djRecordDTO.getBuyMoneySum().compareTo(BigDecimal.ZERO)>0&&limitSum.compareTo(BigDecimal.ZERO)>0) {
				map.put("frozenSum", djRecordDTO.getBuyMoneySum());
				map.put("remainSum", limitSum.subtract(djRecordDTO.getBuyMoneySum()).subtract(recordDTO.getBuyMoneySum()));
			}




			//判断该条限购规则是否有购买记录
			/*LimitRuleRecordDTO limitRuleRecordDTO = new LimitRuleRecordDTO();
			limitRuleRecordDTO.setLimitRuleId(limitRuleDTO.getId());
			List<LimitRuleRecordDTO> limitRuleRecordDTO2 = limitRuleRecordFacade.findLimitRuleRecordAll(limitRuleRecordDTO);
			if (EmptyUtil.isNotEmpty(limitRuleRecordDTO2)) {
				for (LimitRuleRecordDTO  limitRuleRecord: limitRuleRecordDTO2) {
					if (limitRuleRecord.getBuySum().intValue()>0L)
						map.put("record",1);
				}
			}else {
				map.put("record",0);
			}*/

			maps.add(map);
		}
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		pageResult.setList(maps);
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}
	/**
	 * 类型转String
	 * @param periodType
	 * @return
	 */
	private String periodTypeToString(Integer periodType) {
		// 周期类型：1、按日限购 2、按月限购 3、按年限购
		switch (periodType) {
		case 1:
			return "按日限购";
			
		case 2:
			return "按月限购";
			
		case 3:
			return "按年限购";
		default:
			throw new BusinessException("未定义周期类型");
		}
	}

	@Override
	public List<LimitRuleDTO> findLimitRuleAll(LimitRuleDTO dto) {
		return limitRuleFacade.findLimitRuleAll(dto);
	}

	@Override
	public Long insertLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList) {
		return limitRuleFacade.insertLimitRuleWithTx(dto, companyIdList, userCompanyIdList, storeIdList);
	}

	@Override
	public int updateLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList) {
		return limitRuleFacade.updateLimitRuleWithTx(dto, companyIdList, userCompanyIdList, storeIdList);
	}

	@Override
	public int deleteLimitRuleWithTx(LimitRuleDTO dto) {
		return limitRuleFacade.deleteLimitRuleWithTx(dto);
	}
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	@Override
	public int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart) {
		// TODO Auto-generated method stub
		return limitRuleFacade.isLimitRuleStartWithTx(limitRuleId, isStart);
	}
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	@Override
	public Map<String, Object> startLimitRuleByStandardUnitId(Long standardUnitId,Long companyId,Long platformId, Long userId, Long storeId) {
		Map<String, Object> data = new HashMap<>();
		List<String> limitRuleList = limitRuleFacade.startLimitRuleByStandardUnitId(standardUnitId,companyId,platformId, userId, storeId);
		data.put("limitRuleList", limitRuleList);
		return data;
	}

}
	