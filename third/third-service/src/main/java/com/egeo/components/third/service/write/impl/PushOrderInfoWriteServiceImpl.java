package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.PushOrderInfoConverter;
import com.egeo.components.third.dto.PushOrderInfoDTO;
import com.egeo.components.third.manage.write.PushOrderInfoWriteManage;
import com.egeo.components.third.po.PushOrderInfoPO;
import com.egeo.components.third.service.write.PushOrderInfoWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("pushOrderInfoWriteService")
public class PushOrderInfoWriteServiceImpl implements PushOrderInfoWriteService {


    @Autowired
    private PushOrderInfoWriteManage pushOrderInfoWriteManage;
    @Override
    public Long insertPushOrderInfoWithTx(PushOrderInfoDTO dto) {
        PushOrderInfoPO po = PushOrderInfoConverter.toPO(dto);
        Long rt = pushOrderInfoWriteManage.insertPushOrderInfoWithTx(po);
        return rt;
    }

    @Override
    public int updatePushOrderInfoWithTx(PushOrderInfoDTO dto) {
        PushOrderInfoPO po = PushOrderInfoConverter.toPO(dto);
        int rt = pushOrderInfoWriteManage.updatePushOrderInfoWithTx(po);
        return rt;
    }

    @Override
    public int deletePushOrderInfoWithTx(PushOrderInfoDTO dto) {
        PushOrderInfoPO po = PushOrderInfoConverter.toPO(dto);
        int rt = pushOrderInfoWriteManage.deletePushOrderInfoWithTx(po);
        return rt;
    }
}
