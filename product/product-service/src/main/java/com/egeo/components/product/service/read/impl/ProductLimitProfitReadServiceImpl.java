package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductLimitProfitReadService;
import com.egeo.components.product.manage.read.ProductLimitProfitReadManage;
import com.egeo.components.product.converter.ProductLimitProfitConverter;
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.components.product.po.ProductLimitProfitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("productLimitProfitReadService")
public class ProductLimitProfitReadServiceImpl  implements ProductLimitProfitReadService {
	@Autowired
	private ProductLimitProfitReadManage productLimitProfitReadManage;

	@Override
	public ProductLimitProfitDTO findProductLimitProfitById(ProductLimitProfitDTO dto) {
		ProductLimitProfitPO po = ProductLimitProfitConverter.toPO(dto);
		ProductLimitProfitPO list = productLimitProfitReadManage.findProductLimitProfitById(po);		
		return ProductLimitProfitConverter.toDTO(list);
	}

	@Override
	public PageResult<ProductLimitProfitDTO> findProductLimitProfitOfPage(ProductLimitProfitDTO dto, Pagination page) {
		ProductLimitProfitPO po = ProductLimitProfitConverter.toPO(dto);
        PageResult<ProductLimitProfitPO> pageResult = productLimitProfitReadManage.findProductLimitProfitOfPage(po, page);
        
        List<ProductLimitProfitDTO> list = ProductLimitProfitConverter.toDTO(pageResult.getList());
        PageResult<ProductLimitProfitDTO> result = new PageResult<ProductLimitProfitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ProductLimitProfitDTO> findProductLimitProfitAll(ProductLimitProfitDTO dto) {
		ProductLimitProfitPO po = ProductLimitProfitConverter.toPO(dto);
		List<ProductLimitProfitPO> list = productLimitProfitReadManage.findProductLimitProfitAll(po);		
		return ProductLimitProfitConverter.toDTO(list);
	}
}
	