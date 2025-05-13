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

import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.manage.write.StandardUnitWriteManage;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.service.write.StandardUnitWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardUnitWriteService")
public class StandardUnitWriteServiceImpl  implements StandardUnitWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardUnitWriteServiceImpl.class);
	@Autowired
	private StandardUnitWriteManage standardUnitWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardUnitWithTx(StandardUnitDTO dto) {
		StandardUnitPO po = StandardUnitConverter.toPO(dto);
		Long rt = standardUnitWriteManage.insertStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitWithTx(StandardUnitDTO dto) {
		StandardUnitPO po = StandardUnitConverter.toPO(dto);
		int rt = standardUnitWriteManage.updateStandardUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitWithTx(StandardUnitDTO dto) {
		StandardUnitPO po = StandardUnitConverter.toPO(dto);
		int rt = standardUnitWriteManage.deleteStandardUnitWithTx(po);		
		return rt;
	}
	/**
	 * su上下架
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int putawaySoldOutWithTx(StandardUnitDTO dto,int type) {
		return standardUnitWriteManage.putawaySoldOutWithTx(StandardUnitConverter.toPO(dto),type);
	}





	@Override
	public void saveStandardUnit(List<Integer> profitList, List<Long> suIdList, List<Long> spuIdList, List<String> merchantProductSerialNumberList, List<String> productCategoryList,
								 List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> marketPriceList, List<BigDecimal> demoSalePriceList,
								 List<BigDecimal> competingSalePriceList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitPO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++){

			StandardUnitPO standardUnitPO = new StandardUnitPO();
			standardUnitPO.setId(suIdList.get(i));
			standardUnitPO.setMerchantProductSerialNumber(merchantProductSerialNumberList.get(i));
			//6--电商特供
			standardUnitPO.setMerchantId(6L);
			standardUnitPO.setStandardProductUnitId(spuIdList.get(i));
			standardUnitPO.setProductCategory(productCategoryList.get(i));
			standardUnitPO.setName(nameList.get(i));
			standardUnitPO.setIsVisible(0);
			standardUnitPO.setType(0);
			standardUnitPO.setSalePrice(salePriceList.get(i));
			standardUnitPO.setMarketPrice(marketPriceList.get(i));
			standardUnitPO.setStatus(3);
			standardUnitPO.setSaleWay(1);
			standardUnitPO.setFreightExplain("以确认订单页面计算的实际运费为准");
			standardUnitPO.setShipmentsExplain("以商品运营方的实际发货时间为准");
			standardUnitPO.setPlatformId(7L);
			standardUnitPO.setIsSpuKeyword(0);
			standardUnitPO.setDemoSalePrice(demoSalePriceList.get(i));
			standardUnitPO.setCompetingSalePrice(competingSalePriceList.get(i));
			standardUnitPO.setStoreId(1L);
			standardUnitPO.setBuyType(1);
			standardUnitPO.setFrontSerialNumber(99999l);
			standardUnitPO.setJdProfit(profitList.get(i));
			res.add(standardUnitPO);

		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardUnit保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);



		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardUnit",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardUnit]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardUnitPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardUnit");
			saveListFactory.setStandardUnitPOList(list);
			saveListFactory.setStandardUnitWriteManage(standardUnitWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}

	@Override
	public void updateStandardUnitPrice(List<Integer> profits, List<Integer> statusList, List<Integer> isVisibleList, List<Long> standardUnitIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList) {
		List<StandardUnitPO> res = new ArrayList<>();
		for(int i=0;i<standardUnitIdList.size();i++){

			StandardUnitPO standardUnitPO = new StandardUnitPO();
			standardUnitPO.setId(standardUnitIdList.get(i));
			standardUnitPO.setSalePrice(salePriceList.get(i));
			standardUnitPO.setMarketPrice(marketPriceList.get(i));
			standardUnitPO.setDemoSalePrice(demoPriceList.get(i));
			standardUnitPO.setCompetingSalePrice(competingPriceList.get(i));
			standardUnitPO.setStatus(statusList.get(i));
			standardUnitPO.setIsVisible(isVisibleList.get(i));
			standardUnitPO.setJdProfit(profits.get(i));
			res.add(standardUnitPO);

		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateStandardUnitPrice保存22222");
		int page=((res.size()-1)/100)+1;

		for(int i=0;i<page;i++) {
			List<StandardUnitPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * 100; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * 100; j < (i + 1) * 100; j++) {
					list.add(res.get(j));
				}
			}
			standardUnitWriteManage.updateStandardUnitPrice(list);
		}
		logger.info("更新su价格成功");
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		standardUnitWriteManage.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public int updateSuVisible(StandardUnitDTO standardUnitDTO) {
		return standardUnitWriteManage.updateStandardUnitWithTx(StandardUnitConverter.toPO(standardUnitDTO));
	}

	@Override
	public void updateSUList(List<Integer> profitList, List<Long> suIdList, List<String> nameList, List<BigDecimal> marketPriceList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitPO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++){

			StandardUnitPO standardUnitPO = new StandardUnitPO();
			standardUnitPO.setId(suIdList.get(i));
			standardUnitPO.setName(nameList.get(i));
			standardUnitPO.setSalePrice(salePriceList.get(i));
			standardUnitPO.setMarketPrice(marketPriceList.get(i));
			standardUnitPO.setDemoSalePrice(demoSalePriceList.get(i));
			standardUnitPO.setCompetingSalePrice(competingSalePriceList.get(i));
			standardUnitPO.setJdProfit(profitList.get(i));
			res.add(standardUnitPO);

		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateStandardUnit保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<StandardUnitPO> list = new ArrayList<>();
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
			factory.setSaveType("updateStandardUnitList");
			factory.setStandardUnitPOList(list);
			factory.setStandardUnitWriteManage(standardUnitWriteManage);
			executor.execute(factory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateSuAndPuStatusByJd() {
		standardUnitWriteManage.updateSuAndPuStatusByJd();
	}

	@Override
	public void updateJdProductStatusByProfit(Integer productLimitProfit) {
		standardUnitWriteManage.updateJdProductStatusByProfit(productLimitProfit);
	}
}
	