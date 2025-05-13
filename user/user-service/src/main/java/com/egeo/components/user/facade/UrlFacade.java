package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.service.read.UrlReadService;
import com.egeo.components.user.service.write.RoleUrlWriteService;
import com.egeo.components.user.service.write.UrlWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UrlFacade {
	
	@Resource
	private UrlReadService urlReadService;
	
	@Resource
	private UrlWriteService urlWriteService;
	
	@Resource
	private RoleUrlWriteService roleUrlWriteService;

	public List<String> findAll() {
		
		return urlReadService.findAll();
	}

	public int saveOrUpdate(UrlDTO dto) {
		return urlWriteService.saveOrUpdateWithTx(dto);
	}

	public void deleteWithTx(UrlDTO dto) {
		urlWriteService.deleteWithTx(dto);
	}

    public List<UrlDTO> getUrlByRoleId(Long roleId) {
        
        return urlReadService.getUrlByRoleId(roleId);
    }

    public PageResult<UrlDTO> findAll(Pagination page,UrlDTO urlDTO) {
        
        return urlReadService.findAll(page,urlDTO);
    }
    
    private String delRoleUrlByUrlId(Long urlId){
    	
		return roleUrlWriteService.delRoleUrlByUrlId(urlId);
    	
    }
	
    public List<UrlDTO> findAll(UrlDTO urlDTO) {
    	return urlReadService.findAll(urlDTO);
    }

    public UrlDTO findUrlById(Long id) {
    	return urlReadService.findUrlById(id);
    }
    
}
	