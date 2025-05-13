package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.dao.read.ChannelProductBatchReadDAO;
import com.egeo.components.product.manage.read.ChannelProductBatchReadManage;
import com.egeo.components.product.po.ChannelProductBatchPO;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
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
public class ChannelProductBatchReadManageImpl implements ChannelProductBatchReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductBatchReadDAO channelProductBatchReadDAO;

    @Override
    public ChannelProductBatchPO findChannelProductBatchById(ChannelProductBatchPO po) {
        ChannelProductBatchPO ChannelProductBatchPO = new ChannelProductBatchPO();
        ChannelProductBatchPO.setId(po.getId());
        return channelProductBatchReadDAO.findById(ChannelProductBatchPO);
    }

    @Override
    public PageResult<ChannelProductBatchPO> findChannelProductBatchOfPage(ChannelProductBatchPO po, Pagination page) {

        PageResult<ChannelProductBatchPO> pageResult = new PageResult<>();
        List<ChannelProductBatchPO> list = null;

        int cnt = channelProductBatchReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductBatchReadDAO.findOfPage(po, page);
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
    public List<ChannelProductBatchPO> findChannelProductBatchAll(ChannelProductBatchPO po) {

        return channelProductBatchReadDAO.findAll(po,null);
    }

    @Override
    public PageResult<ChannelSupplierProductResponseVO> findChannelProductOfPage(ChannelSupplierProductRequestVO vo, Pagination page){

        PageResult<ChannelSupplierProductResponseVO> pageResult = new PageResult<>();
        List<ChannelSupplierProductResponseVO> list = null;

        int cnt = channelProductBatchReadDAO.countChannelProductBatchOfPage(vo);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductBatchReadDAO.findChannelProductBatchOfPage(vo, page);
        } else {
            list = new ArrayList<>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
}
