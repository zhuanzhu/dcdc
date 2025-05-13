package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.ProductAttNameConverter;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.manage.write.ProductAttNameWriteManage;
import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.components.product.service.write.ProductAttNameWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("productAttNameWriteService")
public class ProductAttNameWriteServiceImpl  implements ProductAttNameWriteService {
	private static final XLogger logger = XLogger.getLogger(ProductAttNameWriteServiceImpl.class);
	@Autowired
	private ProductAttNameWriteManage productAttNameWriteManage;
    @Resource
    private JedisUtil jedisUtil;


    @Override
        public Long saveProductAttNameWithTx(ProductAttNameDTO dto) {
            dto.setSortValue(1);
            return productAttNameWriteManage.saveProductAttNameWithTx(ProductAttNameConverter.toPO(dto));
        }

        @Override
        public String deleteByProductIdWithTx(Long productId) {
            
            return productAttNameWriteManage.deleteByProductIdWithTx(productId);
        }






    //为每个商品构建4个基本属性和一个规格属性
    private List<ProductAttNamePO> buildProductAttNameList(Long productAttNameId, Long productId){
        List<ProductAttNamePO> res = new ArrayList<>();
        ProductAttNamePO productAttNamePO = new ProductAttNamePO();
        productAttNamePO.setId(productAttNameId);
        productAttNamePO.setProductId(productId);
        productAttNamePO.setAttNameId(100L);
        productAttNamePO.setType(2);
        productAttNamePO.setPlatformId(7L);
        productAttNamePO.setShowPicture(1);

        ProductAttNamePO productAttNamePO1 = new ProductAttNamePO();
        productAttNamePO1.setId(productAttNameId+1);
        productAttNamePO1.setProductId(productId);
        productAttNamePO1.setAttNameId(1L);
        productAttNamePO1.setType(1);
        productAttNamePO1.setPlatformId(7L);
        productAttNamePO1.setShowPicture(0);
        ProductAttNamePO productAttNamePO2 = new ProductAttNamePO();
        productAttNamePO2.setId(productAttNameId+2);
        productAttNamePO2.setProductId(productId);
        productAttNamePO2.setAttNameId(2L);
        productAttNamePO2.setType(1);
        productAttNamePO2.setPlatformId(7L);
        productAttNamePO2.setShowPicture(0);
        ProductAttNamePO productAttNamePO3 = new ProductAttNamePO();
        productAttNamePO3.setId(productAttNameId+3);
        productAttNamePO3.setProductId(productId);
        productAttNamePO3.setAttNameId(3L);
        productAttNamePO3.setType(1);
        productAttNamePO3.setPlatformId(7L);
        productAttNamePO3.setShowPicture(0);
        ProductAttNamePO productAttNamePO4 = new ProductAttNamePO();
        productAttNamePO4.setId(productAttNameId+4);
        productAttNamePO4.setProductId(productId);
        productAttNamePO4.setAttNameId(4L);
        productAttNamePO4.setType(1);
        productAttNamePO4.setPlatformId(7L);
        productAttNamePO4.setShowPicture(0);
        res.add(productAttNamePO);
        res.add(productAttNamePO1);
        res.add(productAttNamePO2);
        res.add(productAttNamePO3);
        res.add(productAttNamePO4);
        return res;

    }
    //批量保存productAttname,每个product有多个
    @Override
    public void saveProductAttName(List<Long> productAttNameIdList, List<Long> productIdList) {
        Integer SIZE=10000;
        ExecutorService executor = Executors.newCachedThreadPool();

        List<ProductAttNamePO> list=new ArrayList<>();
        for (int i = 0; i < productIdList.size(); i++) {
            List<ProductAttNamePO> productAttNamePOS = buildProductAttNameList(productAttNameIdList.get(i),productIdList.get(i));
            list.addAll(productAttNamePOS);
        }

        if(EmptyUtil.isEmpty(list)){
            return;
        }
        logger.info("开始异步处理ProductAttName保存");
        CountDownLatch countDownLatch = new CountDownLatch(1);

        int page=((list.size()-1)/SIZE)+1;
        try {
            jedisUtil.set("ProductAttName",24*60*60,page);
        }catch (Exception e){
            logger.info("[ProductAttName]设置redis失败");
        }
        for(int i=0;i<page;i++) {
            List<ProductAttNamePO> res = new ArrayList<>();
            if (i == (page - 1)) {
                for (int j = i * SIZE; j < list.size(); j++) {
                    res.add(list.get(j));
                }
            } else {
                for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
                    res.add(list.get(j));
                }
            }
            SaveListFactory saveListFactory = new SaveListFactory();
            saveListFactory.setLatch(countDownLatch);
            saveListFactory.setSaveType("ProductAttName");
            saveListFactory.setProductAttNamePOList(res);
            saveListFactory.setProductAttNameWriteManage(productAttNameWriteManage);
            saveListFactory.setJedisUtil(jedisUtil);

            executor.execute(saveListFactory);

        }
        countDownLatch.countDown();
        executor.shutdown();
    }
}
	