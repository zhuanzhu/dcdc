package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FuCoinReadService {

	public FuCoinDTO findFuCoinById(FuCoinDTO dto);

	public PageResult<FuCoinDTO> findFuCoinOfPage(FuCoinDTO dto,Pagination page);

	public List<FuCoinDTO> findFuCoinAll(FuCoinDTO dto);
	/**
	 * 根据用户id查询用户积分额度
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	public FuCoinDTO findFCoinByUserId(Long memberId, Long platformId);
}
	