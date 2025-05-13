package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.ServerApp;
import com.egeo.components.product.converter.AttributeValueConverter;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.manage.write.AttributeValueWriteManage;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.components.product.service.write.AttributeValueWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("attributeValueWriteService")
public class AttributeValueWriteServiceImpl  implements AttributeValueWriteService {
	private static final XLogger logger = XLogger.getLogger(ServerApp.class);
	@Autowired
	private AttributeValueWriteManage attributeValueWriteManage;

	@Resource
	private JedisUtil jedisUtil;
        @Override
        public Long saveAttributeValueWithTx(AttributeValueDTO dto) {
            if(dto.getSortValue() == null){
                dto.setSortValue(1);
            }
            return attributeValueWriteManage.saveAttributeValue(AttributeValueConverter.toPO(dto));
        }

		@Override
		public Integer deleteByIdWithTx(AttributeValueDTO dto) {
			return attributeValueWriteManage.deleteByIdWithTx(AttributeValueConverter.toPO(dto));
		}

		@Override
		public Long updateAttributeValueWithTx(AttributeValueDTO attributeValueDTO2) {
			
			return attributeValueWriteManage.updateAttributeValueWithTx(AttributeValueConverter.toPO(attributeValueDTO2));
		}
		/**
		 * 根据属性id删除属性值信息(ps：因为主键唯一无须平台id)
		 * @param id
		 * @return
		 */
		@Override
		public int deleteByAttributeNameIdWithTx(Long attributeNameId) {
			return attributeValueWriteManage.deleteByAttributeNameIdWithTx(attributeNameId);
		}


	@Override
	public void saveAttValue(List<Long> attValueIdList, List<String> attValueList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<AttributeValuePO> res = new ArrayList<>();
		for(int i = 0; i < attValueIdList.size(); i++) {
			AttributeValuePO attributeValuePO = new AttributeValuePO();
			attributeValuePO.setId(attValueIdList.get(i));
			attributeValuePO.setAttributeNameId(100L);
			attributeValuePO.setParentId(0L);
			attributeValuePO.setPlatformId(7L);
			attributeValuePO.setValue(attValueList.get(i));
			attributeValuePO.setSortValue(1);
			res.add(attributeValuePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理AttValue保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("AttValue",24*60*60,page);
		}catch (Exception e){
			logger.info("[AttValue]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<AttributeValuePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("AttValue");
			saveListFactory.setAttributeValuePOList(list);
			saveListFactory.setAttributeValueWriteManage(attributeValueWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}

			countDownLatch.countDown();
		executor.shutdown();


	}

	@Override
	public void updateAttributeValueList(List<Long> attValueIdList, List<String> attValueList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<AttributeValuePO> res = new ArrayList<>();
		for(int i = 0; i < attValueIdList.size(); i++) {
			AttributeValuePO attributeValuePO = new AttributeValuePO();
			attributeValuePO.setId(attValueIdList.get(i));
			attributeValuePO.setAttributeNameId(100L);
			attributeValuePO.setValue(attValueList.get(i));
			res.add(attributeValuePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理AttValue保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;

		for(int i=0;i<page;i++) {
			List<AttributeValuePO> list = new ArrayList<>();
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
			factory.setSaveType("AttValue");
			factory.setAttributeValuePOList(list);
			factory.setAttributeValueWriteManage(attributeValueWriteManage);
			factory.setJedisUtil(jedisUtil);

			executor.execute(factory);
		}

		countDownLatch.countDown();
		executor.shutdown();
	}
}
	