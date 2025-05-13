package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.SplitOrderConfigConverter;
import com.egeo.components.third.dto.SplitOrderConfigDTO;
import com.egeo.components.third.manage.write.SplitOrderConfigWriteManage;
import com.egeo.components.third.po.SplitOrderConfigPO;
import com.egeo.components.third.service.write.SplitOrderConfigWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("splitOrderConfigWriteService")
public class SplitOrderConfigWriteServiceImpl implements SplitOrderConfigWriteService {


    @Autowired
    private SplitOrderConfigWriteManage splitOrderConfigWriteManage;
    @Override
    public Long insertSplitOrderConfigWithTx(SplitOrderConfigDTO dto) {
        SplitOrderConfigPO po = SplitOrderConfigConverter.toPO(dto);
        Long rt = splitOrderConfigWriteManage.insertSplitOrderConfigWithTx(po);
        return rt;
    }

    @Override
    public int updateSplitOrderConfigWithTx(SplitOrderConfigDTO dto) {
        SplitOrderConfigPO po = SplitOrderConfigConverter.toPO(dto);
        int rt = splitOrderConfigWriteManage.updateSplitOrderConfigWithTx(po);
        return rt;
    }

    @Override
    public int deleteSplitOrderConfigWithTx(SplitOrderConfigDTO dto) {
        SplitOrderConfigPO po = SplitOrderConfigConverter.toPO(dto);
        int rt = splitOrderConfigWriteManage.deleteSplitOrderConfigWithTx(po);
        return rt;
    }
}
