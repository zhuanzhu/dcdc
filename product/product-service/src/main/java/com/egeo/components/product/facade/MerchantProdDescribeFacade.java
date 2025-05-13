package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdDescribeReadService;
import com.egeo.components.product.service.write.MerchantProdDescribeWriteService;
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdDescribeFacade {
	
	@Resource
	private MerchantProdDescribeReadService merchantProdDescribeReadService;
	
	@Resource
	private MerchantProdDescribeWriteService merchantProdDescribeWriteService;
	
	
	public MerchantProdDescribeDTO findMerchantProdDescribeById(MerchantProdDescribeDTO dto){
		
		return merchantProdDescribeReadService.findMerchantProdDescribeById(dto);
	}

	public PageResult<MerchantProdDescribeDTO> findMerchantProdDescribeOfPage(MerchantProdDescribeDTO dto,Pagination page){
		
		return merchantProdDescribeReadService.findMerchantProdDescribeOfPage(dto, page);
		
	}

	public List<MerchantProdDescribeDTO> findMerchantProdDescribeAll(MerchantProdDescribeDTO dto){
		
		return merchantProdDescribeReadService.findMerchantProdDescribeAll(dto);
		
	}

	public Long insertMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto){
		
		return merchantProdDescribeWriteService.insertMerchantProdDescribeWithTx(dto);
	}

	public int updateMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto){
		
		return merchantProdDescribeWriteService.updateMerchantProdDescribeWithTx(dto);
	}

	public int deleteMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto){
		
		return merchantProdDescribeWriteService.deleteMerchantProdDescribeWithTx(dto);
		
	}

}
	