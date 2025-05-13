package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ImagetextGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImagetextGroupReadManage {

	public ImagetextGroupPO findImagetextGroupById(ImagetextGroupPO po);

	public PageResult<ImagetextGroupPO> findImagetextGroupOfPage(ImagetextGroupPO po,Pagination page);

	public List<ImagetextGroupPO> findImagetextGroupAll(ImagetextGroupPO po);

	/**
	 * 根据实例id查询图文组件组
	 * @param instId
	 * @return
	 */
	public ImagetextGroupPO queryImagetextGroupByInstId(Long instId, Integer groupType);
}
	