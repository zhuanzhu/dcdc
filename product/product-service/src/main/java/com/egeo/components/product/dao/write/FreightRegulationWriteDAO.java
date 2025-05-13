package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.orm.BaseWriteDAO;

public interface FreightRegulationWriteDAO extends BaseWriteDAO<FreightRegulationPO> {
	/**
	 * 根据修改的运费规则id集合删除不存在的运费规则信息
	 * @param freightRegulationIds
	 * @return
	 */
	int delByFreightRegulationIds(@Param("ids")List<Long> freightRegulationIds);
}
	