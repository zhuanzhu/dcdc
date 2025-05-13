package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoPackageItemManage;
import com.egeo.components.order.facade.SoPackageItemFacade;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soPackageItem")
public class SoPackageItemManageImpl implements SoPackageItemManage{

	
	@Resource(name="soPackageItemFacade")
	private SoPackageItemFacade soPackageItemFacade;

	@Override
	public SoPackageItemDTO findSoPackageItemById(SoPackageItemDTO dto) {
		return soPackageItemFacade.findSoPackageItemById(dto);
	}

	@Override
	public PageResult<SoPackageItemDTO> findSoPackageItemOfPage(SoPackageItemDTO dto, Pagination page) {
		return soPackageItemFacade.findSoPackageItemOfPage(dto, page);
	}

	@Override
	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO dto) {
		return soPackageItemFacade.findSoPackageItemAll(dto);
	}

	@Override
	public int insertSoPackageItemWithTx(SoPackageItemDTO dto) {
		return soPackageItemFacade.insertSoPackageItemWithTx(dto);
	}

	@Override
	public int updateSoPackageItemWithTx(SoPackageItemDTO dto) {
		return soPackageItemFacade.updateSoPackageItemWithTx(dto);
	}

	@Override
	public int deleteSoPackageItemWithTx(SoPackageItemDTO dto) {
		return soPackageItemFacade.deleteSoPackageItemWithTx(dto);
	}


}
	