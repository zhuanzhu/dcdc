package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.service.read.RoleUrlReadService;
import com.egeo.components.user.service.write.RoleUrlWriteService;
import com.egeo.web.JsonResult;


@Component
public class RoleUrlFacade {
	
	@Resource
	private RoleUrlReadService roleUrlReadService;
	
	@Resource
	private RoleUrlWriteService roleUrlWriteService;
	

	public List<String> getUrlListByUserId(Long userId, Long platformId) {
		
		return roleUrlReadService.getUrlListByUserId(userId, platformId);
	}

    public int upDate(String urls, Long roleId) {
        
        return roleUrlWriteService.upDateWithTx(urls,roleId);
    }

    public JsonResult<List<UrlDTO>> showUrl(Long roleId,Long platformId) {
        
        return roleUrlReadService.showUrl(roleId,platformId);
    }
    /**
	 * 根据类型和角色id添加角色和URL的关系
	 * @param roleId
	 * @param type
	 * @param platformId
	 * @return
	 */
	public Integer saveUrlByTypeWithTx(Long roleId, Integer type, Long platformId) {
		// TODO Auto-generated method stub
		return roleUrlWriteService.saveUrlByTypeWithTx(roleId, type, platformId);
	}
	


}
	