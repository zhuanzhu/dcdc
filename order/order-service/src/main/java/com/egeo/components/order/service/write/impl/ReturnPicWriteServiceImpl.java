package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.ReturnPicWriteService;
import com.egeo.components.order.manage.write.ReturnPicWriteManage;

@Service("returnPicWriteService")
public class ReturnPicWriteServiceImpl  implements ReturnPicWriteService {
	@Autowired
	private ReturnPicWriteManage returnPicWriteManage;
}
	