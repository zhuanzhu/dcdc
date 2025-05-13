package com.egeo.components.order.service.write.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.manage.write.SoChildWriteManage;
import com.egeo.components.order.converter.ReceiverAddressConverter;
import com.egeo.components.order.converter.SoChildConverter;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.converter.SoPackageConverter;
import com.egeo.components.order.converter.SoPackageTempConverter;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.dto.SoPackageTempDTO;
import com.egeo.components.order.po.SoChildPO;


@Service("soChildWriteService")
public class SoChildWriteServiceImpl  implements SoChildWriteService {
	@Autowired
	private SoChildWriteManage soChildWriteManage;

	@Override
	public Long insertSoChildWithTx(SoChildDTO dto) {
		SoChildPO po = SoChildConverter.toPO(dto);
		Long rt = soChildWriteManage.insertSoChildWithTx(po);
		return rt;
	}

	@Override
	public int updateSoChildWithTx(SoChildDTO dto) {
		SoChildPO po = SoChildConverter.toPO(dto);
		int rt = soChildWriteManage.updateSoChildWithTx(po);
		return rt;
	}

	@Override
	public int deleteSoChildWithTx(SoChildDTO dto) {
		SoChildPO po = SoChildConverter.toPO(dto);
		int rt = soChildWriteManage.deleteSoChildWithTx(po);
		return rt;
	}

	@Override
	public void updateSochildStatusAndSynchrosoPackageTemp(List<Long> soChildIdList,
			List<SoPackageTempDTO> soPackageTempDTOList, Integer deliveryStatus) {

		soChildWriteManage.updateSochildStatusAndSynchrosoPackageTemp(soChildIdList,SoPackageTempConverter.toPO(soPackageTempDTOList),deliveryStatus);
	}

	@Deprecated
	@Override
	public boolean openOrder(Long sochildId, Map<Long, Integer> puIdAndCountMap, BigDecimal orderPrice,
			Map<Long, BigDecimal> puIdAndPriceMap, Integer orChangeReceiveInfo, ReceiverAddressDTO receiverAddressDTO) {

		return soChildWriteManage.openOrder(sochildId,puIdAndCountMap,orderPrice,puIdAndPriceMap,orChangeReceiveInfo,ReceiverAddressConverter.toPO(receiverAddressDTO));
	}

	@Override
	public Long openOrderWithTx(SoChildDTO insertSoChild, List<SoItemDTO> insertItems,
			List<SoItemDTO> updateItems, SoChildDTO updateSoChild) {

		return soChildWriteManage.openOrderWithTx(
				SoChildConverter.toPO(insertSoChild),
				SoItemConverter.toPO(insertItems),
				SoItemConverter.toPO(updateItems),
				SoChildConverter.toPO(updateSoChild)
				);
	}

	@Override
	public void orderSort(List<Long> orderIds,Long operatorId) {
		soChildWriteManage.orderSortWithTx(orderIds,operatorId);
	}



	@Override
	public void orderChildSort(List<SoChildDTO> orderChildren,Long operatorId) {
		soChildWriteManage.orderChildSortWithTx(orderChildren, operatorId);
	}
	@Override
	public void writeSendInfo(Long orderId, Long operatorId, Long platformId) {
		soChildWriteManage.writeSendInfo(orderId, operatorId, platformId);
	}

	@Override
	public void deliverOrderWithTx(SoChildDTO soChild, SoPackageDTO soPackage, Map<String, Integer> deliverMap) {
		soChildWriteManage.deliverOrderWithTx(SoChildConverter.toPO(soChild), SoPackageConverter.toPO(soPackage), deliverMap);
	}

	@Override
	public void updateSoChildByCodeWithTx(SoChildDTO soChildDTO) {
		soChildWriteManage.updateSoChildByCodeWithTx(SoChildConverter.toPO(soChildDTO));
	}

	@Override
	public int updateSoChildRefundWithTx(SoChildDTO soChild){
		return soChildWriteManage.updateSoChildRefundWithTx(SoChildConverter.toPO(soChild));
	}

	@Override
	public int deleteByChildCodeWithTx(SoChildDTO soChildDTO){
		return soChildWriteManage.deleteByChildCodeWithTx(SoChildConverter.toPO(soChildDTO));
	}
}
