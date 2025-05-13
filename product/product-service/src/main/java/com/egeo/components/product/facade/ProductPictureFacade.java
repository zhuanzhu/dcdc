package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductPictureReadService;
import com.egeo.components.product.service.write.ProductPictureWriteService;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ProductPictureFacade {
	
	@Resource
	private ProductPictureReadService productPictureReadService;
	
	@Resource
	private ProductPictureWriteService productPictureWriteService;
	
	
	public ProductPictureDTO findProductPictureById(ProductPictureDTO dto){
		
		return productPictureReadService.findProductPictureById(dto);
	}

	public PageResult<ProductPictureDTO> findProductPictureOfPage(ProductPictureDTO dto,Pagination page){
		
		return productPictureReadService.findProductPictureOfPage(dto, page);
		
	}

	public List<ProductPictureDTO> findProductPictureAll(ProductPictureDTO dto){
		
		return productPictureReadService.findProductPictureAll(dto);
		
	}

	public Long insertProductPictureWithTx(ProductPictureDTO dto){
		
		return productPictureWriteService.insertProductPictureWithTx(dto);
	}

	public int updateProductPictureWithTx(ProductPictureDTO dto){
		
		return productPictureWriteService.updateProductPictureWithTx(dto);
	}

	public int deleteProductPictureWithTx(ProductPictureDTO dto){
		
		return productPictureWriteService.deleteProductPictureWithTx(dto);
		
	}

}
	