package com.egeo.components.product.service.write.impl.Thread;

import com.egeo.components.product.manage.write.*;
import com.egeo.components.product.po.*;
import com.egeo.utils.cache.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 0.0 on 2019/5/27.
 */
public class UpdateListFactory implements Runnable {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private JedisUtil jedisUtil;

    private CountDownLatch latch;
    private String saveType;

    private List<ProductPO> productPOList;
    private List<PicturePO> picturePOList;
    private List<AttributeValuePO> attributeValuePOList;
    private List<StandardProductUnitPO> standardProductUnitPOList;
    private List<SkuPO> skuPOList;
    private List<MerchantProductPO> merchantProductPOList;
    private List<StandardUnitPO> standardUnitPOList;
    private List<SuSerachRulePO> suSerachRulePOList;
    private List<ProductUnitPO> productUnitPOList;
    private List<CommodityProductUnitPO> commodityProductUnitPOList;


    private ProductWriteManage productWriteManage;
    private PictureWriteManage pictureWriteManage;
    private AttributeValueWriteManage attributeValueWriteManage;
    private StandardProductUnitWriteManage standardProductUnitWriteManage;
    private SkuWriteManage skuWriteManage;
    private MerchantProductWriteManage merchantProductWriteManage;
    private StandardUnitWriteManage standardUnitWriteManage;
    private CommodityProductUnitWriteManage commodityProductUnitWriteManage;
    private ProductUnitWriteManage productUnitWriteManage;
    private SuSerachRuleWriteManage suSerachRuleWriteManage;


    public void setSuSerachRuleWriteManage(SuSerachRuleWriteManage suSerachRuleWriteManage) {
        this.suSerachRuleWriteManage = suSerachRuleWriteManage;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    public void setProductPOList(List<ProductPO> productPOList) {
        this.productPOList = productPOList;
    }

    public void setPicturePOList(List<PicturePO> picturePOList) {
        this.picturePOList = picturePOList;
    }

    public void setAttributeValuePOList(List<AttributeValuePO> attributeValuePOList) {
        this.attributeValuePOList = attributeValuePOList;
    }

    public void setStandardProductUnitPOList(List<StandardProductUnitPO> standardProductUnitPOList) {
        this.standardProductUnitPOList = standardProductUnitPOList;
    }

    public void setSkuPOList(List<SkuPO> skuPOList) {
        this.skuPOList = skuPOList;
    }

    public void setMerchantProductPOList(List<MerchantProductPO> merchantProductPOList) {
        this.merchantProductPOList = merchantProductPOList;
    }

    public void setStandardUnitPOList(List<StandardUnitPO> standardUnitPOList) {
        this.standardUnitPOList = standardUnitPOList;
    }

    public void setSuSerachRulePOList(List<SuSerachRulePO> suSerachRulePOList) {
        this.suSerachRulePOList = suSerachRulePOList;
    }

    public void setProductUnitPOList(List<ProductUnitPO> productUnitPOList) {
        this.productUnitPOList = productUnitPOList;
    }

    public void setCommodityProductUnitPOList(List<CommodityProductUnitPO> commodityProductUnitPOList) {
        this.commodityProductUnitPOList = commodityProductUnitPOList;
    }

    public void setProductWriteManage(ProductWriteManage productWriteManage) {
        this.productWriteManage = productWriteManage;
    }

    public void setPictureWriteManage(PictureWriteManage pictureWriteManage) {
        this.pictureWriteManage = pictureWriteManage;
    }

    public void setAttributeValueWriteManage(AttributeValueWriteManage attributeValueWriteManage) {
        this.attributeValueWriteManage = attributeValueWriteManage;
    }

    public void setStandardProductUnitWriteManage(StandardProductUnitWriteManage standardProductUnitWriteManage) {
        this.standardProductUnitWriteManage = standardProductUnitWriteManage;
    }

    public void setSkuWriteManage(SkuWriteManage skuWriteManage) {
        this.skuWriteManage = skuWriteManage;
    }

    public void setMerchantProductWriteManage(MerchantProductWriteManage merchantProductWriteManage) {
        this.merchantProductWriteManage = merchantProductWriteManage;
    }

    public void setStandardUnitWriteManage(StandardUnitWriteManage standardUnitWriteManage) {
        this.standardUnitWriteManage = standardUnitWriteManage;
    }

    public void setCommodityProductUnitWriteManage(CommodityProductUnitWriteManage commodityProductUnitWriteManage) {
        this.commodityProductUnitWriteManage = commodityProductUnitWriteManage;
    }

    public void setProductUnitWriteManage(ProductUnitWriteManage productUnitWriteManage) {
        this.productUnitWriteManage = productUnitWriteManage;
    }

    public void setJedisUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    @Override
    public void run() {
        logger.info("*******************************进入更新本地商品多线程*********************************");
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.info("SaveListFactory等待异常:->"+saveType);
            e.printStackTrace();
        }
        if(saveType.equals("ProductList")){
            logger.error(Thread.currentThread().getName()+"开始异步处理product更新");
            productWriteManage.updateProductList(productPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理product更新");
        }else if(saveType.equals("PictureList")){
            logger.error(Thread.currentThread().getName()+"开始异步处理PictureList更新");
            pictureWriteManage.updatePictureList(picturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理PictureList更新");
        }else if(saveType.equals("AttValue")){
            logger.error(Thread.currentThread().getName()+"开始异步处理AttValue更新");
            attributeValueWriteManage.updateAttributeValueList(attributeValuePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理AttValue更新");
        }else if(saveType.equals("SPU")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SPU更新");
            standardProductUnitWriteManage.updateStandardProductUnitList(standardProductUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SPU更新");
        }else if(saveType.equals("Sku")){
            logger.error(Thread.currentThread().getName()+"开始异步处理Sku更新");
            skuWriteManage.updateSkuList(skuPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理Sku更新");
        }else if(saveType.equals("updateMerchantProductPrice")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateMerchantProductPrice更新");
            merchantProductWriteManage.updateMerchantProductList(merchantProductPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateMerchantProductPrice更新");
        }else if(saveType.equals("updateStandardUnitList")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateStandardUnitList更新");
            standardUnitWriteManage.updateSUList(standardUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateStandardUnitList更新");
        }else if(saveType.equals("SuSerachRule")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SuSerachRule更新");
            suSerachRuleWriteManage.updateSuSerachRuleList(suSerachRulePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SuSerachRule更新");
        }else if(saveType.equals("updateProductUnit")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateProductUnit更新");
            productUnitWriteManage.updateProductUnitList(productUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateProductUnit更新");
        }else if(saveType.equals("updateCommodityProductUnit")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateCommodityProductUnit更新");
           commodityProductUnitWriteManage.updateCommodityProductUnitList(commodityProductUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateCommodityProductUnit更新");
        }
    }
}
