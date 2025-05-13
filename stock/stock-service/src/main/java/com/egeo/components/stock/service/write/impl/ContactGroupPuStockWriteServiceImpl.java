package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.ContactGroupPuStockWriteService;
import com.egeo.components.stock.manage.write.ContactGroupPuStockWriteManage;
import com.egeo.components.stock.converter.ContactGroupPuStockConverter;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.po.ContactGroupPuStockPO;

import java.util.List;

@Service("contactGroupPuStockWriteService")
public class ContactGroupPuStockWriteServiceImpl  implements ContactGroupPuStockWriteService {
	@Autowired
	private ContactGroupPuStockWriteManage contactGroupPuStockWriteManage;

	@Override
	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
		Long rt = contactGroupPuStockWriteManage.insertContactGroupPuStockWithTx(po);		
		return rt;
	}

	@Override
	public int updateContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
		int rt = contactGroupPuStockWriteManage.updateContactGroupPuStockWithTx(po);		
		return rt;
	}

	@Override
	public int deleteContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
		int rt = contactGroupPuStockWriteManage.deleteContactGroupPuStockWithTx(po);		
		return rt;
	}

	@Override
	public void deleteContactGroupPuStockByParaWithTx(ContactGroupPuStockDTO dto) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
		contactGroupPuStockWriteManage.deleteContactGroupPuStockByParaWithTx(po);
	}

	@Override
	public void insertContactGroupPuListStockWithTx(List<ContactGroupPuStockDTO> list) {
		List<ContactGroupPuStockPO> poList = ContactGroupPuStockConverter.toPO(list);
		contactGroupPuStockWriteManage.insertContactGroupPuListStockWithTx(poList);
	}
}
	