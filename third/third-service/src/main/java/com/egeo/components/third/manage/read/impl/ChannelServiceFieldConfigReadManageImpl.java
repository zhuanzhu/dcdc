package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.ChannelServiceFieldConfigReadDAO;
import com.egeo.components.third.manage.read.ChannelServiceFieldConfigReadManage;
import com.egeo.components.third.po.ChannelServiceFieldConfigPO;
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
public class ChannelServiceFieldConfigReadManageImpl implements ChannelServiceFieldConfigReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelServiceFieldConfigReadDAO channelServiceFieldConfigReadDAO;

    @Override
    public ChannelServiceFieldConfigPO findChannelServiceFieldConfigById(ChannelServiceFieldConfigPO po) {
        ChannelServiceFieldConfigPO attributeNameDecimalpo = new ChannelServiceFieldConfigPO();
        attributeNameDecimalpo.setId(po.getId());
        return channelServiceFieldConfigReadDAO.findById(attributeNameDecimalpo);
    }

    @Override
    public PageResult<ChannelServiceFieldConfigPO> findChannelServiceFieldConfigOfPage(ChannelServiceFieldConfigPO po, Pagination page) {

        PageResult<ChannelServiceFieldConfigPO> pageResult = new PageResult<>();
        List<ChannelServiceFieldConfigPO> list = null;

        int cnt = channelServiceFieldConfigReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelServiceFieldConfigReadDAO.findOfPage(po, page);
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
    public List<ChannelServiceFieldConfigPO> findChannelServiceFieldConfigAll(ChannelServiceFieldConfigPO po) {

        return channelServiceFieldConfigReadDAO.findAll(po,null);
    }
}
