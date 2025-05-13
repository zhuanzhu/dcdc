package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductStoreManage;
import com.egeo.components.product.facade.MerchantProductStoreFacade;
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductStore")
public class MerchantProductStoreManageImpl implements MerchantProductStoreManage{

	
	@Resource(name="merchantProductStoreFacade")
	private MerchantProductStoreFacade merchantProductStoreFacade;

	@Override
	public MerchantProductStoreDTO findMerchantProductStoreById(MerchantProductStoreDTO dto) {
		return merchantProductStoreFacade.findMerchantProductStoreById(dto);
	}

	@Override
	public PageResult<MerchantProductStoreDTO> findMerchantProductStoreOfPage(MerchantProductStoreDTO dto, Pagination page) {
		return merchantProductStoreFacade.findMerchantProductStoreOfPage(dto, page);
	}

	@Override
	public List<MerchantProductStoreDTO> findMerchantProductStoreAll(MerchantProductStoreDTO dto) {
		return merchantProductStoreFacade.findMerchantProductStoreAll(dto);
	}

	@Override
	public Long insertMerchantProductStoreWithTx(MerchantProductStoreDTO dto) {
		return merchantProductStoreFacade.insertMerchantProductStoreWithTx(dto);
	}

	@Override
	public int updateMerchantProductStoreWithTx(MerchantProductStoreDTO dto) {
		return merchantProductStoreFacade.updateMerchantProductStoreWithTx(dto);
	}

	@Override
	public int deleteMerchantProductStoreWithTx(MerchantProductStoreDTO dto) {
		return merchantProductStoreFacade.deleteMerchantProductStoreWithTx(dto);
	}


}
	