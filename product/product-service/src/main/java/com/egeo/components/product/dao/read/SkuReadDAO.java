package com.egeo.components.product.dao.read;

import java.util.List;

import com.egeo.components.product.po.MapResultPO;
import com.egeo.components.product.po.ProductAttValuePO;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.SkuCondition;
import com.egeo.components.product.po.SkuPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SkuReadDAO extends BaseReadDAO<SkuPO>{
	/**
	 * 查询所有电子卡券sku总条数
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	int countECardOfOfPage(@Param("po")SkuPO po);
	/**
	 * 分页查询所有电子卡券sku
	 * @param vo
	 * @param req
	 * @return
	 */
	List<SkuCondition> findSkuECardOfPage(@Param("po") SkuPO po);
	/**
	 * 根据sku编号查询sku电子卡券信息
	 * @param skuSerialNumber
	 * @return
	 */
	SkuPO findSkuECardBySkuSerialNumber(@Param("skuSerialNumber")String skuSerialNumber);
	/**
	 * 查询所有sku信息
	 * @param po
	 * @return
	 */
	List<SkuCondition> findSkuAll(@Param("po")SkuPO po , @Param("page") Pagination page);
	/**
	 * 根据skuid查询sku序列号	
	 * @param skuId
	 * @return
	 */
	String skuSerialNumberBySkuId(@Param("skuId")Long skuId);
	/**
	 * 根据skuid查询sku下面的所有pu数量
	 * @param skuId
	 * @return
	 */
	int puMaxBySkuId(@Param("skuId")Long skuId);
	
	List<SkuCondition> findSkuOfPage(@Param("po")SkuPO po, @Param("page")Pagination page);

	List<SkuPO> findSkuLikeName(@Param("linkedSkuName") String linkedSkuName,@Param("platformId") Long platformId);
	List<MapResultPO> getMembershipSku(@Param("platformId") Long platform);

    SkuPO findSkuByPuId(@Param("puId")Long puId);

	List<ProductAttValuePO> findSkuIdAndPreDaysByPreAttNameId(@Param("precautiousAttNameId") Long precautiousAttNameId);

    Long findLastId();

    List<SkuCondition> findSkuListConByIdList(@Param("idList")List<Long> idList);

	Long findSkuIdAndAttValueId(@Param("standardProductUnitId") Long standardProductUnitId,@Param("attValueId") Long attValueId);

	List<SkuPO> findSkuBySkuSerialNo(@Param("skuNos")List<String> skuNos);

	List<SkuPO> findSkuBySkuIds(@Param("skuIds")List<Long> skuIds);

}
	
