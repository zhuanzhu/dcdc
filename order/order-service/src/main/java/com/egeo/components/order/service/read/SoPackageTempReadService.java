package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoPackageTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoPackageTempReadService {

	public SoPackageTempDTO findSoPackageTempById(SoPackageTempDTO dto);

	public PageResult<SoPackageTempDTO> findSoPackageTempOfPage(SoPackageTempDTO dto,Pagination page);

	public List<SoPackageTempDTO> findSoPackageTempAll(SoPackageTempDTO dto);
}
	