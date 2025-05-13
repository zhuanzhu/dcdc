package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.orm.BaseReadDAO;

public interface AttributeNameReadDAO extends BaseReadDAO<AttributeNamePO> {

	List<AttributeNamePO> findByIds(@Param("ids") String ids,
			@Param("specificationProperty") Integer specificationProperty,
			@Param("parameterProperty") Integer parameterProperty, @Param("name") String name);

	List<AttributeNamePO> findAllByName(@Param("name") String name, @Param("ids") String ids);

	/**
	 * 批量查询属性名
	 * 
	 * @param nameIds
	 * @param platformId
	 * @return
	 */
	List<AttributeNamePO> queryAttributeNamesByIds(@Param("ids") List<Long> nameIds);

	/**
	 * 根据类目id查询属性信息
	 * 
	 * @param categoryId
	 * @return
	 */
	List<AttributeNamePO> findByCategoryId(@Param("categoryId") Long categoryId);

	/**
	 * 根据spu草稿id查询属性和属性值信息
	 * 
	 * @param productId
	 * @return
	 */
	List<AttributeNamePO> findByProductId(@Param("productId") Long productId);

	/**
	 * 根据属性id查询属性信息及扩展信息
	 * 
	 * @param po
	 * @return
	 */
	AttributeNamePO findByAttributeNameId(@Param("po") AttributeNamePO po);
	/**
	 * 根据属性名称查询属性信息
	 * @param name
	 * @return
	 */
	AttributeNamePO findByAttName(@Param("name")String name);
}
