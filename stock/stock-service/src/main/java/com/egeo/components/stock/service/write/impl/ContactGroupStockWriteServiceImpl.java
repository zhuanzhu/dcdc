package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.write.ContactGroupStockWriteService;
import com.egeo.components.stock.manage.write.ContactGroupStockWriteManage;
import com.egeo.components.stock.converter.ContactGroupStockConverter;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.po.ContactGroupStockPO;


@Service("contactGroupStockWriteService")
public class ContactGroupStockWriteServiceImpl  implements ContactGroupStockWriteService {
	@Autowired
	private ContactGroupStockWriteManage contactGroupStockWriteManage;

	@Override
	public Long insertContactGroupStockWithTx(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		Long rt = contactGroupStockWriteManage.insertContactGroupStockWithTx(po);		
		return rt;
	}

	@Override
	public int updateContactGroupStockWithTx(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		int rt = contactGroupStockWriteManage.updateContactGroupStockWithTx(po);		
		return rt;
	}

	@Override
	public int deleteContactGroupStockWithTx(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		int rt = contactGroupStockWriteManage.deleteContactGroupStockWithTx(po);		
		return rt;
	}

	@Override
	public void increaseOneCountWithTx(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		contactGroupStockWriteManage.increaseOneCountWithTx(po);
	}

	@Override
	public void clearCountWithTx(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		contactGroupStockWriteManage.clearCountWithTx(po);
	}

	@Override
	public void reduceOneCountWithTx(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		contactGroupStockWriteManage.reduceOneCountWithTx(po);
	}


}
	