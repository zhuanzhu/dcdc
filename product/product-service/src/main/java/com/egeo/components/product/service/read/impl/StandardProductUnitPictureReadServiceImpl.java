package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitPictureReadService;
import com.egeo.components.product.manage.read.StandardProductUnitPictureReadManage;
import com.egeo.components.product.converter.StandardProductUnitPictureConverter;
import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.components.product.po.StandardProductUnitPicturePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitPictureReadService")
public class StandardProductUnitPictureReadServiceImpl  implements StandardProductUnitPictureReadService {
	@Autowired
	private StandardProductUnitPictureReadManage standardProductUnitPictureReadManage;

	@Override
	public StandardProductUnitPictureDTO findStandardProductUnitPictureById(StandardProductUnitPictureDTO dto) {
		StandardProductUnitPicturePO po = StandardProductUnitPictureConverter.toPO(dto);
		StandardProductUnitPicturePO list = standardProductUnitPictureReadManage.findStandardProductUnitPictureById(po);		
		return StandardProductUnitPictureConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitPictureDTO> findStandardProductUnitPictureOfPage(StandardProductUnitPictureDTO dto, Pagination page) {
		StandardProductUnitPicturePO po = StandardProductUnitPictureConverter.toPO(dto);
        PageResult<StandardProductUnitPicturePO> pageResult = standardProductUnitPictureReadManage.findStandardProductUnitPictureOfPage(po, page);
        
        List<StandardProductUnitPictureDTO> list = StandardProductUnitPictureConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitPictureDTO> result = new PageResult<StandardProductUnitPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitPictureDTO> findStandardProductUnitPictureAll(StandardProductUnitPictureDTO dto) {
		StandardProductUnitPicturePO po = StandardProductUnitPictureConverter.toPO(dto);
		List<StandardProductUnitPicturePO> list = standardProductUnitPictureReadManage.findStandardProductUnitPictureAll(po);		
		return StandardProductUnitPictureConverter.toDTO(list);
	}
}
	