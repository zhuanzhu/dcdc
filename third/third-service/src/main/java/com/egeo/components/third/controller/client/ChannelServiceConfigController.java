package com.egeo.components.third.controller.client;

import com.alibaba.fastjson.JSON;
import com.egeo.components.third.business.ChannelServiceConfigManage;
import com.egeo.components.third.business.EnterpriseChannelServiceRouteManage;
import com.egeo.components.third.business.RemoteExecuteManage;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.*;
import com.egeo.components.third.facade.ChannelServiceConfigFacade;
import com.egeo.components.third.facade.EnterpriseBizConfigFacade;
import com.egeo.components.third.facade.EnterpriseChannelBaffleFacade;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 渠道服务配置
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/third/channelServiceConfig")
public class ChannelServiceConfigController implements ChannelServiceConfigClient {

    public XLogger logger = XLogger.getLogger(this.getClass().getName());


    @Resource(name = "channelServiceConfigManage")
    private ChannelServiceConfigManage channelServiceConfigManage;

    @Resource(name = "enterpriseChannelServiceRouteManage")
    private EnterpriseChannelServiceRouteManage enterpriseChannelServiceRouteManage;

    @Resource
    private EnterpriseBizConfigFacade enterpriseBizConfigFacade;

    @Resource
    private RemoteExecuteManage remoteExecuteManage;

    @Resource
    private EnterpriseChannelBaffleFacade enterpriseChannelBaffleFacade;

    /**
     * @Description 路由可用渠道接口
     **/
    @Override
    @RequestMapping(value = "/routeService", method = { RequestMethod.POST })
    @ResponseBody
    public EnterpriseServiceRouteResponseDTO routeService(@RequestBody EnterpriseServiceRouteRequestDTO dto){
        logger.info("请求参数{}", JSON.toJSONString(dto));
        return enterpriseChannelServiceRouteManage.routeService(dto);
    }

    /**
     * @Description 渠道接口请求类型参数转换
     **/
    @Override
    @RequestMapping(value = "/convertParam", method = { RequestMethod.POST })
    @ResponseBody
    public String convertParam(@RequestBody ConvertParamReqDTO dto) {
        logger.info("请求参数{}", JSON.toJSONString(dto));
        return channelServiceConfigManage.convertParam(dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType(),dto.getJsonString());
    }

    /**
     * @Description 渠道接口请求类型参数转换
     **/
    @Override
    @RequestMapping(value = "/findEnterpriseBizConfigAllDTO", method = { RequestMethod.POST })
    @ResponseBody
    public List<EnterpriseBizConfigDTO> findEnterpriseBizConfigAllDTO(@RequestBody EnterpriseBizConfigDTO dto){
        logger.info("请求参数{}", JSON.toJSONString(dto));
        return enterpriseBizConfigFacade.findEnterpriseBizConfigAll(dto);
    }

    @Override
    @RequestMapping(value = "/findEnterpriseBizConfigDTO", method = { RequestMethod.POST })
    @ResponseBody
    public  EnterpriseBizConfigDTO findEnterpriseBizConfigDTO(@RequestBody EnterpriseBizConfigDTO dto){
        logger.info("请求参数{}", JSON.toJSONString(dto));
        List<EnterpriseBizConfigDTO> list = enterpriseBizConfigFacade.findEnterpriseBizConfigAll(dto);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    /**
     * @Description 执行远程调用
     **/
    @Override
    @RequestMapping(value = "/remoteExecute", method = { RequestMethod.POST })
    @ResponseBody
    public JsonResult remoteExecute(@RequestBody RemoteExecuteDTO dto){
        logger.info("请求参数{}", JSON.toJSONString(dto));
        JsonResult rt =  remoteExecuteManage.remoteExecute(dto);
        logger.info("执行结果:{}",rt !=null?JSON.toJSONString(rt):"执行结果为null");
        return rt;
    }

    @RequestMapping(
            value = {"/findEnterpriseChannelBaffle"},
            method = {RequestMethod.POST}
    )
    @Override
    @ResponseBody
    public EnterpriseChannelBaffleDTO findEnterpriseChannelBaffle(@RequestBody EnterpriseChannelBaffleDTO dto){
        logger.info("请求参数{}", JSON.toJSONString(dto));
        return enterpriseChannelBaffleFacade.findEnterpriseChannelBaffleDTO(dto.getEnterpriseId(),dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType());
    }

}
