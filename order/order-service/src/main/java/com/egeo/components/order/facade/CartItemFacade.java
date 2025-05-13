package com.egeo.components.order.facade;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.utils.NumberUtils;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.client.LimitRuleClient;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.StartLimitRuleByStandardUnitIdDTO;
import com.egeo.components.order.facade.utils.UserMembershipCheckUtils;
import com.egeo.components.order.service.read.CartItemReadService;
import com.egeo.components.order.service.read.CartReadService;
import com.egeo.components.order.service.read.LimitRuleReadService;
import com.egeo.components.order.service.read.LimitRuleRecordReadService;
import com.egeo.components.order.service.write.CartItemWriteService;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.JdProductClient;
import com.egeo.components.product.client.JdProductInnerIdClient;
import com.egeo.components.product.client.MembershipClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitStoreClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.vo.JdProductStatStockAndPriceReqVO;
import com.egeo.components.product.vo.JdProductVO2;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;


@Component
public class CartItemFacade {
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	JdFacade jdService;

	@Autowired
	ReceiverAddressFacade receiverAddressService;
	@Resource
	private CartItemReadService cartItemReadService;

	@Resource
	private CartItemWriteService cartItemWriteService;

	@Resource
	private CartReadService cartReadService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Autowired
	private JdUtils jdUtils;

	@Autowired
	private JdProductClient jdProductService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;

	@Autowired
	private StandardUnitClient standardUnitReadService;

	@Resource
	private LimitRuleReadService limitRuleReadService;

	@Resource
	private LimitRuleRecordReadService limitRuleRecordReadService;

	@Autowired
	private CompanyCoreClient companyCoreReadService;

	@Autowired
	private MembershipUserClient membershipUserReadService;

	@Autowired
	private MembershipClient membershipReadService;
	/*@Autowired
	private StandardUnitMembershipClient standardUnitMembershipReadService;
	@Autowired
	private MembershipAuthorityClient membershipAuthorityReadService;*/
	@Autowired
	private StoreClient storeReadService;
	@Autowired
	private StandardUnitStoreClient standardUnitStoreReadService;
	@Autowired
	private MerchantClient merchantReadService;
	@Autowired
	private CompanyClient companyReadService;

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Autowired
	private JdProductInnerIdClient jdProductInnerIdReadService;
	@Value("${order.cart.stock:true}")
	private boolean cartStock;
	@Autowired
	private JdProductClient jdProductReadService;

	public CartItemDTO findCartItemById(Long id){
		CartItemDTO condition=new CartItemDTO();
		condition.setId(id);
		return cartItemReadService.findCartItemById(condition);
	}

	public PageResult<CartItemDTO> findCartItemOfPage(CartItemDTO dto,Pagination page){

		return cartItemReadService.findCartItemOfPage(dto, page);

	}

	public List<CartItemDTO> findCartItemAll(CartItemDTO dto){

		return cartItemReadService.findCartItemAll(dto);

	}

	public Long insertCartItemWithTx(CartItemDTO dto){

		return cartItemWriteService.insertCartItemWithTx(dto);
	}

	public int updateCartItemWithTx(CartItemDTO dto){

		return cartItemWriteService.updateCartItemWithTx(dto);
	}

