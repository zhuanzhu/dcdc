package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoReturnWriteService;
import com.egeo.components.order.manage.write.SoReturnWriteManage;

@Service("soReturnWriteService")
public class SoReturnWriteServiceImpl  implements SoReturnWriteService {
	@Autowired
	private SoReturnWriteManage soReturnWriteManage;
}
	