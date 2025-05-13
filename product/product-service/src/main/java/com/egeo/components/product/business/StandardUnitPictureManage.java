package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitPictureManage {

	public StandardUnitPictureDTO findStandardUnitPictureById(StandardUnitPictureDTO dto);	

	public PageResult<StandardUnitPictureDTO> findStandardUnitPictureOfPage(StandardUnitPictureDTO dto,Pagination page);

	public List<StandardUnitPictureDTO> findStandardUnitPictureAll(StandardUnitPictureDTO dto);

	Long insertStandardUnitPictureWithTx(StandardUnitPictureDTO dto);

	int updateStandardUnitPictureWithTx(StandardUnitPictureDTO dto);

	int deleteStandardUnitPictureWithTx(StandardUnitPictureDTO dto);
}
	