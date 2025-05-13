package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductPictureReadService;
import com.egeo.components.product.manage.read.ProductPictureReadManage;
import com.egeo.components.product.converter.ProductPictureConverter;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.po.ProductPicturePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("productPictureReadService")
public class ProductPictureReadServiceImpl  implements ProductPictureReadService {
	@Autowired
	private ProductPictureReadManage productPictureReadManage;

	@Override
	public ProductPictureDTO findProductPictureById(ProductPictureDTO dto) {
		ProductPicturePO po = ProductPictureConverter.toPO(dto);
		ProductPicturePO list = productPictureReadManage.findProductPictureById(po);		
		return ProductPictureConverter.toDTO(list);
	}

	@Override
	public PageResult<ProductPictureDTO> findProductPictureOfPage(ProductPictureDTO dto, Pagination page) {
		ProductPicturePO po = ProductPictureConverter.toPO(dto);
        PageResult<ProductPicturePO> pageResult = productPictureReadManage.findProductPictureOfPage(po, page);
        
        List<ProductPictureDTO> list = ProductPictureConverter.toDTO(pageResult.getList());
        PageResult<ProductPictureDTO> result = new PageResult<ProductPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ProductPictureDTO> findProductPictureAll(ProductPictureDTO dto) {
		ProductPicturePO po = ProductPictureConverter.toPO(dto);
		List<ProductPicturePO> list = productPictureReadManage.findProductPictureAll(po);		
		return ProductPictureConverter.toDTO(list);
	}
}
	