package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttValueManage {

	public StandardProductUnitAttValueDTO findStandardProductUnitAttValueById(StandardProductUnitAttValueDTO dto);	

	public PageResult<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValueDTO dto,Pagination page);

	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(StandardProductUnitAttValueDTO dto);

	Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto);

	int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto);

	int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto);
}
	