package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantSeriesReadService;
import com.egeo.components.product.manage.read.MerchantSeriesReadManage;

@Service("merchantSeriesReadService")
public class MerchantSeriesReadServiceImpl  implements MerchantSeriesReadService {
	@Autowired
	private MerchantSeriesReadManage merchantSeriesReadManage;
}
	