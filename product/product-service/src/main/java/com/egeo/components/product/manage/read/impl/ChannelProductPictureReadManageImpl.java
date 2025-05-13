package com.egeo.components.product.manage.read.impl;

import com.egeo.components.product.condition.ChannelProductPicCondition;
import com.egeo.components.product.dao.read.ChannelProductPictureReadDAO;
import com.egeo.components.product.manage.read.ChannelProductPictureReadManage;
import com.egeo.components.product.po.ChannelProductPicturePO;
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
public class ChannelProductPictureReadManageImpl implements ChannelProductPictureReadManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductPictureReadDAO channelProductPictureReadDAO;

    @Override
    public ChannelProductPicturePO findChannelProductPictureById(ChannelProductPicturePO po) {
        ChannelProductPicturePO ChannelProductPicturePO = new ChannelProductPicturePO();
        ChannelProductPicturePO.setId(po.getId());
        return channelProductPictureReadDAO.findById(ChannelProductPicturePO);
    }

    @Override
    public PageResult<ChannelProductPicturePO> findChannelProductPictureOfPage(ChannelProductPicturePO po, Pagination page) {

        PageResult<ChannelProductPicturePO> pageResult = new PageResult<>();
        List<ChannelProductPicturePO> list = null;

        int cnt = channelProductPictureReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = channelProductPictureReadDAO.findOfPage(po, page);
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
    public List<ChannelProductPicturePO> findChannelProductPictureAll(ChannelProductPicturePO po) {

        return channelProductPictureReadDAO.findAll(po,null);
    }

    @Override
    public List<ChannelProductPicturePO> findChannelPicByProductIds(List<String> productIds, String channelCode){
        return channelProductPictureReadDAO.findChannelPicByProductIds(productIds,channelCode);
    }
}
