package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.StandardUnitCardBaseSuConverter;
import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.manage.write.StandardUnitCardBaseSuWriteManage;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
import com.egeo.components.product.service.write.StandardUnitCardBaseSuWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("standardUnitCardBaseSuWriteService")
public class StandardUnitCardBaseSuWriteServiceImpl implements StandardUnitCardBaseSuWriteService {



    @Autowired
    private StandardUnitCardBaseSuWriteManage standardUnitCardBaseSuWriteManage;
    @Override
    public Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto) {
        StandardUnitCardBaseSuPO po = StandardUnitCardBaseSuConverter.toPO(dto);
        Long rt = standardUnitCardBaseSuWriteManage.insertStandardUnitCardBaseSuWithTx(po);
        return rt;
    }

    @Override
    public int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto) {
        StandardUnitCardBaseSuPO po = StandardUnitCardBaseSuConverter.toPO(dto);
        int rt = standardUnitCardBaseSuWriteManage.updateStandardUnitCardBaseSuWithTx(po);
        return rt;
    }

    @Override
    public int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto) {
        StandardUnitCardBaseSuPO po = StandardUnitCardBaseSuConverter.toPO(dto);
        int rt = standardUnitCardBaseSuWriteManage.deleteStandardUnitCardBaseSuWithTx(po);
        return rt;
    }
}
