package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoPackageBoxReadService;
import com.egeo.components.order.manage.read.SoPackageBoxReadManage;
import com.egeo.components.order.converter.SoPackageBoxConverter;
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.po.SoPackageBoxPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soPackageBoxReadService")
public class SoPackageBoxReadServiceImpl  implements SoPackageBoxReadService {
	@Autowired
	private SoPackageBoxReadManage soPackageBoxReadManage;

	@Override
	public SoPackageBoxDTO findSoPackageBoxById(SoPackageBoxDTO dto) {
		SoPackageBoxPO po = SoPackageBoxConverter.toPO(dto);
		SoPackageBoxPO list = soPackageBoxReadManage.findSoPackageBoxById(po);		
		return SoPackageBoxConverter.toDTO(list);
	}

	@Override
	public PageResult<SoPackageBoxDTO> findSoPackageBoxOfPage(SoPackageBoxDTO dto, Pagination page) {
		SoPackageBoxPO po = SoPackageBoxConverter.toPO(dto);
        PageResult<SoPackageBoxPO> pageResult = soPackageBoxReadManage.findSoPackageBoxOfPage(po, page);
        
        List<SoPackageBoxDTO> list = SoPackageBoxConverter.toDTO(pageResult.getList());
        PageResult<SoPackageBoxDTO> result = new PageResult<SoPackageBoxDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoPackageBoxDTO> findSoPackageBoxAll(SoPackageBoxDTO dto) {
		SoPackageBoxPO po = SoPackageBoxConverter.toPO(dto);
		List<SoPackageBoxPO> list = soPackageBoxReadManage.findSoPackageBoxAll(po);		
		return SoPackageBoxConverter.toDTO(list);
	}

	@Override
	public SoPackageBoxDTO queryBoxByBoxCodeAndChildId(Long boxCode,Long soChildId) {
		return SoPackageBoxConverter.toDTO(soPackageBoxReadManage.queryBoxByBoxCodeAndChildId(boxCode,soChildId));
	}
}
	