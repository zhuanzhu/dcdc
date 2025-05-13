package com.egeo.components.order.manage.read.impl;

import com.egeo.components.order.dao.read.QmOrderReadDAO;
import com.egeo.components.order.manage.read.QmOrderReadManage;
import com.egeo.components.order.po.QmOrderPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QmOrderReadManageImpl implements QmOrderReadManage {
    @Resource
    private QmOrderReadDAO qmOrderReadDAO;

    @Override
    public QmOrderPO findByOutTradeNo(String outTradeNo) {
        return qmOrderReadDAO.findByOutTradeNo(outTradeNo);
    }

    @Override
    public QmOrderPO findByOrderCode(String orderCode) {
        return qmOrderReadDAO.findByOrderCode(orderCode);
    }

    @Override
    public QmOrderPO findBySoId(Long soId) {
        return qmOrderReadDAO.findBySoId(soId);
    }

    @Override
    public List<QmOrderPO> findWaitSyncOrder(Integer synPayMaxTimes,Integer pageSize) {
        return qmOrderReadDAO.findWaitSyncOrder(synPayMaxTimes,pageSize);
    }
}
