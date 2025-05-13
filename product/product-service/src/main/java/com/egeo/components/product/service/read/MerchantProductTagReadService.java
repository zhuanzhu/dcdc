package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductTagReadService {

	public MerchantProductTagDTO findMerchantProductTagById(MerchantProductTagDTO dto);

	public PageResult<MerchantProductTagDTO> findMerchantProductTagOfPage(MerchantProductTagDTO dto,Pagination page);

	public List<MerchantProductTagDTO> findMerchantProductTagAll(MerchantProductTagDTO dto);
	/**
	 * 根据su草稿id查询标签信息集合
	 * @param id
	 * @return
	 */
	public List<MerchantProductTagDTO> findTagAllByMerchantProductId(Long merchantProductId);
}
	