package com.egeo.components.product.business.impl;

import com.egeo.components.product.business.ChannelSupplierProductManage;
import com.egeo.components.product.strategy.factory.ChannelPlatformProductFactory;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道/供应商商品查询
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelSupplierProductManage")
public class ChannelSupplierProductManageImpl implements ChannelSupplierProductManage {

    @Autowired
    private ChannelPlatformProductFactory factory;

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> platform(ChannelSupplierProductRequestVO vo, Pagination page){

        return factory.getSearchProductStrategy(vo.getChannelCode()).platform(vo,page);
    }

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> enterprise(ChannelSupplierProductRequestVO vo, Pagination page){
        return factory.getSearchProductStrategy(vo.getChannelCode()).enterprise(vo,page);
    }
}
