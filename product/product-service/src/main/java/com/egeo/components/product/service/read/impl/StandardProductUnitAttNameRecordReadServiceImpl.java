package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitAttNameRecordReadService;
import com.egeo.components.product.manage.read.StandardProductUnitAttNameRecordReadManage;
import com.egeo.components.product.converter.StandardProductUnitAttNameRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttNameRecordReadService")
public class StandardProductUnitAttNameRecordReadServiceImpl  implements StandardProductUnitAttNameRecordReadService {
	@Autowired
	private StandardProductUnitAttNameRecordReadManage standardProductUnitAttNameRecordReadManage;

	@Override
	public StandardProductUnitAttNameRecordDTO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordDTO dto) {
		StandardProductUnitAttNameRecordPO po = StandardProductUnitAttNameRecordConverter.toPO(dto);
		StandardProductUnitAttNameRecordPO list = standardProductUnitAttNameRecordReadManage.findStandardProductUnitAttNameRecordById(po);		
		return StandardProductUnitAttNameRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordDTO dto, Pagination page) {
		StandardProductUnitAttNameRecordPO po = StandardProductUnitAttNameRecordConverter.toPO(dto);
        PageResult<StandardProductUnitAttNameRecordPO> pageResult = standardProductUnitAttNameRecordReadManage.findStandardProductUnitAttNameRecordOfPage(po, page);
        
        List<StandardProductUnitAttNameRecordDTO> list = StandardProductUnitAttNameRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitAttNameRecordDTO> result = new PageResult<StandardProductUnitAttNameRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordDTO dto) {
		StandardProductUnitAttNameRecordPO po = StandardProductUnitAttNameRecordConverter.toPO(dto);
		List<StandardProductUnitAttNameRecordPO> list = standardProductUnitAttNameRecordReadManage.findStandardProductUnitAttNameRecordAll(po);		
		return StandardProductUnitAttNameRecordConverter.toDTO(list);
	}
}
	