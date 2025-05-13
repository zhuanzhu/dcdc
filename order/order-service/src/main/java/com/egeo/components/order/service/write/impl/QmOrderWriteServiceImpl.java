package com.egeo.components.order.service.write.impl;

import com.egeo.components.order.converter.QmOrderConverter;
import com.egeo.components.order.dao.write.QmOrderWriteDAO;
import com.egeo.components.order.dto.QmOrderDTO;
import com.egeo.components.order.service.write.QmOrderWriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QmOrderWriteServiceImpl implements QmOrderWriteService {

    @Resource
    private QmOrderWriteDAO qmOrderWriteDAO;


    @Override
    public int saveOrder(QmOrderDTO qmOrderDTO) {
        return qmOrderWriteDAO.insert(QmOrderConverter.toPo(qmOrderDTO));
    }

    @Override
    public int updateOrder(QmOrderDTO qmOrderDTO) {
        return qmOrderWriteDAO.update(QmOrderConverter.toPo(qmOrderDTO));
    }
}
