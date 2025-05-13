package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardProductUnitAttValueRecordWriteDAO extends BaseWriteDAO<StandardProductUnitAttValueRecordPO> {
	/**
	 * 批量保存spu属性值记录信息
	 * @param standardProductUnitAttValueRecordList
	 * @return
	 */
	int insertAll(@Param("poList")List<StandardProductUnitAttValueRecordPO> standardProductUnitAttValueRecordList);
}
	