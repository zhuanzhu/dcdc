package com.egeo.components.order.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.InvoicePO;
import com.egeo.orm.BaseWriteDAO;

public interface InvoiceWriteDAO extends BaseWriteDAO<InvoicePO> {

	/**
	 * 重置默认发票
	 * @param invoicePO
	 * @return
	 */
	int resetDefaultInvoice(@Param("po")InvoicePO po);
}
	