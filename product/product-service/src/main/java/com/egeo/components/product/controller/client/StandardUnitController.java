package com.egeo.components.product.controller.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitManage;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.dto.StandardUnitByTypeDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitPageDTO;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.read.impl.StandardUnitReadServiceImpl;
import com.egeo.components.product.service.write.StandardUnitWriteService;
import com.egeo.orm.PageResult;
import com.egeo.utils.StringUtils;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/client/product/standardUnit") 
public class StandardUnitController implements StandardUnitClient{ 

	@Autowired
	private StandardUnitReadService standardUnitReadService;
	@Autowired
	private StandardUnitWriteService standardUnitWriteService;
	private static final XLogger logger = XLogger.getLogger(StandardUnitController.class);


    @Resource(name = "standardUnit")
    private StandardUnitManage standardUnitManage;
    
	@Override
	@RequestMapping(value = "/findSpuInfo", method = { RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> findSpuInfo(@RequestBody  List<String> suIdList) {
		return standardUnitReadService.findSpuInfo(com.egeo.utils.StringUtils.stringsToLongs(suIdList));
	} 
 
	@Override
	@RequestMapping(value = "/findPictureInfo", method = { RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> findPictureInfo(@RequestBody  List<String> suIdList) {
		
		return standardUnitReadService.findPictureInfo(StringUtils.stringsToLongs(suIdList));
	} 
 
	@Override
	@RequestMapping(value = "/findStandardUnitByIdDto", method = { RequestMethod.POST })
	@ResponseBody
	public StandardUnitDTO findStandardUnitById(@RequestBody StandardUnitDTO dto) {
		return standardUnitReadService.findStandardUnitById(dto);
	} 


	@Override
	@RequestMapping(value = "/findByStandardUnitCombinationId", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardUnitDTO> findByStandardUnitCombinationId(
			@RequestParam("standardUnitCombinationId") Long standardUnitCombinationId,@RequestParam("platformId") Long platformId) {
		// TODO Auto-generated method stub
		return standardUnitReadService.findByStandardUnitCombinationId(standardUnitCombinationId, platformId);
	} 
	@Override
	@RequestMapping(value = "/findStandardUnitById", method = { RequestMethod.POST })
	@ResponseBody
	public StandardUnitDTO findStandardUnitById(@RequestBody  Long standardUnitId) {
		return standardUnitReadService.findStandardUnitById(standardUnitId);
	} 
	@Override
	@RequestMapping(value = "/findSuCombinationMap", method = { RequestMethod.POST })
	@ResponseBody
	public Map<Long, List<String>> findSuCombinationMap(@RequestParam("suId") Long suId, @RequestParam("platformId") Long platformId) {
		Map<Long, List<Long>> tmp = standardUnitReadService.findSuCombinationMap(suId, platformId);
		Map<Long, List<String>> rslt = new HashMap<Long, List<String>>();
		Iterator<Entry<Long, List<Long>>> iter = tmp.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Long, List<Long>> entry = iter.next();
			Long key = entry.getKey();
			List<Long> val = entry.getValue();
			rslt.put(key, com.egeo.utils.StringUtils.longsToStrings(val));
		}
		return rslt;
	} 
 
	@Override
	@RequestMapping(value = "/countCouponSuBySuId", method = { RequestMethod.POST })
	@ResponseBody
	public int countCouponSuBySuId(@RequestParam("suId") Long suId,@RequestParam("storeId")  Long storeId, @RequestParam("companyId") Long companyId, @RequestParam("companyType") Integer companyType,@RequestParam("platformId")  Long platformId) {
		return standardUnitReadService.countCouponSuBySuId(suId, storeId, companyId, companyType, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/countCouponSuBySuCombinationId", method = { RequestMethod.POST })
	@ResponseBody
	public int countCouponSuBySuCombinationId(@RequestParam("suCombinationId") Long suCombinationId,@RequestParam("storeId")  Long storeId,@RequestParam("companyId")  Long companyId,@RequestParam("companyType")  Integer companyType,@RequestParam("platformId")  Long platformId) {
		return standardUnitReadService.countCouponSuBySuCombinationId(suCombinationId, storeId, companyId, companyType, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/querySuCombinationBySuId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> querySuCombinationBySuId(@RequestBody  Long suId) {
		return com.egeo.utils.StringUtils.longsToStrings(standardUnitReadService.querySuCombinationBySuId(suId));
	} 
 
	@Override
	@RequestMapping(value = "/findStandardUnitOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<StandardUnitDTO> findStandardUnitOfPage(@RequestBody StandardUnitPageDTO dto) {
		return standardUnitReadService.findStandardUnitOfPage(dto.getDto(),dto.getPage());
	}

	@Override
	@RequestMapping(value = "/standardUnitByType", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<StandardUnitDTO> standardUnitByType(@RequestBody StandardUnitByTypeDTO dto) {
		// TODO Auto-generated method stub
		return standardUnitReadService.standardUnitByType(dto.getCouponType(), dto.getSaleWay(), dto.getQueryId(), dto.getType(), dto.getPlatformId(), dto.getUserBalance(), dto.getClientId(), null,dto.getCompanyId(), dto.getCompanyType(), dto.getStoreId(), dto.getPage(), dto.getBuyType());
	}

	@Override
	@RequestMapping(value = "/findSuIdByStandardUnitCombinationId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findSuIdByStandardUnitCombinationId(@RequestParam("suCombId") Long suCombId, @RequestParam("platformId") Long platformId) {
		// TODO Auto-generated method stub
		return com.egeo.utils.StringUtils.longsToStrings(standardUnitReadService.findSuIdByStandardUnitCombinationId(suCombId, platformId));
	}

	@Override
	@RequestMapping(value = "/findBymerchantProdId", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardUnitDTO> findBymerchantProdId(@RequestBody  List<String> ids) {
		// TODO Auto-generated method stub
		return standardUnitReadService.findBymerchantProdId(com.egeo.utils.StringUtils.stringsToLongs(ids));
	}

    /**
     * 刷新su商品搜索规则数据信息
     *
     * @return
     */
    @RequestMapping(value = "/syncSaveSuSerachRule")
    @ResponseBody
    public JsonResult<Map<String, Object>> syncSaveSuSerachRule() {
        logger.info("刷新su商品搜索规则数据信息");
        return standardUnitManage.syncSaveSuSerachRule();
    }

}
