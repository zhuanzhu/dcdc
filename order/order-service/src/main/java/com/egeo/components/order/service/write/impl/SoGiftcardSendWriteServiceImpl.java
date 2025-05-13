package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoGiftcardSendWriteService;
import com.egeo.components.order.manage.write.SoGiftcardSendWriteManage;

@Service("soGiftcardSendWriteService")
public class SoGiftcardSendWriteServiceImpl  implements SoGiftcardSendWriteService {
	@Autowired
	private SoGiftcardSendWriteManage soGiftcardSendWriteManage;
}
	