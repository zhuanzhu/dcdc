package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.EnterpriseChannelServiceChoiceConverter;
import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.manage.write.EnterpriseChannelServiceChoiceWriteManage;
import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;
import com.egeo.components.third.service.write.EnterpriseChannelServiceChoiceWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("enterpriseChannelServiceChoiceWriteService")
public class EnterpriseChannelServiceChoiceWriteServiceImpl implements EnterpriseChannelServiceChoiceWriteService {

    @Autowired
    private EnterpriseChannelServiceChoiceWriteManage enterpriseChannelServiceChoiceWriteManage;
    @Override
    public Long insertEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto) {
        EnterpriseChannelServiceChoicePO po = EnterpriseChannelServiceChoiceConverter.toPO(dto);
        Long rt = enterpriseChannelServiceChoiceWriteManage.insertEnterpriseChannelServiceChoiceWithTx(po);
        return rt;
    }

    @Override
    public int updateEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto) {
        EnterpriseChannelServiceChoicePO po = EnterpriseChannelServiceChoiceConverter.toPO(dto);
        int rt = enterpriseChannelServiceChoiceWriteManage.updateEnterpriseChannelServiceChoiceWithTx(po);
        return rt;
    }

    @Override
    public int deleteEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto) {
        EnterpriseChannelServiceChoicePO po = EnterpriseChannelServiceChoiceConverter.toPO(dto);
        int rt = enterpriseChannelServiceChoiceWriteManage.deleteEnterpriseChannelServiceChoiceWithTx(po);
        return rt;
    }
}
