package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductMembershipManage;
import com.egeo.components.product.facade.MerchantProductMembershipFacade;
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductMembership")
public class MerchantProductMembershipManageImpl implements MerchantProductMembershipManage{

	
	@Resource(name="merchantProductMembershipFacade")
	private MerchantProductMembershipFacade merchantProductMembershipFacade;

	@Override
	public MerchantProductMembershipDTO findMerchantProductMembershipById(MerchantProductMembershipDTO dto) {
		return merchantProductMembershipFacade.findMerchantProductMembershipById(dto);
	}

	@Override
	public PageResult<MerchantProductMembershipDTO> findMerchantProductMembershipOfPage(MerchantProductMembershipDTO dto, Pagination page) {
		return merchantProductMembershipFacade.findMerchantProductMembershipOfPage(dto, page);
	}

	@Override
	public List<MerchantProductMembershipDTO> findMerchantProductMembershipAll(MerchantProductMembershipDTO dto) {
		return merchantProductMembershipFacade.findMerchantProductMembershipAll(dto);
	}

	@Override
	public Long insertMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto) {
		return merchantProductMembershipFacade.insertMerchantProductMembershipWithTx(dto);
	}

	@Override
	public int updateMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto) {
		return merchantProductMembershipFacade.updateMerchantProductMembershipWithTx(dto);
	}

	@Override
	public int deleteMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto) {
		return merchantProductMembershipFacade.deleteMerchantProductMembershipWithTx(dto);
	}


}
	