package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoPackageItemManage {

	public SoPackageItemDTO findSoPackageItemById(SoPackageItemDTO dto);	

	public PageResult<SoPackageItemDTO> findSoPackageItemOfPage(SoPackageItemDTO dto,Pagination page);

	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO dto);

	int insertSoPackageItemWithTx(SoPackageItemDTO dto);

	int updateSoPackageItemWithTx(SoPackageItemDTO dto);

	int deleteSoPackageItemWithTx(SoPackageItemDTO dto);
}
	