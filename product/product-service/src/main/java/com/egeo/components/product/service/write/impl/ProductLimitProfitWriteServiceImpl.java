package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.ProductLimitProfitWriteService;
import com.egeo.components.product.manage.write.ProductLimitProfitWriteManage;
import com.egeo.components.product.converter.ProductLimitProfitConverter;
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.components.product.po.ProductLimitProfitPO;

@Service("productLimitProfitWriteService")
public class ProductLimitProfitWriteServiceImpl  implements ProductLimitProfitWriteService {
	@Autowired
	private ProductLimitProfitWriteManage productLimitProfitWriteManage;

	@Override
	public Long insertProductLimitProfitWithTx(ProductLimitProfitDTO dto) {
		ProductLimitProfitPO po = ProductLimitProfitConverter.toPO(dto);
		Long rt = productLimitProfitWriteManage.insertProductLimitProfitWithTx(po);		
		return rt;
	}

	@Override
	public int updateProductLimitProfitWithTx(ProductLimitProfitDTO dto) {
		ProductLimitProfitPO po = ProductLimitProfitConverter.toPO(dto);
		int rt = productLimitProfitWriteManage.updateProductLimitProfitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteProductLimitProfitWithTx(ProductLimitProfitDTO dto) {
		ProductLimitProfitPO po = ProductLimitProfitConverter.toPO(dto);
		int rt = productLimitProfitWriteManage.deleteProductLimitProfitWithTx(po);		
		return rt;
	}
}
	