package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoPackageItemReadService {

	public SoPackageItemDTO findSoPackageItemById(SoPackageItemDTO dto);

	public PageResult<SoPackageItemDTO> findSoPackageItemOfPage(SoPackageItemDTO dto,Pagination page);

	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO dto);
}
	