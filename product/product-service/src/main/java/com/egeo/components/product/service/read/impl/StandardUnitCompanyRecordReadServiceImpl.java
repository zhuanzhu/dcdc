package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitCompanyRecordReadService;
import com.egeo.components.product.manage.read.StandardUnitCompanyRecordReadManage;
import com.egeo.components.product.converter.StandardUnitCompanyRecordConverter;
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCompanyRecordReadService")
public class StandardUnitCompanyRecordReadServiceImpl  implements StandardUnitCompanyRecordReadService {
	@Autowired
	private StandardUnitCompanyRecordReadManage standardUnitCompanyRecordReadManage;

	@Override
	public StandardUnitCompanyRecordDTO findStandardUnitCompanyRecordById(StandardUnitCompanyRecordDTO dto) {
		StandardUnitCompanyRecordPO po = StandardUnitCompanyRecordConverter.toPO(dto);
		StandardUnitCompanyRecordPO list = standardUnitCompanyRecordReadManage.findStandardUnitCompanyRecordById(po);		
		return StandardUnitCompanyRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordDTO dto, Pagination page) {
		StandardUnitCompanyRecordPO po = StandardUnitCompanyRecordConverter.toPO(dto);
        PageResult<StandardUnitCompanyRecordPO> pageResult = standardUnitCompanyRecordReadManage.findStandardUnitCompanyRecordOfPage(po, page);
        
        List<StandardUnitCompanyRecordDTO> list = StandardUnitCompanyRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitCompanyRecordDTO> result = new PageResult<StandardUnitCompanyRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordDTO dto) {
		StandardUnitCompanyRecordPO po = StandardUnitCompanyRecordConverter.toPO(dto);
		List<StandardUnitCompanyRecordPO> list = standardUnitCompanyRecordReadManage.findStandardUnitCompanyRecordAll(po);		
		return StandardUnitCompanyRecordConverter.toDTO(list);
	}
}
	