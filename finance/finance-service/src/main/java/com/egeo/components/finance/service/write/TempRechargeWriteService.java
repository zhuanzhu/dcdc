package com.egeo.components.finance.service.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.dto.TempRechargeDTO;


public interface TempRechargeWriteService {

	public Long insertTempRechargeWithTx(TempRechargeDTO dto);

	public int updateTempRechargeWithTx(TempRechargeDTO dto);

	public int deleteTempRechargeWithTx(TempRechargeDTO dto);

	/**
	 * 批量插入草稿
	 * @param batchInsertList
	 * @return
	 */
	public int batchInsertTempRecharge(List<TempRechargeDTO> batchInsertList);

	/**
	 * 批量操作公司账户充值至用户账户
	 * @param trList
	 * @param companySalt
	 * @return
	 */
	public int ca2uaBatchUpdate(List<TempRechargeDTO> trList, 
			String companySalt,
			CompanyAccountDTO ca,
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
	public int updateTempRechargeStatus(String sn, int status);

	/**
	 * 根据sn删除充值批次草稿
	 * @param sn
	 * @return
	 */
	public int deleteTempRechargeBySn(String sn);
}
	