package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantPictureManage;
import com.egeo.components.product.facade.MerchantPictureFacade;
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantPicture")
public class MerchantPictureManageImpl implements MerchantPictureManage{

	
	@Resource(name="merchantPictureFacade")
	private MerchantPictureFacade merchantPictureFacade;

	@Override
	public MerchantPictureDTO findMerchantPictureById(MerchantPictureDTO dto) {
		return merchantPictureFacade.findMerchantPictureById(dto);
	}

	@Override
	public PageResult<MerchantPictureDTO> findMerchantPictureOfPage(MerchantPictureDTO dto, Pagination page) {
		return merchantPictureFacade.findMerchantPictureOfPage(dto, page);
	}

	@Override
	public List<MerchantPictureDTO> findMerchantPictureAll(MerchantPictureDTO dto) {
		return merchantPictureFacade.findMerchantPictureAll(dto);
	}

	@Override
	public Long insertMerchantPictureWithTx(MerchantPictureDTO dto) {
		return merchantPictureFacade.insertMerchantPictureWithTx(dto);
	}

	@Override
	public int updateMerchantPictureWithTx(MerchantPictureDTO dto) {
		return merchantPictureFacade.updateMerchantPictureWithTx(dto);
	}

	@Override
	public int deleteMerchantPictureWithTx(MerchantPictureDTO dto) {
		return merchantPictureFacade.deleteMerchantPictureWithTx(dto);
	}


}
	