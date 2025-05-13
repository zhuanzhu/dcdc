package com.egeo.components.product.service.read;


import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantReadService {

	public MerchantDTO findMerchantById(MerchantDTO dto);

	public PageResult<MerchantDTO> findMerchantOfPage(MerchantDTO dto,Pagination page);

	public List<MerchantDTO> findMerchantAll(MerchantDTO dto);

    List<MerchantDTO> findMerchantList();

    List<MerchantDTO> findMerchantListByType(Integer type);
}
	