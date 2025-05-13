package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.WorldBuyOrderManage;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.vo.world.WorldPayReqVO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.WorldBuyUtil;
import com.egeo.utils.log.XLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class WorldBuyOrderManageImpl implements WorldBuyOrderManage {

    XLogger logger = XLogger.getLogger(this.getClass().getName());

    @Resource
    private WorldBuyUtil worldBuyUtil;
    @Resource
    private ChannelServiceConfigClient channelServiceConfigClient;

    @Override
    public JSONObject createOrder(JSONObject jsonObject){
        EnterpriseChannelBaffleDTO dto = new EnterpriseChannelBaffleDTO();
        dto.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
        dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_CREATE.getChannelServiceName());
        dto.setEnterpriseId(1);
        dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
        EnterpriseChannelBaffleDTO enterpriseChannelBaffleDTO = channelServiceConfigClient.findEnterpriseChannelBaffle(dto);
        if(enterpriseChannelBaffleDTO !=null){
            return JSONObject.parseObject(enterpriseChannelBaffleDTO.getReturnData());
        }
        JSONObject submitOrderResult  = worldBuyUtil.createOrder(jsonObject);
        return submitOrderResult;
    }

    @Override
    public JSONObject orderPay(WorldPayReqVO vo) {
        vo.setEnterpriseNum("9131010790");
        vo.setEnterpriseName("大厨管家（上海)网络科技有限公司");
        vo.setPayCommanyCode("9131010790");
        vo.setPayCommanyName("大厨管家（上海)网络科技有限公司");
        JSONObject map =null;
        try {
           /* map = JsonUtils.beanToMap(vo);
            if(map.containsKey("class")){
                map.remove("class");
            }*/
            String jsonString = JSON.toJSONString(vo);
            map = JSONObject.parseObject(jsonString);
        }catch (Exception e){
            e.printStackTrace();
        }
        EnterpriseChannelBaffleDTO dto = new EnterpriseChannelBaffleDTO();
        dto.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
        dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_PAY.getChannelServiceName());
        dto.setEnterpriseId(1);
        dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
        EnterpriseChannelBaffleDTO enterpriseChannelBaffleDTO = channelServiceConfigClient.findEnterpriseChannelBaffle(dto);
        if(enterpriseChannelBaffleDTO !=null){
            return JSONObject.parseObject(enterpriseChannelBaffleDTO.getReturnData());
        }
        logger.info("全球购订单支付清关信息回传接口请求参数{}",JSON.toJSONString(map));
        JSONObject jsonObject = worldBuyUtil.orderPay(map);
        logger.info("全球购订单支付清关信息回传接口请求结果{}",JSON.toJSONString(jsonObject));
        return jsonObject;
    }
}
