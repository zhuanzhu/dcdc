package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.order.business.AddressManage;
import com.egeo.components.order.dto.AddressDTO;
import com.egeo.components.order.facade.AddressFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("address")
public class AddressManageImpl implements AddressManage{

	
	@Resource(name="addressFacade")
	private AddressFacade addressFacade;

	@Override
	public AddressDTO findAddressById(AddressDTO dto) {
		return addressFacade.findAddressById(dto);
	}

	@Override
	public PageResult<AddressDTO> findAddressOfPage(AddressDTO dto, Pagination page) {
		return addressFacade.findAddressOfPage(dto, page);
	}

	@Override
	public List<AddressDTO> findAddressAll(AddressDTO dto) {
		if(dto.getPid() == null){
			dto.setPid(PlatformKeyConstant.PRODUCT_PLATFORMID);
		}
		return addressFacade.findAddressAll(dto);
	}

	@Override
	public Long insertAddressWithTx(AddressDTO dto) {
		return addressFacade.insertAddressWithTx(dto);
	}

	@Override
	public int updateAddressWithTx(AddressDTO dto) {
		return addressFacade.updateAddressWithTx(dto);
	}

	@Override
	public int deleteAddressWithTx(AddressDTO dto) {
		return addressFacade.deleteAddressWithTx(dto);
	}


}
	