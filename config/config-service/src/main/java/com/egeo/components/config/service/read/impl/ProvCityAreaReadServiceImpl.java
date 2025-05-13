package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.ProvCityAreaConverter;
import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.components.config.manage.read.ProvCityAreaReadManage;
import com.egeo.components.config.po.ProvCityAreaPO;
import com.egeo.components.config.service.read.ProvCityAreaReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("provCityAreaReadService")
public class ProvCityAreaReadServiceImpl implements ProvCityAreaReadService {
	@Autowired
	private ProvCityAreaReadManage provCityAreaReadManage;

	@Override
	public ProvCityAreaDTO findProvCityAreaById(ProvCityAreaDTO dto) {
		ProvCityAreaPO po = ProvCityAreaConverter.toPO(dto);
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
	