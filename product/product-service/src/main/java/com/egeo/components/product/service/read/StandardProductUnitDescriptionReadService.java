package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitDescriptionReadService {

	public StandardProductUnitDescriptionDTO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionDTO dto);

	public PageResult<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionDTO dto,Pagination page);

	public List<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionDTO dto);
}
	