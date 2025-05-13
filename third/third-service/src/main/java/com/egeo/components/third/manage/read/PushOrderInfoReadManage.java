package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.PushOrderInfoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface PushOrderInfoReadManage {
    public PushOrderInfoPO findPushOrderInfoById(PushOrderInfoPO po);

    public PageResult<PushOrderInfoPO> findPushOrderInfoOfPage(PushOrderInfoPO po, Pagination page);

    public List<PushOrderInfoPO> findPushOrderInfoAll(PushOrderInfoPO po);
}
