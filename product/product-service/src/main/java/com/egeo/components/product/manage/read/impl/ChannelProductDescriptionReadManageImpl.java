package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.ChannelProductDescriptionReadDAO;
import com.egeo.components.product.manage.read.ChannelProductDescriptionReadManage;
import com.egeo.components.product.po.ChannelProductDescriptionPO;
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
public class ChannelProductDescriptionReadManageImpl implements ChannelProductDescriptionReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductDescriptionReadDAO channelProductDescriptionReadDAO;

    @Override
    public ChannelProductDescriptionPO findChannelProductDescriptionById(ChannelProductDescriptionPO po) {
        ChannelProductDescriptionPO ChannelProductDescriptionPO = new ChannelProductDescriptionPO();
        ChannelProductDescriptionPO.setId(po.getId());
        return channelProductDescriptionReadDAO.findById(ChannelProductDescriptionPO);
    }

    @Override
    public PageResult<ChannelProductDescriptionPO> findChannelProductDescriptionOfPage(ChannelProductDescriptionPO po, Pagination page) {

        PageResult<ChannelProductDescriptionPO> pageResult = new PageResult<>();
        List<ChannelProductDescriptionPO> list = null;

        int cnt = channelProductDescriptionReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductDescriptionReadDAO.findOfPage(po, page);
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
    public List<ChannelProductDescriptionPO> findChannelProductDescriptionAll(ChannelProductDescriptionPO po) {

        return channelProductDescriptionReadDAO.findAll(po,null);
    }
}
