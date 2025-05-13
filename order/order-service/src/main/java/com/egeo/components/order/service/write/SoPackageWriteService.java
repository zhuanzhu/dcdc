package com.egeo.components.order.service.write;

import java.util.List;

import com.egeo.components.order.dto.DeliveryImportExcelDTO;
import com.egeo.components.order.dto.SoPackageDTO;


public interface SoPackageWriteService {

	public Long insertSoPackageWithTx(SoPackageDTO dto);

	public int updateSoPackageWithTx(SoPackageDTO dto);

	public int deleteSoPackageWithTx(SoPackageDTO dto);

	/**
	 * 完成订单发货导入
	 * @param dtoList
	 * @param operatorId
	 * @param platformId
	 */
	public void completeDeliveryImport(List<DeliveryImportExcelDTO> dtoList, Long operatorId, Long platformId);

	/**
	 * 签收
	 * @param deliveryMessage
	 * @param packageId
	 * @param soChildId
	 * @return
	 */
	public boolean expressSignIn(Long soId,String deliveryMessage, Long packageId, Long soChildId);

	boolean expressInway(Long soId, Long packageId, Long soChildId);

	boolean expressTimeWithTx(Long soId, Long packageId, Long soChildId,String acceptTime);

	/**
	 * 完成订单发货导入
	 * @param dtoList
	 * @param operatorId
	 * @param platformId
	 */
	public void completeChildDeliveryImport(List<DeliveryImportExcelDTO> dtoList, Long operatorId, Long platformId);
}
