package com.egeo.components.product.manage.read;

import com.egeo.components.product.po.ChannelProductBatchPO;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductBatchReadManage {

    public ChannelProductBatchPO findChannelProductBatchById(ChannelProductBatchPO po);

    public PageResult<ChannelProductBatchPO> findChannelProductBatchOfPage(ChannelProductBatchPO po, Pagination page);

    public List<ChannelProductBatchPO> findChannelProductBatchAll(ChannelProductBatchPO po);

    public PageResult<ChannelSupplierProductResponseVO> findChannelProductOfPage(ChannelSupplierProductRequestVO vo, Pagination page);
    }
