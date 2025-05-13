package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MerchantProductStoreConverter;
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.components.product.manage.write.MerchantProductStoreWriteManage;
import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.components.product.service.write.MerchantProductStoreWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantProductStoreWriteService")
public class MerchantProductStoreWriteServiceImpl  implements MerchantProductStoreWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantProductStoreWriteServiceImpl.class);
	@Autowired
	private MerchantProductStoreWriteManage merchantProductStoreWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantProductStoreWithTx(MerchantProductStoreDTO dto) {
		MerchantProductStorePO po = MerchantProductStoreConverter.toPO(dto);
		Long rt = merchantProductStoreWriteManage.insertMerchantProductStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProductStoreWithTx(MerchantProductStoreDTO dto) {
		MerchantProductStorePO po = MerchantProductStoreConverter.toPO(dto);
		int rt = merchantProductStoreWriteManage.updateMerchantProductStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProductStoreWithTx(MerchantProductStoreDTO dto) {
		MerchantProductStorePO po = MerchantProductStoreConverter.toPO(dto);
		int rt = merchantProductStoreWriteManage.deleteMerchantProductStoreWithTx(po);		
		return rt;
	}


	@Override
	public void saveMerchantProductStore(List<Long> merchantProductIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProductStorePO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++) {
			MerchantProductStorePO merchantProductStorePO = new MerchantProductStorePO();
			merchantProductStorePO.setStoreId(1L);
			merchantProductStorePO.setMerchantProductId(merchantProductIdList.get(i));
			merchantProductStorePO.setPlatformId(7L);
			res.add(merchantProductStorePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantProductStore保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantProductStore",page);
		}catch (Exception e){
			logger.info("[MerchantProductStore]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantProductStorePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("MerchantProductStore");
			saveListFactory.setMerchantProductStorePOList(list);
			saveListFactory.setMerchantProductStoreWriteManage(merchantProductStoreWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}

}
	