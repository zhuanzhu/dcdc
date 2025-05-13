package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.UrlCondition;
import com.egeo.components.user.po.UrlPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UrlReadManage {

    List<String> findAll();

    List<UrlPO> getUrlByRoleId(Long roleId);

    PageResult<UrlCondition> findPage(Pagination page, UrlPO po);
    
    List<UrlPO> findAll(UrlPO urlPO);

    UrlPO findByName(String name);

    UrlPO findById(Long id);

}
