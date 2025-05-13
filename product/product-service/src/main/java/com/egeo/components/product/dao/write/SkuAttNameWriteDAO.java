package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuAttNameWriteDAO extends BaseWriteDAO<SkuAttNamePO> {
    void saveSkuAttName(@Param("poList")List<SkuAttNamePO> skuAttNamePOList);
}
	