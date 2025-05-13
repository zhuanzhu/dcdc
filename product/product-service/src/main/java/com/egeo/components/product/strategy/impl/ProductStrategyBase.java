package com.egeo.components.product.strategy.impl;

import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.utils.cache.JedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/1/9 23:53
 * @Version V1.0
 **/
public abstract class ProductStrategyBase {

    Gson gson = new Gson();

    public Integer getCurrPage(String channelCode, JedisUtil jedisUtil, ChannelProductSearchBean bean){
        String redisKey = bean.getSearchRedisKey();
        Object redisValue = jedisUtil.get(redisKey);
        if(Objects.isNull(redisValue)){
            return 1;
        }
        String v = (String)redisValue;
        List<KeyWordSearchCachePageBean> voList=gson.fromJson(v, new TypeToken<List<KeyWordSearchCachePageBean>>() {
        }.getType());
        if(CollectionUtils.isEmpty(voList)){
            return 1;
        }
        for (KeyWordSearchCachePageBean keyWordSearchCachePageBean : voList) {
            if(keyWordSearchCachePageBean.getChannelCode().equals(channelCode)){
                return keyWordSearchCachePageBean.getCurrPageNo()+1;
            }
        }
        return 1;
    }

    public void saveRedisPage(KeyWordSearchCachePageBean keyWordSearchCachePageBean, JedisUtil jedisUtil,ChannelProductSearchBean bean){
        String redisKey = bean.getSearchRedisKey();
        ProductRedisKeyEnum productRedisKeyEnum = ProductRedisKeyEnum.USER_KEY_WORD_CAT_KEY;
        int seconds = productRedisKeyEnum.getExpireTime();
        Object redisValue = jedisUtil.get(redisKey);
        if(redisValue == null){
            List<KeyWordSearchCachePageBean> pageBeanList = new ArrayList<>();
            pageBeanList.add(keyWordSearchCachePageBean);
            jedisUtil.set(redisKey, seconds, gson.toJson(pageBeanList));
            return;
        }
        String v = (String)redisValue;
        List<KeyWordSearchCachePageBean> voList=gson.fromJson(v, new TypeToken<List<KeyWordSearchCachePageBean>>() {
        }.getType());
        List<String> channelCodes = FHCollectionUtils.listToStrList(voList,KeyWordSearchCachePageBean::getChannelCode);
        if(channelCodes.contains(keyWordSearchCachePageBean.getChannelCode())){
            for (KeyWordSearchCachePageBean wordSearchCachePageBean : voList) {
                if(wordSearchCachePageBean.getChannelCode().equals(keyWordSearchCachePageBean.getChannelCode())){
                    wordSearchCachePageBean.setCurrPageNo(keyWordSearchCachePageBean.getCurrPageNo());
                    wordSearchCachePageBean.setPageSize(keyWordSearchCachePageBean.getPageSize());
                    wordSearchCachePageBean.setTotalCount(keyWordSearchCachePageBean.getTotalCount());
                }
            }
        }else{
            voList.add(keyWordSearchCachePageBean);
        }
        jedisUtil.set(redisKey, seconds, gson.toJson(voList));
    }
}
