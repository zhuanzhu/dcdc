package com.egeo.components.product.manage.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AttributeNameReadManage {

	AttributeNamePO findById(AttributeNamePO po);

    List<AttributeNamePO> findAll(AttributeNamePO po);

    PageResult<AttributeNamePO> findPage(Pagination page, AttributeNamePO po);

	List<AttributeNamePO> findByIds(String ids,Integer specificationProperty,Integer parameterProperty,String name);

	List<AttributeNamePO> findAllByName(String name, String ids);

	/**
	 * 批量查询规格名称
	 * @param nameIds
	 * @param platformId
	 * @return
	 */
	List<AttributeNamePO> queryAttributeNamesByIds(@Param("ids")List<Long> nameIds);
	/**
	 * 根据类目id查询属性信息
	 * @param categoryId
	 * @return
	 */
	List<AttributeNamePO> findByCategoryId(Long categoryId);
	/**
	 * 根据spu草稿id查询属性和属性值信息
	 * @param productId
	 * @return
	 */
	List<AttributeNamePO> findByProductId(Long productId);
	/**
	 * 根据属性名称查询属性信息
	 * @param name
	 * @return
	 */
	AttributeNamePO findByAttName(String name);
}
	