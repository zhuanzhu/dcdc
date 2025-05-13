package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.UrlReadService;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.components.user.dao.read.UrlReadDAO;
import com.egeo.components.user.converter.UrlConverter;
import com.egeo.components.user.condition.UrlCondition;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("urlReadService")
public class UrlReadServiceImpl implements UrlReadService {
	@Autowired
	private UrlReadDAO urlReadDAO ;

    @Override
    public List<String> findAll() {
        
        List<UrlPO> poList = urlReadDAO.findAll(new UrlPO(),null);
        List<String> ls = new ArrayList<String>();
        for (UrlPO po : poList) {
            ls.add(po.getUrl());
        }
        return ls;
    }

    @Override
    public List<UrlDTO> getUrlByRoleId(Long roleId) {
        List<UrlPO> list = urlReadDAO.getUrlByRoleId(roleId);
        return UrlConverter.toDTO(list);
    }

    @Override
    public PageResult<UrlDTO> findAll(Pagination page, UrlDTO urlDTO) {
        UrlPO po = UrlConverter.toPO(urlDTO);
        int cnt = urlReadDAO.countOfPage(po);
        List<UrlCondition> listT = new ArrayList<UrlCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            listT = urlReadDAO.findUrlConditionOfPage(po, page);
        }
        PageResult<UrlCondition> pageResult = new PageResult<UrlCondition>();
        pageResult.setList(listT);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
		
        List<UrlDTO> list = new ArrayList<UrlDTO>();
        for (UrlCondition tmp : pageResult.getList()) {
            list.add(UrlConverter.conditionToDTO(tmp));
        }
        PageResult<UrlDTO> result = new PageResult<UrlDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }
    
    @Override
    public List<UrlDTO> findAll(UrlDTO urlDTO) {
    	UrlPO po = UrlConverter.toPO(urlDTO);
    	return UrlConverter.toDTO(urlReadDAO.findAll(po,null));
    }

    @Override
    public UrlDTO findUrlById(Long id) {
        UrlPO po = new UrlPO();
        po.setId(id);
    	return UrlConverter.toDTO(urlReadDAO.findById(po));
    }
    
}
