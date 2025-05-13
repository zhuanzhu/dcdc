package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface StandardUnitCardBaseSuWriteService {
    public Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto);

    public int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto);

    public int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto);
}
