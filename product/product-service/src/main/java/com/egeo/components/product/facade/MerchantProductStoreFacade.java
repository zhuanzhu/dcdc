package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProductStoreReadService;
import com.egeo.components.product.service.write.MerchantProductStoreWriteService;
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProductStoreFacade {
	
	@Resource
	private MerchantProductStoreReadService merchantProductStoreReadService;
	
	@Resource
	private MerchantProductStoreWriteService merchantProductStoreWriteService;
	
	
	public MerchantProductStoreDTO findMerchantProductStoreById(MerchantProductStoreDTO dto){
		
		return merchantProductStoreReadService.findMerchantProductStoreById(dto);
	}

	public PageResult<MerchantProductStoreDTO> findMerchantProductStoreOfPage(MerchantProductStoreDTO dto,Pagination page){
		
		return merchantProductStoreReadService.findMerchantProductStoreOfPage(dto, page);
		
	}

	public List<MerchantProductStoreDTO> findMerchantProductStoreAll(MerchantProductStoreDTO dto){
		
		return merchantProductStoreReadService.findMerchantProductStoreAll(dto);
		
	}

	public Long insertMerchantProductStoreWithTx(MerchantProductStoreDTO dto){
		
		return merchantProductStoreWriteService.insertMerchantProductStoreWithTx(dto);
	}

	public int updateMerchantProductStoreWithTx(MerchantProductStoreDTO dto){
		
		return merchantProductStoreWriteService.updateMerchantProductStoreWithTx(dto);
	}

	public int deleteMerchantProductStoreWithTx(MerchantProductStoreDTO dto){
		
		return merchantProductStoreWriteService.deleteMerchantProductStoreWithTx(dto);
		
	}

}
	