package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitPictureRecordManage {

	public StandardUnitPictureRecordDTO findStandardUnitPictureRecordById(StandardUnitPictureRecordDTO dto);	

	public PageResult<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordDTO dto,Pagination page);

	public List<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordDTO dto);

	Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto);

	int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto);

	int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto);
}
	