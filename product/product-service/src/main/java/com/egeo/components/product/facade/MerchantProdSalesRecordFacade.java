package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdSalesRecordReadService;
import com.egeo.components.product.service.write.MerchantProdSalesRecordWriteService;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdSalesRecordFacade {
	
	@Resource
	private MerchantProdSalesRecordReadService merchantProdSalesRecordReadService;
	
	@Resource
	private MerchantProdSalesRecordWriteService merchantProdSalesRecordWriteService;
	
	
	public MerchantProdSalesRecordDTO findMerchantProdSalesRecordById(MerchantProdSalesRecordDTO dto){
		
		return merchantProdSalesRecordReadService.findMerchantProdSalesRecordById(dto);
	}

	public PageResult<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordDTO dto,Pagination page){
		
		return merchantProdSalesRecordReadService.findMerchantProdSalesRecordOfPage(dto, page);
		
	}

	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordDTO dto){
		
		return merchantProdSalesRecordReadService.findMerchantProdSalesRecordAll(dto);
		
	}

	public Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto){
		
		return merchantProdSalesRecordWriteService.insertMerchantProdSalesRecordWithTx(dto);
	}

	public int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto){
		
		return merchantProdSalesRecordWriteService.updateMerchantProdSalesRecordWithTx(dto);
	}

	public int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto){
		
		return merchantProdSalesRecordWriteService.deleteMerchantProdSalesRecordWithTx(dto);
		
	}
	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	public Long findSalesRecordByStandardUnitId(Long standardUnitId) {
		
		return merchantProdSalesRecordReadService.findSalesRecordByStandardUnitId(standardUnitId);
	}

}
	