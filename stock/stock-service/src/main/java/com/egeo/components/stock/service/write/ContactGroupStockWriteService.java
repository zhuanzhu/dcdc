package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.ContactGroupStockDTO;


public interface ContactGroupStockWriteService {

	public Long insertContactGroupStockWithTx(ContactGroupStockDTO dto);

	public int updateContactGroupStockWithTx(ContactGroupStockDTO dto);

	public int deleteContactGroupStockWithTx(ContactGroupStockDTO dto);

    void increaseOneCountWithTx(ContactGroupStockDTO dto);

	void clearCountWithTx(ContactGroupStockDTO dto);

	void reduceOneCountWithTx(ContactGroupStockDTO dto);
}
	