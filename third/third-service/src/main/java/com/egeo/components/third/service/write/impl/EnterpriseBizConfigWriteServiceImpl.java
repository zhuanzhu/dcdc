package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.EnterpriseBizConfigConverter;
import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.third.manage.write.EnterpriseBizConfigWriteManage;
import com.egeo.components.third.po.EnterpriseBizConfigPO;
import com.egeo.components.third.service.write.EnterpriseBizConfigWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("enterpriseBizConfigWriteService")
public class EnterpriseBizConfigWriteServiceImpl implements EnterpriseBizConfigWriteService {

    @Autowired
    private EnterpriseBizConfigWriteManage enterpriseBizConfigWriteManage;
    @Override
    public Long insertEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto) {
        EnterpriseBizConfigPO po = EnterpriseBizConfigConverter.toPO(dto);
        Long rt = enterpriseBizConfigWriteManage.insertEnterpriseBizConfigWithTx(po);
        return rt;
    }

    @Override
    public int updateEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto) {
        EnterpriseBizConfigPO po = EnterpriseBizConfigConverter.toPO(dto);
        int rt = enterpriseBizConfigWriteManage.updateEnterpriseBizConfigWithTx(po);
        return rt;
    }

    @Override
    public int deleteEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto) {
        EnterpriseBizConfigPO po = EnterpriseBizConfigConverter.toPO(dto);
        int rt = enterpriseBizConfigWriteManage.deleteEnterpriseBizConfigWithTx(po);
        return rt;
    }
}
