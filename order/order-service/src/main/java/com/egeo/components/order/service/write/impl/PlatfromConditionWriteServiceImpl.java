package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.PlatfromConditionWriteService;
import com.egeo.components.order.manage.write.PlatfromConditionWriteManage;

@Service("platfromConditionWriteService")
public class PlatfromConditionWriteServiceImpl  implements PlatfromConditionWriteService {
	@Autowired
	private PlatfromConditionWriteManage platfromConditionWriteManage;
}
	