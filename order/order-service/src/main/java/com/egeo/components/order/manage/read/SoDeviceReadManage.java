package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoDevicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoDeviceReadManage {

	public SoDevicePO findSoDeviceById(SoDevicePO po);

	public PageResult<SoDevicePO> findSoDeviceOfPage(SoDevicePO po,Pagination page);

	public List<SoDevicePO> findSoDeviceAll(SoDevicePO po);
}
	