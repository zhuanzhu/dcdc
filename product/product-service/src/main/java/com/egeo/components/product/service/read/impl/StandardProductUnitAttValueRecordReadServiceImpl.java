package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitAttValueRecordReadService;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueRecordReadManage;
import com.egeo.components.product.converter.StandardProductUnitAttValueRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttValueRecordReadService")
public class StandardProductUnitAttValueRecordReadServiceImpl  implements StandardProductUnitAttValueRecordReadService {
	@Autowired
	private StandardProductUnitAttValueRecordReadManage standardProductUnitAttValueRecordReadManage;

	@Override
	public StandardProductUnitAttValueRecordDTO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordDTO dto) {
		StandardProductUnitAttValueRecordPO po = StandardProductUnitAttValueRecordConverter.toPO(dto);
		StandardProductUnitAttValueRecordPO list = standardProductUnitAttValueRecordReadManage.findStandardProductUnitAttValueRecordById(po);		
		return StandardProductUnitAttValueRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordDTO dto, Pagination page) {
		StandardProductUnitAttValueRecordPO po = StandardProductUnitAttValueRecordConverter.toPO(dto);
        PageResult<StandardProductUnitAttValueRecordPO> pageResult = standardProductUnitAttValueRecordReadManage.findStandardProductUnitAttValueRecordOfPage(po, page);
        
        List<StandardProductUnitAttValueRecordDTO> list = StandardProductUnitAttValueRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitAttValueRecordDTO> result = new PageResult<StandardProductUnitAttValueRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordDTO dto) {
		StandardProductUnitAttValueRecordPO po = StandardProductUnitAttValueRecordConverter.toPO(dto);
		List<StandardProductUnitAttValueRecordPO> list = standardProductUnitAttValueRecordReadManage.findStandardProductUnitAttValueRecordAll(po);		
		return StandardProductUnitAttValueRecordConverter.toDTO(list);
	}
}
	