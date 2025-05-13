package com.egeo.components.third.manage.read.impl;

import com.egeo.components.third.dao.read.SplitOrderConfigReadDAO;
import com.egeo.components.third.manage.read.SplitOrderConfigReadManage;
import com.egeo.components.third.po.SplitOrderConfigPO;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
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
 * @Date 2024/7/16 14:39
 * @Version V1.0
 **/
@Service
public class SplitOrderConfigReadManageImpl implements SplitOrderConfigReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SplitOrderConfigReadDAO splitOrderConfigReadDAO;

    @Override
    public SplitOrderConfigPO findSplitOrderConfigById(SplitOrderConfigPO po) {
        SplitOrderConfigPO SplitOrderConfigPO = new SplitOrderConfigPO();
        SplitOrderConfigPO.setId(po.getId());
        return splitOrderConfigReadDAO.findById(SplitOrderConfigPO);
    }

    @Override
    public PageResult<SplitOrderConfigPO> findSplitOrderConfigOfPage(SplitOrderConfigPO po, Pagination page) {

        PageResult<SplitOrderConfigPO> pageResult = new PageResult<>();
        List<SplitOrderConfigPO> list = null;

        int cnt = splitOrderConfigReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = splitOrderConfigReadDAO.findOfPage(po, page);
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
    public List<SplitOrderConfigPO> findSplitOrderConfigAll(SplitOrderConfigPO po) {

        return splitOrderConfigReadDAO.findAll(po,null);
    }
}
