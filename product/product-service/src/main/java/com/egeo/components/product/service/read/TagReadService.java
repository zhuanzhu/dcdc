package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.TagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface TagReadService {

	public TagDTO findTagById(TagDTO dto);

	public PageResult<TagDTO> findTagOfPage(TagDTO dto,Pagination page,List<Long> tagIds);

	public List<TagDTO> findTagAll(TagDTO dto);
	/**
	 * 根据su商品id分页查询标签数据
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<TagDTO> findTagOfPageBystandardUnitCId(TagDTO dto, Pagination page,
			Long standardUnitCombinationId);
}
	