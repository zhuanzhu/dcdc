package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.QueryProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitReadService {
	
	
	public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO dto);

	public PageResult<CommodityProductUnitDTO> findCommodityProductUnitOfPage(CommodityProductUnitDTO dto, Pagination page);

	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto);
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
	public List<CommodityProductUnitDTO> findByPUIds(List<Long> puIds);
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	public List<Long> attValueByProductUnitId(Long id);

	/**
	 * 判断pu是否是unit商品
	 * @param puId
	 * @return
	 */
	public boolean queryIsUnit(Long puId);

	/**
	 * 根据puid查询pu扩展信息
	 * @param puId
	 * @return
	 */
	public CommodityProductUnitDTO findSUSPUByPUId(Long puId);

	/**
	 * 根据名称查询pu
	 * @param puName
	 * @return
	 */
	public List<CommodityProductUnitDTO> queryPuByName(String puName);
	
	/**
	 * 当pu对象中没有图片时调用该方法,
	 * 查询sku图片,sku图片为空时查询spu封面图片
	 * @param puId
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
	 * 通过puid查询commodityTemplateId
	 * @param puId
	 * @return
	 */
	public Long queryCommodityTemplateIdByPuId(Long puId);
	/**
	 * 根据pu名称pu序列号门店名称查询pu信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CommodityProductUnitDTO> findPUByStoreNameSUNameOfPage(QueryProductUnitDTO dto, Pagination page);
	/**
	 * 根据门店puid查询pu图片信息
	 * @param storeProductUnitId
	 * @return
	 */
	public String findPictureByStorePUId(Long storeProductUnitId);

	List<CommodityProductUnitDTO> findPuListBySuId(Long suId);
	
	/**
	 * 分页查询运营方商品列表
	 * @param dto
	 * @param page
	 * @return
	 */
	PageResult<CommodityProductUnitDTO> findMerchantPUOfPage(CommodityProductUnitDTO dto, Pagination page);

    Long findSkuIdByPuId(Long puId);

    boolean queryIsCard(Long puId);
    
    List<CommodityProductUnitDTO> findPuInfoBySuIdList(Integer companyType, List<Long> suIds);

	public List<CommodityProductUnitDTO> findByPUIdSkuId(List<Long> puIdList, Long skuId);

	public List<CommodityProductUnitDTO> findCommodityProductUnitLimit(CommodityProductUnitDTO dto);

    Long findLastId();

    Long findPuIdByExtendSkuId(Long skuId);
	Long findPuIdByJdSkuId(Long skuId);
}
	