package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;

import java.util.List;


public interface ContactGroupSkuStockWriteService {

	public Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto);

	public int updateContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto);

	public int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto);

	void deleteContactGroupSkuStockByParaWithTx(ContactGroupSkuStockDTO dto);

	void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockDTO> list);
}
	