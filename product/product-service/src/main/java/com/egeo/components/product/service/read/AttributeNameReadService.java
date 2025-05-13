package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AttributeNameReadService {

    AttributeNameDTO findById(AttributeNameDTO dto);

    List<AttributeNameDTO> findAll(AttributeNameDTO dto);

    PageResult<AttributeNameDTO> findPageAttributeName(Pagination page, AttributeNameDTO dto);

	List<AttributeNameDTO> findByIds(String ids,Integer specificationProperty,Integer parameterProperty,String name);

	List<AttributeNameDTO> findAllByName(String name, String ids);
	/**
	 * 根据类目id查询属性信息
	 * @param categoryId
	 * @return
	 */
	List<AttributeNameDTO> findByCategoryId(Long categoryId);
	/**
	 * 根据spu草稿id查询属性和属性值信息
	 * @param productId
	 * @return
	 */
	List<AttributeNameDTO> findByProductId(Long productId);
	/**
	 * 根据属性名称查询属性信息
	 * @param name
	 * @return
	 */
	AttributeNameDTO findByAttName(String name);
}
	