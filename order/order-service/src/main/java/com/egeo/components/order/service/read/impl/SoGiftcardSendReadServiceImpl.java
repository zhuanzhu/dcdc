package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoGiftcardSendReadService;
import com.egeo.components.order.manage.read.SoGiftcardSendReadManage;

@Service("soGiftcardSendReadService")
public class SoGiftcardSendReadServiceImpl  implements SoGiftcardSendReadService {
	@Autowired
	private SoGiftcardSendReadManage soGiftcardSendReadManage;
}
	