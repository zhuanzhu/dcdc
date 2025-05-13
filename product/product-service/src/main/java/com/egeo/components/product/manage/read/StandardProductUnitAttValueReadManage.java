package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.StandardProductUnitAttValueCondition;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttValueReadManage {

	public StandardProductUnitAttValuePO findStandardProductUnitAttValueById(StandardProductUnitAttValuePO po);

	public PageResult<StandardProductUnitAttValuePO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValuePO po,Pagination page);

	public List<StandardProductUnitAttValuePO> findStandardProductUnitAttValueAll(StandardProductUnitAttValuePO po);
	/**
	 * 根据su属性id查询属性值信息
	 * @param id
	 * @return
	 */
	public List<StandardProductUnitAttValueCondition> findByStandardProductUnitAttNameId(
			Long standardProductUnitAttNameId);
	/**
	 * 根据spu草稿id和属性值id查询是否存在spu规格属性
	 * @param productId
	 * @param attValueId
	 * @return
	 */
	public StandardProductUnitAttValuePO standardProductUnitAttValueByProductIdAttValueId(Long productId,
			Long attValueId);

	/**
	 * 查询指定属性名的pu属性值id,用来判断pu是否是unit商品
	 * @param puId
	 * @param isUnitInventoryId
	 * @return
	 */
	public Long queryAttValueIdByPuIdAndAttNameId(Long puId, Long isUnitInventoryId);
	/**
	 * 查询指定属性名的spu属性值id,用来判断pu是否是unit商品
	 * @param puId
	 * @return
	 */
	public Long queryAttValueIdBySpuIdAndAttNameId(Long standardProductUnitId, Long attNameId);
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
	 * 根据spuid查询spu关键字
	 * @param standardProductUnitId
	 * @return
	 */
	public List<String> findSpuKeywordByStandardProductUnitId(Long standardProductUnitId);

    int findThirdpartyAttBySpuId(Long id);
}
	