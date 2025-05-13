package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.TopSearchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TopSearchReadManage {

	public TopSearchPO findTopSearchById(TopSearchPO po);

	public PageResult<TopSearchPO> findTopSearchOfPage(TopSearchPO po,Pagination page);

	public List<TopSearchPO> findTopSearchAll(TopSearchPO po);
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
	