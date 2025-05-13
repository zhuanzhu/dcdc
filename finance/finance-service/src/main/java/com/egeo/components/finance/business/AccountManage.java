package com.egeo.components.finance.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.entity.CacheUser;
import com.egeo.web.JsonResult;

public interface AccountManage {

	/**
	 * 后台查询公司账户分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	JsonResult<Map<String, Object>> companyAccountPage(Integer pageNo, Integer pageSize, String accountName,
			List<Long> companyId, Integer disabled,Long platformId);

	/**
	 * 查询账户操作原因
	 * @return
	 */
	JsonResult<Map<String, Object>> accountOperateReasons(Long companyId);

	/**
	 * 充值提交申请
	 * @param accountId
	 * @param sum
	 * @param reasonId
	 * @param remark
	 * @param raBatch
	 * @param finBatch
	 * @param platformId
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> raSubmitForExam(Long accountId,Double sum,Long reasonId,
			String remark,String finBatch,Long platformId, Long userId);

	/**
	 * 公司账户详情
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> companyAccountDetail(Long id);

	/**
	 * 改变公司账户有效性
	 * @param id
	 * @param disabled
	 * @return
	 */
	JsonResult<Map<String, Object>> changeAccountDisable(Long id, Integer disabled,Long userId,Long platformId);

	/**
	 * 恢复企业和员工账户金额
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> recoverAccountBalance(Long id,Integer recover,Long userId,Long platformId);

	/**
	 * 批次操作审核分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> batchTmpPage(Integer pageNo,
			Integer pageSize,String keyWord,Long companyId,
			Integer status,Integer type, Long platformId);

	/**
	 * 批次审核通过/不通过
	 * @param id
	 * @param option
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> batchExam(Long id, Integer option,String reason,Long platformId);

	/**
	 * 批次分页列表
	 * @param batchNo
	 * @param reasonId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> accountBatchPage(Long accountId,String batchNo,Integer type,
			Integer pageNo,Integer pageSize,Long platformId);

	/**
	 * 批次流水分页列表
	 * @param batchId
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> batchFlowPage(Long batchId,String outflowAccount,String inflowAccount,
			Long startTime,Long endTime,Integer pageNo,Integer pageSize,Long platformId);

	/**
	 * 全部流水分页列表
	 * @param startTime
	 * @param endTime
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param pageNo
	 * @param pageSize
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> flowPage(Long startTime,Long endTime,
			String outflowAccount,String inflowAccount,Integer pageNo,
			Integer pageSize,Long platformId);

	/**
	 * 普通公司账户列表
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> normalAccounts(Long platformId,List<Long> companyId);

	/**
	 * 员工账户分页列表
	 * @param name
	 * @param email
	 * @param companyId
	 * @param pageNo
	 * @param pageSize
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> userAccountPage(String name, String email, Long companyId, Integer pageNo,
			Integer pageSize, Long platformId,Long userCompanyId);

	/**
	 * 新增/编辑原因
	 * @param id
	 * @param reason
	 * @param type
	 * @param companyId
	 * @param disabled
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> saveReason(Long id, String reason, Integer type, String companyIds, Integer disabled,
			Long platformId);

	/**
	 * 原因分页列表
	 * @param type
	 * @param companyId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	JsonResult<Map<String, Object>> reasons(Integer type, Long companyId, Integer disabled, Integer pageNo,
			Integer pageSize,Long platformId);

	/**
	 * 用户账户充值记录
	 * @param companyId
	 * @param batchNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	JsonResult<Map<String, Object>> userAccountRechargeRec(Long companyId, String batchNo, Integer pageNo,
			Integer pageSize,Long platformId);

	/**
	 * 用户账户充值导入
	 * @param totalAmount
	 * @param companyId
	 * @param accountType
	 * @param reasonId
	 * @param remark
	 * @param finBatch
	 * @param platformId
	 * @param valueList
	 * @return
	 */
	JsonResult<Map<String, Object>> userAccountRecharge(Double totalAmount, Long companyId, Integer accountType,
			Long reasonId, String remark, String finBatch, Long platformId, List<Map<String, Object>> valueList,CacheUser user);

