package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.DeliveryFlowReadService;
import com.egeo.components.order.manage.read.DeliveryFlowReadManage;
import com.egeo.components.order.converter.DeliveryFlowConverter;
import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.components.order.po.DeliveryFlowPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("deliveryFlowReadService")
public class DeliveryFlowReadServiceImpl  implements DeliveryFlowReadService {
	@Autowired
	private DeliveryFlowReadManage deliveryFlowReadManage;

	@Override
	public DeliveryFlowDTO findDeliveryFlowById(DeliveryFlowDTO dto) {
		DeliveryFlowPO po = DeliveryFlowConverter.toPO(dto);
		DeliveryFlowPO list = deliveryFlowReadManage.findDeliveryFlowById(po);		
		return DeliveryFlowConverter.toDTO(list);
	}

	@Override
	public PageResult<DeliveryFlowDTO> findDeliveryFlowOfPage(DeliveryFlowDTO dto, Pagination page) {
		DeliveryFlowPO po = DeliveryFlowConverter.toPO(dto);
        PageResult<DeliveryFlowPO> pageResult = deliveryFlowReadManage.findDeliveryFlowOfPage(po, page);
        
        List<DeliveryFlowDTO> list = DeliveryFlowConverter.toDTO(pageResult.getList());
        PageResult<DeliveryFlowDTO> result = new PageResult<DeliveryFlowDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DeliveryFlowDTO> findDeliveryFlowAll(DeliveryFlowDTO dto) {
		DeliveryFlowPO po = DeliveryFlowConverter.toPO(dto);
		List<DeliveryFlowPO> list = deliveryFlowReadManage.findDeliveryFlowAll(po);		
		return DeliveryFlowConverter.toDTO(list);
	}
}
	