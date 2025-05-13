package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.ChannelCategoryReadDAO;
import com.egeo.components.product.dao.read.ChannelProductReadDAO;
import com.egeo.components.product.manage.read.ChannelCategoryReadManage;
import com.egeo.components.product.manage.read.ChannelProductReadManage;
import com.egeo.components.product.po.ChannelCategoryPO;
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
public class ChannelCategoryReadManageImpl implements ChannelCategoryReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelCategoryReadDAO channelCategoryReadDAO;

    @Override
    public ChannelCategoryPO findChannelCategoryById(ChannelCategoryPO po) {
        ChannelCategoryPO ChannelCategoryPO = new ChannelCategoryPO();
        ChannelCategoryPO.setId(po.getId());
        return channelCategoryReadDAO.findById(ChannelCategoryPO);
    }

    @Override
    public PageResult<ChannelCategoryPO> findChannelCategoryOfPage(ChannelCategoryPO po, Pagination page) {

        PageResult<ChannelCategoryPO> pageResult = new PageResult<>();
        List<ChannelCategoryPO> list = null;

        int cnt = channelCategoryReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelCategoryReadDAO.findOfPage(po, page);
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
    public List<ChannelCategoryPO> findChannelCategoryAll(ChannelCategoryPO po) {

        return channelCategoryReadDAO.findAll(po,null);
    }
}
