package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.AuthorityPO;


public interface AuthorityWriteManage {

	Long insertAuthorityWithTx(AuthorityPO po);

	int updateAuthorityWithTx(AuthorityPO po);

	int deleteAuthorityWithTx(AuthorityPO po);
}
	