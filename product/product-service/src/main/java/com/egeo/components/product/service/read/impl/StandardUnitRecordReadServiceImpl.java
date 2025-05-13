package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitRecordReadService;
import com.egeo.components.product.manage.read.StandardUnitRecordReadManage;
import com.egeo.components.product.converter.StandardUnitRecordConverter;
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.components.product.po.StandardUnitRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitRecordReadService")
public class StandardUnitRecordReadServiceImpl  implements StandardUnitRecordReadService {
	@Autowired
	private StandardUnitRecordReadManage standardUnitRecordReadManage;

	@Override
	public StandardUnitRecordDTO findStandardUnitRecordById(StandardUnitRecordDTO dto) {
		StandardUnitRecordPO po = StandardUnitRecordConverter.toPO(dto);
		StandardUnitRecordPO list = standardUnitRecordReadManage.findStandardUnitRecordById(po);		
		return StandardUnitRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitRecordDTO> findStandardUnitRecordOfPage(StandardUnitRecordDTO dto, Pagination page) {
		StandardUnitRecordPO po = StandardUnitRecordConverter.toPO(dto);
        PageResult<StandardUnitRecordPO> pageResult = standardUnitRecordReadManage.findStandardUnitRecordOfPage(po, page);
        
        List<StandardUnitRecordDTO> list = StandardUnitRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitRecordDTO> result = new PageResult<StandardUnitRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitRecordDTO> findStandardUnitRecordAll(StandardUnitRecordDTO dto) {
		StandardUnitRecordPO po = StandardUnitRecordConverter.toPO(dto);
		List<StandardUnitRecordPO> list = standardUnitRecordReadManage.findStandardUnitRecordAll(po);		
		return StandardUnitRecordConverter.toDTO(list);
	}
}
	