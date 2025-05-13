package com.egeo.components.finance.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface AccountFlowReadDAO extends BaseReadDAO<AccountFlowPO>{

	/**
	 * 根据批次id查询账户流水分页列表
	 * @param batchId
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param sd
	 * @param ed
	 * @param page
	 * @return
	 */
	List<AccountFlowPO> queryAccountFlowPage(
			@Param("batchId")Long batchId, 
			@Param("outflowAccount")String outflowAccount, 
			@Param("inflowAccount")String inflowAccount,
			@Param("sd")Date sd, 
			@Param("ed")Date ed, 
			@Param("platformId")Long platformId,
			@Param("page")Pagination page);

	/**
	 * 根据批次id查询账户流水分页列表总记录数
	 * @param batchId
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param sd
	 * @param ed
	 * @return
	 */
	Integer queryAccountFlowPageTotalCount(
			@Param("batchId")Long batchId, 
			@Param("outflowAccount")String outflowAccount, 
			@Param("inflowAccount")String inflowAccount,
			@Param("sd")Date sd, 
			@Param("ed")Date ed,
			@Param("platformId")Long platformId);

	/**
	 * 查询账户流水分页列表
	 * @param page
	 * @param accountId
	 * @param mode
	 * @return
	 */
	List<AccountFlowPO> queryAccountFlowPageByAccountId(
			@Param("page")Pagination page, 
			@Param("accountId")Long accountId, 
			@Param("mode")Integer mode);

	/**
	 * 查询账户流水分页列表总记录数
	 * @param accountId
	 * @param mode
	 * @return
	 */
	Integer queryAccountFlowPageByAccountIdTotalSize(
			@Param("accountId")Long accountId, 
			@Param("mode")Integer mode);

	/**
	 * 查询订单退款流水
	 * @param orderId
	 * @return
	 */
	List<AccountFlowPO> queryOrderRefundFlow(@Param("orderId")Long orderId);

	/**
	 * 用户资产流水分页列表
	 * @param accountIdList
	 * @param page
	 * @param startTime
     *@param endTime @return
	 */
	List<AccountFlowPO> userFinFlowPage(
            @Param("accountIdList") List<Long> accountIdList,
            @Param("page") Pagination page, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

	/**
	 * 用户资产流水分页列表
	 * @param accountIdList
	 * @param page
	 * @return
	 */
	Integer userFinFlowPageTotalCount(
			@Param("accountIdList")List<Long> accountIdList, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

	List<AccountFlowPO> userFinFlowEnterprise(@Param("enterpriseId")Long enterprise, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
	List<AccountFlowPO> userFinFlowCompany(@Param("companyId")Long companyId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

	
}
	