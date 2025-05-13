package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AttributeNameManage {

    AttributeNameVO findById(AttributeNameVO attributeNameVO);

    List<AttributeNameVO> findAll(AttributeNameVO attributeNameVO);

    PageResult<Map<String, Object>> findPageAttributeName(Pagination page, AttributeNameVO attributeNameVO);

    Long saveAttributeName(AttributeNameVO attributeNameVO,List<AttValueDTO> lists,String begin, String finish);

    Long updateAttributeName(AttributeNameVO attributeNameVO,List<AttValueDTO> lists,String begin, String finish);

    Long deleteById(AttributeNameVO attributeNameVO);

	List<AttributeNameDTO> attributeNameByCategoryId(Long categoryId,String name,Integer specificationProperty,Integer parameterProperty);

	List<AttributeNameDTO> findAllByName(Long categoryId,String name,Integer specificationProperty,Integer parameterProperty);
	/**
	 * 根据spu草稿id和类目id查询属性值信息
	 * @param productId
	 * @param categoryId
	 * @return
	 */
	List<AttributeNameDTO> attributeValueByProductIdAndCategoryId(Long productId, Long categoryId);
	

}
	