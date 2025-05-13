package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.AdCodeReadService;
import com.egeo.components.stock.manage.read.AdCodeReadManage;
import com.egeo.components.stock.converter.AdCodeConverter;
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.components.stock.po.AdCodePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("adCodeReadService")
public class AdCodeReadServiceImpl  implements AdCodeReadService {
	@Autowired
	private AdCodeReadManage adCodeReadManage;

	@Override
	public AdCodeDTO findAdCodeById(AdCodeDTO dto) {
		AdCodePO po = AdCodeConverter.toPO(dto);
		AdCodePO list = adCodeReadManage.findAdCodeById(po);		
		return AdCodeConverter.toDTO(list);
	}

	@Override
	public PageResult<AdCodeDTO> findAdCodeOfPage(AdCodeDTO dto, Pagination page) {
		AdCodePO po = AdCodeConverter.toPO(dto);
        PageResult<AdCodePO> pageResult = adCodeReadManage.findAdCodeOfPage(po, page);
        
        List<AdCodeDTO> list = AdCodeConverter.toDTO(pageResult.getList());
        PageResult<AdCodeDTO> result = new PageResult<AdCodeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AdCodeDTO> findAdCodeAll(AdCodeDTO dto) {
		AdCodePO po = AdCodeConverter.toPO(dto);
		List<AdCodePO> list = adCodeReadManage.findAdCodeAll(po);		
		return AdCodeConverter.toDTO(list);
	}
}
	