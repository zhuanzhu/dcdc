package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitRecordReadService;
import com.egeo.components.product.manage.read.StandardProductUnitRecordReadManage;
import com.egeo.components.product.converter.StandardProductUnitRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.components.product.po.StandardProductUnitRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitRecordReadService")
public class StandardProductUnitRecordReadServiceImpl  implements StandardProductUnitRecordReadService {
	@Autowired
	private StandardProductUnitRecordReadManage standardProductUnitRecordReadManage;

	@Override
	public StandardProductUnitRecordDTO findStandardProductUnitRecordById(StandardProductUnitRecordDTO dto) {
		StandardProductUnitRecordPO po = StandardProductUnitRecordConverter.toPO(dto);
		StandardProductUnitRecordPO list = standardProductUnitRecordReadManage.findStandardProductUnitRecordById(po);		
		return StandardProductUnitRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitRecordDTO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordDTO dto, Pagination page) {
		StandardProductUnitRecordPO po = StandardProductUnitRecordConverter.toPO(dto);
        PageResult<StandardProductUnitRecordPO> pageResult = standardProductUnitRecordReadManage.findStandardProductUnitRecordOfPage(po, page);
        
        List<StandardProductUnitRecordDTO> list = StandardProductUnitRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitRecordDTO> result = new PageResult<StandardProductUnitRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitRecordDTO> findStandardProductUnitRecordAll(StandardProductUnitRecordDTO dto) {
		StandardProductUnitRecordPO po = StandardProductUnitRecordConverter.toPO(dto);
		List<StandardProductUnitRecordPO> list = standardProductUnitRecordReadManage.findStandardProductUnitRecordAll(po);		
		return StandardProductUnitRecordConverter.toDTO(list);
	}
}
	