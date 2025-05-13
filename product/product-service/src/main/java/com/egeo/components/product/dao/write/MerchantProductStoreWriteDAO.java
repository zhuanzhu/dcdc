package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantProductStoreWriteDAO extends BaseWriteDAO<MerchantProductStorePO> {
    void saveMerchantProductStore(@Param("poList")List<MerchantProductStorePO> merchantProductStorePOList);
}
	