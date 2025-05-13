package com.egeo.components.order.dao.read;

import com.egeo.components.order.po.QmOrderPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QmOrderReadDAO extends BaseReadDAO<QmOrderPO> {


    QmOrderPO findByOutTradeNo(@Param("outTradeNo")String outTradeNo);


    QmOrderPO findByOrderCode(@Param("orderCode")String orderCode);

    QmOrderPO findBySoId(@Param("soId")Long soId);

    List<QmOrderPO> findWaitSyncOrder(@Param("synPayMaxTimes")Integer synPayMaxTimes,@Param("pageSize")Integer pageSize);

}
