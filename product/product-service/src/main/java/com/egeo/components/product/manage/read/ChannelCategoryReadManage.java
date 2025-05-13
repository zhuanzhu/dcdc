package com.egeo.components.product.manage.read;

import com.egeo.components.product.po.ChannelCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelCategoryReadManage {


    public ChannelCategoryPO findChannelCategoryById(ChannelCategoryPO po);

    public PageResult<ChannelCategoryPO> findChannelCategoryOfPage(ChannelCategoryPO po, Pagination page);

    public List<ChannelCategoryPO> findChannelCategoryAll(ChannelCategoryPO po);

}
