package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardProductUnitAttNameConverter;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.manage.write.StandardProductUnitAttNameWriteManage;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.components.product.service.write.StandardProductUnitAttNameWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardProductUnitAttNameWriteService")
public class StandardProductUnitAttNameWriteServiceImpl  implements StandardProductUnitAttNameWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitAttNameWriteServiceImpl.class);
	@Autowired
	private StandardProductUnitAttNameWriteManage standardProductUnitAttNameWriteManage;


	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto) {
		StandardProductUnitAttNamePO po = StandardProductUnitAttNameConverter.toPO(dto);
		Long rt = standardProductUnitAttNameWriteManage.insertStandardProductUnitAttNameWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto) {
		StandardProductUnitAttNamePO po = StandardProductUnitAttNameConverter.toPO(dto);
		int rt = standardProductUnitAttNameWriteManage.updateStandardProductUnitAttNameWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto) {
		StandardProductUnitAttNamePO po = StandardProductUnitAttNameConverter.toPO(dto);
		int rt = standardProductUnitAttNameWriteManage.deleteStandardProductUnitAttNameWithTx(po);		
		return rt;
	}



	//构建4个基本一个规格属性
	private List<StandardProductUnitAttNamePO> buildSPUAttName(Long spuId, Long spuAttNameId){
		List<StandardProductUnitAttNamePO> res = new ArrayList<>();
		StandardProductUnitAttNamePO spuAttName = new StandardProductUnitAttNamePO();
		spuAttName.setId(spuAttNameId);
		spuAttName.setStandardProductUnitId(spuId);
		spuAttName.setAttNameId(100L);
		spuAttName.setType(2);
		spuAttName.setPlatformId(7L);
		spuAttName.setShowPicture(1);
		StandardProductUnitAttNamePO spuAttName1 = new StandardProductUnitAttNamePO();
		spuAttName1.setId(spuAttNameId+1);
		spuAttName1.setStandardProductUnitId(spuId);
		spuAttName1.setAttNameId(1L);
		spuAttName1.setType(1);
		spuAttName1.setPlatformId(7L);
		spuAttName1.setShowPicture(0);
		StandardProductUnitAttNamePO spuAttName2 = new StandardProductUnitAttNamePO();
		spuAttName2.setId(spuAttNameId+2);
		spuAttName2.setStandardProductUnitId(spuId);
		spuAttName2.setAttNameId(2L);
		spuAttName2.setType(1);
		spuAttName2.setPlatformId(7L);
		spuAttName2.setShowPicture(0);
		StandardProductUnitAttNamePO spuAttName3 = new StandardProductUnitAttNamePO();
		spuAttName3.setId(spuAttNameId+3);
		spuAttName3.setStandardProductUnitId(spuId);
		spuAttName3.setAttNameId(3L);
		spuAttName3.setType(1);
		spuAttName3.setPlatformId(7L);
		spuAttName3.setShowPicture(0);
		StandardProductUnitAttNamePO spuAttName4 = new StandardProductUnitAttNamePO();
		spuAttName4.setId(spuAttNameId+4);
		spuAttName4.setStandardProductUnitId(spuId);
		spuAttName4.setAttNameId(4L);
		spuAttName4.setType(1);
		spuAttName4.setPlatformId(7L);
		spuAttName4.setShowPicture(0);
		res.add(spuAttName);
		res.add(spuAttName1);
		res.add(spuAttName2);
		res.add(spuAttName3);
		res.add(spuAttName4);
		return res;

	}
	@Override
	public void saveStandardProductUnitAttName(List<Long> spuIdList, List<Long> spuAttNameIdList){
		ExecutorService executor = Executors.newCachedThreadPool();

		Integer SIZE=10000;
		//spu-att-name(多个)
		List<StandardProductUnitAttNamePO> list = new ArrayList<>();
		for(int i=0;i<spuAttNameIdList.size();i++){
			List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOS = buildSPUAttName(spuIdList.get(i),spuAttNameIdList.get(i));
			list.addAll(standardProductUnitAttNamePOS);
		}
		if(EmptyUtil.isEmpty(list)){
			return;
		}
		logger.info("开始异步处理StandardProductUnitAttName保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((list.size()-1)/SIZE)+1;

		try {
			jedisUtil.set("StandardProductUnitAttName",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardProductUnitAttName]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardProductUnitAttNamePO> res = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < list.size(); j++) {
					res.add(list.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					res.add(list.get(j));
				}
			}
			SaveListFactory saveListFactory=new SaveListFactory();
			saveListFactory.setLatch(countDownLatch);
			saveListFactory.setSaveType("StandardProductUnitAttName");
			saveListFactory.setStandardProductUnitAttNamePOList(res);
			saveListFactory.setStandardProductUnitAttNameWriteManage(standardProductUnitAttNameWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);
			executor.execute(saveListFactory);
		}

		countDownLatch.countDown();
		executor.shutdown();
	}
}
	