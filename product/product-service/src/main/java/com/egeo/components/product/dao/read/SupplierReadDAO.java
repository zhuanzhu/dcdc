package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.SupplierPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierReadDAO extends BaseReadDAO<SupplierPO>{

    List<SupplierPO> findByIdList(@Param("idList")List<Long> ids);

}
	