package com.egeo.components.product.controller.api;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.product.business.MerchantProductManage;
import com.egeo.components.product.converter.MerchantProductConverter;
import com.egeo.components.product.converter.ProductUnitConverter;
import com.egeo.components.product.converter.SellPlatformMerchantProdConverter;
import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.enums.MerchantProductConstant;
import com.egeo.components.product.facade.CommodityProductUnitFacade;
import com.egeo.components.product.facade.StandardProductUnitFacade;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.vo.CommodityProductUnitWarehouseStockVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.log.LogChange;
import com.egeo.utils.log.LogMsgContentEntityToMap;
import com.egeo.utils.log.LogUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProduct")
public class MerchantProductAction extends BaseSpringController {

    @Resource(name = "merchantProduct")
    private MerchantProductManage merchantProductManage;
    @Autowired
    private StandardProductUnitFacade standardProductUnitFacade;

    @Autowired
    public CommodityProductUnitFacade  commodityProductUnitFacade;
    @Autowired
    public CommodityProductUnitWarehouseStockClient  commodityProductUnitWarehouseStockClient;

    
    @Autowired
    public StandardUnitAction standardUnitAction;
    // 业务方法：
    @RequestMapping(value = "/findMerchantProductById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findMerchantProductById(Long merchantProductId, HttpServletRequest req) {
        logger.info("根据su草稿id查询su草稿信息");
        MerchantProductDTO dto = new MerchantProductDTO();
        dto.setId(merchantProductId);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        
        CacheUser user = this.getCacheUser();
        String ip = req.getRemoteAddr();
        Map<String, Object> map = merchantProductManage.findMerchantProductById(dto,user,ip);
        return JsonResult.success(map);

    }
 // 业务方法：
    @RequestMapping(value = "/findMerchantList")
    @ResponseBody
    public JsonResult<List<Map<Long,String>>> findMerchantList() {
    	List<Map<Long,String>> mapList=standardProductUnitFacade.findMerchantList();
        return JsonResult.success(mapList);
    }
    
    // 业务方法：
    @RequestMapping(value = "/findMerchantProductAll")
    @ResponseBody
    public JsonResult<List<MerchantProductVO>> findMerchantProductAll(MerchantProductVO vo, HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<MerchantProductDTO> rt = merchantProductManage.findMerchantProductAll(dto);
        return JsonResult.success(MerchantProductConverter.toVO(rt));
    }

    // 业务方法：
    @RequestMapping(value = "/findMerchantProductOfPage")
    @ResponseBody
    public JsonResult<PageResult<MerchantProductVO>> findMerchantProductOfPage(BigDecimal priceStart,BigDecimal priceEnd,Integer profitStart,Integer profitEnd,MerchantProductVO vo, Long beginTime,Long finishTime, Pagination page, HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        List<String> nameList = new ArrayList<>();
        if(EmptyUtil.isNotEmpty(vo.getName())){
             nameList = JSONArray.parseArray(vo.getName(), String.class);

        }
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        Date startTime=null;
        Date endTime=null;
        if (EmptyUtil.isNotEmpty(beginTime)) {
                startTime= new Date(beginTime);
        }
        if(EmptyUtil.isNotEmpty(finishTime)){
            endTime=new Date(finishTime+(24*60*60*1000-1));
        }
        
        if(StringUtils.isNotBlank(page.getOrderBy())) {
        	if(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_CREATE_TIME_CODE.equals(page.getOrderBy())) {
        		page.setOrderBy(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_CREATE_TIME_NAME);
        	} else if(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_FRONT_NUMBER_CODE.equals(page.getOrderBy())) {
        		page.setOrderBy(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_FRONT_NUMBER_NAME);
        	}
        }

        PageResult<MerchantProductVO> rt = merchantProductManage.findMerchantProductOfPage(null,priceStart,priceEnd,profitStart,profitEnd,startTime,endTime,dto, page,nameList);
        List<MerchantProductVO> list = rt.getList();
        PageResult<MerchantProductVO> result = new PageResult<MerchantProductVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
        return JsonResult.success(result);

    }

