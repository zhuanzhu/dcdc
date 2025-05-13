package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.VideoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface VideoReadManage {

	public VideoPO findVideoById(VideoPO po);

	public PageResult<VideoPO> findVideoOfPage(VideoPO po,Pagination page);

	public List<VideoPO> findVideoAll(VideoPO po);
}
	