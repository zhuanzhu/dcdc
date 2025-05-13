package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.CommodityProductUnitPageDTO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-product-fgj",contextId="CommodityProductUnitClient")
public interface CommodityProductUnitClient {

	/**
	 * 通过puid查询commodityTemplateId
	 * @param puId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/commodityProductUnit/queryCommodityTemplateIdByPuId" }, method = { RequestMethod.POST }) 
	public Long queryCommodityTemplateIdByPuId(Long puId);
	/**
	 * 根据名称查询pu
	 * @param puName
	 * @return
	 */
	@RequestMapping(value = { "/client/product/commodityProductUnit/queryPuByName" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitDTO> queryPuByName(String puName);

	/**
	 * 当pu对象中没有图片时调用该方法,
	 * 查询sku图片,sku图片为空时查询spu封面图片
	 * @param puId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/commodityProductUnit/queryPuNullImgUrl" }, method = { RequestMethod.POST }) 
	public String queryPuNullImgUrl(Long skuId);
	
	@RequestMapping(value = { "/client/product/commodityProductUnit/findSkuIdByPuId" }, method = { RequestMethod.POST }) 
	public Long findSkuIdByPuId(Long puId); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findCommodityProductUnitById" }, method = { RequestMethod.POST }) 
	public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findPuInfoBySuIdList" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitDTO> findPuInfoBySuIdList(@RequestParam("companyType") Integer companyType,@RequestParam("suIds")  List<String> suIds); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findByPUIdSkuId" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitDTO> findByPUIdSkuId(@RequestParam("puIdList") List<String> puIdList,@RequestParam("skuId")  Long skuId); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findByPUIds" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitDTO> findByPUIds(List<String> puIds); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findCommodityProductUnitAll" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/queryIsUnit" }, method = { RequestMethod.POST }) 
	public boolean queryIsUnit(Long puId); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/puIdsBySkuId" }, method = { RequestMethod.POST }) 
	public List<String> puIdsBySkuId(Long skuId); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findPictureByStorePUId" }, method = { RequestMethod.POST }) 
	public String findPictureByStorePUId(Long storeProductUnitId); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findMerchantPUOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<CommodityProductUnitDTO> findMerchantPUOfPage(CommodityProductUnitPageDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/findSUSPUByPUId" }, method = { RequestMethod.POST }) 
	public CommodityProductUnitDTO findSUSPUByPUId(Long puId); 
 
	@RequestMapping(value = { "/client/product/commodityProductUnit/queryIsCard" }, method = { RequestMethod.POST })
	public boolean queryIsCard(Long puId);
	
	@RequestMapping(value = { "/client/product/commodityProductUnit/findPuIdByJdSkuId" }, method = { RequestMethod.POST })
	public Long findPuIdByJdSkuId(Long skuId);
	 
	}