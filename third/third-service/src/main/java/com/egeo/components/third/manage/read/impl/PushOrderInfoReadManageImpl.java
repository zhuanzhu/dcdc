package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.ChannelServiceConfigReadDAO;
import com.egeo.components.third.dao.read.PushOrderInfoReadDAO;
import com.egeo.components.third.manage.read.PushOrderInfoReadManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.PushOrderInfoPO;
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
public class PushOrderInfoReadManageImpl implements PushOrderInfoReadManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PushOrderInfoReadDAO pushOrderInfoReadDAO;

    @Override
    public PushOrderInfoPO findPushOrderInfoById(PushOrderInfoPO po) {
        PushOrderInfoPO pushOrderInfoPO = new PushOrderInfoPO();
        pushOrderInfoPO.setId(po.getId());
        return pushOrderInfoReadDAO.findById(pushOrderInfoPO);
    }

    @Override
    public PageResult<PushOrderInfoPO> findPushOrderInfoOfPage(PushOrderInfoPO po, Pagination page) {

        PageResult<PushOrderInfoPO> pageResult = new PageResult<>();
        List<PushOrderInfoPO> list = null;

        int cnt = pushOrderInfoReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = pushOrderInfoReadDAO.findOfPage(po, page);
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
    public List<PushOrderInfoPO> findPushOrderInfoAll(PushOrderInfoPO po) {

        return pushOrderInfoReadDAO.findAll(po,null);
    }
}
