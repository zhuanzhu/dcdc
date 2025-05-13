package com.egeo.components.third.strategy.impl.sign;

import com.egeo.components.third.bean.SignHelperBean;
import com.egeo.components.third.common.EncryptTypeEnum;
import com.egeo.components.third.strategy.SignService;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("defaultSignServiceImpl")
public class DefaultSignServiceImpl implements SignService {
    @Override
    public String getEncryptType() {
        return EncryptTypeEnum.DEFAULT.getEncryptType();
    }

    @Override
    public Object encryptionData(SignHelperBean bean) {
        return bean.getData();
    }

    @Override
    public Object decryptData(SignHelperBean bean) {
        return bean.getData();
    }

}