    @RequestMapping(value = "/exportMerchantProduct")
    @ResponseBody
    public JsonResult<Map<String, Object>> exportMerchantProduct(BigDecimal priceStart,BigDecimal priceEnd,
                                                                 Integer profitStart,Integer profitEnd,MerchantProductVO vo,
                                                                 Long beginTime,Long finishTime,
                                                                 HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        List<String> nameList = new ArrayList<>();
        if(EmptyUtil.isNotEmpty(vo.getName())){
            nameList = JSONArray.parseArray(vo.getName(), String.class);
        }
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        Date startTime=null;
        Date endTime=null;
        if (EmptyUtil.isNotEmpty(beginTime)) {
            startTime= new Date(beginTime);
        }
        if(EmptyUtil.isNotEmpty(finishTime)){
            endTime=new Date(finishTime+(24*60*60*1000-1));
        }
        JsonResult<Map<String, Object>> rt = merchantProductManage
                .exportMerchantProductOfPage(null,priceStart,priceEnd,profitStart,profitEnd,startTime,endTime,dto,nameList);
        return rt;

    }

    // 业务方法：
    @RequestMapping(value = "/findPlatformMerchantProductOfPage")
    @ResponseBody
    public JsonResult<PageResult<MerchantProductVO>> findPlatformMerchantProductOfPage(BigDecimal priceStart,BigDecimal priceEnd,Integer profitStart,Integer profitEnd,MerchantProductVO vo, Long beginTime,Long finishTime, Pagination page, HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        List<String> nameList = new ArrayList<>();
        if(EmptyUtil.isNotEmpty(vo.getName())){
             nameList = JSONArray.parseArray(vo.getName(), String.class);

        }
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        Date startTime=null;
        Date endTime=null;
        if (EmptyUtil.isNotEmpty(beginTime)) {
                startTime= new Date(beginTime);
        }
        if(EmptyUtil.isNotEmpty(finishTime)){
            endTime=new Date(finishTime+(24*60*60*1000-1));
        }
        
        if(StringUtils.isNotBlank(page.getOrderBy())) {
        	if(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_CREATE_TIME_CODE.equals(page.getOrderBy())) {
        		page.setOrderBy(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_CREATE_TIME_NAME);
        	} else if(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_FRONT_NUMBER_CODE.equals(page.getOrderBy())) {
        		page.setOrderBy(MerchantProductConstant.MERCHANT_PRODUCT_ORDER_BY_FRONT_NUMBER_NAME);
        	}
        }

        PageResult<MerchantProductVO> rt = merchantProductManage.findMerchantProductOfPage(1,priceStart,priceEnd,profitStart,profitEnd,startTime,endTime,dto, page,nameList);
        List<MerchantProductVO> list = rt.getList();
        PageResult<MerchantProductVO> result = new PageResult<MerchantProductVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }


    /**
     * 新增su草稿信息
     *
     * @param vo                           su信息
     * @param picture                      列表图片
     * @param pictureList                  轮播图片集合
     * @param webBannerPictureList         web轮播图图片地址
     * @param sellPlatformMerchantProdList su商品平台关系（此功能去除）
     * @param clientList                   客户端id集合
     * @param companyList                  公司id集合
     * @param content                      su商品详情
     * @param productUnitVOList            pu集合
     * @param tagList                      标签id集合
     * @param keywordList                  关键词集合
     * @param demoCompanyList              演示公司集合
     * @param competingCompanyList         竞品公司集合
     * @param storeIdList         		   门店id集合
     * @param membershipIdList             会籍id集合
     * @param req
     * @return
     */
    @RequestMapping(value = "/insertMerchantProductWithTx")
    @ResponseBody
    public JsonResult<Long> insertMerchantProductWithTx(
            MerchantProductVO vo,
            String picture,
            String pictureList,
            String webBannerPictureList,
            String sellPlatformMerchantProdList,
            String clientList,
            String companyList,
            String content,
            String productUnitVOList,
            String tagList,
            String keywordList,
            String demoCompanyList,
            String competingCompanyList,
            String storeIdList,
            String membershipIdList,
            HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        CacheUser userCache = this.getCacheUser();
        Long userId = userCache.getId();// 用户id
        String userName = userCache.getName();// 用户名称
        String ip = HostUtils.getClientIP(req);
        //根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        
        dto.setCreateUserid(userId);
        dto.setCreateUsername(userName);
        dto.setCreateUserip(ip);
        dto.setCreateUsermac(mac);
        dto.setUpdateUserid(userId);
        dto.setUpdateUsername(userName);
        dto.setUpdateUserip(ip);
        dto.setUpdateUsermac(mac);

        // su商品平台关系（此功能去除）
        List<SellPlatformMerchantProdVO> sellPlatformMerchantProdVOList = null;
        // pu列表集合
        List<ProductUnitVO> productUnitList = null;
        // 标签id集合
        List<Long> tags = null;
        // 关键词集合
        List<String> keywords = null;
        // 门店id集合
        List<Long> storeIds = null;
        // 会籍id集合
        List<Long> membershipIds = null;
        if (EmptyUtil.isNotEmpty(sellPlatformMerchantProdList))
            sellPlatformMerchantProdVOList = JSONArray.parseArray(sellPlatformMerchantProdList, SellPlatformMerchantProdVO.class);
        if (EmptyUtil.isNotEmpty(productUnitVOList))
            productUnitList = JSONArray.parseArray(productUnitVOList, ProductUnitVO.class);
        if (EmptyUtil.isNotEmpty(tagList))	
            tags = JSONArray.parseArray(tagList, Long.class);
        if (EmptyUtil.isNotEmpty(keywordList))
            keywords = JSONArray.parseArray(keywordList, String.class);
        if (EmptyUtil.isNotEmpty(keywordList))
            keywords = JSONArray.parseArray(keywordList, String.class);
        if (EmptyUtil.isNotEmpty(storeIdList))
        	storeIds = JSONArray.parseArray(storeIdList, Long.class);
        if (EmptyUtil.isNotEmpty(membershipIdList))
        	membershipIds = JSONArray.parseArray(membershipIdList, Long.class);


        JsonResult<Long> longJsonResult = merchantProductManage.insertMerchantProductWithTx(
                dto, picture, pictureList, webBannerPictureList, SellPlatformMerchantProdConverter.toDTOs(sellPlatformMerchantProdVOList),
                clientList, companyList, content, ProductUnitConverter.toDTOs(productUnitList), tags, keywords, demoCompanyList, competingCompanyList,
                storeIds, membershipIds);
        return longJsonResult;

    }

