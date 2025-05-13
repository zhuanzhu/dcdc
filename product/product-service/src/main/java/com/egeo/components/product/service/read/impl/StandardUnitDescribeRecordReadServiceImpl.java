package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitDescribeRecordReadService;
import com.egeo.components.product.manage.read.StandardUnitDescribeRecordReadManage;
import com.egeo.components.product.converter.StandardUnitDescribeRecordConverter;
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.components.product.po.StandardUnitDescribeRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitDescribeRecordReadService")
public class StandardUnitDescribeRecordReadServiceImpl  implements StandardUnitDescribeRecordReadService {
	@Autowired
	private StandardUnitDescribeRecordReadManage standardUnitDescribeRecordReadManage;

	@Override
	public StandardUnitDescribeRecordDTO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordDTO dto) {
		StandardUnitDescribeRecordPO po = StandardUnitDescribeRecordConverter.toPO(dto);
		StandardUnitDescribeRecordPO list = standardUnitDescribeRecordReadManage.findStandardUnitDescribeRecordById(po);		
		return StandardUnitDescribeRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordDTO dto, Pagination page) {
		StandardUnitDescribeRecordPO po = StandardUnitDescribeRecordConverter.toPO(dto);
        PageResult<StandardUnitDescribeRecordPO> pageResult = standardUnitDescribeRecordReadManage.findStandardUnitDescribeRecordOfPage(po, page);
        
        List<StandardUnitDescribeRecordDTO> list = StandardUnitDescribeRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitDescribeRecordDTO> result = new PageResult<StandardUnitDescribeRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordDTO dto) {
		StandardUnitDescribeRecordPO po = StandardUnitDescribeRecordConverter.toPO(dto);
		List<StandardUnitDescribeRecordPO> list = standardUnitDescribeRecordReadManage.findStandardUnitDescribeRecordAll(po);		
		return StandardUnitDescribeRecordConverter.toDTO(list);
	}
}
	