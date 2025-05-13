package com.egeo.components.user.dao.read;

import com.egeo.components.user.po.RoleMenuPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuReadDAO extends BaseReadDAO<RoleMenuPO>{

    List<RoleMenuPO> findRoleMenuListByUserId(@Param("userId")Long userId,@Param("platformId")Long platformId);
}
	