package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.DeliveryFlowReadService;
import com.egeo.components.order.service.write.DeliveryFlowWriteService;
import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class DeliveryFlowFacade {
	
	@Resource
	private DeliveryFlowReadService deliveryFlowReadService;
	
	@Resource
	private DeliveryFlowWriteService deliveryFlowWriteService;
	
	
	public DeliveryFlowDTO findDeliveryFlowById(DeliveryFlowDTO dto){
		
		return deliveryFlowReadService.findDeliveryFlowById(dto);
	}

	public PageResult<DeliveryFlowDTO> findDeliveryFlowOfPage(DeliveryFlowDTO dto,Pagination page){
		
		return deliveryFlowReadService.findDeliveryFlowOfPage(dto, page);
		
	}

	public List<DeliveryFlowDTO> findDeliveryFlowAll(DeliveryFlowDTO dto){
		
		return deliveryFlowReadService.findDeliveryFlowAll(dto);
		
	}

	public Long insertDeliveryFlowWithTx(DeliveryFlowDTO dto){
		
		return deliveryFlowWriteService.insertDeliveryFlowWithTx(dto);
	}

	public int updateDeliveryFlowWithTx(DeliveryFlowDTO dto){
		
		return deliveryFlowWriteService.updateDeliveryFlowWithTx(dto);
	}

	public int deleteDeliveryFlowWithTx(DeliveryFlowDTO dto){
		
		return deliveryFlowWriteService.deleteDeliveryFlowWithTx(dto);
		
	}

}
	