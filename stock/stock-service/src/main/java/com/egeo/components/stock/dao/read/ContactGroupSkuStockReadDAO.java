package com.egeo.components.stock.dao.read;

import java.util.List;

import com.egeo.components.stock.po.ContactGroupSkuStockPO;
import com.egeo.orm.BaseReadDAO;

public interface ContactGroupSkuStockReadDAO extends BaseReadDAO<ContactGroupSkuStockPO>{

	List<ContactGroupSkuStockPO> findBySuId(Long suId);
}
	