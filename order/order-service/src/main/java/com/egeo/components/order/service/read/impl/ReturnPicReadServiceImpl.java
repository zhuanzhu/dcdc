package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.ReturnPicReadService;
import com.egeo.components.order.manage.read.ReturnPicReadManage;

@Service("returnPicReadService")
public class ReturnPicReadServiceImpl  implements ReturnPicReadService {
	@Autowired
	private ReturnPicReadManage returnPicReadManage;
}
	