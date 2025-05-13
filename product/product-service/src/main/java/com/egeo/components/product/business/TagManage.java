package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.TagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TagManage {
	/**
	 * 根据标签id查询标签信息
	 * @param tagId
	 * @return
	 */
	public Map<String, Object> findTagById(Long tagId);	

	public PageResult<Map<String, Object>> findTagOfPage(TagDTO dto,Pagination page,List<Long> tags);

	public List<TagDTO> findTagAll(TagDTO dto);

	Long insertTagWithTx(TagDTO dto);

	int updateTagWithTx(TagDTO dto);

	int deleteTagWithTx(TagDTO dto);

	/**
	 * 根据标签id启用停用标签
	 * @param tagId
	 * @param type
	 * @return
	 */
	public int updateTagTypeByIdWithTx(Long tagId, Integer type);

	/**
	 * 根据su商品id分页查询标签数据
	 * @param dto
	 * @param page
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<Map<String, Object>> findTagOfPageBystandardUnitCId(TagDTO dto, Pagination page, Long standardUnitCombinationId);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	public Boolean tagClearRecordByTagId(Long tagId);

	/**
	 * 根据标签ID查询商品列表详情
	 * @param tagList
	 * @param page
	 * @param reqParam
	 * @return
	 */
    PageResult<StandardUnitDTO> querySuInfoByTagId(List<Long> tagList, Pagination page, Map<String, Object> reqParam);

	/**
	 * 根据su商品标识standardUnitId,商品标签标识tagId,删除商品标签关联的商品su关联关系
	 * @param standardUnitId
	 * @return
	 */
	Boolean delMpTagInfo(Long standardUnitId);
}
	