	public int deleteCartItemWithTx(CartItemDTO dto){

		return cartItemWriteService.deleteCartItemWithTx(dto);

	}
	/**
	 * 根据用户id查询购物车pu信息(目前购物车中销售方式只有正常销售,普通预售和会籍预售)
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findCartItemOfPageByUserId(Long clientId,Long userId,Long companyId, Long platformId, Long storeId,
			Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		long startTime = System.currentTimeMillis();
		long begin = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		if(!SpringContextTool.isPrd()) {
			logger.info("findCartItemOfPageByUserId begin:");
		}
		ReceiverAddressDTO addressDefault = receiverAddressService.queryDefaultReceiverAddress(userId, 7l);
		//根据用户id，平台id、门店id查询用户普通预售购物车id
		Long cartId1 = null/*cartReadService.findByUserId(userId,platformId,storeId,4,clientId)*/;

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据用户id，平台id、门店id查询用户普通预售购物车id: {}, 耗时：{}",cartId1,now-begin);
			begin = System.currentTimeMillis();
		}


		//根据用户id，平台id、门店id查询用户会籍预售购物车id
		Long cartId2 = null/*cartReadService.findByUserId(userId, platformId, storeId, 6,clientId)*/;
		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据用户id，平台id、门店id查询用户会籍预售购物车id: {}, 耗时：{}",cartId2,now-begin);
			begin = System.currentTimeMillis();
		}


		//根据用户id，平台id、门店id查询用户普通购物车id
		Long cartId3 = cartReadService.findByUserId(userId, platformId, storeId, 1,clientId);
		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据用户id，平台id、门店id查询用户普通购物车id: {}, 耗时：{}",cartId3,now-begin);
			begin = System.currentTimeMillis();
		}

		//根据购物车id查询购物车pu信息
		CartItemDTO cartItemDTO = new CartItemDTO();
		List<Map<String, Object>> puListByPage = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(cartId1)){
			cartItemDTO.setCartId(cartId1);
			List<CartItemDTO> result=cartItemReadService.findCartItemAll(cartItemDTO);
			puListByPage.addAll(getPUListByPage(result, companyId, userId, platformId,4,storeId));
		}

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据购物车id查询普通预售购物车pu信息: 耗时：{}",now-begin);
			begin = System.currentTimeMillis();
		}

		if(EmptyUtil.isNotEmpty(cartId2)){
			cartItemDTO.setCartId(cartId2);
			List<CartItemDTO> result=cartItemReadService.findCartItemAll(cartItemDTO);
			puListByPage.addAll(getPUListByPage(result, companyId, userId, platformId,6,storeId));

		}

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据购物车id查询会籍预售购物车pu信息: 耗时：{}",now-begin);
			begin = System.currentTimeMillis();
		}

		if(EmptyUtil.isNotEmpty(cartId3)){
			cartItemDTO.setCartId(cartId3);
			//耗时3s
			List<CartItemDTO> result=cartItemReadService.findCartItemAll(cartItemDTO);
			puListByPage.addAll(getPUListByPage(addressDefault,result, companyId, userId, platformId,1,storeId));
		}

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据购物车id查询普通购物车pu信息: 耗时：{}",now-begin);
			begin = System.currentTimeMillis();
		}


		if(puListByPage==null||puListByPage.size()==0){
			pageResult.setList(null);

		}else{
			//进行分页处理
			List<Map<String, Object>> res = new ArrayList<>();
			pageResult.setTotalSize(puListByPage.size());
			int pageNo=page.getPageNo();
			int pageSize = page.getPageSize();
			int start = pageSize * (pageNo - 1);
			int end = pageSize * pageNo - 1;
			if(end<=puListByPage.size()-1){
				for(;start<=end;start++){
					puListByPage.get(start);
					res.add(puListByPage.get(start));
				}
			}else{
				for(;start<puListByPage.size();start++){
					res.add(puListByPage.get(start));
				}
			}
			pageResult.setList(res);




		}
		pageResult.setPageSize(page.getPageSize());
		pageResult.setPageNo(page.getPageNo());

		logger.info("用户查询自己的购物车耗时：{},返回结果:{}",System.currentTimeMillis()-startTime, JSON.toJSONString(pageResult));

		return pageResult;



	}


	//根据条件购物车的所有商品信息
	private List<Map<String, Object>> getPUListByPage(ReceiverAddressDTO addressDto,List<CartItemDTO> result,Long companyId,Long userId,Long platformId,Integer saleWay,Long storeId){
		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		logger.info("3test1:"+companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		List<CartItemDTO> cartItemList = result;
		if(EmptyUtil.isNotEmpty(cartItemList)&&cartItemList.size()!=0){
			//拼接puid集合
			List<Long> puIds = new ArrayList<>();
			List<Long> jdpuIds = new ArrayList<>();
			for (CartItemDTO cartItemDTO2 : cartItemList) {
				Integer source = cartItemDTO2.getSource();
				if(source!=null && source.intValue()==3) {
					jdpuIds.add(cartItemDTO2.getProductUnitId());
				}else {
					puIds.add(cartItemDTO2.getProductUnitId());
				}

			}
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(storeId);
			StoreDTO storeById = storeReadService.findStoreById(storeDTO);
			logger.info("3test2:"+storeId);
			//根据puid集合查询pu信息
			List<CommodityProductUnitDTO> commodityProductUnitList = new ArrayList<CommodityProductUnitDTO>();
			if(puIds!=null && puIds.size()>0) {
				commodityProductUnitList = commodityProductUnitReadService.findByPUIds(com.egeo.utils.StringUtils.longsToStrings(puIds));
			}
			logger.info("3test3:"+puIds.toString());
			Map<Long, String> merchantMap = findMerchantMap();
			logger.info("3test4:");
			Map<Long,JdProductVO2> jdProductReqs = new HashMap<Long,JdProductVO2>();
			HashMap<String,Integer> jdSkuNumMap = new HashMap<String,Integer>();
			Map<String,JdProductDTO> jdProducts = null;
			for (CartItemDTO cartItemDTO2 : cartItemList) {
				if(cartItemDTO2.getSource()!=null && cartItemDTO2.getSource()==3) {
					JdProductVO2 jdProductDTO = new JdProductVO2();
					jdProductDTO.setId(cartItemDTO2.getProductUnitId());
					jdProductReqs.put(cartItemDTO2.getProductUnitId(),jdProductDTO);
					jdSkuNumMap.put(cartItemDTO2.getProductUnitId().longValue()+"", cartItemDTO2.getNum().intValue()+1);
				}
			}
			if(jdProductReqs!=null && jdProductReqs.size()>0) {
				List<JdProductVO2> req = jdProductReqs.entrySet().stream().map(et ->et.getValue()).collect(Collectors.toList());
				JdProductStatStockAndPriceReqVO vo = new JdProductStatStockAndPriceReqVO();
				vo.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				vo.setCompanyId(RuntimeContext.cacheUser().getCompanyId());
				vo.setSkuNumMap(jdSkuNumMap);
				vo.setAddressStr(JSON.toJSONString(addressDto));
				vo.setPrice(true);
				vo.setStock(cartStock);
				jdProducts = jdProductReadService.getJdProductStatStockAndPrice(vo);
			}
			if(jdProducts==null) {
				jdProducts = new HashMap<String,JdProductDTO>();
			}

			for (CartItemDTO cartItemDTO2 : cartItemList) {
				Map<String, Object> map = new HashMap<>();
				map.put("cartItemId", cartItemDTO2.getId());
				map.put("storeName", storeById.getName());
				map.put("merchantId", cartItemDTO2.getMerchantId());
				map.put("merchantName", merchantMap.get(cartItemDTO2.getMerchantId()));
				map.put("isOwnMerchant", cartItemDTO2.getMerchantId().equals(1L) ? 1 : 0);
				map.put("channelProductId", cartItemDTO2.getChannelProductId());
				if(cartItemDTO2.getSource()!=null && cartItemDTO2.getSource()==3) {
					JdProductDTO snapshot = cartItemDTO2.getSnapshot()==null?null:(JSON.parseObject(cartItemDTO2.getSnapshot(), JdProductDTO.class));
					JdProductVO2 jdProductDTO = new JdProductVO2();
					jdProductDTO.setId(cartItemDTO2.getProductUnitId());
					JdProductDTO jdProduct = jdProducts.get(cartItemDTO2.getProductUnitId().longValue()+"");
					if(jdProduct==null) {
						continue;
					}
		        	map.put("buyType",1);
		        	map.put("source",3);
		        	map.put("standardUnitId", cartItemDTO2.getProductUnitId());
		        	map.put("productUnitId", cartItemDTO2.getProductUnitId());
		        	map.put("standardUnitName", snapshot==null?"":snapshot.getName());
					map.put("limitBuyNum", 1);
					if(snapshot.getSkuJson()!=null && snapshot.getSkuJson().length()>5) {
		                 JSONObject skuJson = JSON.parseObject(snapshot.getSkuJson());
		                 StringBuffer skuDesc = new StringBuffer();
		                 for(Entry<String, Object> entry: skuJson.entrySet()) {
		                	 if(skuDesc.length()>2) {
		                		 skuDesc.append("\n");
		                	 }
		                	 skuDesc.append(entry.getKey()).append(":").append(entry.getValue().toString());
		                 }
		                 map.put("skuDesc", skuDesc.toString());
		             }
					String imagePath = (snapshot==null?"":snapshot.getImagePath());
		        	map.put("status", jdProduct.standardUnitState());
		        	// 设置su商品销售价格
		        	map.put("salePrice", jdProduct.getPrice());
		        	map.put("addressSale", jdProduct.getSaleState());
		        	map.put("marketPrice", jdProduct.getMarketPrice());
		        	map.put("isVendible",1);
		        	if(imagePath.startsWith("{")) {
		        		JSONObject object = JSONObject.parseObject(imagePath);
			             String assisImg = object.getString("assisImg");
			             String primaryImg = object.getString("primaryImg");
			             map.put("puPicUrl", primaryImg);
		        	}else {
			        	map.put("puPicUrl", imagePath);
		        	}
		        	 /*JSONObject object = JSONObject.parseObject(imagePath);
		             String assisImg = object.getString("assisImg");
		             String primaryImg = object.getString("primaryImg");*/



		        	map.put("num", cartItemDTO2.getNum());
		        	map.put("realStockNum", jdProduct.getStock());

		        	map.put("commodityTemplateId", 2);
		        	map.put("storeId", 1);
		        	map.put("activityCode", "大厨管家总店");
		        	map.put("isOwnMerchant", 0);
		        	map.put("authority", 1);
		        	map.put("saleWay", 1);
		    		//设置运营方名称
		        	map.put("merchantName", "电商特供品");
		        	map.put("standardUnitMembersExplain", "");
		        	maps.add(map);
				}else if(cartItemDTO2.getSource()!=null && cartItemDTO2.getSource()==4){
					CakeProductDetailDTO  snapshot = cartItemDTO2.getSnapshot()==null?null:(JSON.parseObject(cartItemDTO2.getSnapshot(), CakeProductDetailDTO.class));
					CakeProductDetailProductsDTO cakeProductDetailProductsDTO = snapshot.getProduct();
					if(Objects.isNull(snapshot) || Objects.isNull(cakeProductDetailProductsDTO)){
						continue;
					}
					CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(snapshot,String.valueOf(cartItemDTO2.getProductUnitId()));
					map.put("buyType",1);
					map.put("source",4);
					map.put("standardUnitId", cartItemDTO2.getProductUnitId());
					map.put("productUnitId", cartItemDTO2.getProductUnitId());
					map.put("channelProductId", EmptyUtil.isNotEmpty(cartItemDTO2.getChannelProductId())?cartItemDTO2.getChannelProductId():cakeProductDetailProductsDTO.getId());
					map.put("standardUnitName", snapshot==null?"":cakeProductDetailProductsDTO.getTitle()+specsDTO.getName());
					map.put("limitBuyNum", 1);
					map.put("puPicUrl", EmptyUtil.isNotEmpty(specsDTO.getSpec_img())?specsDTO.getSpec_img():cakeProductDetailProductsDTO.getImage_path());
					map.put("skuDesc", specsDTO.getDescription());
					map.put("status",cakeProductDetailProductsDTO.getStatus().equals("1")?3:4);
					// 设置su商品销售价格
					map.put("salePrice", NumberUtils.retainDecimalPlaces(specsDTO.getPrice()));
					map.put("addressSale", specsDTO.getCan_buy());
					map.put("marketPrice", NumberUtils.retainDecimalPlaces(specsDTO.getMarket_price()));
					map.put("isVendible",1);
					map.put("num", cartItemDTO2.getNum());
					map.put("realStockNum", specsDTO.getStock());

					map.put("commodityTemplateId", 2);
					map.put("storeId", 1);
					map.put("activityCode", "大厨管家总店");
					map.put("isOwnMerchant", 0);
					map.put("authority", 1);
					map.put("saleWay", 1);
					//设置运营方名称
					map.put("merchantName", "蛋糕叔叔");
					map.put("standardUnitMembersExplain", "");
					maps.add(map);
				}else if(cartItemDTO2.getSource()!=null && cartItemDTO2.getSource()==5){
					ChannelProductDetailVO snapshot = cartItemDTO2.getSnapshot()==null?null:(JSON.parseObject(cartItemDTO2.getSnapshot(), ChannelProductDetailVO.class));
					ChannelProductDTO channelProductDTO = snapshot.getChannelProductDTO();
					if(Objects.isNull(snapshot) || Objects.isNull(channelProductDTO)){
						continue;
					}
					ChannelProductDescriptionDTO descriptionDTO = snapshot.getDescriptionDTO();
					List<ChannelProductBatchDTO> batchDTOList = snapshot.getBatchDTOList();
					ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(String.valueOf(cartItemDTO2.getProductUnitId()),batchDTOList);
					/*String content = null;
					if(Objects.nonNull(descriptionDTO)){
						content = descriptionDTO.getContent();
					}*/
					map.put("buyType",1);
					map.put("source",5);
					map.put("standardUnitId", cartItemDTO2.getProductUnitId());
					map.put("productUnitId", cartItemDTO2.getProductUnitId());
					map.put("channelProductId", cartItemDTO2.getChannelProductId());
					map.put("standardUnitName", snapshot==null?"":channelProductDTO.getTitle()+batchDTO.getSpecName());
					map.put("limitBuyNum", 1);
					map.put("puPicUrl", EmptyUtil.isNotEmpty(snapshot.getProductImg())?snapshot.getProductImg():null);
					map.put("skuDesc", batchDTO.getSpecName());
					map.put("status",channelProductDTO.getStatus()==1?3:4);
					// 设置su商品销售价格
					map.put("salePrice", NumberUtils.retainDecimalPlaces(batchDTO.getPrice().toPlainString()));
					map.put("addressSale", "1");
					map.put("marketPrice", NumberUtils.retainDecimalPlaces(batchDTO.getPriceMarket()));
					map.put("isVendible",1);
					map.put("num", cartItemDTO2.getNum());
					map.put("realStockNum", batchDTO.getNum());

					map.put("commodityTemplateId", 2);
					map.put("storeId", 1);
					map.put("activityCode", "大厨管家总店");
					map.put("isOwnMerchant", 0);
					map.put("authority", 1);
					map.put("saleWay", 1);
					//设置运营方名称
					map.put("merchantName", "全球购");
					map.put("standardUnitMembersExplain", "");
					maps.add(map);
				}else {

					for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitList) {
						logger.info("2test1");

						/*
						判断该商品是否属于该门店
						 */
						Boolean flag=false;
						//如果是总店商城
						StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(commodityProductUnitDTO.getStandardUnitId());
						logger.info("2test2");

						map.put("buyType",standardUnitById.getBuyType());
						if(storeId==1L||storeId==2L){
							if(standardUnitById.getStoreId().equals(storeId)){
								flag = true;
							}
						}else{
							//是门店
							//根据suid,门店id查询standardunitstore表,判断该su是否属于该门店
							StandardUnitStoreDTO standardUnitStoreDTO = new StandardUnitStoreDTO();
							standardUnitStoreDTO.setStoreId(storeId);
							standardUnitStoreDTO.setStandardUnitId(commodityProductUnitDTO.getStandardUnitId());
							List<StandardUnitStoreDTO> standardUnitStoreById = standardUnitStoreReadService.findStandardUnitStoreAll(standardUnitStoreDTO);
							if(EmptyUtil.isNotEmpty(standardUnitStoreById)){
								flag = true;
							}
						}

						logger.info("2test3");



						if(cartItemDTO2.getProductUnitId().equals(commodityProductUnitDTO.getId())&&flag){
							map.put("productUnitName", commodityProductUnitDTO.getName());
							map.put("standardUnitId", commodityProductUnitDTO.getStandardUnitId());
							map.put("limitBuyNum", commodityProductUnitDTO.getLimitBuyNum());


							logger.info("2test4");


							//校验用户是否有预售权限
							if(saleWay==6){
								Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, commodityProductUnitDTO.getStandardUnitId(), saleWay, platformId);
								for(Integer i:integerStringMap.keySet()){
									if(i!=1){
										map.put("authority",0);
									}else{
										map.put("authority",1);
									}
								}
							}else{
								map.put("authority",1);
							}
							logger.info("2test5");

							if(saleWay==4||saleWay==6){
								//显示预售期
								//StandardUnitDTO standardUnitDTO = standardUnitReadService.findStandardUnitById(commodityProductUnitDTO.getStandardUnitId());
								Long presellPeriod = standardUnitById.getPresellPeriod();
								map.put("presellPeriod", presellPeriod);

							}


							map.put("productUnitId", cartItemDTO2.getProductUnitId());
							//根据公司type来确定pu价格
							if(companyDTO.getCompanyType()==0){
								//正式公司
								map.put("salePrice", commodityProductUnitDTO.getSalePrice());
							}else if(companyDTO.getCompanyType()==1){
								//演示公司
								map.put("salePrice", commodityProductUnitDTO.getDemoSalePrice());
							}else if(companyDTO.getCompanyType()==2){
								//竞品公司
								map.put("salePrice", commodityProductUnitDTO.getCompetingSalePrice());

							}
							map.put("isVendible", commodityProductUnitDTO.getIsVendible());
							if(cartItemDTO2.getSalePrice().compareTo(commodityProductUnitDTO.getSalePrice()) != 0){
								map.put("isUpdateSalePrice", 1);
							}else{
								map.put("isUpdateSalePrice", 0);
							}
							map.put("status", commodityProductUnitDTO.getStatus());
							//如果没有pu的img则展示su的img
							String puImg = commodityProductUnitDTO.getPuPicUrl();
							logger.info("2test6");

							if (EmptyUtil.isBlank(puImg)) {
								// pu图片查询逻辑
								puImg =  commodityProductUnitReadService.queryPuNullImgUrl(commodityProductUnitDTO.getSkuId());

							}
							logger.info("2test7");

							map.put("puPicUrl", puImg);
							/*if(EmptyUtil.isEmpty(commodityProductUnitDTO.getPuPicUrl())){
								map.put("puPicUrl", standardUnitById.getPictureUrl());
							}else{
								map.put("puPicUrl", commodityProductUnitDTO.getPuPicUrl());
							}*/
							map.put("num", cartItemDTO2.getNum());
							map.put("commodityTemplateId", commodityProductUnitDTO.getCommodityTemplateId());
							//根据puid查询pu库存

							Long realStockNum = commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(cartItemDTO2.getProductUnitId());
							map.put("realStockNum", realStockNum);
							logger.info("2test8");

							// 为了保证代码的逻辑性和以后的扩展性、因此采用了根据suId一条一条去查询的方式查询限购规则信息、拼接前台显示限购信息
							String limitRule = jointLimitRule(commodityProductUnitDTO.getStandardUnitId(),companyId,userId,platformId,storeId);
							logger.info("2test9");

							map.put("limitRule", limitRule);
							map.put("saleWay", saleWay);
							//京东商品需要返回spu所属的其他pu信息
							Long jdSpuId = commodityProductUnitDTO.getJdSpuId();
							if(jdSpuId!=null){
								List<Map<String, Object>> spuList = getSpuList(jdSpuId);
								map.put("puInfoOfSameSpu", spuList);


							}

							logger.info("2test10");


							if(realStockNum.equals(0L) || commodityProductUnitDTO.getStatus() != 3){
								maps.add(map);
							}else{
								maps.add(0, map);
							}
							break;
						}
					}
				}
			}

			return maps;
		}else{
			return maps;
		}
	}


	public PageResult<Map<String, Object>> refreshJdCartItemOfPageByUserId(Long clientId,Long userId,Long companyId, Long platformId, Long storeId,
			Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		long startTime = System.currentTimeMillis();
		long begin = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		if(!SpringContextTool.isPrd()) {
			logger.info("findCartItemOfPageByUserId begin:");
		}

		//根据用户id，平台id、门店id查询用户普通预售购物车id
		Long cartId1 = cartReadService.findByUserId(userId,platformId,storeId,4,clientId);

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据用户id，平台id、门店id查询用户普通预售购物车id: {}, 耗时：{}",cartId1,now-begin);
			begin = System.currentTimeMillis();
		}


		//根据用户id，平台id、门店id查询用户会籍预售购物车id
		Long cartId2 = cartReadService.findByUserId(userId, platformId, storeId, 6,clientId);
		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据用户id，平台id、门店id查询用户会籍预售购物车id: {}, 耗时：{}",cartId2,now-begin);
			begin = System.currentTimeMillis();
		}


		//根据用户id，平台id、门店id查询用户普通购物车id
		Long cartId3 = cartReadService.findByUserId(userId, platformId, storeId, 1,clientId);
		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据用户id，平台id、门店id查询用户普通购物车id: {}, 耗时：{}",cartId3,now-begin);
			begin = System.currentTimeMillis();
		}

		//根据购物车id查询购物车pu信息
		CartItemDTO cartItemDTO = new CartItemDTO();
		List<Map<String, Object>> puListByPage = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(cartId1)){
			cartItemDTO.setCartId(cartId1);
			List<CartItemDTO> result=cartItemReadService.findCartItemAll(cartItemDTO);
			puListByPage.addAll(getPUListByPage(result, companyId, userId, platformId,4,storeId));
		}

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据购物车id查询普通预售购物车pu信息: 耗时：{}",now-begin);
			begin = System.currentTimeMillis();
		}

		if(EmptyUtil.isNotEmpty(cartId2)){
			cartItemDTO.setCartId(cartId2);
			List<CartItemDTO> result=cartItemReadService.findCartItemAll(cartItemDTO);
			puListByPage.addAll(getPUListByPage(result, companyId, userId, platformId,6,storeId));

		}

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据购物车id查询会籍预售购物车pu信息: 耗时：{}",now-begin);
			begin = System.currentTimeMillis();
		}

		if(EmptyUtil.isNotEmpty(cartId3)){
			cartItemDTO.setCartId(cartId3);
			//耗时3s
			List<CartItemDTO> result=cartItemReadService.findCartItemAll(cartItemDTO);
			puListByPage.addAll(getPUListByPage(result, companyId, userId, platformId,1,storeId));
		}

		if(!SpringContextTool.isPrd()) {
			now = System.currentTimeMillis();
			logger.info("findCartItemOfPageByUserId 根据购物车id查询普通购物车pu信息: 耗时：{}",now-begin);
			begin = System.currentTimeMillis();
		}


		if(puListByPage==null||puListByPage.size()==0){
			pageResult.setList(null);

		}else{
			//进行分页处理
			List<Map<String, Object>> res = new ArrayList<>();
			pageResult.setTotalSize(puListByPage.size());
			int pageNo=page.getPageNo();
			int pageSize = page.getPageSize();
			int start = pageSize * (pageNo - 1);
			int end = pageSize * pageNo - 1;
			if(end<=puListByPage.size()-1){
				for(;start<=end;start++){
					puListByPage.get(start);
					res.add(puListByPage.get(start));
				}
			}else{
				for(;start<puListByPage.size();start++){
					res.add(puListByPage.get(start));
				}
			}
			pageResult.setList(res);




		}
		pageResult.setPageSize(page.getPageSize());
		pageResult.setPageNo(page.getPageNo());

		logger.info("用户查询自己的购物车耗时：{},返回结果:{}",System.currentTimeMillis()-startTime, JSON.toJSONString(pageResult));

		return pageResult;



	}

	//根据条件购物车的所有商品信息
	private List<Map<String, Object>> getPUListByPage(List<CartItemDTO> result,Long companyId,Long userId,Long platformId,Integer saleWay,Long storeId){
		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		logger.info("3test1:"+companyId);
		if(EmptyUtil.isEmpty(companyDTO)){
			throw new BusinessException("所属公司不存在");
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		List<CartItemDTO> cartItemList = result;
		if(EmptyUtil.isNotEmpty(cartItemList)&&cartItemList.size()!=0){
			//拼接puid集合
			List<Long> puIds = new ArrayList<>();
			List<Long> jdpuIds = new ArrayList<>();
			for (CartItemDTO cartItemDTO2 : cartItemList) {
				Integer source = cartItemDTO2.getSource();
				if(source!=null && source.intValue()==3) {
					jdpuIds.add(cartItemDTO2.getProductUnitId());
				}else {
					puIds.add(cartItemDTO2.getProductUnitId());
				}

			}
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(storeId);
			StoreDTO storeById = storeReadService.findStoreById(storeDTO);
			logger.info("3test2:"+storeId);
			//根据puid集合查询pu信息
			List<CommodityProductUnitDTO> commodityProductUnitList = new ArrayList<CommodityProductUnitDTO>();
			if(puIds!=null && puIds.size()>0) {
				commodityProductUnitList = commodityProductUnitReadService.findByPUIds(com.egeo.utils.StringUtils.longsToStrings(puIds));
			}
			logger.info("3test3:"+puIds.toString());
			Map<Long, String> merchantMap = findMerchantMap();
			logger.info("3test4:");
			Map<Long,JdProductVO2> jdProductReqs = new HashMap<Long,JdProductVO2>();
			List<JdProductDTO> jdProducts = new ArrayList<JdProductDTO>();
			for (CartItemDTO cartItemDTO2 : cartItemList) {
				if(cartItemDTO2.getSource()!=null && cartItemDTO2.getSource()==3) {
					JdProductVO2 jdProductDTO = new JdProductVO2();
					jdProductDTO.setId(cartItemDTO2.getProductUnitId());
					jdProductReqs.put(cartItemDTO2.getProductUnitId(),jdProductDTO);
				}
			}
			if(jdProductReqs!=null && jdProductReqs.size()>0) {
				List<JdProductVO2> req = jdProductReqs.entrySet().stream().map(et ->et.getValue()).collect(Collectors.toList());
				jdProducts = jdProductReadService.findJdProductByIds(req);
			}
			if(jdProducts==null) {
				jdProducts = new ArrayList<JdProductDTO>();
			}

			for (CartItemDTO cartItemDTO2 : cartItemList) {
				Map<String, Object> map = new HashMap<>();
				map.put("cartItemId", cartItemDTO2.getId());
				map.put("storeName", storeById.getName());
				map.put("merchantId", cartItemDTO2.getMerchantId());
				map.put("merchantName", merchantMap.get(cartItemDTO2.getMerchantId()));
				map.put("isOwnMerchant", cartItemDTO2.getMerchantId().equals(1L) ? 1 : 0);
				if(cartItemDTO2.getSource()!=null && cartItemDTO2.getSource()==3) {
					JdProductVO2 jdProductDTO = new JdProductVO2();
					jdProductDTO.setId(cartItemDTO2.getProductUnitId());
					JdProductDTO jdProduct = null;
					for(JdProductDTO jdOne : jdProducts) {
						if(jdOne.getId().equals(cartItemDTO2.getProductUnitId())) {
							jdProduct = jdOne;
						}
					}

					if(jdProduct==null) {
						continue;
					}
		        	map.put("buyType",1);
		        	map.put("source",3);
		        	map.put("standardUnitId", cartItemDTO2.getProductUnitId());
		        	map.put("productUnitId", cartItemDTO2.getProductUnitId());
		        	map.put("standardUnitName", jdProduct.getName());
					map.put("limitBuyNum", 1);

					String imagePath = jdProduct.getImagePath();
		        	map.put("status", jdProduct.standardUnitState());
		        	// 设置su商品销售价格
		        	map.put("salePrice", jdProduct.getPrice());
		        	map.put("marketPrice", jdProduct.getMarketPrice());
		        	map.put("isVendible",1);
		        	 /*JSONObject object = JSONObject.parseObject(imagePath);
		             String assisImg = object.getString("assisImg");
		             String primaryImg = object.getString("primaryImg");*/
		        	map.put("puPicUrl", imagePath);



		        	map.put("num", cartItemDTO2.getNum());
		        	map.put("realStockNum", cartItemDTO2.getProductUnitId()%100);

		        	map.put("commodityTemplateId", 2);
		        	map.put("storeId", 1);
		        	map.put("activityCode", "大厨管家总店");
		        	map.put("isOwnMerchant", 0);
		        	map.put("authority", 1);
		        	map.put("saleWay", 1);
		    		//设置运营方名称
		        	map.put("merchantName", "电商特供品");
		        	map.put("standardUnitMembersExplain", "");
		        	maps.add(map);
				}else {

					for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitList) {
						logger.info("2test1");

						/*
						判断该商品是否属于该门店
						 */
						Boolean flag=false;
						//如果是总店商城
						StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(commodityProductUnitDTO.getStandardUnitId());
						logger.info("2test2");

						map.put("buyType",standardUnitById.getBuyType());
						if(storeId==1L||storeId==2L){
							if(standardUnitById.getStoreId().equals(storeId)){
								flag = true;
							}
						}else{
							//是门店
							//根据suid,门店id查询standardunitstore表,判断该su是否属于该门店
							StandardUnitStoreDTO standardUnitStoreDTO = new StandardUnitStoreDTO();
							standardUnitStoreDTO.setStoreId(storeId);
							standardUnitStoreDTO.setStandardUnitId(commodityProductUnitDTO.getStandardUnitId());
							List<StandardUnitStoreDTO> standardUnitStoreById = standardUnitStoreReadService.findStandardUnitStoreAll(standardUnitStoreDTO);
							if(EmptyUtil.isNotEmpty(standardUnitStoreById)){
								flag = true;
							}
						}

						logger.info("2test3");



						if(cartItemDTO2.getProductUnitId().equals(commodityProductUnitDTO.getId())&&flag){
							map.put("productUnitName", commodityProductUnitDTO.getName());
							map.put("standardUnitId", commodityProductUnitDTO.getStandardUnitId());
							map.put("limitBuyNum", commodityProductUnitDTO.getLimitBuyNum());


							logger.info("2test4");


							//校验用户是否有预售权限
							if(saleWay==6){
								Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, commodityProductUnitDTO.getStandardUnitId(), saleWay, platformId);
								for(Integer i:integerStringMap.keySet()){
									if(i!=1){
										map.put("authority",0);
									}else{
										map.put("authority",1);
									}
								}
							}else{
								map.put("authority",1);
							}
							logger.info("2test5");

							if(saleWay==4||saleWay==6){
								//显示预售期
								//StandardUnitDTO standardUnitDTO = standardUnitReadService.findStandardUnitById(commodityProductUnitDTO.getStandardUnitId());
								Long presellPeriod = standardUnitById.getPresellPeriod();
								map.put("presellPeriod", presellPeriod);

							}


							map.put("productUnitId", cartItemDTO2.getProductUnitId());
							//根据公司type来确定pu价格
							if(companyDTO.getCompanyType()==0){
								//正式公司
								map.put("salePrice", commodityProductUnitDTO.getSalePrice());
							}else if(companyDTO.getCompanyType()==1){
								//演示公司
								map.put("salePrice", commodityProductUnitDTO.getDemoSalePrice());
							}else if(companyDTO.getCompanyType()==2){
								//竞品公司
								map.put("salePrice", commodityProductUnitDTO.getCompetingSalePrice());

							}
							map.put("isVendible", commodityProductUnitDTO.getIsVendible());
							if(cartItemDTO2.getSalePrice().compareTo(commodityProductUnitDTO.getSalePrice()) != 0){
								map.put("isUpdateSalePrice", 1);
							}else{
								map.put("isUpdateSalePrice", 0);
							}
							map.put("status", commodityProductUnitDTO.getStatus());
							//如果没有pu的img则展示su的img
							String puImg = commodityProductUnitDTO.getPuPicUrl();
							logger.info("2test6");

							if (EmptyUtil.isBlank(puImg)) {
								// pu图片查询逻辑
								puImg =  commodityProductUnitReadService.queryPuNullImgUrl(commodityProductUnitDTO.getSkuId());

							}
							logger.info("2test7");

							map.put("puPicUrl", puImg);
							/*if(EmptyUtil.isEmpty(commodityProductUnitDTO.getPuPicUrl())){
								map.put("puPicUrl", standardUnitById.getPictureUrl());
							}else{
								map.put("puPicUrl", commodityProductUnitDTO.getPuPicUrl());
							}*/
							map.put("num", cartItemDTO2.getNum());
							map.put("commodityTemplateId", commodityProductUnitDTO.getCommodityTemplateId());
							//根据puid查询pu库存

							Long realStockNum = commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(cartItemDTO2.getProductUnitId());
							map.put("realStockNum", realStockNum);
							logger.info("2test8");

							// 为了保证代码的逻辑性和以后的扩展性、因此采用了根据suId一条一条去查询的方式查询限购规则信息、拼接前台显示限购信息
							String limitRule = jointLimitRule(commodityProductUnitDTO.getStandardUnitId(),companyId,userId,platformId,storeId);
							logger.info("2test9");

							map.put("limitRule", limitRule);
							map.put("saleWay", saleWay);
							//京东商品需要返回spu所属的其他pu信息
							Long jdSpuId = commodityProductUnitDTO.getJdSpuId();
							if(jdSpuId!=null){
								List<Map<String, Object>> spuList = getSpuList(jdSpuId);
								map.put("puInfoOfSameSpu", spuList);


							}

							logger.info("2test10");


							if(realStockNum.equals(0L) || commodityProductUnitDTO.getStatus() != 3){
								maps.add(map);
							}else{
								maps.add(0, map);
							}
							break;
						}
					}
				}
			}

			return maps;
		}else{
			return maps;
		}
	}

	//查询所属spu下的pu信息
	private List<Map<String, Object>> getSpuList(Long jdSpuId){
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			CommodityProductUnitDTO dto = new CommodityProductUnitDTO();
			dto.setJdSpuId(jdSpuId);
			List<CommodityProductUnitDTO> commodityProductUnitAll = commodityProductUnitReadService.findCommodityProductUnitAll(dto);

			for (CommodityProductUnitDTO pu : commodityProductUnitAll) {
				Map<String, Object> map = new HashMap<>();
				Long puId = pu.getId();
				JdProductInnerIdDTO innerIdDTO = new JdProductInnerIdDTO();
				innerIdDTO.setInnerPuId(puId);
				List<JdProductInnerIdDTO> jdProductInnerIdAll = jdProductInnerIdReadService.findJdProductInnerIdAll(innerIdDTO);
				if (EmptyUtil.isNotEmpty(jdProductInnerIdAll)) {
					JdProductInnerIdDTO innerIdDTO1 = jdProductInnerIdAll.get(0);
					Long jdSkuId = innerIdDTO1.getJdSkuId();
					JdProductDTO jdProductDTO = new JdProductDTO();
					jdProductDTO.setId(jdSkuId);
					JdProductDTO jdProductById = jdProductReadService.findJdProductById(jdProductDTO);
					String skuJson = jdProductById.getSkuJson();
					skuJson=getJdSkuJson(skuJson);
					map.put("skuJdon", skuJson);
					map.put("puId", pu.getId());
					map.put("suId", pu.getStandardUnitId());
					map.put("ImageUrl",pu.getPuPicUrl());
					result.add(map);
				}
			}
		}catch (Exception e){
			logger.error("查询spu所属信息失败"+jdSpuId);
		}
		return result;

	}

	private String getJdSkuJson(String skuJson){
		if(StringUtils.isNotEmpty(skuJson)){
			skuJson = skuJson.replaceAll("\"", "");
			skuJson = skuJson.replace("{", "");
			skuJson = skuJson.replace("}", "");

			String[] splitArr=skuJson.split(":");
			if(splitArr.length>1){
				skuJson=splitArr[1];
			}
		}
		return skuJson;
	}

	private Map<Long, String> findMerchantMap() {
		Map<Long, String> merchantMap = new HashMap<>();
		List<MerchantDTO> merchantList = merchantReadService.findMerchantAll(new MerchantDTO());
		if (EmptyUtil.isNotEmpty(merchantList)) {
			for (MerchantDTO merchantDTO : merchantList) {
				merchantMap.put(merchantDTO.getId(), merchantDTO.getName());
			}
		}
		return merchantMap;
	}


	/**
	 * 为了保证代码的逻辑性和以后的扩展性、因此采用了根据suId一条一条去查询的方式查询限购规则信息、拼接前台显示限购信息
	 * @param standardUnitId
	 * @param storeId
	 * @return
	 */
	private String jointLimitRule(Long standardUnitId, Long companyId, Long userId, Long platformId, Long storeId) {
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		List<Long> suCombinationIdList = com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.querySuCombinationBySuId(standardUnitId));
		Map<Long, List<Long>> map = new HashMap<>();
		for(Long comId:suCombinationIdList){
			List<Long> suIdByStandardUnitCombinationId = com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.findSuIdByStandardUnitCombinationId(standardUnitId, platformId));
			map.put(comId,suIdByStandardUnitCombinationId);
		}
		List<String> strings = limitRuleReadService.startLimitRuleByStandardUnitId(standardUnitId, companyId, companyAllId, platformId, userId, storeId, map);
		if(EmptyUtil.isNotEmpty(strings)){
			return "该商品为限购商品，"+strings.get(0);
		}
		return null;
	}

	/**
	 * 加入购物车

	 * @return
	 */
	public JsonResult<String> saveCartItemWithTx(CommodityProductUnitDTO pu,CartItemDTO dto, Long userId, Long platformId, Long storeId, Long clientId) {

		//根据suid查询su信息
		Integer source = dto.getSource();
		Integer saleWay = 1;
		if(source!=null && (source.intValue()==3 || source.intValue()==4 || source.intValue()==5)) {
			dto.setStandardUnitId(pu.getStandardUnitId());
			dto.setSalePrice(pu.getSalePrice());
			dto.setMerchantId(pu.getMerchantId());
		}else{
			StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
			standardUnitDTO.setId(pu.getStandardUnitId());
			StandardUnitDTO standardUnitDTO2 = standardUnitReadService.findStandardUnitById(standardUnitDTO);
			if(EmptyUtil.isNotEmpty(standardUnitDTO2)){
				if(standardUnitDTO2.getStatus() != 3){
					throw new BusinessException("商品已下架");
				}
			}
			saleWay=standardUnitDTO2.getSaleWay();

			dto.setStandardUnitId(pu.getStandardUnitId());
			dto.setSalePrice(pu.getSalePrice());
			dto.setMerchantId(standardUnitDTO2.getMerchantId());
		}

		if(pu.getStatus() != 3){
			throw new BusinessException("商品已下架");
		}
		//校验用户是否有预售权限

		if(saleWay==6){
			Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, pu.getStandardUnitId(), saleWay, platformId);
			for(Integer i:integerStringMap.keySet()){
				if(i!=1){
					throw new BusinessException(integerStringMap.get(i));
				}
			}
		}
		//dto已经拥有suid,saleprice,商家id,platformId,puid
		Long rt = cartItemWriteService.saveCartItemWithTx(dto, userId, saleWay, storeId, clientId);
		if(EmptyUtil.isNotEmpty(rt)){
			return JsonResult.success("加入购物车成功");
		}else{
			return JsonResult.success("加入购物车失败");
		}
	}

	/**
	 * 校验用户是否有会籍预售的权限
	 * @param cartItemId
	 * @return     0表示失败,1表示成功
	private Map<Integer,String> checkUserMembershipAuthority(Long userId,Long suId,Integer saleWay,Long platformId){
		Map<Integer, String> res = new HashMap<>();
		//判断用户是否有权限购买该商品(如果销售方式是6.会籍预售则校验用户的权限)
		if(saleWay==6){


			//查询该用户的所有会籍
			MembershipUserDTO membershipUserDTO = new MembershipUserDTO();
			membershipUserDTO.setStatusCode(1);//会员状态有效
			membershipUserDTO.setUserId(userId);
			List<MembershipUserDTO> membershipUserAll = membershipUserReadService.findMembershipUserAll(membershipUserDTO);
			if(membershipUserAll==null){
				res.put(1,"会籍预售商品,需要拥有会籍权限");
				return res;

			}
			//获取su的所有关联会籍id
			StandardUnitMembershipDTO suMembershipDTO=new StandardUnitMembershipDTO();
			suMembershipDTO.setPlatformId(platformId);
			suMembershipDTO.setStandardUnitId(suId);
			List<StandardUnitMembershipDTO> standardUnitMembershipAll = standardUnitMembershipReadService.findStandardUnitMembershipAll(suMembershipDTO);

			//判断用户是否拥有的会籍是否存在该商品要求的会籍
			boolean flag = false;
			for(StandardUnitMembershipDTO sumDTO:standardUnitMembershipAll){
				for( MembershipUserDTO userDTO:membershipUserAll){
					if(userDTO.getMembershipId()==sumDTO.getMembershipId()){
						//拥有关联会籍,校验会籍的权限是否正确
						MembershipAuthorityDTO authorityDTO = new MembershipAuthorityDTO();
						authorityDTO.setPlatformId(platformId);
						authorityDTO.setMembershipId(userDTO.getMembershipId());
						authorityDTO.setIsStop(0);
						//设置预售权限
						authorityDTO.setAuthorityId(1L);
						List<MembershipAuthorityDTO> membershipAuthorityAll = membershipAuthorityReadService.findMembershipAuthorityAll(authorityDTO);
						if(membershipAuthorityAll==null||membershipAuthorityAll.size()==0){
							res.put(0,"商品关联的会籍已无会籍预售的权限,联系客服询问");
							return res;
						}else if(membershipAuthorityAll.size()>1){
							//出现一个会籍对应多个相同权限(正常不会出现)
							res.put(0,"会籍权限设置有误,请联系客服");
							return res;

						}else if(membershipAuthorityAll.size()==1){
							res.put(1, "校验成功");
							flag = true;
						}

					}
				}
			}
			//用户已拥有会籍拥有会籍预售的权限校验失败(成功则正常进入加入购物车)
			if(!flag){
				res.put(0, "会籍预售商品,您无该权限,请先购买会籍");
				return res;
			}




		}
		return res;
	}*/



	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	public int addNumWithTx(Long cartItemId) {
		//根据购物车商品id查询购物车商品信息
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setId(cartItemId);
		CartItemDTO cartItemDTO2 = cartItemReadService.findCartItemById(cartItemDTO);
		if(EmptyUtil.isNotEmpty(cartItemDTO2) && cartItemDTO2.getSource()!=null && cartItemDTO2.getSource().intValue()==3) {
			ReceiverAddressDTO receiver = receiverAddressService.queryDefaultReceiverAddress(RuntimeContext.cacheUser().getId(), 7l);
			Boolean hasJdStock = jdService.hasStock(receiver, (cartItemDTO2.getNum() + 1), cartItemDTO2.getStandardUnitId());
			if(hasJdStock!=null && hasJdStock) {
				//库存足够
			}else {
				throw new BusinessException("商品库存不足");
			}
		}else if(EmptyUtil.isNotEmpty(cartItemDTO2) && cartItemDTO2.getSource()!=null && cartItemDTO2.getSource().intValue()==3){
			ReceiverAddressDTO receiver = receiverAddressService.queryDefaultReceiverAddress(RuntimeContext.cacheUser().getId(), 7l);
		}else if(EmptyUtil.isNotEmpty(cartItemDTO2) && cartItemDTO2.getSource()!=null && (cartItemDTO2.getSource().intValue()==4 || cartItemDTO2.getSource().intValue() ==5)){

		}else{
			Long realStockNum = commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(cartItemDTO2.getProductUnitId());
			if(realStockNum.longValue() < cartItemDTO2.getNum() + 1L){
				throw new BusinessException("商品库存不足");
			}
		}

		return cartItemWriteService.addNumWithTx(cartItemId);
	}
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	public int minusNumWithTx(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemWriteService.minusNumWithTx(cartItemId);
	}
	/**
	 * 根据用户id查询购物车pu数量
	 * @return
	 */
	public Integer findCartItemSumByUserId(Long storeId,Long userId, Long platformId, Long clientId) {
		// TODO Auto-generated method stub
		//根据userid和storeid查询所有购物车商品
		CartDTO dto = new CartDTO();
		int sum=0;
		dto.setStoreId(storeId);
		dto.setUserid(userId);
		dto.setClientId(clientId);
		dto.setPlatformId(platformId);
		List<CartDTO> cartAll = cartReadService.findCartAll(dto);
		if(EmptyUtil.isNotEmpty(cartAll)){
			for(CartDTO cartDTO:cartAll){
				CartItemDTO cartItemDTO = new CartItemDTO();
				cartItemDTO.setCartId(cartDTO.getId());
				List<CartItemDTO> cartItemAll = cartItemReadService.findCartItemAll(cartItemDTO);
				if(EmptyUtil.isNotEmpty(cartItemAll)){
					for(CartItemDTO itemDTO:cartItemAll){
						if(itemDTO.getSource()==null) {

							StandardUnitDTO standardUnitById = standardUnitReadService.findStandardUnitById(itemDTO.getStandardUnitId());
							if(EmptyUtil.isEmpty(standardUnitById)){
								logger.warn("用户("+userId+")购物车数据错误:"+itemDTO.getStandardUnitId()+"商品已移除");
								continue;
								//throw new BusinessException(itemDTO.getStandardUnitId()+"商品已移除");
							}
							if(storeId==1||storeId==2){
								if(standardUnitById.getStoreId().equals(storeId)){
									sum++;
								}
							}else{
								StandardUnitStoreDTO standardUnitStoreDTO = new StandardUnitStoreDTO();
								standardUnitStoreDTO.setStoreId(storeId);
								standardUnitStoreDTO.setStandardUnitId(standardUnitById.getId());
								List<StandardUnitStoreDTO> standardUnitStoreAll = standardUnitStoreReadService.findStandardUnitStoreAll(standardUnitStoreDTO);
								if(EmptyUtil.isNotEmpty(standardUnitStoreAll)){
									sum++;
								}
							}
						}else {
							//数据来源为三方，不需要判断商品状态，直接累加
							sum++;
						}
					}


				}

			}
		}


		return sum;
		//return cartItemReadService.findCartItemSumByUserId(storeId,userId, platformId, clientId);
	}
	/**
	 * 根据用户id查询用户购物车pu种类数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	public int findCartItemPUSumByUserId(Long userId, Long platformId,Long storeId) {
		// TODO Auto-generated method stub
		return cartItemReadService.findCartItemPUSumByUserId(userId, platformId,storeId);
	}
	/**
	 * 根据购物车id集合批量删除购物车pu商品关系
	 * @param cartItemIdList
	 * @return
	 */
	public int deleteCartItemByIdsWithTx(List<Long> cartItemIdList) {
		// TODO Auto-generated method stub
		return cartItemWriteService.deleteCartItemByIdsWithTx(cartItemIdList);
	}

}
