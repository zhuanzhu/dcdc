package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductLimitProfitReadService;
import com.egeo.components.product.service.write.ProductLimitProfitWriteService;
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ProductLimitProfitFacade {
	
	@Resource
	private ProductLimitProfitReadService productLimitProfitReadService;
	
	@Resource
	private ProductLimitProfitWriteService productLimitProfitWriteService;
	
	
	public ProductLimitProfitDTO findProductLimitProfitById(ProductLimitProfitDTO dto){
		
		return productLimitProfitReadService.findProductLimitProfitById(dto);
	}

	public PageResult<ProductLimitProfitDTO> findProductLimitProfitOfPage(ProductLimitProfitDTO dto,Pagination page){
		
		return productLimitProfitReadService.findProductLimitProfitOfPage(dto, page);
		
	}

	public List<ProductLimitProfitDTO> findProductLimitProfitAll(ProductLimitProfitDTO dto){
		
		return productLimitProfitReadService.findProductLimitProfitAll(dto);
		
	}

	public Long insertProductLimitProfitWithTx(ProductLimitProfitDTO dto){
		
		return productLimitProfitWriteService.insertProductLimitProfitWithTx(dto);
	}

	public int updateProductLimitProfitWithTx(ProductLimitProfitDTO dto){
		
		return productLimitProfitWriteService.updateProductLimitProfitWithTx(dto);
	}

	public int deleteProductLimitProfitWithTx(ProductLimitProfitDTO dto){
		
		return productLimitProfitWriteService.deleteProductLimitProfitWithTx(dto);
		
	}

    public Integer findProductLimitProfit() {
		ProductLimitProfitDTO productLimitProfitDTO = new ProductLimitProfitDTO();
		productLimitProfitDTO.setId(1L);
		return productLimitProfitReadService.findProductLimitProfitById(productLimitProfitDTO).getProductLimitProfit();
	}
}
	