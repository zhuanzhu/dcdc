package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoDeviceReadService {

	public SoDeviceDTO findSoDeviceById(SoDeviceDTO dto);

	public PageResult<SoDeviceDTO> findSoDeviceOfPage(SoDeviceDTO dto,Pagination page);

	public List<SoDeviceDTO> findSoDeviceAll(SoDeviceDTO dto);
}
	