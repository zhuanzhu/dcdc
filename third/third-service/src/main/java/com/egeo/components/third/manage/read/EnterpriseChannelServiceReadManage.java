package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.EnterpriseChannelServicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceReadManage {

    EnterpriseChannelServicePO findEnterpriseChannelServiceById(EnterpriseChannelServicePO po);

    PageResult<EnterpriseChannelServicePO> findEnterpriseChannelServiceOfPage(EnterpriseChannelServicePO po, Pagination page);

    List<EnterpriseChannelServicePO> findEnterpriseChannelServiceAll(EnterpriseChannelServicePO po);
}
