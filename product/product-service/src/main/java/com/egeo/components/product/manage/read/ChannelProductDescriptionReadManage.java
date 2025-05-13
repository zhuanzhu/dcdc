package com.egeo.components.product.manage.read;

import com.egeo.components.product.po.ChannelProductDescriptionPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductDescriptionReadManage {

    public ChannelProductDescriptionPO findChannelProductDescriptionById(ChannelProductDescriptionPO po);

    public PageResult<ChannelProductDescriptionPO> findChannelProductDescriptionOfPage(ChannelProductDescriptionPO po, Pagination page);

    public List<ChannelProductDescriptionPO> findChannelProductDescriptionAll(ChannelProductDescriptionPO po);

}
