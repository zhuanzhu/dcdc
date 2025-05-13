package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductPictureManage;
import com.egeo.components.product.facade.ProductPictureFacade;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("productPicture")
public class ProductPictureManageImpl implements ProductPictureManage{

	
	@Resource(name="productPictureFacade")
	private ProductPictureFacade productPictureFacade;

	@Override
	public ProductPictureDTO findProductPictureById(ProductPictureDTO dto) {
		return productPictureFacade.findProductPictureById(dto);
	}

	@Override
	public PageResult<ProductPictureDTO> findProductPictureOfPage(ProductPictureDTO dto, Pagination page) {
		return productPictureFacade.findProductPictureOfPage(dto, page);
	}

	@Override
	public List<ProductPictureDTO> findProductPictureAll(ProductPictureDTO dto) {
		return productPictureFacade.findProductPictureAll(dto);
	}

	@Override
	public Long insertProductPictureWithTx(ProductPictureDTO dto) {
		return productPictureFacade.insertProductPictureWithTx(dto);
	}

	@Override
	public int updateProductPictureWithTx(ProductPictureDTO dto) {
		return productPictureFacade.updateProductPictureWithTx(dto);
	}

	@Override
	public int deleteProductPictureWithTx(ProductPictureDTO dto) {
		return productPictureFacade.deleteProductPictureWithTx(dto);
	}


}
	