package com.egeo.components.user.business;

import java.util.Map;

import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreAdminManage {

	public StoreAdminDTO findStoreAdminById(StoreAdminDTO dto);	

	public PageResult<StoreAdminDTO> findStoreAdminOfPage(StoreAdminDTO dto,Pagination page);

	public Map<String, Object> findStoreAdminAll(StoreAdminDTO dto);

	Long insertStoreAdminWithTx(StoreAdminDTO dto);

	int updateStoreAdminWithTx(StoreAdminDTO dto);

	int deleteStoreAdminWithTx(StoreAdminDTO dto);
}
	