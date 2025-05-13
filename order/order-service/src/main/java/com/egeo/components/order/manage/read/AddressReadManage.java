package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.AddressPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AddressReadManage {

	public AddressPO findAddressById(AddressPO po);

	public PageResult<AddressPO> findAddressOfPage(AddressPO po,Pagination page);

	public List<AddressPO> findAddressAll(AddressPO po);
}
	