package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoCancelConfigReadService;
import com.egeo.components.order.manage.read.SoCancelConfigReadManage;

@Service("soCancelConfigReadService")
public class SoCancelConfigReadServiceImpl  implements SoCancelConfigReadService {
	@Autowired
	private SoCancelConfigReadManage soCancelConfigReadManage;
}
	