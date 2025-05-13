package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitCompanyRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitCompanyRecordWriteDAO extends BaseWriteDAO<StandardUnitCompanyRecordPO> {
	/**
	 * 批量保存公司关系记录信息
	 * @param standardUnitCompanyRecords
	 * @return
	 */
	int insertAll(@Param("poList")List<StandardUnitCompanyRecordPO> standardUnitCompanyRecords);
}
	