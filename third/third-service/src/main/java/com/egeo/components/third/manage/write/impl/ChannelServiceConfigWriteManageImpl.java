package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.dao.write.ChannelServiceConfigWriteDAO;
import com.egeo.components.third.manage.write.ChannelServiceConfigWriteManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.exception.BusinessException;
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
public class ChannelServiceConfigWriteManageImpl implements ChannelServiceConfigWriteManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelServiceConfigWriteDAO channelServiceConfigWriteDAO;

    @Override
    public Long insertChannelServiceConfigWithTx(ChannelServiceConfigPO po) {

        int i ;
        try {
            i = channelServiceConfigWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateChannelServiceConfigWithTx(ChannelServiceConfigPO po) {
        int i;
        i = channelServiceConfigWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteChannelServiceConfigWithTx(ChannelServiceConfigPO po) {
        int i;
        i = channelServiceConfigWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
