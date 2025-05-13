package com.egeo.components.product.manage.read;

import com.egeo.components.product.po.ChannelProductTextPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductTextReadManage {


    public ChannelProductTextPO findChannelProductTextById(ChannelProductTextPO po);

    public PageResult<ChannelProductTextPO> findChannelProductTextOfPage(ChannelProductTextPO po, Pagination page);

    public List<ChannelProductTextPO> findChannelProductTextAll(ChannelProductTextPO po);

}
