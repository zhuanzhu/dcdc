package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.QrCodeCondition;
import com.egeo.components.user.po.QrCodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface QrCodeReadManage {

	public QrCodePO findQrCodeById(QrCodePO po);

	public PageResult<QrCodePO> findQrCodeOfPage(QrCodePO po,Pagination page);

	public List<QrCodePO> findQrCodeAll(QrCodePO po);

	List<QrCodeCondition> findQrCodeListByCouponUnitIds(List<Long> couponUnitIds);

    QrCodeCondition findQrCodeByCouponUnitId(Long id);
}
	