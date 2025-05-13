package com.egeo.components.user.dao.read;

import com.egeo.components.user.po.PayTypePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayTypeReadDAO extends BaseReadDAO<PayTypePO>{
    PayTypePO findByCode(Integer payTypeCode);

    List<PayTypePO> findByCodes(@Param("payTypeCodes") List<Integer> payTypeCodes);
}
	