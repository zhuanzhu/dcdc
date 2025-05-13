package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.AdCodePO;


public interface AdCodeWriteManage {

	Long insertAdCodeWithTx(AdCodePO po);

	int updateAdCodeWithTx(AdCodePO po);

	int deleteAdCodeWithTx(AdCodePO po);
}
	