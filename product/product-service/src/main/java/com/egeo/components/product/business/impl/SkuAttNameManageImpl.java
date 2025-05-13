package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SkuAttNameManage;
import com.egeo.components.product.facade.SkuAttNameFacade;
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("skuAttName")
public class SkuAttNameManageImpl implements SkuAttNameManage{

	
	@Resource(name="skuAttNameFacade")
	private SkuAttNameFacade skuAttNameFacade;

	@Override
	public SkuAttNameDTO findSkuAttNameById(SkuAttNameDTO dto) {
		return skuAttNameFacade.findSkuAttNameById(dto);
	}

	@Override
	public PageResult<SkuAttNameDTO> findSkuAttNameOfPage(SkuAttNameDTO dto, Pagination page) {
		return skuAttNameFacade.findSkuAttNameOfPage(dto, page);
	}

	@Override
	public List<SkuAttNameDTO> findSkuAttNameAll(SkuAttNameDTO dto) {
		return skuAttNameFacade.findSkuAttNameAll(dto);
	}

	@Override
	public Long insertSkuAttNameWithTx(SkuAttNameDTO dto) {
		return skuAttNameFacade.insertSkuAttNameWithTx(dto);
	}

	@Override
	public int updateSkuAttNameWithTx(SkuAttNameDTO dto) {
		return skuAttNameFacade.updateSkuAttNameWithTx(dto);
	}

	@Override
	public int deleteSkuAttNameWithTx(SkuAttNameDTO dto) {
		return skuAttNameFacade.deleteSkuAttNameWithTx(dto);
	}


}
	