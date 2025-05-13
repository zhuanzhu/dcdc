package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.RoleUrlManage;
import com.egeo.components.user.facade.RoleUrlFacade;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.web.JsonResult;

@Service("roleUrl")
public class RoleUrlManageImpl implements RoleUrlManage{

	
	@Resource(name="roleUrlFacade")
	private RoleUrlFacade roleUrlFacade;

	@Override
	public List<String> getUrlListByUserId(Long userId, Long platformId) {
		
		return roleUrlFacade.getUrlListByUserId(userId, platformId);
	}

    @Override
    public int upDate(String urls, Long roleId) {
        
        return roleUrlFacade.upDate(urls,roleId);
    }

    @Override
    public JsonResult<List<UrlDTO>> showUrl(Long roleId,Long platformId) {
        return roleUrlFacade.showUrl(roleId,platformId);
    }
    /**
	 * 根据类型和角色id添加角色和URL的关系
	 * @param roleId
	 * @param type
	 * @param platformId
	 * @return
	 */
	@Override
	public Integer saveUrlByTypeWithTx(Long roleId, Integer type, Long platformId) {
		// TODO Auto-generated method stub
		return roleUrlFacade.saveUrlByTypeWithTx(roleId, type, platformId);
	}
	


}
	