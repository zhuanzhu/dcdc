package com.egeo.components.order.manage.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.LimitRuleRecordReadManage;
import com.egeo.components.order.dao.read.LimitRuleRecordReadDAO;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LimitRuleRecordReadManageImpl implements LimitRuleRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleRecordReadDAO limitRuleRecordReadDAO;
	
	public LimitRuleRecordPO findLimitRuleRecordById(LimitRuleRecordPO po) {
		/*LimitRuleRecordPO limitRuleRecordpo = new LimitRuleRecordPO();
		limitRuleRecordpo.setId(po.getId());
		return limitRuleRecordReadDAO.findById(limitRuleRecordpo);*/
		return limitRuleRecordReadDAO.findById(po);
	}

	public PageResult<LimitRuleRecordPO> findLimitRuleRecordOfPage(LimitRuleRecordPO po, Pagination page) {
		
		PageResult<LimitRuleRecordPO> pageResult = new PageResult<LimitRuleRecordPO>();
		List<LimitRuleRecordPO> list = null;

		int cnt = limitRuleRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = limitRuleRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LimitRuleRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LimitRuleRecordPO> findLimitRuleRecordAll(LimitRuleRecordPO po) {

		return limitRuleRecordReadDAO.findAll(po,null);
	}
	/**
	 * 根据限购规则id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	@Override
	public Long findBuySumByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadDAO.findBuySumByLimitRuleId(limitRuleId);
	}
	/**
	 * 根据限购规则id平台id查询限购规则购买商品总量
	 * @param id
	 * @return
	 */
	@Override
	public Long findBuySumByLimitRuleIdPlatformId(Long limitRuleId,Long platformId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadDAO.findBuySumByLimitRuleIdPlatformId(limitRuleId,platformId);
	}
	/**
	 * 根据限购规则id查询限购规则购买商品总金额
	 * @param id
	 * @return
	 */
	@Override
	public BigDecimal findBuyMoneySumByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadDAO.findBuyMoneySumByLimitRuleId(limitRuleId);
	}
	/**
	 * 根据用户id和限购规则id平台id查询限购规则记录
	 * @param standardUnitId
	 * @param userId
	 * @param platformId
	 * @return
	 */
	@Override
	public List<LimitRuleRecordPO> findByUserIdLimitRuleId(Long limitRuleId, Long userId, Long platformId) {
		LimitRuleRecordPO limitRuleRecordPO = new LimitRuleRecordPO();
		limitRuleRecordPO.setLimitRuleId(limitRuleId);
		limitRuleRecordPO.setCreateUserid(userId);
		limitRuleRecordPO.setPlatformId(platformId);
		return limitRuleRecordReadDAO.findAll(limitRuleRecordPO,null);
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
	public List<LimitRuleRecordPO> findByPeriodTypeUserIdLimitRuleId(Integer periodType, Long memberId,
			Long limitRuleId, Long platformId) {
		// TODO Auto-generated method stub
		return limitRuleRecordReadDAO.findByPeriodTypeUserIdLimitRuleId( periodType, memberId, limitRuleId, platformId);
	}

	@Override
	public LimitRuleRecordPO selectLimitStatistic(Integer orderStatus, Long limitRuleId, Long standardUnitId, List<Long> standardUnitIdList,
												  Long userId, Long storeId, Long companyId, Integer periodType) {
		return limitRuleRecordReadDAO.selectLimitStatistic(orderStatus, limitRuleId, standardUnitId, standardUnitIdList, userId, storeId, companyId, periodType);
	}
}
	