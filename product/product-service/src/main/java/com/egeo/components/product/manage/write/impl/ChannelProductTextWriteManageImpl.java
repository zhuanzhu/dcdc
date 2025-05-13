package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.dao.write.ChannelProductTextWriteDAO;
import com.egeo.components.product.manage.write.ChannelProductTextWriteManage;
import com.egeo.components.product.po.ChannelProductTextPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class ChannelProductTextWriteManageImpl implements ChannelProductTextWriteManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductTextWriteDAO channelProductTextWriteDAO;

    @Override
    public Long insertChannelProductTextWithTx(ChannelProductTextPO po) {

        int i ;
        try {
            i = channelProductTextWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateChannelProductTextWithTx(ChannelProductTextPO po) {
        int i;
        i = channelProductTextWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteChannelProductTextWithTx(ChannelProductTextPO po) {
        int i;
        i = channelProductTextWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
