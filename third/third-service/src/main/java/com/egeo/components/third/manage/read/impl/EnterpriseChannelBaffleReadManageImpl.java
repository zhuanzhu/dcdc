package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.EnterpriseChannelBaffleReadDAO;
import com.egeo.components.third.manage.read.EnterpriseChannelBaffleReadManage;
import com.egeo.components.third.po.EnterpriseChannelBafflePO;
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
@Service("enterpriseChannelBaffleReadManage")
public class EnterpriseChannelBaffleReadManageImpl implements EnterpriseChannelBaffleReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseChannelBaffleReadDAO enterpriseChannelBaffleReadDAO;

    @Override
    public EnterpriseChannelBafflePO findEnterpriseChannelBaffleById(EnterpriseChannelBafflePO po) {
        EnterpriseChannelBafflePO enterpriseChannelBafflePO = new EnterpriseChannelBafflePO();
        enterpriseChannelBafflePO.setId(po.getId());
        return enterpriseChannelBaffleReadDAO.findById(enterpriseChannelBafflePO);
    }

    @Override
    public PageResult<EnterpriseChannelBafflePO> findEnterpriseChannelBaffleOfPage(EnterpriseChannelBafflePO po, Pagination page) {

        PageResult<EnterpriseChannelBafflePO> pageResult = new PageResult<>();
        List<EnterpriseChannelBafflePO> list = null;

        int cnt = enterpriseChannelBaffleReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = enterpriseChannelBaffleReadDAO.findOfPage(po, page);
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
    public List<EnterpriseChannelBafflePO> findEnterpriseChannelBaffleAll(EnterpriseChannelBafflePO po) {

        return enterpriseChannelBaffleReadDAO.findAll(po,null);
    }
}
