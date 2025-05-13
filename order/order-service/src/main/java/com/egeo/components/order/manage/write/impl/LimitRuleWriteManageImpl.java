package com.egeo.components.order.manage.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.LimitRuleWriteManage;
import com.egeo.components.order.dao.write.LimitRuleCompanyWriteDAO;
import com.egeo.components.order.dao.write.LimitRuleStoreWriteDAO;
import com.egeo.components.order.dao.write.LimitRuleUserWriteDAO;
import com.egeo.components.order.dao.write.LimitRuleWriteDAO;
import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.components.order.po.LimitRulePO;
import com.egeo.components.order.po.LimitRuleStorePO;
import com.egeo.components.order.po.LimitRuleUserPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.DateUtils;

@Service
public class LimitRuleWriteManageImpl implements LimitRuleWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleWriteDAO limitRuleWriteDAO;
	
	@Autowired
	private LimitRuleCompanyWriteDAO limitRuleCompanyWriteDAO;
	@Autowired
	private LimitRuleUserWriteDAO limitRuleUserWriteDAO;
	@Autowired
	private LimitRuleStoreWriteDAO limitRuleStoreWriteDAO;

	@Override
	public Long insertLimitRuleWithTx(LimitRulePO po, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList) {
		
		int i ;
		try {
			//加上三位随机数
			Random random = new Random();
			int end3 = random.nextInt(999);
			//如果不足三位前面补0
			String serialNumber = DateUtils.getDefaultDateTimeNowNUM() + String.format("%03d", end3);
			po.setSerialNumber(serialNumber);
				i = limitRuleWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		
		if (EmptyUtil.isNotEmpty(companyIdList)) {
			List<LimitRuleCompanyPO> limitRuleCompanyPOs = new ArrayList<>();
			for (Long companyId : companyIdList) {
				LimitRuleCompanyPO limitRuleCompanyPO = new LimitRuleCompanyPO();
				limitRuleCompanyPO.setLimitRuleId(po.getId());
				limitRuleCompanyPO.setCompanyId(companyId);
				limitRuleCompanyPOs.add(limitRuleCompanyPO);
			}
			limitRuleCompanyWriteDAO.insertAll(limitRuleCompanyPOs);
		}
		if (EmptyUtil.isNotEmpty(userCompanyIdList)) {
			for (Long userCompanyId : userCompanyIdList) {
				LimitRuleUserPO limitRuleUserPO = new LimitRuleUserPO();
				limitRuleUserPO.setLimitRuleId(po.getId());
				limitRuleUserPO.setCompanyId(userCompanyId);
				limitRuleUserWriteDAO.insert(limitRuleUserPO);
			}
		}
		if (EmptyUtil.isNotEmpty(storeIdList)) {
			for (Long storeId : storeIdList) {
				LimitRuleStorePO limitRuleStorePO = new LimitRuleStorePO();
				limitRuleStorePO.setLimitRuleId(po.getId());
				limitRuleStorePO.setStoreId(storeId);
				limitRuleStoreWriteDAO.insert(limitRuleStorePO);
			}
		}
		return po.getId();
	}

	@Override
	public int updateLimitRuleWithTx(LimitRulePO po, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList) {
		int i;
		i = limitRuleWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		
		// 根据限购规则Id删除限购规则与公司id关系
		LimitRuleCompanyPO limitRuleCompany = new LimitRuleCompanyPO();
		limitRuleCompany.setLimitRuleId(po.getId());
		limitRuleCompanyWriteDAO.deleteByPara(limitRuleCompany);
		
		List<LimitRuleCompanyPO> limitRuleCompanyPOs = new ArrayList<>();
		for (Long companyId : companyIdList) {
			LimitRuleCompanyPO limitRuleCompanyPO = new LimitRuleCompanyPO();
			limitRuleCompanyPO.setLimitRuleId(po.getId());
			limitRuleCompanyPO.setCompanyId(companyId);
			limitRuleCompanyPOs.add(limitRuleCompanyPO);
		}
		limitRuleCompanyWriteDAO.insertAll(limitRuleCompanyPOs);
		
		LimitRuleUserPO delLimitRuleUserPO = new LimitRuleUserPO();
		delLimitRuleUserPO.setLimitRuleId(po.getId());
		limitRuleUserWriteDAO.deleteByPara(delLimitRuleUserPO);
		for (Long userCompanyId : userCompanyIdList) {
			LimitRuleUserPO limitRuleUserPO = new LimitRuleUserPO();
			limitRuleUserPO.setLimitRuleId(po.getId());
			limitRuleUserPO.setCompanyId(userCompanyId);
			limitRuleUserWriteDAO.insert(limitRuleUserPO);
		}
		
		LimitRuleStorePO delLimitRuleStorePO = new LimitRuleStorePO();
		delLimitRuleStorePO.setLimitRuleId(po.getId());
		limitRuleStoreWriteDAO.deleteByPara(delLimitRuleStorePO);
		for (Long storeId : storeIdList) {
			LimitRuleStorePO limitRuleStorePO = new LimitRuleStorePO();
			limitRuleStorePO.setLimitRuleId(po.getId());
			limitRuleStorePO.setStoreId(storeId);
			limitRuleStoreWriteDAO.insert(limitRuleStorePO);
		}
		return i;
	}

	@Override
	public int deleteLimitRuleWithTx(LimitRulePO po) {
		int i;
		i = limitRuleWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	@Override
	public int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart) {
		LimitRulePO limitRulePO = new LimitRulePO();
		limitRulePO.setId(limitRuleId);
		limitRulePO.setIsStart(isStart);
		return limitRuleWriteDAO.update(limitRulePO);
	}	
}
	