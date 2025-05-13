package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.AttributeValueDTO;

public interface AttributeValueReadService {

    AttributeValueDTO findById(AttributeValueDTO dto);
    
    List<AttributeValueDTO> findAll(AttributeValueDTO dto);
    /**
     * 根据属性id查询属性值排序最大的值
     * @param attributeNameId
     * @return
     */
	Integer maxSortValue(Long attributeNameId);
	/**
	 * 根据属性值集合和属性id查询属性值信息
	 * @param ids
	 * @param attNameId
	 * @return
	 */
	List<AttributeValueDTO> findByProductIdAndAttNameId(List<Long> ids, Long attNameId);
	/**
	 * 根据spu草稿id和属性id查询属性值信息
	 * @param productId
	 * @param attNameId
	 * @return
	 */
	List<AttributeValueDTO> attributeValueByProductIdAndAttNameId(Long productId, Long attNameId);
	/**
	 * 根据属性id和sup草稿id查询spu属性值信息
	 * @param productId
	 * @param id
	 * @return
	 */
	List<AttributeValueDTO> spuAttributeValueByProductIdAndAttNameId(Long productId, Long attNameId);
	/**
	 * 根据属性值id集合查询属性值信息
	 * @param puids
	 * @return
	 */
	List<AttributeValueDTO> findByAttributeValueIds(List<Long> attributeValueIds);

    Long findLastId();
}
	