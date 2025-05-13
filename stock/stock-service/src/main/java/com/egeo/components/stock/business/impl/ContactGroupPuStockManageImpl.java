package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.stock.dto.ContactGroupStockDTO;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.ContactGroupPuStockManage;
import com.egeo.components.stock.facade.ContactGroupPuStockFacade;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("contactGroupPuStock")
public class ContactGroupPuStockManageImpl implements ContactGroupPuStockManage{

	
	@Resource(name="contactGroupPuStockFacade")
	private ContactGroupPuStockFacade contactGroupPuStockFacade;

	@Override
	public ContactGroupPuStockDTO findContactGroupPuStockById(ContactGroupPuStockDTO dto) {
		return contactGroupPuStockFacade.findContactGroupPuStockById(dto);
	}

	@Override
	public PageResult<ContactGroupPuStockDTO> findContactGroupPuStockOfPage(ContactGroupPuStockDTO dto, Pagination page) {
		return contactGroupPuStockFacade.findContactGroupPuStockOfPage(dto, page);
	}

	@Override
	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(ContactGroupPuStockDTO dto) {
		return contactGroupPuStockFacade.findContactGroupPuStockAll(dto);
	}

	@Override
	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		return contactGroupPuStockFacade.insertContactGroupPuStockWithTx(dto);
	}

	@Override
	public int updateContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		return contactGroupPuStockFacade.updateContactGroupPuStockWithTx(dto);
	}

	@Override
	public int deleteContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		return contactGroupPuStockFacade.deleteContactGroupPuStockWithTx(dto);
	}




}
	