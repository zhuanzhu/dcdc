package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.AuthorityReadService;
import com.egeo.components.product.service.write.AuthorityWriteService;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class AuthorityFacade {
	
	@Resource
	private AuthorityReadService authorityReadService;
	
	@Resource
	private AuthorityWriteService authorityWriteService;
	
	
	public AuthorityDTO findAuthorityById(AuthorityDTO dto){
		
		return authorityReadService.findAuthorityById(dto);
	}

	public PageResult<AuthorityDTO> findAuthorityOfPage(AuthorityDTO dto,Pagination page){
		
		return authorityReadService.findAuthorityOfPage(dto, page);
		
	}

	public List<AuthorityDTO> findAuthorityAll(AuthorityDTO dto){
		
		return authorityReadService.findAuthorityAll(dto);
		
	}

	public Long insertAuthorityWithTx(AuthorityDTO dto){
		
		return authorityWriteService.insertAuthorityWithTx(dto);
	}

	public int updateAuthorityWithTx(AuthorityDTO dto){
		
		return authorityWriteService.updateAuthorityWithTx(dto);
	}

	public int deleteAuthorityWithTx(AuthorityDTO dto){
		
		return authorityWriteService.deleteAuthorityWithTx(dto);
		
	}

}
	