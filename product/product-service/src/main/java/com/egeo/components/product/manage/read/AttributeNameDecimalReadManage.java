package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AttributeNameDecimalReadManage {

	public AttributeNameDecimalPO findAttributeNameDecimalById(AttributeNameDecimalPO po);

	public PageResult<AttributeNameDecimalPO> findAttributeNameDecimalOfPage(AttributeNameDecimalPO po,Pagination page);

	public List<AttributeNameDecimalPO> findAttributeNameDecimalAll(AttributeNameDecimalPO po);
}
	