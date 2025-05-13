package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.LimitRuleReadManage;
import com.egeo.components.order.dao.read.LimitRuleReadDAO;
import com.egeo.components.order.dao.read.LimitRuleRecordReadDAO;
import com.egeo.components.order.po.LimitRulePO;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LimitRuleReadManageImpl implements LimitRuleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleReadDAO limitRuleReadDAO;
	@Autowired
	private LimitRuleRecordReadDAO limitRuleRecordReadDAO;
	
	public LimitRulePO findLimitRuleById(LimitRulePO po) {
		LimitRulePO limitRulepo = new LimitRulePO();
		limitRulepo.setId(po.getId());
		return limitRuleReadDAO.findById(limitRulepo);
	}

	public PageResult<LimitRulePO> findLimitRuleOfPage(LimitRulePO po, Pagination page) {
		
		PageResult<LimitRulePO> pageResult = new PageResult<LimitRulePO>();
		List<LimitRulePO> list = null;

		int cnt = limitRuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = limitRuleReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LimitRulePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LimitRulePO> findLimitRuleAll(LimitRulePO po) {

		return limitRuleReadDAO.findAll(po,null);
	}
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	@Override
	public List<LimitRulePO> startLimitRuleByStandardUnitId(Long standardUnitId, List<Long> suCombIdList, Long platformId) {
		// TODO Auto-generated method stub
		return limitRuleReadDAO.startLimitRuleByStandardUnitId(standardUnitId, suCombIdList, platformId);
	}
	
	@Override
	public LimitRuleRecordPO selectLimitStatistic(Integer orderStatus, Long limitRuleId, Long standardUnitId, List<Long> standardUnitIdList,
												  Long userId, Long storeId, Long companyId, Integer periodType) {
		return limitRuleRecordReadDAO.selectLimitStatistic(orderStatus,limitRuleId, standardUnitId, standardUnitIdList, userId, storeId, companyId, periodType);
	}
}
	