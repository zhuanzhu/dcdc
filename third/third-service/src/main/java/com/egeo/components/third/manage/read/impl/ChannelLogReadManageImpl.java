package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.ChannelLogReadDAO;
import com.egeo.components.third.manage.read.ChannelLogReadManage;
import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.orm.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 13:56
 * @Version V1.0
 **/
@Service
public class ChannelLogReadManageImpl implements ChannelLogReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelLogReadDAO channelLogReadDAO;

    @Override
    public ChannelLogPO findChannelLogById(ChannelLogPO po) {
        ChannelLogPO attributeNameDecimalpo = new ChannelLogPO();
        attributeNameDecimalpo.setId(po.getId());
        return channelLogReadDAO.findById(attributeNameDecimalpo);
    }

    @Override
    public PageResult<ChannelLogPO> findChannelLogOfPage(ChannelLogPO po, Pagination page) {

        PageResult<ChannelLogPO> pageResult = new PageResult<>();
        List<ChannelLogPO> list = null;

        int cnt = channelLogReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelLogReadDAO.findOfPage(po, page);
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
    public List<ChannelLogPO> findChannelLogAll(ChannelLogPO po) {

        return channelLogReadDAO.findAll(po,null);
    }
}
