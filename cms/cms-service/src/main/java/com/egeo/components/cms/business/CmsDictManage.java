package com.egeo.components.cms.business;

import java.util.List;

import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsDictManage {

	public CmsDictDTO findCmsDictById(CmsDictDTO dto);	

	public PageResult<CmsDictDTO> findCmsDictOfPage(CmsDictDTO dto,Pagination page);

	public List<CmsDictDTO> findCmsDictAll(CmsDictDTO dto);

	Long insertCmsDictWithTx(CmsDictDTO dto);

	int updateCmsDictWithTx(CmsDictDTO dto);

	int deleteCmsDictWithTx(CmsDictDTO dto);
}
	