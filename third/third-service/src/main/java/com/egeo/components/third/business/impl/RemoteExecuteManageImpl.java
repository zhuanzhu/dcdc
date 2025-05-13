package com.egeo.components.third.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.bean.SignHelperBean;
import com.egeo.components.third.business.ChannelServiceConfigManage;
import com.egeo.components.third.business.RemoteExecuteManage;
import com.egeo.components.third.common.ProductChannelCodeEnum;
import com.egeo.components.third.common.StateEnum;
import com.egeo.components.third.dto.ChannelLogDTO;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.third.facade.ChannelLogFacade;
import com.egeo.components.third.facade.ChannelServiceConfigFacade;
import com.egeo.components.third.facade.EnterpriseChannelBaffleFacade;
import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.components.third.strategy.factory.RemoteHttpFactory;
import com.egeo.components.third.strategy.factory.SignFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class RemoteExecuteManageImpl implements RemoteExecuteManage {
    public XLogger logger = XLogger.getLogger(this.getClass().getName());


    @Resource
    private ChannelServiceConfigFacade channelServiceConfigFacade;

    @Resource
    private ChannelServiceConfigManage channelServiceConfigManage;

    @Resource
    private SignFactory signFactory;

    @Resource
    private EnterpriseChannelBaffleFacade enterpriseChannelBaffleFacade;

    @Resource
    private RemoteHttpFactory remoteHttpFactory;

    @Resource
    private ChannelLogFacade channelLogFacade;

    @Override
    public JsonResult remoteExecute(RemoteExecuteDTO dto){
        //获取到当前需要请求的接口
        ChannelServiceConfigDTO channelServiceConfigDTO = channelServiceConfigFacade.getChannelServiceConfigDTO(dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType());
        if(Objects.isNull(channelServiceConfigDTO)){
            return JsonResult.fail("未找到相应的接口");
        }
        ChannelLogDTO channelLogDTO = null;
        boolean hasSaveLog = false;
        if(channelServiceConfigDTO.getIfLog() !=null && channelServiceConfigDTO.getIfLog() == 1){
            channelLogDTO = channelLogFacade.saveChannelLogWithTx(dto);
            hasSaveLog = true;
        }
        //进行参数转换
        String paramString = channelServiceConfigManage.convertParam(dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType(),dto.getJsonString());
        //加密
        Object paramObject = getEncryptionData(channelServiceConfigDTO,paramString);
        //执行远程调用
        Object result = doRemoteExecute(channelServiceConfigDTO, dto.getEnterpriseId(), paramObject);
        if(Objects.isNull(result)){
            editChannelLog(hasSaveLog,channelLogDTO,null);
            return JsonResult.success();
        }
        //结果转换
        String resultString = channelServiceConfigManage.convertParam(dto.getChannelCode(),dto.getChannelServiceName(), ChannelServiceTypeEnum.RESP.getChannelServiceType(), JSON.toJSONString(result));
        Object responseRT =  parseResult(resultString);
        editChannelLog(hasSaveLog,channelLogDTO,responseRT);
        JsonResult rt = JsonResult.success(responseRT);
        return rt;
    }

    private void editChannelLog(boolean hasSaveLog,ChannelLogDTO channelLogDTO,Object result){
        try {
            if(!hasSaveLog || channelLogDTO == null){
                return;
            }
            ChannelLogDTO editChannelLogDTO = new ChannelLogDTO();
            editChannelLogDTO.setId(channelLogDTO.getId());
            if(result !=null){
                if(result instanceof String){
                    editChannelLogDTO.setResponseJson((String)result);
                }else{
                    editChannelLogDTO.setResponseJson(JSON.toJSONString(result));
                }
                findResponse(channelLogDTO,result,editChannelLogDTO);
            }else{
                editChannelLogDTO.setResponseJson("请求结果为空了");
                editChannelLogDTO.setResponseCode("-1");
            }
            channelLogFacade.editChannelLogWithTx(editChannelLogDTO);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void findResponse(ChannelLogDTO channelLogDTO,Object result,ChannelLogDTO editChannelLogDTO){
        try {
            //未执行解析或解析失败
            editChannelLogDTO.setResponseCode("-2");
            JSONObject jsonObject = null;
            if(result instanceof  String){
                jsonObject = JSONObject.parseObject((String)result);
            }else if(result instanceof JSONObject){
                jsonObject = (JSONObject)result;
            }
            if(jsonObject !=null && jsonObject.containsKey("code")){
                editChannelLogDTO.setResponseCode(jsonObject.getString("code"));
            }else if(jsonObject !=null && jsonObject.containsKey("status")){
                editChannelLogDTO.setResponseCode(jsonObject.getString("status"));
            }
            //找msg
            if(jsonObject !=null && jsonObject.containsKey("msg")){
                editChannelLogDTO.setResponseMsg(jsonObject.getString("msg"));
            }else if(jsonObject !=null && jsonObject.containsKey("message")){
                editChannelLogDTO.setResponseMsg(jsonObject.getString("message"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    private Object parseResult(String resultString){
        if(EmptyUtil.isEmpty(resultString)){
            return resultString;
        }
        try {
            return JSONObject.parseObject(resultString);
        }catch (Exception e){
            e.printStackTrace();
            return resultString;
        }
    }

    private Object getEncryptionData(ChannelServiceConfigDTO channelServiceConfigDTO,String paramString){
        if(channelServiceConfigDTO.getIfEncrypted() == 0){
            return paramString;
        }
        //进行加密
        Object enData = signFactory.getSignService(channelServiceConfigDTO.getEncryptType()).encryptionData(new SignHelperBean(paramString));
        return enData;
    }

    private Object doRemoteExecute(ChannelServiceConfigDTO dto,Integer enterpriseId,Object paramObject){
        //获取挡板，若存在挡板，按挡板返回
        EnterpriseChannelBaffleDTO baffleDTO = enterpriseChannelBaffleFacade.findEnterpriseChannelBaffleDTO(enterpriseId,dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType());
        //挡板不需要解密
        if(Objects.nonNull(baffleDTO) && baffleDTO.getState().equals(StateEnum.NORMAL.getCode()) && baffleDTO.getIfDecrypt() == 0){
            logger.info("走的挡板不需要解密企业id:{},渠道:{},接口名:{},类型:{}",enterpriseId,dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType());
            Object responseRT =  parseResult(baffleDTO.getReturnData());
            return responseRT;
        }
        //挡板是否需要解密
        if(Objects.nonNull(baffleDTO) && baffleDTO.getState().equals(StateEnum.NORMAL.getCode()) && baffleDTO.getIfDecrypt() == 1){
            logger.info("走的挡板需要解密企业id:{},渠道:{},接口名:{},类型:{}",enterpriseId,dto.getChannelCode(),dto.getChannelServiceName(),dto.getChannelServiceType());
            return getDecryptData(dto,baffleDTO.getReturnData());
        }
        //执行http请求
        String result = remoteHttpFactory.getRemoteHttpService(dto.getChannelServiceMethod()).send(dto,paramObject);
        //无需解密
       if(dto.getIfEncrypted() == 0){
           return result;
       }
        //解密
        Object decryptResult =  getDecryptData(dto,result);
        return decryptResult;
    }

    private Object getDecryptData(ChannelServiceConfigDTO channelServiceConfigDTO,Object result){
        if(channelServiceConfigDTO.getIfEncrypted() == 0){
            return result;
        }
        //进行加密
        Object enData = signFactory.getSignService(channelServiceConfigDTO.getEncryptType()).decryptData(new SignHelperBean(result));
        return enData;
    }


}
