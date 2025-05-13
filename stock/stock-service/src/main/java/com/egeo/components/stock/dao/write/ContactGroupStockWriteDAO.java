package com.egeo.components.stock.dao.write;

import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.orm.BaseWriteDAO;

public interface ContactGroupStockWriteDAO extends BaseWriteDAO<ContactGroupStockPO> {

    void increaseOneCountWithTx(ContactGroupStockPO po);

    void clearCountWithTx(ContactGroupStockPO po);

    void reduceOneCountWithTx(ContactGroupStockPO po);
}
	