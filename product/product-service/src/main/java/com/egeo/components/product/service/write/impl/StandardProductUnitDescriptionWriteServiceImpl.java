package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardProductUnitDescriptionConverter;
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.components.product.manage.write.StandardProductUnitDescriptionWriteManage;
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.components.product.service.write.StandardProductUnitDescriptionWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardProductUnitDescriptionWriteService")
public class StandardProductUnitDescriptionWriteServiceImpl  implements StandardProductUnitDescriptionWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitDescriptionWriteServiceImpl.class);
	@Autowired
	private StandardProductUnitDescriptionWriteManage standardProductUnitDescriptionWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto) {
		StandardProductUnitDescriptionPO po = StandardProductUnitDescriptionConverter.toPO(dto);
		Long rt = standardProductUnitDescriptionWriteManage.insertStandardProductUnitDescriptionWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto) {
		StandardProductUnitDescriptionPO po = StandardProductUnitDescriptionConverter.toPO(dto);
		int rt = standardProductUnitDescriptionWriteManage.updateStandardProductUnitDescriptionWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto) {
		StandardProductUnitDescriptionPO po = StandardProductUnitDescriptionConverter.toPO(dto);
		int rt = standardProductUnitDescriptionWriteManage.deleteStandardProductUnitDescriptionWithTx(po);		
		return rt;
	}



	@Override
	public void saveStandardProductUnitDescription(List<Long> spuIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardProductUnitDescriptionPO> res = new ArrayList<>();
		for(int i=0;i<spuIdList.size();i++){
			StandardProductUnitDescriptionPO descriptionPO = new StandardProductUnitDescriptionPO();
			descriptionPO.setStandardProductUnitId(spuIdList.get(i));
			descriptionPO.setContent("");
			descriptionPO.setPlatformId(7L);
			res.add(descriptionPO);
		}

		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardProductUnitDescription保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardProductUnitDescription",page);
		}catch (Exception e){
			logger.info("[StandardProductUnitDescription]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardProductUnitDescriptionPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardProductUnitDescription");
			saveListFactory.setStandardProductUnitDescriptionPOList(list);
			saveListFactory.setStandardProductUnitDescriptionWriteManage(standardProductUnitDescriptionWriteManage);
			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateStandardProductUnitDescriptionSWithTx(StandardProductUnitDescriptionDTO standardProductUnitDescriptionDTO) {
		standardProductUnitDescriptionWriteManage.updateStandardProductUnitDescriptionSWithTx(StandardProductUnitDescriptionConverter.toPO(standardProductUnitDescriptionDTO) );
	}


}
	