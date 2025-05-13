package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.SkuPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuWriteDAO extends BaseWriteDAO<SkuPO> {
    int updateSkuParamsWithTx(@Param("po")SkuPO po);

    void saveSku(@Param("poList") List<SkuPO> skuPOList);

    void updateSkuList(@Param("poList")List<SkuPO> skuPOList);
}
	