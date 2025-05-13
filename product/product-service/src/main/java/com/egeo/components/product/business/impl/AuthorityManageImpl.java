package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.AuthorityManage;
import com.egeo.components.product.facade.AuthorityFacade;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("authority")
public class AuthorityManageImpl implements AuthorityManage{

	
	@Resource(name="authorityFacade")
	private AuthorityFacade authorityFacade;

	@Override
	public AuthorityDTO findAuthorityById(AuthorityDTO dto) {
		return authorityFacade.findAuthorityById(dto);
	}

	@Override
	public PageResult<AuthorityDTO> findAuthorityOfPage(AuthorityDTO dto, Pagination page) {
		return authorityFacade.findAuthorityOfPage(dto, page);
	}

	@Override
	public List<AuthorityDTO> findAuthorityAll(AuthorityDTO dto) {
		return authorityFacade.findAuthorityAll(dto);
	}

	@Override
	public Long insertAuthorityWithTx(AuthorityDTO dto) {
		return authorityFacade.insertAuthorityWithTx(dto);
	}

	@Override
	public int updateAuthorityWithTx(AuthorityDTO dto) {
		return authorityFacade.updateAuthorityWithTx(dto);
	}

	@Override
	public int deleteAuthorityWithTx(AuthorityDTO dto) {
		return authorityFacade.deleteAuthorityWithTx(dto);
	}


}
	