package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.ProductDescriptionConverter;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.manage.read.ProductDescriptionReadManage;
import com.egeo.components.product.manage.write.ProductDescriptionWriteManage;
import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.components.product.service.write.ProductDescriptionWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("productDescriptionWriteService")
public class ProductDescriptionWriteServiceImpl  implements ProductDescriptionWriteService {
	private static final XLogger logger = XLogger.getLogger(ProductDescriptionWriteServiceImpl.class);
	@Autowired
	private ProductDescriptionWriteManage productDescriptionWriteManage;
	
	@Autowired
        private ProductDescriptionReadManage productDescriptionReadManage;
    @Resource
    private JedisUtil jedisUtil;

    @Override
        public Long saveProductDescriptionWithTx(ProductDescriptionDTO dto) {
            return productDescriptionWriteManage.saveProductDescription(ProductDescriptionConverter.toPO(dto));
        }

        @Override
        public String updateProductDescriptionWithTx(ProductDescriptionDTO dto) {
            ProductDescriptionPO productDescriptionPO = ProductDescriptionConverter.toPO(dto);
            ProductDescriptionPO po = new ProductDescriptionPO();
            po.setProductId(productDescriptionPO.getProductId());
            //根据id查询产品描述信息
            List<ProductDescriptionPO> list = productDescriptionReadManage.findAll(po);
            if(list.size() > 0){
            	ProductDescriptionPO productDescriptionPO2 = list.get(0);
                if(productDescriptionPO.getContent() != null){
                    productDescriptionPO2.setContent(productDescriptionPO.getContent());
                }
                return productDescriptionWriteManage.updateProductDescriptionWithTx(productDescriptionPO2);
            }else{
            	return null;
            }
            
        }

        @Override
        public String deleteByProductIdWithTx(Long productId) {
            
            return productDescriptionWriteManage.deleteByProductId(productId);
        }



    @Override
    public void saveProductDescription(List<Long> productIdList){
        Integer SIZE=10000;
        ExecutorService executor = Executors.newCachedThreadPool();

        List<ProductDescriptionPO> res = new ArrayList<>();
        for(int i=0;i<productIdList.size();i++){
            ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO();
            productDescriptionPO.setProductId(productIdList.get(i));
            productDescriptionPO.setContent("");
            productDescriptionPO.setPlatformId(7L);
            res.add(productDescriptionPO);
        }
        if(EmptyUtil.isEmpty(res)){
            return;
        }
        logger.info("开始异步处理ProductDescription保存");
        CountDownLatch countDownLatch = new CountDownLatch(1);

        int page=((res.size()-1)/SIZE)+1;
        try {
            jedisUtil.set("ProductDescription",24*60*60,page);
        }catch (Exception e){
            logger.info("[ProductDescription]设置redis失败");
        }
        for(int i=0;i<page;i++) {
            List<ProductDescriptionPO> list = new ArrayList<>();
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
            saveListFactory.setSaveType("ProductDescription");
            saveListFactory.setProductDescriptionPOList(list);
            saveListFactory.setProductDescriptionWriteManage(productDescriptionWriteManage);
            saveListFactory.setJedisUtil(jedisUtil);

            executor.execute(saveListFactory);
        }
        countDownLatch.countDown();
        executor.shutdown();

    }

    @Override
    public void updateProductDescriptionSWithTx(ProductDescriptionDTO productDescriptionDTO) {
        productDescriptionWriteManage.updateProductDescriptionSWithTx(ProductDescriptionConverter.toPO(productDescriptionDTO));
    }
}
	