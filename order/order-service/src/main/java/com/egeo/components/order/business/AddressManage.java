package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.AddressDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AddressManage {

	public AddressDTO findAddressById(AddressDTO dto);	

	public PageResult<AddressDTO> findAddressOfPage(AddressDTO dto,Pagination page);

	public List<AddressDTO> findAddressAll(AddressDTO dto);

	Long insertAddressWithTx(AddressDTO dto);

	int updateAddressWithTx(AddressDTO dto);

	int deleteAddressWithTx(AddressDTO dto);
}
	