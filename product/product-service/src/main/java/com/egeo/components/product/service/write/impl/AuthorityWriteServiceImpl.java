package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.AuthorityWriteService;
import com.egeo.components.product.manage.write.AuthorityWriteManage;
import com.egeo.components.product.converter.AuthorityConverter;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.components.product.po.AuthorityPO;

@Service("authorityWriteService")
public class AuthorityWriteServiceImpl  implements AuthorityWriteService {
	@Autowired
	private AuthorityWriteManage authorityWriteManage;

	@Override
	public Long insertAuthorityWithTx(AuthorityDTO dto) {
		AuthorityPO po = AuthorityConverter.toPO(dto);
		Long rt = authorityWriteManage.insertAuthorityWithTx(po);		
		return rt;
	}

	@Override
	public int updateAuthorityWithTx(AuthorityDTO dto) {
		AuthorityPO po = AuthorityConverter.toPO(dto);
		int rt = authorityWriteManage.updateAuthorityWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAuthorityWithTx(AuthorityDTO dto) {
		AuthorityPO po = AuthorityConverter.toPO(dto);
		int rt = authorityWriteManage.deleteAuthorityWithTx(po);		
		return rt;
	}
}
	