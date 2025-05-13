package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoDeliveryReadService;
import com.egeo.components.order.manage.read.SoDeliveryReadManage;
import com.egeo.components.order.converter.SoDeliveryConverter;
import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.po.SoDeliveryPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soDeliveryReadService")
public class SoDeliveryReadServiceImpl  implements SoDeliveryReadService {
	@Autowired
	private SoDeliveryReadManage soDeliveryReadManage;

	@Override
	public SoDeliveryDTO findSoDeliveryById(SoDeliveryDTO dto) {
		SoDeliveryPO po = SoDeliveryConverter.toPO(dto);
		SoDeliveryPO list = soDeliveryReadManage.findSoDeliveryById(po);		
		return SoDeliveryConverter.toDTO(list);
	}

	@Override
	public PageResult<SoDeliveryDTO> findSoDeliveryOfPage(SoDeliveryDTO dto, Pagination page) {
		SoDeliveryPO po = SoDeliveryConverter.toPO(dto);
        PageResult<SoDeliveryPO> pageResult = soDeliveryReadManage.findSoDeliveryOfPage(po, page);
        
        List<SoDeliveryDTO> list = SoDeliveryConverter.toDTO(pageResult.getList());
        PageResult<SoDeliveryDTO> result = new PageResult<SoDeliveryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoDeliveryDTO> findSoDeliveryAll(SoDeliveryDTO dto) {
		SoDeliveryPO po = SoDeliveryConverter.toPO(dto);
		List<SoDeliveryPO> list = soDeliveryReadManage.findSoDeliveryAll(po);		
		return SoDeliveryConverter.toDTO(list);
	}
}
	