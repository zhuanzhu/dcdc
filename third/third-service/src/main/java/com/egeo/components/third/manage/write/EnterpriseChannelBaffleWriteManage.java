package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.EnterpriseChannelBafflePO;
import com.egeo.components.third.po.PushOrderInfoPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelBaffleWriteManage {

    public Long insertEnterpriseChannelBaffleWithTx(EnterpriseChannelBafflePO po);

    public int updateEnterpriseChannelBaffleWithTx(EnterpriseChannelBafflePO po);

    public int deleteEnterpriseChannelBaffleWithTx(EnterpriseChannelBafflePO po);
}
