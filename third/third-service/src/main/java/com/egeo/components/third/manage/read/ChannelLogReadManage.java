package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 13:56
 * @Version V1.0
 **/
public interface ChannelLogReadManage {

    ChannelLogPO findChannelLogById(ChannelLogPO po);

    PageResult<ChannelLogPO> findChannelLogOfPage(ChannelLogPO po, Pagination page);

    List<ChannelLogPO> findChannelLogAll(ChannelLogPO po);
}
