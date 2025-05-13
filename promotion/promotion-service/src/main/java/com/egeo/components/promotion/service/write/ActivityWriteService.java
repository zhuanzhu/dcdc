package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ActivityDTO;

public interface ActivityWriteService {

	Long saveActivityWithTx(ActivityDTO dto);

	Integer deleteWithTx(ActivityDTO dto);

	Long updateActivity(ActivityDTO dto);
}
	