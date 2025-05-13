package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.DownloadPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface DownloadReadDAO extends BaseReadDAO<DownloadPO>{

	int countOfPageByBlurry(@Param("po") DownloadPO po);

	List<DownloadPO> findOfPageByBlurry(@Param("po") DownloadPO po, @Param("page")Pagination page);
}
	