package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.ProvCityAreaReadService;
import com.egeo.components.order.manage.read.ProvCityAreaReadManage;
import com.egeo.components.order.converter.ProvCityAreaConverter;
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.components.order.po.ProvCityAreaPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("provCityAreaReadService")
public class ProvCityAreaReadServiceImpl  implements ProvCityAreaReadService {
	@Autowired
	private ProvCityAreaReadManage provCityAreaReadManage;

	@Override
	public ProvCityAreaDTO findProvCityAreaById(Long id) {
		ProvCityAreaPO po = new ProvCityAreaPO();
		po.setId(id);
		ProvCityAreaPO list = provCityAreaReadManage.findProvCityAreaById(po);		
		return ProvCityAreaConverter.toDTO(list);
	}

	@Override
	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto, Pagination page) {
		ProvCityAreaPO po = ProvCityAreaConverter.toPO(dto);
        PageResult<ProvCityAreaPO> pageResult = provCityAreaReadManage.findProvCityAreaOfPage(po, page);
        
        List<ProvCityAreaDTO> list = ProvCityAreaConverter.toDTO(pageResult.getList());
        PageResult<ProvCityAreaDTO> result = new PageResult<ProvCityAreaDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto) {
		ProvCityAreaPO po = ProvCityAreaConverter.toPO(dto);
		List<ProvCityAreaPO> list = provCityAreaReadManage.findProvCityAreaAll(po);		
		return ProvCityAreaConverter.toDTO(list);
	}
}
	