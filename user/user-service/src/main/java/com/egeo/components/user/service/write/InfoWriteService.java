package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.InfoDTO;


public interface InfoWriteService {

	public Long insertInfoWithTx(InfoDTO dto,List<Long> sendWayIds);

	public int updateInfoWithTx(InfoDTO dto);

	public int deleteInfoWithTx(InfoDTO dto);
	/**
	 * 发送消息
	 * @param info
	 * @return
	 */
	public int sendInfoWithTx(InfoDTO dto);
}
	