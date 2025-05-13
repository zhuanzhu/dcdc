package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.MerchantProdAttValuePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantProdAttValueWriteDAO extends BaseWriteDAO<MerchantProdAttValuePO> {
    void insertList(@Param("poList")List<MerchantProdAttValuePO> list);
}
	