package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardUnitClientConverter;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.manage.write.StandardUnitClientWriteManage;
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.components.product.service.write.StandardUnitClientWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardUnitClientWriteService")
public class StandardUnitClientWriteServiceImpl  implements StandardUnitClientWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardUnitClientWriteServiceImpl.class);
	@Autowired
	private StandardUnitClientWriteManage standardUnitClientWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardUnitClientWithTx(StandardUnitClientDTO dto) {
		StandardUnitClientPO po = StandardUnitClientConverter.toPO(dto);
		Long rt = standardUnitClientWriteManage.insertStandardUnitClientWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitClientWithTx(StandardUnitClientDTO dto) {
		StandardUnitClientPO po = StandardUnitClientConverter.toPO(dto);
		int rt = standardUnitClientWriteManage.updateStandardUnitClientWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitClientWithTx(StandardUnitClientDTO dto) {
		StandardUnitClientPO po = StandardUnitClientConverter.toPO(dto);
		int rt = standardUnitClientWriteManage.deleteStandardUnitClientWithTx(po);		
		return rt;
	}
	/**
	 * 根据suid删除su客户端关系表
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitClientWriteManage.deleteByStandardUnitIdWithTx(standardUnitId);
	}



	@Override
	public void saveStandardUnitClient(List<Long> suIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitClientPO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++) {

			StandardUnitClientPO standardUnitClientPO1 = new StandardUnitClientPO();
			standardUnitClientPO1.setStandardUnitId(suIdList.get(i));
			standardUnitClientPO1.setClientId(2L);
			res.add(standardUnitClientPO1);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardUnitClient保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardUnitClient",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardUnitClient]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardUnitClientPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardUnitClient");
			saveListFactory.setStandardUnitClientPOList(list);
			saveListFactory.setStandardUnitClientWriteManage(standardUnitClientWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}
}
	