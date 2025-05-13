package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdClientManage;
import com.egeo.components.product.facade.MerchantProdClientFacade;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdClient")
public class MerchantProdClientManageImpl implements MerchantProdClientManage{

	
	@Resource(name="merchantProdClientFacade")
	private MerchantProdClientFacade merchantProdClientFacade;

	@Override
	public MerchantProdClientDTO findMerchantProdClientById(MerchantProdClientDTO dto) {
		return merchantProdClientFacade.findMerchantProdClientById(dto);
	}

	@Override
	public PageResult<MerchantProdClientDTO> findMerchantProdClientOfPage(MerchantProdClientDTO dto, Pagination page) {
		return merchantProdClientFacade.findMerchantProdClientOfPage(dto, page);
	}

	@Override
	public List<MerchantProdClientDTO> findMerchantProdClientAll(MerchantProdClientDTO dto) {
		return merchantProdClientFacade.findMerchantProdClientAll(dto);
	}

	@Override
	public Long insertMerchantProdClientWithTx(MerchantProdClientDTO dto) {
		return merchantProdClientFacade.insertMerchantProdClientWithTx(dto);
	}

	@Override
	public int updateMerchantProdClientWithTx(MerchantProdClientDTO dto) {
		return merchantProdClientFacade.updateMerchantProdClientWithTx(dto);
	}

	@Override
	public int deleteMerchantProdClientWithTx(MerchantProdClientDTO dto) {
		return merchantProdClientFacade.deleteMerchantProdClientWithTx(dto);
	}


}
	