package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdClientReadService;
import com.egeo.components.product.service.write.MerchantProdClientWriteService;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdClientFacade {
	
	@Resource
	private MerchantProdClientReadService merchantProdClientReadService;
	
	@Resource
	private MerchantProdClientWriteService merchantProdClientWriteService;
	
	
	public MerchantProdClientDTO findMerchantProdClientById(MerchantProdClientDTO dto){
		
		return merchantProdClientReadService.findMerchantProdClientById(dto);
	}

	public PageResult<MerchantProdClientDTO> findMerchantProdClientOfPage(MerchantProdClientDTO dto,Pagination page){
		
		return merchantProdClientReadService.findMerchantProdClientOfPage(dto, page);
		
	}

	public List<MerchantProdClientDTO> findMerchantProdClientAll(MerchantProdClientDTO dto){
		
		return merchantProdClientReadService.findMerchantProdClientAll(dto);
		
	}

	public Long insertMerchantProdClientWithTx(MerchantProdClientDTO dto){
		
		return merchantProdClientWriteService.insertMerchantProdClientWithTx(dto);
	}

	public int updateMerchantProdClientWithTx(MerchantProdClientDTO dto){
		
		return merchantProdClientWriteService.updateMerchantProdClientWithTx(dto);
	}

	public int deleteMerchantProdClientWithTx(MerchantProdClientDTO dto){
		
		return merchantProdClientWriteService.deleteMerchantProdClientWithTx(dto);
		
	}
	/**
	 * 根据su草稿与客户端的关系id集合批量删除su草稿与客户端的关系
	 * @param merchantProdClientIds
	 * @return
	 */
	public int deleteByMerchantProdClientIdsWithTx(List<Long> merchantProdClientIds) {
		// TODO Auto-generated method stub
		return merchantProdClientWriteService.deleteByMerchantProdClientIdsWithTx(merchantProdClientIds);
	}

}
	