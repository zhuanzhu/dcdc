package com.egeo.components.product.business.impl.Thread;

import com.egeo.components.product.business.ChannelSupplierProductManage;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.SpringContextTool;
import com.egeo.web.JsonResult;

import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/1/13 13:34
 * @Version V1.0
 **/
public class CakeExportSearchThread implements Callable<JsonResult<PageResult<ChannelSupplierProductResponseVO>>> {

    private ChannelSupplierProductManage channelSupplierProductManage;

    private ChannelSupplierProductRequestVO vo;

    private Pagination page;

    public CakeExportSearchThread(ChannelSupplierProductRequestVO vo, Pagination page) {
        this.channelSupplierProductManage = SpringContextTool.getBean(ChannelSupplierProductManage.class);
        this.vo = vo;
        this.page = page;
    }

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> call(){
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> pageResult=channelSupplierProductManage.enterprise(vo,page);
        return pageResult;
    }
}
