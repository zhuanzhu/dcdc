package com.egeo.components.product.service.write.impl.Thread;

import com.egeo.components.product.manage.write.*;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.po.*;
import com.egeo.utils.cache.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 0.0 on 2019/4/9.
 */
@Component
public class SaveListFactory implements Runnable{
    Logger logger = LoggerFactory.getLogger(this.getClass());
private JedisUtil jedisUtil;

    public void setJedisUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    private ProductWriteManage productWriteManage;

    private PictureWriteManage pictureWriteManage;

    private AttributeValueWriteManage attributeValueWriteManage;

    private ProductAttNameWriteManage productAttNameWriteManage;

    private ProductAttValueWriteManage productAttValueWriteManage;

    private ProductDescriptionWriteManage productDescriptionWriteManage;

    private ProductPictureWriteManage productPictureWriteManage;

    private StandardProductUnitWriteManage standardProductUnitWriteManage;

    private StandardProductUnitPictureWriteManage standardProductUnitPictureWriteManage;
    private StandardProductUnitAttNameWriteManage standardProductUnitAttNameWriteManage;

    private StandardProductUnitAttValueWriteManage standardProductUnitAttValueWriteManage;

    private StandardProductUnitDescriptionWriteManage standardProductUnitDescriptionWriteManage;

    private SkuWriteManage skuWriteManage;

    private SkuAttNameWriteManage skuAttNameWriteManage;

    private SkuAttValueWriteManage skuAttValueWriteManage;

    private MerchantProductWriteManage merchantProductWriteManage;

    private MerchantProductStoreWriteManage merchantProductStoreWriteManage;

    private MerchantProdPictureWriteManage merchantProdPictureWriteManage;

    private MerchantPictureWriteManage merchantPictureWriteManage;

    private MerchantProductCompanyWriteManage merchantProductCompanyWriteManage;

    private MerchantProdDescribeWriteManage merchantProdDescribeWriteManage;

    private MerchantProdClientWriteManage merchantProdClientWriteManage;

    private StandardUnitStoreWriteManage standardUnitStoreWriteManage;

    private StandardUnitClientWriteManage standardUnitClientWriteManage;

    private StandardUnitCompanyWriteManage standardUnitCompanyWriteManage;

    private StandardUnitPictureWriteManage standardUnitPictureWriteManage;

    private StandardUnitDescribeWriteManage standardUnitDescribeWriteManage;

    private StandardUnitWriteManage standardUnitWriteManage;

    private CommodityProductUnitWriteManage commodityProductUnitWriteManage;

    private ProductUnitWriteManage productUnitWriteManage;
    private JdProductInnerIdWriteManage jdProductInnerIdWriteManage;

    public void setJdProductInnerIdWriteManage(JdProductInnerIdWriteManage jdProductInnerIdWriteManage) {
        this.jdProductInnerIdWriteManage = jdProductInnerIdWriteManage;
    }

    public void setJdProductWriteManage(JdProductWriteManage jdProductWriteManage) {
        this.jdProductWriteManage = jdProductWriteManage;
    }

    private JdProductWriteManage jdProductWriteManage;

