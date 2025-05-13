package com.egeo.components.finance.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface AccountBatchReadDAO extends BaseReadDAO<AccountBatchPO>{

	/**
	 * 根据财务批次号查询批次
	 * @param finBatch
	 * @return
	 */
	AccountBatchPO queryAccountBatchByFinBatch(@Param("finBatch")String finBatch);

	/**
	 * 查询今天的批次数量
	 * @return
	 */
	Integer queryBatchDayCount();

	/**
	 * 查询账户批次分页列表
	 * @param batchNo 
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	List<AccountBatchPO> queryAccountBatchPage(
			@Param("accountId")Long accountId,
			@Param("batchNo")String batchNo, 
			@Param("page")Pagination page,
			@Param("keyWord")String keyWord, 
			@Param("companyId")Long companyId,
			@Param("type")Integer type,
			@Param("status")Integer status,
			@Param("platformId")Long platformId);

	/**
	 * 查询账户批次分页列表总记录数
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	Integer queryAccountBatchPageTotalCount(
			@Param("accountId")Long accountId,
			@Param("batchNo")String batchNo, 
			@Param("keyWord")String keyWord, 
			@Param("companyId")Long companyId,
			@Param("type")Integer type,
			@Param("status")Integer status,
			@Param("platformId")Long platformId);

	/**
	 * 查询一周内总额为sum的批次
	 * @param sum
	 * @return
	 */
	List<AccountBatchPO> querySameSumBatchInWeek(@Param("sum")double sum);

	/**
	 * 查询充值批次记录分页列表
	 * @param page
	 * @param companyId
	 * @param batchNo
	 * @return
	 */
	List<AccountBatchPO> queryRechargeAccountBatchPage(
			@Param("page")Pagination page, 
			@Param("companyId")Long companyId, 
			@Param("batchNo")String batchNo);
	
	/**
	 * 查询充值批次记录分页列表总记录数
	 * @param companyId
	 * @param batchNo
	 * @return
	 */
	Integer queryRechargeAccountBatchPageTotalSize(
			@Param("companyId")Long companyId, 
			@Param("batchNo")String batchNo);
}
	