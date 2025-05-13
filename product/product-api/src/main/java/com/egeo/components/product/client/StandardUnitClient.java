package com.egeo.components.product.client;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.StandardUnitByTypeDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;


@FeignClient(name = "service-product-fgj",contextId="StandardUnitClient")
public interface StandardUnitClient {
	
	@RequestMapping(value = { "/client/product/standardUnit/findSuIdByStandardUnitCombinationId" }, method = { RequestMethod.POST })
	public List<String> findSuIdByStandardUnitCombinationId(@RequestParam("suCombId") Long suCombId, @RequestParam("platformId") Long platformId);
	
	@RequestMapping(value = { "/client/product/standardUnit/findSpuInfo" }, method = { RequestMethod.POST }) 
	public List<Map<String, Object>> findSpuInfo(List<String> suIdList); 
 
 
	@RequestMapping(value = { "/client/product/standardUnit/findPictureInfo" }, method = { RequestMethod.POST }) 
	public List<Map<String, Object>> findPictureInfo(List<String> suIdList); 
 
 
	@RequestMapping(value = { "/client/product/standardUnit/findStandardUnitById" }, method = { RequestMethod.POST }) 
	public StandardUnitDTO findStandardUnitById(Long standardUnitId); 
 

	@RequestMapping(value = { "/client/product/standardUnit/findStandardUnitByIdDto" }, method = { RequestMethod.POST }) 
	public StandardUnitDTO findStandardUnitById(StandardUnitDTO dto);
	
	@RequestMapping(value = { "/client/product/standardUnit/findSuCombinationMap" }, method = { RequestMethod.POST }) 
	public Map<Long, List<String>> findSuCombinationMap(@RequestParam("suId") Long suId, @RequestParam("platformId") Long platformId); 
 
 
	@RequestMapping(value = { "/client/product/standardUnit/countCouponSuBySuId" }, method = { RequestMethod.POST }) 
	public int countCouponSuBySuId(@RequestParam("suId") Long suId,@RequestParam("storeId")  Long storeId, @RequestParam("companyId") Long companyId, @RequestParam("companyType") Integer companyType,@RequestParam("platformId")  Long platformId); 
 
	/**
	 * 根据前台类目节点id、商品集合id分页查询su商品信息
	 * queryId：查询id
	 * type类型：1、类目节点id，2、商品集合id
     * @param couponType
     * @param saleWay
     * @param companyType 商品所有公司可见类型id -1、所有正式公司 -2、所有演示公司 -3、所有竞品公司
     * @param buyType
     */
	@RequestMapping(value = { "/client/product/standardUnit/standardUnitByType" }, method = { RequestMethod.POST }) 
	public PageResult<StandardUnitDTO> standardUnitByType(StandardUnitByTypeDTO dto);
	
	@RequestMapping(value = { "/client/product/standardUnit/countCouponSuBySuCombinationId" }, method = { RequestMethod.POST }) 
	public int countCouponSuBySuCombinationId(@RequestParam("suCombinationId") Long suCombinationId,@RequestParam("storeId")  Long storeId,@RequestParam("companyId")  Long companyId,@RequestParam("companyType")  Integer companyType,@RequestParam("platformId")  Long platformId); 
 
 
	@RequestMapping(value = { "/client/product/standardUnit/querySuCombinationBySuId" }, method = { RequestMethod.POST }) 
	public List<String> querySuCombinationBySuId(Long suId); 
 
 
	@RequestMapping(value = { "/client/product/standardUnit/findStandardUnitOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitPageDTO dto); 
	

	/**
	 * 根据su组合id查询su商品信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@RequestMapping(value = { "/client/product/standardUnit/findByStandardUnitCombinationId" }, method = { RequestMethod.POST })
	public List<StandardUnitDTO> findByStandardUnitCombinationId(
			@RequestParam("standardUnitCombinationId") Long standardUnitCombinationId,@RequestParam("platformId") Long platformId);
 

	/**
	 * 根据suid集合查询su信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/client/product/standardUnit/findBymerchantProdId" }, method = { RequestMethod.POST })
	public List<StandardUnitDTO> findBymerchantProdId(List<String> ids);
	@RequestMapping(value = { "/client/product/standardUnit/syncSaveSuSerachRule" }, method = { RequestMethod.POST })
	public JsonResult<Map<String, Object>> syncSaveSuSerachRule();
	
	
	
	
}