package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MerchantProdPictureConverter;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.components.product.manage.write.MerchantProdPictureWriteManage;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.components.product.service.write.MerchantProdPictureWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("merchantProdPictureWriteService")
public class MerchantProdPictureWriteServiceImpl  implements MerchantProdPictureWriteService {
	private static final XLogger logger = XLogger.getLogger(MerchantProdPictureWriteServiceImpl.class);
	@Autowired
	private MerchantProdPictureWriteManage merchantProdPictureWriteManage;

	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantProdPictureWithTx(MerchantProdPictureDTO dto) {
		MerchantProdPicturePO po = MerchantProdPictureConverter.toPO(dto);
		Long rt = merchantProdPictureWriteManage.insertMerchantProdPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdPictureWithTx(MerchantProdPictureDTO dto) {
		MerchantProdPicturePO po = MerchantProdPictureConverter.toPO(dto);
		int rt = merchantProdPictureWriteManage.updateMerchantProdPictureWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdPictureWithTx(MerchantProdPictureDTO dto) {
		MerchantProdPicturePO po = MerchantProdPictureConverter.toPO(dto);
		int rt = merchantProdPictureWriteManage.deleteMerchantProdPictureWithTx(po);		
		return rt;
	}
	/**
	 * 根据su草稿图片关系id集合批量删除su草稿图片关系
	 * @param merchantProdPictureIds
	 * @return
	 */
	@Override
	public int deleteBymerchantProdPictureIdsWithTx(List<Long> merchantProdPictureIds) {
		// TODO Auto-generated method stub
		return merchantProdPictureWriteManage.deleteBymerchantProdPictureIds(merchantProdPictureIds);
	}
	/**
	 * 根据su商品id删除su商品图片信息
	 */
	@Override
	public int delByMerchantProductId(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProdPictureWriteManage.delByMerchantProductId(merchantProductId);
	}


	@Override
	public void saveMerchantProdPicture(List<Long> merchantProductIdList, List<Long> merchantPictureIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MerchantProdPicturePO> res = new ArrayList<>();
		for(int i=0;i<merchantProductIdList.size();i++){
			MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
			merchantProdPicturePO.setMerchantPictureId(merchantPictureIdList.get(i));
			merchantProdPicturePO.setType(1);
			merchantProdPicturePO.setPlatformId(7L);
			merchantProdPicturePO.setMerchantProdId(merchantProductIdList.get(i));
			res.add(merchantProdPicturePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理MerchantProdPicture保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("MerchantProdPicture",24*60*60,page);
		}catch (Exception e){
			logger.info("[MerchantProdPicture]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<MerchantProdPicturePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("MerchantProdPicture");
			saveListFactory.setMerchantProdPicturePOList(list);
			saveListFactory.setMerchantProdPictureWriteManage(merchantProdPictureWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();

	}
}
	