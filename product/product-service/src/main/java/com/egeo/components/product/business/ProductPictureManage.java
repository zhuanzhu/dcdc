package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProductPictureManage {

	public ProductPictureDTO findProductPictureById(ProductPictureDTO dto);	

	public PageResult<ProductPictureDTO> findProductPictureOfPage(ProductPictureDTO dto,Pagination page);

	public List<ProductPictureDTO> findProductPictureAll(ProductPictureDTO dto);

	Long insertProductPictureWithTx(ProductPictureDTO dto);

	int updateProductPictureWithTx(ProductPictureDTO dto);

	int deleteProductPictureWithTx(ProductPictureDTO dto);
}
	