    @RequestMapping(value = "/insertAndUpMerchantProductQuickWithTx")
    @ResponseBody
    public JsonResult<Long> insertAndUpMerchantProductQuickWithTx(
            String salePrice,
            String standardProductUnitId,
            String stockNum,
            HttpServletRequest req) {
    	//1.创建
    	if(stockNum==null || stockNum.length()==0) {
    		throw new BusinessException("库存只能是数字" );
    	}
    	Long realStockNum = Long.valueOf(stockNum);
    	
    	JsonResult<Long> longJsonResult = insertMerchantProductQuickWithTx(salePrice, standardProductUnitId, req);
    	//2.通过
    	merchantProductPassWithTx(longJsonResult.getData(), null, 1, req);
    	//加库存
    	List<CommodityProductUnitDTO> cpuDTOs = commodityProductUnitFacade.findPuListBySuId(longJsonResult.getData());

    	//上架
    	standardUnitAction.putawaySoldOut(3, longJsonResult.getData(), 1);
    	//刷新缓存
    	standardUnitAction.syncSaveSuSerachRule();
    	if(cpuDTOs!=null && cpuDTOs.size()>0 && realStockNum>0) {
    		CommodityProductUnitDTO cpuDTO = null;
        	for(CommodityProductUnitDTO cpuOne : cpuDTOs) {
        		if(cpuDTO==null) {
        			cpuDTO = cpuOne;
        			continue;
        		}
        		if(cpuOne.getCreateTime()!=null && cpuDTO.getCreateTime()!=null) {
        			if( cpuOne.getCreateTime().compareTo(cpuDTO.getCreateTime())>0) {
        				cpuDTO = cpuOne;
        			}
        		}
        	}
        	CommodityProductUnitWarehouseStockVO vo = new CommodityProductUnitWarehouseStockVO();
        	vo.setCommodityProductUnitId(cpuDTO.getId());
        	vo.setRealStockNum(realStockNum);
        	if(cpuDTO!=null) {
        		Long platformId = null;
        		String str = req.getHeader("platformId");		
        		if(EmptyUtil.isNotEmpty(str)){
        			platformId = Long.valueOf(str);
        		}
        		CacheUser userCache = this.getCacheUser();
        		//获得客户端的ip地址 
        		String ip = req.getRemoteAddr();
        		//根据ip获取mac地址
        		String mac;
        		try {
        			mac = HostUtils.getLocalMac(ip);
        		} catch (Exception e) {
        			throw new BusinessException("获取mac地址异常" + e.getMessage());
        		}
        		Long userId = userCache.getId();
        		String userName = userCache.getName();
        		vo.setPlatformId(platformId);
        		vo.setIp(ip);
        		vo.setMac(mac);
        		try {

            		commodityProductUnitWarehouseStockClient.merchandiseStockWithTx(vo);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
        	}
    	}
    	return longJsonResult;
    }
    		
    @RequestMapping(value = "/insertMerchantProductQuickWithTx")
    @ResponseBody
    public JsonResult<Long> insertMerchantProductQuickWithTx(
            String salePrice,
            String standardProductUnitId,
            HttpServletRequest req) {
    	Long spuId = Long.valueOf(standardProductUnitId);
    	Map<String, Object> spuMap = standardProductUnitFacade.findStandardProductUnitByIdQuick(spuId);
    	if(!spuMap.containsKey("standardProductUnit")) {
    		throw new BusinessException("解析产品数据异常" );
    	}
    	if(!spuMap.containsKey("skuList")) {
    		throw new BusinessException("解析产品数据异常" );
    	}
    	if(!spuMap.containsKey("keyWordList")) {
    		throw new BusinessException("解析产品数据异常" );
    	}
    	StandardProductUnitDTO spuDTO = (StandardProductUnitDTO) spuMap.get("standardProductUnit");
    	List<SkuDTO> skuList = (List<SkuDTO>) spuMap.get("skuList");
    	if(skuList.size()>1) {
    		throw new BusinessException("暂不支持多SKU操作" );
    	}
    	List<String> keywords = (List<String>) spuMap.get("keyWordList");
        MerchantProductDTO dto = new MerchantProductDTO();
        dto.setMerchantId(1l);
        dto.setStandardProductUnitId(spuDTO.getId());
        dto.setName(spuDTO.getName());
    	dto.setFrontSerialNumber(merchantProductManage.findMaxfrontSerialNumber()+1);
    	dto.setIsVisible(0);
    	dto.setSaleWay(1);
    	dto.setBuyType(1);
    	dto.setFreightExplain("自营类商品免邮，其他运营方商品邮费标准请以详见结算页面显示为准");
    	dto.setShipmentsExplain("自营类商品免邮，其他运营方商品邮费标准请以详见结算页面显示为准");
    	dto.setFreightExplain("自营类商品免邮，其他运营方商品邮费标准请以详见结算页面显示为准");
    	dto.setStatus(2);
    	dto.setIsSpuKeyword(3);
    	dto.setStoreId(1l);
    	dto.setSupplierId(spuDTO.getSupplierId());
    	dto.setPresellPeriod(0l);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        CacheUser userCache = this.getCacheUser();
        Long userId = userCache.getId();// 用户id
        String userName = userCache.getName();// 用户名称
        String ip = HostUtils.getClientIP(req);
        //根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        
        dto.setCreateUserid(userId);
        dto.setCreateUsername(userName);
        dto.setCreateUserip(ip);
        dto.setCreateUsermac(mac);
        dto.setUpdateUserid(userId);
        dto.setUpdateUsername(userName);
        dto.setUpdateUserip(ip);
        dto.setUpdateUsermac(mac);
        dto.setSalePrice(new BigDecimal(salePrice));
        if(skuList.get(0).getSkuMarketPrice()!=null) {
            dto.setMarketPrice(skuList.get(0).getSkuMarketPrice());
        }
        
        
    	String picture = spuDTO.getPicture();
        String pictureList = JSON.toJSONString(spuDTO.getPictureDTOs());
        String webBannerPictureList = null;
        String sellPlatformMerchantProdList = "[{\"name\":\"天猫旗舰店\",\"sellPlatformId\":3,\"salePrice\":\"\",\"path\":\"\"},{\"name\":\"京东旗舰店\",\"sellPlatformId\":2,\"salePrice\":\"\",\"path\":\"\"},{\"name\":\"京东自营\",\"sellPlatformId\":1,\"salePrice\":\"\",\"path\":\"\"}]";
        String clientList="[2]";
        String companyList="[-1]";
        String content = spuDTO.getContent();
        String tagList = "[]";
        //String keywordList;
        String demoCompanyList = null;
        String competingCompanyList = null;
        String storeIdList="[]";
        String membershipIdList="[]";
        // su商品平台关系（此功能去除）
        List<SellPlatformMerchantProdVO> sellPlatformMerchantProdVOList = null;
        // pu列表集合
        List<ProductUnitVO> productUnitList = new ArrayList<ProductUnitVO>();

        for(SkuDTO sku : skuList) {
        	ProductUnitVO puvo = new ProductUnitVO(); 
        	puvo.setChecked(true);
        	puvo.setName(sku.getSkuName());
        	puvo.setStatus(3);
        	puvo.setSkuId(sku.getId());
        	puvo.setSalePrice(new BigDecimal(salePrice));
        	puvo.setPuPicUrl(sku.getSkuPicUrl());
        	productUnitList.add(puvo);
        }
        // 标签id集合
        List<Long> tags = null;
        // 门店id集合
        List<Long> storeIds = null;
        // 会籍id集合
        List<Long> membershipIds = null;
        if (EmptyUtil.isNotEmpty(sellPlatformMerchantProdList))
            sellPlatformMerchantProdVOList = JSONArray.parseArray(sellPlatformMerchantProdList, SellPlatformMerchantProdVO.class);
        if (EmptyUtil.isNotEmpty(tagList))	
            tags = JSONArray.parseArray(tagList, Long.class);
        if (EmptyUtil.isNotEmpty(storeIdList))
        	storeIds = JSONArray.parseArray(storeIdList, Long.class);
        if (EmptyUtil.isNotEmpty(membershipIdList))
        	membershipIds = JSONArray.parseArray(membershipIdList, Long.class);


        JsonResult<Long> longJsonResult = merchantProductManage.insertMerchantProductWithTx(
                dto, picture, pictureList, webBannerPictureList, SellPlatformMerchantProdConverter.toDTOs(sellPlatformMerchantProdVOList),
                clientList, companyList, content, ProductUnitConverter.toDTOs(productUnitList), tags, keywords, demoCompanyList, competingCompanyList,
                storeIds, membershipIds);
        return longJsonResult;

    }
    
    
    
    /**
     * 根据su商品草稿id更新su商品草稿信息
     *
     * @param vo                           su商品信息
     * @param sellPlatformMerchantProdList su商品平台关系（此功能去除）
     * @param clientList                   客户端id集合
     * @param companyList                  公司id集合
     * @param content                      su商品详情
     * @param tagList                      标签id集合
     * @param keywordList                  关键词id集合
     * @param demoCompanyList              演示公司集合
     * @param competingCompanyList         竞品公司集合
     * @param storeIdList         		       门店id集合
     * @param membershipIdList             会籍id集合
     * @param req
     * @return
     */
    @RequestMapping(value = "/updateMerchantProductByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateMerchantProductByIdWithTx(
            MerchantProductVO vo,
            String sellPlatformMerchantProdList,
            String clientList,
            String companyList,
            String content,
            String tagList,
            String keywordList,
            String demoCompanyList,
            String competingCompanyList,
            String webBannerPictureList,
            String storeIdList,
            String membershipIdList,
            HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        // 赋值更新用户信息
        saveUpdateUserInfo(dto, req);
        // 前端如果传为空则赋待审核1
        if (EmptyUtil.isEmpty(vo.getStatus())) {
            vo.setStatus(1);
        }
        // 标签id集合
        List<Long> tags = null;
        // 平台信息集合
        List<SellPlatformMerchantProdVO> sellPlatformMerchantProdVOList = null;
        // 关键词集合
        List<String> keywords = null;
        // 门店id集合
        List<Long> storeIds = null;
        // 会籍id集合
        List<Long> membershipIds = null;
        if (EmptyUtil.isNotEmpty(tagList))
            tags = JSONArray.parseArray(tagList, Long.class);
        if (EmptyUtil.isNotEmpty(keywordList))
            keywords = JSONArray.parseArray(keywordList, String.class);
        if (EmptyUtil.isNotEmpty(sellPlatformMerchantProdList))
            sellPlatformMerchantProdVOList = JSONArray.parseArray(sellPlatformMerchantProdList, SellPlatformMerchantProdVO.class);
        if (EmptyUtil.isNotEmpty(storeIdList))
        	storeIds = JSONArray.parseArray(storeIdList, Long.class);
        if (EmptyUtil.isNotEmpty(membershipIdList))
        	membershipIds = JSONArray.parseArray(membershipIdList, Long.class);
        
        // 查询修改前数据
        Map<String, Object> oldObj = merchantProductManage.findMerchantProductById(dto);

        int rt = merchantProductManage.updateMerchantProductWithTx(
                dto, SellPlatformMerchantProdConverter.toDTOs(sellPlatformMerchantProdVOList), 
                clientList, companyList, content, tags, keywords, demoCompanyList, competingCompanyList, webBannerPictureList,
                storeIds,membershipIds);
        // 查询修改后数据
        Map<String, Object> newObj = merchantProductManage.findMerchantProductById(dto);

        MerchantProductShowVO oldMerchantProductDTO = (MerchantProductShowVO) oldObj.get("merchantProduct");
        List<ProductUnitDTO> oldProductUnitList = (List<ProductUnitDTO>) oldObj.get("productUnitList");

        MerchantProductShowVO newMerchantProductDTO = (MerchantProductShowVO) newObj.get("merchantProduct");
        List<ProductUnitDTO> newProductUnitList = (List<ProductUnitDTO>) newObj.get("productUnitList");

        List<Object> diff = LogUtil.getObjDifference(oldMerchantProductDTO, newMerchantProductDTO);

        for (int j = 0; j < newProductUnitList.size(); j++) {
            List<Object> list = LogMsgContentEntityToMap.getObjDifference(
                    oldProductUnitList.get(j), newProductUnitList.get(j), newProductUnitList.get(j).getClass(), newProductUnitList.get(j).getName());
            diff.addAll(list);
        }
        EgeoLog log = new EgeoLog();
        log.setMsgId(LogConstant.STANDARDUNIT_REVOCATION.getStatus());
        log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
        log.setOperObject("MerchantProductAction_updateMerchantProductByIdWithTx");
        log.setOperatorObjId(dto.getId());
        log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
        log.setOperatorObjName(newMerchantProductDTO.getName());
        log.setOperatorObjCode(newMerchantProductDTO.getMerchantProductSerialNumber());
        //设置msg详情
        log.setMsgContent(JSON.toJSONString(diff));
        EgeoBusinessLogCommon.fillLogValue(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}

        return JsonResult.success(rt);
    }

    /**
     * 赋值更新用户信息
     *
     * @param dto
     * @param req
     */
    private void saveUpdateUserInfo(MerchantProductDTO dto, HttpServletRequest req) {
        CacheUser userCache = this.getCacheUser();
        Long userId = userCache.getId();// 用户id
        String userName = userCache.getName();// 用户名称
        String ip = HostUtils.getClientIP(req);
        //根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        dto.setUpdateUserid(userId);
        dto.setUpdateUsername(userName);
        dto.setUpdateUserip(ip);
        dto.setUpdateUsermac(mac);
    }


    /**
     * 根据su草稿id更新su草稿图片信息
     *
     * @param merchantProductId    su商品id
     * @param picture              su封面图片
     * @param pictureList          suApp轮播图图片地址
     * @param webBannerPictureList suWeb端轮播图地址
     * @return
     */
    @RequestMapping(value = "/updateMerchantProductPictureByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateMerchantProductByIdWithTx(
            Long merchantProductId,
            String picture,
            String pictureList,
            String webBannerPictureList,
            Integer status,
            HttpServletRequest req) {
        logger.info("根据su草稿id更新su草稿图片信息,su草稿id：{}", merchantProductId);
        String str = req.getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        }
        //su草稿状态空值处理
        if (EmptyUtil.isEmpty(status)) {
            status = 1;
        }
        // suApp端轮播图
        List<String> pictures = null;
        if (EmptyUtil.isNotEmpty(pictureList))
            pictures = new ArrayList<String>(JSONArray.parseArray(pictureList, String.class));
        // suweb端轮播图
        List<String> webBannerPictures = null;
        if (EmptyUtil.isNotEmpty(webBannerPictureList))
            webBannerPictures = new ArrayList<String>(JSONArray.parseArray(webBannerPictureList, String.class));

        MerchantProductDTO dto = new MerchantProductDTO();
        dto.setId(merchantProductId);
        dto.setStatus(status);
        dto.setPlatformId(platformId);
        // 赋值更新用户信息
        saveUpdateUserInfo(dto, req);

        // 查询修改前数据
        Map<String, Object> oldObj = merchantProductManage.findMerchantProductById(dto);

        int rt = merchantProductManage.updateMerchantProductPictureByIdWithTx(merchantProductId, picture, pictures, webBannerPictures, dto);

        // 查询修改后数据
        Map<String, Object> newObj = merchantProductManage.findMerchantProductById(dto);

        String oldPicture = (String) oldObj.get("picture");
        List<String> oldPictureList = (List<String>) oldObj.get("pictureList");

        String newPicture = (String) newObj.get("picture");
        List<String> newPictureList = (List<String>) newObj.get("pictureList");
        MerchantProductShowVO newMerchantProductDTO = (MerchantProductShowVO) newObj.get("merchantProduct");
        List<Object> diff = new ArrayList<>();
        if (!oldPicture.equals(newPicture)) {
            diff.add(new LogChange("picture", oldPicture, newPicture));
        }
        if (!oldPictureList.equals(newPictureList)) {
            diff.add(new LogChange("pictureList", oldPictureList.toString(), newPictureList.toString()));
        }

        EgeoLog log = new EgeoLog();
        log.setMsgId(LogConstant.STANDARDUNIT_REVOCATION.getStatus());
        log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
        log.setOperObject("MerchantProductAction_updateMerchantProductByIdWithTx");
        log.setOperatorObjId(dto.getId());
        log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
        log.setOperatorObjName(newMerchantProductDTO.getName());
        log.setOperatorObjCode(newMerchantProductDTO.getMerchantProductSerialNumber());
        //设置msg详情
        log.setMsgContent(JSON.toJSONString(diff));
        EgeoBusinessLogCommon.fillLogValue(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}

        return JsonResult.success(rt);
    }

