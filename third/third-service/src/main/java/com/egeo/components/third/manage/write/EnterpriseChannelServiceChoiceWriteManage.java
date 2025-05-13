package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceChoiceWriteManage {

    Long insertEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoicePO po);

    int updateEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoicePO po);

    int deleteEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoicePO po);
}
