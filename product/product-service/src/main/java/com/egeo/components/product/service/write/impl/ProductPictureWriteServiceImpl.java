package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.ProductPictureConverter;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.manage.write.ProductPictureWriteManage;
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.components.product.service.write.ProductPictureWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("productPictureWriteService")
public class ProductPictureWriteServiceImpl  implements ProductPictureWriteService {
	private static final XLogger logger = XLogger.getLogger(ProductPictureWriteServiceImpl.class);
	@Autowired
	private ProductPictureWriteManage productPictureWriteManage;

	@Resource
	private JedisUtil jedisUtil;

	@Override
	public Long insertProductPictureWithTx(ProductPictureDTO dto) {
		ProductPicturePO po = ProductPictureConverter.toPO(dto);
		Long rt = productPictureWriteManage.insertProductPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updateProductPictureWithTx(ProductPictureDTO dto) {
		ProductPicturePO po = ProductPictureConverter.toPO(dto);
		int rt = productPictureWriteManage.updateProductPictureWithTx(po);		
		return rt;
	}

	@Override
	public int deleteProductPictureWithTx(ProductPictureDTO dto) {
		ProductPicturePO po = ProductPictureConverter.toPO(dto);
		int rt = productPictureWriteManage.deleteProductPictureWithTx(po);		
		return rt;
	}
	/**
	 * 根据产品id删除产品与图片的关系
	 */
	@Override
	public int deleteByProductIdWithTx(Long id) {
		// TODO Auto-generated method stub
		return productPictureWriteManage.deleteByProductIdWithTx(id);
	}


	@Override
	public void saveProductPicture(List<Long> productIdList, List<Long> pictureIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<ProductPicturePO> res = new ArrayList<>();
		for(int i=0;i<productIdList.size();i++){
			ProductPicturePO productPicturePO = new ProductPicturePO();
			productPicturePO.setProductId(productIdList.get(i));
			productPicturePO.setType(1);
			productPicturePO.setPictureId(pictureIdList.get(i));
			productPicturePO.setPlatformId(7L);
			res.add(productPicturePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理ProductPicture保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("ProductPicture",24*60*60,page);
		}catch (Exception e){
			logger.info("[ProductPicture]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<ProductPicturePO> list = new ArrayList<>();
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
			saveListFactory.setSaveType("ProductPicture");
			saveListFactory.setProductPicturePOList(list);
			saveListFactory.setProductPictureWriteManage(productPictureWriteManage);
			executor.execute(saveListFactory);

		}
		countDownLatch.countDown();
		executor.shutdown();


	}
}
	