package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.common.BusinessException;
import com.egeo.components.third.dao.write.SplitOrderConfigWriteDAO;
import com.egeo.components.third.manage.write.SplitOrderConfigWriteManage;
import com.egeo.components.third.po.SplitOrderConfigPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class SplitOrderConfigWriteManageImpl implements SplitOrderConfigWriteManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SplitOrderConfigWriteDAO splitOrderConfigWriteDAO;

    @Override
    public Long insertSplitOrderConfigWithTx(SplitOrderConfigPO po) {

        int i ;
        try {
            i = splitOrderConfigWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateSplitOrderConfigWithTx(SplitOrderConfigPO po) {
        int i;
        i = splitOrderConfigWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteSplitOrderConfigWithTx(SplitOrderConfigPO po) {
        int i;
        i = splitOrderConfigWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
