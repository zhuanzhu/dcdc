package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdAttValueManage {

	public MerchantProdAttValueDTO findMerchantProdAttValueById(MerchantProdAttValueDTO dto);	

	public PageResult<MerchantProdAttValueDTO> findMerchantProdAttValueOfPage(MerchantProdAttValueDTO dto,Pagination page);

	public List<MerchantProdAttValueDTO> findMerchantProdAttValueAll(MerchantProdAttValueDTO dto);

	Long insertMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto);

	int updateMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto);

	int deleteMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto);

    void insertList(List<String> my);
}
	