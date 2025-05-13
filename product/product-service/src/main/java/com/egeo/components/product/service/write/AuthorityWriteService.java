package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.AuthorityDTO;


public interface AuthorityWriteService {

	public Long insertAuthorityWithTx(AuthorityDTO dto);

	public int updateAuthorityWithTx(AuthorityDTO dto);

	public int deleteAuthorityWithTx(AuthorityDTO dto);
}
	