package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.ContactGroupPuStockDTO;

import java.util.List;


public interface ContactGroupPuStockWriteService {

	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto);

	public int updateContactGroupPuStockWithTx(ContactGroupPuStockDTO dto);

	public int deleteContactGroupPuStockWithTx(ContactGroupPuStockDTO dto);

	void deleteContactGroupPuStockByParaWithTx(ContactGroupPuStockDTO dto);

	void insertContactGroupPuListStockWithTx(List<ContactGroupPuStockDTO> list);
	
	
}
	