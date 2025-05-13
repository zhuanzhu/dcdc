package com.egeo.components.third.strategy.impl.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.bean.SignHelperBean;
import com.egeo.components.third.common.EncryptTypeEnum;
import com.egeo.components.third.strategy.SignService;
import com.egeo.components.utils.DLFUtil;
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
@Service("yDSignServiceImpl")
public class YDSignServiceImpl implements SignService {
    public XLogger logger = XLogger.getLogger(this.getClass().getName());
    @Resource
    private YDUtil ydUtil;

    @Override
    public String getEncryptType() {
        return EncryptTypeEnum.YD.getEncryptType();
    }

    @Override
    public Object encryptionData(SignHelperBean bean) {
        Map<String,Object> finalMap = ydUtil.getRequestMap(bean.getData());
        return JSON.toJSONString(finalMap);

    }

    @Override
    public Object decryptData(SignHelperBean bean) {
        if(bean.getData() == null){
            return bean.getData();
        }

        Object o = convertMsgAndJsonObject(bean);
        return o;
    }

    private Object convertMsgAndJsonObject(SignHelperBean bean){
        try {
            String dataString =null;
            if(bean.getData() instanceof String){
                dataString = (String)bean.getData();
            }
            JSONObject jsonObject = JSONObject.parseObject(null !=dataString?dataString:bean.getData().toString());
            return jsonObject;
        }catch (Exception e){
            return bean.getData();
        }
    }
}