    private SuSerachRuleWriteManage suSerachRuleWriteManage;
    private CountDownLatch latch;
    private String saveType;
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }


    private List<SuSerachRulePO> suSerachRuleList;
    private List<ProductPO> productPOList;
    private List<PicturePO> picturePOList;
    private List<AttributeValuePO> attributeValuePOList;
    private List<ProductAttNamePO> productAttNamePOList;
    private List<ProductAttValuePO> productAttValuePOList;
    private List<ProductDescriptionPO> productDescriptionPOList;
    private List<ProductPicturePO> productPicturePOList;
    private List<StandardProductUnitPO> standardProductUnitPOList;
    private List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOList;
    private List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList;
    private List<StandardProductUnitDescriptionPO> standardProductUnitDescriptionPOList;
    private List<StandardProductUnitPicturePO> standardProductUnitPicturePOList;
    private List<SkuPO> skuPOList;
    private List<SkuAttNamePO> skuAttNamePOList;
    private List<SkuAttValuePO> skuAttValuePOList;
    private List<MerchantProductPO> merchantProductPOList;
    private List<MerchantProductPO> merchantProductPricePOList;
    private List<MerchantProductStorePO> merchantProductStorePOList;
    private List<MerchantProductCompanyPO> merchantProductCompanyPOList;
    private List<MerchantPicturePO> merchantPicturePOList;
    private List<MerchantProdPicturePO> merchantProdPicturePOList;
    private List<MerchantProdDescribePO> merchantProdDescribePOList;
    private List<MerchantProdClientPO> merchantProdClientPOList;
    private List<StandardUnitStorePO> standardUnitStorePOList;
    private List<StandardUnitPO> standardUnitPOList;
    private List<StandardUnitPO> standardUnitPricePOList;
    private List<StandardUnitPicturePO> standardUnitPicturePOList;
    private List<StandardUnitCompanyPO> standardUnitCompanyPOList;
    private List<StandardUnitClientPO> standardUnitClientPOList;
    private List<ProductUnitPO> productUnitPOList;
    private List<ProductUnitPO> productUnitPricePOList;
    private List<CommodityProductUnitPO> commodityProductUnitPOList;
    private List<CommodityProductUnitPO> commodityProductUnitPricePOList;
    private List<JdProductPO> jdProductPOList;
    private List<JdProductPO> updateList;
    private List<JdProductInnerIdPO> jdProductInnerIdPOList;
    private List<Long> jdProductIdList;

    public List<Long> getJdProductIdList() {
        return jdProductIdList;
    }

    public void setJdProductIdList(List<Long> jdProductIdList) {
        this.jdProductIdList = jdProductIdList;
    }

    public void setJdProductInnerIdPOList(List<JdProductInnerIdPO> jdProductInnerIdPOList) {
        this.jdProductInnerIdPOList = jdProductInnerIdPOList;
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

    public void setProductAttNameWriteManage(ProductAttNameWriteManage productAttNameWriteManage) {
        this.productAttNameWriteManage = productAttNameWriteManage;
    }

    public void setProductAttValueWriteManage(ProductAttValueWriteManage productAttValueWriteManage) {
        this.productAttValueWriteManage = productAttValueWriteManage;
    }

    public void setProductDescriptionWriteManage(ProductDescriptionWriteManage productDescriptionWriteManage) {
        this.productDescriptionWriteManage = productDescriptionWriteManage;
    }

    public void setProductPictureWriteManage(ProductPictureWriteManage productPictureWriteManage) {
        this.productPictureWriteManage = productPictureWriteManage;
    }

    public void setStandardProductUnitWriteManage(StandardProductUnitWriteManage standardProductUnitWriteManage) {
        this.standardProductUnitWriteManage = standardProductUnitWriteManage;
    }

    public void setStandardProductUnitPictureWriteManage(StandardProductUnitPictureWriteManage standardProductUnitPictureWriteManage) {
        this.standardProductUnitPictureWriteManage = standardProductUnitPictureWriteManage;
    }

    public void setStandardProductUnitAttNameWriteManage(StandardProductUnitAttNameWriteManage standardProductUnitAttNameWriteManage) {
        this.standardProductUnitAttNameWriteManage = standardProductUnitAttNameWriteManage;
    }

    public void setStandardProductUnitAttValueWriteManage(StandardProductUnitAttValueWriteManage standardProductUnitAttValueWriteManage) {
        this.standardProductUnitAttValueWriteManage = standardProductUnitAttValueWriteManage;
    }

    public void setStandardProductUnitDescriptionWriteManage(StandardProductUnitDescriptionWriteManage standardProductUnitDescriptionWriteManage) {
        this.standardProductUnitDescriptionWriteManage = standardProductUnitDescriptionWriteManage;
    }

    public void setSkuWriteManage(SkuWriteManage skuWriteManage) {
        this.skuWriteManage = skuWriteManage;
    }

    public void setSkuAttNameWriteManage(SkuAttNameWriteManage skuAttNameWriteManage) {
        this.skuAttNameWriteManage = skuAttNameWriteManage;
    }

    public void setSkuAttValueWriteManage(SkuAttValueWriteManage skuAttValueWriteManage) {
        this.skuAttValueWriteManage = skuAttValueWriteManage;
    }

    public void setMerchantProductWriteManage(MerchantProductWriteManage merchantProductWriteManage) {
        this.merchantProductWriteManage = merchantProductWriteManage;
    }

    public void setMerchantProductStoreWriteManage(MerchantProductStoreWriteManage merchantProductStoreWriteManage) {
        this.merchantProductStoreWriteManage = merchantProductStoreWriteManage;
    }

    public void setMerchantProdPictureWriteManage(MerchantProdPictureWriteManage merchantProdPictureWriteManage) {
        this.merchantProdPictureWriteManage = merchantProdPictureWriteManage;
    }

    public void setMerchantPictureWriteManage(MerchantPictureWriteManage merchantPictureWriteManage) {
        this.merchantPictureWriteManage = merchantPictureWriteManage;
    }

    public void setMerchantProductCompanyWriteManage(MerchantProductCompanyWriteManage merchantProductCompanyWriteManage) {
        this.merchantProductCompanyWriteManage = merchantProductCompanyWriteManage;
    }

    public void setMerchantProdDescribeWriteManage(MerchantProdDescribeWriteManage merchantProdDescribeWriteManage) {
        this.merchantProdDescribeWriteManage = merchantProdDescribeWriteManage;
    }

    public void setMerchantProdClientWriteManage(MerchantProdClientWriteManage merchantProdClientWriteManage) {
        this.merchantProdClientWriteManage = merchantProdClientWriteManage;
    }

    public void setStandardUnitStoreWriteManage(StandardUnitStoreWriteManage standardUnitStoreWriteManage) {
        this.standardUnitStoreWriteManage = standardUnitStoreWriteManage;
    }

    public void setStandardUnitClientWriteManage(StandardUnitClientWriteManage standardUnitClientWriteManage) {
        this.standardUnitClientWriteManage = standardUnitClientWriteManage;
    }

    public void setStandardUnitCompanyWriteManage(StandardUnitCompanyWriteManage standardUnitCompanyWriteManage) {
        this.standardUnitCompanyWriteManage = standardUnitCompanyWriteManage;
    }

    public void setStandardUnitPictureWriteManage(StandardUnitPictureWriteManage standardUnitPictureWriteManage) {
        this.standardUnitPictureWriteManage = standardUnitPictureWriteManage;
    }

    public void setStandardUnitDescribeWriteManage(StandardUnitDescribeWriteManage standardUnitDescribeWriteManage) {
        this.standardUnitDescribeWriteManage = standardUnitDescribeWriteManage;
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

    public void setSuSerachRuleWriteManage(SuSerachRuleWriteManage suSerachRuleWriteManage) {
        this.suSerachRuleWriteManage = suSerachRuleWriteManage;
    }

    public void setJdProductPOList(List<JdProductPO> jdProductPOList) {
        this.jdProductPOList = jdProductPOList;
    }

    public void setCommodityProductUnitPricePOList(List<CommodityProductUnitPO> commodityProductUnitPricePOList) {
        this.commodityProductUnitPricePOList = commodityProductUnitPricePOList;
    }

    public void setProductUnitPricePOList(List<ProductUnitPO> productUnitPricePOList) {
        this.productUnitPricePOList = productUnitPricePOList;
    }

    public void setStandardUnitPricePOList(List<StandardUnitPO> standardUnitPricePOList) {
        this.standardUnitPricePOList = standardUnitPricePOList;
    }

    public void setMerchantProductPricePOList(List<MerchantProductPO> merchantProductPricePOList) {
        this.merchantProductPricePOList = merchantProductPricePOList;
    }

    public void setUpdateList(List<JdProductPO> updateList) {
        this.updateList = updateList;
    }

    public void setCommodityProductUnitPOList(List<CommodityProductUnitPO> commodityProductUnitPOList) {
        this.commodityProductUnitPOList = commodityProductUnitPOList;
    }

    public void setProductUnitPOList(List<ProductUnitPO> productUnitPOList) {
        this.productUnitPOList = productUnitPOList;
    }

    public void setSuSerachRuleList(List<SuSerachRulePO> suSerachRuleList) {
        this.suSerachRuleList = suSerachRuleList;
    }

    public void setStandardUnitClientPOList(List<StandardUnitClientPO> standardUnitClientPOList) {
        this.standardUnitClientPOList = standardUnitClientPOList;
    }

    public void setStandardUnitCompanyPOList(List<StandardUnitCompanyPO> standardUnitCompanyPOList) {
        this.standardUnitCompanyPOList = standardUnitCompanyPOList;
    }

    public void setStandardUnitPicturePOList(List<StandardUnitPicturePO> standardUnitPicturePOList) {
        this.standardUnitPicturePOList = standardUnitPicturePOList;
    }

    private List<StandardUnitDescribePO> standardUnitDescribePOList;

    public void setStandardUnitDescribePOList(List<StandardUnitDescribePO> standardUnitDescribePOList) {
        this.standardUnitDescribePOList = standardUnitDescribePOList;
    }

    public void setStandardUnitPOList(List<StandardUnitPO> standardUnitPOList) {
        this.standardUnitPOList = standardUnitPOList;
    }

    public void setStandardUnitStorePOList(List<StandardUnitStorePO> standardUnitStorePOList) {
        this.standardUnitStorePOList = standardUnitStorePOList;
    }

    public void setMerchantProdClientPOList(List<MerchantProdClientPO> merchantProdClientPOList) {
        this.merchantProdClientPOList = merchantProdClientPOList;
    }

    public void setMerchantProdDescribePOList(List<MerchantProdDescribePO> merchantProdDescribePOList) {
        this.merchantProdDescribePOList = merchantProdDescribePOList;
    }

    public void setMerchantProdPicturePOList(List<MerchantProdPicturePO> merchantProdPicturePOList) {
        this.merchantProdPicturePOList = merchantProdPicturePOList;
    }

    public void setMerchantPicturePOList(List<MerchantPicturePO> merchantPicturePOList) {
        this.merchantPicturePOList = merchantPicturePOList;
    }

    public void setMerchantProductCompanyPOList(List<MerchantProductCompanyPO> merchantProductCompanyPOList) {
        this.merchantProductCompanyPOList = merchantProductCompanyPOList;
    }

    public void setMerchantProductStorePOList(List<MerchantProductStorePO> merchantProductStorePOList) {
        this.merchantProductStorePOList = merchantProductStorePOList;
    }

    public void setMerchantProductPOList(List<MerchantProductPO> merchantProductPOList) {
        this.merchantProductPOList = merchantProductPOList;
    }

    public void setSkuAttValuePOList(List<SkuAttValuePO> skuAttValuePOList) {
        this.skuAttValuePOList = skuAttValuePOList;
    }

    public void setSkuAttNamePOList(List<SkuAttNamePO> skuAttNamePOList) {
        this.skuAttNamePOList = skuAttNamePOList;
    }

    public void setSkuPOList(List<SkuPO> skuPOList) {
        this.skuPOList = skuPOList;
    }

    public void setStandardProductUnitPicturePOList(List<StandardProductUnitPicturePO> standardProductUnitPicturePOList) {
        this.standardProductUnitPicturePOList = standardProductUnitPicturePOList;
    }

    public void setStandardProductUnitDescriptionPOList(List<StandardProductUnitDescriptionPO> standardProductUnitDescriptionPOList) {
        this.standardProductUnitDescriptionPOList = standardProductUnitDescriptionPOList;
    }

    public void setStandardProductUnitAttValuePOList(List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList) {
        this.standardProductUnitAttValuePOList = standardProductUnitAttValuePOList;
    }

    public void setStandardProductUnitAttNamePOList(List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOList) {
        this.standardProductUnitAttNamePOList = standardProductUnitAttNamePOList;
    }

    public void setStandardProductUnitPOList(List<StandardProductUnitPO> standardProductUnitPOList) {
        this.standardProductUnitPOList = standardProductUnitPOList;
    }

    public void setProductPicturePOList(List<ProductPicturePO> productPicturePOList) {
        this.productPicturePOList = productPicturePOList;
    }

    public void setProductDescriptionPOList(List<ProductDescriptionPO> productDescriptionPOList) {
        this.productDescriptionPOList = productDescriptionPOList;
    }

    public void setProductAttValuePOList(List<ProductAttValuePO> productAttValuePOList) {
        this.productAttValuePOList = productAttValuePOList;
    }

    public void setProductAttNamePOList(List<ProductAttNamePO> productAttNamePOList) {
        this.productAttNamePOList = productAttNamePOList;
    }

    public void setAttributeValuePOList(List<AttributeValuePO> attributeValuePOList) {
        this.attributeValuePOList = attributeValuePOList;
    }
    public void setProductPOList(List<ProductPO> productPOList) {
        this.productPOList = productPOList;
    }

    public void setPicturePOList(List<PicturePO> picturePOList) {
        this.picturePOList = picturePOList;
    }

    @Override
    public void run() {
        logger.info("*******************************进入多线程*********************************");
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.info("SaveListFactory等待异常:->"+saveType);
            e.printStackTrace();
        }
        if(saveType.equals("ProductList")){
            logger.error(Thread.currentThread().getName()+"开始异步处理product保存");
            productWriteManage.saveProductList(productPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理product保存");

        }else if(saveType.equals("Picture")){
            logger.error(Thread.currentThread().getName()+"开始异步处理Picture保存");
            pictureWriteManage.savePicture(picturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理Picture保存");
        }else if(saveType.equals("AttValue")){
            logger.error(Thread.currentThread().getName()+"开始异步处理AttValue保存");
            attributeValueWriteManage.saveAttValue(attributeValuePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理AttValue保存");
        }else if(saveType.equals("ProductAttName")){
            logger.error(Thread.currentThread().getName()+"开始异步处理ProductAttName保存");
            productAttNameWriteManage.saveProductAttName(productAttNamePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理ProductAttName保存");
        }else if(saveType.equals("ProductAttValue")){
            logger.error(Thread.currentThread().getName()+"开始异步处理ProductAttValue保存");
            productAttValueWriteManage.saveProductAttValue(productAttValuePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理ProductAttValue保存");
        }else if(saveType.equals("ProductDescription")){
            logger.error(Thread.currentThread().getName()+"开始异步处理ProductDescription保存");
            productDescriptionWriteManage.saveProductDescriptionList(productDescriptionPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理ProductDescription保存");
        }else if(saveType.equals("ProductPicture")){
            logger.error(Thread.currentThread().getName()+"开始异步处理ProductPicture保存");
            productPictureWriteManage.saveProductPicture(productPicturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理ProductPicture保存");
        }else if(saveType.equals("SPU")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SPU保存");
            standardProductUnitWriteManage.saveSPU(standardProductUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SPU保存");
        }else if(saveType.equals("StandardProductUnitAttName")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardProductUnitAttName保存");
            standardProductUnitAttNameWriteManage.saveStandardProductUnitAttName(standardProductUnitAttNamePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardProductUnitAttName保存");
        }else if(saveType.equals("SPUValue")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SPUValue保存");
            standardProductUnitAttValueWriteManage.saveSPUValue(standardProductUnitAttValuePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SPUValue保存");
        }else if(saveType.equals("StandardProductUnitDescription")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardProductUnitDescription保存");
            standardProductUnitDescriptionWriteManage.saveStandardProductUnitDescription(standardProductUnitDescriptionPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardProductUnitDescription保存");
        }else if(saveType.equals("StandardProductUnitPicture")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardProductUnitPicture保存");
            standardProductUnitPictureWriteManage.saveStandardProductUnitPicture(standardProductUnitPicturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardProductUnitPicture保存");
        }else if(saveType.equals("Sku")){
            logger.error(Thread.currentThread().getName()+"开始异步处理Sku保存");
            skuWriteManage.saveSku(skuPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理Sku保存");
        }else if(saveType.equals("SkuAttName")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SkuAttName保存");
            skuAttNameWriteManage.saveSkuAttName(skuAttNamePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SkuAttName保存");
        }else if(saveType.equals("SkuAttValuePO")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SkuAttValuePO保存");
            skuAttValueWriteManage.saveSkuAttValuePO(skuAttValuePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SkuAttValuePO保存");
        }else if(saveType.equals("MerchantProduct")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantProduct保存");
            merchantProductWriteManage.saveMerchantProduct(merchantProductPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantProduct保存");
        }else if(saveType.equals("MerchantProductStore")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantProductStore保存");
            merchantProductStoreWriteManage.saveMerchantProductStore(merchantProductStorePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantProductStore保存");
        }else if(saveType.equals("MerchantProdPicture")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantProdPicture保存");
            merchantProdPictureWriteManage.saveMerchantProdPicture(merchantProdPicturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantProdPicture保存");
        }else if(saveType.equals("MerchantPicture")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantPicture保存");
            merchantPictureWriteManage.saveMerchantPicture(merchantPicturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantPicture保存");
        } else if(saveType.equals("MerchantProductCompany")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantProductCompany保存");
            merchantProductCompanyWriteManage.saveMerchantProductCompany(merchantProductCompanyPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantProductCompany保存");
        }else if(saveType.equals("MerchantProdDescribe")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantProdDescribe保存");
           merchantProdDescribeWriteManage.saveMerchantProdDescribe(merchantProdDescribePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantProdDescribe保存");
        }else if(saveType.equals("MerchantProdClient")){
            logger.error(Thread.currentThread().getName()+"开始异步处理MerchantProdClient保存");
            merchantProdClientWriteManage.saveMerchantProdClient(merchantProdClientPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理MerchantProdClient保存");
        }else if(saveType.equals("StandardUnitClient")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardUnitClient保存");
            standardUnitClientWriteManage.saveStandardUnitClient(standardUnitClientPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardUnitClient保存");
        }else if(saveType.equals("StandardUnitCompany")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardUnitCompany保存");
            standardUnitCompanyWriteManage.saveStandardUnitCompany(standardUnitCompanyPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardUnitCompany保存");
        }else if(saveType.equals("StandardUnitStore")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardUnitStore保存");
            standardUnitStoreWriteManage.saveStandardUnitStore(standardUnitStorePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardUnitStore保存");
        }else if(saveType.equals("StandardUnitPicture")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardUnitPicture保存");
            standardUnitPictureWriteManage.saveStandardUnitPicture(standardUnitPicturePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardUnitPicture保存");
        }else if(saveType.equals("StandardUnitDescribe")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardUnitDescribe保存");
            standardUnitDescribeWriteManage.saveStandardUnitDescribe(standardUnitDescribePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardUnitDescribe保存");
        }else if(saveType.equals("StandardUnit")){
            logger.error(Thread.currentThread().getName()+"开始异步处理StandardUnit保存");
            standardUnitWriteManage.saveStandardUnit(standardUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理StandardUnit保存");
        }else if(saveType.equals("CommodityProductUnit")){
            logger.error(Thread.currentThread().getName()+"开始异步处理CommodityProductUnit保存");
            commodityProductUnitWriteManage.saveCommodityProductUnit(commodityProductUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理CommodityProductUnit保存");
        }else if(saveType.equals("ProductUnit")){
            logger.error(Thread.currentThread().getName()+"开始异步处理ProductUnit保存");
            productUnitWriteManage.saveProductUnit(productUnitPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理ProductUnit保存");
        }else if(saveType.equals("JdProductListFirst")){
            logger.error(Thread.currentThread().getName()+"开始异步处理JdProductListFirst保存");

            jdProductWriteManage.saveJdProductListFirst(jdProductPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理JdProductListFirst保存");


        }else if(saveType.equals("updateJdProductList")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateJdProductList更新");
            jdProductWriteManage.updateList(updateList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateJdProductList更新");
        }else if(saveType.equals("SuSerachRule")){
            logger.error(Thread.currentThread().getName()+"开始异步处理SuSerachRule更新");
            suSerachRuleWriteManage.saveSuSerachRulet(suSerachRuleList);
            logger.error(Thread.currentThread().getName()+"完成异步处理SuSerachRule更新");
        }else if(saveType.equals("updateMerchantProductPrice")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateMerchantProductPrice更新");
            merchantProductWriteManage.updateMerchantProductPrice(merchantProductPricePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateMerchantProductPrice更新:"+merchantProductPricePOList.size());
        }else if(saveType.equals("updateStandardUnitPrice")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateStandardUnitPrice更新");
            standardUnitWriteManage.updateStandardUnitPrice(standardUnitPricePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateStandardUnitPrice更新:"+standardUnitPricePOList.size());
        }else if(saveType.equals("updateProductUnitPrice")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateProductUnitPrice更新:");
            productUnitWriteManage.updateProductUnitPrice(productUnitPricePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateProductUnitPrice更新:"+productUnitPricePOList.size());
        }else if(saveType.equals("updateCommodityProductUnitPrice")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateCommodityProductUnitPrice更新");
            commodityProductUnitWriteManage.updateCommodityProductUnitPrice(commodityProductUnitPricePOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateCommodityProductUnitPrice更新:"+commodityProductUnitPricePOList.size());
        }else if(saveType.equals("JdProductInnerIdList")){
            logger.error(Thread.currentThread().getName()+"开始异步处理JdProductInnerIdList更新");
            jdProductInnerIdWriteManage.saveJdProductInnerIdList(jdProductInnerIdPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理JdProductInnerIdList更新");
        }else if(saveType.equals("updateJdProductPrice")){
            logger.error(Thread.currentThread().getName()+"开始异步处理updateJdProductPrice更新");
            jdProductWriteManage.updateJdProductPrice(jdProductPOList);
            logger.error(Thread.currentThread().getName()+"完成异步处理updateJdProductPrice更新:"+jdProductPOList.size());
        }else if(saveType.equals("updateJdProductProductCreateTime")){
            logger.error(Thread.currentThread().getName()+"开始异步处理京东本地上铺创建时间更新");
            jdProductWriteManage.updateProductCreateTime(jdProductIdList);
            logger.error(Thread.currentThread().getName()+"完成异步处理京东本地上铺创建时间更新");
        }



    }
}
