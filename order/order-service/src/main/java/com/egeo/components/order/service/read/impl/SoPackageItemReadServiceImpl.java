package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoPackageItemReadService;
import com.egeo.components.order.manage.read.SoPackageItemReadManage;
import com.egeo.components.order.converter.SoPackageItemConverter;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.components.order.po.SoPackageItemPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soPackageItemReadService")
public class SoPackageItemReadServiceImpl  implements SoPackageItemReadService {
	@Autowired
	private SoPackageItemReadManage soPackageItemReadManage;

	@Override
	public SoPackageItemDTO findSoPackageItemById(SoPackageItemDTO dto) {
		SoPackageItemPO po = SoPackageItemConverter.toPO(dto);
		SoPackageItemPO list = soPackageItemReadManage.findSoPackageItemById(po);		
		return SoPackageItemConverter.toDTO(list);
	}

	@Override
	public PageResult<SoPackageItemDTO> findSoPackageItemOfPage(SoPackageItemDTO dto, Pagination page) {
		SoPackageItemPO po = SoPackageItemConverter.toPO(dto);
        PageResult<SoPackageItemPO> pageResult = soPackageItemReadManage.findSoPackageItemOfPage(po, page);
        
        List<SoPackageItemDTO> list = SoPackageItemConverter.toDTO(pageResult.getList());
        PageResult<SoPackageItemDTO> result = new PageResult<SoPackageItemDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO dto) {
		SoPackageItemPO po = SoPackageItemConverter.toPO(dto);
		List<SoPackageItemPO> list = soPackageItemReadManage.findSoPackageItemAll(po);		
		return SoPackageItemConverter.toDTO(list);
	}
}
	