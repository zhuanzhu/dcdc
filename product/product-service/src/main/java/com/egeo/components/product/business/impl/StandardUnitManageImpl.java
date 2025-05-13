package com.egeo.components.product.business.impl;


import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.facade.*;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.StringUtil;
import com.egeo.utils.StringUtils;
import lombok.val;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.CacheKeyConstant;
import com.egeo.components.product.bean.ProductAttrBean;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.business.StandardUnitManage;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.facade.CommodityProductUnitFacade;
import com.egeo.components.product.facade.PictureFacade;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.product.facade.StandardUnitFacade;
import com.egeo.components.product.facade.UserFacade;
import com.egeo.components.product.vo.StandardUnitExportVO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Service("standardUnit")
public class StandardUnitManageImpl implements StandardUnitManage {

    public Logger logger = Logger.getLogger(StandardUnitManageImpl.class);

    @Resource(name = "standardUnitFacade")
    private StandardUnitFacade standardUnitFacade;

    @Resource(name = "commodityProductUnitFacade")
    private CommodityProductUnitFacade commodityProductUnitFacade;

    @Resource
    private JedisUtil cache;

    @Resource(name = "pictureFacade")
    private PictureFacade pictureFacade;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "skuFacade")
    private SkuFacade skuFacade;

    @Resource(name="jedisUtil")
    private JedisUtil jedisUtil;


	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;

    @Resource(name="cakeProductManage")
	private CakeProductManage cakeProductManage;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private ChannelProductFacade channelProductFacade;

    @Override
    public StandardUnitDTO findStandardUnitById(StandardUnitDTO dto) {
        return standardUnitFacade.findStandardUnitById(dto);
    }

    @Override
    public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitDTO dto, Pagination page) {
        return standardUnitFacade.findStandardUnitOfPage(dto, page);
    }

    @Override
    public List<StandardUnitDTO> findStandardUnitAll(StandardUnitDTO dto) {
        return standardUnitFacade.findStandardUnitAll(dto);
    }

    @Override
    public Long insertStandardUnitWithTx(StandardUnitDTO dto) {
        return standardUnitFacade.insertStandardUnitWithTx(dto);
    }

    @Override
    public int updateStandardUnitWithTx(StandardUnitDTO dto) {
        return standardUnitFacade.updateStandardUnitWithTx(dto);
    }

    @Override
    public int deleteStandardUnitWithTx(StandardUnitDTO dto) {
        return standardUnitFacade.deleteStandardUnitWithTx(dto);
    }

    /**
     * su上下架
     *

     * @return
     */
    @Override
    public int putawaySoldOut(StandardUnitDTO dto, int type) {

        return standardUnitFacade.putawaySoldOut(dto, type);
    }

    /**
     * 分页显示所有在线su的库存信息
     *

     * @return
     */
    @Override
    public PageResult<Map<String, Object>> findStandardUnitOfPageStock(StandardUnitDTO dto, Pagination page) {
        PageResult<StandardUnitDTO> result = standardUnitFacade.findStandardUnitOfPage(dto, page);
        List<StandardUnitDTO> standardUnitList = result.getList();
        PageResult<Map<String, Object>> pageResult = new PageResult<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (StandardUnitDTO standardUnitDTO : standardUnitList) {
            //su在线库存
            Long realStockNum = 0L;
            //su在线冻结库存
            Long realFrozenStockNum = 0L;
            //根据suid查询su库存信息
            List<CommodityProductUnitWarehouseStockDTO> commodityProductUnitWarehouseStockList = standardUnitFacade.standardProductStock(standardUnitDTO);
            for (CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO : commodityProductUnitWarehouseStockList) {
                realStockNum = realStockNum + commodityProductUnitWarehouseStockDTO.getRealStockNum();
                realFrozenStockNum = realFrozenStockNum + commodityProductUnitWarehouseStockDTO.getRealFrozenStockNum();
            }
            //spu真实库存
            Long spuRealStockNum = 0L;
            //根据spuid查询库存信息
            List<MerchantProductWarehouseStockDTO> merchantProductWarehouseStockList = standardUnitFacade.standardProductStock(standardUnitDTO.getStandardProductUnitId());
            for (MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO : merchantProductWarehouseStockList) {
                spuRealStockNum = spuRealStockNum + merchantProductWarehouseStockDTO.getRealStockNum();
            }

            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitId", standardUnitDTO.getId());
            map.put("standardProductUnitId", standardUnitDTO.getStandardProductUnitId());
            map.put("standardUnitSerialNumber", standardUnitDTO.getMerchantProductSerialNumber());
            map.put("name", standardUnitDTO.getName());
            map.put("realStockNum", realStockNum);
            map.put("realFrozenStockNum", realFrozenStockNum);
            map.put("spuRealStockNum", spuRealStockNum);
            list.add(map);
        }
        pageResult.setList(list);
        pageResult.setPageNo(result.getPageNo());
        pageResult.setPageSize(result.getPageSize());
        pageResult.setTotalSize(result.getTotalSize());
        return pageResult;
    }

    /**
     * 根据suid查询在线su的库存信息
     *

     * @return
     */
    @Override
    public Map<String, Object> findStandardUnitStockById(StandardUnitDTO dto) {
        Map<String, Object> map = new HashMap<>();
        StandardUnitDTO standardUnitDTO = standardUnitFacade.findStandardUnitById(dto);
        map.put("standardUnitSerialNumber", standardUnitDTO.getMerchantProductSerialNumber());
        map.put("name", standardUnitDTO.getName());
        //根据suid查询pu信息
        CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
        commodityProductUnitDTO.setStandardUnitId(dto.getId());
        List<CommodityProductUnitDTO> commodityProductUnitList = commodityProductUnitFacade.findCommodityProductUnitAll(commodityProductUnitDTO);
        //根据suid查询su库存信息
        List<CommodityProductUnitWarehouseStockDTO> commodityProductUnitWarehouseStockList = standardUnitFacade.standardProductStock(standardUnitDTO);

        //根据spuid查询库存信息
        List<MerchantProductWarehouseStockDTO> merchantProductWarehouseStockList = standardUnitFacade.standardProductStock(standardUnitDTO.getStandardProductUnitId());
        Map<String, Object> map2 = new HashMap<>();
        for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
            map2.put("name", commodityProductUnitDTO2.getName());
            for (CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO : commodityProductUnitWarehouseStockList) {
                if (commodityProductUnitDTO2.getId().equals(commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId())) {
                    //pu在线库存
                    map2.put("realStockNum", commodityProductUnitWarehouseStockDTO.getRealStockNum());
                    //真实冻结库存
                    map2.put("realFrozenStockNum", commodityProductUnitWarehouseStockDTO.getRealFrozenStockNum());
                    break;
                }
            }
            for (MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO : merchantProductWarehouseStockList) {
                if (commodityProductUnitDTO2.getSkuId().equals(merchantProductWarehouseStockDTO.getSkuId())) {
                    //sku实体库存
                    map2.put("skuRealStockNum", merchantProductWarehouseStockDTO.getRealStockNum());
                    break;
                }

            }
            //根据skuid查询sku属性和属性值集合
            List<Map<String, Object>> attNameValueList = standardUnitFacade.findAttNameValue(commodityProductUnitDTO2.getSkuId());
            map2.put("attNameValueList", attNameValueList);
        }
        map.put("PUList", map2);

        return map;
    }

    /**
     * app商品列表
     *

     * @param page

     * @return
     */
    @Override
    public PageResult<Map<String, Object>> findStandardUnitOfPageAPP(StandardUnitDTO dto, Pagination page) {
        List<Map<String, Object>> standardUnitList = new ArrayList<>();
        dto.setIsVisible(0);
        PageResult<StandardUnitDTO> result = standardUnitFacade.findStandardUnitOfPageAPP(dto, page);
        List<StandardUnitDTO> standardUnitDTOList = result.getList();
        for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitId", standardUnitDTO.getId());
            map.put("name", standardUnitDTO.getName());
            map.put("pictureUrl", standardUnitDTO.getPictureUrl());
            map.put("salePrice", standardUnitDTO.getSalePrice());
            map.put("marketPrice", standardUnitDTO.getMarketPrice());
            standardUnitList.add(map);
        }
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setList(standardUnitList);
        pageResult.setPageNo(result.getPageNo());
        pageResult.setPageSize(result.getPageSize());
        pageResult.setTotalSize(result.getTotalSize());
        return pageResult;
    }

    /**
     * 根据类目节点id查询su商品信息
     *

     * @return
     */
    @Override
    public PageResult<Map<String, Object>> standardUnitStockByCategoryTreeNodeId(StandardUnitDTO dto, Pagination page) {
        String categoryTreeNodeId = cache.get(CacheKeyConstant.MODULE_CATEGORY_TREE_NODE_ID).toString();

        List<Map<String, Object>> standardUnitList = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(categoryTreeNodeId)) {
            dto.setCategoryTreeNodeId(Long.valueOf(categoryTreeNodeId));
        } else {
            throw new BusinessException("类目节点id未输入");
        }
        dto.setIsVendible(0);
        PageResult<StandardUnitDTO> result = standardUnitFacade.standardUnitStockByCategoryTreeNodeId(dto, page);
        List<StandardUnitDTO> standardUnitDTOList = result.getList();
        for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitId", standardUnitDTO.getId());
            map.put("name", standardUnitDTO.getName());
            map.put("pictureUrl", standardUnitDTO.getPictureUrl());
            map.put("salePrice", standardUnitDTO.getSalePrice());
            map.put("marketPrice", standardUnitDTO.getMarketPrice());
            standardUnitList.add(map);
        }
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setList(standardUnitList);
        pageResult.setPageNo(result.getPageNo());
        pageResult.setPageSize(result.getPageSize());
        pageResult.setTotalSize(result.getTotalSize());
        return pageResult;
    }

    /**
     * 根据suid查询su基本信息和销售量
     *
     * @param standardUnitId
     * @param clientId       客户端id 1、app 2、微信端 3、web端
     * @return
     */
    @Override
    public Map<String, Object> standardUnitByStandardUnitId(Long standardUnitId, Long companyId, Long platformId, Long clientId, Long storeId, Long userId,Integer source,String channelProductId) {


    	StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
        standardUnitDTO.setId(standardUnitId);
        standardUnitDTO.setSource(source);
        Map<String, Object> map = new HashMap<>();
        if(standardUnitDTO.isJd()) {
        	JdProductDTO dto = new JdProductDTO();
        	dto.setId(standardUnitId);
        	JdProductDTO  jdProduct = jdProductManage.findJdProductById(dto);
        	map.put("buyType",1);
        	map.put("standardUnitId", standardUnitId);
        	map.put("standardUnitName", jdProduct.getName());
        	map.put("status", jdProduct.standardUnitState());
        	// 设置su商品销售价格
        	map.put("salePrice", jdProduct.getPrice());
        	map.put("marketPrice", jdProduct.getMarketPrice());

        	map.put("freightExplain","以确认订单页面计算的实际运费为准");
        	map.put("shipmentsExplain", "以商品运营方的实际发货时间为准");
        	map.put("content", jdProduct.getIntroduction().replaceAll("width:750px", "width:100%").replaceAll(".ssd-module-wrap\\{position:relative;margin:0 auto;width:100%", ".ssd-module-wrap\\{position:relative;margin:0 auto;width:100%;height:100%"));
        	map.put("salesVolume", standardUnitId%100);
        	map.put("isOwnMerchant", 0);
        	map.put("contentGoodsJD", jdProduct.getWxContent());

        	map.put("commodityTemplateId", 2);
        	map.put("storeId", 1);
        	map.put("activityCode", "大厨管家总店");
        	map.put("presellPeriod", null);
        	map.put("relevanceSuId", null);
        	map.put("saleWay", 1);
    		//设置运营方名称
        	map.put("merchantName", "电商特供品");
        	map.put("standardUnitMembersExplain", "");

        	Map<String, Object> attmap = new HashMap<>();
			List<Map<String, Object>> attmapList = new ArrayList<>();
			/*Map paramMap = JSON.parseObject(jdProduct.getParam(), Map.class);
			for(Object key : paramMap.keySet()) {
				attmap.put("attName",key);
				attmap.put("attValue",paramMap.get(key));
			}*/
			if(jdProduct.getParam()!=null && jdProduct.getParam().length()>15) {
				JSONArray groupObjs = JSON.parseArray(jdProduct.getParam());
				List<ProductAttrBean> pa = new ArrayList<ProductAttrBean>();
				if(groupObjs!=null && groupObjs.size()>0) {
					for(int index=0;index<groupObjs.size();index++) {
						JSONObject groupObj = groupObjs.getJSONObject(index);
						if(groupObj==null) {
							continue;
						}
						ProductAttrBean productAttrDTO = new ProductAttrBean();
						pa.add(productAttrDTO);
						List<HashMap<String,Object>> atts = new ArrayList<HashMap<String,Object>>();
						String groupName = groupObj.getString("groupName");
						String attsStr = groupObj.getString("atts");
						productAttrDTO.setAtts(atts);
						productAttrDTO.setGroupName(groupName);
						JSONArray attArray = JSON.parseArray(attsStr);
						for(int attIndex=0;attIndex<attArray.size();attIndex++) {
							JSONObject attObj = attArray.getJSONObject(attIndex);
							String attName = attObj.getString("attName");
							List attValTmps = attObj.getObject("vals", List.class);
							List<String> attVals = new ArrayList<String>();
							for(Object val : attValTmps) {
								attVals.add(val.toString());
							}
							HashMap<String,Object> att = new HashMap<>();
							att.put("attValue", String.join(";", attVals));
							att.put("attName", attName);
							atts.add(att);
						}
					}
				}
				map.put("attList", JSON.toJSONString(pa));
			}else {

				attmapList.add(attmap);
				//map.put("attList", attmapList);
			}

			String imagePath = jdProduct.getImagePath();
            JSONObject object = JSONObject.parseObject(imagePath);
            String assisImg = object.getString("assisImg");
            String primaryImg = object.getString("primaryImg");
            List<String> strings = JSONObject.parseArray(assisImg, String.class);
            strings.add(0, primaryImg);
            map.put("pictureList", strings);
            map.put("picture", primaryImg);


        }else if(standardUnitDTO.isCake()){
            CakeProductDetailSearchDTO search= new CakeProductDetailSearchDTO();
            search.setProduct_id(channelProductId);
            if(StringUtil.isEmpty(channelProductId)){
                search.setProduct_id(String.valueOf(standardUnitId));
            }
            JsonResult<CakeProductDetailDTO> searchResult =  cakeProductManage.searchProductCalcPriceDetail(search);
            if(Objects.isNull(searchResult) || Objects.isNull(searchResult.getData())){
                throw new BusinessException("未找到对应的商品信息");
            }
            CakeProductDetailDTO  cakeProductDetailDTO = searchResult.getData();
            CakeProductDetailProductsDTO cakeProductDetailProductsDTO = cakeProductDetailDTO.getProduct();
            List<CakeProductDetailSpecsDTO> specs = cakeProductDetailDTO.getSpecs();
            map.put("merchantId", 7);
            map.put("buyType",1);
            map.put("channelProductId", channelProductId);
            map.put("standardUnitId", standardUnitId);
            map.put("standardUnitName", cakeProductDetailProductsDTO.getTitle());
            map.put("status", (cakeProductDetailProductsDTO.getStatus()!=null && Integer.valueOf(cakeProductDetailProductsDTO.getStatus()).intValue()==1)?3:4);
            // 设置su商品销售价格
            map.put("salePrice", EmptyUtil.isNotEmpty(cakeProductDetailProductsDTO.getPrice())?new BigDecimal(cakeProductDetailProductsDTO.getPrice()):null);
            map.put("marketPrice", EmptyUtil.isNotEmpty(cakeProductDetailProductsDTO.getMarket_price())?new BigDecimal(cakeProductDetailProductsDTO.getMarket_price()):null);

            map.put("freightExplain","以确认订单页面计算的实际运费为准");
            map.put("shipmentsExplain", "以商品运营方的实际发货时间为准");
            map.put("content", cakeProductDetailDTO.getContent_images());
            map.put("salesVolume", standardUnitId%100);
            map.put("isOwnMerchant", 0);

            map.put("commodityTemplateId", 2);
            map.put("storeId", 1);
            map.put("activityCode", "大厨管家总店");
            map.put("presellPeriod", null);
            map.put("relevanceSuId", null);
            map.put("saleWay", 1);
            //设置运营方名称
            map.put("merchantName", "蛋糕叔叔");
            map.put("standardUnitMembersExplain", "");
            //轮播图
            map.put("pictureList", cakeProductDetailDTO.getImages());
            map.put("picture", cakeProductDetailProductsDTO.getImage_path());


            List<HashMap<String,String>> atts = new ArrayList<HashMap<String,String>>();
            if(Objects.nonNull(cakeProductDetailDTO.getBrand()) && StringUtils.isNotEmpty(cakeProductDetailDTO.getBrand().getName())){
                HashMap<String,String> attBrand = new HashMap<>();
                attBrand.put("attValue", cakeProductDetailDTO.getBrand().getName());
                attBrand.put("attName", "品牌");
                atts.add(attBrand);
            }
          /*  if(StringUtils.isNotEmpty(cakeProductDetailProductsDTO.getLabel_name())){
                HashMap<String,String> attLabel = new HashMap<>();
                attLabel.put("attValue", cakeProductDetailProductsDTO.getLabel_name());
                attLabel.put("attName", "标签");
                atts.add(attLabel);
            }*/
            CakeProducTextraDataDTO textraDataDTO = cakeProductDetailProductsDTO.getExtra_data();
            if(null !=textraDataDTO){
                if(StringUtils.isNotEmpty(textraDataDTO.getDistrubtion())){
                    HashMap<String,String> attDistrubtion = new HashMap<>();
                    attDistrubtion.put("attValue", textraDataDTO.getDistrubtion());
                    attDistrubtion.put("attName", "配送规则");
                    atts.add(attDistrubtion);
                }
                if(StringUtils.isNotEmpty(textraDataDTO.getMaterial())){
                    HashMap<String,String> attMaterial = new HashMap<>();
                    attMaterial.put("attValue", textraDataDTO.getMaterial());
                    attMaterial.put("attName", "商品材料");
                    atts.add(attMaterial);
                }
            }

            map.put("attList", atts);
            //setCakeSuPrice(specs,map,standardUnitId);
        }else if(standardUnitDTO.isWorldBuy()){
            ChannelProductDetailRequestVO detailRequestVO = new ChannelProductDetailRequestVO();
            detailRequestVO.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
            detailRequestVO.setProductId(channelProductId);
            detailRequestVO.setSkuId(String.valueOf(standardUnitId));
            ChannelProductDetailVO channelProductDetailVO = channelProductFacade.getChannelProductDetail(detailRequestVO);
            if(channelProductDetailVO == null){
                throw new BusinessException("商品不存在");
            }
            ChannelProductDTO channelProductDTO =  channelProductDetailVO.getChannelProductDTO();
            if(channelProductDTO == null){
                throw new BusinessException("商品明细不存在");
            }
            List<ChannelProductBatchDTO> batchDTOList =  channelProductDetailVO.getBatchDTOList();
            ChannelProductDescriptionDTO descriptionDTO = channelProductDetailVO.getDescriptionDTO();
            ChannelProductBatchDTO currBatch=  channelProductFacade.getCurrBatch(detailRequestVO,batchDTOList);
            List<String> pictureList = channelProductDetailVO.getPictureList();
            map.put("merchantId", 8);
            map.put("buyType",1);
            map.put("channelProductId", channelProductId);
            map.put("standardUnitId", standardUnitId);
            map.put("standardUnitName", channelProductDTO.getName());
            map.put("status", (channelProductDTO.getStatus()!=null && Integer.valueOf(channelProductDTO.getStatus()).intValue()==1)?3:4);
            // 设置su商品销售价格
            map.put("salePrice", (currBatch !=null && currBatch.getPrice()!=null)?currBatch.getPrice():channelProductDTO.getPrice());
            map.put("marketPrice", (currBatch !=null && currBatch.getPriceMarket()!=null) ?currBatch.getPriceMarket():channelProductDTO.getMarketPrice());

            map.put("freightExplain","以确认订单页面计算的实际运费为准");
            map.put("shipmentsExplain", "以商品运营方的实际发货时间为准");
            map.put("content", descriptionDTO !=null?descriptionDTO.getContent():channelProductDTO.getTitle());
            map.put("salesVolume", standardUnitId%100);
            map.put("isOwnMerchant", 0);

            map.put("commodityTemplateId", 2);
            map.put("storeId", 1);
            map.put("activityCode", "大厨管家总店");
            map.put("presellPeriod", null);
            map.put("relevanceSuId", null);
            map.put("saleWay", 1);
            //设置运营方名称
            map.put("merchantName", "海外专卖品");
            map.put("standardUnitMembersExplain", "");
            //轮播图
            map.put("pictureList", pictureList);
            map.put("picture", channelProductDetailVO.getProductImg());

            List<HashMap<String,String>> atts = new ArrayList<HashMap<String,String>>();
            if(EmptyUtil.isNotEmpty(channelProductDTO.getCountryName())){
                HashMap<String,String> attBrand = new HashMap<>();
                attBrand.put("attValue", channelProductDTO.getCountryName());
                attBrand.put("attName", "产地");
                atts.add(attBrand);
            }
            if(EmptyUtil.isNotEmpty(channelProductDTO.getBrandName())){
                HashMap<String,String> attBrand = new HashMap<>();
                attBrand.put("attValue", channelProductDTO.getBrandName());
                attBrand.put("attName", "品牌");
                atts.add(attBrand);
            }
            if(channelProductDTO.getGoodsType() !=null){
                String goodsTypeName = "其他";
                if(channelProductDTO.getGoodsType().intValue()==0){
                    goodsTypeName="一般贸易";
                }else if(channelProductDTO.getGoodsType().intValue()==1){
                    goodsTypeName="保税";
                }else if(channelProductDTO.getGoodsType().intValue()==2){
                    goodsTypeName="海外直邮";
                }
                HashMap<String,String> attBrand = new HashMap<>();
                attBrand.put("attValue", goodsTypeName);
                attBrand.put("attName", "商品等级");
                atts.add(attBrand);
            }
            if(EmptyUtil.isNotEmpty(channelProductDTO.getGoodsLevel())){
                HashMap<String,String> attBrand = new HashMap<>();
                attBrand.put("attValue", channelProductDTO.getGoodsLevel());
                attBrand.put("attName", "商品等级");
                atts.add(attBrand);
            }
            if(EmptyUtil.isNotEmpty(channelProductDTO.getUnitName())){
                HashMap<String,String> attBrand = new HashMap<>();
                attBrand.put("attValue", channelProductDTO.getUnitName());
                attBrand.put("attName", "计量单位");
                atts.add(attBrand);
            }
            map.put("attList", atts);
        }else{
            StandardUnitDTO standardUnitById = findStandardUnitById(standardUnitDTO);
            if(EmptyUtil.isEmpty(standardUnitById)){
                throw new BusinessException("su商品不存在");
            }
            SkuDTO skuDTO = new SkuDTO();
            skuDTO.setStandardProductUnitId(standardUnitById.getStandardProductUnitId());
            List<SkuDTO> skuAll = skuFacade.findSkuAll(skuDTO);
            if(EmptyUtil.isEmpty(skuAll)){
                throw new BusinessException("su对应的sku不存在");
            }



            Integer companyType = null;
        	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
        		companyType = userFacade.findCompanyTypeById(companyId);
        	}else {
        		companyType = RuntimeContext.cacheUser().getCompanyType();
        	}

            // 公司类型 0:正式公司 1:测试公司 2:竞品公司
             map = findByStandardUnitId(skuAll.get(0),standardUnitId, companyId, platformId, companyType, storeId, userId);

            // 类型：1、列表图片 2、app轮播图片 3、web轮播图
            List<String> pictureList = pictureFacade.findByStandardUnitIdAndType(standardUnitId, 1);
            if (pictureList.size() != 1)
                throw new BusinessException("商品编号：" + standardUnitId + "封面图异常");
            if (clientId.equals(1L) || clientId.equals(2L)) {
                map.put("pictureList", pictureFacade.findByStandardUnitIdAndType(standardUnitId, 2));
            }
            if (clientId.equals(3L)) {
                map.put("pictureList", pictureFacade.findByStandardUnitIdAndType(standardUnitId, 3));
            }
            //京东商品单独提供图片
            //根据su查询当前商品是否为京东商品,京东商品单独获取轮播图

            if(standardUnitById.getMerchantId().equals(6L)){

                String externalSkuId = skuAll.get(0).getExternalSkuId();
                if(EmptyUtil.isEmpty(externalSkuId)){
                    throw new BusinessException("京东商品缺失京东商品id");
                }
                JdProductDTO jdProductById = skuFacade.findJdProductById(externalSkuId);
                if(EmptyUtil.isEmpty(jdProductById)){
                    throw new BusinessException("京东商品不存在");
                }
                String imagePath = jdProductById.getImagePath();
                JSONObject object = JSONObject.parseObject(imagePath);
                String assisImg = object.getString("assisImg");
                List<String> strings = JSONObject.parseArray(assisImg, String.class);
                map.put("pictureList", strings);
            }

            map.put("merchantId", standardUnitById.getMerchantId());
            map.put("picture", pictureList.get(0));

        }
        return map;
    }



    private void setCakeSuPrice(List<CakeProductDetailSpecsDTO> specs, Map<String, Object> map, Long standardUnitId) {
        if(CollectionUtils.isEmpty(specs)){
            return;
        }
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(ProductChannelCodeEnum.CAKE.getCode(), RuntimeContext.cacheUser().getEnterpriseId(),false);
       List<StandardUnitCondition> tempList = new ArrayList<>();
        for (CakeProductDetailSpecsDTO spec : specs) {
            Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(spec.getMarket_price(),spec.getClearing_price(),channelEnterpriseConfigs,null);
            StandardUnitCondition tmp = new StandardUnitCondition();
            tmp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)).setScale(2));
            tmp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)).setScale(2));
            tempList.add(tmp);
        }
        if(!CollectionUtils.isEmpty(tempList)){
            Optional<StandardUnitCondition> minSalePriceCondition = findMinSalePrice(tempList);
            minSalePriceCondition.ifPresent(condition -> {
                map.put("salePrice", condition.getSalePrice());
                map.put("marketPrice", condition.getMarketPrice());
            });

        }
    }

    // 使用Stream API找到具有最小salePrice的StandardUnitCondition对象
    public static Optional<StandardUnitCondition> findMinSalePrice(List<StandardUnitCondition> conditions) {
        return conditions.stream()
                .min(Comparator.comparing(StandardUnitCondition::getSalePrice));
    }

    /**
     * 根据suid查询su详情
     *
     *
     * @param skuDTO
     * @param standardUnitId
     * @return
     */
    private Map<String, Object> findByStandardUnitId(SkuDTO skuDTO, Long standardUnitId, Long companyId, Long platformId, Integer companyType, Long storeId, Long userId) {
        // TODO Auto-generated method stub
        return standardUnitFacade.findByStandardUnitId(skuDTO,standardUnitId, companyId, platformId, companyType, storeId, userId);
    }

    /**
     * 根据suid查询su销售量
     *
     * @param standardUnitId
     * @return
     */
    private Long findSalesRecordByStandardUnitId(Long standardUnitId) {
        // TODO Auto-generated method stub
        return standardUnitFacade.findSalesRecordByStandardUnitId(standardUnitId);
    }

    /**
     * 根据spuid查询su轮播图信息
     *

     * @return
     */
    private List<String> findPicturesByStandardUnitId(Long standardUnitId) {
        // TODO Auto-generated method stub
        return pictureFacade.findByStandardUnitId(standardUnitId);
    }

    /**
     * 根据suid查询su封面图信息
     *

     * @return
     */
    private String findPictureByStandardUnitId(Long standardUnitId) {
        // TODO Auto-generated method stub
        return pictureFacade.findPictureByStandardUnitId(standardUnitId);
    }

    /**
     * 根据条件查询所有上架suid和名称
     *

     * @return
     */
    @Override
    public List<Map<String, Object>> findStandardUnitIdAndName(StandardUnitDTO dto) {
        List<Map<String, Object>> maps = new ArrayList<>();
        List<StandardUnitDTO> list = standardUnitFacade.findStandardUnitIdAndName(dto);
        for (StandardUnitDTO standardUnitDTO : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitId", standardUnitDTO.getId());
            map.put("standardUnitName", standardUnitDTO.getName());
            maps.add(map);
        }
        return maps;
    }

    /**
     * 根据功能模版id查询su商品信息
     *

     * @return
     */
    @Override
    public PageResult<Map<String, Object>> standardUnitByFunctionModuleId(StandardUnitDTO dto, Long functionModuleId,
                                                                          Pagination page) {
        List<Map<String, Object>> standardUnitList = new ArrayList<>();
        PageResult<StandardUnitDTO> result = standardUnitFacade.standardUnitByFunctionModuleId(dto, functionModuleId, page);
        List<StandardUnitDTO> standardUnitDTOList = result.getList();
        for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitId", standardUnitDTO.getId());
            map.put("name", standardUnitDTO.getName());
            map.put("pictureUrl", standardUnitDTO.getPictureUrl());
            map.put("salePrice", standardUnitDTO.getSalePrice());
            map.put("marketPrice", standardUnitDTO.getMarketPrice());
            standardUnitList.add(map);
        }
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setList(standardUnitList);
        pageResult.setPageNo(result.getPageNo());
        pageResult.setPageSize(result.getPageSize());
        pageResult.setTotalSize(result.getTotalSize());
        return pageResult;
    }

    /**
     * 根据类目节点id或商品组合id查询su商品列表
     *

     * @param saleWay
     * @param buyType
     * @return
     */
    @Override
    public PageResult<Map<String, Object>> findByCategoryTreeNodeIdOrSUCombination(Integer saleWay, Long categoryTreeNodeId,
                                                                                   Long standardUnitCombinationId, Integer type, Integer fubiPay, Long userId, Long clientId,Long enterrprisetId, Long companyId, Long platformId,
                                                                                   Long couponUnitId, Pagination page, Long storeId, Integer buyType,Integer sellState,String keyWord) {
        // 公司类型 0:正式公司 1:测试公司 2:竞品公司
    	Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = userFacade.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}

        return standardUnitFacade.findByCategoryTreeNodeIdOrSUCombination(saleWay,categoryTreeNodeId, standardUnitCombinationId, type,
                fubiPay, userId, clientId,enterrprisetId, companyId, platformId, couponUnitId, companyType, page, storeId, buyType,sellState,keyWord);
    }

    /**
     * su商品组合选择商品_su商品列表
     *
     * @return
     */
    @Override
    public PageResult<Map<String, Object>> findBaseByConditionOfPage(Long standardUnitCombinationId, StandardUnitDTO standardUnitDTO, Pagination page) {
        List<Map<String, Object>> standardUnitList = new ArrayList<>();
        PageResult<StandardUnitDTO> result = standardUnitFacade.findBaseByConditionOfPage(standardUnitCombinationId, standardUnitDTO, page);
        List<StandardUnitDTO> standardUnitDTOList = result.getList();
        for (StandardUnitDTO standardUnit : standardUnitDTOList) {
            Map<String, Object> map = new HashMap<>();
            map.put("standardUnitId", standardUnit.getId());
            map.put("supplierId", standardUnit.getSupplierId());
            map.put("merchantCateTreeNodeId", standardUnit.getMerchantCateTreeNodeId());
            map.put("productCategory", standardUnit.getProductCategory());
            map.put("name", standardUnit.getName());
            map.put("salePrice", standardUnit.getSalePrice());
            map.put("status", standardUnit.getStatus());
            map.put("saleWay", standardUnit.getSaleWay());
            map.put("merchantId", standardUnit.getMerchantId());
            standardUnitList.add(map);
        }
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setList(standardUnitList);
        pageResult.setPageNo(result.getPageNo());
        pageResult.setPageSize(result.getPageSize());
        pageResult.setTotalSize(result.getTotalSize());
        return pageResult;
    }

    /**
     * 根据su商品名称查询所有su商品
     *
     *
     * @param platformId
     * @param standardUnitName
     * @return
     */
    @Override
    public List<Map<String, Object>> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName) {
        // TODO Auto-generated method stub
        return  standardUnitFacade.findByStandardUnitName(platformId,standardUnitId, standardUnitName);
    }

    /**
     * 根据suid查询su真实数据
     *
     * @param standardUnitId
     * @return
     */
    @Override
    public StandardUnitExportVO queryByStandardUnitId(Long standardUnitId) {
        // TODO Auto-generated method stub
        return standardUnitFacade.queryByStandardUnitId(standardUnitId);
    }

    /**
     * 限购规则分页显示已上架su商品列表
     *
     * @param dto
     * @param page
     * @return
     */
    @Override
    public PageResult<Map<String, Object>> findStandardUnitMapOfPage(StandardUnitDTO dto, Pagination page, List<Long> standardUnitIdList) {
        PageResult<StandardUnitDTO> pageResult = standardUnitFacade.findStandardUnitExtendOfPage(dto, page, standardUnitIdList);
        List<StandardUnitDTO> standardUnitList = pageResult.getList();
        List<Map<String, Object>> maps = new ArrayList<>();
        for (StandardUnitDTO standardUnitDTO : standardUnitList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", standardUnitDTO.getId());
            map.put("serialNumber", standardUnitDTO.getMerchantProductSerialNumber());
            map.put("name", standardUnitDTO.getName());
            map.put("salesVolume", standardUnitDTO.getSalesVolume());
            map.put("salePrice", standardUnitDTO.getSalePrice());
            map.put("marketPrice", standardUnitDTO.getMarketPrice());
            map.put("promotionPrice", standardUnitDTO.getPromotionPrice());
            map.put("soldBase", standardUnitDTO.getSoldBase());
            map.put("saleWay", standardUnitDTO.getSaleWay());
            map.put("merchantName", standardUnitDTO.getMerchantName());
            map.put("createTime", standardUnitDTO.getCreateTime());
            maps.add(map);
        }
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(maps);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public PageResult<Map<String, Object>> queryStandardUnitListByBlurry(StandardUnitDTO dto, Long excludeId, Pagination page) {
        PageResult<StandardUnitDTO> rt = standardUnitFacade.queryStandardUnitListByBlurry(dto, excludeId, page);
        List<Map<String, Object>> list = new ArrayList<>();
        for (StandardUnitDTO standardUnitDTO : rt.getList()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", standardUnitDTO.getId());
            map.put("serialNumber", standardUnitDTO.getMerchantProductSerialNumber());
            map.put("name", standardUnitDTO.getName());
            map.put("salePrice", standardUnitDTO.getSalePrice());
            map.put("saleWay", standardUnitDTO.getSaleWay());
            map.put("status", standardUnitDTO.getStatus());
            map.put("createTime", standardUnitDTO.getCreateTime() != null ? standardUnitDTO.getCreateTime().getTime() : null);
            list.add(map);
        }

        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
        return result;
    }

    @Override
    public PageResult<Map<String, Object>> findByKeywordOfPage(Integer saleWay, Long storeId, String name, Long userId, Integer fubiPay, Long clientId, Long companyId, Long platformId, Pagination page, Integer buyType) {
        // 公司类型 0:正式公司 1:测试公司 2:竞品公司
        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = userFacade.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
        if (buyType == 0) {
            buyType = null;
        }
        return standardUnitFacade.findByKeywordOfPage(saleWay,storeId,name, userId, fubiPay, clientId, companyId, platformId, companyType, page, buyType);
    }

    /**
     * 刷新su商品搜索规则数据信息
     */
    @Override
    public JsonResult<Map<String, Object>> syncSaveSuSerachRule() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("更新商品搜索表");
                    standardUnitFacade.syncSaveSuSerachRule();
                } catch (Exception e) {
                    logger.info("刷新su商品搜索规则数据信息失败:" + e.getMessage());
                    throw new BusinessException(e.getMessage() + "刷新su商品搜索规则数据信息失败");
                }
            }
        }).start();

        return JsonResult.success("succeed", "刷新su商品搜索规则数据信息成功,将在一分钟左右完成");
    }


    @Override
    public PageResult<Map<String, Object>> querySuByCategoryTreeNodeIds(Pagination page,
                                                   List<Long> categoryTreeNodeIds, Map<String, Object> param) {
        List<Map<String, Object>> standardUnitList = new ArrayList<>();
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        /*if (EmptyUtil.isNotEmpty(categoryTreeNodeIds)) {
            param.put("categoryTreeNodeIds",categoryTreeNodeIds);
        }*/
        PageResult<StandardUnitDTO> result = standardUnitFacade.querySuByCategoryTreeNodeIds(page, param, categoryTreeNodeIds);
        if (EmptyUtil.isNotEmpty(result)) {
            List<StandardUnitDTO> standardUnitDTOList = result.getList();
            for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
                Map<String, Object> map = new HashMap<>();
                map.put("standardUnitId", standardUnitDTO.getId());
                map.put("merchantProductSerialNumber", standardUnitDTO.getMerchantProductSerialNumber());
                map.put("name", standardUnitDTO.getName());
                map.put("salePrice", standardUnitDTO.getSalePrice());
                map.put("promotionPrice", standardUnitDTO.getPromotionPrice());
                map.put("marketPrice", standardUnitDTO.getMarketPrice());
                map.put("status", standardUnitDTO.getStatus());
                map.put("isVisible", standardUnitDTO.getIsVisible());
                standardUnitList.add(map);
            }
            pageResult.setList(standardUnitList);
            pageResult.setPageNo(result.getPageNo());
            pageResult.setPageSize(result.getPageSize());
            pageResult.setTotalSize(result.getTotalSize());
        }
        return pageResult;
    }

	@Override
	public Map<String, Object> findStandardUnitByStoreMenuIdOfPage(
			Long storeMenuNodeId,Long storeId,Long platformId, Pagination page) {
		Map<String, Object> data = new HashMap<String, Object>();
		PageResult<Map<String, Object>> result = standardUnitFacade.findStandardUnitByStoreMenuIdOfPage(storeMenuNodeId,storeId,platformId, page);
		data.put("result", result);
		return data;
	}

    @Override
    public CouponUnitDTO findCouponUnitByCouponUnitId(Long couponUnitId) {
        return standardUnitFacade.findCouponUnitByCouponUnitId(couponUnitId);
    }

    @Override
    public Integer findSuStatus(Long suId) {
        StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
        standardUnitDTO.setId(suId);
        StandardUnitDTO standardUnitById = standardUnitFacade.findStandardUnitById(standardUnitDTO);
        if(EmptyUtil.isEmpty(standardUnitById)){
            throw new BusinessException("su不存在");
        }
        return standardUnitById.getStatus();
    }

    @Override
    public Long findPuStock(Long puId,Long storeId) {

        Long stockNum;
        Long realStockNum =commodityProductUnitFacade.realStockNumByCommodityProductUnitId(puId);


        if (EmptyUtil.isEmpty(realStockNum)) {
            stockNum = 0L;
        }
        Long storeRealStockNum = 0L;
        if( storeId.equals(1L) || storeId.equals(2L)) {
            // 商城不查门店库存
            stockNum = realStockNum;
        } else {
            StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
            storeProductUnitDTO.setCommodityProductUnitId(puId);
            storeProductUnitDTO.setStoreId(storeId);
            List<StoreProductUnitDTO> storeProductUnitDTOList = standardUnitFacade.findStoreProductUnitAll(storeProductUnitDTO);
            if (EmptyUtil.isNotEmpty(storeProductUnitDTOList)) {
                // 查询门店库存
                storeRealStockNum = standardUnitFacade.realStockNumByStoreProductUnitId(storeProductUnitDTOList.get(0).getId(), storeId);
                if (EmptyUtil.isEmpty(storeRealStockNum)) {
                    storeRealStockNum = 0L;
                }
            }
            // 比较两个库存，取较小值
            stockNum=realStockNum < storeRealStockNum ? realStockNum : storeRealStockNum;
        }
        return stockNum;


    }

    @Override
    public void checkJdProductDetail(Long standardUnitId) {
        commodityProductUnitFacade.checkJdProductDetail(cache,standardUnitId);
    }

    @Override
    public int updateSuVisible(StandardUnitDTO standardUnitDTO) {
        return standardUnitFacade.updateSuVisible(standardUnitDTO);
    }

    @Override
    public int updateMerchantProductVisible(Long standardUnitId, Integer status) {
        return standardUnitFacade. updateMerchantProductVisible(standardUnitId,status);
    }


}
