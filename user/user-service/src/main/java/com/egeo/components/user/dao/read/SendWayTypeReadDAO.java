package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.orm.BaseReadDAO;

public interface SendWayTypeReadDAO extends BaseReadDAO<SendWayTypePO>{

	List<String> findSendWayTypeByInfoId(@Param("infoId")Long infoId);
}
	