package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitCardBaseSuPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface StandardUnitCardBaseSuWriteManage {


    public Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuPO po);

    public int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuPO po);

    public int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuPO po);
}
