package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdCauseReadService;
import com.egeo.components.product.manage.read.MerchantProdCauseReadManage;
import com.egeo.components.product.converter.MerchantProdCauseConverter;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.components.product.po.MerchantProdCausePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdCauseReadService")
public class MerchantProdCauseReadServiceImpl  implements MerchantProdCauseReadService {
	@Autowired
	private MerchantProdCauseReadManage merchantProdCauseReadManage;

	@Override
	public MerchantProdCauseDTO findMerchantProdCauseById(MerchantProdCauseDTO dto) {
		MerchantProdCausePO po = MerchantProdCauseConverter.toPO(dto);
		MerchantProdCausePO list = merchantProdCauseReadManage.findMerchantProdCauseById(po);		
		return MerchantProdCauseConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdCauseDTO> findMerchantProdCauseOfPage(MerchantProdCauseDTO dto, Pagination page) {
		MerchantProdCausePO po = MerchantProdCauseConverter.toPO(dto);
        PageResult<MerchantProdCausePO> pageResult = merchantProdCauseReadManage.findMerchantProdCauseOfPage(po, page);
        
        List<MerchantProdCauseDTO> list = MerchantProdCauseConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdCauseDTO> result = new PageResult<MerchantProdCauseDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdCauseDTO> findMerchantProdCauseAll(MerchantProdCauseDTO dto) {
		MerchantProdCausePO po = MerchantProdCauseConverter.toPO(dto);
		List<MerchantProdCausePO> list = merchantProdCauseReadManage.findMerchantProdCauseAll(po);		
		return MerchantProdCauseConverter.toDTO(list);
	}
}
	