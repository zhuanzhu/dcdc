package com.egeo.components.third.client;

import com.egeo.components.third.dto.*;
import com.egeo.web.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@FeignClient(
        name = "service-third-fgj",
        contextId = "ChannelServiceConfigClient"
)
public interface ChannelServiceConfigClient {

    @RequestMapping(
            value = {"/client/third/channelServiceConfig/routeService"},
            method = {RequestMethod.POST}
    )
    public EnterpriseServiceRouteResponseDTO routeService(EnterpriseServiceRouteRequestDTO dto);

    @RequestMapping(
            value = {"/client/third/channelServiceConfig/convertParam"},
            method = {RequestMethod.POST}
    )
    public String convertParam(ConvertParamReqDTO dto);

    @RequestMapping(
            value = {"/client/third/channelServiceConfig/findEnterpriseBizConfigAllDTO"},
            method = {RequestMethod.POST}
    )
    public List<EnterpriseBizConfigDTO> findEnterpriseBizConfigAllDTO(EnterpriseBizConfigDTO dto);

    @RequestMapping(
            value = {"/client/third/channelServiceConfig/findEnterpriseBizConfigDTO"},
            method = {RequestMethod.POST}
    )
    public EnterpriseBizConfigDTO findEnterpriseBizConfigDTO(EnterpriseBizConfigDTO dto);

    @RequestMapping(
            value = {"/client/third/channelServiceConfig/remoteExecute"},
            method = {RequestMethod.POST}
    )
    public JsonResult remoteExecute(@RequestBody RemoteExecuteDTO dto);

    @RequestMapping(
            value = {"/client/third/channelServiceConfig/findEnterpriseChannelBaffle"},
            method = {RequestMethod.POST}
    )
    public EnterpriseChannelBaffleDTO findEnterpriseChannelBaffle(EnterpriseChannelBaffleDTO dto);

}
