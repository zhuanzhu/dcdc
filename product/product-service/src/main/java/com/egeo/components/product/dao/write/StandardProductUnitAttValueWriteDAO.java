package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardProductUnitAttValueWriteDAO extends BaseWriteDAO<StandardProductUnitAttValuePO> {
	/**
	 * 批量保存spu属性值信息
	 * @param standardProductUnitAttValueList
	 * @return
	 */
	int insertAll(@Param("poList")List<StandardProductUnitAttValuePO> standardProductUnitAttValueList);

    void saveSPUValue(@Param("poList")List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList);
}
	