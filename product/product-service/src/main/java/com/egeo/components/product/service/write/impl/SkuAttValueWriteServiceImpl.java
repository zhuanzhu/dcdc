package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.SkuAttValueConverter;
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.components.product.manage.write.SkuAttValueWriteManage;
import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.components.product.service.write.SkuAttValueWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("skuAttValueWriteService")
public class SkuAttValueWriteServiceImpl  implements SkuAttValueWriteService {
	private static final XLogger logger = XLogger.getLogger(SkuAttValueWriteServiceImpl.class);
	@Autowired
	private SkuAttValueWriteManage skuAttValueWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertSkuAttValueWithTx(SkuAttValueDTO dto) {
		SkuAttValuePO po = SkuAttValueConverter.toPO(dto);
		Long rt = skuAttValueWriteManage.insertSkuAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int updateSkuAttValueWithTx(SkuAttValueDTO dto) {
		SkuAttValuePO po = SkuAttValueConverter.toPO(dto);
		int rt = skuAttValueWriteManage.updateSkuAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSkuAttValueWithTx(SkuAttValueDTO dto) {
		SkuAttValuePO po = SkuAttValueConverter.toPO(dto);
		int rt = skuAttValueWriteManage.deleteSkuAttValueWithTx(po);		
		return rt;
	}




	private List<SkuAttValuePO> buildSkuAttValue(Long skuAttNameId, Long attValueId){
		List<SkuAttValuePO> res = new ArrayList<>();
		//sku-attvalue
		SkuAttValuePO skuAttValuePO = new SkuAttValuePO();
		skuAttValuePO.setSkuAttNameId(skuAttNameId);
		skuAttValuePO.setAttValueId(attValueId);
		/*SkuAttValuePO skuAttValuePO1 = new SkuAttValuePO();
		skuAttValuePO1.setSkuAttNameId(skuAttNameId+1);
		skuAttValuePO1.setAttValueId(2L);
		SkuAttValuePO skuAttValuePO2 = new SkuAttValuePO();
		skuAttValuePO2.setSkuAttNameId(skuAttNameId+2);
		skuAttValuePO2.setAttValueId(4L);
		SkuAttValuePO skuAttValuePO3 = new SkuAttValuePO();
		skuAttValuePO3.setSkuAttNameId(skuAttNameId+3);
		skuAttValuePO3.setAttValueId(6L);
		SkuAttValuePO skuAttValuePO4 = new SkuAttValuePO();
		skuAttValuePO4.setSkuAttNameId(skuAttNameId+4);
		skuAttValuePO4.setAttValueId(13L);*/
		res.add(skuAttValuePO);
		/*res.add(skuAttValuePO1);
		res.add(skuAttValuePO2);
		res.add(skuAttValuePO3);
		res.add(skuAttValuePO4);*/
		return res;
	}
	@Override
	public void saveSkuAttValuePO(List<Long> skuAttNameIdList, List<Long> attValueIdList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<SkuAttValuePO> res = new ArrayList<>();
		for (int i = 0; i < skuAttNameIdList.size(); i++) {
			List<SkuAttValuePO> skuAttValuePOS = buildSkuAttValue(skuAttNameIdList.get(i), attValueIdList.get(i));
			res.addAll(skuAttValuePOS);
		}
		if (EmptyUtil.isEmpty(res)) {
			return;
		}
		logger.info("开始异步处理SkuAttValuePO保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("SkuAttValuePO",24*60*60,page);
		}catch (Exception e){
			logger.info("[SkuAttValuePO]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<SkuAttValuePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("SkuAttValuePO");
			saveListFactory.setSkuAttValuePOList(list);
			saveListFactory.setSkuAttValueWriteManage(skuAttValueWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}

}
	