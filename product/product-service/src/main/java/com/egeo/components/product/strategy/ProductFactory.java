package com.egeo.components.product.strategy;

import com.egeo.components.product.common.BusinessException;
import com.egeo.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 供应商（渠道）产品服务工厂
 * @Author lsl
 * @Date 2024/4/30 1:12
 * @Version V1.0
 **/
@Service
public class ProductFactory implements ApplicationContextAware {

    /***供应商产品查询策略***/
    private Map<String, SearchProductStrategy> managerServiceMap = new HashMap<>();

    /***供应商产品推送通知策略***/
    private Map<String, ReceiveProductStrategy> productNoticeServiceMap = new HashMap<>();

    /***供应商产品上下线推送通知策略***/
    private Map<String, ProductUpOrDownStrategy> productUpOrDownServiceMap = new HashMap<>();

    /***供应商产品关键字查询策略***/
    private Map<String, KeyWordSearchStrategy> keyWordSearchStrategyMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /***供应商产品查询策略***/
        Map<String,SearchProductStrategy> map = applicationContext.getBeansOfType(SearchProductStrategy.class);
        map.forEach((key,value)->managerServiceMap.put(value.getProductCode(), value));

        /***供应商产品推送通知策略***/
        Map<String,ReceiveProductStrategy> receiveMap = applicationContext.getBeansOfType(ReceiveProductStrategy.class);
        receiveMap.forEach((key,value)->productNoticeServiceMap.put(value.getProductCode(), value));

        /***供应商产品上下线推送通知策略***/
        Map<String,ProductUpOrDownStrategy> productUpOrDownMap = applicationContext.getBeansOfType(ProductUpOrDownStrategy.class);
        productUpOrDownMap.forEach((key,value)->productUpOrDownServiceMap.put(value.getProductCode(), value));

        /***供应商产品关键字查询策略***/
        Map<String,KeyWordSearchStrategy> keyWordSearchStrategyBeanMap = applicationContext.getBeansOfType(KeyWordSearchStrategy.class);
        keyWordSearchStrategyBeanMap.forEach((key,value)->keyWordSearchStrategyMap.put(value.getProductCode(), value));
    }

    public SearchProductStrategy getSearchProductStrategy(String productCode){
        if(!checkHasSupportSearchProduct(productCode)){
            throw new BusinessException("未定义对应的商品码策略");
        }
        return managerServiceMap.get(productCode);
    }

    public boolean checkHasSupportSearchProduct(String productCode){
        if(StringUtils.isEmpty(productCode)){
            throw new BusinessException("缺少商品查找code");
        }
        return managerServiceMap.containsKey(productCode);
    }

    public ReceiveProductStrategy getReceiveProductStrategy(String productCode){
        if(!checkHasSupportSearchProduct(productCode)){
            throw new BusinessException("未定义对应通知的商品码策略");
        }
        return productNoticeServiceMap.get(productCode);
    }

    public boolean checkHasSupportReceiveProduct(String productCode){
        if(StringUtils.isEmpty(productCode)){
            throw new BusinessException("缺少渠道码");
        }
        return productNoticeServiceMap.containsKey(productCode);
    }

    public ProductUpOrDownStrategy getProductUpOrDownStrategy(String productCode){
        if(!checkHasSupportSearchProduct(productCode)){
            throw new BusinessException("未定义对应通知的商品码策略");
        }
        return productUpOrDownServiceMap.get(productCode);
    }

    public boolean checkHasSupportProductUpOrDown(String productCode){
        if(StringUtils.isEmpty(productCode)){
            throw new BusinessException("缺少渠道码");
        }
        return productUpOrDownServiceMap.containsKey(productCode);
    }


    public KeyWordSearchStrategy getKeyWordSearchProductStrategy(String productCode){
        if(!checkHasSupportKeyWordSearchProduct(productCode)){
            throw new BusinessException("未定义对应的商品码策略");
        }
        return keyWordSearchStrategyMap.get(productCode);
    }

    public boolean checkHasSupportKeyWordSearchProduct(String productCode){
        if(StringUtils.isEmpty(productCode)){
            throw new BusinessException("缺少商品查找code");
        }
        return keyWordSearchStrategyMap.containsKey(productCode);
    }
}
