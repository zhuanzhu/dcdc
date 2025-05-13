package com.egeo.components.stock.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.read.MerchantProductActivityStockReadService;
import com.egeo.components.stock.manage.read.MerchantProductActivityStockReadManage;

@Service("merchantProductActivityStockReadService")
public class MerchantProductActivityStockReadServiceImpl  implements MerchantProductActivityStockReadService {
	@Autowired
	private MerchantProductActivityStockReadManage merchantProductActivityStockReadManage;
}
	