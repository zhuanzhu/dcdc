package com.egeo.components.product.service.write.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.JdProductConverter;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.manage.write.JdProductWriteManage;
import com.egeo.components.product.po.JdProductPO;
import com.egeo.components.product.service.write.JdProductWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.log.XLogger;

@Service("jdProductWriteService")
public class JdProductWriteServiceImpl  implements JdProductWriteService {
	private static final XLogger logger = XLogger.getLogger(JdProductWriteServiceImpl.class);
	@Autowired
	private JdProductWriteManage jdProductWriteManage;







	@Override
	public Long insertJdProductWithTx(JdProductDTO dto) {
		JdProductPO po = JdProductConverter.toPO(dto);
		Long rt = jdProductWriteManage.insertJdProductWithTx(po);
		return rt;
	}

	@Override
	public int updateJdProductWithTx(JdProductDTO dto) {
		JdProductPO po = JdProductConverter.toPO(dto);
		int rt = jdProductWriteManage.updateJdProductWithTx(po);
		return rt;
	}

	@Override
	public int deleteJdProductWithTx(JdProductDTO dto) {
		JdProductPO po = JdProductConverter.toPO(dto);
		int rt = jdProductWriteManage.deleteJdProductWithTx(po);
		return rt;
	}

	@Override
	public void updateSyncStatus(JdProductDTO dto) {
		jdProductWriteManage.updateSyncStatus(JdProductConverter.toPO(dto));
	}

	@Override
	public void saveJdProductListFirst(List<JdProductDTO> jdProductDTOList){
		ExecutorService executor = Executors.newFixedThreadPool(100);

		CountDownLatch latch = new CountDownLatch(1);
		int page=((jdProductDTOList.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<JdProductDTO> jdProductDTOListChild = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * 10000; j < jdProductDTOList.size(); j++) {
					jdProductDTOListChild.add(jdProductDTOList.get(j));
				}
			} else {
				for (int j = i * 10000; j < (i + 1) * 10000; j++) {
					jdProductDTOListChild.add(jdProductDTOList.get(j));
				}
			}
			logger.info("开始执行京东商品保存线程");
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setSaveType("JdProductListFirst");
			saveListFactory.setJdProductPOList(JdProductConverter.toPO(jdProductDTOListChild));
			saveListFactory.setJdProductWriteManage(jdProductWriteManage);
			saveListFactory.setLatch(latch);
			executor.execute(saveListFactory);
		}
		latch.countDown();
		executor.shutdown();
		logger.info("主线程完成");
	}

	@Override
	public void setAllSyncStatus(int status) {
		jdProductWriteManage.setAllSyncStatus(status);

	}
	@Override
	public void updateJdProductList(List<JdProductDTO> updateList){
		CountDownLatch latch = new CountDownLatch(1);
		SaveListFactory saveListFactory = new SaveListFactory();
		saveListFactory.setSaveType("updateJdProductList");
		saveListFactory.setUpdateList(JdProductConverter.toPO(updateList));
		saveListFactory.setLatch(latch);
		saveListFactory.setJdProductWriteManage(jdProductWriteManage);
		Thread thread = new Thread(saveListFactory);
		thread.start();
		latch.countDown();
	}

	@Override
	public void updateJdProductPrice(List<Integer> profitList, List<Long> skuIdList, List<BigDecimal> priceList, List<BigDecimal> marketPrices) {
		ExecutorService executor = Executors.newFixedThreadPool(100);

		int page=((skuIdList.size()-1)/10000)+1;
		CountDownLatch latch = new CountDownLatch(1);

		for(int i=0;i<page;i++) {
			List<JdProductDTO> jdProductDTOList= new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * 10000; j < skuIdList.size(); j++) {
					JdProductDTO dto = new JdProductDTO();
					dto.setId(skuIdList.get(j));
					dto.setPrice(priceList.get(j));
					dto.setMarketPrice(marketPrices.get(j));
					dto.setProfit(profitList.get(j));
					jdProductDTOList.add(dto);
				}
			} else {
				for (int j = i * 10000; j < (i + 1) * 10000; j++) {
					JdProductDTO dto = new JdProductDTO();
					dto.setId(skuIdList.get(j));
					dto.setPrice(priceList.get(j));
					dto.setMarketPrice(marketPrices.get(j));
					dto.setProfit(profitList.get(j));
					jdProductDTOList.add(dto);
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setSaveType("updateJdProductPrice");
			saveListFactory.setJdProductPOList(JdProductConverter.toPO(jdProductDTOList));
			saveListFactory.setJdProductWriteManage(jdProductWriteManage);
			saveListFactory.setLatch(latch);
			executor.execute(saveListFactory);
		}
		latch.countDown();
		executor.shutdown();


		/*

		SaveListFactory saveListFactory = new SaveListFactory();
		saveListFactory.setSaveType("updaateJdProductPrice");
		saveListFactory.setUpdateList(JdProductConverter.toPO(updateList));
		saveListFactory.setLatch(latch);
		saveListFactory.setJdProductWriteManage(jdProductWriteManage);
		Thread thread = new Thread(saveListFactory);
		thread.start();
		latch.countDown();*/
	}

	@Override
	public void updateProductCreateTime(List<Long> jdId) {
		ExecutorService executor = Executors.newFixedThreadPool(100);

		int page=((jdId.size()-1)/10000)+1;
		CountDownLatch latch = new CountDownLatch(1);

		for(int i=0;i<page;i++) {
			List<Long> list= new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * 10000; j < jdId.size(); j++) {
					list.add(jdId.get(j));
				}
			} else {
				for (int j = i * 10000; j < (i + 1) * 10000; j++) {
					list.add(jdId.get(j));
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setSaveType("updateJdProductProductCreateTime");
			saveListFactory.setJdProductIdList(list);
			saveListFactory.setJdProductWriteManage(jdProductWriteManage);
			saveListFactory.setLatch(latch);
			executor.execute(saveListFactory);
		}
		latch.countDown();
		executor.shutdown();

	}

	/*@Override
	public void updateJdProductList(List<Integer> isShowList, List<Long> jdProductIdList) {
		List<JdProductDTO> res = new ArrayList<>();
		for(int i=0;i<isShowList.size();i++){
			JdProductDTO dto = new JdProductDTO();
			dto.setId(jdProductIdList.get(i));
			dto.setIsShow(isShowList.get(i));
			dto.setProductCreateTime(new Date());
			res.add(dto);
		}
		CountDownLatch latch = new CountDownLatch(1);
		saveListFactory.setSaveType("updateJdProductCreateList");
		saveListFactory.setUpdateJdProductCreateList(JdProductConverter.toPO(res));
		saveListFactory.setLatch(latch);
		Thread thread = new Thread(saveListFactory);
		thread.start();
		latch.countDown();


	}*/

	//------------------------------------------第二部分(创建正式spu,sku)




















