package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoPackageBoxReadService {

	public SoPackageBoxDTO findSoPackageBoxById(SoPackageBoxDTO dto);

	public PageResult<SoPackageBoxDTO> findSoPackageBoxOfPage(SoPackageBoxDTO dto,Pagination page);

	public List<SoPackageBoxDTO> findSoPackageBoxAll(SoPackageBoxDTO dto);

	/**
	 * 根据箱号查询箱子
	 * @param boxCode
	 * @param soChildId 
	 * @return
	 */
	public SoPackageBoxDTO queryBoxByBoxCodeAndChildId(Long boxCode, Long soChildId);
}
	