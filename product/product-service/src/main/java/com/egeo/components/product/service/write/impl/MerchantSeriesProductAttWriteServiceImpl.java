package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantSeriesProductAttWriteService;
import com.egeo.components.product.manage.write.MerchantSeriesProductAttWriteManage;

@Service("merchantSeriesProductAttWriteService")
public class MerchantSeriesProductAttWriteServiceImpl  implements MerchantSeriesProductAttWriteService {
	@Autowired
	private MerchantSeriesProductAttWriteManage merchantSeriesProductAttWriteManage;
}
	