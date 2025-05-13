package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.CommodityProductUnitCondition;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitReadDAO extends BaseReadDAO<CommodityProductUnitPO>{
	/**
	 * 根据skuid查询pu总条数
	 * @param id
	 * @return
	 */
	int countRecord(@Param("skuId")Long skuId);
	/**
	 * 根据puid集合查询pu信息
	 * @param puIds
	 * @return
	 */
	List<CommodityProductUnitCondition> findByPUIds(@Param("ids")List<Long> puIds);
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	List<Long> attValueIdByProductUnitId(@Param("productUnitId")Long productUnitId);
	
	/**
	 * 根据puid查询pu扩展信息
	 * @param puId
	 * @return
	 */
	CommodityProductUnitCondition findSUSPUByPUId(@Param("puId")Long puId);
	
	/**
	 * 根据名称查询pu
	 * @param puName
	 * @return
	 */
	List<CommodityProductUnitPO> queryPuByName(@Param("puName")String puName);
	/**
	 * 根据skuId查询puid集合
	 * @param skuId
	 * @return
	 */
	List<Long> puIdsBySkuId(@Param("skuId")Long skuId);
	/**
	 * 根据suid查询所有puid集合
	 * @param merchantProductId
	 * @return
	 */
	List<Long> findByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
	
	/**
	 * 通过puid查询commodityTemplateId
	 * @param puId
	 * @return
	 */
	Long queryCommodityTemplateIdByPuId(@Param("puId")Long puId);
	/**
	 * 根据pu草稿id集合查询正式puid集合
	 * @param puIdlist
	 * @return
	 */
	List<Long> findIdsByPUIds(@Param("ids")List<Long> puIdlist);
	/**
	 * 根据门店PUid查询pu图片信息
	 * @param storeProductUnitId
	 * @return
	 */
	CommodityProductUnitPO findPictureByStorePUId(@Param("storeProductUnitId")Long storeProductUnitId);
	
	List<CommodityProductUnitCondition> findCommodityProductUnitOfPage(
			@Param("po")CommodityProductUnitPO po, 
			@Param("page")Pagination page);
	
	int countCommodityProductUnitOfPage(@Param("po") CommodityProductUnitPO po);

    List<CommodityProductUnitPO> findPuListBySuId(@Param("suId")Long suId);
    
	List<CommodityProductUnitCondition> findMerchantPUOfPage(
			@Param("po")CommodityProductUnitCondition po,
			@Param("page")Pagination page);
	
	int countMerchantPUOfPage(@Param("po")CommodityProductUnitCondition po);

    Long findSkuIdByPuId(@Param("puId")Long puId);
    
    List<CommodityProductUnitCondition> findPuInfoBySuIdList(@Param("companyType")Integer companyType, @Param("suIds")List<Long> suIds);
    
    /**
     * 查询同skuId 的 pu
     * @param puIdList
     * @param skuId
     * @return
     */
	List<CommodityProductUnitPO> findByPUIdSkuId(@Param("ids") List<Long> puIdList,@Param("skuId") Long skuId);
	/**
	 * 
	 * @param po
	 * @return
	 */
	List<CommodityProductUnitPO> findCommodityProductUnitLimit(@Param("po")CommodityProductUnitPO po , @Param("page") Pagination page);

    Long findLastId();

    Long findPuIdByExtendSkuId(@Param("skuId")Long skuId);
}
	
