package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.EnterpriseChannelServiceReadDAO;
import com.egeo.components.third.manage.read.EnterpriseChannelServiceReadManage;
import com.egeo.components.third.po.EnterpriseChannelServicePO;
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
public class EnterpriseChannelServiceReadManageImpl implements EnterpriseChannelServiceReadManage {




    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseChannelServiceReadDAO enterpriseChannelServiceReadDAO;

    @Override
    public EnterpriseChannelServicePO findEnterpriseChannelServiceById(EnterpriseChannelServicePO po) {
        EnterpriseChannelServicePO attributeNameDecimalpo = new EnterpriseChannelServicePO();
        attributeNameDecimalpo.setId(po.getId());
        return enterpriseChannelServiceReadDAO.findById(attributeNameDecimalpo);
    }

    @Override
    public PageResult<EnterpriseChannelServicePO> findEnterpriseChannelServiceOfPage(EnterpriseChannelServicePO po, Pagination page) {

        PageResult<EnterpriseChannelServicePO> pageResult = new PageResult<>();
        List<EnterpriseChannelServicePO> list = null;

        int cnt = enterpriseChannelServiceReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = enterpriseChannelServiceReadDAO.findOfPage(po, page);
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
    public List<EnterpriseChannelServicePO> findEnterpriseChannelServiceAll(EnterpriseChannelServicePO po) {

        return enterpriseChannelServiceReadDAO.findAll(po,null);
    }
}
