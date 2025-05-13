package com.egeo.components.order.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.orm.BaseWriteDAO;

public interface LimitRuleRecordWriteDAO extends BaseWriteDAO<LimitRuleRecordPO> {
	/**
	 * 批量添加限购记录信息
	 * @param limitRuleRecords
	 * @return
	 */
	int saveLimitRuleRecords(@Param("poList")List<LimitRuleRecordPO> limitRuleRecords);

    void updateOrderStatus(@Param("orderCode")String orderCode,@Param("orderStatus")Integer orderStatus);
}
	