package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardUnitDescribeConverter;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.components.product.manage.write.StandardUnitDescribeWriteManage;
import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.components.product.service.write.StandardUnitDescribeWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardUnitDescribeWriteService")
public class StandardUnitDescribeWriteServiceImpl  implements StandardUnitDescribeWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardUnitDescribeWriteServiceImpl.class);
	@Autowired
	private StandardUnitDescribeWriteManage standardUnitDescribeWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto) {
		StandardUnitDescribePO po = StandardUnitDescribeConverter.toPO(dto);
		Long rt = standardUnitDescribeWriteManage.insertStandardUnitDescribeWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto) {
		StandardUnitDescribePO po = StandardUnitDescribeConverter.toPO(dto);
		int rt = standardUnitDescribeWriteManage.updateStandardUnitDescribeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto) {
		StandardUnitDescribePO po = StandardUnitDescribeConverter.toPO(dto);
		int rt = standardUnitDescribeWriteManage.deleteStandardUnitDescribeWithTx(po);		
		return rt;
	}
	/**
	 * 根据suid同步su商品详情信息
	 * @param standardUnitDescribeDTO
	 * @return
	 */
	@Override
	public int updateByStandardUnitIdWithTx(StandardUnitDescribeDTO standardUnitDescribeDTO) {
		// TODO Auto-generated method stub
		return standardUnitDescribeWriteManage.updateByStandardUnitIdWithTx(StandardUnitDescribeConverter.toPO(standardUnitDescribeDTO));
	}


	@Override
	public void saveStandardUnitDescribe(List<Long> suIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitDescribePO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++){
			StandardUnitDescribePO standardUnitDescribePO = new StandardUnitDescribePO();
			standardUnitDescribePO.setContent("");
			standardUnitDescribePO.setPlatformId(7L);
			standardUnitDescribePO.setStandardUnitId(suIdList.get(i));
			standardUnitDescribePO.setContentUrl("");
			res.add(standardUnitDescribePO);
		}

		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardUnitDescribe保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);



		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardUnitDescribe",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardUnitDescribe]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardUnitDescribePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardUnitDescribe");
			saveListFactory.setStandardUnitDescribePOList(list);
			saveListFactory.setStandardUnitDescribeWriteManage(standardUnitDescribeWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();

	}

	@Override
	public void updateStandardUnitDescribeSWithTx(StandardUnitDescribeDTO standardUnitDescribeDTO) {
		standardUnitDescribeWriteManage.updateStandardUnitDescribeSWithTx(StandardUnitDescribeConverter.toPO(standardUnitDescribeDTO) );
	}
}
	