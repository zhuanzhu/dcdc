package com.egeo.components.stock.dao.write;

import com.egeo.components.stock.po.ContactGroupPuStockPO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface ContactGroupPuStockWriteDAO extends BaseWriteDAO<ContactGroupPuStockPO> {

    void insertContactGroupPuListStockWithTx(List<ContactGroupPuStockPO> list);

}
	