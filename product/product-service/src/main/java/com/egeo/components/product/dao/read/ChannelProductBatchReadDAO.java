package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.ChannelProductBatchPO;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductBatchReadDAO extends BaseReadDAO<ChannelProductBatchPO> {

    int countChannelProductBatchOfPage(@Param("po")ChannelSupplierProductRequestVO po);

    List<ChannelSupplierProductResponseVO> findChannelProductBatchOfPage(@Param("po")ChannelSupplierProductRequestVO po,@Param("page") Pagination page);

}
