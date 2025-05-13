package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantProdDescribeWriteDAO extends BaseWriteDAO<MerchantProdDescribePO> {
    void saveMerchantProdDescribe(@Param("poList")List<MerchantProdDescribePO> merchantProdDescribePOList);

    void updateMerchantProdDescribeSWithTx(@Param("po")MerchantProdDescribePO merchantProdDescribePO);
}
	