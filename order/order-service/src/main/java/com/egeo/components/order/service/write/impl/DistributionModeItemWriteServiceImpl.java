package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.DistributionModeItemWriteService;
import com.egeo.components.order.manage.write.DistributionModeItemWriteManage;

@Service("distributionModeItemWriteService")
public class DistributionModeItemWriteServiceImpl  implements DistributionModeItemWriteService {
	@Autowired
	private DistributionModeItemWriteManage distributionModeItemWriteManage;
}
	