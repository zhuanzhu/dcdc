package com.egeo.components.order.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.read.SoTrackReadService;
import com.egeo.components.order.manage.read.SoTrackReadManage;

@Service("soTrackReadService")
public class SoTrackReadServiceImpl  implements SoTrackReadService {
	@Autowired
	private SoTrackReadManage soTrackReadManage;
}
	