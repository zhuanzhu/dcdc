package com.egeo.components.order.manage.write.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.BusinessException;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.converter.CakeDTOConverter;
import com.egeo.components.order.converter.ChannelAddressConverter;
import com.egeo.components.order.dto.CakeAddResultDTO;
import com.egeo.components.order.dto.CakeAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.enums.OrderRedisKeyEnum;
import com.egeo.components.order.manage.read.ChannelAddressReadManage;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.manage.write.ChannelAddressWriteManage;
import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.components.utils.CakeAddressUtil;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.JsonUtils;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/29 11:12
 * @Version V1.0
 **/
@Service
public class CakeAddressWriteManageImpl implements CakeAddressWriteManage {
    private static final XLogger logger = XLogger.getLogger(CakeAddressWriteManageImpl.class);
    @Resource
    private CakeUtil cakeUtil;

    @Resource
    private JedisUtil jedisUtil;

    @Resource
    private ChannelAddressReadManage channelAddressReadManage;

    @Resource
    private ChannelAddressWriteManage channelAddressWriteManage;

    @Override
    public String getCityId(String cityName){
        OrderRedisKeyEnum productRedisKeyEnum = OrderRedisKeyEnum.CAKE_CITY_ID_KEY;
        String redisKey  = OrderRedisKeyEnum.getCakeCityIdKey(MD5Util.MD5(cityName));
        int seconds = productRedisKeyEnum.getExpireTime();
        Object redisValue = jedisUtil.get(redisKey);
        if(EmptyUtil.isNotEmpty(redisValue)){
            logger.info("缓存中有城市id，直接返回缓存中的城市id:{}",redisValue);
            return (String)redisValue;
        }
        String cityId = getCityIdRemote(cityName);
        if(EmptyUtil.isNotEmpty(cityId)){
            jedisUtil.set(redisKey, seconds, cityId);
        }
        return cityId;
    }

    private String getCityIdRemote(String cityName) {
        JSONObject jsonObject = cakeUtil.getCityId(cityName);
        JsonResult checkUserRT = cakeUtil.checkResult(jsonObject);
        if(Objects.nonNull(checkUserRT)){
            throw new com.egeo.exception.BusinessException("获取渠道城市错误:"+ checkUserRT.getError());
        }
        JSONObject dataJsonObject = jsonObject.getJSONObject(cakeUtil.DATA_KEY);
        if(Objects.isNull(dataJsonObject)){
            throw new com.egeo.exception.BusinessException("获取渠道城市失败");
        }
        return dataJsonObject.getString("id");
    }

    @Override
    public CakeAddResultDTO addOrEditCakeAddress(ReceiverAddressDTO dto,String channelUserId){
        //logger.info("收货地址打印:{}",JSON.toJSONString(dto));
        ChannelAddressPO channelAddressPO = channelAddressReadManage.findByReceiverAddressIdChannel(dto.getId(),ProductChannelCodeEnum.CAKE.getCode());
        if(Objects.nonNull(channelAddressPO)  && Objects.equals(channelAddressPO.getAddr(),dto.getGoodReceiverAddress())){
           //logger.info("收货地址id:{}本地库中有，转化返回",dto.getId());
            return ChannelAddressConverter.toCakeAddResultDTO(channelAddressPO);
        }
        CakeAddressDTO cakeAddressDTO = CakeDTOConverter.toCakeAddressDTO(dto);
        if(StringUtils.isNotEmpty(channelUserId)){
            cakeAddressDTO.setUser_id(channelUserId);
        }
        Map<String,String> paramMap = beanToMap(cakeAddressDTO);
        logger.info("创建地址请求参数{}",JSON.toJSONString(paramMap));
        JSONObject jsonObject = cakeUtil.oprateAddr(paramMap);
        logger.info("创建地址请求结果{}",JSON.toJSONString(jsonObject));
        JsonResult result = cakeUtil.checkResult(jsonObject);
        if(Objects.nonNull(result)){
            throw new BusinessException("新增渠道地址失败:"+result.getError());
        }

        String data = jsonObject.getString(cakeUtil.DATA_KEY);
        CakeAddResultDTO resultDTO = JSONObject.parseObject(data,CakeAddResultDTO.class);
        saveOrUpdateChannelAddress(resultDTO,dto);
        return resultDTO;
    }

    private void saveOrUpdateChannelAddress(CakeAddResultDTO resultDTO,ReceiverAddressDTO dto){
        try {
            ChannelAddressPO tar = ChannelAddressConverter.cakeAddResultToPO(resultDTO, ProductChannelCodeEnum.CAKE.getCode(),dto.getUserId(),dto.getId());
            if(tar ==null){
                return;
            }
            ChannelAddressPO channelAddressPO = channelAddressReadManage.findByReceiverAddressIdChannel(dto.getId(),ProductChannelCodeEnum.CAKE.getCode());
            if(channelAddressPO ==null){
                channelAddressWriteManage.insertChannelAddressWithTx(tar);
                return;
            }
            tar.setId(channelAddressPO.getId());
            channelAddressWriteManage.updateChannelAddressWithTx(tar);
        }catch (Exception e){
            logger.error("地址id:{}保存渠道地址失败:{}",dto.getId(),e);
        }
    }


    @Override
    public void deleteCakeAddress(String userId,String id){
        JSONObject jsonObject = cakeUtil.delAddr(id,userId);
        JsonResult result = cakeUtil.checkResult(jsonObject);
        if(!Objects.equals(result.getCode(),Integer.valueOf(cakeUtil.CODE_SUCCESS))){
            throw new BusinessException("删除失败"+result.getError());
        }
    }

    private Map<String,String> beanToMap(Object bean){
        try {
            return JsonUtils.beanToMap(bean);
        }catch (Exception e){

        }
        return null;
    }
}
