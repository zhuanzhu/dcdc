package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.user.vo.UrlVO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UrlManage {

    public List<String> getUrlList();

    public int saveOrUpdate(UrlVO vo);

    public void deleteWithTx(UrlDTO dto);

    public List<UrlDTO> getUrlByRoleId(Long roleId);

    public PageResult<UrlDTO> findAll(Pagination page,UrlDTO urlDTO);

    List<UrlDTO> findAll(UrlDTO urlDTO);
    
    UrlDTO findUrlById(Long id);
    
}
