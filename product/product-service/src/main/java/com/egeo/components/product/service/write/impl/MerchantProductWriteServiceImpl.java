package com.egeo.components.product.service.write.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.egeo.common.BusinessConstant;
import com.egeo.components.product.converter.MerchantProductConverter;
import com.egeo.components.product.converter.ProductUnitConverter;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.manage.read.CommodityProductUnitReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitReadManage;
import com.egeo.components.product.manage.read.StoreTreeNodeReadManage;
import com.egeo.components.product.manage.write.MerchantProductWriteManage;
import com.egeo.components.product.manage.write.StoreProductUnitWriteManage;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.components.product.service.write.MerchantPictureWriteService;
import com.egeo.components.product.service.write.MerchantProdPictureWriteService;
import com.egeo.components.product.service.write.MerchantProductWriteService;
import com.egeo.components.product.service.write.PictureWriteService;
import com.egeo.components.product.service.write.ProductUnitWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantProductWriteService")
public class MerchantProductWriteServiceImpl  implements MerchantProductWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantProductWriteServiceImpl.class);

	@Autowired
	private MerchantProductWriteManage merchantProductWriteManage;

	@Autowired
	private MerchantPictureWriteService merchantPictureWriteService;

	@Autowired
	private MerchantProdPictureWriteService merchantProdPictureWriteService;

	@Autowired
	private ProductUnitWriteService productUnitWriteService;

	@Autowired
	private PictureWriteService pictureWriteService;

	@Autowired
	private StandardProductUnitReadManage standardProductUnitReadManage;
	
	@Autowired
	private CommodityProductUnitReadManage commodityProductUnitReadManage;
	
	@Autowired
	private StoreProductUnitWriteManage storeProductUnitWriteManage;
	
	@Autowired
	private StoreTreeNodeReadManage storeTreeNodeReadManage;
	@Resource
	private JedisUtil jedisUtil;



	@Override
	public Long insertMerchantProductWithTx(MerchantProductDTO dto, String picture, String pictureList,String webBannerPictureList,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList, String clientList,
			String content, List<ProductUnitDTO> productUnitList, List<Long> tagList,List<String> keywords,
			List<Long> companys,List<Long> demoCompanys,List<Long> competingCompanys,
			List<Long> storeIds,List<Long> membershipIds) {
		MerchantProductPO po = MerchantProductConverter.toPO(dto);

		if(EmptyUtil.isEmpty(clientList))
			throw new BusinessException("请选择所属平台");
		
		List<Long> clients = new ArrayList<>(JSONArray.parseArray(clientList, Long.class));
		if (EmptyUtil.isEmpty(clients)) 
			throw new BusinessException("请选择所属平台");
		if(EmptyUtil.isEmpty(picture))
			throw new BusinessException("商品封面图片不能为空");
		// app轮播图
		List<String> pictures = null;
		if(EmptyUtil.isNotEmpty(pictureList))
			pictures = JSONArray.parseArray(pictureList, String.class);
		
		// web轮播图
		List<String> webBannerPictures = null;
		if(EmptyUtil.isNotEmpty(webBannerPictureList))
			webBannerPictures = JSONArray.parseArray(webBannerPictureList, String.class);
		
		// 如果客户端id包含app端或微信端id并且其轮播图图片为空
		if((clients.contains(BusinessConstant.APP_CLIENT_Id) || clients.contains(BusinessConstant.WEIXIN_CLIENT_ID)) && EmptyUtil.isEmpty(pictures))
			throw new BusinessException("商品app端或微信端轮播图不能为空");
				
		// 如果客户端id包含web客户端id并且其轮播图图片为空
		if(clients.contains(BusinessConstant.WEB_CLIENT_ID) && EmptyUtil.isEmpty(webBannerPictures))
			throw new BusinessException("商品web端轮播图不能为空");

		return merchantProductWriteManage.insertMerchantProductWithTx(po, picture, pictures,webBannerPictures, clients,
				content, ProductUnitConverter.toPO(productUnitList), tagList,keywords,companys,demoCompanys,competingCompanys,
				storeIds,membershipIds);
	}
	@Override
	public int updateMerchantProductWithTx(MerchantProductDTO dto,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList, 
			String clientList,
			List<Long> tagList,
			List<String> keywords,
			List<Long> companys,
			List<Long> demoCompanys,
			List<Long> competingCompanys,
			String webBannerPictureList,
			List<Long> storeIdList,
			List<Long> membershipIdList) {

		StandardProductUnitPO standardProductUnitPO2 = standardProductUnitReadManage.querySpuBySuId(dto.getId());

		if (standardProductUnitPO2.getCommodityTemplateId().equals(1L)) {
			/*if (dto.getName().length() > 8) {
				throw new BusinessException("商品名称不能超过8位");
			}*/
			double length = getLength(dto.getName());
			if (length > 8) {
				throw new BusinessException("商品名称不能超过8位");
			}
		}
		if(EmptyUtil.isEmpty(clientList))
			throw new BusinessException("请选择所属平台");
		
		List<Long> clients = new ArrayList<>(JSONArray.parseArray(clientList, Long.class));
		if (EmptyUtil.isEmpty(clients)) 
			throw new BusinessException("请选择所属平台");
		
		// web轮播图
		List<String> webBannerPictures = null;
		if(EmptyUtil.isNotEmpty(webBannerPictureList))
			webBannerPictures = JSONArray.parseArray(webBannerPictureList, String.class);
		
		// 如果客户端id包含web客户端id并且其轮播图图片为空
		if(clients.contains(BusinessConstant.WEB_CLIENT_ID) && EmptyUtil.isEmpty(webBannerPictures))
			throw new BusinessException("商品web端轮播图不能为空");
		
		
		return merchantProductWriteManage.updateMerchantProductWithTx(
				MerchantProductConverter.toPO(dto),keywords,clients,tagList,companys,demoCompanys,competingCompanys,webBannerPictures,
				storeIdList,membershipIdList);
	}

	@Override
	public int deleteMerchantProductWithTx(MerchantProductDTO dto) {
		MerchantProductPO po = MerchantProductConverter.toPO(dto);
		int rt = merchantProductWriteManage.deleteMerchantProductWithTx(po);
		return rt;
	}

	/**
	 * 提交审核
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public int submitAuditWithTx(MerchantProductDTO dto) {
		// TODO Auto-generated method stub
		return merchantProductWriteManage.submitAuditWithTx(MerchantProductConverter.toPO(dto));
	}

	/**
	 * 修改su草稿状态为已上架
	 * 
	 * @param merchantProductDTO
	 * @return
	 */
	@Override
	public int updateStatusWithTx(MerchantProductDTO merchantProductDTO) {
		// TODO Auto-generated method stub
		return merchantProductWriteManage.updateStatus(MerchantProductConverter.toPO(merchantProductDTO));
	}

	@Override
	public int updateMerchantProductPictureByIdWithTx(
			Long merchantProductId, 
			String picture,
			List<String> pictureVOList,
			List<String> webBannerPictures, 
			MerchantProductDTO merchantProductDTO) {
		
		return merchantProductWriteManage.updateMerchantProductPictureByIdWithTx(merchantProductId, picture, pictureVOList, webBannerPictures, MerchantProductConverter.toPO(merchantProductDTO)); 
	}

	/**
	 * 根据su草稿id更新supu信息
	 *
	 * @param merchantProductId
	 * @param picture
	 * @param pictureList
	 * @return
	 */
	@Override
	public int updateProductUnitByIdWithTx(List<ProductUnitDTO> dto, MerchantProductDTO merchantProductDTO,
										   List<Long> companyIds,List<Long> demoCompanyIds,List<Long> competingCompanyIds) {
		Integer i = 0;
		for (ProductUnitDTO productUnitDTO : dto) {
			if (productUnitDTO.isChecked()) {
				if (EmptyUtil.isNotEmpty(companyIds) ){
					if (EmptyUtil.isEmpty(productUnitDTO.getSalePrice())
							|| productUnitDTO.getSalePrice().doubleValue() <= 0) {
						throw new BusinessException("pu设置为有效后正式公司售价不能为空或小于0");
					}
				}
				if (EmptyUtil.isNotEmpty(demoCompanyIds) ){
					if (EmptyUtil.isEmpty(productUnitDTO.getDemoSalePrice())
							|| productUnitDTO.getDemoSalePrice().doubleValue() <= 0) {
						throw new BusinessException("pu设置为有效后演示公司售价不能为空或小于0");
					}
				}
				if (EmptyUtil.isNotEmpty(competingCompanyIds) ){
					if (EmptyUtil.isEmpty(productUnitDTO.getCompetingSalePrice())
							|| productUnitDTO.getCompetingSalePrice().doubleValue() <= 0) {
						throw new BusinessException("pu设置为有效后竞品公司售价不能为空或小于0");
					}
				}
				if (EmptyUtil.isNotEmpty(productUnitDTO.getName())) {
					logger.info("puName不为空:"+productUnitDTO.getName());
					Double length = getLength(productUnitDTO.getName());
					/*if (length > 30) {
						throw new BusinessException("pu名称不能超过30字");
					}*/
				} else {
					throw new BusinessException("pu名称不能为空");
				}
				productUnitDTO.setIsVendible(1);
			} else {
				productUnitDTO.setIsVendible(0);
			}

			i = i + productUnitWriteService.updateProductUnitWithTx(productUnitDTO);
		}
		if (i > 0) {
			merchantProductWriteManage.updateStatus(MerchantProductConverter.toPO(merchantProductDTO));
		}

		return i;
	}

	private double getLength(String str) {
		double valueLength = 0;
		String chinese = "[\\\\u4e00-\\\\u9fa5]";
		for (int i = 0; i < str.length(); i++) {
			// 获取一个字符
			String temp = str.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
			// 中文字符长度为1
				valueLength += 1;
			} else {
			// 其他字符长度为0.5
				valueLength += 0.5;
			}
		}
		//进位取整
		return Math.ceil(valueLength);
	}


	/**
	 * 根据su商品id通过审核
	 */
	@Override
	public List<Long> merchantProductPassWithTx(Long standardUnitId,Long platformId) {
		// 审核通过后会返回pu草稿id集合
		List<Long> puIdlist = merchantProductWriteManage.merchantProductPassWithTx(standardUnitId);
		List<Long> puIds = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(puIdlist))
			puIds = commodityProductUnitReadManage.findIdsByPUIds(puIdlist);
		// 如果不为空根据pu草稿id集合查询正式puId
		if(EmptyUtil.isNotEmpty(puIdlist)){
			// 根据平台查询所有门店
			List<Long>  storeIds = storeTreeNodeReadManage.findStoreTreeNodeAllByPlatformId(platformId);
			// 门店关系不为空则生成门店pu
			if(EmptyUtil.isNotEmpty(storeIds)){
				List<StoreProductUnitPO> list = new ArrayList<>();
				for (Long storeId : storeIds) {
					for (Long puId : puIds) {
						StoreProductUnitPO storeProductUnitPO = new StoreProductUnitPO();
						storeProductUnitPO.setStoreId(storeId);
						storeProductUnitPO.setStandardUnitId(standardUnitId);
						storeProductUnitPO.setCommodityProductUnitId(puId);
						// 默认上架（本版本废弃，后续版本开发）
						storeProductUnitPO.setStatus(3);
						storeProductUnitPO.setPlatformId(platformId);
						list.add(storeProductUnitPO);
					}
				}
				// 批量生产门店pu
				if(EmptyUtil.isNotEmpty(list))
					storeProductUnitWriteManage.insertAllWithTx(list);
			}
			return puIds;
		}
		return null;
	}





	@Override
	public void saveMerchantProduct(List<Integer> profitList, List<Long> merchantProductIdList, List<Long> spuIdList, List<String> merchantProductSerialNumberList,
									List<String> productCategoryList, List<String> nameList, List<BigDecimal> salePriceList,
									List<BigDecimal> marketPriceList,
									List<BigDecimal> demoSalePriceList,
									List<BigDecimal> competingSalePriceList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProductPO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++){
			MerchantProductPO merchantProductPO = new MerchantProductPO();
			merchantProductPO.setId(merchantProductIdList.get(i));
			merchantProductPO.setMerchantProductSerialNumber(merchantProductSerialNumberList.get(i));
			merchantProductPO.setMerchantId(6L);
			merchantProductPO.setStandardProductUnitId(spuIdList.get(i));
			merchantProductPO.setProductCategory(productCategoryList.get(i));
			merchantProductPO.setName(nameList.get(i));
			merchantProductPO.setIsVisible(0);
			merchantProductPO.setSalePrice(salePriceList.get(i));
			merchantProductPO.setMarketPrice(marketPriceList.get(i));
			merchantProductPO.setStatus(3);
			merchantProductPO.setSaleWay(1);
			merchantProductPO.setFreightExplain("以确认订单页面计算的实际运费为准");
			merchantProductPO.setShipmentsExplain("以商品运营方的实际发货时间为准");
			merchantProductPO.setPlatformId(7L);
			merchantProductPO.setIsSpuKeyword(0);
			merchantProductPO.setDemoSalePrice(demoSalePriceList.get(i));
			merchantProductPO.setCompetingSalePrice(competingSalePriceList.get(i));
			merchantProductPO.setStoreId(1L);
			merchantProductPO.setBuyType(1);
			merchantProductPO.setFrontSerialNumber(99999l);
			merchantProductPO.setJdProfit(profitList.get(i));
			res.add(merchantProductPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantProduct保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantProduct",24*60*60,page);
		}catch (Exception e){
			logger.info("[MerchantProduct]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantProductPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					list.add(res.get(j));
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setLatch(countDownLatch);
			saveListFactory.setSaveType("MerchantProduct");
			saveListFactory.setMerchantProductPOList(list);
			saveListFactory.setMerchantProductWriteManage(merchantProductWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}

	@Override
	public void updateMerchantProductPrice(List<Integer> profits, List<Integer> statusList, List<Integer> isVisibleList, List<Long> merchantProductIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProductPO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++){
			MerchantProductPO po = new MerchantProductPO();
			po.setId(merchantProductIdList.get(i));
			po.setMarketPrice(marketPriceList.get(i));
			po.setSalePrice(salePriceList.get(i));
			po.setDemoSalePrice(demoPriceList.get(i));
			po.setCompetingSalePrice(competingPriceList.get(i));
			po.setStatus(statusList.get(i));
			po.setIsVisible(isVisibleList.get(i));
			po.setJdProfit(profits.get(i));
			res.add(po);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateMerchantProductPrice保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<MerchantProductPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * 10000; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * 10000; j < (i + 1) * 10000; j++) {
					list.add(res.get(j));
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setLatch(countDownLatch);
			saveListFactory.setSaveType("updateMerchantProductPrice");
			saveListFactory.setMerchantProductPricePOList(list);
			saveListFactory.setMerchantProductWriteManage(merchantProductWriteManage);
			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		merchantProductWriteManage.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public int updateMerchantProductVisible(Long standardUnitId, Integer status) {
		return merchantProductWriteManage.updateMerchantProductVisible(standardUnitId,status);
	}

	@Override
	public void updateMerchantProductList(List<Integer> profitList, List<Long> merchantProductIdList, List<String> nameList, List<BigDecimal> marketPriceList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProductPO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++){
			MerchantProductPO po = new MerchantProductPO();
			po.setId(merchantProductIdList.get(i));
			po.setName(nameList.get(i));
			po.setMarketPrice(marketPriceList.get(i));
			po.setSalePrice(salePriceList.get(i));
			po.setDemoSalePrice(demoSalePriceList.get(i));
			po.setCompetingSalePrice(competingSalePriceList.get(i));
			po.setJdProfit(profitList.get(i));
			res.add(po);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateMerchantProductPrice保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<MerchantProductPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * 10000; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * 10000; j < (i + 1) * 10000; j++) {
					list.add(res.get(j));
				}
			}
			UpdateListFactory factory = new UpdateListFactory();
			factory.setLatch(countDownLatch);
			factory.setSaveType("updateMerchantProductPrice");
			factory.setMerchantProductPOList(list);
			factory.setMerchantProductWriteManage(merchantProductWriteManage);
			executor.execute(factory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateSuAndPuStatusByJd() {
		merchantProductWriteManage.updateSuAndPuStatusByJd();
	}

	@Override
	public void updateJdProductStatusByProfit(Integer productLimitProfit) {
		merchantProductWriteManage.updateJdProductStatusByProfit( productLimitProfit);
	}




}
