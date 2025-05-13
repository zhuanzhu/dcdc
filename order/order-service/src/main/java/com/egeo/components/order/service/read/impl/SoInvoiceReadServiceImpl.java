package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoInvoiceReadService;
import com.egeo.components.order.manage.read.SoInvoiceReadManage;
import com.egeo.components.order.converter.SoInvoiceConverter;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.po.SoInvoicePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soInvoiceReadService")
public class SoInvoiceReadServiceImpl  implements SoInvoiceReadService {
	@Autowired
	private SoInvoiceReadManage soInvoiceReadManage;

	@Override
	public SoInvoiceDTO findSoInvoiceById(Long id) {
		SoInvoiceDTO condition=new SoInvoiceDTO();
		condition.setId(id);
		SoInvoicePO po = SoInvoiceConverter.toPO(condition);
		SoInvoicePO list = soInvoiceReadManage.findSoInvoiceById(po);		
		return SoInvoiceConverter.toDTO(list);
	}

	@Override
	public PageResult<SoInvoiceDTO> findSoInvoiceOfPage(SoInvoiceDTO dto, Pagination page) {
		SoInvoicePO po = SoInvoiceConverter.toPO(dto);
        PageResult<SoInvoicePO> pageResult = soInvoiceReadManage.findSoInvoiceOfPage(po, page);
        
        List<SoInvoiceDTO> list = SoInvoiceConverter.toDTO(pageResult.getList());
        PageResult<SoInvoiceDTO> result = new PageResult<SoInvoiceDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoInvoiceDTO> findSoInvoiceAll(SoInvoiceDTO dto) {
		SoInvoicePO po = SoInvoiceConverter.toPO(dto);
		List<SoInvoicePO> list = soInvoiceReadManage.findSoInvoiceAll(po);		
		return SoInvoiceConverter.toDTO(list);
	}

	@Override
	public SoInvoiceDTO querySoInvoiceByOrderCode(String orderCode,Long platformId) {
		SoInvoicePO po=soInvoiceReadManage.querySoInvoiceByOrderCode(orderCode,platformId);
		return SoInvoiceConverter.toDTO(po);
	}

	@Override
	public List<SoInvoiceDTO> queryDistinctInvoiceByUserId(Long userId, Long platformId) {
		
		return SoInvoiceConverter.toDTO(soInvoiceReadManage.queryDistinctInvoiceByUserId(userId,platformId));
	}

	@Override
	public SoInvoiceDTO queryMainSoInvoiceByOrderId(Long orderId) {
		return SoInvoiceConverter.toDTO(soInvoiceReadManage.queryMainSoInvoiceByOrderId(orderId));
	}

	@Override
	public SoInvoiceDTO querySoInvoiceBySoChildId(Long id) {
		return SoInvoiceConverter.toDTO(soInvoiceReadManage.querySoInvoiceBySoChildId(id));
	}
}