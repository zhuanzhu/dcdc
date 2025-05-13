package com.egeo.components.promotion.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.FuCoinPO;
import com.egeo.orm.BaseReadDAO;

public interface FuCoinReadDAO extends BaseReadDAO<FuCoinPO>{
	/**
	 * 根据用户id查询用户积分额度
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	FuCoinPO findFCoinByUserId(@Param("memberId")Long memberId, @Param("platformId")Long platformId);
}
	