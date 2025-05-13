package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.MerchantProductCompanyPO;


public interface MerchantProductCompanyWriteManage {

	Long insertMerchantProductCompanyWithTx(MerchantProductCompanyPO po);

	int updateMerchantProductCompanyWithTx(MerchantProductCompanyPO po);

	int deleteMerchantProductCompanyWithTx(MerchantProductCompanyPO po);
	/**
	 * 根据拼接的su草稿企业关系id集合批量删除
	 * @param merchantProductCompanyIds
	 * @return
	 */
	int deleteByMerchantProductCompanyIdsWithTx(List<Long> merchantProductCompanyIds);

    void saveMerchantProductCompany(List<MerchantProductCompanyPO> merchantProductCompanyPOList);
}
	