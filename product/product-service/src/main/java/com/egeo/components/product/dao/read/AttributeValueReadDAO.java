package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.orm.BaseReadDAO;

public interface AttributeValueReadDAO extends BaseReadDAO<AttributeValuePO>{
	/**
     * 根据属性id查询属性值排序最大的值
     * @param attributeNameId
     * @return
     */
	Integer maxSortValue(@Param("attributeNameId")Long attributeNameId);
	/**
	 * 根据属性值集合和属性id查询属性值信息
	 * @param ids
	 * @param attNameId
	 * @return
	 */
	List<AttributeValuePO> findByProductIdAndAttNameId(@Param("ids")List<Long> ids, @Param("attNameId")Long attNameId);
	/**
	 * 根据spu草稿id和属性id查询属性值信息
	 * @param productId
	 * @param attNameId
	 * @return
	 */
	List<AttributeValuePO> attributeValueByProductIdAndAttNameId(@Param("productId")Long productId, @Param("attNameId")Long attNameId);
	/**
	 * 根据属性id和sup草稿id查询spu属性值信息
	 * @param productId
	 * @param id
	 * @return
	 */
	List<AttributeValuePO> spuAttributeValueByProductIdAndAttNameId(@Param("productId")Long productId,@Param("attNameId")Long attNameId);
	/**
	 * 根据属性值id集合查询属性值信息
	 * @param puids
	 * @return
	 */
	List<AttributeValuePO> findByAttributeValueIds(@Param("ids")List<Long> attributeValueIds);

    Long findLastId();
}
	