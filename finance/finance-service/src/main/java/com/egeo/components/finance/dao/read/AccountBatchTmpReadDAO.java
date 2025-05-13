package com.egeo.components.finance.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountBatchTmpPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface AccountBatchTmpReadDAO extends BaseReadDAO<AccountBatchTmpPO>{

	/**
	 * 根据财务批次号查询账户批次草稿
	 * @param finBatch
	 * @return
	 */
	AccountBatchTmpPO queryAccountBatchTmpByFinBatch(@Param("finBatch")String finBatch);

	/**
	 * 查询账户批次草稿分页列表
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	List<AccountBatchTmpPO> queryAccountBatchTmpPage(
			@Param("page")Pagination page, 
			@Param("keyWord")String keyWord, 
			@Param("companyId")Long companyId, 
			@Param("status")Integer status,
			@Param("type")Integer type,
			@Param("platformId")Long platformId);

	/**
	 * 查询账户批次草稿分页列表总记录数
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	Integer queryAccountBatchTmpPageTotalSize(
			@Param("keyWord")String keyWord, 
			@Param("companyId")Long companyId, 
			@Param("status")Integer status,
			@Param("type")Integer type,
			@Param("platformId")Long platformId);
}
	