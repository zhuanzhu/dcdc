package com.egeo.components.third.strategy.impl.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.egeo.components.third.common.ChannelServiceMethodEnum;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.strategy.RemoteHttpService;
import com.egeo.components.utils.YDUtil;
import com.egeo.utils.log.XLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("hutoolPostByMapHttpServiceImpl")
public class HutoolPostByMapHttpServiceImpl implements RemoteHttpService {
    public XLogger logger = XLogger.getLogger(this.getClass().getName());

    @Resource
    private YDUtil ydUtil;
    @Override
    public String getChannelServiceMethod() {
        return ChannelServiceMethodEnum.HUTOOL_POST_BY_MAP.getChannelServiceMethod();
    }

    @Override
    public String send(ChannelServiceConfigDTO dto, Object paramObject) {
        Map<String,Object> paramMap = convertToMap(paramObject);
        logger.info("请求配置信息:{},请求参数:【{}】", JSON.toJSONString(dto),JSON.toJSONString(paramMap));
        return HttpUtil.post(dto.getChannelServiceUrl(),paramMap,2000);
    }

    private Map<String,Object> convertToMap( Object paramObject){
        return ydUtil.convertToMap(paramObject);
    }

}
