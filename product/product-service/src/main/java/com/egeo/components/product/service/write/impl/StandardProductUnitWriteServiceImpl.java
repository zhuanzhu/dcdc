package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardProductUnitConverter;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.manage.write.StandardProductUnitWriteManage;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.components.product.service.write.StandardProductUnitWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardProductUnitWriteService")
public class StandardProductUnitWriteServiceImpl  implements StandardProductUnitWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitWriteServiceImpl.class);
	@Autowired
	private StandardProductUnitWriteManage standardProductUnitWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardProductUnitWithTx(StandardProductUnitDTO dto) {
		StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
		Long rt = standardProductUnitWriteManage.insertStandardProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitWithTx(StandardProductUnitDTO dto) {
		StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
		int rt = standardProductUnitWriteManage.updateStandardProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitWithTx(StandardProductUnitDTO dto) {
		StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
		int rt = standardProductUnitWriteManage.deleteStandardProductUnitWithTx(po);		
		return rt;
	}




	@Override
	public void saveSPU(List<Long> spuIdList, List<String> spuSerialNumberList,
						List<String> productCategoryList, List<Long> catgoryTreeNodeIdList,
						List<String> nameList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<StandardProductUnitPO> res = new ArrayList<>();
		for(int i=0;i<spuIdList.size();i++){
			StandardProductUnitPO spu = new StandardProductUnitPO();
			spu.setId(spuIdList.get(i));
			spu.setProductSerialNumber(spuSerialNumberList.get(i));
			spu.setProductCategory(productCategoryList.get(i));
			spu.setCategoryTreeNodeId(catgoryTreeNodeIdList.get(i));
			spu.setName(nameList.get(i));
			spu.setStatus(3);
			spu.setIsAvailable(1);
			spu.setPlatformId(7L);
			spu.setCommodityTemplateId(2L);
			res.add(spu);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理SPU保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("SPU",24*60*60,page);
		}catch (Exception e){
			logger.info("[SPU]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardProductUnitPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("SPU");
			saveListFactory.setStandardProductUnitPOList(list);
			saveListFactory.setStandardProductUnitWriteManage(standardProductUnitWriteManage);
			executor.execute(saveListFactory);


		}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateStandardProductUnitList(List<Long> standardProductUnitIdList, List<String> nameList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardProductUnitPO> res = new ArrayList<>();
		for(int i=0;i<standardProductUnitIdList.size();i++){
			StandardProductUnitPO spu = new StandardProductUnitPO();
			spu.setId(standardProductUnitIdList.get(i));
			spu.setName(nameList.get(i));
			res.add(spu);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理SPU保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;

		for(int i=0;i<page;i++) {
			List<StandardProductUnitPO> list = new ArrayList<>();
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
			factory.setSaveType("SPU");
			factory.setStandardProductUnitPOList(list);
			factory.setStandardProductUnitWriteManage(standardProductUnitWriteManage);
			executor.execute(factory);


		}
		countDownLatch.countDown();
		executor.shutdown();

	}

}
	