	/**
	 * 员工账户充值导入预览
	 * @param sn
	 * @return
	 */
	//JsonResult<Map<String, Object>> userAccountRechargePreview(String sn);

	/**
	 * 确认导入
	 * @param sn
	 * @param confirm
	 * @return
	 */
	JsonResult<Map<String, Object>> impConfirm(String sn, Integer confirm,Long userId);

	/**
	 * 用户资产
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> userAssets(Long userId);

	/**
	 * 用户账户流水分页列表
	 * @param accountId
	 * @param batchNo
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> userAccountFlowPage(Long accountId, Integer mode, Integer pageNo, Integer pageSize,
			Long userId);

	/**
	 * 全部批次分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param tableType
	 * @param batchNo
	 * @param type
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> fullBatchPage(Integer pageNo, Integer pageSize,
			Integer tableType,String batchNo,Integer type,Integer status,Long platformId);
	/**
	 * 冻结或扣除用户积分账户
	 * @param req
	 * @return
	 */
	Integer foscoinAccountDeductWithTx(Long userId, String orderCode,Long platformId,String paymentPassword,
			String userName,String ip,String mac,Long companyId,Integer payType,String realName,String idCardNo,String cardIds, HttpServletRequest req);

	/**
	 * 充值记录明细分页列表
	 * @param batchId
	 * @param pageNo
	 * @param pageSize
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> userAccountRechargeRecDetail(Long batchId, Integer pageNo, Integer pageSize,
			Long platformId);
	/**
	 * 根据订单编号解冻订单积分
	 * @param userId
	 * @param orderCode
	 * @param platformId
	 * @return
	 */
	Integer unfreezeSoFubi(Long userId, String orderCode, Long platformId);

	/**
	 * 根据订单编号解冻订单积分
	 * @param userId
	 * @param orderCode
	 * @param platformId
	 * @return
	 */
	Integer unfreezeSoChildFubi(Long userId, String orderCode, Long platformId);

	Integer checkBalance(Long userId, String month, Long platformId);
	/**
	 * 员工账户流水分页列表
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> userFinFlowPage(Long userId,Integer accountType,Integer pageNo,Integer pageSize);

	/**
	 * 查询所有流水类型
	 * @return
	 */
	JsonResult<Map<String, Object>> flowTypeList();

	/**
	 * 获取账户操作原因列表
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> reasonDetail(Long reasonId);

	/**
	 * 获取指定类型的账户操作原因列表
	 *
     * @param platformId
     * @param types
     * @param companyId
     * @return
	 */
	JsonResult<Map<String, Object>> certainTypeReasons(Long platformId, String types, Long accountId, Long companyId);

	/**
	 * 通过订单编号查询订单
	 * @param orderCode
	 * @return
	 */
	SoDTO querySoByOrderCode(String orderCode);

	/**
	 * 查询批次审核通过/不通过的原因
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> batchExamReason(Long id);

    JsonResult<Map<String,Object>> userFlowExportBackStage(List<Long> userIdList, Long platformId, Date startTime, Date endTime);

//	/**
//	 *
//	 * @param userId
//	 * @param type
//	 * @param accountId
//	 * @param pageNo
//	 * @param pageSize
//	 * @return
//	 */
//	JsonResult<Map<String, Object>> userAccountFlowPage(Long userId, Integer type, Long accountId, Integer pageNo,
//			Integer pageSize);

	Map<String, Object> AccountDisabledLock(Long accountId,  CacheUser user);

	void releaseAccountLock(Long id,int type,CacheUser user);

	Boolean judgeAccountLock(Long id,int type,CacheUser user);

	void updateAccountFlowReadStatus(List<Long> ids);

	UserAccountDTO userAccount(Long userId, Integer type);

	/**
	 * 根据退款记录清除已退餐卡金额
	 * @param userId
	 * @param refundId
	 * @param platformId
	 * @return
	 */
	Integer unRefundAmount(Long userId, Long refundId, Long platformId);

}
