package com.egeo.components.order.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.orm.BaseWriteDAO;

public interface SoChildFlowWriteDAO extends BaseWriteDAO<SoChildFlowPO> {

	/**
	 * 根据订单id批量插入子订单操作记录
	 * @param orderId
	 * @param operatorId
	 * @param operate
	 */
	int insertSoChildFlowByOrderId(
			@Param("orderId")Long orderId, 
			@Param("operatorId")Long operatorId, 
			@Param("operate")int operate);

}
	