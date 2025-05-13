package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.orm.BaseReadDAO;

public interface SoChildFlowReadDAO extends BaseReadDAO<SoChildFlowPO>{

	/**
	 * 根据订单id查询操作流水
	 * @param orderId
	 * @return
	 */
	List<SoChildFlowPO> queryFlowListBySoId(@Param("orderId")Long orderId);

	/**
	 * 根据子订单id查询操作流水
	 * @param soChildId
	 * @return
	 */
	List<SoChildFlowPO> queryFlowListBySoChildId(@Param("soChildId")Long soChildId);
}
	