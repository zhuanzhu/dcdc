package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoItemGiftcardReadService;
import com.egeo.components.order.manage.read.SoItemGiftcardReadManage;

@Service("soItemGiftcardReadService")
public class SoItemGiftcardReadServiceImpl  implements SoItemGiftcardReadService {
	@Autowired
	private SoItemGiftcardReadManage soItemGiftcardReadManage;
}
	