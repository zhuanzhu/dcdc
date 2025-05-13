package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitPictureManage {

	public StandardProductUnitPictureDTO findStandardProductUnitPictureById(StandardProductUnitPictureDTO dto);	

	public PageResult<StandardProductUnitPictureDTO> findStandardProductUnitPictureOfPage(StandardProductUnitPictureDTO dto,Pagination page);

	public List<StandardProductUnitPictureDTO> findStandardProductUnitPictureAll(StandardProductUnitPictureDTO dto);

	Long insertStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto);

	int updateStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto);

	int deleteStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto);
}
	