package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardProductUnitDescriptionReadService;
import com.egeo.components.product.manage.read.StandardProductUnitDescriptionReadManage;
import com.egeo.components.product.converter.StandardProductUnitDescriptionConverter;
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitDescriptionReadService")
public class StandardProductUnitDescriptionReadServiceImpl  implements StandardProductUnitDescriptionReadService {
	@Autowired
	private StandardProductUnitDescriptionReadManage standardProductUnitDescriptionReadManage;

	@Override
	public StandardProductUnitDescriptionDTO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionDTO dto) {
		StandardProductUnitDescriptionPO po = StandardProductUnitDescriptionConverter.toPO(dto);
		StandardProductUnitDescriptionPO list = standardProductUnitDescriptionReadManage.findStandardProductUnitDescriptionById(po);		
		return StandardProductUnitDescriptionConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionDTO dto, Pagination page) {
		StandardProductUnitDescriptionPO po = StandardProductUnitDescriptionConverter.toPO(dto);
        PageResult<StandardProductUnitDescriptionPO> pageResult = standardProductUnitDescriptionReadManage.findStandardProductUnitDescriptionOfPage(po, page);
        
        List<StandardProductUnitDescriptionDTO> list = StandardProductUnitDescriptionConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitDescriptionDTO> result = new PageResult<StandardProductUnitDescriptionDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionDTO dto) {
		StandardProductUnitDescriptionPO po = StandardProductUnitDescriptionConverter.toPO(dto);
		List<StandardProductUnitDescriptionPO> list = standardProductUnitDescriptionReadManage.findStandardProductUnitDescriptionAll(po);		
		return StandardProductUnitDescriptionConverter.toDTO(list);
	}
}
	