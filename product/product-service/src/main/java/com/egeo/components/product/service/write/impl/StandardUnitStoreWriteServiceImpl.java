package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardUnitStoreConverter;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.manage.write.StandardUnitStoreWriteManage;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.components.product.service.write.StandardUnitStoreWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardUnitStoreWriteService")
public class StandardUnitStoreWriteServiceImpl  implements StandardUnitStoreWriteService {

	private static final XLogger logger = XLogger.getLogger(StandardUnitStoreWriteServiceImpl.class);
	
	@Autowired
	private StandardUnitStoreWriteManage standardUnitStoreWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardUnitStoreWithTx(StandardUnitStoreDTO dto) {
		StandardUnitStorePO po = StandardUnitStoreConverter.toPO(dto);
		Long rt = standardUnitStoreWriteManage.insertStandardUnitStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitStoreWithTx(StandardUnitStoreDTO dto) {
		StandardUnitStorePO po = StandardUnitStoreConverter.toPO(dto);
		int rt = standardUnitStoreWriteManage.updateStandardUnitStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitStoreWithTx(StandardUnitStoreDTO dto) {
		StandardUnitStorePO po = StandardUnitStoreConverter.toPO(dto);
		int rt = standardUnitStoreWriteManage.deleteStandardUnitStoreWithTx(po);		
		return rt;
	}

	@Override
	public void saveStandardUnitStore(List<Long> suIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitStorePO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++) {
			StandardUnitStorePO standardUnitStorePO = new StandardUnitStorePO();
			standardUnitStorePO.setStoreId(1L);
			standardUnitStorePO.setStandardUnitId(suIdList.get(i));
			standardUnitStorePO.setPlatformId(7L);
			res.add(standardUnitStorePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardUnitStore保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardUnitStore",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardUnitStore]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardUnitStorePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardUnitStore");
			saveListFactory.setStandardUnitStorePOList(list);
			saveListFactory.setStandardUnitStoreWriteManage(standardUnitStoreWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}
}
	