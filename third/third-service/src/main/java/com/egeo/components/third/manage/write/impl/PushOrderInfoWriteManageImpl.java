package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.dao.write.PushOrderInfoWriteDAO;
import com.egeo.components.third.manage.write.PushOrderInfoWriteManage;
import com.egeo.components.third.po.PushOrderInfoPO;
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
public class PushOrderInfoWriteManageImpl implements PushOrderInfoWriteManage {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PushOrderInfoWriteDAO pushOrderInfoWriteDAO;

    @Override
    public Long insertPushOrderInfoWithTx(PushOrderInfoPO po) {

        int i ;
        try {
            i = pushOrderInfoWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updatePushOrderInfoWithTx(PushOrderInfoPO po) {
        int i;
        i = pushOrderInfoWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deletePushOrderInfoWithTx(PushOrderInfoPO po) {
        int i;
        i = pushOrderInfoWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
