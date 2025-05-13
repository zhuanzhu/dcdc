package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitPictureReadService;
import com.egeo.components.product.manage.read.StandardUnitPictureReadManage;
import com.egeo.components.product.converter.StandardUnitPictureConverter;
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.components.product.po.StandardUnitPicturePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitPictureReadService")
public class StandardUnitPictureReadServiceImpl  implements StandardUnitPictureReadService {
	@Autowired
	private StandardUnitPictureReadManage standardUnitPictureReadManage;

	@Override
	public StandardUnitPictureDTO findStandardUnitPictureById(StandardUnitPictureDTO dto) {
		StandardUnitPicturePO po = StandardUnitPictureConverter.toPO(dto);
		StandardUnitPicturePO list = standardUnitPictureReadManage.findStandardUnitPictureById(po);		
		return StandardUnitPictureConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitPictureDTO> findStandardUnitPictureOfPage(StandardUnitPictureDTO dto, Pagination page) {
		StandardUnitPicturePO po = StandardUnitPictureConverter.toPO(dto);
        PageResult<StandardUnitPicturePO> pageResult = standardUnitPictureReadManage.findStandardUnitPictureOfPage(po, page);
        
        List<StandardUnitPictureDTO> list = StandardUnitPictureConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitPictureDTO> result = new PageResult<StandardUnitPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitPictureDTO> findStandardUnitPictureAll(StandardUnitPictureDTO dto) {
		StandardUnitPicturePO po = StandardUnitPictureConverter.toPO(dto);
		List<StandardUnitPicturePO> list = standardUnitPictureReadManage.findStandardUnitPictureAll(po);		
		return StandardUnitPictureConverter.toDTO(list);
	}
}
	