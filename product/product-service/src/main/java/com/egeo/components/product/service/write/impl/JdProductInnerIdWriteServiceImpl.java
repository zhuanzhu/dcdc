package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.JdProductInnerIdConverter;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.manage.write.JdProductInnerIdWriteManage;
import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.components.product.service.write.JdProductInnerIdWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("jdProductInnerIdWriteService")
public class JdProductInnerIdWriteServiceImpl  implements JdProductInnerIdWriteService {
	private static final XLogger logger = XLogger.getLogger(JdProductInnerIdWriteServiceImpl.class);
	@Autowired
	private JdProductInnerIdWriteManage jdProductInnerIdWriteManage;
	@Resource
	private JedisUtil jedisUtil;

	@Override
	public Long insertJdProductInnerIdWithTx(JdProductInnerIdDTO dto) {
		JdProductInnerIdPO po = JdProductInnerIdConverter.toPO(dto);
		Long rt = jdProductInnerIdWriteManage.insertJdProductInnerIdWithTx(po);		
		return rt;
	}

	@Override
	public int updateJdProductInnerIdWithTx(JdProductInnerIdDTO dto) {
		JdProductInnerIdPO po = JdProductInnerIdConverter.toPO(dto);
		int rt = jdProductInnerIdWriteManage.updateJdProductInnerIdWithTx(po);		
		return rt;
	}

	@Override
	public int deleteJdProductInnerIdWithTx(JdProductInnerIdDTO dto) {
		JdProductInnerIdPO po = JdProductInnerIdConverter.toPO(dto);
		int rt = jdProductInnerIdWriteManage.deleteJdProductInnerIdWithTx(po);		
		return rt;
	}

	@Override
	public void saveJdProductInnerIdList(List<String> jdSkuIdList, List<Long> productIdList, List<Long> skuIdList, List<Long> suIdList, List<Long> productUnitIdList, List<Long> puIdList, List<Long> pictureIdList, List<Long> attValueIdList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<JdProductInnerIdPO> res = new ArrayList<>();
		for(int i=0;i<jdSkuIdList.size();i++){
			JdProductInnerIdPO po = new JdProductInnerIdPO();
			po.setJdSkuId(Long.valueOf(jdSkuIdList.get(i)));
			po.setInnerAttValueId(attValueIdList.get(i));
			po.setInnerPictureId(pictureIdList.get(i));
			po.setInnerProductId(productIdList.get(i));
			po.setInnerProductUnitId(productUnitIdList.get(i));
			po.setInnerPuId(puIdList.get(i));
			po.setInnerSkuId(skuIdList.get(i));
			po.setInnerSuId(suIdList.get(i));
			res.add(po);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理JdProductInnerIdList保存");

		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/SIZE)+1;

		for(int i=0;i<page;i++) {
			List<JdProductInnerIdPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("JdProductInnerIdList");
			saveListFactory.setJdProductInnerIdPOList(list);
			saveListFactory.setJdProductInnerIdWriteManage(jdProductInnerIdWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();

	}
}
	