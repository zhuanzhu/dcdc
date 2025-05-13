package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitDescriptionRecordReadService;
import com.egeo.components.product.manage.read.StandardProductUnitDescriptionRecordReadManage;
import com.egeo.components.product.converter.StandardProductUnitDescriptionRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitDescriptionRecordReadService")
public class StandardProductUnitDescriptionRecordReadServiceImpl  implements StandardProductUnitDescriptionRecordReadService {
	@Autowired
	private StandardProductUnitDescriptionRecordReadManage standardProductUnitDescriptionRecordReadManage;

	@Override
	public StandardProductUnitDescriptionRecordDTO findStandardProductUnitDescriptionRecordById(StandardProductUnitDescriptionRecordDTO dto) {
		StandardProductUnitDescriptionRecordPO po = StandardProductUnitDescriptionRecordConverter.toPO(dto);
		StandardProductUnitDescriptionRecordPO list = standardProductUnitDescriptionRecordReadManage.findStandardProductUnitDescriptionRecordById(po);		
		return StandardProductUnitDescriptionRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordDTO dto, Pagination page) {
		StandardProductUnitDescriptionRecordPO po = StandardProductUnitDescriptionRecordConverter.toPO(dto);
        PageResult<StandardProductUnitDescriptionRecordPO> pageResult = standardProductUnitDescriptionRecordReadManage.findStandardProductUnitDescriptionRecordOfPage(po, page);
        
        List<StandardProductUnitDescriptionRecordDTO> list = StandardProductUnitDescriptionRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitDescriptionRecordDTO> result = new PageResult<StandardProductUnitDescriptionRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordDTO dto) {
		StandardProductUnitDescriptionRecordPO po = StandardProductUnitDescriptionRecordConverter.toPO(dto);
		List<StandardProductUnitDescriptionRecordPO> list = standardProductUnitDescriptionRecordReadManage.findStandardProductUnitDescriptionRecordAll(po);		
		return StandardProductUnitDescriptionRecordConverter.toDTO(list);
	}
}
	