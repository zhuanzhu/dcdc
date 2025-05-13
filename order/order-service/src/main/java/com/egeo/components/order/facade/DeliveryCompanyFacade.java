package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.DeliveryCompanyReadService;
import com.egeo.components.order.service.write.DeliveryCompanyWriteService;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class DeliveryCompanyFacade {

	@Resource
	private DeliveryCompanyReadService dcReadService;

	@Resource
	private DeliveryCompanyWriteService dcWriteService;


	public DeliveryCompanyDTO findDeliveryCompanyById(Long Id){
		if(Id ==null){
			return  null;
		}
		return dcReadService.findDeliveryCompanyById(Id);
	}

	public PageResult<DeliveryCompanyDTO> findDeliveryCompanyOfPage(DeliveryCompanyDTO dto,Pagination page){

		return dcReadService.findDeliveryCompanyOfPage(dto, page);

	}

	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto){

		return dcReadService.findDeliveryCompanyAll(dto);

	}

	public int insertDeliveryCompanyWithTx(DeliveryCompanyDTO dto){

		return dcWriteService.insertDeliveryCompanyWithTx(dto);
	}

	public int updateDeliveryCompanyWithTx(DeliveryCompanyDTO dto){

		return dcWriteService.updateDeliveryCompanyWithTx(dto);
	}

	public int deleteDeliveryCompanyWithTx(DeliveryCompanyDTO dto){

		return dcWriteService.deleteDeliveryCompanyWithTx(dto);

	}

	/**
	 * 根据名称查询物流公司
	 * @param deliveryCompany
	 * @return
	 */
	public DeliveryCompanyDTO queryDeliveryCompanyByName(String name) {
		return dcReadService.queryDeliveryCompanyByName(name);
	}

}
