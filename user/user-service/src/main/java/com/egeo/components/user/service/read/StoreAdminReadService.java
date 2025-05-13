package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreAdminReadService {

	public StoreAdminDTO findStoreAdminById(StoreAdminDTO dto);

	public PageResult<StoreAdminDTO> findStoreAdminOfPage(StoreAdminDTO dto,Pagination page);

	public List<StoreAdminDTO> findStoreAdminAll(StoreAdminDTO dto);
}
	