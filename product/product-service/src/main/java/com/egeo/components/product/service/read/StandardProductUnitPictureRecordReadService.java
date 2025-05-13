package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitPictureRecordReadService {

	public StandardProductUnitPictureRecordDTO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordDTO dto);

	public PageResult<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordDTO dto,Pagination page);

	public List<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordDTO dto);
}
	