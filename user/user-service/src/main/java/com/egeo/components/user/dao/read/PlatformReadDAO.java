package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.PlatformPO;
import com.egeo.orm.BaseReadDAO;

public interface PlatformReadDAO extends BaseReadDAO<PlatformPO>{

    List<PlatformPO> PlatformByUserId(@Param("useId")Long useId);

    PlatformPO find(@Param("pid")Long pid);

    List<PlatformPO> PlatformByUid(@Param("userId")Long userId);
    
    List<PlatformPO> findAllPlatform();
}
	