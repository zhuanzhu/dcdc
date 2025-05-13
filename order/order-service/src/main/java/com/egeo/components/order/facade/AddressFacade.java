package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.AddressReadService;
import com.egeo.components.order.service.write.AddressWriteService;
import com.egeo.components.order.dto.AddressDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class AddressFacade {
	
	@Resource
	private AddressReadService addressReadService;
	
	@Resource
	private AddressWriteService addressWriteService;
	
	
	public AddressDTO findAddressById(AddressDTO dto){
		
		return addressReadService.findAddressById(dto);
	}

	public PageResult<AddressDTO> findAddressOfPage(AddressDTO dto,Pagination page){
		
		return addressReadService.findAddressOfPage(dto, page);
		
	}

	public List<AddressDTO> findAddressAll(AddressDTO dto){
		
		return addressReadService.findAddressAll(dto);
		
	}

	public Long insertAddressWithTx(AddressDTO dto){
		
		return addressWriteService.insertAddressWithTx(dto);
	}

	public int updateAddressWithTx(AddressDTO dto){
		
		return addressWriteService.updateAddressWithTx(dto);
	}

	public int deleteAddressWithTx(AddressDTO dto){
		
		return addressWriteService.deleteAddressWithTx(dto);
		
	}

}
	