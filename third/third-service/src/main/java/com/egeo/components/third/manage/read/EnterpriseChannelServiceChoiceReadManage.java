package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceChoiceReadManage {

    EnterpriseChannelServiceChoicePO findEnterpriseChannelServiceChoiceById(EnterpriseChannelServiceChoicePO po);

    PageResult<EnterpriseChannelServiceChoicePO> findEnterpriseChannelServiceChoiceOfPage(EnterpriseChannelServiceChoicePO po, Pagination page);

    List<EnterpriseChannelServiceChoicePO> findEnterpriseChannelServiceChoiceAll(EnterpriseChannelServiceChoicePO po);
}
