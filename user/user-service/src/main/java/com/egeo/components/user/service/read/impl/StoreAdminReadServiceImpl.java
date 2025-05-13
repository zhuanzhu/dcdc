package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.StoreAdminReadService;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.po.StoreAdminPO;
import com.egeo.components.user.dao.read.StoreAdminReadDAO;
import com.egeo.components.user.converter.StoreAdminConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("storeAdminReadService")
public class StoreAdminReadServiceImpl implements StoreAdminReadService {
	@Autowired
	private StoreAdminReadDAO storeAdminReadDAO ;

	@Override
	public StoreAdminDTO findStoreAdminById(StoreAdminDTO dto){
		StoreAdminPO po = StoreAdminConverter.toPO(dto);
		StoreAdminPO storeAdminpo = new StoreAdminPO();
		storeAdminpo.setId(po.getId());
		StoreAdminPO list = storeAdminReadDAO.findById(storeAdminpo);
		return StoreAdminConverter.toDTO(list);
	}
	@Override
	public PageResult<StoreAdminDTO> findStoreAdminOfPage(StoreAdminDTO dto, Pagination page) {
		StoreAdminPO po = StoreAdminConverter.toPO(dto);
		PageResult<StoreAdminPO> pageResult = new PageResult<StoreAdminPO>();
		List<StoreAdminPO> listT = null;
		int cnt = storeAdminReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = storeAdminReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<StoreAdminPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<StoreAdminDTO> list = StoreAdminConverter.toDTO(pageResult.getList());
        PageResult<StoreAdminDTO> result = new PageResult<StoreAdminDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreAdminDTO> findStoreAdminAll(StoreAdminDTO dto) {
		StoreAdminPO po = StoreAdminConverter.toPO(dto);
		List<StoreAdminPO> list = storeAdminReadDAO.findAll(po,null);
		return StoreAdminConverter.toDTO(list);
	}
}
