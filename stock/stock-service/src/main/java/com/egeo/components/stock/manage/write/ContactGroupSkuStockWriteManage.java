package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.ContactGroupSkuStockPO;

import java.util.List;


public interface ContactGroupSkuStockWriteManage {

	Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockPO po);

	int updateContactGroupSkuStockWithTx(ContactGroupSkuStockPO po);

	int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockPO po);

    void deleteContactGroupSkuStockByParaWithTx(ContactGroupSkuStockPO po);

	void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockPO> poList);
}
	