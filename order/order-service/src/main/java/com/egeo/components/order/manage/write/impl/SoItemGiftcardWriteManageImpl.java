package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoItemGiftcardWriteManage;
import com.egeo.components.order.dao.write.SoItemGiftcardWriteDAO;

@Service
public class SoItemGiftcardWriteManageImpl implements SoItemGiftcardWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoItemGiftcardWriteDAO soItemGiftcardWriteDAO;
}
	