package com.egeo.components.third.strategy.impl.remote;

import cn.hutool.http.HttpUtil;
import com.egeo.components.third.common.ChannelServiceMethodEnum;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.strategy.RemoteHttpService;
import com.egeo.components.utils.YDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("hutoolGetByMapHttpServiceImpl")
public class HutoolGetByMapHttpServiceImpl implements RemoteHttpService {

    @Resource
    private YDUtil ydUtil;
    @Override
    public String getChannelServiceMethod() {
        return ChannelServiceMethodEnum.HUTOOL_GET_BY_MAP.getChannelServiceMethod();
    }

    @Override
    public String send(ChannelServiceConfigDTO dto, Object paramObject) {
        Map<String,Object> paramMap = convertToMap(paramObject);
        return HttpUtil.get(dto.getChannelServiceUrl(),paramMap,2000);
    }

    private Map<String,Object> convertToMap( Object paramObject){
        return ydUtil.convertToMap(paramObject);
    }

}
