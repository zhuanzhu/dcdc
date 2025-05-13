package com.egeo.components.finance.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.orm.BaseReadDAO;

public interface TempRechargeReadDAO extends BaseReadDAO<TempRechargePO>{

	
	Double queryTempRechargeSummaryBySn(@Param("sn")String sn);

	/**
	 * 根据sn查询草稿列表
	 * @param sn
	 * @return
	 */
	List<TempRechargePO> queryTempRechargeBySn(@Param("sn")String sn);

	/**
	 * 根据sn查询记录数量
	 * @param sn
	 * @return
	 */
	int queryTempRechargeCountBySn(@Param("sn")String sn);
}
	