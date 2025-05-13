package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.JdProductPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JdProductWriteDAO extends BaseWriteDAO<JdProductPO> {
	
	public void updateSyncStatus(@Param("po")JdProductPO po);

    void saveJdProductListFirst(@Param("poList")List<JdProductPO> param);

    void setAllSyncStatus(@Param("status")int status);

    void updateList(@Param("poList")List<JdProductPO> updateList);

    void updateJdProductPrice(@Param("poList")List<JdProductPO> jdProductPOList);

    void updateProductCreateTime(@Param("idList")List<Long> jdProductIdList);
}
	