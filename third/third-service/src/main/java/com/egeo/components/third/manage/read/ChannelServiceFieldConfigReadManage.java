package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.ChannelServiceFieldConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelServiceFieldConfigReadManage {

    ChannelServiceFieldConfigPO findChannelServiceFieldConfigById(ChannelServiceFieldConfigPO po);

    PageResult<ChannelServiceFieldConfigPO> findChannelServiceFieldConfigOfPage(ChannelServiceFieldConfigPO po, Pagination page);

    List<ChannelServiceFieldConfigPO> findChannelServiceFieldConfigAll(ChannelServiceFieldConfigPO po);
}
