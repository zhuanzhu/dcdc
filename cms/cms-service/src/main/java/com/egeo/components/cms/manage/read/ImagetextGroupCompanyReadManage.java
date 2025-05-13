package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ImagetextGroupCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImagetextGroupCompanyReadManage {

	public ImagetextGroupCompanyPO findImagetextGroupCompanyById(ImagetextGroupCompanyPO po);

	public PageResult<ImagetextGroupCompanyPO> findImagetextGroupCompanyOfPage(ImagetextGroupCompanyPO po,Pagination page);

	public List<ImagetextGroupCompanyPO> findImagetextGroupCompanyAll(ImagetextGroupCompanyPO po);
}
	