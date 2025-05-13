package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdPictureReadService;
import com.egeo.components.product.service.write.MerchantProdPictureWriteService;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdPictureFacade {
	
	@Resource
	private MerchantProdPictureReadService merchantProdPictureReadService;
	
	@Resource
	private MerchantProdPictureWriteService merchantProdPictureWriteService;
	
	
	public MerchantProdPictureDTO findMerchantProdPictureById(MerchantProdPictureDTO dto){
		
		return merchantProdPictureReadService.findMerchantProdPictureById(dto);
	}

	public PageResult<MerchantProdPictureDTO> findMerchantProdPictureOfPage(MerchantProdPictureDTO dto,Pagination page){
		
		return merchantProdPictureReadService.findMerchantProdPictureOfPage(dto, page);
		
	}

	public List<MerchantProdPictureDTO> findMerchantProdPictureAll(MerchantProdPictureDTO dto){
		
		return merchantProdPictureReadService.findMerchantProdPictureAll(dto);
		
	}

	public Long insertMerchantProdPictureWithTx(MerchantProdPictureDTO dto){
		
		return merchantProdPictureWriteService.insertMerchantProdPictureWithTx(dto);
	}

	public int updateMerchantProdPictureWithTx(MerchantProdPictureDTO dto){
		
		return merchantProdPictureWriteService.updateMerchantProdPictureWithTx(dto);
	}

	public int deleteMerchantProdPictureWithTx(MerchantProdPictureDTO dto){
		
		return merchantProdPictureWriteService.deleteMerchantProdPictureWithTx(dto);
		
	}
	/**
	 * 根据su草稿图片关系id集合批量删除su草稿图片关系
	 * @param merchantProdPictureIds
	 * @return
	 */
	public int deleteBymerchantProdPictureIds(List<Long> merchantProdPictureIds) {
		// TODO Auto-generated method stub
		return merchantProdPictureWriteService.deleteBymerchantProdPictureIdsWithTx(merchantProdPictureIds);
	}

}
	