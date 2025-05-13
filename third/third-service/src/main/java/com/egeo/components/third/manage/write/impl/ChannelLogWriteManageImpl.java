package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.dao.write.ChannelLogWriteDAO;
import com.egeo.components.third.manage.write.ChannelLogWriteManage;
import com.egeo.components.third.po.ChannelLogPO;
import org.springframework.stereotype.Service;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:16
 * @Version V1.0
 **/
@Service
public class ChannelLogWriteManageImpl implements ChannelLogWriteManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelLogWriteDAO channelLogWriteDAO;

    @Override
    public Long insertChannelLogWithTx(ChannelLogPO po) {

        int i ;
        try {
            i = channelLogWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateChannelLogWithTx(ChannelLogPO po) {
        int i;
        i = channelLogWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteChannelLogWithTx(ChannelLogPO po) {
        int i;
        i = channelLogWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
