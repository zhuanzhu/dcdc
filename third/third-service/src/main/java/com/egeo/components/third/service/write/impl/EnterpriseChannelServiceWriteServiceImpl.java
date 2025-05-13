package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.EnterpriseChannelServiceConverter;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.manage.write.EnterpriseChannelServiceWriteManage;
import com.egeo.components.third.po.EnterpriseChannelServicePO;
import com.egeo.components.third.service.write.EnterpriseChannelServiceWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("enterpriseChannelServiceWriteService")
public class EnterpriseChannelServiceWriteServiceImpl implements EnterpriseChannelServiceWriteService {

    @Autowired
    private EnterpriseChannelServiceWriteManage enterpriseChannelServiceWriteManage;
    @Override
    public Long insertEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto) {
        EnterpriseChannelServicePO po = EnterpriseChannelServiceConverter.toPO(dto);
        Long rt = enterpriseChannelServiceWriteManage.insertEnterpriseChannelServiceWithTx(po);
        return rt;
    }

    @Override
    public int updateEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto) {
        EnterpriseChannelServicePO po = EnterpriseChannelServiceConverter.toPO(dto);
        int rt = enterpriseChannelServiceWriteManage.updateEnterpriseChannelServiceWithTx(po);
        return rt;
    }

    @Override
    public int deleteEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto) {
        EnterpriseChannelServicePO po = EnterpriseChannelServiceConverter.toPO(dto);
        int rt = enterpriseChannelServiceWriteManage.deleteEnterpriseChannelServiceWithTx(po);
        return rt;
    }
}
