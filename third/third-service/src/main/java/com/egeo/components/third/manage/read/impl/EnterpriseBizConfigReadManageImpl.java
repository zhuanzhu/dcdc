package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.ChannelServiceConfigReadDAO;
import com.egeo.components.third.dao.read.EnterpriseBizConfigReadDAO;
import com.egeo.components.third.manage.read.EnterpriseBizConfigReadManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.EnterpriseBizConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class EnterpriseBizConfigReadManageImpl implements EnterpriseBizConfigReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseBizConfigReadDAO enterpriseBizConfigReadDAO;

    @Override
    public EnterpriseBizConfigPO findEnterpriseBizConfigById(EnterpriseBizConfigPO po) {
        EnterpriseBizConfigPO attributeNameDecimalpo = new EnterpriseBizConfigPO();
        attributeNameDecimalpo.setId(po.getId());
        return enterpriseBizConfigReadDAO.findById(attributeNameDecimalpo);
    }

    @Override
    public PageResult<EnterpriseBizConfigPO> findEnterpriseBizConfigOfPage(EnterpriseBizConfigPO po, Pagination page) {

        PageResult<EnterpriseBizConfigPO> pageResult = new PageResult<>();
        List<EnterpriseBizConfigPO> list = null;

        int cnt = enterpriseBizConfigReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = enterpriseBizConfigReadDAO.findOfPage(po, page);
        } else {
            list = new ArrayList<>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;

    }

    @Override
    public List<EnterpriseBizConfigPO> findEnterpriseBizConfigAll(EnterpriseBizConfigPO po) {

        return enterpriseBizConfigReadDAO.findAll(po,null);
    }
}
