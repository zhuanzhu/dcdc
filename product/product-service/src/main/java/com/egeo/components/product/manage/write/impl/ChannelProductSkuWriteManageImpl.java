package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.dao.write.ChannelProductSkuWriteDAO;
import com.egeo.components.product.manage.write.ChannelProductSkuWriteManage;
import com.egeo.components.product.po.ChannelProductSkuPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class ChannelProductSkuWriteManageImpl implements ChannelProductSkuWriteManage {



    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductSkuWriteDAO channelProductSkuWriteDAO;

    @Override
    public Long insertChannelProductSkuWithTx(ChannelProductSkuPO po) {

        int i ;
        try {
            i = channelProductSkuWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateChannelProductSkuWithTx(ChannelProductSkuPO po) {
        int i;
        i = channelProductSkuWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteChannelProductSkuWithTx(ChannelProductSkuPO po) {
        int i;
        i = channelProductSkuWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }

    @Override
    public int updateChannelProductSkuStateWithTx(List<String> skuIdList,String channelCode) {
        int i;
        i = channelProductSkuWriteDAO.updateChannelProductSkuState(skuIdList,channelCode);
       /* if (i == 0)
            throw new BusinessException("未能成功更新数据!");*/
        return i;
    }
}
