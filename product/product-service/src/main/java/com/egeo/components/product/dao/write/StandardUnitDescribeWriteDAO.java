package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardUnitDescribeWriteDAO extends BaseWriteDAO<StandardUnitDescribePO> {
	/**
	 * 根据suid同步su商品详情信息
	 * @param standardUnitDescribeDTO
	 * @return
	 */
	int updateByStandardUnitIdWithTx(StandardUnitDescribePO po);

    void saveStandardUnitDescribe(@Param("poList")List<StandardUnitDescribePO> standardUnitDescribePOList);

    void updateStandardUnitDescribeSWithTx(@Param("po")StandardUnitDescribePO standardUnitDescribePO);
}
	