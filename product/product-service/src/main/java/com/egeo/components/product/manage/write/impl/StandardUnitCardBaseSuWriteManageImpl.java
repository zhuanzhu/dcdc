package com.egeo.components.product.manage.write.impl;

import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.dao.write.StandardUnitCardBaseSuWriteDAO;
import com.egeo.components.product.manage.write.StandardUnitCardBaseSuWriteManage;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
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
public class StandardUnitCardBaseSuWriteManageImpl implements StandardUnitCardBaseSuWriteManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StandardUnitCardBaseSuWriteDAO standardUnitCardBaseSuWriteDAO;

    @Override
    public Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuPO po) {

        int i ;
        try {
            i = standardUnitCardBaseSuWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuPO po) {
        int i;
        i = standardUnitCardBaseSuWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuPO po) {
        int i;
        i = standardUnitCardBaseSuWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
