package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitTagReadService;
import com.egeo.components.product.manage.read.StandardUnitTagReadManage;
import com.egeo.components.product.converter.StandardUnitTagConverter;
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.components.product.po.StandardUnitTagPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitTagReadService")
public class StandardUnitTagReadServiceImpl  implements StandardUnitTagReadService {
	@Autowired
	private StandardUnitTagReadManage standardUnitTagReadManage;

	@Override
	public StandardUnitTagDTO findStandardUnitTagById(StandardUnitTagDTO dto) {
		StandardUnitTagPO po = StandardUnitTagConverter.toPO(dto);
		StandardUnitTagPO list = standardUnitTagReadManage.findStandardUnitTagById(po);		
		return StandardUnitTagConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitTagDTO> findStandardUnitTagOfPage(StandardUnitTagDTO dto, Pagination page) {
		StandardUnitTagPO po = StandardUnitTagConverter.toPO(dto);
        PageResult<StandardUnitTagPO> pageResult = standardUnitTagReadManage.findStandardUnitTagOfPage(po, page);
        
        List<StandardUnitTagDTO> list = StandardUnitTagConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitTagDTO> result = new PageResult<StandardUnitTagDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitTagDTO> findStandardUnitTagAll(StandardUnitTagDTO dto) {
		StandardUnitTagPO po = StandardUnitTagConverter.toPO(dto);
		List<StandardUnitTagPO> list = standardUnitTagReadManage.findStandardUnitTagAll(po);		
		return StandardUnitTagConverter.toDTO(list);
	}
}
	