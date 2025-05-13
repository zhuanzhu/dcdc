package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.StandardUnitCardBaseSuReadDAO;
import com.egeo.components.product.manage.read.StandardUnitCardBaseSuReadManage;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class StandardUnitCardBaseSuReadManageImpl implements StandardUnitCardBaseSuReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StandardUnitCardBaseSuReadDAO standardUnitCardBaseSuReadDAO;

    @Override
    public StandardUnitCardBaseSuPO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuPO po) {
        StandardUnitCardBaseSuPO StandardUnitCardBaseSuPO = new StandardUnitCardBaseSuPO();
        StandardUnitCardBaseSuPO.setId(po.getId());
        return standardUnitCardBaseSuReadDAO.findById(StandardUnitCardBaseSuPO);
    }

    @Override
    public PageResult<StandardUnitCardBaseSuPO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuPO po, Pagination page) {

        PageResult<StandardUnitCardBaseSuPO> pageResult = new PageResult<>();
        List<StandardUnitCardBaseSuPO> list = null;

        int cnt = standardUnitCardBaseSuReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitCardBaseSuReadDAO.findOfPage(po, page);
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
    public List<StandardUnitCardBaseSuPO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuPO po) {

        return standardUnitCardBaseSuReadDAO.findAll(po,null);
    }

    @Override
    public  int findStandardUnitCardBaseSuNumberMax(Long cardBaseId){

        return standardUnitCardBaseSuReadDAO.findStandardUnitCardBaseSuNumberMax(cardBaseId);
    }

    /**
     * 根据卡片id查询su商品数量
     * @param cardBaseId
     * @return
     */
    @Override
    public Integer findStandardUnitSize(Long cardBaseId) {
        // TODO Auto-generated method stub
        return standardUnitCardBaseSuReadDAO.findStandardUnitSize(cardBaseId);
    }

    @Override
    public List<StandardUnitCardBaseSuPO> syncChannelSellState(Integer source,
                                                       Date endCheckTime,
                                                       int size){
        return standardUnitCardBaseSuReadDAO.syncChannelSellState(source,endCheckTime,size);
    }
}
