package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdPictureManage;
import com.egeo.components.product.facade.MerchantProdPictureFacade;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdPicture")
public class MerchantProdPictureManageImpl implements MerchantProdPictureManage{

	
	@Resource(name="merchantProdPictureFacade")
	private MerchantProdPictureFacade merchantProdPictureFacade;

	@Override
	public MerchantProdPictureDTO findMerchantProdPictureById(MerchantProdPictureDTO dto) {
		return merchantProdPictureFacade.findMerchantProdPictureById(dto);
	}

	@Override
	public PageResult<MerchantProdPictureDTO> findMerchantProdPictureOfPage(MerchantProdPictureDTO dto, Pagination page) {
		return merchantProdPictureFacade.findMerchantProdPictureOfPage(dto, page);
	}

	@Override
	public List<MerchantProdPictureDTO> findMerchantProdPictureAll(MerchantProdPictureDTO dto) {
		return merchantProdPictureFacade.findMerchantProdPictureAll(dto);
	}

	@Override
	public Long insertMerchantProdPictureWithTx(MerchantProdPictureDTO dto) {
		return merchantProdPictureFacade.insertMerchantProdPictureWithTx(dto);
	}

	@Override
	public int updateMerchantProdPictureWithTx(MerchantProdPictureDTO dto) {
		return merchantProdPictureFacade.updateMerchantProdPictureWithTx(dto);
	}

	@Override
	public int deleteMerchantProdPictureWithTx(MerchantProdPictureDTO dto) {
		return merchantProdPictureFacade.deleteMerchantProdPictureWithTx(dto);
	}


}
	