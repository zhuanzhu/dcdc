package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.UrlWhiteListCondition;
import com.egeo.components.user.po.UrlWhiteListPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UrlWhiteListReadManage {

	public UrlWhiteListPO findUrlWhiteListById(UrlWhiteListPO po);

	public PageResult<UrlWhiteListCondition> findUrlWhiteListOfPage(UrlWhiteListPO po,Pagination page);

	public List<UrlWhiteListCondition> findUrlWhiteListAll(UrlWhiteListPO po);

	public List<String> findUrlWhiteList(Long platformId);
}
	