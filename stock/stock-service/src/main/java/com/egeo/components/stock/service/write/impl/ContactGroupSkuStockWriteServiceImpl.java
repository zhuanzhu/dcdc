package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.ContactGroupSkuStockWriteService;
import com.egeo.components.stock.manage.write.ContactGroupSkuStockWriteManage;
import com.egeo.components.stock.converter.ContactGroupSkuStockConverter;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.po.ContactGroupSkuStockPO;

import java.util.List;

@Service("contactGroupSkuStockWriteService")
public class ContactGroupSkuStockWriteServiceImpl  implements ContactGroupSkuStockWriteService {
	@Autowired
	private ContactGroupSkuStockWriteManage contactGroupSkuStockWriteManage;

	@Override
	public Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
		Long rt = contactGroupSkuStockWriteManage.insertContactGroupSkuStockWithTx(po);		
		return rt;
	}

	@Override
	public int updateContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
		int rt = contactGroupSkuStockWriteManage.updateContactGroupSkuStockWithTx(po);		
		return rt;
	}

	@Override
	public int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
		int rt = contactGroupSkuStockWriteManage.deleteContactGroupSkuStockWithTx(po);		
		return rt;
	}

	@Override
	public void deleteContactGroupSkuStockByParaWithTx(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
		contactGroupSkuStockWriteManage.deleteContactGroupSkuStockByParaWithTx(po);
	}

	@Override
	public void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockDTO> list) {
		List<ContactGroupSkuStockPO> poList = ContactGroupSkuStockConverter.toPO(list);
		contactGroupSkuStockWriteManage.insertContactGroupSkuStockListWithTx(poList);
	}
}
	