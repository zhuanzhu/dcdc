package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductCateTreeNodeReadService;
import com.egeo.components.product.manage.read.ProductCateTreeNodeReadManage;
import com.egeo.components.product.converter.ProductCateTreeNodeConverter;
import com.egeo.components.product.dto.ProductCateTreeNodeDTO;
import com.egeo.components.product.po.ProductCateTreeNodePO;


@Service("productCateTreeNodeReadService")
public class ProductCateTreeNodeReadServiceImpl  implements ProductCateTreeNodeReadService {
	@Autowired
	private ProductCateTreeNodeReadManage productCateTreeNodeReadManage;
		
	@Override
	public List<ProductCateTreeNodeDTO> findAll(ProductCateTreeNodeDTO productCateTreeNodeDTO) {
		
		ProductCateTreeNodePO po = ProductCateTreeNodeConverter.toPO(productCateTreeNodeDTO);
		List<ProductCateTreeNodePO> poList = productCateTreeNodeReadManage.findAll(po);
		
		if(poList != null && poList.size() > 0){
			return  ProductCateTreeNodeConverter.toDTO(poList);
		}
		
		return null;
	}
	
}
	