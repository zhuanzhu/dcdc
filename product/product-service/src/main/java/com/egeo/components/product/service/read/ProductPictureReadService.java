package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProductPictureReadService {

	public ProductPictureDTO findProductPictureById(ProductPictureDTO dto);

	public PageResult<ProductPictureDTO> findProductPictureOfPage(ProductPictureDTO dto,Pagination page);

	public List<ProductPictureDTO> findProductPictureAll(ProductPictureDTO dto);
}
	