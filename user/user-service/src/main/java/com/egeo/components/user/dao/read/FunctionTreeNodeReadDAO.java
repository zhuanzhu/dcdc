package com.egeo.components.user.dao.read;

import com.egeo.components.user.po.FunctionTreeNodePO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionTreeNodeReadDAO extends BaseReadDAO<FunctionTreeNodePO>{

    List<UrlPO> findUrlList(@Param("id") Long id);


}
	