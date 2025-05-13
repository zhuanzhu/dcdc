package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.ChannelProductReadDAO;
import com.egeo.components.product.manage.read.ChannelProductReadManage;
import com.egeo.components.product.po.ChannelProductPO;
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
public class ChannelProductReadManageImpl implements ChannelProductReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductReadDAO channelProductReadDAO;

    @Override
    public ChannelProductPO findChannelProductById(ChannelProductPO po) {
        ChannelProductPO ChannelProductPO = new ChannelProductPO();
        ChannelProductPO.setId(po.getId());
        return channelProductReadDAO.findById(ChannelProductPO);
    }

    @Override
    public PageResult<ChannelProductPO> findChannelProductOfPage(ChannelProductPO po, Pagination page) {

        PageResult<ChannelProductPO> pageResult = new PageResult<>();
        List<ChannelProductPO> list = null;

        int cnt = channelProductReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductReadDAO.findOfPage(po, page);
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
    public List<ChannelProductPO> findChannelProductAll(ChannelProductPO po) {

        return channelProductReadDAO.findAll(po,null);
    }
}
