package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.JdPriceLimitUploadPO;


public interface JdPriceLimitUploadWriteManage {

	Long insertJdPriceLimitUploadWithTx(JdPriceLimitUploadPO po);

	int updateJdPriceLimitUploadWithTx(JdPriceLimitUploadPO po);

	int deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadPO po);
}
	