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

import com.egeo.components.product.converter.CommodityProductUnitConverter;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.manage.write.CommodityProductUnitWriteManage;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.service.write.CommodityProductUnitWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("commodityProductUnitWriteService")
public class CommodityProductUnitWriteServiceImpl  implements CommodityProductUnitWriteService {
	private static final XLogger logger = XLogger.getLogger(CommodityProductUnitWriteServiceImpl.class);
	@Autowired
	private CommodityProductUnitWriteManage commodityProductUnitWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
		Long rt = commodityProductUnitWriteManage.insertCommodityProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
		int rt = commodityProductUnitWriteManage.updateCommodityProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
		int rt = commodityProductUnitWriteManage.deleteCommodityProductUnitWithTx(po);		
		return rt;
	}
	/**
	 * 根据pu草稿id修改pu信息
	 * @param commodityProductUnitDTO
	 * @return
	 */
	@Override
	public int updateCommodityProductUnitByProductUnitIdWithTx(CommodityProductUnitDTO commodityProductUnitDTO) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteManage.updateCommodityProductUnitByProductUnitIdWithTx(CommodityProductUnitConverter.toPO(commodityProductUnitDTO));
	}




	@Override
	public void saveCommodityProductUnit(List<Long> jdSpuIdList,List<Long> puIdList, List<String> productUnitSerialNumberList, List<Long> productUnitIdList, List<Long> skuIdList,
										 List<Long> suIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList,
										 List<BigDecimal> competingSalePriceList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<CommodityProductUnitPO> res = new ArrayList<>();
		for(int i=0;i<puIdList.size();i++){
			CommodityProductUnitPO pu = new CommodityProductUnitPO();
			pu.setId(puIdList.get(i));
			pu.setProductUnitSerialNumber(productUnitSerialNumberList.get(i));
			pu.setProductUnitId(productUnitIdList.get(i));
			pu.setSkuId(skuIdList.get(i));
			pu.setStandardUnitId(suIdList.get(i));
			pu.setName(nameList.get(i));
			pu.setSalePrice(salePriceList.get(i));
			pu.setStatus(3);
			pu.setIsVendible(1);
			pu.setPlatformId(7L);
			pu.setDemoSalePrice(demoSalePriceList.get(i));
			pu.setCompetingSalePrice(competingSalePriceList.get(i));
			pu.setJdSpuId(jdSpuIdList.get(i));
			res.add(pu);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理CommodityProductUnit保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("CommodityProductUnit",24*60*60,page);
		}catch (Exception e){
			logger.info("[CommodityProductUnit]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<CommodityProductUnitPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("CommodityProductUnit");
			saveListFactory.setCommodityProductUnitPOList(list);
			saveListFactory.setCommodityProductUnitWriteManage(commodityProductUnitWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateCommodityProductUnitPrice(List<Long> puIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<CommodityProductUnitPO> res = new ArrayList<>();
		for(int i=0;i<puIdList.size();i++){
			CommodityProductUnitPO pu = new CommodityProductUnitPO();
			pu.setId(puIdList.get(i));
			pu.setSalePrice(salePriceList.get(i));
			pu.setDemoSalePrice(demoPriceList.get(i));
			pu.setCompetingSalePrice(competingPriceList.get(i));
			res.add(pu);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateCommodityProductUnitPrice保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<CommodityProductUnitPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("updateCommodityProductUnitPrice");
			saveListFactory.setCommodityProductUnitPricePOList(list);
			saveListFactory.setCommodityProductUnitWriteManage(commodityProductUnitWriteManage);
			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		commodityProductUnitWriteManage.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);

	}

	@Override
	public void updateCommodityProductUnitList(List<Long> puIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<CommodityProductUnitPO> res = new ArrayList<>();
		for(int i=0;i<puIdList.size();i++){
			CommodityProductUnitPO pu = new CommodityProductUnitPO();
			pu.setId(puIdList.get(i));
			pu.setName(nameList.get(i));
			pu.setSalePrice(salePriceList.get(i));
			pu.setDemoSalePrice(demoSalePriceList.get(i));
			pu.setCompetingSalePrice(competingSalePriceList.get(i));
			res.add(pu);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理updateCommodityProductUnit保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/10000)+1;

		for(int i=0;i<page;i++) {
			List<CommodityProductUnitPO> list = new ArrayList<>();
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
			factory.setSaveType("updateCommodityProductUnit");
			factory.setCommodityProductUnitPOList(list);
			factory.setCommodityProductUnitWriteManage(commodityProductUnitWriteManage);
			executor.execute(factory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

}
	