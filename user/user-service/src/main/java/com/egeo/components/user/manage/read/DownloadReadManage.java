package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.DownloadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DownloadReadManage {

	public DownloadPO findDownloadById(DownloadPO po);

	public PageResult<DownloadPO> findDownloadOfPage(DownloadPO po,Pagination page);

	public List<DownloadPO> findDownloadAll(DownloadPO po);

	public PageResult<DownloadPO> findDownloadOfPageByBlurry(DownloadPO po, Pagination page);
}
	