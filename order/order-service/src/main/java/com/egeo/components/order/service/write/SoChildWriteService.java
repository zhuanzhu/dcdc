package com.egeo.components.order.service.write;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.dto.SoPackageTempDTO;


public interface SoChildWriteService {

	public Long insertSoChildWithTx(SoChildDTO dto);

	public int updateSoChildWithTx(SoChildDTO dto);

	public int deleteSoChildWithTx(SoChildDTO dto);

	public void updateSochildStatusAndSynchrosoPackageTemp(List<Long> soChildIdList,
			List<SoPackageTempDTO> soPackageTempDTOList, Integer deliveryStatus);

	@Deprecated
	public boolean openOrder(Long sochildId, Map<Long, Integer> puIdAndCountMap, BigDecimal orderPrice,
			Map<Long, BigDecimal> puIdAndPriceMap, Integer orChangeReceiveInfo, ReceiverAddressDTO receiverAddressDTO);

	/**
	 * 拆单
	 * @param soChildId
	 * @param insertSoChild
	 * @param insertItems
	 * @param updateItems
	 * @param changedOldSoChildAmount
	 * @param operatorId
	 * @return
	 */
	public Long openOrderWithTx(SoChildDTO insertSoChild, List<SoItemDTO> insertItems,
			List<SoItemDTO> updateItems, SoChildDTO updateSoChild);

	/**
	 * 变更订单的所有子订单为分拣状态
	 * @param orderIds
	 */
	public void orderSort(List<Long> orderIds,Long operatorId);

    void writeSendInfo(Long orderId, Long operatorId, Long platformId);

    /**
     * 三方运营订单发货，目前为网店管家发货接口
     * @param soChild
     * @param soPackage
     * @param deliverMap
     */
    void deliverOrderWithTx(SoChildDTO soChild, SoPackageDTO soPackage, Map<String, Integer> deliverMap);

    void updateSoChildByCodeWithTx(SoChildDTO soChildDTO);

	void orderChildSort(List<SoChildDTO> orderChildren, Long operatorId);

	int updateSoChildRefundWithTx(SoChildDTO soChild);

	public int deleteByChildCodeWithTx(SoChildDTO dto);
}
