package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.EnterpriseChannelBafflePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelBaffleReadManage {

    public EnterpriseChannelBafflePO findEnterpriseChannelBaffleById(EnterpriseChannelBafflePO po);

    public PageResult<EnterpriseChannelBafflePO> findEnterpriseChannelBaffleOfPage(EnterpriseChannelBafflePO po, Pagination page);

    public List<EnterpriseChannelBafflePO> findEnterpriseChannelBaffleAll(EnterpriseChannelBafflePO po);
}
