package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdAttValueReadService;
import com.egeo.components.product.service.write.MerchantProdAttValueWriteService;
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdAttValueFacade {
	
	@Resource
	private MerchantProdAttValueReadService merchantProdAttValueReadService;
	
	@Resource
	private MerchantProdAttValueWriteService merchantProdAttValueWriteService;
	
	
	public MerchantProdAttValueDTO findMerchantProdAttValueById(MerchantProdAttValueDTO dto){
		
		return merchantProdAttValueReadService.findMerchantProdAttValueById(dto);
	}

	public PageResult<MerchantProdAttValueDTO> findMerchantProdAttValueOfPage(MerchantProdAttValueDTO dto,Pagination page){
		
		return merchantProdAttValueReadService.findMerchantProdAttValueOfPage(dto, page);
		
	}

	public List<MerchantProdAttValueDTO> findMerchantProdAttValueAll(MerchantProdAttValueDTO dto){
		
		return merchantProdAttValueReadService.findMerchantProdAttValueAll(dto);
		
	}

	public Long insertMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto){
		
		return merchantProdAttValueWriteService.insertMerchantProdAttValueWithTx(dto);
	}

	public int updateMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto){
		
		return merchantProdAttValueWriteService.updateMerchantProdAttValueWithTx(dto);
	}

	public int deleteMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto){
		
		return merchantProdAttValueWriteService.deleteMerchantProdAttValueWithTx(dto);
		
	}

    public void insertList(List<String> my) {

		merchantProdAttValueWriteService.insertList(my);
    }
}
	