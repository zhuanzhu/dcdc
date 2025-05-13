package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.PicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PictureReadManage {

	public PicturePO findPictureById(PicturePO po);

	public PageResult<PicturePO> findPictureOfPage(PicturePO po,Pagination page);

	public List<PicturePO> findPictureAll(PicturePO po);
}
	