package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.StandardProductUnitPictureConverter;
import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.components.product.manage.write.StandardProductUnitPictureWriteManage;
import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.components.product.service.write.StandardProductUnitPictureWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("standardProductUnitPictureWriteService")
public class StandardProductUnitPictureWriteServiceImpl  implements StandardProductUnitPictureWriteService {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitPictureWriteServiceImpl.class);
	@Autowired
	private StandardProductUnitPictureWriteManage standardProductUnitPictureWriteManage;
	@Resource
	private JedisUtil jedisUtil;

	@Override
	public Long insertStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto) {
		StandardProductUnitPicturePO po = StandardProductUnitPictureConverter.toPO(dto);
		Long rt = standardProductUnitPictureWriteManage.insertStandardProductUnitPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updateStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto) {
		StandardProductUnitPicturePO po = StandardProductUnitPictureConverter.toPO(dto);
		int rt = standardProductUnitPictureWriteManage.updateStandardProductUnitPictureWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto) {
		StandardProductUnitPicturePO po = StandardProductUnitPictureConverter.toPO(dto);
		int rt = standardProductUnitPictureWriteManage.deleteStandardProductUnitPictureWithTx(po);		
		return rt;
	}
	/**
	 * 根据spuid删除spu图片关系
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByStandardProductUnitIdWithTx(Long id) {
		// TODO Auto-generated method stub
		return standardProductUnitPictureWriteManage.deleteByStandardProductUnitId(id);
	}


	@Override
	public void saveStandardProductUnitPicture(List<Long> spuIdList, List<Long> pictureIdList){
		ExecutorService executor = Executors.newCachedThreadPool();

		Integer SIZE=10000;
		List<StandardProductUnitPicturePO> res = new ArrayList<>();
		for(int i=0;i<pictureIdList.size();i++){
			StandardProductUnitPicturePO picturePO = new StandardProductUnitPicturePO();
			picturePO.setStandardProductUnitId(spuIdList.get(i));
			picturePO.setPictureId(pictureIdList.get(i));
			picturePO.setType(1);
			picturePO.setPlatformId(7L);
			res.add(picturePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理StandardProductUnitPicture保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("StandardProductUnitPicture",24*60*60,page);
		}catch (Exception e){
			logger.info("[StandardProductUnitPicture]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<StandardProductUnitPicturePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("StandardProductUnitPicture");
			saveListFactory.setStandardProductUnitPicturePOList(list);
			saveListFactory.setStandardProductUnitPictureWriteManage(standardProductUnitPictureWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}

		countDownLatch.countDown();
		executor.shutdown();
	}
}
	