package com.egeo.components.finance.manage.write;

import java.util.List;

import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.components.finance.po.CashFlowAccountPO;
import com.egeo.components.finance.po.CashFlowResultPO;


public interface AccountBatchWriteManage {

	Long insertAccountBatchWithTx(AccountBatchPO po);

	int updateAccountBatchWithTx(AccountBatchPO po);

	int deleteAccountBatchWithTx(AccountBatchPO po);

	/**
	 * 批次审核通过/不通过
	 *
     * @param platformId
     * @param id
     * @param pass
     * @return
	 */
	int batchExamWithTx(Long platformId, Long id, boolean pass, String outFlowSalt);

	/**
	 * 原子处理单对单账户间资金流动
	 * @param outFlowAccBalance
	 * @param inFlowAccBalance
	 * @param sum
	 * @param outFlowAccSalt
	 * @param inFlowAccSalt
	 * @param inFlowAccType
	 * @param inFlowAccId
	 * @param outFlowAccType
	 * @param outFlowAccId
	 * @param operatorId
	 * @param platformId
	 * @return
	 */
//	@Deprecated
//	CashFlowResultPO unifiedO2OCashFlowWithTx(
//			BigDecimal outFlowAccBalance, BigDecimal inFlowAccBalance, BigDecimal sum,
//			String outFlowAccSalt,String inFlowAccSalt,
//			Long outFlowAccId, Integer outFlowAccType,
//			Long inFlowAccId, Integer inFlowAccType,
//			Long platformId, Long operatorId,String finBatch,
//			Long reasonId,String remark,Integer type,
//			String orderCode,Long orderId,boolean isRecharge);

	/**
	 * 原子处理单对多账户间资金流动
	 * @param outFlowAccId
	 * @param outFlowAccType
	 * @param outFlowAccBalance
	 * @param outFlowAccSalt
	 * @param operatorId
	 * @param inFlowAccts
	 * @param inFlowAccType
	 * @param type
	 * @param sum
	 * @param finBatch
	 * @param platformId
	 * @param reasonId
	 * @param remark
	 * @param isRecharge
	 * @return
	 */
//	@Deprecated
//	CashFlowResultPO unifiedO2MCashFlowWithTx(Long outFlowAccId, Integer outFlowAccType,
//			BigDecimal outFlowAccBalance, String outFlowAccSalt, Long operatorId, List<CashFlowAccountPO> inFlowAccts,
//			Integer inFlowAccType, Integer type, BigDecimal sum, String finBatch, Long platformId, Long reasonId,
//			String remark,boolean isRecharge,Long companyId);

	/**
	 * 原子处理多对单账户间资金流动(员工离职资金流动逻辑)
	 * @param outFlowAccts
	 * @param inFlowAccBalance
	 * @param inFlowAccSalt
	 * @param inFlowAccId
	 * @param sum 方法内不再统计,所以一定要先统计好
	 * @param platformId
	 * @param operatorId
	 * @param reasonId
	 * @param remark
	 * @param type
	 * @return
	 */
//	@Deprecated
//	CashFlowResultPO unifiedM2OCashFlowWithTx(List<CashFlowAccountPO> outFlowAccts, BigDecimal inFlowAccBalance,
//			String inFlowAccSalt, Long inFlowAccId,BigDecimal sum,
//			Long platformId, Long operatorId, Long reasonId, String remark,
//			Integer type);

	/**
	 * 原子处理账户间资金流动
	 * @param po
	 * @param outFlowAccType
	 * @param po2
	 * @param inFlowAccType
	 * @param nonNegLimit
	 * @param platformId
	 * @param type
	 * @param orderId
	 * @param orderCode
	 * @param operatorId
	 * @param finBatch
	 * @param reasonId
	 * @param remark
	 * @param isRecharge
	 * @param operateType
	 * @return
	 */
	CashFlowResultPO unifiedCashFlowWithTx(List<CashFlowAccountPO> po, Integer outFlowAccType,
			List<CashFlowAccountPO> po2, Integer inFlowAccType, boolean nonNegLimit, Long platformId, Integer type,
			Long orderId, String orderCode, Long operatorId, int finBatchType, Long reasonId, String remark,
			boolean isRecharge, Integer operateType,String otherInfo);

}
