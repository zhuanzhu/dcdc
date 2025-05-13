package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UrlManage;
import com.egeo.components.user.converter.UrlConverter;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.facade.UrlFacade;
import com.egeo.components.user.vo.UrlVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;

@Service("url")
public class UrlManageImpl implements UrlManage{

	public Logger logger = LoggerFactory.getLogger(UrlManageImpl.class);
	
	@Resource(name="urlFacade")
	private UrlFacade urlFacade;
	
	@Resource
	private JedisUtil cache;
	
	@Override
	public List<String> getUrlList() {

		return urlFacade.findAll();
	}


	@Override
	public int saveOrUpdate(UrlVO vo) {
		return urlFacade.saveOrUpdate(UrlConverter.toDTO(vo));
	}


	@Override
	public void deleteWithTx(UrlDTO dto) {
		//根据urlid删除与其关联的角色
		
		urlFacade.deleteWithTx(dto);
	}


    @Override
    public List<UrlDTO> getUrlByRoleId(Long roleId) {
        
        return urlFacade.getUrlByRoleId(roleId);
    }

    
    @Override
    public PageResult<UrlDTO> findAll(Pagination page,UrlDTO urlDTO) {
        
        return urlFacade.findAll(page,urlDTO);
    }

    @Override
    public List<UrlDTO> findAll(UrlDTO urlDTO) {
    	return urlFacade.findAll(urlDTO);
    }
	
    @Override
    public UrlDTO findUrlById(Long id) {
    	return urlFacade.findUrlById(id);
    }
}
	