package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoFlowPO;
import com.egeo.orm.BaseReadDAO;

public interface SoFlowReadDAO extends BaseReadDAO<SoFlowPO>{

	/**
	 * 根据订单id查询订单操作记录列表
	 * @param orderId
	 * @return
	 */
	List<SoFlowPO> queryFlowListBySoId(@Param("orderId")Long orderId);
}
	