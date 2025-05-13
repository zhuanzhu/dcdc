package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdPictureReadService {

	public MerchantProdPictureDTO findMerchantProdPictureById(MerchantProdPictureDTO dto);

	public PageResult<MerchantProdPictureDTO> findMerchantProdPictureOfPage(MerchantProdPictureDTO dto,Pagination page);

	public List<MerchantProdPictureDTO> findMerchantProdPictureAll(MerchantProdPictureDTO dto);
	/**
	 * 根据su草稿id查询su草稿图片信息
	 * @param merchantProductId
	 * @return
	 */
	public List<MerchantProdPictureDTO> findByMerchantProductId(Long merchantProductId);
}
	