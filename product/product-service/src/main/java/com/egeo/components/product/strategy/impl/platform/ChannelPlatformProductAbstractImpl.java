package com.egeo.components.product.strategy.impl.platform;

import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.strategy.ChannelPlatformProductStrategy;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.ArrayList;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public abstract class ChannelPlatformProductAbstractImpl implements ChannelPlatformProductStrategy {

    protected PageResult<ChannelSupplierProductResponseVO> setDefaultPageResult(ChannelSupplierProductRequestVO vo,Pagination page) {
        PageResult<ChannelSupplierProductResponseVO> pageResult = new PageResult<>();
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
}
