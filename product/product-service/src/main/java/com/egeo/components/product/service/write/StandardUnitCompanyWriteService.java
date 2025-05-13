package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitCompanyDTO;

import java.util.List;


public interface StandardUnitCompanyWriteService {

	public Long insertStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto);

	public int updateStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto);

	public int deleteStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto);
	/**
	 * 根据suid删除su福利企业关系信息
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long standardUnitId);

    void saveStandardUnitCompany(List<Long> suIdList);
}
	