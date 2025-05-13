package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductTagManage;
import com.egeo.components.product.facade.MerchantProductTagFacade;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductTag")
public class MerchantProductTagManageImpl implements MerchantProductTagManage{

	
	@Resource(name="merchantProductTagFacade")
	private MerchantProductTagFacade merchantProductTagFacade;

	@Override
	public MerchantProductTagDTO findMerchantProductTagById(MerchantProductTagDTO dto) {
		return merchantProductTagFacade.findMerchantProductTagById(dto);
	}

	@Override
	public PageResult<MerchantProductTagDTO> findMerchantProductTagOfPage(MerchantProductTagDTO dto, Pagination page) {
		return merchantProductTagFacade.findMerchantProductTagOfPage(dto, page);
	}

	@Override
	public List<MerchantProductTagDTO> findMerchantProductTagAll(MerchantProductTagDTO dto) {
		return merchantProductTagFacade.findMerchantProductTagAll(dto);
	}

	@Override
	public Long insertMerchantProductTagWithTx(MerchantProductTagDTO dto) {
		return merchantProductTagFacade.insertMerchantProductTagWithTx(dto);
	}

	@Override
	public int updateMerchantProductTagWithTx(MerchantProductTagDTO dto) {
		return merchantProductTagFacade.updateMerchantProductTagWithTx(dto);
	}

	@Override
	public int deleteMerchantProductTagWithTx(MerchantProductTagDTO dto) {
		return merchantProductTagFacade.deleteMerchantProductTagWithTx(dto);
	}


}
	