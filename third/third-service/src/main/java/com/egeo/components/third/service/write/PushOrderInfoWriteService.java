package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.PushOrderInfoDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface PushOrderInfoWriteService {

    public Long insertPushOrderInfoWithTx(PushOrderInfoDTO dto);

    public int updatePushOrderInfoWithTx(PushOrderInfoDTO dto);

    public int deletePushOrderInfoWithTx(PushOrderInfoDTO dto);
}
