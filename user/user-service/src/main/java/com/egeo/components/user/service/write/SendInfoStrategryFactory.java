package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.InfoDTO;

public interface SendInfoStrategryFactory {
	/**
	 * 发送消息
	 * @param po
	 */
	public boolean sendInfo(InfoDTO infoDTO);
}
	