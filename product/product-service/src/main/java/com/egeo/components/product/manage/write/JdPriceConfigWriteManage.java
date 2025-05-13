package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.JdPriceConfigPO;


public interface JdPriceConfigWriteManage {

	Long insertJdPriceConfigWithTx(JdPriceConfigPO po);

	int updateJdPriceConfigWithTx(JdPriceConfigPO po);

	int deleteJdPriceConfigWithTx(JdPriceConfigPO po);
}
	