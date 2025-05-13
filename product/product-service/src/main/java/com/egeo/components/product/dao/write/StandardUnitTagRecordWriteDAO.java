package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitTagRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitTagRecordWriteDAO extends BaseWriteDAO<StandardUnitTagRecordPO> {
	/**
	 * 批量保存su标签记录信息
	 * @param standardUnitTagRecords
	 * @return
	 */
	int insertAll(@Param("poList")List<StandardUnitTagRecordPO> standardUnitTagRecords);
}
	