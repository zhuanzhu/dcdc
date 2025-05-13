package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.SplitOrderConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface SplitOrderConfigReadManage {

    public SplitOrderConfigPO findSplitOrderConfigById(SplitOrderConfigPO po);

    public PageResult<SplitOrderConfigPO> findSplitOrderConfigOfPage(SplitOrderConfigPO po, Pagination page);

    public List<SplitOrderConfigPO> findSplitOrderConfigAll(SplitOrderConfigPO po);
}
