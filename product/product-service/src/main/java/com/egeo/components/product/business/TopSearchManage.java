package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.orm.Pagination;	

public interface TopSearchManage {

	public Map<String, Object> findTopSearchById(TopSearchDTO dto);	

	public Map<String, Object> findTopSearchOfPage(TopSearchDTO dto,Pagination page);

	public List<TopSearchDTO> findTopSearchAll(TopSearchDTO dto);

	Long insertTopSearchWithTx(TopSearchDTO dto);

	int updateTopSearchWithTx(TopSearchDTO dto);

	int deleteTopSearchWithTx(TopSearchDTO dto);
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	public int startStopTopSearchWithTx(Long topSearchId);
	/**
	 * 显示启用的热搜
	 * @param dto
	 * @return
	 */
	public Map<String, Object> findStartTopSearchAll(TopSearchDTO dto);
}
	