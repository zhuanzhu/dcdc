package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantPictureManage {

	public MerchantPictureDTO findMerchantPictureById(MerchantPictureDTO dto);	

	public PageResult<MerchantPictureDTO> findMerchantPictureOfPage(MerchantPictureDTO dto,Pagination page);

	public List<MerchantPictureDTO> findMerchantPictureAll(MerchantPictureDTO dto);

	Long insertMerchantPictureWithTx(MerchantPictureDTO dto);

	int updateMerchantPictureWithTx(MerchantPictureDTO dto);

	int deleteMerchantPictureWithTx(MerchantPictureDTO dto);
}
	