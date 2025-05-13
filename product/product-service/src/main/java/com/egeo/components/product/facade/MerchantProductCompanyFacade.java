package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProductCompanyReadService;
import com.egeo.components.product.service.write.MerchantProductCompanyWriteService;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProductCompanyFacade {
	
	@Resource
	private MerchantProductCompanyReadService merchantProductCompanyReadService;
	
	@Resource
	private MerchantProductCompanyWriteService merchantProductCompanyWriteService;
	
	
	public MerchantProductCompanyDTO findMerchantProductCompanyById(MerchantProductCompanyDTO dto){
		
		return merchantProductCompanyReadService.findMerchantProductCompanyById(dto);
	}

	public PageResult<MerchantProductCompanyDTO> findMerchantProductCompanyOfPage(MerchantProductCompanyDTO dto,Pagination page){
		
		return merchantProductCompanyReadService.findMerchantProductCompanyOfPage(dto, page);
		
	}

	public List<MerchantProductCompanyDTO> findMerchantProductCompanyAll(MerchantProductCompanyDTO dto){
		
		return merchantProductCompanyReadService.findMerchantProductCompanyAll(dto);
		
	}

	public Long insertMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto){
		
		return merchantProductCompanyWriteService.insertMerchantProductCompanyWithTx(dto);
	}

	public int updateMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto){
		
		return merchantProductCompanyWriteService.updateMerchantProductCompanyWithTx(dto);
	}

	public int deleteMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto){
		
		return merchantProductCompanyWriteService.deleteMerchantProductCompanyWithTx(dto);
		
	}
	/**
	 * 根据拼接的su草稿企业关系id集合批量删除
	 * @param merchantProductCompanyIds
	 * @return
	 */
	public int deleteByMerchantProductCompanyIdsWithTx(List<Long> merchantProductCompanyIds) {
		// TODO Auto-generated method stub
		return merchantProductCompanyWriteService.deleteByMerchantProductCompanyIdsWithTx(merchantProductCompanyIds);
	}

}
	