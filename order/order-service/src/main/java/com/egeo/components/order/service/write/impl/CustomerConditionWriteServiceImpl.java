package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.CustomerConditionWriteService;
import com.egeo.components.order.manage.write.CustomerConditionWriteManage;

@Service("customerConditionWriteService")
public class CustomerConditionWriteServiceImpl  implements CustomerConditionWriteService {
	@Autowired
	private CustomerConditionWriteManage customerConditionWriteManage;
}
	