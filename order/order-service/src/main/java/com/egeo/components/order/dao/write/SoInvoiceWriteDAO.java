package com.egeo.components.order.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoInvoicePO;
import com.egeo.orm.BaseWriteDAO;

public interface SoInvoiceWriteDAO extends BaseWriteDAO<SoInvoicePO> {

	/**
	 * 以订单号为条件修改发票
	 * @param orderCode
	 * @param titleType
	 * @param title
	 * @param invoiceType
	 * @param taxNo
	 * @param invoiceContentType 
	 * @return
	 */
	int updateSoInvoiceBySoId(@Param("soId")Long soId, 
			@Param("titleType")Integer titleType, @Param("title")String title, 
			@Param("invoiceType")Integer invoiceType, @Param("taxNo")String taxNo, 
			@Param("invoiceContentType")Integer invoiceContentType);

	/**
	 * 更换公共发票信息
	 * @param po
	 * @return
	 */
	Integer changeCommonInvoiceInfoWithTx(@Param("po")SoInvoicePO po);

	Integer updateInvoiceTypeInfoWithTx(@Param("po")SoInvoicePO po);
}
	