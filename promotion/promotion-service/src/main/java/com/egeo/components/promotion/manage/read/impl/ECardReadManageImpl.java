package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.read.ECardReadDAO;
import com.egeo.components.promotion.manage.read.ECardReadManage;
import com.egeo.components.promotion.po.ECardPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ECardReadManageImpl implements ECardReadManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ECardReadDAO eCardReadDAO;

    public ECardPO findECardById(ECardPO po) {
        ECardPO eCardpo = new ECardPO();
        eCardpo.setId(po.getId());
        return eCardReadDAO.findById(eCardpo);
    }

    public PageResult<ECardPO> findECardOfPage(ECardPO po, Pagination page) {

        PageResult<ECardPO> pageResult = new PageResult<ECardPO>();
        List<ECardPO> list = null;

        int cnt = eCardReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = eCardReadDAO.findOfPage(po, page);
        } else {
            list = new ArrayList<ECardPO>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;

    }

    public List<ECardPO> findECardAll(ECardPO po) {

        return eCardReadDAO.findAll(po,null);
    }

    @Override
    public List<ECardPO> queryECardListByKey(Map<String, Object> keys) {
        return eCardReadDAO.queryECardListByKey(keys);
    }

}
