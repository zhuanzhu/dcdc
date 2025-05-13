package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoInvoiceWriteService;
import com.egeo.components.order.manage.write.SoInvoiceWriteManage;
import com.egeo.components.order.converter.SoInvoiceConverter;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.po.SoInvoicePO;

@Service("soInvoiceWriteService")
public class SoInvoiceWriteServiceImpl  implements SoInvoiceWriteService {
	@Autowired
	private SoInvoiceWriteManage soInvoiceWriteManage;

	@Override
	public Long insertSoInvoiceWithTx(SoInvoiceDTO dto,String mail) {
		SoInvoicePO po = SoInvoiceConverter.toPO(dto);
		return soInvoiceWriteManage.insertSoInvoiceWithTx(po,mail);
	}

	@Override
	public int updateSoInvoiceWithTx(SoInvoiceDTO dto) {
		SoInvoicePO po = SoInvoiceConverter.toPO(dto);
		int rt = soInvoiceWriteManage.updateSoInvoiceWithTx(po);		
		return rt;
	}
	
	@Override
	public int updateSoInvoiceWithTx(SoInvoiceDTO dto, Long commonInvoiceId) {
		SoInvoicePO po = SoInvoiceConverter.toPO(dto);
		int rt = soInvoiceWriteManage.updateSoInvoiceWithTx(po, commonInvoiceId);		
		return rt;
	}

	@Override
	public int deleteSoInvoiceWithTx(SoInvoiceDTO dto) {
		SoInvoicePO po = SoInvoiceConverter.toPO(dto);
		int rt = soInvoiceWriteManage.deleteSoInvoiceWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoInvoiceBySoId(Long soId, Integer titleType, String title, Integer invoiceType,
			String taxNo,Integer invoiceContentType) {
		return soInvoiceWriteManage.updateSoInvoiceBySoIdWithTx(soId,titleType,title,invoiceType,taxNo,invoiceContentType);
	}

	@Override
	public Long insertSoInvoiceWithTx(SoInvoiceDTO inv) {
		SoInvoicePO po = SoInvoiceConverter.toPO(inv);
		return soInvoiceWriteManage.insertSoInvoiceWithTx(po);
	}
	@Override
	public Long insertSoInvoiceWithTx(SoInvoiceDTO inv, Long commonInvoiceId) {
		SoInvoicePO po = SoInvoiceConverter.toPO(inv);
		return soInvoiceWriteManage.insertSoInvoiceWithTx(po, commonInvoiceId);
	}

	@Override
	public Integer changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId) {
		
		return soInvoiceWriteManage.changeCommonInvoiceInfoWithTx(orderId, commonInvoiceId);
	}

	@Override
	public Integer updateInvoiceTypeInfoWithTx(SoInvoiceDTO dto) {
		
		return soInvoiceWriteManage.updateInvoiceTypeInfoWithTx(SoInvoiceConverter.toPO(dto));
	}
}
	