package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductStoreManage {

	public MerchantProductStoreDTO findMerchantProductStoreById(MerchantProductStoreDTO dto);	

	public PageResult<MerchantProductStoreDTO> findMerchantProductStoreOfPage(MerchantProductStoreDTO dto,Pagination page);

	public List<MerchantProductStoreDTO> findMerchantProductStoreAll(MerchantProductStoreDTO dto);

	Long insertMerchantProductStoreWithTx(MerchantProductStoreDTO dto);

	int updateMerchantProductStoreWithTx(MerchantProductStoreDTO dto);

	int deleteMerchantProductStoreWithTx(MerchantProductStoreDTO dto);
}
	