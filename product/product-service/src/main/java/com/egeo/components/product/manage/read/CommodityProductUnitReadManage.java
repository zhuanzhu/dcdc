package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.CommodityProductUnitCondition;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.QueryProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitReadManage {

	public CommodityProductUnitPO findCommodityProductUnitById(CommodityProductUnitPO po);

	public PageResult<CommodityProductUnitCondition> findCommodityProductUnitOfPage(CommodityProductUnitPO po, Pagination page);

	public List<CommodityProductUnitPO> findCommodityProductUnitAll(CommodityProductUnitPO po);
	/**
	 * 根据skuid查询pu总条数
	 * @param id
	 * @return
	 */
	public int countRecord(Long skuId);
	/**
	 * 根据puid集合查询pu信息
	 * @param puIds
	 * @return
	 */
	public List<CommodityProductUnitCondition> findByPUIds(List<Long> puIds);
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	public List<Long> attValueIdByProductUnitId(Long productUnitId);

	/**
	 * 根据puid查询pu扩展信息
	 * @param puId
	 * @return
	 */
	public CommodityProductUnitCondition findSUSPUByPUId(Long puId);

	/**
	 * 根据名称查询pu
	 * @param puName
	 * @return
	 */
	public List<CommodityProductUnitPO> queryPuByName(String puName);

	/**
	 * 当pu对象中没有图片时调用该方法,
	 * 查询sku图片,sku图片为空时查询spu封面图片
	 * @param skuId
	 * @return
	 */
	public String queryPuNullImgUrl(Long skuId);
	/**
	 * 根据skuId查询puid集合
	 * @param skuId
	 * @return
	 */
	public List<Long> puIdsBySkuId(Long skuId);
	/**
	 * 根据suid查询所有puid集合
	 * @param merchantProductId
	 * @return
	 */
	public List<Long> findByStandardUnitId(Long merchantProductId);

	/**
	 * 通过puid查询commodityTemplateId
	 * @param puId
	 * @return
	 */
	public Long queryCommodityTemplateIdByPuId(Long puId);
	/**
	 * 根据pu草稿id集合查询正式puid集合
	 * @param puIdlist
	 * @return
	 */
	public List<Long> findIdsByPUIds(List<Long> puIdlist);
	/**
	 * 
	 * @param queryProductUnitPO
	 * @param page
	 * @return
	 */
	public PageResult<CommodityProductUnitPO> findPUByStoreNameSUNameOfPage(QueryProductUnitPO queryProductUnitPO, Pagination page);
	/**
	 * 根据门店puid查询pu图片信息
	 * @param storeProductUnitId
	 * @return
	 */
	public String findPictureByStorePUId(Long storeProductUnitId);

	List<CommodityProductUnitPO> findPuListBySuId(Long suId);
	
	/**
	 * 查询运营方商品列表
	 * @param po
	 * @param page
	 * @return
	 */
	PageResult<CommodityProductUnitCondition> findMerchantPUOfPage(CommodityProductUnitCondition po,
			Pagination page);

    Long findSkuIdByPuId(Long platformId);
    
    List<CommodityProductUnitCondition> findPuInfoBySuIdList(Integer companyType, List<Long> suIds);

	public List<CommodityProductUnitPO> findByPUIdSkuId(List<Long> puIdList, Long skuId);

	public List<CommodityProductUnitPO> findCommodityProductUnitLimit(CommodityProductUnitPO po);

    Long findLastId();

    Long findPuIdByExtendSkuId(Long skuId);
}
	