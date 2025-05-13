package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.ContactGroupSkuStockReadService;
import com.egeo.components.stock.service.write.ContactGroupSkuStockWriteService;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ContactGroupSkuStockFacade {
	
	@Resource
	private ContactGroupSkuStockReadService contactGroupSkuStockReadService;
	
	@Resource
	private ContactGroupSkuStockWriteService contactGroupSkuStockWriteService;
	
	
	public ContactGroupSkuStockDTO findContactGroupSkuStockById(ContactGroupSkuStockDTO dto){
		
		return contactGroupSkuStockReadService.findContactGroupSkuStockById(dto);
	}

	public PageResult<ContactGroupSkuStockDTO> findContactGroupSkuStockOfPage(ContactGroupSkuStockDTO dto,Pagination page){
		
		return contactGroupSkuStockReadService.findContactGroupSkuStockOfPage(dto, page);
		
	}

	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockAll(ContactGroupSkuStockDTO dto){
		
		return contactGroupSkuStockReadService.findContactGroupSkuStockAll(dto);
		
	}

	public Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto){
		
		return contactGroupSkuStockWriteService.insertContactGroupSkuStockWithTx(dto);
	}

	public int updateContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto){
		
		return contactGroupSkuStockWriteService.updateContactGroupSkuStockWithTx(dto);
	}

	public int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto){
		
		return contactGroupSkuStockWriteService.deleteContactGroupSkuStockWithTx(dto);
		
	}

}
	