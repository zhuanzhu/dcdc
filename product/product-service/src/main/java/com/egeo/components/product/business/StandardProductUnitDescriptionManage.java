package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitDescriptionManage {

	public StandardProductUnitDescriptionDTO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionDTO dto);	

	public PageResult<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionDTO dto,Pagination page);

	public List<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionDTO dto);

	Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto);

	int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto);

	int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto);
}
	