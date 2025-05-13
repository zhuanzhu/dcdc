package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoDeviceReadService;
import com.egeo.components.order.manage.read.SoDeviceReadManage;
import com.egeo.components.order.converter.SoDeviceConverter;
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.po.SoDevicePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soDeviceReadService")
public class SoDeviceReadServiceImpl  implements SoDeviceReadService {
	@Autowired
	private SoDeviceReadManage soDeviceReadManage;

	@Override
	public SoDeviceDTO findSoDeviceById(SoDeviceDTO dto) {
		SoDevicePO po = SoDeviceConverter.toPO(dto);
		SoDevicePO list = soDeviceReadManage.findSoDeviceById(po);		
		return SoDeviceConverter.toDTO(list);
	}

	@Override
	public PageResult<SoDeviceDTO> findSoDeviceOfPage(SoDeviceDTO dto, Pagination page) {
		SoDevicePO po = SoDeviceConverter.toPO(dto);
        PageResult<SoDevicePO> pageResult = soDeviceReadManage.findSoDeviceOfPage(po, page);
        
        List<SoDeviceDTO> list = SoDeviceConverter.toDTO(pageResult.getList());
        PageResult<SoDeviceDTO> result = new PageResult<SoDeviceDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoDeviceDTO> findSoDeviceAll(SoDeviceDTO dto) {
		SoDevicePO po = SoDeviceConverter.toPO(dto);
		List<SoDevicePO> list = soDeviceReadManage.findSoDeviceAll(po);		
		return SoDeviceConverter.toDTO(list);
	}
}
	