package com.egeo.components.user.business.impl;
	

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.StoreAdminManage;
import com.egeo.components.user.facade.StoreAdminFacade;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeAdmin")
public class StoreAdminManageImpl implements StoreAdminManage{

	
	@Resource(name="storeAdminFacade")
	private StoreAdminFacade storeAdminFacade;

	@Override
	public StoreAdminDTO findStoreAdminById(StoreAdminDTO dto) {
		return storeAdminFacade.findStoreAdminById(dto);
	}

	@Override
	public PageResult<StoreAdminDTO> findStoreAdminOfPage(StoreAdminDTO dto, Pagination page) {
		return storeAdminFacade.findStoreAdminOfPage(dto, page);
	}

	@Override
	public Map<String, Object> findStoreAdminAll(StoreAdminDTO dto) {
		return storeAdminFacade.findStoreAdminAll(dto);
	}

	@Override
	public Long insertStoreAdminWithTx(StoreAdminDTO dto) {
		return storeAdminFacade.insertStoreAdminWithTx(dto);
	}

	@Override
	public int updateStoreAdminWithTx(StoreAdminDTO dto) {
		return storeAdminFacade.updateStoreAdminWithTx(dto);
	}

	@Override
	public int deleteStoreAdminWithTx(StoreAdminDTO dto) {
		return storeAdminFacade.deleteStoreAdminWithTx(dto);
	}


}
	