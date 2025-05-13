package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitPictureRecordManage {

	public StandardProductUnitPictureRecordDTO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordDTO dto);	

	public PageResult<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordDTO dto,Pagination page);

	public List<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordDTO dto);

	Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto);

	int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto);

	int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto);
}
	