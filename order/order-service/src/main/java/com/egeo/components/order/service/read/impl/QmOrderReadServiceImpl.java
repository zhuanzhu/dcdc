package com.egeo.components.order.service.read.impl;

import com.egeo.components.order.converter.QmOrderConverter;
import com.egeo.components.order.dto.QmOrderDTO;
import com.egeo.components.order.manage.read.QmOrderReadManage;
import com.egeo.components.order.po.QmOrderPO;
import com.egeo.components.order.service.read.QmOrderReadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QmOrderReadServiceImpl implements QmOrderReadService {
    @Resource
    private QmOrderReadManage qmOrderReadManage;


    @Override
    public QmOrderDTO findByOutTradeNo(String outTradeNo) {
        QmOrderPO qmOrderPO=qmOrderReadManage.findByOutTradeNo(outTradeNo);
        return QmOrderConverter.toDTO(qmOrderPO);
    }

    @Override
    public QmOrderDTO findByOrderCode(String orderCode) {
        QmOrderPO qmOrderPO=qmOrderReadManage.findByOrderCode(orderCode);
        return QmOrderConverter.toDTO(qmOrderPO);
    }

    @Override
    public QmOrderDTO findBySoId(Long soId) {
        QmOrderPO qmOrderPO=qmOrderReadManage.findBySoId(soId);
        return QmOrderConverter.toDTO(qmOrderPO);
    }


    @Override
    public List<QmOrderDTO> findWaitSyncOrder(Integer synPayMaxTimes,Integer pageSize) {
        return QmOrderConverter.toDTOList(qmOrderReadManage.findWaitSyncOrder(synPayMaxTimes,pageSize));
    }
}
