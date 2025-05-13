package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.VersionsChildCondition;
import com.egeo.components.user.po.VersionsChildPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface VersionsChildReadDAO extends BaseReadDAO<VersionsChildPO>{
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	List<VersionsChildCondition> versionsChildAndChannelOfPage(@Param("po")VersionsChildPO po, @Param("page")Pagination page);
}
	