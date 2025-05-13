package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoEnquiryReadService;
import com.egeo.components.order.manage.read.SoEnquiryReadManage;

@Service("soEnquiryReadService")
public class SoEnquiryReadServiceImpl  implements SoEnquiryReadService {
	@Autowired
	private SoEnquiryReadManage soEnquiryReadManage;
}
	