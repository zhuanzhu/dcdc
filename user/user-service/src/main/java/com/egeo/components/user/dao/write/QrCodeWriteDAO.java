package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.QrCodePO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface QrCodeWriteDAO extends BaseWriteDAO<QrCodePO> {

	int updateByccId(@Param("po")QrCodePO po);

    int insertList(@Param("poList")List<QrCodePO> poList);

    int updateQrCodeByCouponUnitIds(@Param("couponUnitIds")List<Long> couponUnitIds, @Param("channelActivityId")Long channelActivityId, @Param("channelId")Long channelId);
}
	