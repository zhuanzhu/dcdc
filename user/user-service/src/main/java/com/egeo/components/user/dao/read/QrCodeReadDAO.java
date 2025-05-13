package com.egeo.components.user.dao.read;

import com.egeo.components.user.condition.QrCodeCondition;
import com.egeo.components.user.po.QrCodePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QrCodeReadDAO extends BaseReadDAO<QrCodePO>{
    List<QrCodeCondition> findQrCodeListByCouponUnitIds(@Param("couponUnitIds")List<Long> couponUnitIds);

    QrCodeCondition findQrCodeByCouponUnitId(@Param("id")Long id);
}
	