package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdPictureManage {

	public MerchantProdPictureDTO findMerchantProdPictureById(MerchantProdPictureDTO dto);	

	public PageResult<MerchantProdPictureDTO> findMerchantProdPictureOfPage(MerchantProdPictureDTO dto,Pagination page);

	public List<MerchantProdPictureDTO> findMerchantProdPictureAll(MerchantProdPictureDTO dto);

	Long insertMerchantProdPictureWithTx(MerchantProdPictureDTO dto);

	int updateMerchantProdPictureWithTx(MerchantProdPictureDTO dto);

	int deleteMerchantProdPictureWithTx(MerchantProdPictureDTO dto);
}
	