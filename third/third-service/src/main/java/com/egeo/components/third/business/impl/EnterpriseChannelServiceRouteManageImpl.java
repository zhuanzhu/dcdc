package com.egeo.components.third.business.impl;

import com.egeo.components.third.business.EnterpriseChannelServiceRouteManage;
import com.egeo.components.third.common.StateEnum;
import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.dto.EnterpriseServiceRouteRequestDTO;
import com.egeo.components.third.dto.EnterpriseServiceRouteResponseDTO;
import com.egeo.components.third.facade.EnterpriseChannelServiceChoiceFacade;
import com.egeo.components.third.facade.EnterpriseChannelServiceFacade;
import com.egeo.components.third.strategy.EnterpriseServiceRouteStrategy;
import com.egeo.components.third.strategy.factory.EnterpriseServiceRouteFactory;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.StringUtil;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.json.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("enterpriseChannelServiceRouteManage")
public class EnterpriseChannelServiceRouteManageImpl implements EnterpriseChannelServiceRouteManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EnterpriseChannelServiceChoiceFacade serviceChoiceFacade;

    @Resource
    private EnterpriseChannelServiceFacade channelServiceFacade;

    @Resource
    private EnterpriseServiceRouteFactory routeFactory;

    @Override
    public EnterpriseServiceRouteResponseDTO routeService(EnterpriseServiceRouteRequestDTO dto) {
        logger.info("请求参数"+ JsonUtils.objectToJson(dto));
        //检查请求参数
        checkEnterpriseServiceRouteRequestDTO(dto);
        String enterpriseId = String.valueOf(dto.getEnterpriseId());
        //获取需要路由规则
        EnterpriseChannelServiceChoiceDTO ruteInfo = serviceChoiceFacade.getSingleEnterpriseChannelServiceRute(enterpriseId,dto.getChannelServiceName(), dto.getChannelServiceType());
        //获取可使用的渠道和接口
        List<EnterpriseChannelServiceDTO> serviceList = channelServiceFacade.findEnterpriseChannelServiceAll(enterpriseId,dto.getChannelServiceName(), dto.getChannelServiceType());
       //获取路由策略服务
        EnterpriseServiceRouteStrategy routeStrategy = routeFactory.getRouteService(Objects.isNull(ruteInfo)?null:ruteInfo.getRuteName());
        //执行策略
        serviceList = routeStrategy.filterEnterpriseChannelService(serviceList,ruteInfo);
        //检查是否添加兜底
        checkOrAddIfDefault(dto,enterpriseId,serviceList);
        //将执行策略后的结果返回响应结果对象中
        return new EnterpriseServiceRouteResponseDTO(dto,serviceList);
    }

    private void checkEnterpriseServiceRouteRequestDTO(EnterpriseServiceRouteRequestDTO dto){
        if(Objects.isNull(dto)
                || StringUtil.isEmpty(dto.getChannelServiceName())
                || Objects.isNull(dto.getEnterpriseId())
                || StringUtil.isEmpty(dto.getChannelServiceType())){
            logger.error("路由渠道时缺少必填参数:"+ JsonUtils.objectToJson(dto));
            throw new BusinessException("缺少必填参数");
        }
    }

    private void checkOrAddIfDefault(EnterpriseServiceRouteRequestDTO dto,String enterpriseId,List<EnterpriseChannelServiceDTO> serviceList){
        if(!CollectionUtils.isEmpty(serviceList)){
            return;
        }
        if(serviceList == null){
            serviceList = new ArrayList<>();
        }
        List<EnterpriseChannelServiceDTO> defaultServiceList = channelServiceFacade.findDefaultEnterpriseChannelServiceAll(enterpriseId,dto.getChannelServiceName(), dto.getChannelServiceType());
        if(!CollectionUtils.isEmpty(defaultServiceList)){
            serviceList.addAll(defaultServiceList);
       }
    }
}
