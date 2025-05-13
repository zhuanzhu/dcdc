package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.CakeManage;
import com.egeo.components.order.enums.OrderRedisKeyEnum;
import com.egeo.components.utils.CakeUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 22:45
 * @Version V1.0
 **/
@Service
public class CakeManageImpl implements CakeManage {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private CakeUtil cakeUtil;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public String getCakeUserId() {
        OrderRedisKeyEnum orderRedisKeyEnum = OrderRedisKeyEnum.CAKE_USER_ID_KEY;
        String redisKey = OrderRedisKeyEnum.getCakeUserIdKey(cakeUtil.getUid());
        Object userId = jedisUtil.get(redisKey);
        if(EmptyUtil.isNotEmpty(userId)){
            logger.info("缓存中有蛋糕叔叔的用户id，直接返回缓存中的蛋糕叔叔的用户id{}",userId);
            return (String)userId;
        }
        userId = getCakeUserIdByRemote();
        if(EmptyUtil.isNotEmpty(userId)){
            jedisUtil.set(redisKey,orderRedisKeyEnum.getExpireTime(),userId);
            return (String)userId;
        }
        return null;
    }

    private String getCakeUserIdByRemote(){
        JSONObject userObject = cakeUtil.userLogin(null);
        JsonResult checkUserRT = cakeUtil.checkResult(userObject);
        if(Objects.nonNull(checkUserRT)){
            logger.error("请求登录三方用户发生失败{}", JSON.toJSONString(checkUserRT));
            return null;
        }
        JSONObject userData = userObject.getJSONObject(cakeUtil.DATA_KEY);
        String userId = userData.getString("id");
        return userId;
    }
}
