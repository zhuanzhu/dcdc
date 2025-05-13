package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.JdPriceLimitUploadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdPriceLimitUploadReadManage {

	public JdPriceLimitUploadPO findJdPriceLimitUploadById(JdPriceLimitUploadPO po);

	public PageResult<JdPriceLimitUploadPO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadPO po,Pagination page);

	public List<JdPriceLimitUploadPO> findJdPriceLimitUploadAll(JdPriceLimitUploadPO po);
}
	