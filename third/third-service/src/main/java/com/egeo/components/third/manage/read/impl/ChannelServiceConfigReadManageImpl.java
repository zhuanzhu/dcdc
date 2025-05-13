package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.ChannelServiceConfigReadDAO;
import com.egeo.components.third.manage.read.ChannelServiceConfigReadManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
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
public class ChannelServiceConfigReadManageImpl implements ChannelServiceConfigReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelServiceConfigReadDAO channelServiceConfigReadDAO;

    @Override
    public ChannelServiceConfigPO findChannelServiceConfigById(ChannelServiceConfigPO po) {
        ChannelServiceConfigPO attributeNameDecimalpo = new ChannelServiceConfigPO();
        attributeNameDecimalpo.setId(po.getId());
        return channelServiceConfigReadDAO.findById(attributeNameDecimalpo);
    }

    @Override
    public PageResult<ChannelServiceConfigPO> findChannelServiceConfigOfPage(ChannelServiceConfigPO po, Pagination page) {

        PageResult<ChannelServiceConfigPO> pageResult = new PageResult<>();
        List<ChannelServiceConfigPO> list = null;

        int cnt = channelServiceConfigReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelServiceConfigReadDAO.findOfPage(po, page);
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
    public List<ChannelServiceConfigPO> findChannelServiceConfigAll(ChannelServiceConfigPO po) {

        return channelServiceConfigReadDAO.findAll(po,null);
    }
}
