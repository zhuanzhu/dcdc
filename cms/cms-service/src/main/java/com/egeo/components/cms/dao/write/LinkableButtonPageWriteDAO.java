package com.egeo.components.cms.dao.write;

import java.util.List;

import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.orm.BaseWriteDAO;

public interface LinkableButtonPageWriteDAO extends BaseWriteDAO<LinkableButtonPagePO> {
	
	public int insertBatch(List<LinkableButtonPagePO> linkableButtonPagePOList);
}
	