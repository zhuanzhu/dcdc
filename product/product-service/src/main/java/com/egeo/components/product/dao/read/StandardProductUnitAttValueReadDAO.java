package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardProductUnitAttValueCondition;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.orm.BaseReadDAO;

public interface StandardProductUnitAttValueReadDAO extends BaseReadDAO<StandardProductUnitAttValuePO>{
	/**
	 * 根据su属性id查询属性值信息
	 * @param id
	 * @return
	 */
	List<StandardProductUnitAttValueCondition> findByStandardProductUnitAttNameId(@Param("standardProductUnitAttNameId")Long standardProductUnitAttNameId);
	/**
	 * 根据spu草稿id和属性值id查询是否存在spu规格属性
	 * @param productId
	 * @param attValueId
	 * @return
	 */
	StandardProductUnitAttValuePO standardProductUnitAttValueByProductIdAttValueId(@Param("productId")Long productId, @Param("attValueId")Long attValueId);
	
	/**
	 * 查询指定属性名的pu属性值id,用来判断pu是否是unit商品
	 * @param puId
	 * @param attNameId
	 * @return
	 */
	Long queryAttValueIdByPuIdAndAttNameId(
			@Param("puId")Long puId, 
			@Param("attNameId")Long attNameId);
	/**
	 * 根据spuid属性id属性值id查询spu规格
	 * @param productId
	 * @param attributeNameId
	 * @param attributeValueId
	 * @return
	 */
	StandardProductUnitAttValuePO findBypuIdAttNameIdAttValueId(@Param("productId")Long productId, @Param("attributeNameId")Long attributeNameId,
			@Param("attributeValueId")Long attributeValueId);
	/**
	 * 根据suId查询要显示的规格值图片
	 * @param standardUnitId
	 * @return
	 */
	List<StandardProductUnitAttValueCondition> findAttNamePicture(@Param("standardUnitId")Long standardUnitId);
	/**
	 * 查询指定属性名的spu属性值id,用来判断pu是否是unit商品
	 * @param puId
	 * @return
	 */
	Long queryAttValueIdBySpuIdAndAttNameId(
			@Param("standardProductUnitId")Long standardProductUnitId, 
			@Param("attNameId")Long attNameId);
	/**
	 * 根据spuid查询spu关键词
	 * @param standardProductUnitId
	 * @param platformId
	 * @return
	 */
	List<String> keyWordByStandardProductUnitId(@Param("standardProductUnitId")Long standardProductUnitId, @Param("platformId")Long platformId);
	/**
	 * 根据skuid和属性id查询属性值Id
	 * @param skuId
	 * @return
	 */
	Long findAttValueIdBySkuIdAndAttNameId(@Param("skuId")Long skuId,@Param("attNameId")Long attNameId);
	/**
	 * 根据spuid查询spu关键字
	 * @param standardProductUnitId
	 * @return
	 */
	List<String> findSpuKeywordByStandardProductUnitId(@Param("standardProductUnitId")Long standardProductUnitId);

    int findThirdpartyAttBySpuId(@Param("id")Long id);
}
	