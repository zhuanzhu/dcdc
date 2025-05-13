package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitPictureRecordReadService {

	public StandardUnitPictureRecordDTO findStandardUnitPictureRecordById(StandardUnitPictureRecordDTO dto);

	public PageResult<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordDTO dto,Pagination page);

	public List<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordDTO dto);
}
	