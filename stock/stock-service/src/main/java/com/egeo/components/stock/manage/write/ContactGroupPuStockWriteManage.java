package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.ContactGroupPuStockPO;

import java.util.List;


public interface ContactGroupPuStockWriteManage {

	Long insertContactGroupPuStockWithTx(ContactGroupPuStockPO po);

	int updateContactGroupPuStockWithTx(ContactGroupPuStockPO po);

	int deleteContactGroupPuStockWithTx(ContactGroupPuStockPO po);


	void deleteContactGroupPuStockByParaWithTx(ContactGroupPuStockPO po);

	void insertContactGroupPuListStockWithTx(List<ContactGroupPuStockPO> poList);
}
	