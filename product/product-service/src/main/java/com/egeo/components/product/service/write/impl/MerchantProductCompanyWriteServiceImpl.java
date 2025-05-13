package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MerchantProductCompanyConverter;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.components.product.manage.write.MerchantProductCompanyWriteManage;
import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.components.product.service.write.MerchantProductCompanyWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantProductCompanyWriteService")
public class MerchantProductCompanyWriteServiceImpl  implements MerchantProductCompanyWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantProductCompanyWriteServiceImpl.class);
	@Autowired
	private MerchantProductCompanyWriteManage merchantProductCompanyWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto) {
		MerchantProductCompanyPO po = MerchantProductCompanyConverter.toPO(dto);
		Long rt = merchantProductCompanyWriteManage.insertMerchantProductCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto) {
		MerchantProductCompanyPO po = MerchantProductCompanyConverter.toPO(dto);
		int rt = merchantProductCompanyWriteManage.updateMerchantProductCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto) {
		MerchantProductCompanyPO po = MerchantProductCompanyConverter.toPO(dto);
		int rt = merchantProductCompanyWriteManage.deleteMerchantProductCompanyWithTx(po);		
		return rt;
	}
	/**
	 * 根据拼接的su草稿企业关系id集合批量删除
	 * @param merchantProductCompanyIds
	 * @return
	 */
	@Override
	public int deleteByMerchantProductCompanyIdsWithTx(List<Long> merchantProductCompanyIds) {
		// TODO Auto-generated method stub
		return merchantProductCompanyWriteManage.deleteByMerchantProductCompanyIdsWithTx(merchantProductCompanyIds);
	}


	@Override
	public void saveMerchantProductCompany(List<Long> merchantProductIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProductCompanyPO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++){
			MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
			merchantProductCompanyPO.setMerchantProductId(merchantProductIdList.get(i));
			merchantProductCompanyPO.setCompanyId(-1L);
			merchantProductCompanyPO.setCompanyType(0);
			MerchantProductCompanyPO merchantProductCompanyPO1 = new MerchantProductCompanyPO();
			merchantProductCompanyPO1.setMerchantProductId(merchantProductIdList.get(i));
			merchantProductCompanyPO1.setCompanyId(-1L);
			merchantProductCompanyPO1.setCompanyType(1);
			MerchantProductCompanyPO merchantProductCompanyPO2 = new MerchantProductCompanyPO();
			merchantProductCompanyPO2.setMerchantProductId(merchantProductIdList.get(i));
			merchantProductCompanyPO2.setCompanyId(-1L);
			merchantProductCompanyPO2.setCompanyType(2);
			res.add(merchantProductCompanyPO);
			res.add(merchantProductCompanyPO1);
			res.add(merchantProductCompanyPO2);

		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantProductCompany保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);



		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantProductCompany",24*60*60,page);
		}catch (Exception e){
			logger.info("[MerchantProductCompany]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantProductCompanyPO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("MerchantProductCompany");
			saveListFactory.setMerchantProductCompanyPOList(list);
			saveListFactory.setMerchantProductCompanyWriteManage(merchantProductCompanyWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}
}
	