package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.ConditionPoolReadService;
import com.egeo.components.order.manage.read.ConditionPoolReadManage;

@Service("conditionPoolReadService")
public class ConditionPoolReadServiceImpl  implements ConditionPoolReadService {
	@Autowired
	private ConditionPoolReadManage conditionPoolReadManage;
}
	