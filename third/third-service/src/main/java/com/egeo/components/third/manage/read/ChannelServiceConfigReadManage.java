package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelServiceConfigReadManage{

    ChannelServiceConfigPO findChannelServiceConfigById(ChannelServiceConfigPO po);

    PageResult<ChannelServiceConfigPO> findChannelServiceConfigOfPage(ChannelServiceConfigPO po, Pagination page);

    List<ChannelServiceConfigPO> findChannelServiceConfigAll(ChannelServiceConfigPO po);
}
