package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoEnquiryWriteService;
import com.egeo.components.order.manage.write.SoEnquiryWriteManage;

@Service("soEnquiryWriteService")
public class SoEnquiryWriteServiceImpl  implements SoEnquiryWriteService {
	@Autowired
	private SoEnquiryWriteManage soEnquiryWriteManage;
}
	