package com.egeo.components.order.manage.write;

import java.util.List;

import com.egeo.components.order.po.DeliveryImportExcelPO;
import com.egeo.components.order.po.SoPackagePO;


public interface SoPackageWriteManage {

	Long insertSoPackageWithTx(SoPackagePO po);

	int updateSoPackageWithTx(SoPackagePO po);

	int deleteSoPackageWithTx(SoPackagePO po);

	/**
	 * 完成订单发货导入
	 * @param poList
	 * @param operatorId
	 * @param platformId
	 */
	void completeDeliveryImport(List<DeliveryImportExcelPO> poList, Long operatorId, Long platformId);

	/**
	 * 签收
	 * @param deliveryMessage
	 * @param packageId
	 * @param soChildId
	 * @return
	 */
	boolean expressSignInWithTx(Long soId,String deliveryMessage, Long packageId, Long soChildId);
	boolean expressInwayWithTx(Long soId, Long packageId, Long soChildId);

	boolean expressTimeWithTx(Long soId, Long packageId, Long soChildId,String acceptTime);

	/**
	 * 完成订单发货导入
	 * @param poList
	 * @param operatorId
	 * @param platformId
	 */
	void completeChildDeliveryImport(List<DeliveryImportExcelPO> poList, Long operatorId, Long platformId);
}
