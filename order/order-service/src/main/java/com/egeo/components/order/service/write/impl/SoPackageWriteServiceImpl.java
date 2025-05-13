package com.egeo.components.order.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.write.SoPackageWriteService;
import com.egeo.components.order.manage.write.SoPackageWriteManage;
import com.egeo.components.order.converter.DeliveryImportExcelConverter;
import com.egeo.components.order.converter.SoPackageConverter;
import com.egeo.components.order.dto.DeliveryImportExcelDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.po.DeliveryImportExcelPO;
import com.egeo.components.order.po.SoPackagePO;


@Service("soPackageWriteService")
public class SoPackageWriteServiceImpl  implements SoPackageWriteService {
	@Autowired
	private SoPackageWriteManage soPackageWriteManage;

	@Override
	public Long insertSoPackageWithTx(SoPackageDTO dto) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
		Long rt = soPackageWriteManage.insertSoPackageWithTx(po);
		return rt;
	}

	@Override
	public int updateSoPackageWithTx(SoPackageDTO dto) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
		int rt = soPackageWriteManage.updateSoPackageWithTx(po);
		return rt;
	}

	@Override
	public int deleteSoPackageWithTx(SoPackageDTO dto) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
		int rt = soPackageWriteManage.deleteSoPackageWithTx(po);
		return rt;
	}

	@Override
	public void completeDeliveryImport(
			List<DeliveryImportExcelDTO> dtoList,
			Long operatorId, Long platformId) {
		List<DeliveryImportExcelPO> poList=DeliveryImportExcelConverter.toPO(dtoList);
		soPackageWriteManage.completeDeliveryImport(poList,operatorId,platformId);
	}

	@Override
	public boolean expressSignIn(Long soId,String deliveryMessage, Long packageId, Long soChildId) {
		return soPackageWriteManage.expressSignInWithTx(soId,deliveryMessage,packageId,soChildId);
	}
	@Override
	public boolean expressInway(Long soId, Long packageId, Long soChildId) {
		return soPackageWriteManage.expressInwayWithTx(soId,packageId,soChildId);
	}

	@Override
	public boolean expressTimeWithTx(Long soId, Long packageId, Long soChildId,String acceptTime){
		return soPackageWriteManage.expressTimeWithTx(soId,packageId,soChildId,acceptTime);
	}

	@Override
	public void completeChildDeliveryImport(
			List<DeliveryImportExcelDTO> dtoList,
			Long operatorId, Long platformId) {
		List<DeliveryImportExcelPO> poList=DeliveryImportExcelConverter.toPO(dtoList);
		soPackageWriteManage.completeChildDeliveryImport(poList,operatorId,platformId);
	}
}
