package com.egeo.components.finance.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AdjustReasonPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface AdjustReasonReadDAO extends BaseReadDAO<AdjustReasonPO>{

	/**
	 * 查询所有调整原因
	 * @return
	 */
	List<AdjustReasonPO> queryAdjustReasons(@Param("companyId")Long companyId);

	/**
	 * 原因分页列表
	 * @param type
	 * @param companyId
	 * @param disabled
	 * @param page
	 * @return
	 */
	List<AdjustReasonPO> queryAdjustReasonPage(
			@Param("type")Integer type, 
			@Param("companyId")Long companyId, 
			@Param("disabled")Integer disabled,
			@Param("platformId")Long platformId,
			@Param("page")Pagination page);

	/**
	 * 原因分页列表总记录数
	 * @param type
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	Integer queryAdjustReasonPageTotalSize(
			@Param("type")Integer type,
			@Param("companyId")Long companyId, 
			@Param("disabled")Integer disabled,
			@Param("platformId")Long platformId);

	/**
	 * 查询几个指定类型的原因
	 *
     * @param platformId
     * @param typeList
     * @param companyId
     * @return
	 */
	List<AdjustReasonPO> queryAdjustReasonsByTypes(
			@Param("platformId")Long platformId, @Param("typeList") List<Integer> typeList, @Param("accountId") Long accountId, @Param("companyId") Long companyId);
}
	