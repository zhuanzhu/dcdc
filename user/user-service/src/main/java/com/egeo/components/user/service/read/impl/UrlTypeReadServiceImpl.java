package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.UrlTypeReadService;
import com.egeo.components.user.converter.UrlTypeConverter;
import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.components.user.manage.read.UrlTypeReadManage;
import com.egeo.components.user.po.UrlTypePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("urlTypeReadService")
public class UrlTypeReadServiceImpl implements UrlTypeReadService {
	@Autowired
	private UrlTypeReadManage urlTypeReadManage;

	@Override
	public UrlTypeDTO findUrlTypeById(UrlTypeDTO dto) {
		UrlTypePO po = UrlTypeConverter.toPO(dto);
		UrlTypePO list = urlTypeReadManage.findUrlTypeById(po);		
		return UrlTypeConverter.toDTO(list);
	}

	@Override
	public PageResult<UrlTypeDTO> findUrlTypeOfPage(UrlTypeDTO dto, Pagination page) {
		UrlTypePO po = UrlTypeConverter.toPO(dto);
        PageResult<UrlTypePO> pageResult = urlTypeReadManage.findUrlTypeOfPage(po, page);
        
        List<UrlTypeDTO> list = UrlTypeConverter.toDTO(pageResult.getList());
        PageResult<UrlTypeDTO> result = new PageResult<UrlTypeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UrlTypeDTO> findUrlTypeAll(UrlTypeDTO dto) {
		UrlTypePO po = UrlTypeConverter.toPO(dto);
		List<UrlTypePO> list = urlTypeReadManage.findUrlTypeAll(po);		
		return UrlTypeConverter.toDTO(list);
	}
}
	