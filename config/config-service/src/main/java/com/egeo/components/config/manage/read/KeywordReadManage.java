package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.KeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface KeywordReadManage {

	public KeywordPO findKeywordById(KeywordPO po);

	public PageResult<KeywordPO> findKeywordOfPage(KeywordPO po,Pagination page);

	public List<KeywordPO> findKeywordAll(KeywordPO po);
}
	