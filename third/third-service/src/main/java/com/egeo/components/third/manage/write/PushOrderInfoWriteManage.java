package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.PushOrderInfoPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface PushOrderInfoWriteManage {

    public Long insertPushOrderInfoWithTx(PushOrderInfoPO po);

    public int updatePushOrderInfoWithTx(PushOrderInfoPO po);

    public int deletePushOrderInfoWithTx(PushOrderInfoPO po);
}
