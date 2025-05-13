package com.egeo.components.product.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.read.MerchantSeriesProductAttReadService;
import com.egeo.components.product.manage.read.MerchantSeriesProductAttReadManage;

@Service("merchantSeriesProductAttReadService")
public class MerchantSeriesProductAttReadServiceImpl  implements MerchantSeriesProductAttReadService {
	@Autowired
	private MerchantSeriesProductAttReadManage merchantSeriesProductAttReadManage;
}
	