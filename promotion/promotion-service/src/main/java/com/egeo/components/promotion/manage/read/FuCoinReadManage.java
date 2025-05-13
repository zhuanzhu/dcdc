package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.FuCoinPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FuCoinReadManage {

	public FuCoinPO findFuCoinById(FuCoinPO po);

	public PageResult<FuCoinPO> findFuCoinOfPage(FuCoinPO po,Pagination page);

	public List<FuCoinPO> findFuCoinAll(FuCoinPO po);
	/**
	 * 根据用户id查询用户积分额度
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	public FuCoinPO findFCoinByUserId(Long memberId, Long platformId);
}
	