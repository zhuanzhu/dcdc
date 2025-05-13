package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitRecordStoreReadService;
import com.egeo.components.product.manage.read.StandardUnitRecordStoreReadManage;
import com.egeo.components.product.converter.StandardUnitRecordStoreConverter;
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.components.product.po.StandardUnitRecordStorePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitRecordStoreReadService")
public class StandardUnitRecordStoreReadServiceImpl  implements StandardUnitRecordStoreReadService {
	@Autowired
	private StandardUnitRecordStoreReadManage standardUnitRecordStoreReadManage;

	@Override
	public StandardUnitRecordStoreDTO findStandardUnitRecordStoreById(StandardUnitRecordStoreDTO dto) {
		StandardUnitRecordStorePO po = StandardUnitRecordStoreConverter.toPO(dto);
		StandardUnitRecordStorePO list = standardUnitRecordStoreReadManage.findStandardUnitRecordStoreById(po);		
		return StandardUnitRecordStoreConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStoreDTO dto, Pagination page) {
		StandardUnitRecordStorePO po = StandardUnitRecordStoreConverter.toPO(dto);
        PageResult<StandardUnitRecordStorePO> pageResult = standardUnitRecordStoreReadManage.findStandardUnitRecordStoreOfPage(po, page);
        
        List<StandardUnitRecordStoreDTO> list = StandardUnitRecordStoreConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitRecordStoreDTO> result = new PageResult<StandardUnitRecordStoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreAll(StandardUnitRecordStoreDTO dto) {
		StandardUnitRecordStorePO po = StandardUnitRecordStoreConverter.toPO(dto);
		List<StandardUnitRecordStorePO> list = standardUnitRecordStoreReadManage.findStandardUnitRecordStoreAll(po);		
		return StandardUnitRecordStoreConverter.toDTO(list);
	}
}
	