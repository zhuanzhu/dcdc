package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.QmOrderDTO;

public interface QmOrderWriteService {

    int saveOrder(QmOrderDTO qmOrderDTO);

    int updateOrder(QmOrderDTO qmOrderDTO);

}
