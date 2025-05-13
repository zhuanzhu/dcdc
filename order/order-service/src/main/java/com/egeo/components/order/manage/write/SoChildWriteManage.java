package com.egeo.components.order.manage.write;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.components.order.po.SoPackageTempPO;


public interface SoChildWriteManage {

	Long insertSoChildWithTx(SoChildPO po);

	int updateSoChildWithTx(SoChildPO po);

	int deleteSoChildWithTx(SoChildPO po);

	void updateSochildStatusAndSynchrosoPackageTemp(List<Long> soChildIdList, List<SoPackageTempPO> po, Integer deliveryStatus);

	@Deprecated
	boolean openOrder(Long sochildId, Map<Long, Integer> puIdAndCountMap, BigDecimal orderPrice,
			Map<Long, BigDecimal> puIdAndPriceMap, Integer orChangeReceiveInfo, ReceiverAddressPO po);

	/**
	 * 拆单
	 * @param soChildId
	 * @param insertSoChild
	 * @param insertItems
	 * @param updateItems
	 * @param changedOldSoChildAmount
	 * @param operatorIds
	 * @return
	 */
	Long openOrderWithTx(SoChildPO insertSoChild, List<SoItemPO> insertItems, List<SoItemPO> updateItems, SoChildPO updateSoChild);

	/**
	 * 变更订单的所有子订单为分拣状态
	 * @param orderIds
	 */
	void orderSortWithTx(List<Long> orderIds,Long operatorId);

    void writeSendInfo(Long orderId, Long operatorId, Long platformId);

    /**
     * 三方运营订单发货，目前为网店管家发货接口
     * @param soChild
     * @param soPackage
     * @param deliverMap
     */
    void deliverOrderWithTx(SoChildPO soChild, SoPackagePO soPackage, Map<String, Integer> deliverMap);

	void updateSoChildByCodeWithTx(SoChildPO soChildPO);

	void orderChildSortWithTx(List<SoChildDTO> orderChildren, Long operatorId);

	int updateSoChildRefundWithTx(SoChildPO soChild);

	int deleteByChildCodeWithTx(SoChildPO dto);
}
