package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.DeliveryCompanyManage;
import com.egeo.components.order.facade.DeliveryCompanyFacade;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("deliveryCompany")
public class DeliveryCompanyManageImpl implements DeliveryCompanyManage{

	
	@Resource(name="deliveryCompanyFacade")
	private DeliveryCompanyFacade deliveryCompanyFacade;

	@Override
	public DeliveryCompanyDTO findDeliveryCompanyById(Long id) {
		return deliveryCompanyFacade.findDeliveryCompanyById(id);
	}

	@Override
	public PageResult<DeliveryCompanyDTO> findDeliveryCompanyOfPage(DeliveryCompanyDTO dto, Pagination page) {
		return deliveryCompanyFacade.findDeliveryCompanyOfPage(dto, page);
	}

	@Override
	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto) {
		return deliveryCompanyFacade.findDeliveryCompanyAll(dto);
	}

	@Override
	public int insertDeliveryCompanyWithTx(DeliveryCompanyDTO dto) {
		return deliveryCompanyFacade.insertDeliveryCompanyWithTx(dto);
	}

	@Override
	public int updateDeliveryCompanyWithTx(DeliveryCompanyDTO dto) {
		return deliveryCompanyFacade.updateDeliveryCompanyWithTx(dto);
	}

	@Override
	public int deleteDeliveryCompanyWithTx(DeliveryCompanyDTO dto) {
		return deliveryCompanyFacade.deleteDeliveryCompanyWithTx(dto);
	}


}
	