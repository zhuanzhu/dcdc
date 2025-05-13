package com.egeo.components.user.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.RolePO;
import com.egeo.orm.BaseReadDAO;

public interface RoleReadDAO extends BaseReadDAO<RolePO> {

    List<RolePO> findRoleListByUserId(@Param("po") RolePO po);

    List<RolePO> findRoleListByParam(@Param("po") RolePO po,@Param("maxCreateDate") Date maxCreateDate,@Param("minCreateDate") Date minCreateDate);

    int cntRoleListByParam(@Param("po") RolePO po,@Param("maxCreateDate") Date maxCreateDate,@Param("minCreateDate") Date minCreateDate);
    
    List<RolePO> roleByPlatformId(Long platformId);

    List<RolePO> roleListByUserId(Long id);
}
