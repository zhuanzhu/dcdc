package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoCustomerServiceReadService;
import com.egeo.components.order.service.write.SoCustomerServiceWriteService;
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoCustomerServiceFacade {
	
	@Resource
	private SoCustomerServiceReadService soCustomerServiceReadService;
	
	@Resource
	private SoCustomerServiceWriteService soCustomerServiceWriteService;
	
	
	public SoCustomerServiceDTO findSoCustomerServiceById(SoCustomerServiceDTO dto){
		
		return soCustomerServiceReadService.findSoCustomerServiceById(dto);
	}

	public PageResult<SoCustomerServiceDTO> findSoCustomerServiceOfPage(SoCustomerServiceDTO dto,Pagination page){
		
		return soCustomerServiceReadService.findSoCustomerServiceOfPage(dto, page);
		
	}

	public List<SoCustomerServiceDTO> findSoCustomerServiceAll(SoCustomerServiceDTO dto){
		
		return soCustomerServiceReadService.findSoCustomerServiceAll(dto);
		
	}

	public Long insertSoCustomerServiceWithTx(SoCustomerServiceDTO dto){
		
		return soCustomerServiceWriteService.insertSoCustomerServiceWithTx(dto);
	}

	public int updateSoCustomerServiceWithTx(SoCustomerServiceDTO dto){
		
		return soCustomerServiceWriteService.updateSoCustomerServiceWithTx(dto);
	}

	public int deleteSoCustomerServiceWithTx(SoCustomerServiceDTO dto){
		
		return soCustomerServiceWriteService.deleteSoCustomerServiceWithTx(dto);
		
	}

	/**
	 * 根据子订单id查询售后信息
	 * @param scId
	 * @return
	 */
	public SoCustomerServiceDTO queryCustomerServiceBySoChildId(Long scId) {
		return soCustomerServiceReadService.queryCustomerServiceBySoChildId(scId);
	}

}
	