package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.dao.write.ChannelProductBatchWriteDAO;
import com.egeo.components.product.manage.write.ChannelProductBatchWriteManage;
import com.egeo.components.product.po.ChannelProductBatchPO;
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
public class ChannelProductBatchWriteManageImpl implements ChannelProductBatchWriteManage {



    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductBatchWriteDAO channelProductBatchWriteDAO;

    @Override
    public Long insertChannelProductBatchWithTx(ChannelProductBatchPO po) {

        int i ;
        try {
            i = channelProductBatchWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateChannelProductBatchWithTx(ChannelProductBatchPO po) {
        int i;
        i = channelProductBatchWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteChannelProductBatchWithTx(ChannelProductBatchPO po) {
        int i;
        i = channelProductBatchWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
    @Override
    public int updateChannelBatchSkuState(List<String> skuIdList, String channelCode){
        return channelProductBatchWriteDAO.updateChannelBatchSkuState(skuIdList,channelCode);
    }
}
