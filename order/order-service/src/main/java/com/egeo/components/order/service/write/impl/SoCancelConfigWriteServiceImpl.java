package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoCancelConfigWriteService;
import com.egeo.components.order.manage.write.SoCancelConfigWriteManage;

@Service("soCancelConfigWriteService")
public class SoCancelConfigWriteServiceImpl  implements SoCancelConfigWriteService {
	@Autowired
	private SoCancelConfigWriteManage soCancelConfigWriteManage;
}
	