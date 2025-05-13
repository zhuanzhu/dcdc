package com.egeo.components.third.strategy.impl.remote;

import com.egeo.components.third.common.ChannelServiceMethodEnum;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.strategy.RemoteHttpService;
import com.egeo.components.utils.HttpService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/7/3 10:46
 * @Version V1.0
 **/
@Service("postFormRemoteHttpServiceImpl")
public class PostFormRemoteHttpServiceImpl implements RemoteHttpService {
    @Override
    public String getChannelServiceMethod() {
        return ChannelServiceMethodEnum.POST_FORM.getChannelServiceMethod();
    }

    @Override
    public String send(ChannelServiceConfigDTO dto, Object paramObject) {
        String paramString = null;
        if(Objects.nonNull(paramObject)){
            paramString = (String)paramObject;
        }
        return HttpService.sendPost(dto.getChannelServiceUrl(),paramString);
    }
}
