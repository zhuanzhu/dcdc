package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.DeliveryCompanyReadService;
import com.egeo.components.order.manage.read.DeliveryCompanyReadManage;
import com.egeo.components.order.converter.DeliveryCompanyConverter;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.po.DeliveryCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("deliveryCompanyReadService")
public class DeliveryCompanyReadServiceImpl  implements DeliveryCompanyReadService {
	@Autowired
	private DeliveryCompanyReadManage deliveryCompanyReadManage;

	@Override
	public DeliveryCompanyDTO findDeliveryCompanyById(Long id) {
		DeliveryCompanyPO po = new DeliveryCompanyPO();
		po.setId(id);
		DeliveryCompanyPO list = deliveryCompanyReadManage.findDeliveryCompanyById(po);
		return DeliveryCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<DeliveryCompanyDTO> findDeliveryCompanyOfPage(DeliveryCompanyDTO dto, Pagination page) {
		DeliveryCompanyPO po = DeliveryCompanyConverter.toPO(dto);
        PageResult<DeliveryCompanyPO> pageResult = deliveryCompanyReadManage.findDeliveryCompanyOfPage(po, page);
        
        List<DeliveryCompanyDTO> list = DeliveryCompanyConverter.toDTO(pageResult.getList());
        PageResult<DeliveryCompanyDTO> result = new PageResult<DeliveryCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto) {
		DeliveryCompanyPO po = DeliveryCompanyConverter.toPO(dto);
		List<DeliveryCompanyPO> list = deliveryCompanyReadManage.findDeliveryCompanyAll(po);		
		return DeliveryCompanyConverter.toDTO(list);
	}

	@Override
	public DeliveryCompanyDTO queryDeliveryCompanyByName(String name) {
		return DeliveryCompanyConverter.toDTO(deliveryCompanyReadManage.queryDeliveryCompanyByName(name));
	}
}
	