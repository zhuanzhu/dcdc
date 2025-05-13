package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.EnterpriseChannelBaffleConverter;
import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.manage.write.EnterpriseChannelBaffleWriteManage;
import com.egeo.components.third.po.EnterpriseChannelBafflePO;
import com.egeo.components.third.service.write.EnterpriseChannelBaffleWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class EnterpriseChannelBaffleWriteServiceImpl implements EnterpriseChannelBaffleWriteService {


    @Autowired
    private EnterpriseChannelBaffleWriteManage enterpriseChannelBaffleWriteManage;
    @Override
    public Long insertEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto) {
        EnterpriseChannelBafflePO po = EnterpriseChannelBaffleConverter.toPO(dto);
        Long rt = enterpriseChannelBaffleWriteManage.insertEnterpriseChannelBaffleWithTx(po);
        return rt;
    }

    @Override
    public int updateEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto) {
        EnterpriseChannelBafflePO po = EnterpriseChannelBaffleConverter.toPO(dto);
        int rt = enterpriseChannelBaffleWriteManage.updateEnterpriseChannelBaffleWithTx(po);
        return rt;
    }

    @Override
    public int deleteEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto) {
        EnterpriseChannelBafflePO po = EnterpriseChannelBaffleConverter.toPO(dto);
        int rt = enterpriseChannelBaffleWriteManage.deleteEnterpriseChannelBaffleWithTx(po);
        return rt;
    }
}
