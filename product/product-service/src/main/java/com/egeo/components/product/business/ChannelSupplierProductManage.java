package com.egeo.components.product.business;

import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelSupplierProductManage {

    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> platform(ChannelSupplierProductRequestVO vo, Pagination page);

    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> enterprise(ChannelSupplierProductRequestVO vo, Pagination page);

}
