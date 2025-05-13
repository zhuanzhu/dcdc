package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.dao.write.ChannelProductWriteDAO;
import com.egeo.components.product.manage.write.ChannelProductWriteManage;
import com.egeo.components.product.po.ChannelProductPO;
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
public class ChannelProductWriteManageImpl implements ChannelProductWriteManage {



    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductWriteDAO channelProductWriteDAO;

    @Override
    public Long insertChannelProductWithTx(ChannelProductPO po) {

        int i ;
        try {
            i = channelProductWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateChannelProductWithTx(ChannelProductPO po) {
        int i;
        i = channelProductWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteChannelProductWithTx(ChannelProductPO po) {
        int i;
        i = channelProductWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
