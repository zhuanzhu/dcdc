package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdSalesRecordManage {

	public MerchantProdSalesRecordDTO findMerchantProdSalesRecordById(MerchantProdSalesRecordDTO dto);	

	public PageResult<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordDTO dto,Pagination page);

	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordDTO dto);

	Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);

	int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);

	int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);
}
	