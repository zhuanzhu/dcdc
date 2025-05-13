package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.MerchantProductCompanyDTO;


public interface MerchantProductCompanyWriteService {

	public Long insertMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto);

	public int updateMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto);

	public int deleteMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto);
	/**
	 * 根据拼接的su草稿企业关系id集合批量删除
	 * @param merchantProductCompanyIds
	 * @return
	 */

	public int deleteByMerchantProductCompanyIdsWithTx(List<Long> merchantProductCompanyIds);

    void saveMerchantProductCompany(List<Long> merchantProductIdList);
}
	