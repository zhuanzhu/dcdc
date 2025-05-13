package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitTagRecordReadService;
import com.egeo.components.product.manage.read.StandardUnitTagRecordReadManage;
import com.egeo.components.product.converter.StandardUnitTagRecordConverter;
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.components.product.po.StandardUnitTagRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitTagRecordReadService")
public class StandardUnitTagRecordReadServiceImpl  implements StandardUnitTagRecordReadService {
	@Autowired
	private StandardUnitTagRecordReadManage standardUnitTagRecordReadManage;

	@Override
	public StandardUnitTagRecordDTO findStandardUnitTagRecordById(StandardUnitTagRecordDTO dto) {
		StandardUnitTagRecordPO po = StandardUnitTagRecordConverter.toPO(dto);
		StandardUnitTagRecordPO list = standardUnitTagRecordReadManage.findStandardUnitTagRecordById(po);		
		return StandardUnitTagRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitTagRecordDTO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordDTO dto, Pagination page) {
		StandardUnitTagRecordPO po = StandardUnitTagRecordConverter.toPO(dto);
        PageResult<StandardUnitTagRecordPO> pageResult = standardUnitTagRecordReadManage.findStandardUnitTagRecordOfPage(po, page);
        
        List<StandardUnitTagRecordDTO> list = StandardUnitTagRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitTagRecordDTO> result = new PageResult<StandardUnitTagRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitTagRecordDTO> findStandardUnitTagRecordAll(StandardUnitTagRecordDTO dto) {
		StandardUnitTagRecordPO po = StandardUnitTagRecordConverter.toPO(dto);
		List<StandardUnitTagRecordPO> list = standardUnitTagRecordReadManage.findStandardUnitTagRecordAll(po);		
		return StandardUnitTagRecordConverter.toDTO(list);
	}
}
	