package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.CommodityProductUnitConverter;
import com.egeo.components.product.converter.SkuConverter;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.manage.write.CommodityProductUnitWriteManage;
import com.egeo.components.product.manage.write.ProductUnitWriteManage;
import com.egeo.components.product.manage.write.SkuWriteManage;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.SkuPO;
import com.egeo.components.product.service.write.SkuWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("skuWriteService")
public class SkuWriteServiceImpl  implements SkuWriteService {
	private static final XLogger logger = XLogger.getLogger(SkuWriteServiceImpl.class);
	@Autowired
	private SkuWriteManage skuWriteManage;
	
	@Autowired
	private CommodityProductUnitWriteManage commodityProductUnitWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Autowired
	private ProductUnitWriteManage productUnitWriteManage;

	@Override
	public Long insertSkuWithTx(SkuDTO dto) {
		SkuPO po = SkuConverter.toPO(dto);
		Long rt = skuWriteManage.insertSkuWithTx(po);		
		return rt;
	}

	@Override
	public int updateSkuWithTx(SkuDTO dto) {
		SkuPO po = SkuConverter.toPO(dto);
		int rt = skuWriteManage.updateSkuWithTx(po);	
		if(rt != 0){
			if(dto.getIsValid() == 0){
				//如果sku失效、同步失效sku下面的所有pu
				commodityProductUnitWriteManage.updatePUIsVendibleBySkuIdWithTx(dto.getId());
				
				//如果sku失效、同步失效sku下面的所有草稿pu
				int i = productUnitWriteManage.updatePUIsVendibleBySkuIdWithTx(dto.getId());
			}
			
		}
		return rt;
	}

	@Override
	public int deleteSkuWithTx(SkuDTO dto) {
		SkuPO po = SkuConverter.toPO(dto);
		int rt = skuWriteManage.deleteSkuWithTx(po);		
		return rt;
	}
	/**
	 * 批量启用停用
	 * @param ids
	 * @param type
	 * @return
	 */
	@Override
	public int isAvailableWithTx(List<Long> ids, int type) {
		int i = 0;
		for (Long skuId : ids) {
			SkuPO skuPO = new SkuPO();
			skuPO.setId(skuId);
			skuPO.setIsAvailable(type);
			int tx = skuWriteManage.updateSkuWithTx(skuPO);
			if(tx != 0){
				if(type == 0){
					//根据skuid批量下架pu
					commodityProductUnitWriteManage.updateStatusBySkuId(skuId);
					//根据skuid同步下架pu草稿信息
					productUnitWriteManage.updateStatusBySkuId(skuId);
				}
				i = i + tx;
			}
			
		}
		return i;
	}
	/**
	 * 根据spuid更新supu信息
	 * @param standardProductUnitId
	 */
	@Override
	public List<CommodityProductUnitDTO> updateSuPuWithTx(Long standardProductUnitId, Long merchantProductId) {
		List<CommodityProductUnitPO> pos = skuWriteManage.updateSuPuWithTx(standardProductUnitId, merchantProductId);
		return CommodityProductUnitConverter.toDTO(pos);
	}

	@Override
	public void updateSkuParamsWithTx(SkuDTO skuDTO) {
		SkuPO po = SkuConverter.toPO(skuDTO);
		int rt = skuWriteManage.updateSkuParamsWithTx(po);
		if(rt==0){
			throw new BusinessException("保存失败");
		}
	}




	@Override
	public void saveSku(List<Long> skuIdList, List<Long> spuIdList, List<String> skuSerialNumberList, List<String> nameList, List<String> jdSkuIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<SkuPO> res = new ArrayList<>();
		for(int i=0;i<skuIdList.size();i++){
			SkuPO skuPO = new SkuPO();
			skuPO.setId(skuIdList.get(i));
			skuPO.setSkuSerialNumber(skuSerialNumberList.get(i));
			skuPO.setMerchantId(6L);
			skuPO.setStandardProductUnitId(spuIdList.get(i));
			skuPO.setSkuName(nameList.get(i));
			skuPO.setIsAvailable(1);
			skuPO.setIsValid(1);
			skuPO.setPlatformId(7L);
			skuPO.setExternalSkuId(jdSkuIdList.get(i));
			res.add(skuPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理Sku保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);



		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("Sku",24*60*60,page);
		}catch (Exception e){
			logger.info("[Sku]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<SkuPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("Sku");
			saveListFactory.setSkuPOList(list);
			saveListFactory.setSkuWriteManage(skuWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateSkuList(List<Long> skuIdList, List<String> nameList, List<Long> jdSkuIdList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<SkuPO> res = new ArrayList<>();
		for(int i=0;i<skuIdList.size();i++){
			SkuPO skuPO = new SkuPO();
			skuPO.setId(skuIdList.get(i));
			skuPO.setSkuName(nameList.get(i));
			skuPO.setExternalSkuId(jdSkuIdList.get(i)+"");
			res.add(skuPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理Sku保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;

		for(int i=0;i<page;i++) {
			List<SkuPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					list.add(res.get(j));
				}
			}
			UpdateListFactory factory = new UpdateListFactory();


			factory.setLatch(countDownLatch);
			factory.setSaveType("Sku");
			factory.setSkuPOList(list);
			factory.setSkuWriteManage(skuWriteManage);
			factory.setJedisUtil(jedisUtil);

			executor.execute(factory);

		}
		countDownLatch.countDown();
		executor.shutdown();

	}

}
	