package com.egeo.components.stock.dao.write;

import com.egeo.components.stock.po.ContactGroupSkuStockPO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface ContactGroupSkuStockWriteDAO extends BaseWriteDAO<ContactGroupSkuStockPO> {

    void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockPO> list);

}
	