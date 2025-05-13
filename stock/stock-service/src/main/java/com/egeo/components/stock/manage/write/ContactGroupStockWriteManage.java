package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.ContactGroupStockPO;


public interface ContactGroupStockWriteManage {

	Long insertContactGroupStockWithTx(ContactGroupStockPO po);

	int updateContactGroupStockWithTx(ContactGroupStockPO po);

	int deleteContactGroupStockWithTx(ContactGroupStockPO po);

    void increaseOneCountWithTx(ContactGroupStockPO po);

	void clearCountWithTx(ContactGroupStockPO po);

	void reduceOneCountWithTx(ContactGroupStockPO po);
}
	