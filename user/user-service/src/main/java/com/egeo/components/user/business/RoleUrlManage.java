package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.user.dto.UrlDTO;
import com.egeo.web.JsonResult;

public interface RoleUrlManage {

	List<String> getUrlListByUserId(Long userId, Long platformId);

    int upDate(String urls, Long roleId);

    JsonResult<List<UrlDTO>> showUrl(Long roleId,Long platformId);
    /**
	 * 根据类型和角色id添加角色和URL的关系
	 * @param roleId
	 * @param type
	 * @param platformId
	 * @return
	 */
	Integer saveUrlByTypeWithTx(Long roleId, Integer type, Long platformId);
	

}
	