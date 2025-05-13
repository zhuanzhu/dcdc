package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardUnitPictureConverter;
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.components.product.manage.write.StandardUnitPictureWriteManage;
import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.components.product.service.write.StandardUnitPictureWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardUnitPictureWriteService")
public class StandardUnitPictureWriteServiceImpl  implements StandardUnitPictureWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardUnitPictureWriteServiceImpl.class);
	@Autowired
	private StandardUnitPictureWriteManage standardUnitPictureWriteManage;
	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertStandardUnitPictureWithTx(StandardUnitPictureDTO dto) {
		StandardUnitPicturePO po = StandardUnitPictureConverter.toPO(dto);
		Long rt = standardUnitPictureWriteManage.insertStandardUnitPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardUnitPictureWithTx(StandardUnitPictureDTO dto) {
		StandardUnitPicturePO po = StandardUnitPictureConverter.toPO(dto);
		int rt = standardUnitPictureWriteManage.updateStandardUnitPictureWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardUnitPictureWithTx(StandardUnitPictureDTO dto) {
		StandardUnitPicturePO po = StandardUnitPictureConverter.toPO(dto);
		int rt = standardUnitPictureWriteManage.deleteStandardUnitPictureWithTx(po);		
		return rt;
	}
	/**
	 * 根据suid删除su图片关系表
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long merchantProdId) {
		// TODO Auto-generated method stub
		return standardUnitPictureWriteManage.deleteByStandardUnitIdWithTx(merchantProdId);
	}


	@Override
	public void saveStandardUnitPicture(List<Long> suIdList, List<Long> merchantPictureIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<StandardUnitPicturePO> res=new ArrayList<>();
		for(int i=0;i<suIdList.size();i++){
			StandardUnitPicturePO standardUnitPicturePO = new StandardUnitPicturePO();
			standardUnitPicturePO.setMerchantPictureId(merchantPictureIdList.get(i));
			standardUnitPicturePO.setType(1);
			standardUnitPicturePO.setPlatformId(7L);
			standardUnitPicturePO.setStandardUnitId(suIdList.get(i));
			res.add(standardUnitPicturePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardUnitPicture保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardUnitPicture",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardUnitPicture]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardUnitPicturePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardUnitPicture");
			saveListFactory.setStandardUnitPicturePOList(list);
			saveListFactory.setStandardUnitPictureWriteManage(standardUnitPictureWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();
	}
}
	