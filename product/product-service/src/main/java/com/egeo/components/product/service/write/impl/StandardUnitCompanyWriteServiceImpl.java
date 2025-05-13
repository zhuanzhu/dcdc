package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardUnitCompanyConverter;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.manage.write.StandardUnitCompanyWriteManage;
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.components.product.service.write.StandardUnitCompanyWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardUnitCompanyWriteService")
public class StandardUnitCompanyWriteServiceImpl  implements StandardUnitCompanyWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardUnitCompanyWriteServiceImpl.class);
	@Autowired
	private StandardUnitCompanyWriteManage standardUnitCompanyWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
		Long rt = standardUnitCompanyWriteManage.insertStandardUnitCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
		int rt = standardUnitCompanyWriteManage.updateStandardUnitCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto) {
		StandardUnitCompanyPO po = StandardUnitCompanyConverter.toPO(dto);
		int rt = standardUnitCompanyWriteManage.deleteStandardUnitCompanyWithTx(po);		
		return rt;
	}
	/**
	 * 根据suid删除su福利企业关系信息
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitCompanyWriteManage.deleteByStandardUnitIdWithTx(standardUnitId);
	}




	@Override
	public void saveStandardUnitCompany(List<Long> suIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitCompanyPO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++) {
			StandardUnitCompanyPO standardUnitCompanyPO = new StandardUnitCompanyPO();
			standardUnitCompanyPO.setStandardUnitId(suIdList.get(i));
			standardUnitCompanyPO.setCompanyId(-1L);
			standardUnitCompanyPO.setCompanyType(0);
			StandardUnitCompanyPO standardUnitCompanyPO1 = new StandardUnitCompanyPO();
			standardUnitCompanyPO1.setStandardUnitId(suIdList.get(i));
			standardUnitCompanyPO1.setCompanyId(-1L);
			standardUnitCompanyPO1.setCompanyType(1);
			StandardUnitCompanyPO standardUnitCompanyPO2 = new StandardUnitCompanyPO();
			standardUnitCompanyPO2.setStandardUnitId(suIdList.get(i));
			standardUnitCompanyPO2.setCompanyId(-1L);
			standardUnitCompanyPO2.setCompanyType(2);
			res.add(standardUnitCompanyPO);
			res.add(standardUnitCompanyPO1);
			res.add(standardUnitCompanyPO2);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardUnitCompany保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardUnitCompany",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardUnitCompany]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardUnitCompanyPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardUnitCompany");
			saveListFactory.setStandardUnitCompanyPOList(list);
			saveListFactory.setStandardUnitCompanyWriteManage(standardUnitCompanyWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}
}
	