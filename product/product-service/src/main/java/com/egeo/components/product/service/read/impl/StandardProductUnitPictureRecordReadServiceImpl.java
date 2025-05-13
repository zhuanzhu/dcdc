package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitPictureRecordReadService;
import com.egeo.components.product.manage.read.StandardProductUnitPictureRecordReadManage;
import com.egeo.components.product.converter.StandardProductUnitPictureRecordConverter;
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitPictureRecordReadService")
public class StandardProductUnitPictureRecordReadServiceImpl  implements StandardProductUnitPictureRecordReadService {
	@Autowired
	private StandardProductUnitPictureRecordReadManage standardProductUnitPictureRecordReadManage;

	@Override
	public StandardProductUnitPictureRecordDTO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordDTO dto) {
		StandardProductUnitPictureRecordPO po = StandardProductUnitPictureRecordConverter.toPO(dto);
		StandardProductUnitPictureRecordPO list = standardProductUnitPictureRecordReadManage.findStandardProductUnitPictureRecordById(po);		
		return StandardProductUnitPictureRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordDTO dto, Pagination page) {
		StandardProductUnitPictureRecordPO po = StandardProductUnitPictureRecordConverter.toPO(dto);
        PageResult<StandardProductUnitPictureRecordPO> pageResult = standardProductUnitPictureRecordReadManage.findStandardProductUnitPictureRecordOfPage(po, page);
        
        List<StandardProductUnitPictureRecordDTO> list = StandardProductUnitPictureRecordConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitPictureRecordDTO> result = new PageResult<StandardProductUnitPictureRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordDTO dto) {
		StandardProductUnitPictureRecordPO po = StandardProductUnitPictureRecordConverter.toPO(dto);
		List<StandardProductUnitPictureRecordPO> list = standardProductUnitPictureRecordReadManage.findStandardProductUnitPictureRecordAll(po);		
		return StandardProductUnitPictureRecordConverter.toDTO(list);
	}
}
	