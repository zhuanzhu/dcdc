package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ImagetextPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImagetextReadManage {

	public ImagetextPO findImagetextById(ImagetextPO po);

	public PageResult<ImagetextPO> findImagetextOfPage(ImagetextPO po,Pagination page);

	public List<ImagetextPO> findImagetextAll(ImagetextPO po);

	/**
	 * 根据组id查询图文组件
	 * @param groupId
	 * @return
	 */
	public List<ImagetextPO> queryImagetextByGroupId(Long groupId);

}
	