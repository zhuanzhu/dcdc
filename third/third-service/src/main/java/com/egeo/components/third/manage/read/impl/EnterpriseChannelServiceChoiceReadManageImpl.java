package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.ChannelServiceConfigReadDAO;
import com.egeo.components.third.dao.read.EnterpriseChannelServiceChoiceReadDAO;
import com.egeo.components.third.manage.read.EnterpriseChannelServiceChoiceReadManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;
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
public class EnterpriseChannelServiceChoiceReadManageImpl implements EnterpriseChannelServiceChoiceReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseChannelServiceChoiceReadDAO enterpriseChannelServiceChoiceReadDAO;

    @Override
    public EnterpriseChannelServiceChoicePO findEnterpriseChannelServiceChoiceById(EnterpriseChannelServiceChoicePO po) {
        EnterpriseChannelServiceChoicePO attributeNameDecimalpo = new EnterpriseChannelServiceChoicePO();
        attributeNameDecimalpo.setId(po.getId());
        return enterpriseChannelServiceChoiceReadDAO.findById(attributeNameDecimalpo);
    }

    @Override
    public PageResult<EnterpriseChannelServiceChoicePO> findEnterpriseChannelServiceChoiceOfPage(EnterpriseChannelServiceChoicePO po, Pagination page) {

        PageResult<EnterpriseChannelServiceChoicePO> pageResult = new PageResult<>();
        List<EnterpriseChannelServiceChoicePO> list = null;

        int cnt = enterpriseChannelServiceChoiceReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = enterpriseChannelServiceChoiceReadDAO.findOfPage(po, page);
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
    public List<EnterpriseChannelServiceChoicePO> findEnterpriseChannelServiceChoiceAll(EnterpriseChannelServiceChoicePO po) {

        return enterpriseChannelServiceChoiceReadDAO.findAll(po,null);
    }
}
