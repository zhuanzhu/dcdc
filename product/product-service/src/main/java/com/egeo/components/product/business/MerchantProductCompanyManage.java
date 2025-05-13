package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductCompanyManage {

	public MerchantProductCompanyDTO findMerchantProductCompanyById(MerchantProductCompanyDTO dto);	

	public PageResult<MerchantProductCompanyDTO> findMerchantProductCompanyOfPage(MerchantProductCompanyDTO dto,Pagination page);

	public List<MerchantProductCompanyDTO> findMerchantProductCompanyAll(MerchantProductCompanyDTO dto);

	Long insertMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto);

	int updateMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto);

	int deleteMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto);
}
	