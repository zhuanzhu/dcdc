package com.egeo.components.order.scheduler;

import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.dto.QmOrderDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.service.SoService;
import com.egeo.components.order.service.read.QmOrderReadService;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.utils.QingMeiUtil;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class QingMeiOrderScheduleFacade {
    Logger logger = LoggerFactory.getLogger(QingMeiOrderScheduleFacade.class);

    @Resource
    private QmOrderReadService qmOrderReadService;
    @Resource
    private SoClient soClient;
    @Resource
    private SoService soService;
    @Resource
    private UserClient userReadService;
    @Resource
    private QingMeiUtil qingMeiUtil;
    @Resource
    private SoRefundReadService soRefundReadService;
    @Resource
    private SoItemReadService soItemReadService;

    /**
     * 同步清美支付结果
     */
    public void syncSuccessPayOrders(){
        List<QmOrderDTO> waitSyncOrders= qmOrderReadService.findWaitSyncOrder(qingMeiUtil.syncPayResultMaxCount,50);
        if (EmptyUtil.isNotEmpty(waitSyncOrders)){
            for (QmOrderDTO qmOrderDTO:waitSyncOrders){
                SoDTO soDTO= soClient.findSoById(qmOrderDTO.getSoId());
                if (Objects.isNull(soDTO)){
                    logger.error("soId:{}对应的so订单不存在",qmOrderDTO.getSoId());
                    continue;
                }
                if (Objects.equals(soDTO.getOrderPayStatus(), OrderConstant.ORDER_STATUS_PAYED.getStatus())){
                    boolean expire=EmptyUtil.isEmpty(qmOrderDTO.getExpireTime())?false:qmOrderDTO.getExpireTime().before(new Date());
                    UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
                    if(expire){
                        if (soDTO.getOrderStatus()!=10){
                            logger.info("清美订单同步超时,系统取消,outTradeNo:{},orderNo:{}",qmOrderDTO.getOutTradeNo(), qmOrderDTO.getOrderCode());
                            List<String> soRefundNOList = soRefundReadService.genSoRefundNO();
                            // 支付成功,但第三方订单处理失败,取消订单自动退款
                            List<SoItemDTO> items = soItemReadService.querySoItemListBySoId(soDTO.getId());
                            SoDTO soById = soClient.findSoById(soDTO.getId());
                            soService.cancelAndRefundOrderWithTx(userDTO.getName(),soById, items, userDTO.getId(), soRefundNOList.get(0),
                                    soRefundNOList.get(1), new HttpServletRequestDTO());
                        }
                    }else {
                        boolean success= soClient.dealThirdpartyOrderWithTx(userDTO.getName(),soDTO.getUserId(),soDTO.getId(),soDTO.getOrderCode());
                        logger.info("同步清美支付成功结果outTradeNo:{},orderNo:{},结果:{}",qmOrderDTO.getOutTradeNo(),
                                qmOrderDTO.getOrderCode(),success);
                    }
                }
            }
        }
    }
}
