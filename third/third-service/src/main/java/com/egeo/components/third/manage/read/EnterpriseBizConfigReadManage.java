package com.egeo.components.third.manage.read;

import com.egeo.components.third.po.EnterpriseBizConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseBizConfigReadManage {

    public EnterpriseBizConfigPO findEnterpriseBizConfigById(EnterpriseBizConfigPO po);

    public PageResult<EnterpriseBizConfigPO> findEnterpriseBizConfigOfPage(EnterpriseBizConfigPO po, Pagination page);

    public List<EnterpriseBizConfigPO> findEnterpriseBizConfigAll(EnterpriseBizConfigPO po);
}
