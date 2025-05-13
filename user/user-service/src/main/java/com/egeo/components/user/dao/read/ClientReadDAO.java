package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.ClientPO;
import com.egeo.orm.BaseReadDAO;

public interface ClientReadDAO extends BaseReadDAO<ClientPO>{
	/**
	 * 根据所属平台id集合查询所属平台信息
	 * @param clientIds
	 * @return
	 */
	List<ClientPO> findClientByClientIds(@Param("ids")List<Long> clientIds);
	/**
	 * 根据运营方Id集合查询运营方名称
	 * @param clientIdList
	 * @return
	 */
	List<String> clientNameByClientIds(@Param("ids")List<Long> clientIdList);
}
	