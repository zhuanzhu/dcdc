package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductCompanyManage;
import com.egeo.components.product.facade.MerchantProductCompanyFacade;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductCompany")
public class MerchantProductCompanyManageImpl implements MerchantProductCompanyManage{

	
	@Resource(name="merchantProductCompanyFacade")
	private MerchantProductCompanyFacade merchantProductCompanyFacade;

	@Override
	public MerchantProductCompanyDTO findMerchantProductCompanyById(MerchantProductCompanyDTO dto) {
		return merchantProductCompanyFacade.findMerchantProductCompanyById(dto);
	}

	@Override
	public PageResult<MerchantProductCompanyDTO> findMerchantProductCompanyOfPage(MerchantProductCompanyDTO dto, Pagination page) {
		return merchantProductCompanyFacade.findMerchantProductCompanyOfPage(dto, page);
	}

	@Override
	public List<MerchantProductCompanyDTO> findMerchantProductCompanyAll(MerchantProductCompanyDTO dto) {
		return merchantProductCompanyFacade.findMerchantProductCompanyAll(dto);
	}

	@Override
	public Long insertMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto) {
		return merchantProductCompanyFacade.insertMerchantProductCompanyWithTx(dto);
	}

	@Override
	public int updateMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto) {
		return merchantProductCompanyFacade.updateMerchantProductCompanyWithTx(dto);
	}

	@Override
	public int deleteMerchantProductCompanyWithTx(MerchantProductCompanyDTO dto) {
		return merchantProductCompanyFacade.deleteMerchantProductCompanyWithTx(dto);
	}


}
	