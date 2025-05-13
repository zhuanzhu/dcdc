package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UrlReadManage;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.condition.UrlCondition;
import com.egeo.components.user.dao.read.UrlReadDAO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;

@Service
public class UrlReadManageImpl implements UrlReadManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlReadDAO urlReadDAO;

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
    public List<UrlPO> getUrlByRoleId(Long roleId) {
        
        return urlReadDAO.getUrlByRoleId(roleId);
    }

    @Override
    public PageResult<UrlCondition> findPage(Pagination page, UrlPO po) {
        int cnt = urlReadDAO.countOfPage(po);
        List<UrlCondition> list = new ArrayList<UrlCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = urlReadDAO.findUrlConditionOfPage(po, page);
        }
        PageResult<UrlCondition> pageResult = new PageResult<UrlCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    @Override
    public List<UrlPO> findAll(UrlPO urlPO) {

        return urlReadDAO.findAll(urlPO,null);
    }

    @Override
    public UrlPO findByName(String name) {
        UrlPO po = new UrlPO();
        po.setName(name);
        List<UrlPO> list = urlReadDAO.findAll(po,null);
        if (list.size() > 1) {
            throw new BusinessException(BusinessExceptionConstant.URL_NO_UNIQUE, "URL不唯一");
        }
        if(list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public UrlPO findById(Long id) {
        UrlPO po = new UrlPO();
        po.setId(id);
        return urlReadDAO.findById(po);
    }

}
