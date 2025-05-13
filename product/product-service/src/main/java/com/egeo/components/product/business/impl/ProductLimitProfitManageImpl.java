package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductLimitProfitManage;
import com.egeo.components.product.facade.ProductLimitProfitFacade;
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("productLimitProfit")
public class ProductLimitProfitManageImpl implements ProductLimitProfitManage{

	
	@Resource(name="productLimitProfitFacade")
	private ProductLimitProfitFacade productLimitProfitFacade;

	@Override
	public ProductLimitProfitDTO findProductLimitProfitById(ProductLimitProfitDTO dto) {
		return productLimitProfitFacade.findProductLimitProfitById(dto);
	}

	@Override
	public PageResult<ProductLimitProfitDTO> findProductLimitProfitOfPage(ProductLimitProfitDTO dto, Pagination page) {
		return productLimitProfitFacade.findProductLimitProfitOfPage(dto, page);
	}

	@Override
	public List<ProductLimitProfitDTO> findProductLimitProfitAll(ProductLimitProfitDTO dto) {
		return productLimitProfitFacade.findProductLimitProfitAll(dto);
	}

	@Override
	public Long insertProductLimitProfitWithTx(ProductLimitProfitDTO dto) {
		return productLimitProfitFacade.insertProductLimitProfitWithTx(dto);
	}

	@Override
	public int updateProductLimitProfitWithTx(ProductLimitProfitDTO dto) {
		return productLimitProfitFacade.updateProductLimitProfitWithTx(dto);
	}

	@Override
	public int deleteProductLimitProfitWithTx(ProductLimitProfitDTO dto) {
		return productLimitProfitFacade.deleteProductLimitProfitWithTx(dto);
	}


}
	