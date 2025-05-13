package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.PraisePointPO;


public interface PraisePointWriteManage {

	Long insertPraisePointWithTx(PraisePointPO po);

	int updatePraisePointWithTx(PraisePointPO po);

	int deletePraisePointWithTx(PraisePointPO po);

	/**
	 * 增加点赞福豆
	 * @param delta
	 * @param userId 
	 * @param ciphertextNew 
	 * @return
	 */
	int increasePraisePointWithTx(double delta, Long userId, String ciphertextNew);
}
	