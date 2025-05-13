package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SkuAttValueManage;
import com.egeo.components.product.facade.SkuAttValueFacade;
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("skuAttValue")
public class SkuAttValueManageImpl implements SkuAttValueManage{

	
	@Resource(name="skuAttValueFacade")
	private SkuAttValueFacade skuAttValueFacade;

	@Override
	public SkuAttValueDTO findSkuAttValueById(SkuAttValueDTO dto) {
		return skuAttValueFacade.findSkuAttValueById(dto);
	}

	@Override
	public PageResult<SkuAttValueDTO> findSkuAttValueOfPage(SkuAttValueDTO dto, Pagination page) {
		return skuAttValueFacade.findSkuAttValueOfPage(dto, page);
	}

	@Override
	public List<SkuAttValueDTO> findSkuAttValueAll(SkuAttValueDTO dto) {
		return skuAttValueFacade.findSkuAttValueAll(dto);
	}

	@Override
	public Long insertSkuAttValueWithTx(SkuAttValueDTO dto) {
		return skuAttValueFacade.insertSkuAttValueWithTx(dto);
	}

	@Override
	public int updateSkuAttValueWithTx(SkuAttValueDTO dto) {
		return skuAttValueFacade.updateSkuAttValueWithTx(dto);
	}

	@Override
	public int deleteSkuAttValueWithTx(SkuAttValueDTO dto) {
		return skuAttValueFacade.deleteSkuAttValueWithTx(dto);
	}


}
	