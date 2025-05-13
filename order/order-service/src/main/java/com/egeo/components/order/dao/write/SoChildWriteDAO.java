package com.egeo.components.order.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoChildPO;
import com.egeo.orm.BaseWriteDAO;

public interface SoChildWriteDAO extends BaseWriteDAO<SoChildPO> {

	/**
	 * 更改母订单下所有子订单的物流状态
	 * @param orderId
	 * @param deliveryStatus
	 * @return
	 */
	int updateDeliveryStatusBySoId(
			@Param("orderId")Long orderId,
			@Param("deliveryStatus")int deliveryStatus);
	int updateDeliveryStatusBySoChildId(
			@Param("orderId")Long childOrderId,
			@Param("deliveryStatus")int deliveryStatus);
    void updateSoChildByCodeWithTx(@Param("po")SoChildPO soChildPO);

	int updateSoChildRefundWithTx(@Param("po")SoChildPO po);

	int deleteByChildCodeWithTx(@Param("childCode") String childCode,@Param("deleteStatus") int deleteStatus);
}
