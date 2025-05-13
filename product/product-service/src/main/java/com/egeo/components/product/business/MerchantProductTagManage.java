package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductTagManage {

	public MerchantProductTagDTO findMerchantProductTagById(MerchantProductTagDTO dto);	

	public PageResult<MerchantProductTagDTO> findMerchantProductTagOfPage(MerchantProductTagDTO dto,Pagination page);

	public List<MerchantProductTagDTO> findMerchantProductTagAll(MerchantProductTagDTO dto);

	Long insertMerchantProductTagWithTx(MerchantProductTagDTO dto);

	int updateMerchantProductTagWithTx(MerchantProductTagDTO dto);

	int deleteMerchantProductTagWithTx(MerchantProductTagDTO dto);
}
	