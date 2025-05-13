package com.egeo.components.stock.dao.read;

import java.util.List;

import com.egeo.components.stock.po.ContactGroupPuStockPO;
import com.egeo.orm.BaseReadDAO;

public interface ContactGroupPuStockReadDAO extends BaseReadDAO<ContactGroupPuStockPO>{
	
	public List<Long> findPuIdListByPuId(Long puid);

	/**
	 * 根据contactGroupSkuId查询所有共用pu
	 * @param contactGroupSkuId
	 * @return
	 */
	public List<ContactGroupPuStockPO> findContactGroupPuStockBySkuId(Long contactGroupSkuId);
}
	