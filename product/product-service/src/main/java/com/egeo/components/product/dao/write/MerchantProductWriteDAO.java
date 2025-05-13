package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantProductWriteDAO extends BaseWriteDAO<MerchantProductPO> {
    void saveMerchantProduct(@Param("poList")List<MerchantProductPO> merchantProductPOList);

    void updateMerchantProductPrice(@Param("poList")List<MerchantProductPO> merchantProductPricePOList);

    void updateJdProductPriceByRate(@Param("competingCompanyRate")Integer competingCompanyRate,
                                    @Param("democompanysCompanyRate")Integer democompanysCompanyRate,
                                    @Param("standardCompanyRate")Integer standardCompanyRate);

    void updateMerchantProductList(@Param("poList")List<MerchantProductPO> merchantProductPOList);

    void updateSuAndPuStatusByJd();

    void updateJdProductStatusByProfit(@Param("profit")Integer productLimitProfit);
}
	