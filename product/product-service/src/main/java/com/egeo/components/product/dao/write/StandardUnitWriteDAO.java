package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardUnitWriteDAO extends BaseWriteDAO<StandardUnitPO> {
    void saveStandardUnit(@Param("poList")List<StandardUnitPO> standardUnitPOList);

    void updateStandardUnitPrice(@Param("poList")List<StandardUnitPO> standardUnitPricePOList);

    void updateStandardUnitPriceList(@Param("poList")List<StandardUnitPO> standardUnitPricePOList);

    void updateJdProductPriceByRate(@Param("competingCompanyRate")Integer competingCompanyRate,
                                    @Param("democompanysCompanyRate")Integer democompanysCompanyRate,
                                    @Param("standardCompanyRate")Integer standardCompanyRate);

    void updateSUList(@Param("poList")List<StandardUnitPO> standardUnitPOList);

    void updateSuAndPuStatusByJd();

    void updateJdProductStatusByProfit(@Param("profit")Integer productLimitProfit);
}
	