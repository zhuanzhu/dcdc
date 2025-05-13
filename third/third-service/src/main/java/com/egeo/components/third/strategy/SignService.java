package com.egeo.components.third.strategy;

import com.egeo.components.third.bean.SignHelperBean;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface SignService {

    public String getEncryptType();

    public Object encryptionData(SignHelperBean bean);

    public Object decryptData(SignHelperBean bean);
}
