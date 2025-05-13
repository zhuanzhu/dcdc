package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoDeliveryItemReadService;
import com.egeo.components.order.manage.read.SoDeliveryItemReadManage;
import com.egeo.components.order.converter.SoDeliveryItemConverter;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.components.order.po.SoDeliveryItemPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soDeliveryItemReadService")
public class SoDeliveryItemReadServiceImpl  implements SoDeliveryItemReadService {
	@Autowired
	private SoDeliveryItemReadManage soDeliveryItemReadManage;

	@Override
	public SoDeliveryItemDTO findSoDeliveryItemById(SoDeliveryItemDTO dto) {
		SoDeliveryItemPO po = SoDeliveryItemConverter.toPO(dto);
		SoDeliveryItemPO list = soDeliveryItemReadManage.findSoDeliveryItemById(po);		
		return SoDeliveryItemConverter.toDTO(list);
	}

	@Override
	public PageResult<SoDeliveryItemDTO> findSoDeliveryItemOfPage(SoDeliveryItemDTO dto, Pagination page) {
		SoDeliveryItemPO po = SoDeliveryItemConverter.toPO(dto);
        PageResult<SoDeliveryItemPO> pageResult = soDeliveryItemReadManage.findSoDeliveryItemOfPage(po, page);
        
        List<SoDeliveryItemDTO> list = SoDeliveryItemConverter.toDTO(pageResult.getList());
        PageResult<SoDeliveryItemDTO> result = new PageResult<SoDeliveryItemDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoDeliveryItemDTO> findSoDeliveryItemAll(SoDeliveryItemDTO dto) {
		SoDeliveryItemPO po = SoDeliveryItemConverter.toPO(dto);
		List<SoDeliveryItemPO> list = soDeliveryItemReadManage.findSoDeliveryItemAll(po);		
		return SoDeliveryItemConverter.toDTO(list);
	}
}
	