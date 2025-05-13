package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdAttNameManage {

	public MerchantProdAttNameDTO findMerchantProdAttNameById(MerchantProdAttNameDTO dto);	

	public PageResult<MerchantProdAttNameDTO> findMerchantProdAttNameOfPage(MerchantProdAttNameDTO dto,Pagination page);

	public List<MerchantProdAttNameDTO> findMerchantProdAttNameAll(MerchantProdAttNameDTO dto);

	Long insertMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto);

	int updateMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto);

	int deleteMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto);
}
	