package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserRolePO;
import com.egeo.orm.BaseWriteDAO;

public interface UserRoleWriteDAO extends BaseWriteDAO<UserRolePO> {

    int deleteByUserAndRole(UserRolePO po);
    /**
     * 根据用户id删除管理员角色
     * @param id
     * @return
     */
	int delByUserId(@Param("userId")Long userId);
	
	int deleteByUser(@Param("userId")Long userId);
	

}
	