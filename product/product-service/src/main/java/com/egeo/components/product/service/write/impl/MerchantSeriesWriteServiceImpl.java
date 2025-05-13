package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantSeriesWriteService;
import com.egeo.components.product.manage.write.MerchantSeriesWriteManage;

@Service("merchantSeriesWriteService")
public class MerchantSeriesWriteServiceImpl  implements MerchantSeriesWriteService {
	@Autowired
	private MerchantSeriesWriteManage merchantSeriesWriteManage;
}
	