package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StandardUnitClientPO;


public interface StandardUnitClientWriteManage {

	Long insertStandardUnitClientWithTx(StandardUnitClientPO po);

	int updateStandardUnitClientWithTx(StandardUnitClientPO po);

	int deleteStandardUnitClientWithTx(StandardUnitClientPO po);
	/**
	 * 根据suid删除su客户端关系表
	 * @param merchantProdId
	 * @return
	 */
	int deleteByStandardUnitIdWithTx(Long standardUnitId);
	/**
	 * 根据需要排除的su客户端关系id及suid删除不存在的su客户端关系
	 * @param merchantProductId
	 * @param clientId
	 * @return
	 */
	int delByStandardUnitIdClientId(Long standardUnitId, List<Long> clientId);
	/**
	 * 根据suid全部删除
	 * @param merchantProductId
	 * @return
	 */
	int delByStandardUnitId(Long standardUnitId);

    void saveStandardUnitClient(List<StandardUnitClientPO> standardUnitClientPOList);
}
	