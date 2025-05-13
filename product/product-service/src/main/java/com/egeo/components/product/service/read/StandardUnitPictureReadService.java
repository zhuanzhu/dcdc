package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitPictureReadService {

	public StandardUnitPictureDTO findStandardUnitPictureById(StandardUnitPictureDTO dto);

	public PageResult<StandardUnitPictureDTO> findStandardUnitPictureOfPage(StandardUnitPictureDTO dto,Pagination page);

	public List<StandardUnitPictureDTO> findStandardUnitPictureAll(StandardUnitPictureDTO dto);
}
	