//-----------------------------------第三部分



























	/**
 	 * @param suIdList
	 * @param spuIdList
	 */










/*	public void test(){
		//--------------------第一步操作的数据库
		//spu草稿(插入后获得spuId)
		ProductPO productPO = new ProductPO();
		productPO.setProductSerialNumber();
		productPO.setProductCategory();
		productPO.setCategoryTreeNodeId();
		productPO.setName();
		productPO.setStatus();
		productPO.setIsAvailable();
		productPO.setPlatformId();
		productPO.setCommodityTemplateId();
		//picture
		PicturePO picture= new PicturePO();
		picture.setName();
		picture.setUrl();
		picture.setSortValue(0);
		picture.setType();
		picture.setPlatformId();

		ProductAttNamePO productAttNamePO = new ProductAttNamePO();
		productAttNamePO.setProductId();
		productAttNamePO.setAttNameId();
		productAttNamePO.setSortValue();
		productAttNamePO.setType();
		productAttNamePO.setPlatformId();
		productAttNamePO.setShowPicture();


		ProductAttValuePO productAttValuePO = new ProductAttValuePO();
		productAttValuePO.setProductAttNameId();
		productAttValuePO.setAttValueId();
		productAttValuePO.setSortValue();
		productAttValuePO.setPictureUrl();
		productAttValuePO.setPlatformId();

		ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO();
		productDescriptionPO.setProductId();
		productDescriptionPO.setContent();
		productDescriptionPO.setPlatformId();


		ProductPicturePO productPicturePO = new ProductPicturePO();
		productPicturePO.setProductId();
		productPicturePO.setType();
		productPicturePO.setPictureId();
		productPicturePO.setPlatformId();

		//--------------第二步操作的是数据库
		//spu
		StandardProductUnitPO spu = new StandardProductUnitPO();
		spu.setId();
		spu.setProductSerialNumber();
		spu.setProductCategory();
		spu.setCategoryTreeNodeId();
		spu.setName();
		spu.setStatus();
		spu.setIsAvailable();
		spu.setPlatformId();
		spu.setCommodityTemplateId();
		//spu-att-name(多个)
		StandardProductUnitAttNamePO spuAttName = new StandardProductUnitAttNamePO();
		spuAttName.setStandardProductUnitId();
		spuAttName.setAttNameId();
		spuAttName.setSortValue(0);
		spuAttName.setType();
		spuAttName.setPlatformId();
		spuAttName.setShowPicture();
		//spu-att_value(多个)
		StandardProductUnitAttValuePO spuValuePO = new StandardProductUnitAttValuePO();
		spuValuePO.setStandardProductUnitAttNameId();
		spuValuePO.setAttValueId();
		spuValuePO.setSortValue(0);
		spuValuePO.setPlatformId();
		//spu-descrition
		StandardProductUnitDescriptionPO descriptionPO = new StandardProductUnitDescriptionPO();
		descriptionPO.setStandardProductUnitId();
		descriptionPO.setContent();
		descriptionPO.setPlatformId();
		//spu-picture
		StandardProductUnitPicturePO picturePO = new StandardProductUnitPicturePO();
		picturePO.setStandardProductUnitId();
		picturePO.setPictureId();
		picturePO.setType();
		picturePO.setSortValue(0);
		picturePO.setPlatformId();



		//创建sku
		SkuPO skuPO = new SkuPO();
		skuPO.setSkuSerialNumber();
		skuPO.setMerchantId();
		skuPO.setStandardProductUnitId();
		skuPO.setSkuName();
		skuPO.setIsAvailable();
		skuPO.setIsValid();
		skuPO.setCode();
		skuPO.setPlatformId();
		skuPO.setExternalSkuId();

		//sku-attname
		SkuAttNamePO skuAttNamePO = new SkuAttNamePO();
		skuAttNamePO.setSkuId();
		skuAttNamePO.setAttNameId();
		skuAttNamePO.setSortValue(0);
		//sku-attvalue
		SkuAttValuePO skuAttValuePO = new SkuAttValuePO();
		skuAttValuePO.setSkuAttNameId();
		skuAttValuePO.setAttValueId();
		skuAttValuePO.setSortValue(0);
		//-----------------------第三步操作的数据库
		MerchantProductPO merchantProductPO = new MerchantProductPO();
		merchantProductPO.setMerchantProductSerialNumber();
		merchantProductPO.setMerchantId();
		merchantProductPO.setStandardProductUnitId();
		merchantProductPO.setProductCategory();
		merchantProductPO.setName();
		merchantProductPO.setIsVisible();
		merchantProductPO.setType();
		merchantProductPO.setSalePrice();
		merchantProductPO.setMarketPrice();
		merchantProductPO.setPromotionPrice();
		merchantProductPO.setStatus();
		merchantProductPO.setSaleWay();
		merchantProductPO.setFreightExplain();
		merchantProductPO.setShipmentsExplain();
		merchantProductPO.setPlatformId();
		merchantProductPO.setCreateUserid();
		merchantProductPO.setCreateUsername();
		merchantProductPO.setCreateUserip();
		merchantProductPO.setUpdateUserid();
		merchantProductPO.setUpdateUserip();
		merchantProductPO.setUpdateUsername();
		merchantProductPO.setIsSpuKeyword();
		merchantProductPO.setDemoSalePrice();
		merchantProductPO.setCompetingSalePrice();
		merchantProductPO.setStoreId();
		merchantProductPO.setPresellPeriod();
		merchantProductPO.setBuyType();
		merchantProductPO.setFrontSerialNumber();


		MerchantProdClientPO merchantProdClientPO = new MerchantProdClientPO();
		merchantProdClientPO.setMerchantProductId();
		merchantProdClientPO.setClientId();

		MerchantProdDescribePO merchantProdDescribePO = new MerchantProdDescribePO();
		merchantProdDescribePO.setContent();
		merchantProdDescribePO.setPlatformId();
		merchantProdDescribePO.setMerchantProductId();


		MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
		merchantProdPicturePO.setMerchantPictureId();
		merchantProdPicturePO.setType();
		merchantProdPicturePO.setPlatformId();
		merchantProdPicturePO.setMerchantProdId();

		MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
		merchantProductCompanyPO.setMerchantProductId();
		merchantProductCompanyPO.setCompanyId();
		merchantProductCompanyPO.setCompanyType();



		MerchantProductStorePO merchantProductStorePO = new MerchantProductStorePO();
		merchantProductStorePO.setStoreId();
		merchantProductStorePO.setMerchantProductId();
		merchantProductStorePO.setPlatformId();


		MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
		merchantProductTagPO.setMerchantProductId();
		merchantProductTagPO.setTagId();



		//--------------------第四部数据库操作
		StandardUnitPO standardUnitPO = new StandardUnitPO();
		standardUnitPO.setId();
		standardUnitPO.setMerchantSeriesId();
		standardUnitPO.setMerchantId();
		standardUnitPO.setStandardProductUnitId();
		standardUnitPO.setProductCategory();
		standardUnitPO.setName();
		standardUnitPO.setIsVisible();
		standardUnitPO.setType();
		standardUnitPO.setSalePrice();
		standardUnitPO.setMarketPrice();
		standardUnitPO.setPromotionPrice();
		standardUnitPO.setStatus();
		standardUnitPO.setSaleWay();
		standardUnitPO.setFreightExplain();
		standardUnitPO.setShipmentsExplain();
		standardUnitPO.setPlatformId();
		standardUnitPO.setCreateUserid();
		standardUnitPO.setCreateUsername();
		standardUnitPO.setCreateUserip();
		standardUnitPO.setUpdateUserid();
		standardUnitPO.setUpdateUserip();
		standardUnitPO.setUpdateUsername();
		standardUnitPO.setIsSpuKeyword();
		standardUnitPO.setDemoSalePrice();
		standardUnitPO.setCompetingSalePrice();
		standardUnitPO.setStoreId();
		standardUnitPO.setPresellPeriod();
		standardUnitPO.setBuyType();
		standardUnitPO.setFrontSerialNumber();

		StandardUnitDescribePO standardUnitDescribePO = new StandardUnitDescribePO();
		standardUnitDescribePO.setContent();
		standardUnitDescribePO.setPlatformId();
		standardUnitDescribePO.setStandardUnitId();
		standardUnitDescribePO.setContentUrl();

		StandardUnitPicturePO standardUnitPicturePO = new StandardUnitPicturePO();
		standardUnitPicturePO.setMerchantPictureId();
		standardUnitPicturePO.setType();
		standardUnitPicturePO.setPlatformId();
		standardUnitPicturePO.setStandardUnitId();

		StandardUnitStorePO standardUnitStorePO = new StandardUnitStorePO();
		standardUnitStorePO.setStoreId();
		standardUnitStorePO.setStandardUnitId();
		standardUnitStorePO.setPlatformId();

		StandardUnitTagPO standardUnitTagPO = new StandardUnitTagPO();
		standardUnitTagPO.setStandardUnitId();
		standardUnitTagPO.setTagId();


		StandardUnitCompanyPO standardUnitCompanyPO = new StandardUnitCompanyPO();
		standardUnitCompanyPO.setStandardUnitId();
		standardUnitCompanyPO.setCompanyId();
		standardUnitCompanyPO.setCompanyType();

		StandardUnitClientPO standardUnitClientPO = new StandardUnitClientPO();
		standardUnitClientPO.setStandardUnitId();
		standardUnitClientPO.setClientId();


		CommodityProductUnitPO pu= new CommodityProductUnitPO();
		pu.setProductUnitSerialNumber();
		pu.setProductUnitId();
		pu.setSkuId();
		pu.setStandardUnitId();
		pu.setName();
		pu.setSalePrice();
		pu.setStatus();
		pu.setIsVendible();
		pu.setCode();
		pu.setPuPicUrl();
		pu.setPlatformId();
		pu.setDemoSalePrice();
		pu.setCompetingSalePrice();

		//库存
		CommodityProductUnitWarehouseStock stock = new CommodityProductUnitWarehouseStock();
		stock.setCommodityProductUnitId();
		stock.setStandardUnitId():
		stock.setMerchantId();
		stock.setMerchantProductId();
		stock.setRelStockNum();
		stock.setRelFrozenStockNum();
		stock.setPlatformId();


	}*/

}
	