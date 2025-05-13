package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantPictureReadService;
import com.egeo.components.product.service.write.MerchantPictureWriteService;
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantPictureFacade {
	
	@Resource
	private MerchantPictureReadService merchantPictureReadService;
	
	@Resource
	private MerchantPictureWriteService merchantPictureWriteService;
	
	
	public MerchantPictureDTO findMerchantPictureById(MerchantPictureDTO dto){
		
		return merchantPictureReadService.findMerchantPictureById(dto);
	}

	public PageResult<MerchantPictureDTO> findMerchantPictureOfPage(MerchantPictureDTO dto,Pagination page){
		
		return merchantPictureReadService.findMerchantPictureOfPage(dto, page);
		
	}

	public List<MerchantPictureDTO> findMerchantPictureAll(MerchantPictureDTO dto){
		
		return merchantPictureReadService.findMerchantPictureAll(dto);
		
	}

	public Long insertMerchantPictureWithTx(MerchantPictureDTO dto){
		
		return merchantPictureWriteService.insertMerchantPictureWithTx(dto);
	}

	public int updateMerchantPictureWithTx(MerchantPictureDTO dto){
		
		return merchantPictureWriteService.updateMerchantPictureWithTx(dto);
	}

	public int deleteMerchantPictureWithTx(MerchantPictureDTO dto){
		
		return merchantPictureWriteService.deleteMerchantPictureWithTx(dto);
		
	}

}
	