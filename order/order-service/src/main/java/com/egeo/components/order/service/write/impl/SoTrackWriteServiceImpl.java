package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoTrackWriteService;
import com.egeo.components.order.manage.write.SoTrackWriteManage;

@Service("soTrackWriteService")
public class SoTrackWriteServiceImpl  implements SoTrackWriteService {
	@Autowired
	private SoTrackWriteManage soTrackWriteManage;
}
	