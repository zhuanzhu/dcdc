package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.ContactGroupPuStockReadService;
import com.egeo.components.stock.manage.read.ContactGroupPuStockReadManage;
import com.egeo.components.stock.converter.ContactGroupPuStockConverter;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.po.ContactGroupPuStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("contactGroupPuStockReadService")
public class ContactGroupPuStockReadServiceImpl  implements ContactGroupPuStockReadService {
	@Autowired
	private ContactGroupPuStockReadManage contactGroupPuStockReadManage;

	@Override
	public ContactGroupPuStockDTO findContactGroupPuStockById(ContactGroupPuStockDTO dto) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
		ContactGroupPuStockPO list = contactGroupPuStockReadManage.findContactGroupPuStockById(po);		
		return ContactGroupPuStockConverter.toDTO(list);
	}

	@Override
	public PageResult<ContactGroupPuStockDTO> findContactGroupPuStockOfPage(ContactGroupPuStockDTO dto, Pagination page) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
        PageResult<ContactGroupPuStockPO> pageResult = contactGroupPuStockReadManage.findContactGroupPuStockOfPage(po, page);
        
        List<ContactGroupPuStockDTO> list = ContactGroupPuStockConverter.toDTO(pageResult.getList());
        PageResult<ContactGroupPuStockDTO> result = new PageResult<ContactGroupPuStockDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(ContactGroupPuStockDTO dto) {
		ContactGroupPuStockPO po = ContactGroupPuStockConverter.toPO(dto);
		List<ContactGroupPuStockPO> list = contactGroupPuStockReadManage.findContactGroupPuStockAll(po);		
		return ContactGroupPuStockConverter.toDTO(list);
	}

	@Override
	public List<Long> findPuIdListByPuId(Long puid) {
		return contactGroupPuStockReadManage.findPuIdListByPuId(puid);
	}
}
	