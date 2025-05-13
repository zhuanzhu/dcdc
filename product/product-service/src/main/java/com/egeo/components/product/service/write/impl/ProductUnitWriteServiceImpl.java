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

import com.egeo.components.product.converter.ProductUnitConverter;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.manage.write.ProductUnitWriteManage;
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.components.product.service.write.ProductUnitWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("productUnitWriteService")
public class ProductUnitWriteServiceImpl  implements ProductUnitWriteService {
	private static final XLogger logger = XLogger.getLogger(ProductUnitWriteServiceImpl.class);
	@Autowired
	private ProductUnitWriteManage productUnitWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertProductUnitWithTx(ProductUnitDTO dto) {
		ProductUnitPO po = ProductUnitConverter.toPO(dto);
		Long rt = productUnitWriteManage.insertProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateProductUnitWithTx(ProductUnitDTO dto) {
		ProductUnitPO po = ProductUnitConverter.toPO(dto);
		int rt = productUnitWriteManage.updateProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteProductUnitWithTx(ProductUnitDTO dto) {
		ProductUnitPO po = ProductUnitConverter.toPO(dto);
		int rt = productUnitWriteManage.deleteProductUnitWithTx(po);		
		return rt;
	}



	@Override
	public void saveProductUnit(List<Long> productUnitIdList, List<String> productUnitSerialNumberList, List<Long> skuIdList, List<Long> merchantProductIdList,
								List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<ProductUnitPO> res=new ArrayList<>();
		for(int i=0;i<productUnitIdList.size();i++){
			ProductUnitPO productUnitPO = new ProductUnitPO();
			productUnitPO.setId(productUnitIdList.get(i));
			productUnitPO.setProductUnitSerialNumber(productUnitSerialNumberList.get(i));
			productUnitPO.setSkuId(skuIdList.get(i));
			productUnitPO.setMerchantProductId(merchantProductIdList.get(i));
			productUnitPO.setName(nameList.get(i));
			productUnitPO.setSalePrice(salePriceList.get(i));
			productUnitPO.setStatus(3);
			productUnitPO.setIsVendible(1);
			productUnitPO.setPlatformId(7L);
			productUnitPO.setDemoSalePrice(demoSalePriceList.get(i));
			productUnitPO.setCompetingSalePrice(competingSalePriceList.get(i));
			res.add(productUnitPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理ProductUnit保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);



		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("ProductUnit",24*60*60,page);
		}catch (Exception e){
			logger.info("[ProductUnit]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<ProductUnitPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("ProductUnit");
			saveListFactory.setProductUnitPOList(list);
			saveListFactory.setProductUnitWriteManage(productUnitWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();

	}

	@Override
	public void updateProductUnitPrice(List<Long> productUnitIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<ProductUnitPO> res=new ArrayList<>();
		for(int i=0;i<productUnitIdList.size();i++){
			ProductUnitPO productUnitPO = new ProductUnitPO();
			productUnitPO.setId(productUnitIdList.get(i));
			productUnitPO.setSalePrice(salePriceList.get(i));
			productUnitPO.setDemoSalePrice(demoPriceList.get(i));
			productUnitPO.setCompetingSalePrice(competingPriceList.get(i));
			res.add(productUnitPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateProductUnitPrice保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<ProductUnitPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("updateProductUnitPrice");
			saveListFactory.setProductUnitPricePOList(list);
			saveListFactory.setProductUnitWriteManage(productUnitWriteManage);
			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		productUnitWriteManage.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public void updateProductUnitList(List<Long> productUnitIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<ProductUnitPO> res=new ArrayList<>();
		for(int i=0;i<productUnitIdList.size();i++){
			ProductUnitPO productUnitPO = new ProductUnitPO();
			productUnitPO.setName(nameList.get(i));
			productUnitPO.setId(productUnitIdList.get(i));
			productUnitPO.setSalePrice(salePriceList.get(i));
			productUnitPO.setDemoSalePrice(demoSalePriceList.get(i));
			productUnitPO.setCompetingSalePrice(competingSalePriceList.get(i));
			res.add(productUnitPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateProductUnit保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<ProductUnitPO> list = new ArrayList<>();
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
			factory.setSaveType("updateProductUnit");
			factory.setProductUnitPOList(list);
			factory.setProductUnitWriteManage(productUnitWriteManage);
			executor.execute(factory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}
}
	