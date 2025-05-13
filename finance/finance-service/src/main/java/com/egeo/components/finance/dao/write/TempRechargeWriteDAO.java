package com.egeo.components.finance.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.orm.BaseWriteDAO;

public interface TempRechargeWriteDAO extends BaseWriteDAO<TempRechargePO> {

	/**
	 * 批量插入草稿
	 * @param batchInsertList
	 * @return
	 */
	int batchInsertTempRecharge(@Param("list")List<TempRechargePO> batchInsertList);

	/**
	 * 根据文件序号改变充值批次草稿状态
	 * @param sn
	 * @param status
	 * @return
	 */
	int updateTempRechargeStatus(@Param("sn")String sn, @Param("status")int status);

	
	/**
	 * 根据sn删除充值批次草稿
	 * @param sn
	 * @return
	 */
	int deleteTempRechargeBySn(@Param("sn")String sn);
}
	