package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdClientManage {

	public MerchantProdClientDTO findMerchantProdClientById(MerchantProdClientDTO dto);	

	public PageResult<MerchantProdClientDTO> findMerchantProdClientOfPage(MerchantProdClientDTO dto,Pagination page);

	public List<MerchantProdClientDTO> findMerchantProdClientAll(MerchantProdClientDTO dto);

	Long insertMerchantProdClientWithTx(MerchantProdClientDTO dto);

	int updateMerchantProdClientWithTx(MerchantProdClientDTO dto);

	int deleteMerchantProdClientWithTx(MerchantProdClientDTO dto);
}
	