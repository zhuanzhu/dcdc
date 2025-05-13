package com.egeo.components.product.strategy.impl.keyword;

import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
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
 * @Date 2024/11/27 0:36
 * @Version V1.0
 **/
public abstract class KeyWordSearchCommonBase {

    public PageResult<StandardUnitDTO> newNullPageResult(Pagination page){
        PageResult<StandardUnitDTO> result = new PageResult<>();
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setTotalSize(0);
        return result;
    }

    public Integer getPlatformPage(KeyWordSearchBean bean){
        Pagination page = bean.getPage();
        int totalCnt = EmptyUtil.isNotEmpty(bean.getTotalCount())?bean.getTotalCount():0;
        int platformPage = (totalCnt==0?0:((totalCnt/page.getPageSize())+1));
        if(totalCnt%page.getPageSize()>0) {
            platformPage = platformPage-1;
        }
        return platformPage;
    }

    public Integer getCurrPage(KeyWordSearchBean bean){
        Pagination page = bean.getPage();
        int platformPage = getPlatformPage(bean);
        int currPage = page.getPageNo()-platformPage;
        if(currPage <=0){
            return 1;
        }
        return currPage;
    }

    public Integer getCurrPage(String channelCode,JedisUtil jedisUtil,KeyWordSearchBean bean){
        String redisKey = bean.getKeyWordRedisKey();
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

    Gson gson = new Gson();
    public void saveRedisPage(KeyWordSearchCachePageBean keyWordSearchCachePageBean, JedisUtil jedisUtil,KeyWordSearchBean bean){
        String redisKey = bean.getKeyWordRedisKey();
        ProductRedisKeyEnum productRedisKeyEnum = ProductRedisKeyEnum.KEY_WORD_SEARCH_PAGE_KEY;
        int seconds = productRedisKeyEnum.getExpireTime();
        Object redisValue = jedisUtil.get(redisKey);
        if(redisValue == null){
            List<KeyWordSearchCachePageBean> pageBeanList = new ArrayList<>();
            pageBeanList.add(keyWordSearchCachePageBean);
            jedisUtil.set(bean.getKeyWordRedisKey(), seconds, gson.toJson(pageBeanList));
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
        jedisUtil.set(bean.getKeyWordRedisKey(), seconds, gson.toJson(voList));
    }
}
