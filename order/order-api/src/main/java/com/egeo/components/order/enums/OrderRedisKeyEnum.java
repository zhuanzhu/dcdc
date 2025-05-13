package com.egeo.components.order.enums;

import com.egeo.utils.EmptyUtil;

/**
 * @Description 产品服务redisKey枚举
 * @Author lsl
 * @Date 2024/11/29 13:54
 * @Version V1.0
 **/
public enum OrderRedisKeyEnum {
    KEY_WORD_SEARCH_PAGE_KEY("KW_",60*30,"关键字搜索分页redisKey,时间单位为秒"),
    KEY_WORD_CAT_SEARCH_PAGE_KEY("KWC_SEARCH_",60*30,"关键字和类目id搜索分页redisKey,时间单位为秒"),
    CAKE_USER_ID_KEY("CAKE_USER_",60*60*24*30,"蛋糕叔叔userId存放redisKey,时间单位为秒"),
    CAKE_CITY_ID_KEY("USER:CAKE:CITY_ID:",60*60*24*7,"蛋糕叔叔城市id存放redisKey,时间单位为秒"),
    CAKE_PRODUCT_ID_KEY("CAKE:PRODUCT:",60*10,"蛋糕叔叔商品存放redisKey,时间单位为秒"),


    ;
    /**
     * redisKey，若有后缀可以追加后缀
     **/
    private String redisKey;

    /**
     * 失效时间:
     * 60*30=60秒*30分钟=30分钟
     * 60*60=60秒*60分钟=1个小时
     * 60*60*24=60秒*60分钟*24小时=1天
     * 60*60*24*7=60秒*60分钟*24小时*7天=1周
     * 60*60*24*30=60秒*60分钟*24小时*30天=1个月
     **/
    private int expireTime;

    /**
     * redisKey的描述说明
     **/
    private String comment;

    OrderRedisKeyEnum(String redisKey, int expireTime, String comment) {
        this.redisKey = redisKey;
        this.expireTime = expireTime;
        this.comment = comment;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static String getKeyWordCatSearchPageKey(String suffix, String keyWord){
        if(EmptyUtil.isNotEmpty(keyWord)){
            suffix = suffix+keyWord;
        }
        return getRedisKeyString(suffix, OrderRedisKeyEnum.KEY_WORD_CAT_SEARCH_PAGE_KEY);
    }

    public static String getKeyWordSearchPageKey(String suffix){

        return getRedisKeyString(suffix, OrderRedisKeyEnum.KEY_WORD_SEARCH_PAGE_KEY);
    }

    public static String getCakeCityIdKey(String suffix){
        return getRedisKeyString(suffix, OrderRedisKeyEnum.CAKE_CITY_ID_KEY);
    }

    public static String getCakeUserIdKey(String suffix){
        return getRedisKeyString(suffix, OrderRedisKeyEnum.CAKE_USER_ID_KEY);
    }

    public static String getRedisKeyString(String suffix, OrderRedisKeyEnum redisEnum){
        if(EmptyUtil.isEmpty(suffix)){
            return redisEnum.getRedisKey();
        }
        if(EmptyUtil.isNotEmpty(suffix) && EmptyUtil.isEmpty(redisEnum.getRedisKey())){
            return suffix;
        }
        return redisEnum.getRedisKey()+suffix;
    }
}
