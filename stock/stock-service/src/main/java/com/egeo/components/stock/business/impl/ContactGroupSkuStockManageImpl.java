package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.ContactGroupSkuStockManage;
import com.egeo.components.stock.facade.ContactGroupSkuStockFacade;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("contactGroupSkuStock")
public class ContactGroupSkuStockManageImpl implements ContactGroupSkuStockManage{

	
	@Resource(name="contactGroupSkuStockFacade")
	private ContactGroupSkuStockFacade contactGroupSkuStockFacade;

	@Override
	public ContactGroupSkuStockDTO findContactGroupSkuStockById(ContactGroupSkuStockDTO dto) {
		return contactGroupSkuStockFacade.findContactGroupSkuStockById(dto);
	}

	@Override
	public PageResult<ContactGroupSkuStockDTO> findContactGroupSkuStockOfPage(ContactGroupSkuStockDTO dto, Pagination page) {
		return contactGroupSkuStockFacade.findContactGroupSkuStockOfPage(dto, page);
	}

	@Override
	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockAll(ContactGroupSkuStockDTO dto) {
		return contactGroupSkuStockFacade.findContactGroupSkuStockAll(dto);
	}

	@Override
	public Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		return contactGroupSkuStockFacade.insertContactGroupSkuStockWithTx(dto);
	}

	@Override
	public int updateContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		return contactGroupSkuStockFacade.updateContactGroupSkuStockWithTx(dto);
	}

	@Override
	public int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		return contactGroupSkuStockFacade.deleteContactGroupSkuStockWithTx(dto);
	}


}
	