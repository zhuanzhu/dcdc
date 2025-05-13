package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MerchantProdClientConverter;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.components.product.manage.write.MerchantProdClientWriteManage;
import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.components.product.service.write.MerchantProdClientWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantProdClientWriteService")
public class MerchantProdClientWriteServiceImpl  implements MerchantProdClientWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantProdClientWriteServiceImpl.class);
	@Autowired
	private MerchantProdClientWriteManage merchantProdClientWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantProdClientWithTx(MerchantProdClientDTO dto) {
		MerchantProdClientPO po = MerchantProdClientConverter.toPO(dto);
		Long rt = merchantProdClientWriteManage.insertMerchantProdClientWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdClientWithTx(MerchantProdClientDTO dto) {
		MerchantProdClientPO po = MerchantProdClientConverter.toPO(dto);
		int rt = merchantProdClientWriteManage.updateMerchantProdClientWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdClientWithTx(MerchantProdClientDTO dto) {
		MerchantProdClientPO po = MerchantProdClientConverter.toPO(dto);
		int rt = merchantProdClientWriteManage.deleteMerchantProdClientWithTx(po);		
		return rt;
	}
	/**
	 * 根据su草稿与客户端的关系id集合批量删除su草稿与客户端的关系
	 * @param merchantProdClientIds
	 * @return
	 */
	@Override
	public int deleteByMerchantProdClientIdsWithTx(List<Long> merchantProdClientIds) {
		// TODO Auto-generated method stub
		return merchantProdClientWriteManage.deleteByMerchantProdClientIdsWithTx(merchantProdClientIds);
	}


	@Override
	public void saveMerchantProdClient(List<Long> merchantProductIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProdClientPO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++){

			MerchantProdClientPO merchantProdClientPO1 = new MerchantProdClientPO();
			merchantProdClientPO1.setMerchantProductId(merchantProductIdList.get(i));
			merchantProdClientPO1.setClientId(2L);
			res.add(merchantProdClientPO1);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantProdClient保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantProdClient",24*60*60,page);
		}catch (Exception e){
			logger.info("[MerchantProdClient]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantProdClientPO> list = new ArrayList<>();
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
		saveListFactory.setSaveType("MerchantProdClient");
		saveListFactory.setMerchantProdClientPOList(list);
		saveListFactory.setMerchantProdClientWriteManage(merchantProdClientWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}
}
	