package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.ContactGroupPuStockReadService;
import com.egeo.components.stock.service.write.ContactGroupPuStockWriteService;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ContactGroupPuStockFacade {
	
	@Resource
	private ContactGroupPuStockReadService contactGroupPuStockReadService;
	
	@Resource
	private ContactGroupPuStockWriteService contactGroupPuStockWriteService;
	
	
	public ContactGroupPuStockDTO findContactGroupPuStockById(ContactGroupPuStockDTO dto){
		
		return contactGroupPuStockReadService.findContactGroupPuStockById(dto);
	}

	public PageResult<ContactGroupPuStockDTO> findContactGroupPuStockOfPage(ContactGroupPuStockDTO dto,Pagination page){
		
		return contactGroupPuStockReadService.findContactGroupPuStockOfPage(dto, page);
		
	}

	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(ContactGroupPuStockDTO dto){
		
		return contactGroupPuStockReadService.findContactGroupPuStockAll(dto);
		
	}

	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto){
		
		return contactGroupPuStockWriteService.insertContactGroupPuStockWithTx(dto);
	}

	public int updateContactGroupPuStockWithTx(ContactGroupPuStockDTO dto){
		
		return contactGroupPuStockWriteService.updateContactGroupPuStockWithTx(dto);
	}

	public int deleteContactGroupPuStockWithTx(ContactGroupPuStockDTO dto){
		
		return contactGroupPuStockWriteService.deleteContactGroupPuStockWithTx(dto);
		
	}

}
	