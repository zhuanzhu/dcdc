package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.SkuAttNameConverter;
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.components.product.manage.write.SkuAttNameWriteManage;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.components.product.service.write.SkuAttNameWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("skuAttNameWriteService")
public class SkuAttNameWriteServiceImpl  implements SkuAttNameWriteService {
	private static final XLogger logger = XLogger.getLogger(SkuAttNameWriteServiceImpl.class);
	@Autowired
	private SkuAttNameWriteManage skuAttNameWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertSkuAttNameWithTx(SkuAttNameDTO dto) {
		SkuAttNamePO po = SkuAttNameConverter.toPO(dto);
		Long rt = skuAttNameWriteManage.insertSkuAttNameWithTx(po);		
		return rt;
	}

	@Override
	public int updateSkuAttNameWithTx(SkuAttNameDTO dto) {
		SkuAttNamePO po = SkuAttNameConverter.toPO(dto);
		int rt = skuAttNameWriteManage.updateSkuAttNameWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSkuAttNameWithTx(SkuAttNameDTO dto) {
		SkuAttNamePO po = SkuAttNameConverter.toPO(dto);
		int rt = skuAttNameWriteManage.deleteSkuAttNameWithTx(po);		
		return rt;
	}



	//金传递规格属性,自动创建基本属性返回
	private List<SkuAttNamePO> buildSkuAttName(Long id, Long skuId){
		List<SkuAttNamePO> res = new ArrayList<>();
		//sku-attname
		SkuAttNamePO skuAttNamePO = new SkuAttNamePO();
		skuAttNamePO.setId(id);
		skuAttNamePO.setSkuId(skuId);
		skuAttNamePO.setAttNameId(100L);
		/*SkuAttNamePO skuAttNamePO1 = new SkuAttNamePO();
		skuAttNamePO1.setId(id+1);
		skuAttNamePO1.setSkuId(skuId);
		skuAttNamePO1.setAttNameId(1L);
		SkuAttNamePO skuAttNamePO2 = new SkuAttNamePO();
		skuAttNamePO2.setId(id+2);
		skuAttNamePO2.setSkuId(skuId);
		skuAttNamePO2.setAttNameId(2L);
		SkuAttNamePO skuAttNamePO3 = new SkuAttNamePO();
		skuAttNamePO3.setId(id+3);
		skuAttNamePO3.setSkuId(skuId);
		skuAttNamePO3.setAttNameId(3L);
		SkuAttNamePO skuAttNamePO4 = new SkuAttNamePO();
		skuAttNamePO4.setId(id+4);
		skuAttNamePO4.setSkuId(skuId);
		skuAttNamePO4.setAttNameId(4L);*/
		res.add(skuAttNamePO);
		/*res.add(skuAttNamePO1);
		res.add(skuAttNamePO2);
		res.add(skuAttNamePO3);
		res.add(skuAttNamePO4);*/
		return res;

	}
	@Override
	public void saveSkuAttName(List<Long> skuAttNameIdList, List<Long> skuIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<SkuAttNamePO> res = new ArrayList<>();
		for(int i=0;i<skuIdList.size();i++){
			List<SkuAttNamePO> skuAttNamePOS = buildSkuAttName(skuAttNameIdList.get(i),skuIdList.get(i));
			res.addAll(skuAttNamePOS);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理SkuAttName保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("SkuAttName",24*60*60,page);
		}catch (Exception e){
			logger.info("[SkuAttName]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<SkuAttNamePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("SkuAttName");
			saveListFactory.setSkuAttNamePOList(list);
			saveListFactory.setSkuAttNameWriteManage(skuAttNameWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}


}
	