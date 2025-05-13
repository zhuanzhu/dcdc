package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MerchantProdDescribeConverter;
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.components.product.manage.write.MerchantProdDescribeWriteManage;
import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.components.product.service.write.MerchantProdDescribeWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantProdDescribeWriteService")
public class MerchantProdDescribeWriteServiceImpl  implements MerchantProdDescribeWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantProdDescribeWriteServiceImpl.class);
	@Autowired
	private MerchantProdDescribeWriteManage merchantProdDescribeWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto) {
		MerchantProdDescribePO po = MerchantProdDescribeConverter.toPO(dto);
		Long rt = merchantProdDescribeWriteManage.insertMerchantProdDescribeWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto) {
		MerchantProdDescribePO po = MerchantProdDescribeConverter.toPO(dto);
		int rt = merchantProdDescribeWriteManage.updateMerchantProdDescribeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto) {
		MerchantProdDescribePO po = MerchantProdDescribeConverter.toPO(dto);
		int rt = merchantProdDescribeWriteManage.deleteMerchantProdDescribeWithTx(po);		
		return rt;
	}


	@Override
	public void saveMerchantProdDescribe(List<Long> merchantProductIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProdDescribePO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++) {
			MerchantProdDescribePO merchantProdDescribePO = new MerchantProdDescribePO();
			merchantProdDescribePO.setContent("");
			merchantProdDescribePO.setPlatformId(7L);
			merchantProdDescribePO.setMerchantProductId(merchantProductIdList.get(i));
			res.add(merchantProdDescribePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantProdDescribe保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantProdDescribe",24*60*60,page);
		}catch (Exception e){
			logger.info("[MerchantProdDescribe]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantProdDescribePO> list = new ArrayList<>();
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
		saveListFactory.setSaveType("MerchantProdDescribe");
		saveListFactory.setMerchantProdDescribePOList(list);
		saveListFactory.setMerchantProdDescribeWriteManage(merchantProdDescribeWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
	}
		countDownLatch.countDown();
		executor.shutdown();
	}

	@Override
	public void updateMerchantProdDescribeSWithTx(MerchantProdDescribeDTO merchantProdDescribeDTO) {
		merchantProdDescribeWriteManage.updateMerchantProdDescribeSWithTx(MerchantProdDescribeConverter.toPO(merchantProdDescribeDTO) );
	}
}
	