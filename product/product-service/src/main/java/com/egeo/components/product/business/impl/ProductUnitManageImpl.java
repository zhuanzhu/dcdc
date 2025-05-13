package com.egeo.components.product.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductUnitManage;
import com.egeo.components.product.facade.ProductUnitFacade;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("productUnit")
public class ProductUnitManageImpl implements ProductUnitManage{

	
	@Resource(name="productUnitFacade")
	private ProductUnitFacade productUnitFacade;

	@Override
	public ProductUnitDTO findProductUnitById(ProductUnitDTO dto) {
		return productUnitFacade.findProductUnitById(dto);
	}

	@Override
	public PageResult<ProductUnitDTO> findProductUnitOfPage(ProductUnitDTO dto, Pagination page) {
		return productUnitFacade.findProductUnitOfPage(dto, page);
	}

	@Override
	public List<ProductUnitDTO> findProductUnitAll(ProductUnitDTO dto) {
		return productUnitFacade.findProductUnitAll(dto);
	}

	@Override
	public Long insertProductUnitWithTx(ProductUnitDTO dto) {
		return productUnitFacade.insertProductUnitWithTx(dto);
	}

	@Override
	public int updateProductUnitWithTx(ProductUnitDTO dto) {
		return productUnitFacade.updateProductUnitWithTx(dto);
	}

	@Override
	public int deleteProductUnitWithTx(ProductUnitDTO dto) {
		return productUnitFacade.deleteProductUnitWithTx(dto);
	}
	/**
	 * 根据puid查询pu所有pu信息
	 * @return
	 */
	@Override
	public Map<String, Object> findCommodityProductUnitAllByStandardUnitId(Long merchantProductId, Integer f) {
		// TODO Auto-generated method stub
		return productUnitFacade.findCommodityProductUnitAllByStandardUnitId(merchantProductId, f);
	}


}
	