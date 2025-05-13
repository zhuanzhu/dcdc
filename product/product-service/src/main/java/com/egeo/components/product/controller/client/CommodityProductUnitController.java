package com.egeo.components.product.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.CommodityProductUnitPageDTO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.write.CommodityProductUnitWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/product/commodityProductUnit") 
public class CommodityProductUnitController implements CommodityProductUnitClient{ 

	@Autowired
	private CommodityProductUnitReadService commodityProductUnitReadService;
	@Autowired
	private CommodityProductUnitWriteService commodityProductUnitWriteService;


	@Override
	@RequestMapping(value = "/findSkuIdByPuId", method = { RequestMethod.POST })
	@ResponseBody
	public Long findSkuIdByPuId(@RequestBody  Long puId) {
		return commodityProductUnitReadService.findSkuIdByPuId(puId);
	} 
 
	@Override
	@RequestMapping(value = "/findCommodityProductUnitById", method = { RequestMethod.POST })
	@ResponseBody
	public CommodityProductUnitDTO findCommodityProductUnitById(@RequestBody CommodityProductUnitDTO dto) {
		return commodityProductUnitReadService.findCommodityProductUnitById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findPuInfoBySuIdList", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitDTO> findPuInfoBySuIdList(@RequestParam("companyType") Integer companyType,@RequestParam("suIds")  List<String> suIds) {
		return commodityProductUnitReadService.findPuInfoBySuIdList(companyType, com.egeo.utils.StringUtils.stringsToLongs(suIds));
	} 
 
	@Override
	@RequestMapping(value = "/findByPUIdSkuId", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitDTO> findByPUIdSkuId(@RequestParam("puIdList") List<String> puIdList,@RequestParam("skuId")  Long skuId) {
		return commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.stringsToLongs(puIdList), skuId);
	} 
 
	@Override
	@RequestMapping(value = "/findByPUIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitDTO> findByPUIds(@RequestBody  List<String> puIds) {
		if(puIds==null || puIds.size()==0) {
			return new ArrayList<CommodityProductUnitDTO>();
		}
		return commodityProductUnitReadService.findByPUIds(com.egeo.utils.StringUtils.stringsToLongs(puIds));
	} 
 
	@Override
	@RequestMapping(value = "/findCommodityProductUnitAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(@RequestBody CommodityProductUnitDTO dto) {
		return commodityProductUnitReadService.findCommodityProductUnitAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/queryIsUnit", method = { RequestMethod.POST })
	@ResponseBody
	public boolean queryIsUnit(@RequestBody  Long puId) {
		return commodityProductUnitReadService.queryIsUnit(puId);
	} 
 
	@Override
	@RequestMapping(value = "/puIdsBySkuId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> puIdsBySkuId(@RequestBody  Long skuId) {
		return com.egeo.utils.StringUtils.longsToStrings(commodityProductUnitReadService.puIdsBySkuId(skuId));
	} 
 
	@Override
	@RequestMapping(value = "/findPictureByStorePUId", method = { RequestMethod.POST })
	@ResponseBody
	public String findPictureByStorePUId(@RequestBody Long storeProductUnitId) {
		return commodityProductUnitReadService.findPictureByStorePUId(storeProductUnitId);
	} 
 
	@Override
	@RequestMapping(value = "/findMerchantPUOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<CommodityProductUnitDTO> findMerchantPUOfPage(@RequestBody CommodityProductUnitPageDTO dto) {
		return commodityProductUnitReadService.findMerchantPUOfPage(dto.getDto(), dto.getPage());
	} 
 
	@Override
	@RequestMapping(value = "/findSUSPUByPUId", method = { RequestMethod.POST })
	@ResponseBody
	public CommodityProductUnitDTO findSUSPUByPUId(@RequestBody  Long puId) {
		return commodityProductUnitReadService.findSUSPUByPUId(puId);
	}

	@Override
	@RequestMapping(value = "/queryIsCard", method = { RequestMethod.POST })
	@ResponseBody
	public boolean queryIsCard(@RequestBody  Long puId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.queryIsCard(puId);
	}

	@Override
	@RequestMapping(value = "/queryPuByName", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitDTO> queryPuByName(@RequestBody String puName) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.queryPuByName(puName);
	}

	@Override
	@RequestMapping(value = "/queryPuNullImgUrl", method = { RequestMethod.POST })
	@ResponseBody
	public String queryPuNullImgUrl(@RequestBody  Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.queryPuNullImgUrl(skuId);
	}

	@Override
	@RequestMapping(value = "/queryCommodityTemplateIdByPuId", method = { RequestMethod.POST })
	@ResponseBody
	public Long queryCommodityTemplateIdByPuId(@RequestBody  Long puId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.queryCommodityTemplateIdByPuId(puId);
	}

	@Override
	@RequestMapping(value = "/findPuIdByJdSkuId", method = { RequestMethod.POST })
	@ResponseBody
	public Long findPuIdByJdSkuId(@RequestBody  Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadService.findPuIdByJdSkuId(skuId);
	} 
} 
