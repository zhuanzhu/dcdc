package com.egeo.components.finance.manage.write.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dao.read.UserAccountReadDAO;
import com.egeo.components.finance.dao.write.AccountBatchWriteDAO;
import com.egeo.components.finance.dao.write.AccountFlowWriteDAO;
import com.egeo.components.finance.dao.write.CompanyAccountWriteDAO;
import com.egeo.components.finance.dao.write.TempRechargeWriteDAO;
import com.egeo.components.finance.dao.write.UserAccountWriteDAO;
import com.egeo.components.finance.manage.write.TempRechargeWriteManage;
import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.components.finance.po.UserAccountPO;

@Service
public class TempRechargeWriteManageImpl implements TempRechargeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TempRechargeWriteDAO tempRechargeWriteDAO;
	
	@Autowired
	private UserAccountWriteDAO userAccountWriteDAO;
	
	@Autowired
	private UserAccountReadDAO userAccountReadDAO;
	@Autowired
	private AccountFlowWriteDAO accountFlowWriteDAO;
	@Autowired
	private CompanyAccountWriteDAO companyAccountWriteDAO;
	
	@Autowired
	private AccountBatchWriteDAO accountBatchWriteDAO;

	@Override
	public Long insertTempRechargeWithTx(TempRechargePO po) {
		
		int i ;
		try {
				i = tempRechargeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateTempRechargeWithTx(TempRechargePO po) {
		int i;
		i = tempRechargeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteTempRechargeWithTx(TempRechargePO po) {
		return tempRechargeWriteDAO.delete(po);
	}

	@Override
	public int batchInsertTempRecharge(List<TempRechargePO> batchInsertList) {
		return tempRechargeWriteDAO.batchInsertTempRecharge(batchInsertList);
	}

	@Override
	public int ca2uaBatchUpdate(
			List<TempRechargePO> trList, 
			String companySalt,
			CompanyAccountPO ca,
			BigDecimal summary,
			String batchNo,
			String finBatch,
			Long operatorId,
			Long reasonId,
			String remark,
			Integer accountType) {
		//公司账户减少总值
		companyAccountWriteDAO.increaseAccountBalance(ca.getId(), summary.negate(), false,companySalt);
		//插入公司账户批次
		AccountBatchPO batch=new AccountBatchPO();
		batch.setId(ca.getId());
		batch.setCompanyId(ca.getCompanyId());
		batch.setFinBatch(finBatch);
		batch.setFlowAmount(trList.size());
		batch.setOperatorId(operatorId);
		batch.setRaBatch(batchNo);
		batch.setReasonId(reasonId);
		batch.setRemark(remark);
		batch.setStatus(3);
		batch.setSum(summary);
		int opType=FlowTypeConstant.UA_RECHARGE.getStatus()+accountType;
		//accountType=0积分账户 1:点赞福豆账户
		//7:员工积分充值 8:员工点赞福豆充值
		batch.setType(opType);
		accountBatchWriteDAO.insert(batch);
		Long batchId=batch.getId();
		for(TempRechargePO po:trList){
			BigDecimal sum=po.getSum();
			summary=summary.add(sum);
			UserAccountPO ua=userAccountReadDAO.queryUserAccountByUserIdAndType(po.getUserId(), accountType);
			//增加用户账户余额
			userAccountWriteDAO.increaseUserAccount(ua.getId(),sum,true,po.getSalt());
			//插入流水
			AccountFlowPO af=new AccountFlowPO();
			af.setBatchId(batchId);
			af.setInflowAccountid(ua.getId());
			af.setInflowAccounttype(1);
			af.setOutflowAccountid(ca.getId());
			af.setOutflowAccounttype(0);
			af.setReason(po.getFlowReason());
			af.setRemark(po.getRemark());
			af.setSum(po.getSum());
			af.setType(opType);
			accountFlowWriteDAO.insert(af);
		}
		return 1;
	}

	@Override
	public int updateTempRechargeStatus(String sn, int status) {
		return tempRechargeWriteDAO.updateTempRechargeStatus(sn,status);
	}

	@Override
	public int deleteTempRechargeBySnWithTx(String sn) {
		
		return tempRechargeWriteDAO.deleteTempRechargeBySn(sn);
	}	
}
	