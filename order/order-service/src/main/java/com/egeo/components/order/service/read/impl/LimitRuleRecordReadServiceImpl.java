package com.egeo.components.order.service.read.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.LimitRuleRecordReadService;
import com.egeo.components.order.manage.read.LimitRuleRecordReadManage;
import com.egeo.components.order.converter.LimitRuleRecordConverter;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.po.LimitRuleRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("limitRuleRecordReadService")
public class LimitRuleRecordReadServiceImpl  implements LimitRuleRecordReadService {
	@Autowired
	private LimitRuleRecordReadManage limitRuleRecordReadManage;

	@Override
	public LimitRuleRecordDTO findLimitRuleRecordById(LimitRuleRecordDTO dto) {
		LimitRuleRecordPO po = LimitRuleRecordConverter.toPO(dto);
		LimitRuleRecordPO list = limitRuleRecordReadManage.findLimitRuleRecordById(po);		
		return LimitRuleRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<LimitRuleRecordDTO> findLimitRuleRecordOfPage(LimitRuleRecordDTO dto, Pagination page) {
		LimitRuleRecordPO po = LimitRuleRecordConverter.toPO(dto);
        PageResult<LimitRuleRecordPO> pageResult = limitRuleRecordReadManage.findLimitRuleRecordOfPage(po, page);
        List<LimitRuleRecordDTO> list = LimitRuleRecordConverter.toDTO(pageResult.getList());
        PageResult<LimitRuleRecordDTO> result = new PageResult<LimitRuleRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LimitRuleRecordDTO> findLimitRuleRecordAll(LimitRuleRecordDTO dto) {
		LimitRuleRecordPO po = LimitRuleRecordConverter.toPO(dto);
		List<LimitRuleRecordPO> list = limitRuleRecordReadManage.findLimitRuleRecordAll(po);		
		return LimitRuleRecordConverter.toDTO(list);
	}
	/**
	 * 根据限购规则id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	@Override
	public Long findBuySumByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadManage.findBuySumByLimitRuleId(limitRuleId);
	}
	/**
	 * 根据限购规则id平台id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	@Override
	public Long findBuySumByLimitRuleIdPlatformId(Long limitRuleId,Long platformId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadManage.findBuySumByLimitRuleIdPlatformId(limitRuleId,platformId);
	}
	/**
	 * 根据限购规则id查询限购规则购买商品总金额
	 * @param id
	 * @return
	 */
	@Override
	public BigDecimal findBuyMoneySumByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadManage.findBuyMoneySumByLimitRuleId(limitRuleId);
	}
	/**
	 * 根据用户id和限购规则id平台id查询限购规则记录
	 * @param limitRuleId
	 * @param userId
	 * @param platformId
	 * @return
	 */
	@Override
	public List<LimitRuleRecordDTO> findByUserIdLimitRuleId(Long limitRuleId, Long userId, Long platformId) {
		List<LimitRuleRecordPO> limitRuleRecordList = limitRuleRecordReadManage.findByUserIdLimitRuleId(limitRuleId, userId, platformId);
		return LimitRuleRecordConverter.toDTO(limitRuleRecordList);
	}
	/**
	 * 根据自然年、月、日类型，用户id，限购规则id，平台id查询限购规则记录信息
	 * @param periodType 周期类型：1、按日限购 2、按月限购 3、按年限购
	 * @param memberId 用户id
	 * @param limitRuleId 限购规则id
	 * @param platformId 平台id
	 * @return
	 */
	@Override
	public List<LimitRuleRecordDTO> findByPeriodTypeUserIdLimitRuleId(Integer periodType, Long memberId,
			Long limitRuleId, Long platformId) {
		List<LimitRuleRecordPO> limitRuleRecordList = limitRuleRecordReadManage.findByPeriodTypeUserIdLimitRuleId(periodType, memberId, limitRuleId, platformId);
		return LimitRuleRecordConverter.toDTO(limitRuleRecordList); 
	}

	@Override
	public LimitRuleRecordDTO selectLimitStatistic(Integer orderStatus,Long limitRuleId, Long standardUnitId, List<Long> standardUnitIdList,
			Long userId, Long storeId, Long companyId, Integer periodType) {
		LimitRuleRecordPO limitRuleRecordPO = limitRuleRecordReadManage.selectLimitStatistic(orderStatus,limitRuleId, standardUnitId, standardUnitIdList, userId, storeId, companyId, periodType);
		return LimitRuleRecordConverter.toDTO(limitRuleRecordPO);
	}
}
	