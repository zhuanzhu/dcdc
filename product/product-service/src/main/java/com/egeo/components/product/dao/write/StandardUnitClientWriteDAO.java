package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitClientWriteDAO extends BaseWriteDAO<StandardUnitClientPO> {
	/**
	 * 根据需要排除的su客户端关系id及suid删除不存在的su客户端关系
	 * @param merchantProductId
	 * @param clientId
	 * @return
	 */
	int delByStandardUnitIdClientId(@Param("standardUnitId")Long standardUnitId, @Param("ids")List<Long> clientId);
	/**
	 * 批量保存su平台关系信息
	 * @param merchantProdClients
	 */
	void insertAll(@Param("poList")List<StandardUnitClientPO> standardUnitClients);

    void saveStandardUnitClient(@Param("poList")List<StandardUnitClientPO> standardUnitClientPOList);
}
	