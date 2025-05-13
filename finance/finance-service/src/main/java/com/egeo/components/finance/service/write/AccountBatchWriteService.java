package com.egeo.components.finance.service.write;

import java.util.List;

import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CashFlowResultDTO;

public interface AccountBatchWriteService {

	/**
	 * 统一资金流动接口<br>
	 * 接口内会对账户的金额加密值进行校验<br>
	 * 如非必要,调用接口前无需主动校验涉及的账户加密信息<br>
	 * outFlowAccs和inFlowAccs的所有元素的salt都需要赋值<br>
	 * sum赋值方法!!!很重要!!!
	 * 单账户->单账户:在outFlowAccs[0]的sum字段指定金额<br>
	 * 单账户->多账户:在inFlowAccs每个元素的sum字段指定金额<br>
	 * 多账户->单账户:在outFlowAccs每个元素的sum字段指定金额<br>
	 * sum总是正数
	 * 多账户对单账户流动视为资金回收逻辑,
	 * 执行该逻辑需要赋值userAccountType字段 0:积分账户 1:点赞福豆账户 2:积分冻结账户 3:现金支出账户
	 * subRemark用法参见CashFlowAccountDTO.subRemark注释
	 * @param outFlowAccs 流出方账户列表
	 * @param outFlowAccType 0:公司 1:个人
	 * @param inFlowAccs 流入方账户列表
	 * @param inFlowAccType 0:公司 1:个人
	 * @param nonNegLimit 流出方账户是否可以小于0
	 * @param platformId
	 * @param type 操作类型 参见com.egeo.core.Constant.FlowTypeConstant
	 * @param orderId 订单id(非必填)
	 * @param orderCode 订单编号(非必填,订单信息冗余,方便查询)
	 * @param operatorId 操作人id(非必填,非审核人)
	 * @param operateType 操作类型 0:一对一 1:一对多 2:多对一
	 * @param finBatchType 批次财务编号:参见com.egeo.core.Constant.FinBatchConstant
	 * @param remark 批次表\流水表remark字段,当inFlowAccs中元素的subRemark有值时,subRemark会覆盖remark作为流水表的remark字段
	 * @return
	 */
	public CashFlowResultDTO unifiedCashFlow(
			List<CashFlowAccountDTO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountDTO> inFlowAccs, Integer inFlowAccType,
			boolean nonNegLimit,Long platformId,Integer type,
			Long orderId,String orderCode,Long operatorId,
			int finBatchType,Long reasonId,String remark,boolean isRecharge
			,Integer operateType);

	public Long insertAccountBatchWithTx(AccountBatchDTO dto);

	public int updateAccountBatchWithTx(AccountBatchDTO dto);

	public int deleteAccountBatchWithTx(AccountBatchDTO dto);

	/**
	 * 批次审核通过/不通过
	 *
	 *
     * @param platformId
     * @param id
     * @param pass
     * @return
	 */
	public int batchExam(Long platformId, Long id, boolean pass, String outflowSalt);



	/**
	 * 统一资金流动接口<br>
	 * 接口内会对账户的金额加密值进行校验<br>
	 * 如非必要,调用接口前无需主动校验涉及的账户加密信息<br>
	 * outFlowAccs和inFlowAccs的所有元素的salt都需要赋值<br>
	 * sum赋值方法!!!很重要!!!
	 * 单账户->单账户:在outFlowAccs[0]的sum字段指定金额<br>
	 * 单账户->多账户:在inFlowAccs每个元素的sum字段指定金额<br>
	 * 多账户->单账户:在outFlowAccs每个元素的sum字段指定金额<br>
	 * sum总是正数
	 * 多账户对单账户流动视为资金回收逻辑,
	 * 执行该逻辑需要赋值userAccountType字段 0:积分账户 1:点赞福豆账户 2:积分冻结账户 3:现金支出账户
	 * subRemark用法参见CashFlowAccountDTO.subRemark注释
	 * @param outFlowAccs 流出方账户列表
	 * @param outFlowAccType 0:公司 1:个人
	 * @param inFlowAccs 流入方账户列表
	 * @param inFlowAccType 0:公司 1:个人
	 * @param nonNegLimit 流出方账户是否可以小于0
	 * @param platformId
	 * @param type 操作类型 参见com.egeo.core.Constant.FlowTypeConstant
	 * @param orderId 订单id(非必填)
	 * @param orderCode 订单编号(非必填,订单信息冗余,方便查询)
	 * @param operatorId 操作人id(非必填,非审核人)
	 * @param operateType 操作类型 0:一对一 1:一对多 2:多对一
	 * @param finBatchType 批次财务编号:参见com.egeo.core.Constant.FinBatchConstant
	 * @param remark 批次表\流水表remark字段,当inFlowAccs中元素的subRemark有值时,subRemark会覆盖remark作为流水表的remark字段
	 * @Param otherInfo 其他信息
	 * @return
	 */
	public CashFlowResultDTO unifiedCashFlow(
			List<CashFlowAccountDTO> outFlowAccs, Integer outFlowAccType,
			List<CashFlowAccountDTO> inFlowAccs, Integer inFlowAccType,
			boolean nonNegLimit,Long platformId,Integer type,
			Long orderId,String orderCode,Long operatorId,
			int finBatchType,Long reasonId,String remark,boolean isRecharge
			,Integer operateType,String otherInfo);
}
