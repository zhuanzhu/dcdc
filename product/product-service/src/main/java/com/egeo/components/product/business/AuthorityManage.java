package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AuthorityManage {

	public AuthorityDTO findAuthorityById(AuthorityDTO dto);	

	public PageResult<AuthorityDTO> findAuthorityOfPage(AuthorityDTO dto,Pagination page);

	public List<AuthorityDTO> findAuthorityAll(AuthorityDTO dto);

	Long insertAuthorityWithTx(AuthorityDTO dto);

	int updateAuthorityWithTx(AuthorityDTO dto);

	int deleteAuthorityWithTx(AuthorityDTO dto);
}
	