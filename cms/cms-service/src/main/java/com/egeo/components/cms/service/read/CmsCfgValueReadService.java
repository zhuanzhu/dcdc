package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsCfgValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsCfgValueReadService {

	public CmsCfgValueDTO findCmsCfgValueById(CmsCfgValueDTO dto);

	public PageResult<CmsCfgValueDTO> findCmsCfgValueOfPage(CmsCfgValueDTO dto,Pagination page);

	public List<CmsCfgValueDTO> findCmsCfgValueAll(CmsCfgValueDTO dto);
}
	