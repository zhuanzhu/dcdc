package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.ContactGroupStockReadService;
import com.egeo.components.stock.manage.read.ContactGroupStockReadManage;
import com.egeo.components.stock.converter.ContactGroupStockConverter;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.po.ContactGroupStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("contactGroupStockReadService")
public class ContactGroupStockReadServiceImpl  implements ContactGroupStockReadService {
	@Autowired
	private ContactGroupStockReadManage contactGroupStockReadManage;

	@Override
	public ContactGroupStockDTO findContactGroupStockById(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		ContactGroupStockPO list = contactGroupStockReadManage.findContactGroupStockById(po);		
		return ContactGroupStockConverter.toDTO(list);
	}

	@Override
	public PageResult<ContactGroupStockDTO> findContactGroupStockOfPage(ContactGroupStockDTO dto, Pagination page) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
        PageResult<ContactGroupStockPO> pageResult = contactGroupStockReadManage.findContactGroupStockOfPage(po, page);
        
        List<ContactGroupStockDTO> list = ContactGroupStockConverter.toDTO(pageResult.getList());
        PageResult<ContactGroupStockDTO> result = new PageResult<ContactGroupStockDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ContactGroupStockDTO> findContactGroupStockAll(ContactGroupStockDTO dto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		List<ContactGroupStockPO> list = contactGroupStockReadManage.findContactGroupStockAll(po);		
		return ContactGroupStockConverter.toDTO(list);
	}

	@Override
	public PageResult<ContactGroupStockDTO> findContactGroupStockMapOfPage(ContactGroupStockDTO dto, Pagination page, List<Long> spuIds, List<Long> merchantIds) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		PageResult<ContactGroupStockPO> pageResult = contactGroupStockReadManage.findContactGroupStockMapOfPage(po, page, spuIds, merchantIds);
		List<ContactGroupStockDTO> list = ContactGroupStockConverter.toDTO(pageResult.getList());
		PageResult<ContactGroupStockDTO> result = new PageResult<ContactGroupStockDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public ContactGroupStockDTO findContactGroupStockByMerchantIdAndSuId(ContactGroupStockDTO dto, Long suId) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(dto);
		ContactGroupStockPO contactGroupStockPO = contactGroupStockReadManage.findContactGroupStockByMerchantIdAndSuId(po, suId);

		return ContactGroupStockConverter.toDTO(contactGroupStockPO);
	}

	@Override
	public List<ContactGroupStockDTO> findAllByName(ContactGroupStockDTO contactDto) {
		ContactGroupStockPO po = ContactGroupStockConverter.toPO(contactDto);
		List<ContactGroupStockPO> list = contactGroupStockReadManage.findAllByName(po);
		return ContactGroupStockConverter.toDTO(list);
	}
}
	