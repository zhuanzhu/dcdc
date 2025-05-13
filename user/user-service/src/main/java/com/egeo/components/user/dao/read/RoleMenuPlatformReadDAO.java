package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.RoleMenuPlatformPO;
import com.egeo.components.user.po.UserPlatformPO;
import com.egeo.orm.BaseReadDAO;

public interface RoleMenuPlatformReadDAO extends BaseReadDAO<RoleMenuPlatformPO>{

	List<String> getMenuList(@Param("po") UserPlatformPO po);

	List<String> getMenuByUserPlatform(@Param("po") UserPlatformPO po);
}
	