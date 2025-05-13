package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface TopSearchReadService {

	public TopSearchDTO findTopSearchById(TopSearchDTO dto);

	public PageResult<TopSearchDTO> findTopSearchOfPage(TopSearchDTO dto,Pagination page);

	public List<TopSearchDTO> findTopSearchAll(TopSearchDTO dto);
	/**
	 * 查询排序最大值
	 * @return
	 */
	public int sortValueMax();
	/**
	 * 查询启用的热搜条数
	 * @return
	 */
	public int findStartTopSearchNum(Long platformId);
}
	