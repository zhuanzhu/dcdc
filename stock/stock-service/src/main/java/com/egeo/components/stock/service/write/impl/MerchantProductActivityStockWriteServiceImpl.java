package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.MerchantProductActivityStockWriteService;
import com.egeo.components.stock.manage.write.MerchantProductActivityStockWriteManage;

@Service("merchantProductActivityStockWriteService")
public class MerchantProductActivityStockWriteServiceImpl  implements MerchantProductActivityStockWriteService {
	@Autowired
	private MerchantProductActivityStockWriteManage merchantProductActivityStockWriteManage;
}
	