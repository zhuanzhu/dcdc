package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.ChannelProductTextReadDAO;
import com.egeo.components.product.manage.read.ChannelProductTextReadManage;
import com.egeo.components.product.po.ChannelProductTextPO;
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
public class ChannelProductTextReadManageImpl implements ChannelProductTextReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductTextReadDAO channelProductTextReadDAO;

    @Override
    public ChannelProductTextPO findChannelProductTextById(ChannelProductTextPO po) {
        ChannelProductTextPO ChannelProductTextPO = new ChannelProductTextPO();
        ChannelProductTextPO.setId(po.getId());
        return channelProductTextReadDAO.findById(ChannelProductTextPO);
    }

    @Override
    public PageResult<ChannelProductTextPO> findChannelProductTextOfPage(ChannelProductTextPO po, Pagination page) {

        PageResult<ChannelProductTextPO> pageResult = new PageResult<>();
        List<ChannelProductTextPO> list = null;

        int cnt = channelProductTextReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductTextReadDAO.findOfPage(po, page);
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
    public List<ChannelProductTextPO> findChannelProductTextAll(ChannelProductTextPO po) {

        return channelProductTextReadDAO.findAll(po,null);
    }
}
