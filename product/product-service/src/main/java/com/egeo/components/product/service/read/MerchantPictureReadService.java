package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantPictureReadService {

	public MerchantPictureDTO findMerchantPictureById(MerchantPictureDTO dto);

	public PageResult<MerchantPictureDTO> findMerchantPictureOfPage(MerchantPictureDTO dto,Pagination page);

	public List<MerchantPictureDTO> findMerchantPictureAll(MerchantPictureDTO dto);

    Long findLastId();
}
	