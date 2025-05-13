package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdCauseManage {

	public MerchantProdCauseDTO findMerchantProdCauseById(MerchantProdCauseDTO dto);	

	public PageResult<MerchantProdCauseDTO> findMerchantProdCauseOfPage(MerchantProdCauseDTO dto,Pagination page);

	public List<MerchantProdCauseDTO> findMerchantProdCauseAll(MerchantProdCauseDTO dto);

	Long insertMerchantProdCauseWithTx(MerchantProdCauseDTO dto);

	int updateMerchantProdCauseWithTx(MerchantProdCauseDTO dto);

	int deleteMerchantProdCauseWithTx(MerchantProdCauseDTO dto);
	/**
	 * 审核是否通过
	 * @param vo
	 * @param req
	 * @return
	 */
	public int isPass(MerchantProdCauseDTO dto);
}
	