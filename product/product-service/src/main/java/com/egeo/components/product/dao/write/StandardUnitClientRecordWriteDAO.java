package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitClientRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitClientRecordWriteDAO extends BaseWriteDAO<StandardUnitClientRecordPO> {
	/**
	 * 批量保存su平台关系记录信息
	 * @param standardUnitClientRecords
	 * @return
	 */
	int insertAll(@Param("poList")List<StandardUnitClientRecordPO> standardUnitClientRecords);
}
	