    /**
     * 根据su草稿id更新supu信息
     * @param productUnitVOList
     * @param status
     * @param req
     * @return
     */
    @RequestMapping(value = "/updateProductUnitByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateMerchantProductByIdWithTx(String productUnitVOList, Integer status, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        }
        List<ProductUnitVO> productUnitList = null;
        if (EmptyUtil.isNotEmpty(productUnitVOList)) {
            productUnitList = new ArrayList<ProductUnitVO>(JSONArray.parseArray(productUnitVOList, ProductUnitVO.class));
        }
        MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
        merchantProductDTO.setId(productUnitList.get(0).getMerchantProductId());
        merchantProductDTO.setStatus(status);
        merchantProductDTO.setPlatformId(platformId);
        // 赋值更新用户信息
        saveUpdateUserInfo(merchantProductDTO, req);

        // 查询修改前数据
        Map<String, Object> oldObj = merchantProductManage.findMerchantProductById(merchantProductDTO);
        // 查询修改后数据
        Map<String, Object> newObj = merchantProductManage.findMerchantProductById(merchantProductDTO);

        MerchantProductShowVO merchantProductShowVO = (MerchantProductShowVO)newObj.get("merchantProduct");
        List<Long> companyIds = merchantProductShowVO.getCompanyIds();
        List<Long> demoCompanyIds = merchantProductShowVO.getDemoCompanyIds();
        List<Long> competingCompanyIds = merchantProductShowVO.getCompetingCompanyIds();

        int rt = merchantProductManage.updateProductUnitByIdWithTx(productUnitList, merchantProductDTO,companyIds,demoCompanyIds,competingCompanyIds);

        MerchantProductShowVO oldMerchantProductDTO = (MerchantProductShowVO) oldObj.get("merchantProduct");
        List<ProductUnitDTO> oldProductUnitList = (List<ProductUnitDTO>) oldObj.get("productUnitList");

        MerchantProductShowVO newMerchantProductDTO = (MerchantProductShowVO) newObj.get("merchantProduct");
        List<ProductUnitDTO> newProductUnitList = (List<ProductUnitDTO>) newObj.get("productUnitList");

        List<Object> diff = LogUtil.getObjDifference(oldMerchantProductDTO, newMerchantProductDTO);

        for (int j = 0; j < newProductUnitList.size(); j++) {
            List<Object> list = LogMsgContentEntityToMap.getObjDifference(
                    oldProductUnitList.get(j), newProductUnitList.get(j), newProductUnitList.get(j).getClass(), newProductUnitList.get(j).getName());
            diff.addAll(list);
        }
        EgeoLog log = new EgeoLog();
        log.setMsgId(LogConstant.STANDARDUNIT_REVOCATION.getStatus());
        log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
        log.setOperObject("MerchantProductAction_updateMerchantProductByIdWithTx");
        log.setOperatorObjId(newMerchantProductDTO.getId());
        log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
        log.setOperatorObjName(newMerchantProductDTO.getName());
        log.setOperatorObjCode(newMerchantProductDTO.getMerchantProductSerialNumber());
        //设置msg详情
        log.setMsgContent(JSON.toJSONString(diff));
        EgeoBusinessLogCommon.fillLogValue(log, req);

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteMerchantProductWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteMerchantProductWithTx(MerchantProductVO vo, HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = merchantProductManage.deleteMerchantProductWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 提交审核
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/submitAuditWithTx")
    @ResponseBody
    public JsonResult<Integer> submitAuditWithTx(MerchantProductVO vo, HttpServletRequest req) {
        MerchantProductDTO dto = MerchantProductConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = merchantProductManage.submitAuditWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 批量通过
     * @param ids
     * @param req
     * @return
     */
    @RequestMapping(value = "/passAllAuditWithTx")
    @ResponseBody
    public JsonResult<Integer> passAllAuditWithTx(String ids, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        }

        int rt = merchantProductManage.passAllAuditWithTx(ids, platformId, req);
        if (rt == 0) {
            return JsonResult.fail("请选择商品");
        }
        return JsonResult.success(rt);
    }

    /**
     * 根据id审核是否通过
     * @param merchantProductId
     * @param cause
     * @param type
     * @param req
     * @return
     */
    @RequestMapping(value = "/merchantProductPassWithTx")
    @ResponseBody
    public JsonResult<Integer> merchantProductPassWithTx(Long merchantProductId, String cause, int type, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        }
        int rt = merchantProductManage.merchantProductPassWithTx(merchantProductId, cause, type, platformId, req);
        if (rt == 0) {
            return  JsonResult.fail("请选择商品");
        }
        return JsonResult.success(rt);
    }

    /**
     * 根据su草稿id查询基本信息（app预览）
     *
     * @param merchantProductId
     * @return
     */
    @RequestMapping(value = "/findMerchantProductAppById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findMerchantProductAppById(Long merchantProductId) {
        logger.info("根据su草稿id查询基本信息（app预览）");
        Map<String, Object> map = merchantProductManage.findMerchantProductAppById(merchantProductId);
        return JsonResult.success(map);

    }


    /**
     * 标签管理,商品草稿详情展示
     * @param param
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findCommodityDetailsOfPage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PageResult<MerchantProductVO>> commodityDetails(@RequestBody Map<String, Object> param,
                                                                      HttpServletRequest req) throws Exception {
        PageResult<MerchantProductVO> result = new PageResult<MerchantProductVO>();
        try {
            CommodityDetailsDTO dto = new CommodityDetailsDTO();
            Pagination page = new Pagination();
            if (!EmptyUtil.isEmpty(param)) {
                page.setPageNo((int) param.get("pageNo"));
                page.setPageSize((int) param.get("pageSize"));
                if (!EmptyUtil.isEmpty(param.get("name"))) {
                    dto.setName(param.get("name").toString());
                }
                if (!EmptyUtil.isEmpty(param.get("merchantProductSerialNumber"))) {
                    dto.setMerchantProductSerialNumber(param.get("merchantProductSerialNumber").toString());
                }
                if (!EmptyUtil.isEmpty(param.get("beginSalePrice"))) {
                    dto.setBeginSalePrice(new BigDecimal(param.get("beginSalePrice").toString()));
                }
                if (!EmptyUtil.isEmpty(param.get("endSalePrice"))) {
                    dto.setEndSalePrice(new BigDecimal(param.get("endSalePrice").toString()));
                }
                if (!EmptyUtil.isEmpty(param.get("beginPromotionPrice"))) {
                    dto.setBeginPromotionPrice(new BigDecimal(param.get("beginPromotionPrice").toString()));
                }
                if (!EmptyUtil.isEmpty(param.get("endPromotionPrice"))) {
                    dto.setEndPromotionPrice(new BigDecimal(param.get("endPromotionPrice").toString()));
                }
                if (!EmptyUtil.isEmpty(param.get("status"))) {
                    dto.setStatus(Integer.valueOf(param.get("status").toString()));
                }
                if (!EmptyUtil.isEmpty(param.get("saleWay"))) {
                    dto.setSaleWay(Integer.valueOf(param.get("saleWay").toString()));
                }
            }
            String str = req.getHeader("platformId");
            if (EmptyUtil.isNotEmpty(str)) {
                dto.setPlatformId(Long.valueOf(str));
            }
            PageResult<MerchantProductDTO> rt = merchantProductManage.findCommodityDetailsOfPage(dto, page);
            if (!EmptyUtil.isEmpty(rt)) {
                result.setList(MerchantProductConverter.toVO(rt.getList()));
                result.setPageNo(rt.getPageNo());
                result.setPageSize(rt.getPageSize());
                result.setTotalSize(rt.getTotalSize());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("查询商品草稿详情异常,请稍后在试!");
        }
        return JsonResult.success(result);
    }

    /**
     * 根据suId查询puName集合
     * @param suId
     * @return
     */
    @RequestMapping("/findPuNameBySuIdBackStage")
    @ResponseBody
    public JsonResult<List<Map<String,Object>>> findPuNameBySuIdBackStage(Long suId){
        logger.info("[后台生成pu二维码接口]根据suId查询puName集合");
        logger.info("[后台生成pu二维码接口]接收参数:" + suId);
        if(EmptyUtil.isEmpty(suId)){
            return JsonResult.fail("suId不能为空");
        }
        return merchantProductManage.findPuNameBySuIdBackStage(suId);
    }
}
	