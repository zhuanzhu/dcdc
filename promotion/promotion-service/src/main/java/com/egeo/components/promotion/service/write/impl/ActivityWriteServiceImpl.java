package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ActivityWriteService;
import com.egeo.components.promotion.manage.write.ActivityWriteManage;
import com.egeo.components.promotion.converter.ActivityConverter;
import com.egeo.components.promotion.dto.ActivityDTO;

@Service("activityWriteService")
public class ActivityWriteServiceImpl implements ActivityWriteService {
	@Autowired
	private ActivityWriteManage activityWriteManage;

	@Override
	public Long saveActivityWithTx(ActivityDTO dto) {
		return activityWriteManage.saveActivity(ActivityConverter.toPO(dto));
	}

	@Override
	public Integer deleteWithTx(ActivityDTO dto) {
		return activityWriteManage.deleteWithTx(ActivityConverter.toPO(dto));
	}

	@Override
	public Long updateActivity(ActivityDTO dto) {
		return activityWriteManage.updateActivity(ActivityConverter.toPO(dto));
	}
}
	