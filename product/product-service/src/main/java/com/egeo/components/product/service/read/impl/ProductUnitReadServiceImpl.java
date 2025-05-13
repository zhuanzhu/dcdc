package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductUnitReadService;
import com.egeo.components.product.manage.read.ProductUnitReadManage;
import com.egeo.components.product.converter.ProductUnitConverter;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.po.ProductUnitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("productUnitReadService")
public class ProductUnitReadServiceImpl  implements ProductUnitReadService {
	@Autowired
	private ProductUnitReadManage productUnitReadManage;

	@Override
	public ProductUnitDTO findProductUnitById(ProductUnitDTO dto) {
		ProductUnitPO po = ProductUnitConverter.toPO(dto);
		ProductUnitPO list = productUnitReadManage.findProductUnitById(po);		
		return ProductUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<ProductUnitDTO> findProductUnitOfPage(ProductUnitDTO dto, Pagination page) {
		ProductUnitPO po = ProductUnitConverter.toPO(dto);
        PageResult<ProductUnitPO> pageResult = productUnitReadManage.findProductUnitOfPage(po, page);
        
        List<ProductUnitDTO> list = ProductUnitConverter.toDTO(pageResult.getList());
        PageResult<ProductUnitDTO> result = new PageResult<ProductUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ProductUnitDTO> findProductUnitAll(ProductUnitDTO dto) {
		ProductUnitPO po = ProductUnitConverter.toPO(dto);
		List<ProductUnitPO> list = productUnitReadManage.findProductUnitAll(po);		
		return ProductUnitConverter.toDTO(list);
	}
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	@Override
	public List<Long> attValueByProductUnitId(Long productUnitId) {
		// TODO Auto-generated method stub
		return productUnitReadManage.attValueByProductUnitId(productUnitId);
	}

	@Override
	public Long findLastId() {
		return productUnitReadManage.findLastId();
	}
}
	