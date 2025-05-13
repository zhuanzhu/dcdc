package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProductCompanyReadService;
import com.egeo.components.product.manage.read.MerchantProductCompanyReadManage;
import com.egeo.components.product.converter.MerchantProductCompanyConverter;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.components.product.po.MerchantProductCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductCompanyReadService")
public class MerchantProductCompanyReadServiceImpl  implements MerchantProductCompanyReadService {
	@Autowired
	private MerchantProductCompanyReadManage merchantProductCompanyReadManage;

	@Override
	public MerchantProductCompanyDTO findMerchantProductCompanyById(MerchantProductCompanyDTO dto) {
		MerchantProductCompanyPO po = MerchantProductCompanyConverter.toPO(dto);
		MerchantProductCompanyPO list = merchantProductCompanyReadManage.findMerchantProductCompanyById(po);		
		return MerchantProductCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProductCompanyDTO> findMerchantProductCompanyOfPage(MerchantProductCompanyDTO dto, Pagination page) {
		MerchantProductCompanyPO po = MerchantProductCompanyConverter.toPO(dto);
        PageResult<MerchantProductCompanyPO> pageResult = merchantProductCompanyReadManage.findMerchantProductCompanyOfPage(po, page);
        
        List<MerchantProductCompanyDTO> list = MerchantProductCompanyConverter.toDTOs(pageResult.getList());
        PageResult<MerchantProductCompanyDTO> result = new PageResult<MerchantProductCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProductCompanyDTO> findMerchantProductCompanyAll(MerchantProductCompanyDTO dto) {
		MerchantProductCompanyPO po = MerchantProductCompanyConverter.toPO(dto);
		List<MerchantProductCompanyPO> list = merchantProductCompanyReadManage.findMerchantProductCompanyAll(po);		
		return MerchantProductCompanyConverter.toDTOs(list);
	}
}
	