package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsDictReadService {

	public CmsDictDTO findCmsDictById(CmsDictDTO dto);

	public PageResult<CmsDictDTO> findCmsDictOfPage(CmsDictDTO dto,Pagination page);

	public List<CmsDictDTO> findCmsDictAll(CmsDictDTO dto);
}
	