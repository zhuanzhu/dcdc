package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitClientRecordReadService;
import com.egeo.components.product.manage.read.StandardUnitClientRecordReadManage;
import com.egeo.components.product.converter.StandardUnitClientRecordConverter;
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.components.product.po.StandardUnitClientRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitClientRecordReadService")
public class StandardUnitClientRecordReadServiceImpl  implements StandardUnitClientRecordReadService {
	@Autowired
	private StandardUnitClientRecordReadManage standardUnitClientRecordReadManage;

	@Override
	public StandardUnitClientRecordDTO findStandardUnitClientRecordById(StandardUnitClientRecordDTO dto) {
		StandardUnitClientRecordPO po = StandardUnitClientRecordConverter.toPO(dto);
		StandardUnitClientRecordPO list = standardUnitClientRecordReadManage.findStandardUnitClientRecordById(po);		
		return StandardUnitClientRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitClientRecordDTO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordDTO dto, Pagination page) {
		StandardUnitClientRecordPO po = StandardUnitClientRecordConverter.toPO(dto);
        PageResult<StandardUnitClientRecordPO> pageResult = standardUnitClientRecordReadManage.findStandardUnitClientRecordOfPage(po, page);
        
        List<StandardUnitClientRecordDTO> list = StandardUnitClientRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitClientRecordDTO> result = new PageResult<StandardUnitClientRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitClientRecordDTO> findStandardUnitClientRecordAll(StandardUnitClientRecordDTO dto) {
		StandardUnitClientRecordPO po = StandardUnitClientRecordConverter.toPO(dto);
		List<StandardUnitClientRecordPO> list = standardUnitClientRecordReadManage.findStandardUnitClientRecordAll(po);		
		return StandardUnitClientRecordConverter.toDTO(list);
	}
}
	