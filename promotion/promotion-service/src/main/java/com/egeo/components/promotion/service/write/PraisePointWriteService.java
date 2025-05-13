package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.PraisePointDTO;


public interface PraisePointWriteService {

	public Long insertPraisePointWithTx(PraisePointDTO dto);

	public int updatePraisePointWithTx(PraisePointDTO dto);

	public int deletePraisePointWithTx(PraisePointDTO dto);

	/**
	 * 增加点赞福豆
	 * @param delta
	 * @param userId 
	 * @param saltValue 
	 * @return
	 */
	public int increasePraisePoint(double delta, Long userId, String ciphertextNew);
}
	