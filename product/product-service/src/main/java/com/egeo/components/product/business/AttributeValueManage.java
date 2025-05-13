package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.AttributeValueVO;
import com.egeo.components.product.dto.AttributeValueDTO;

public interface AttributeValueManage {

    Long saveAttributeValue(AttributeValueVO attributeValueVO);

    AttributeValueDTO findById(AttributeValueVO attributeValueVO);

	Integer deleteById(AttributeValueVO attributeValueVO);
	/**
	 * 根据属性id查询属性值信息
	 * @param attributeValueVO
	 * @param req
	 * @return
	 */
	java.util.List<AttributeValueVO> findByAttributeNameId(AttributeValueVO attributeValueVO);
	/**
	 * 根据sup草稿id和属性id查询属性值信息
	 * @param productId
	 * @param attNameId
	 * @return
	 */
	List<AttributeValueVO> findByProductIdAndAttNameId(Long productId, Long attNameId);
	/**
	 * 根据spu草稿id和属性id查询属性值信息
	 * @param productId
	 * @param attNameId
	 * @return
	 */
	List<AttributeValueVO> attributeValueByProductIdAndAttNameId(Long productId, Long attNameId);
	

}
	