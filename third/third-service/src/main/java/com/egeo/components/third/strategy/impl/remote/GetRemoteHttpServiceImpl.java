package com.egeo.components.third.strategy.impl.remote;

import com.egeo.components.third.common.ChannelServiceMethodEnum;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.strategy.RemoteHttpService;
import com.egeo.components.utils.HttpService;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("getRemoteHttpServiceImpl")
public class GetRemoteHttpServiceImpl implements RemoteHttpService {
    @Override
    public String getChannelServiceMethod() {
        return ChannelServiceMethodEnum.GET.getChannelServiceMethod();
    }

    @Override
    public String send(ChannelServiceConfigDTO dto, Object paramObject) {
        String paramString = null;
        if(Objects.nonNull(paramObject)){
            paramString = (String)paramObject;
        }
        return HttpService.sendGet(dto.getChannelServiceUrl(),paramString);
    }

    /**
     * @Description //TODO
     * @Param param map参数
     * @Param eqStr  =
     * @Param connectStr &
     * @return
     * @return java.lang.String
     **/
    private String getUrlParam(Map<String, String> param, String eqStr, String connectStr){
        if(EmptyUtil.isEmpty(param)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append(entry.getKey()).append(eqStr).append(entry.getValue()).append(connectStr);
        }
        // 删除最后一个字符
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
