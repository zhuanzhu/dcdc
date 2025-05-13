package com.egeo.components.third.strategy.impl.sign;

import com.egeo.components.third.bean.SignHelperBean;
import com.egeo.components.third.common.EncryptTypeEnum;
import com.egeo.components.third.strategy.SignService;
import org.springframework.stereotype.Service;

/**
 * @Description 蛋糕叔叔加解密实现
 * @Author lsl
 * @Version V1.0
 **/
@Service("cakeSignServiceImpl")
public class CakeSignServiceImpl implements SignService {
    @Override
    public String getEncryptType() {
        return EncryptTypeEnum.CAKE.getEncryptType();
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
