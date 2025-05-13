package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.ContactGroupSkuStockReadService;
import com.egeo.components.stock.manage.read.ContactGroupSkuStockReadManage;
import com.egeo.components.stock.converter.ContactGroupSkuStockConverter;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.po.ContactGroupSkuStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("contactGroupSkuStockReadService")
public class ContactGroupSkuStockReadServiceImpl  implements ContactGroupSkuStockReadService {
	@Autowired
	private ContactGroupSkuStockReadManage contactGroupSkuStockReadManage;

	@Override
	public ContactGroupSkuStockDTO findContactGroupSkuStockById(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
		ContactGroupSkuStockPO list = contactGroupSkuStockReadManage.findContactGroupSkuStockById(po);		
		return ContactGroupSkuStockConverter.toDTO(list);
	}

	@Override
	public PageResult<ContactGroupSkuStockDTO> findContactGroupSkuStockOfPage(ContactGroupSkuStockDTO dto, Pagination page) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
        PageResult<ContactGroupSkuStockPO> pageResult = contactGroupSkuStockReadManage.findContactGroupSkuStockOfPage(po, page);
        
        List<ContactGroupSkuStockDTO> list = ContactGroupSkuStockConverter.toDTO(pageResult.getList());
        PageResult<ContactGroupSkuStockDTO> result = new PageResult<ContactGroupSkuStockDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockAll(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockPO po = ContactGroupSkuStockConverter.toPO(dto);
		List<ContactGroupSkuStockPO> list = contactGroupSkuStockReadManage.findContactGroupSkuStockAll(po);		
		return ContactGroupSkuStockConverter.toDTO(list);
	}

	@Override
	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockBySuId(Long suId) {
		List<ContactGroupSkuStockPO> list = contactGroupSkuStockReadManage.findContactGroupSkuStockBySuId(suId);		
		return ContactGroupSkuStockConverter.toDTO(list);
	}
}
	