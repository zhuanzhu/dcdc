package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.CustomerConditionReadService;
import com.egeo.components.order.manage.read.CustomerConditionReadManage;

@Service("customerConditionReadService")
public class CustomerConditionReadServiceImpl  implements CustomerConditionReadService {
	@Autowired
	private CustomerConditionReadManage customerConditionReadManage;
}
	