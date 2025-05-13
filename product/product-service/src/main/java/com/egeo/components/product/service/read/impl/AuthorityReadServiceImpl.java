package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.AuthorityReadService;
import com.egeo.components.product.manage.read.AuthorityReadManage;
import com.egeo.components.product.converter.AuthorityConverter;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.components.product.po.AuthorityPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("authorityReadService")
public class AuthorityReadServiceImpl  implements AuthorityReadService {
	@Autowired
	private AuthorityReadManage authorityReadManage;

	@Override
	public AuthorityDTO findAuthorityById(AuthorityDTO dto) {
		AuthorityPO po = AuthorityConverter.toPO(dto);
		AuthorityPO list = authorityReadManage.findAuthorityById(po);		
		return AuthorityConverter.toDTO(list);
	}

	@Override
	public PageResult<AuthorityDTO> findAuthorityOfPage(AuthorityDTO dto, Pagination page) {
		AuthorityPO po = AuthorityConverter.toPO(dto);
        PageResult<AuthorityPO> pageResult = authorityReadManage.findAuthorityOfPage(po, page);
        
        List<AuthorityDTO> list = AuthorityConverter.toDTO(pageResult.getList());
        PageResult<AuthorityDTO> result = new PageResult<AuthorityDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AuthorityDTO> findAuthorityAll(AuthorityDTO dto) {
		AuthorityPO po = AuthorityConverter.toPO(dto);
		List<AuthorityPO> list = authorityReadManage.findAuthorityAll(po);		
		return AuthorityConverter.toDTO(list);
	}
}
	