package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitPictureRecordReadService;
import com.egeo.components.product.manage.read.StandardUnitPictureRecordReadManage;
import com.egeo.components.product.converter.StandardUnitPictureRecordConverter;
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.components.product.po.StandardUnitPictureRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitPictureRecordReadService")
public class StandardUnitPictureRecordReadServiceImpl  implements StandardUnitPictureRecordReadService {
	@Autowired
	private StandardUnitPictureRecordReadManage standardUnitPictureRecordReadManage;

	@Override
	public StandardUnitPictureRecordDTO findStandardUnitPictureRecordById(StandardUnitPictureRecordDTO dto) {
		StandardUnitPictureRecordPO po = StandardUnitPictureRecordConverter.toPO(dto);
		StandardUnitPictureRecordPO list = standardUnitPictureRecordReadManage.findStandardUnitPictureRecordById(po);		
		return StandardUnitPictureRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordDTO dto, Pagination page) {
		StandardUnitPictureRecordPO po = StandardUnitPictureRecordConverter.toPO(dto);
        PageResult<StandardUnitPictureRecordPO> pageResult = standardUnitPictureRecordReadManage.findStandardUnitPictureRecordOfPage(po, page);
        
        List<StandardUnitPictureRecordDTO> list = StandardUnitPictureRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitPictureRecordDTO> result = new PageResult<StandardUnitPictureRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordDTO dto) {
		StandardUnitPictureRecordPO po = StandardUnitPictureRecordConverter.toPO(dto);
		List<StandardUnitPictureRecordPO> list = standardUnitPictureRecordReadManage.findStandardUnitPictureRecordAll(po);		
		return StandardUnitPictureRecordConverter.toDTO(list);
	}
}
	