package com.egeo.components.order.business;

import com.egeo.components.order.dto.QingMeiOrderDTO;
import com.egeo.components.order.dto.QmOrderRefundDTO;
import com.egeo.components.order.dto.QmOrderStatusUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface QingMeiOrderManage {


    String unifiedOrderWithTx(QingMeiOrderDTO orderDTO, HttpServletRequest req);

    void orderStateWithTx(QmOrderStatusUpdateDTO statusUpdateDTO,HttpServletRequest req);

    String orderRefundWithTx(QmOrderRefundDTO refundDTO, HttpServletRequest req);

    void verifyUnifiedOrder(QingMeiOrderDTO orderDTO);

    void verifyOrderState(QmOrderStatusUpdateDTO statusUpdateDTO);

    void verifyOrderRefund(QmOrderRefundDTO refundDTO);

    void checkSign(Map<String,String> params);
}
