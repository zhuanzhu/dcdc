package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.TopSearchDTO;


public interface TopSearchWriteService {

	public Long insertTopSearchWithTx(TopSearchDTO dto);

	public int updateTopSearchWithTx(TopSearchDTO dto);

	public int deleteTopSearchWithTx(TopSearchDTO dto);
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	public int startStopTopSearchWithTx(Long topSearchId);
}
	