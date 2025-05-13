package com.egeo.components.promotion.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.PraisePointPO;
import com.egeo.orm.BaseWriteDAO;

public interface PraisePointWriteDAO extends BaseWriteDAO<PraisePointPO> {

	/**
	 * 增加点赞福豆
	 * @param delta
	 * @param ciphertextNew 
	 * @return
	 */
	int increasePraisePointWithTx(@Param("delta")double delta,@Param("userId")Long userId, @Param("ciphertextNew")String ciphertextNew);
}
	