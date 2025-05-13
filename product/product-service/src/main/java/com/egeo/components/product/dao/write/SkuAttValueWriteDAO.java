package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuAttValueWriteDAO extends BaseWriteDAO<SkuAttValuePO> {
    void saveSkuAttValuePO(@Param("poList")List<SkuAttValuePO> skuAttValuePOList);
}
	