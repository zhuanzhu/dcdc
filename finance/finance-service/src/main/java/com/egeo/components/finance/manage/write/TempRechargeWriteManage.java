package com.egeo.components.finance.manage.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.components.finance.po.TempRechargePO;


public interface TempRechargeWriteManage {

	Long insertTempRechargeWithTx(TempRechargePO po);

	int updateTempRechargeWithTx(TempRechargePO po);

	int deleteTempRechargeWithTx(TempRechargePO po);

	/**
	 * 批量插入草稿
	 * @param batchInsertList
	 * @return
	 */
	int batchInsertTempRecharge(List<TempRechargePO> batchInsertList);

	/**
	 * 批量操作公司账户充值至用户账户
	 * @param trList
	 * @param companySalt
	 * @return
	 */
	int ca2uaBatchUpdate(List<TempRechargePO> trList, 
			String companySalt,
			CompanyAccountPO ca,
			BigDecimal summary,
			String batchNo,
			String finBatch,
			Long operatorId,
			Long reasonId,
			String remark,
			Integer accountType);

	/**
	 * 根据文件序号改变充值批次草稿状态
	 * @param sn
	 * @param status
	 * @return
	 */
	int updateTempRechargeStatus(String sn, int status);

	/**
	 * 根据sn删除批次草稿
	 * @param sn
	 * @return
	 */
	int deleteTempRechargeBySnWithTx(String sn);
}
	