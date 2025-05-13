package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.TagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TagReadManage {

	public TagPO findTagById(TagPO po);

	public PageResult<TagPO> findTagOfPage(TagPO po,Pagination page,List<Long> tags);

	public List<TagPO> findTagAll(TagPO po);
	/**
	 * 根据su商品id分页查询标签数据
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<TagPO> findTagOfPageBystandardUnitCId(TagPO po, Pagination page, Long standardUnitCombinationId);
}
	