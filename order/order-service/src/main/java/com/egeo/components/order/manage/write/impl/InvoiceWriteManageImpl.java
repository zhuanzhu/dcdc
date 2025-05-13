package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.InvoiceWriteManage;
import com.egeo.components.order.dao.write.InvoiceWriteDAO;
import com.egeo.components.order.po.InvoicePO;
import com.egeo.exception.BusinessException;

@Service
public class InvoiceWriteManageImpl implements InvoiceWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InvoiceWriteDAO invoiceWriteDAO;

	@Override
	public Long insertInvoiceWithTx(InvoicePO po) {
		// 发票抬头处理: 设置新的默认抬头时，同时取消旧的默认抬头
		resetDefaultInvoice(po);

		int i;
		try {
			i = invoiceWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateInvoiceWithTx(InvoicePO po) {
		if (po.getIsDelete() == null || po.getIsDelete() != 1) {
			
			// 发票抬头处理: 设置新的默认抬头时，同时取消旧的默认抬头
			resetDefaultInvoice(po);
		}
		
		int i;
		i = invoiceWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteInvoiceWithTx(InvoicePO po) {
		int i;
		i = invoiceWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	
	/**
	 * 发票抬头处理: 设置新的默认抬头时，同时取消旧的默认抬头
	 * 
	 * @param po
	 */
	private void resetDefaultInvoice(InvoicePO po) {
		if (po.getIsDefault() != null && po.getIsDefault() == 1) {
			InvoicePO invoicePO = new InvoicePO();
			invoicePO.setPlatformId(po.getPlatformId());
			invoicePO.setUserId(po.getUserId());
			invoicePO.setIsDefault(0);
			invoiceWriteDAO.resetDefaultInvoice(invoicePO);
		}
	}
}
