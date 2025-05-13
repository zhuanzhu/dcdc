package com.egeo.components.product.manage.read;

import com.egeo.components.product.po.ChannelProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductReadManage {


    public ChannelProductPO findChannelProductById(ChannelProductPO po);

    public PageResult<ChannelProductPO> findChannelProductOfPage(ChannelProductPO po, Pagination page);

    public List<ChannelProductPO> findChannelProductAll(ChannelProductPO po);

}
