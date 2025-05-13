package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.EnterpriseChannelServicePO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceWriteManage {

    Long insertEnterpriseChannelServiceWithTx(EnterpriseChannelServicePO po);

    int updateEnterpriseChannelServiceWithTx(EnterpriseChannelServicePO po);

    int deleteEnterpriseChannelServiceWithTx(EnterpriseChannelServicePO po);
}
