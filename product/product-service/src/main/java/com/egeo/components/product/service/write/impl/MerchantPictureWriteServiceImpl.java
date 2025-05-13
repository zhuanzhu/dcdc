package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MerchantPictureConverter;
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.components.product.manage.write.MerchantPictureWriteManage;
import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.components.product.service.write.MerchantPictureWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantPictureWriteService")
public class MerchantPictureWriteServiceImpl  implements MerchantPictureWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantPictureWriteServiceImpl.class);
	@Autowired
	private MerchantPictureWriteManage merchantPictureWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantPictureWithTx(MerchantPictureDTO dto) {
		MerchantPicturePO po = MerchantPictureConverter.toPO(dto);
		Long rt = merchantPictureWriteManage.insertMerchantPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantPictureWithTx(MerchantPictureDTO dto) {
		MerchantPicturePO po = MerchantPictureConverter.toPO(dto);
		int rt = merchantPictureWriteManage.updateMerchantPictureWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantPictureWithTx(MerchantPictureDTO dto) {
		MerchantPicturePO po = MerchantPictureConverter.toPO(dto);
		int rt = merchantPictureWriteManage.deleteMerchantPictureWithTx(po);		
		return rt;
	}



	@Override
	public void saveMerchantPicture(List<Long> merchantPictureIdList, List<Long> pictureIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantPicturePO> res = new ArrayList<>();
		for(int i=0;i<merchantPictureIdList.size();i++){
			MerchantPicturePO merchantPicturePO = new MerchantPicturePO();
			merchantPicturePO.setId(merchantPictureIdList.get(i));
			merchantPicturePO.setMerchantId(6L);
			merchantPicturePO.setPictureId(pictureIdList.get(i));
			merchantPicturePO.setPlatformId(7L);
			res.add(merchantPicturePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantPicture保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantPicture",24*60*60,page);
		}catch (Exception e){
			logger.info("[MerchantPicture]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantPicturePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("MerchantPicture");
			saveListFactory.setMerchantPicturePOList(list);
			saveListFactory.setMerchantPictureWriteManage(merchantPictureWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}

		countDownLatch.countDown();
		executor.shutdown();
	}
}
	