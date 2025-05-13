package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.dao.read.ChannelProductSkuReadDAO;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.manage.read.ChannelProductSkuReadManage;
import com.egeo.components.product.po.ChannelProductSkuPO;
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
public class ChannelProductSkuReadManageImpl implements ChannelProductSkuReadManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductSkuReadDAO channelProductSkuReadDAO;

    @Override
    public ChannelProductSkuPO findChannelProductSkuById(ChannelProductSkuPO po) {
        ChannelProductSkuPO ChannelProductSkuPO = new ChannelProductSkuPO();
        ChannelProductSkuPO.setId(po.getId());
        return channelProductSkuReadDAO.findById(ChannelProductSkuPO);
    }

    @Override
    public PageResult<ChannelProductSkuPO> findChannelProductSkuOfPage(ChannelProductSkuPO po, Pagination page) {

        PageResult<ChannelProductSkuPO> pageResult = new PageResult<>();
        List<ChannelProductSkuPO> list = null;

        int cnt = channelProductSkuReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductSkuReadDAO.findOfPage(po, page);
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
    public List<ChannelProductSkuPO> findChannelProductSkuAll(ChannelProductSkuPO po) {

        return channelProductSkuReadDAO.findAll(po,null);
    }

    @Override
    public List<ChannelProductSkuPO> findChannelProductSkuBySkuIds(List<String> skuList,String channelCode){
        return channelProductSkuReadDAO.findChannelProductSkuBySkuIds(skuList,channelCode);
    }
    @Override
    public PageResult<ChannelProductAndSkuCondition> getChannelProductAndSkuListOfPage(ChannelProductAndSkuListDTO dto, Pagination page){
        //return channelProductSkuReadDAO.getChannelProductAndSkuListOfPage(dto,page);

        PageResult<ChannelProductAndSkuCondition> pageResult = new PageResult<>();
        List<ChannelProductAndSkuCondition> list = null;

        int cnt = channelProductSkuReadDAO.getChannelProductAndSkuListCountOfPage(dto);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductSkuReadDAO.getChannelProductAndSkuListOfPage(dto, page);
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
    public List<ChannelProductSkuPO> findChannelProductSkuBySkuCodes(List<String> skuList,String channelCode){
        return channelProductSkuReadDAO.findChannelProductSkuBySkuCodes(skuList,channelCode);
    }
}
