package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitAttValueReadService {
	public StandardProductUnitAttValueDTO findStandardProductUnitAttValueById(StandardProductUnitAttValueDTO dto);

	public PageResult<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValueDTO dto,Pagination page);

	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(StandardProductUnitAttValueDTO dto);
	/**
	 * 根据su属性id查询属性值信息
	 * @param id
	 * @return
	 */
	public List<StandardProductUnitAttValueDTO> findByStandardProductUnitAttNameId(Long id);
	/**
	 * 根据spuid查询spu关键词
	 * @param standardProductUnitId
	 * @param platformId
	 * @return
	 */
	public List<String> keyWordByStandardProductUnitId(Long standardProductUnitId, Long platformId);
	/**
	 * 根据skuid查询是否在app内使用
	 * @param skuId
	 * @return
	 */
	public boolean isAppUseBySkuId(Long skuId);
	/**
	 * 根据skuid和属性id查询属性值Id
	 * @param skuId
	 * @return
	 */
	public Long findAttValueIdBySkuIdAndAttNameId(Long skuId,Long attNameId);

	/**
	 * 根据spuid查询第三方对接参数值
	 * @param id
	 * @return
	 */
    int findThirdpartyAttBySpuId(Long id);
}
	