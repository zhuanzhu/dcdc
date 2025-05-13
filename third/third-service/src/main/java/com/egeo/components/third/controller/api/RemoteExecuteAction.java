package com.egeo.components.third.controller.api;

import com.alibaba.fastjson.JSON;
import com.egeo.components.third.business.RemoteExecuteManage;
import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.facade.EnterpriseChannelBaffleFacade;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/third/execute")
public class RemoteExecuteAction extends BaseSpringController {

    @Resource
    private RemoteExecuteManage remoteExecuteManage;

    @Resource
    private EnterpriseChannelBaffleFacade enterpriseChannelBaffleFacade;

    /**
     * @Description 执行远程调用
     **/
    @RequestMapping(value = "/remoteExecute", method = { RequestMethod.POST })
    @ResponseBody
    public JsonResult remoteExecute(@RequestBody RemoteExecuteDTO dto){
        JsonResult rt = remoteExecuteManage.remoteExecute(dto);
        logger.info("请求结果:{}", JSON.toJSONString(rt));
        return rt;
    }

    @RequestMapping( value = {"/findEnterpriseChannelBaffle"}, method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffle(@RequestBody EnterpriseChannelBaffleDTO dto){
        EnterpriseChannelBaffleDTO rt = enterpriseChannelBaffleFacade.findEnterpriseChannelBaffleDTO(dto.getEnterpriseId(),dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType());
        logger.info("查询挡板结果:{}", JSON.toJSONString(rt));
        return JsonResult.success(rt);
    }
}
