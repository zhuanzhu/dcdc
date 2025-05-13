package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardProductUnitAttValueConverter;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.manage.write.StandardProductUnitAttValueWriteManage;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.components.product.service.write.StandardProductUnitAttValueWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardProductUnitAttValueWriteService")
public class StandardProductUnitAttValueWriteServiceImpl  implements StandardProductUnitAttValueWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitAttValueWriteServiceImpl.class);
	@Autowired
	private StandardProductUnitAttValueWriteManage standardProductUnitAttValueWriteManage;
	@Resource
	private JedisUtil jedisUtil;

	@Override
	public Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto) {
		StandardProductUnitAttValuePO po = StandardProductUnitAttValueConverter.toPO(dto);
		Long rt = standardProductUnitAttValueWriteManage.insertStandardProductUnitAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto) {
		StandardProductUnitAttValuePO po = StandardProductUnitAttValueConverter.toPO(dto);
		int rt = standardProductUnitAttValueWriteManage.updateStandardProductUnitAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto) {
		StandardProductUnitAttValuePO po = StandardProductUnitAttValueConverter.toPO(dto);
		int rt = standardProductUnitAttValueWriteManage.deleteStandardProductUnitAttValueWithTx(po);		
		return rt;
	}
	/**
	 * 根据spu属性id删除spu属性值信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByStandardProductUnitAttNameIdWithTx(Long standardProductUnitAttNameId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueWriteManage.deleteByStandardProductUnitAttNameId(standardProductUnitAttNameId);
	}





	private List<StandardProductUnitAttValuePO> buildSPUValue(Long spuAttNameId, Long attValueId){
		List<StandardProductUnitAttValuePO> res = new ArrayList<>();
		StandardProductUnitAttValuePO spuValuePO = new StandardProductUnitAttValuePO();
		spuValuePO.setStandardProductUnitAttNameId(spuAttNameId);
		spuValuePO.setAttValueId(attValueId);
		spuValuePO.setPlatformId(7L);
		StandardProductUnitAttValuePO spuValuePO1 = new StandardProductUnitAttValuePO();
		spuValuePO1.setStandardProductUnitAttNameId(spuAttNameId+1);
		spuValuePO1.setAttValueId(2L);
		spuValuePO1.setPlatformId(7L);
		StandardProductUnitAttValuePO spuValuePO2= new StandardProductUnitAttValuePO();
		spuValuePO2.setStandardProductUnitAttNameId(spuAttNameId+2);
		spuValuePO2.setAttValueId(4L);
		spuValuePO2.setPlatformId(7L);
		StandardProductUnitAttValuePO spuValuePO3 = new StandardProductUnitAttValuePO();
		spuValuePO3.setStandardProductUnitAttNameId(spuAttNameId+3);
		spuValuePO3.setAttValueId(6L);
		spuValuePO3.setPlatformId(7L);
		StandardProductUnitAttValuePO spuValuePO4 = new StandardProductUnitAttValuePO();
		spuValuePO4.setStandardProductUnitAttNameId(spuAttNameId+4);
		spuValuePO4.setAttValueId(13L);
		spuValuePO4.setPlatformId(7L);
		res.add(spuValuePO);
		res.add(spuValuePO1);
		res.add(spuValuePO2);
		res.add(spuValuePO3);
		res.add(spuValuePO4);
		return res;
	}
	@Override
	public void saveSPUValue(List<Long> spuAttNameIdList, List<Long> attValueIdList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardProductUnitAttValuePO> res = new ArrayList<>();
		for (int i = 0; i < spuAttNameIdList.size(); i++) {
			List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOS = buildSPUValue(spuAttNameIdList.get(i), attValueIdList.get(i));
			res.addAll(standardProductUnitAttValuePOS);

		}
		if (EmptyUtil.isEmpty(res)) {
			return;
		}
		logger.info("开始异步处理SPUValue保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("SPUValue",page);
		}catch (Exception e){
			logger.info("[SPUValue]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardProductUnitAttValuePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("SPUValue");
			saveListFactory.setStandardProductUnitAttValuePOList(list);
			saveListFactory.setStandardProductUnitAttValueWriteManage(standardProductUnitAttValueWriteManage);
			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}
}
	